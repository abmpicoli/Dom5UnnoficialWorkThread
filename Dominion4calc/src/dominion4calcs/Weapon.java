package dominion4calcs;

public class Weapon {

	private String weaponName;
	private int length;
	private int attack;
	private int dmg;
	private String type;

	public Weapon(PropertiesUtils p, String weaponName) {
		this.weaponName=weaponName;
		this.length = p.getInt("W."+weaponName+".LEN");
		this.attack = p.getInt("W."+weaponName+".AT");
		this.dmg=p.getInt("W."+weaponName+".DMG");
		this.type=p.getString("W."+weaponName+".TYPE").trim();
		
	}

}
