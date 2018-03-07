package dominion4calcs;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Combat {
	private static Random random = new Random();

	
	public static void main(String[] args) throws IOException {
		
		
		Unit attacker = new Unit("AtlantianSpearman.unit");
		Unit defender = new Unit("Barbarian.unit");
		
		Scenario scenario = new Scenario(attacker,10,defender,10);
		Thread th = new Thread(scenario);
		
		int d1 = random.nextInt(58);
		int d2 = random.nextInt(29);
		int dX = Math.max(d1, d2);
		int dY = Math.min(d1, d2);
		UnitSquad[] orderSquad = new UnitSquad[] { new UnitSquad(attacker,d1,0,0, attackers };
		for(int step=0; step < 2; ++step) {
			int actionPoints = orderSquad[step].unit.move;
			if(dX > 1 || dY > 1) {
				if(dY > 1) {
					int diagonalMoves = dY - 1;
					diagonalMoves = Math.min(actionPoints / 3, diagonalMoves);
					actionPoints = actionPoints - diagonalMoves * 3;
					dY = dY - diagonalMoves;
					dX = dX - diagonalMoves;
				}
				if(dX > 1 && actionPoints >= 2) {
					int horizontalMoves = Math.min(actionPoints/2, dX-1);
					dX = dX - horizontalMoves;
				}
				
			}
			if(actionPoints > 0 && (dX <=1 || dY <= 1) ) {
				attack(orderSquad[step], orderSquad[(step + 1 ) % 2]);
			}

		}
		
	}
	
}
