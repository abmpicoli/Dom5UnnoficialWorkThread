package dominion4calcs;

import java.util.Random;

public class SimpleSimulation {

	public static void main(String[] args) {
		
		double slingerCount = 30;
		double currentRange = 30;
		double slingerAmmo = 15;
		double minimumPrecision = 8/2-2;
		double precision = 8;
		double deviation	= 1.25 * currentRange / precision;
		double deviationArea = Math.PI*deviation*deviation;
		System.out.println("Deviation:" + deviation);
		double militiaCount=30;
		int militiaSize=2;
		double squadSize = 6/ militiaSize;
		double militiaArea = militiaCount / squadSize;
		double squadHitChance = Math.min(1.0,(militiaArea / deviationArea));
		Random random = new Random();
		System.out.println("SquadHitChance:" + squadHitChance);
		double totalDamage=0;
		double hitCounts =0;
		double kills=0;
		int militiaHitPoints =10;
		int numberTests = 32000;
		for(int tests = 0; tests < 32000; ++tests) {
		for(int i=0; i < slingerCount; ++i) {
			if(Math.random() < squadHitChance) {
				for(int j=0; j < squadSize; ++j) {
					int attackRoll = Basic.fastDrn(random) + 6;
					int defenderRoll = 2 + Basic.fastDrn(random);
					if(attackRoll > defenderRoll) {
						int damageRoll = Basic.fastDrn(random) + 8;
						int protectionRoll = Basic.fastDrn(random)+5;
						int damage = damageRoll - protectionRoll;
						if(damage >0) {
							boolean headShot = random.nextInt(10)==0;
							if(headShot) {
								damage = damage * 3/2;
							}
							if(damage > militiaHitPoints) {
								++kills;
							} else {
								totalDamage += damage;
							}
							++hitCounts;
						}
					}
				}
			}
		}
		}
		if(hitCounts > 0) {
			System.out.println((hitCounts-kills)/numberTests + " damaged; " + kills/numberTests + " kills.  average damage for survivors: " + (totalDamage/(hitCounts-kills)));
		}
	}
	
}
