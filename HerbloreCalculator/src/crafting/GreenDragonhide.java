package crafting;

public class GreenDragonhide extends Leather {
	private static String leatherURL = "http://services.runescape.com/m=itemdb_rs/Green_dragon_leather/viewitem?obj=1745";
	private static String hideURL = "http://services.runescape.com/m=itemdb_rs/Green_dragonhide/viewitem?obj=1753";

	public GreenDragonhide(String itemName, int leatherPerItem) {
		super(leatherURL, itemName, hideURL, leatherURL, leatherPerItem);
	}

	public static GreenDragonhide Coif() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Green_d'hide_coif/viewitem?obj=12936";
		int leatherPerItem = 2;
		GreenDragonhide coif = new GreenDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static GreenDragonhide Body() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Green_d'hide_body/viewitem?obj=1135";
		int leatherPerItem = 3;
		GreenDragonhide coif = new GreenDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static GreenDragonhide Chaps() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Green_d'hide_chaps/viewitem?obj=1099";
		int leatherPerItem = 2;
		GreenDragonhide coif = new GreenDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static GreenDragonhide Vambraces() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Green_d'hide_vambraces/viewitem?obj=1065";
		int leatherPerItem = 1;
		GreenDragonhide coif = new GreenDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static GreenDragonhide Shield() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Green_d'hide_shield/viewitem?obj=25794";
		int leatherPerItem = 4;
		GreenDragonhide coif = new GreenDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static void printAllFromLeather(int leathersNeeded) {
		GreenDragonhide.Coif().printProfitFromLeather(leathersNeeded / 2);
		GreenDragonhide.Body().printProfitFromLeather(leathersNeeded / 3);
		GreenDragonhide.Chaps().printProfitFromLeather(leathersNeeded / 2);
		GreenDragonhide.Vambraces().printProfitFromLeather(leathersNeeded);
		GreenDragonhide.Shield().printProfitFromLeather(leathersNeeded / 4);
	}

}
