package dominion4calcs;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Basic {



	private static int[] fastDrn={2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 21, 21, 22, 22, 22, 23, 24, 24, 25, 27, 30};

	
			
	
	
	
	
	private static final class DrnRunnable implements Runnable {
		private Result result;
		private long testLength;
		private boolean useFastDRN;
		public void run() {
			Random random = new Random();
			long end = System.currentTimeMillis()+testLength;
			int[] odds = new int[256];
			while( System.currentTimeMillis() < end ) {
				for(int i=0; i < 32000 ; ++i) {
					
					int total;
					if(useFastDRN) {
						total = fastDrn(random);
					} else {
						total = drn(random); 
					}
					odds[total] ++;
				}
				synchronized(result) {
					for(int i=0; i < odds.length;++i) {
						result.addOdd(i, odds[i]);
						odds[i]=0;
					}
				}
			}
		}
		public DrnRunnable(Result result, long testLength, boolean useFastDRN) {
			this.result = result;
			this.testLength = testLength;
			this.useFastDRN = useFastDRN;
		}
		
		
	}
	static class Stats {
		long count;
		long sum;
		double average;
	}
	
	static class Result {
		private long[] totalOdds = new long[256];
		
		public void addOdd(int value, int chance) {
			totalOdds[value]+=chance;
		}
		private Stats summary;
		public Stats summarize() {
			if(summary==null) {
				summary = new Stats();
				for(int i=0 ; i< totalOdds.length;++i) {
					summary.count += totalOdds[i];
					summary.sum += ((long)(totalOdds[i]))*i;
				}
				summary.average= ((double)(summary.sum)) / summary.count;
				
			}
			return summary;
			
		}
		public double getOdds(int i) {
			summarize();
			return totalOdds[i] / (double)(summary.count);
			
		}
		
	}
	/**
	 * Show odds of drawing certain DRN numbers.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Result result = new Result();
		boolean fast=false;
		runTest(result, fast);
		int fastDrnIndex=0;
		int value=0;
		/*
		fastDrn=new int[1024];
		Arrays.fill(fastDrn, -1);
		double round=0.5;
		while(fastDrnIndex < fastDrn.length && value < 256) {
			double countDbl = result.getOdds(value)*fastDrn.length;
			int count = (int)(countDbl+round);
			round = round + countDbl - count;
			while(count > 0 && fastDrnIndex < fastDrn.length) {
				fastDrn[fastDrnIndex++]=value;
				--count;
			}
			++value;
			
		}
		*/
		System.out.println("COUNT: " + result.summarize().count);
		System.out.println("AVERAGE:" + result.summarize().average);
		System.out.println("Typical numbers:" + Arrays.toString(fastDrn));
		result = new Result();
		fast=true;
		runTest(result,fast);
		System.out.println("COUNT WITH FAST RNG: " + result.summarize().count);
		System.out.println("AVERAGE WITH FAST RNG: " + result.summarize().average);
	}

	private static void runTest(Result result, boolean fast) throws InterruptedException {
		int numThreads = Runtime.getRuntime().availableProcessors()*11/10;
		Thread[] th = new Thread[numThreads];
		for(int i=0; i < numThreads; ++i) {
			th[i] = new Thread(new DrnRunnable(result,30000,fast));
			th[i].start();
		}
		for(int i=0; i < numThreads; ++i) {
			th[i].join();
		}
	}

	/**
	 * Generates a Dominion 4 DRN draw. (page 8).
	 * @param random
	 * @return
	 */
	public static int drn(Random random) {
		int total=0;
		
		for(int j=0; j < 2 && total < 256; ++j) {
			int k;
			do {
				k = random.nextInt(6);
				if(k==5) {
					total = total +5;
				} else {
					total = total + k + 1;
				}
			} while(k==5 && total < 256);
			if(total >= 256) {
				total=255;
			}
		
		}
		return total;
	}
	
	public static int fastDrn(Random random) {
		return fastDrn[random.nextInt(fastDrn.length)];
	}
}
