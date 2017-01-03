package crafting;

public class BlueDragonhide extends Leather {
	private static String leatherURL = "http://services.runescape.com/m=itemdb_rs/Blue_dragon_leather/viewitem?obj=2505";
	private static String hideURL = "http://services.runescape.com/m=itemdb_rs/Blue_dragonhide/viewitem?obj=1751";

	public BlueDragonhide(String itemName, int leatherPerItem) {
		super(leatherURL, itemName, hideURL, leatherURL, leatherPerItem);
	}

	public static BlueDragonhide Coif() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Blue_d'hide_coif/viewitem?obj=12943";
		int leatherPerItem = 2;
		BlueDragonhide coif = new BlueDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlueDragonhide Body() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Blue_d'hide_body/viewitem?obj=2499";
		int leatherPerItem = 3;
		BlueDragonhide coif = new BlueDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlueDragonhide Chaps() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Blue_d'hide_chaps/viewitem?obj=2493";
		int leatherPerItem = 2;
		BlueDragonhide coif = new BlueDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlueDragonhide Vambraces() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Blue_d'hide_vambraces/viewitem?obj=2487";
		int leatherPerItem = 1;
		BlueDragonhide coif = new BlueDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlueDragonhide Shield() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Blue_d'hide_shield/viewitem?obj=25796";
		int leatherPerItem = 4;
		BlueDragonhide coif = new BlueDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static void printAllFromLeather(int leathersNeeded) {
		BlueDragonhide.Coif().printProfitFromLeather(leathersNeeded / 2);
		BlueDragonhide.Body().printProfitFromLeather(leathersNeeded / 3);
		BlueDragonhide.Chaps().printProfitFromLeather(leathersNeeded / 2);
		BlueDragonhide.Vambraces().printProfitFromLeather(leathersNeeded);
		BlueDragonhide.Shield().printProfitFromLeather(leathersNeeded / 4);
		System.out.println();
	}

}
