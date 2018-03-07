import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestFile {

	public static void main(String[] args) throws IOException {
		File testFile = new File("C:\\Users\\alexander\\AppData\\Roaming\\Dominions5\\savedgames\\aram\\late_ctis.2h");
		try(FileInputStream fi = new FileInputStream(testFile)) {
			int i;
			while((i = fi.read()) != -1) {
				System.out.print((char)(i+32));
			}
		}
	}
}
