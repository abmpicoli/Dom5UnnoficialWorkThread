package dominion4calcs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapAnalyzer {

	static class Province implements Comparable<Province>{
		Set<Province> neighbors = new HashSet<Province>();
		boolean isWater;
		private final int id;
		public Province(int id, int terrain) {
			this.id = id;
			setTerrain(terrain);
		}
		void setTerrain(String terrainStr) {
			int terrain = Integer.parseInt(terrainStr);
			setTerrain(terrain);
		}
		void setTerrain(int newValue) {
			isWater = (newValue & 0x804) > 0;
		}
		@Override
		public int compareTo(Province o) {
			return this.id-o.id;
		}
		public void addNeighbor(Province province2) {
			neighbors.add(province2);
			
		}
		
		public boolean isSameDomain(Province p2) {
			return !(isWater ^ p2.isWater);
		}
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append(id).append(":( ");
			boolean first=true;
			for(Province p: neighbors) {
				if(!first) {
					result.append(" , ");
				} else {
					first=false;
				}
				if(p.isSameDomain(this)) {
					result.append("*");
				}
				result.append(p.id);
			}
			result.append(")");
			return result.toString();
		}
	}
	private static HashMap<Integer,Province> provinces = new HashMap<Integer,Province>();
	private static double adminLevel;
	private static ExecutorService executor; 
	
	private static class ProvinceState {
		boolean hasFort;
		double resourceValue=0.5;
	}
	
	private static class MapState {
		private ProvinceState[] states = new ProvinceState[provinces.size()+1];
		public MapState() {
			for(int i=0; i < states.length; ++i) {
				states[i]=new ProvinceState();
			}
			
		}
		public MapState(MapState parent) {
			synchronized(states) {
				System.arraycopy(parent.states,0,states,0,states.length);
			}
		}
		
		public boolean hasFort(int id) {
			return states[id].hasFort;
		}
		
		public MapState buildFort(int id) {
			MapState result;
			synchronized(states) {
			ProvinceState ps = states[id];
			if(ps.hasFort) {
				return this;
			} else {
				result = new MapState(this);
			}
			}
			ProvinceState provinceState = new ProvinceState();
				provinceState.hasFort = true;
				provinceState.resourceValue = 1.0;
				result.states[id]=provinceState;
				Province province = provinces.get(id);
				for(Province neighbor: province.neighbors) {
					
				}
			}
			
		}
		
		double getResourceValue(int id) {
			
		}
	int threadIndex;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		adminLevel = Double.parseDouble(args[1]);
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		try(FileReader r0 = new FileReader(args[0]); BufferedReader reader = new BufferedReader(r0)) {
			String line;
			while((line = reader.readLine())!=null ) {
				if(line.startsWith("#neighbour ")) {
					String[] parts = line.split("\\s+");
					Province province1 = createProvince(parts[1]);
					Province province2 = createProvince(parts[2]);
					province1.addNeighbor(province2);
					province2.addNeighbor(province1);
				} else if(line.startsWith("#terrain ")) {
					String[] parts  = line.split("\\s+");
					Province province = createProvince(parts[1]);
					province.setTerrain(parts[2]);
				}
			}
		}
		for(Province p: provinces.values()) {
			System.out.println(p);
		}
		MapState state = new MapState();
		
	}

	private static Province createProvince(String idStr) {
		int id = Integer.parseInt(idStr);
		Province result = provinces.get(id);
		
		if(result==null) {
			result = new Province(id,0);
			provinces.put(id,result);
		}
		return result;
	}
}
