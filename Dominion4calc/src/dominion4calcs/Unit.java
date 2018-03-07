package dominion4calcs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unit implements Cloneable {
	
	final Map<String,Integer> mageSkill = new HashMap<String,Integer>();
	
	final int hitPointsBasic;
	final int magicResistanceBasic;
	final int moraleBasic;
	final String name;
	final int encumbrance;
	final int combatSpeed;
	final int strengthBasic;
	final Weapon[] weapons;
	final int attack;
	final int basicDefense;
	final int protectionTotal;
	final int protectionHead;
	final int protectionBody;
	final int precision;
	final int size;
	private int priceGold;
	private int priceResources;
	private int hitPointsCurrent;
	private int protectionBasic;
	private int magicResistanceCurrent;
	private int currentMorale;
	private int upkeepCost;
	private int strengthCurrent;
	private int currentDefense;
	private int basicPrecision;
	private int currentPrecision;
	private final boolean isSacred;

	private final int priceRecruitment;

	private int priceCommand;

	public Unit (Properties props) {
		PropertiesUtils p = new PropertiesUtils(props);
		name = p.getString("NAME");
		priceGold=p.getInt("PRICE_GOLD");
		priceResources = p.getInt("PRICE_RESOURCES");
		priceRecruitment=p.getInt("PRICE_RECRUITMENT");
		priceCommand=p.getInt("PRICE_COMMAND");
		hitPointsBasic = p.getInt("HIT_POINTS_BASIC");
		hitPointsCurrent = p.getInt("HIT_POINTS_CURRENT");
		size=p.getInt("SIZE");
		protectionBasic = p.getInt("BASIC_PROTECTION");
		protectionTotal = p.getInt("TOTAL_PROTECTION");
		protectionHead = p.getInt("TOTAL_PROTECTION_HEAD");
		protectionBody = p.getInt("TOTAL_PROTECTION_BODY");
		magicResistanceBasic = p.getInt("MAGIC_RESISTANCE_BASIC");
		magicResistanceCurrent=p.getInt("MAGIC_RESISTANCE_CURRENT");
		moraleBasic = p.getInt("BASIC_MORALE");
		currentMorale = p.getInt("CURRENT_MORALE");
		upkeepCost=p.getInt("UPKEEP_COST");
		strengthBasic = p.getInt("BASIC_STRENGTH");
		strengthCurrent = p.getInt("CURRENT_STRENGTH");
		attack = p.getInt("ATTACK");
		basicDefense=p.getInt("BASIC_DEFENSE_SKILL");
		currentDefense=p.getInt("CURRENT_DEFENSE_SKILL");
		basicPrecision=p.getInt("BASIC_PRECISION");
		currentPrecision= p.getInt("CURRENT_PRECISION");
		precision = p.getInt("PRECISION");
		isSacred = p.getBoolean("IS_SACRED");
		encumbrance = p.getInt("ENCUMBRANCE");
		String mageSkill=p.getString("MAGELEVELS");
		Pattern pattern = Pattern.compile("[FAWESDNBH]\\d+");
		
		combatSpeed = p.getInt("MOVES");
		String[] weaponNames = p.getString("WEAPONS").trim().split("\\W+");
		this.weapons = new Weapon[weaponNames.length];
		for (int i=0; i < weaponNames.length;++i) {
			this.weapons[i] = new Weapon(p,weaponNames[i]);
		}
	}
	
	public Unit(String filename) throws IOException {
		
		this(PropertiesUtils.load(filename));
		
	}
	
	
}
