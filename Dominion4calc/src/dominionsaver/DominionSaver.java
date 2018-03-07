package dominionsaver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DominionSaver {

	public static class SaveId implements Comparable<SaveId> {

		@Override
		public int compareTo(SaveId arg0) {
			int result = 0;
			Iterator<Entry<String, Long>> they = arg0.idMembers.entrySet().iterator();
			Iterator<Entry<String, Long>> us = this.idMembers.entrySet().iterator();
			while (they.hasNext() && us.hasNext()) {
				Entry<String, Long> my = us.next();
				Entry<String, Long> theirs = they.next();
				result = my.getKey().compareTo(theirs.getKey());
				if (result != 0) {
					return result;
				}
				long difference = my.getValue() - theirs.getValue();
				if (Math.abs(difference) > 2000) {
					return Long.signum(difference);
				}
			}
			if (they.hasNext()) {
				return 1;
			} else if (us.hasNext()) {
				return -1;
			} else {
				return 0;
			}
		}

		private SortedMap<String, Long> idMembers = new TreeMap<String, Long>();

		/**
		 * Adds a file to the save id.
		 */
		public void addFile(File fileName) {
			idMembers.put(fileName.getName(), fileName.lastModified());
		}

		@Override
		public boolean equals(Object arg0) {
			return arg0 instanceof SaveId && ((SaveId) arg0).compareTo(this) == 0;
		}

		@Override
		public int hashCode() {
			return 0;
		}

		public void addFile(ZipEntry ze) {
			idMembers.put(ze.getName(), ze.getTime());
			
		}
		@Override
		public String toString() {
			StringBuilder bdr = new StringBuilder();
			for(Entry<String, Long> x : idMembers.entrySet()) {
				bdr.append(x.getKey());
				bdr.append("/");
				bdr.append(x.getValue());
				bdr.append("; ");
			}
			return bdr.toString();
		}
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
	private static File savedGamesDir = new File("C:\\Users\\alexander\\AppData\\Roaming\\Dominions5\\savedgames");
	private static File compressedDir = new File("D:\\dominionSaves");

	private static SortedSet<SaveId> savedFilesId = new TreeSet<SaveId>();
	private static DominionSaverGui gui = new DominionSaverGui();
	public static void main(String[] args) throws ZipException, IOException, InterruptedException {
		gui.setVisible(true);
		checkCompressedDir(false);
		while (true) {
			while (!savedGamesDir.isDirectory()) {
				Thread.sleep(1000);
			}
			byte[] saveBuffer = null;
			Thread.sleep(2000);
			
			boolean needRefresh=false;
			synchronized (DominionSaver.class) {
				for (File gameDir : savedGamesDir.listFiles()) {
					boolean needSave = false;
					if (gameDir.isDirectory()) {
						File[] filesCount = gameDir.listFiles();
						if (filesCount.length > 2) {
							SaveId id = new SaveId();
							for (File ff : filesCount) {
								id.addFile(ff);
								
							}
							if(!savedFilesId.contains(id)) {
								needSave=true;
							}
						}
						if (needSave) {
							needRefresh=true;
							if (saveBuffer == null) {
								saveBuffer = new byte[(int) Math.min(Integer.MAX_VALUE,
										Runtime.getRuntime().freeMemory() / 4)];
							}
							File output = new File("D:\\dominionSaves\\" + gameDir.getName() + "."
									+ format.format(new Date()) + ".zip");
							SaveId saveId = new SaveId();
							try (FileOutputStream fo = new FileOutputStream(output);

									ZipOutputStream zipFile = new ZipOutputStream(fo)) {

								for (File ff : gameDir.listFiles()) {
									saveId.addFile(ff);
									ZipEntry zipEntry = new ZipEntry(ff.getName());
									zipEntry.setTime(ff.lastModified());
									zipEntry.setMethod(ZipEntry.DEFLATED);
									zipFile.putNextEntry(zipEntry);
									try (FileInputStream fi = new FileInputStream(ff)) {
										int length;
										do {
											length = fi.read(saveBuffer);
											if (length != -1) {
												zipFile.write(saveBuffer, 0, length);
											}
										} while (length != -1);
									}
									if (ff.getName().endsWith(".trn")) {

									}
									zipFile.closeEntry();
								}

							}
							gui.message(output + " saved");
							savedFilesId.add(saveId);
							
						}
					}
				}
				if(needRefresh) {
					checkCompressedDir(true);
				}
				
			}

		}
	}

	private synchronized static void checkCompressedDir(boolean force) {
			List<Object> list = new LinkedList<Object>();
			boolean needRefresh=false;
			gui.setStatus("Checking compressed files");
			for (File f : compressedDir.listFiles()) {
				SaveId id = new SaveId();
				StringBuilder lineToPrint = new StringBuilder();
				lineToPrint.append("[");
				lineToPrint.append(f.getName());
				lineToPrint.append("] ");
				gui.setStatus("Checking compressed files+");
				try (ZipInputStream zis = new ZipInputStream(new FileInputStream(f))) {
					ZipEntry ze;
					
					do {
						ze = zis.getNextEntry();
						if (ze != null) {
							id.addFile(ze);
							if (ze.getName().endsWith(".trn")) {
								lineToPrint.append("(");
								lineToPrint.append(ze.getName());
								byte[] b = new byte[15];
								if (zis.read(b) > 14) {
									lineToPrint.append(":");
									lineToPrint.append(b[14]);
									lineToPrint.append(")");
								}
							}
							if(ze.getName().equals("notes.txt")) {
								byte[] b = new byte[40];
							    int read = zis.read(b);
							    if(read > 0) {
							    	lineToPrint.append(":");
							    	lineToPrint.append(new String(b,0,read));
							    }
							}
						}
					} while (ze != null);
					if(savedFilesId.add(id)) {
						needRefresh=true;
						gui.setList(list.toArray());
					}
					list.add(lineToPrint.toString());
					gui.setStatus("Checking compressed files*");
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
			if(needRefresh || force) {
				gui.message("New files found");
				gui.setList(list.toArray());
			} else {
				gui.message("No need for list refresh");
			}
			gui.setStatus("Waiting");
		
	}

	private static final Matcher listLineMatcher = Pattern.compile("\\[(.*?)\\].*").matcher("");
	public static synchronized void restore(String line) { 
		String zipFile;
		listLineMatcher.reset(line);
		if(listLineMatcher.find()) {
			zipFile = listLineMatcher.group(1);
		} else {
			gui.message("Error: filename not found in >"  + line +"<");
			return;
		}
		
		File outputFolder = new File(compressedDir, zipFile);
		zipFile = outputFolder.getAbsolutePath();
		outputFolder = new File(savedGamesDir, outputFolder.getName().split("\\.")[0]);
		{

			byte[] buffer = new byte[1024];

			try {

				// create output directory is not exists

				// get the zip file content
				ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
				// get the zipped file list entry
				ZipEntry ze = zis.getNextEntry();

				while (ze != null) {

					String fileName = ze.getName();
					File newFile = new File(outputFolder + File.separator + fileName);

					gui.message("file unzip : " + newFile.getAbsoluteFile());

					// create all non exists folders
					// else you will hit
					// FileNotFoundException for
					// compressed
					// folder
					new File(newFile.getParent()).mkdirs();

					FileOutputStream fos = new FileOutputStream(newFile);

					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();
					ze = zis.getNextEntry();
				}

				zis.closeEntry();
				zis.close();
				
				gui.message("Done");
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void delete(String line) {
		String zipFile;
		listLineMatcher.reset(line);
		if(listLineMatcher.find()) {
			zipFile = listLineMatcher.group(1);
			File outputFolder = new File(compressedDir, zipFile);
			outputFolder.delete();
			checkCompressedDir(true);
		} else {
			gui.message("Error: filename not found in >"  + line +"<");
			return;
		}
		
		
	}
}