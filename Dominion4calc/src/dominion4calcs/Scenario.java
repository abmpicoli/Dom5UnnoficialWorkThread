package dominion4calcs;

import java.util.Random;

public class Scenario implements Runnable {

	private UnitSquad attackers;
	private UnitSquad defenders;
	private Random random = new Random(); 

	public Scenario(Unit attacker, int i, Unit defender, int j) {
		attackers = new UnitSquad(attacker,10,random.nextInt(29), random.nextInt(29));
		defenders = new UnitSquad(defender,10,random.nextInt(29)+29,random.nextInt(29));
		

		
		
	}

	@Override
	public void run() {
		while(!attackers.isRouted() && !defenders.isRouted()) {
			
		}
		
	}

}
