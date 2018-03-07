package dominion4calcs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SimpleCombatSimulator {

	
	//simulation: Lion tribe warriors defending from Slingers
	public static class Slash implements DamageCalculator {
		private final Random random;

		public Slash(Random random) {
			this.random = random;
		}

		@Override
		public int damage(Unit attacker, Unit defender) {
			int result = attacker.basicDamage+Basic.drn(random)-defender.protection-Basic.drn(random);
			if(result > 0) {
				result = (int)(result * 1.25);
			} else {
				result =0;
			}
			return result;
		}
	}

	interface DamageCalculator {
		
		public int damage(Unit attacker, Unit defender);
		
	}
	
	public static class Unit implements Cloneable {
		int attack;
		int defense;
		int hitpoints;
		int protection;
		int basicDamage;
		int meleeFatigue;
		int parry;
		DamageCalculator damage;
		volatile int defenseCount;
		volatile int fatigue;
		@Override
		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}
		public void newTurn() {
			defenseCount=0;
		}
		
		public int defenseRoll() {
			
		}
		
		public int attackRoll() {
			
		}
		
		public int protectionRoll(boolean isShieldHit) {
			
		}
		
		
	}
	final Random random = new Random(); 
	public void combat() {
		List<Unit> attackers = new LinkedList<Unit>();
		List<Unit> defenders = new LinkedList<Unit>();
		Unit att = new Unit();
		att.attack=12;
		att.defense=12;
		att.hitpoints=10;
		att.protection=10;
		att.damage= new Slash(random);
		att.meleeFatigue=2;
		att.parry=4;
		attackers.add(att);
		for(int i=1; i < 8;++i) {
			attackers.add((Unit)att.clone());
		}
		
		Unit def=new Unit();
		def.attack=10;
		def.defense=13;
		def.hitpoints=10;
		def.protection=20;
		def.meleeFatigue=2;
		def.parry=0;
		def.damage = new Slash(random);
		defenders.add(def);
		for(int i=1; i < 5; ++i) {
			defenders.add((Unit)def.clone());
		}
		
		attacks(defenders, attackers, random);
		attacks(attackers,defenders, random);
		
	}

	private void attacks(List<Unit> attackers, List<Unit> defenders) {
		List<Unit> attackerList = new ArrayList<Unit>(attackers);
		List<Unit> defenderList = new ArrayList<Unit>(defenders);
		while(attackerList.size() > 0) {
			
		}
		
	}
	
}
