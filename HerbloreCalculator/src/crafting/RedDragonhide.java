package crafting;

public class RedDragonhide extends Leather {
	private static String leatherURL = "http://services.runescape.com/m=itemdb_rs/Red_dragon_leather/viewitem?obj=2507";
	private static String hideURL = "http://services.runescape.com/m=itemdb_rs/Red_dragonhide/viewitem?obj=1749";

	public RedDragonhide(String itemName, int leatherPerItem) {
		super(leatherURL, itemName, hideURL, leatherURL, leatherPerItem);
	}

	public static RedDragonhide Coif() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Red_d'hide_coif/viewitem?obj=12950";
		int leatherPerItem = 2;
		RedDragonhide coif = new RedDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static RedDragonhide Body() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Red_d'hide_body/viewitem?obj=2501";
		int leatherPerItem = 3;
		RedDragonhide coif = new RedDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static RedDragonhide Chaps() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Red_d'hide_chaps/viewitem?obj=2495";
		int leatherPerItem = 2;
		RedDragonhide coif = new RedDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static RedDragonhide Vambraces() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Red_d'hide_vambraces/viewitem?obj=2489";
		int leatherPerItem = 1;
		RedDragonhide coif = new RedDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static RedDragonhide Shield() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Red_d'hide_shield/viewitem?obj=25798";
		int leatherPerItem = 4;
		RedDragonhide coif = new RedDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static void printAllFromLeather(int leathersNeeded) {
		RedDragonhide.Coif().printProfitFromLeather(leathersNeeded / 2);
		RedDragonhide.Body().printProfitFromLeather(leathersNeeded / 3);
		RedDragonhide.Chaps().printProfitFromLeather(leathersNeeded / 2);
		RedDragonhide.Vambraces().printProfitFromLeather(leathersNeeded);
		RedDragonhide.Shield().printProfitFromLeather(leathersNeeded / 4);
	}

}
