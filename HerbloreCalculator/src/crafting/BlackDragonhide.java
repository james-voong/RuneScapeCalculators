package crafting;

public class BlackDragonhide extends Leather {
	private static String leatherURL = "http://services.runescape.com/m=itemdb_rs/Black_dragon_leather/viewitem?obj=2509";
	private static String hideURL = "http://services.runescape.com/m=itemdb_rs/Black_dragonhide/viewitem?obj=1747";

	public BlackDragonhide(String itemName, int leatherPerItem) {
		super(leatherURL, itemName, hideURL, leatherURL, leatherPerItem);
	}

	public static BlackDragonhide Coif() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Black_d'hide_coif/viewitem?obj=12957";
		int leatherPerItem = 2;
		BlackDragonhide coif = new BlackDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlackDragonhide Body() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Black_d'hide_body/viewitem?obj=2503";
		int leatherPerItem = 3;
		BlackDragonhide coif = new BlackDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlackDragonhide Chaps() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Black_d'hide_chaps/viewitem?obj=2497";
		int leatherPerItem = 2;
		BlackDragonhide coif = new BlackDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlackDragonhide Vambraces() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Black_d'hide_vambraces/viewitem?obj=2491";
		int leatherPerItem = 1;
		BlackDragonhide coif = new BlackDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static BlackDragonhide Shield() {
		String itemName = "http://services.runescape.com/m=itemdb_rs/Black_d'hide_shield/viewitem?obj=25800";
		int leatherPerItem = 4;
		BlackDragonhide coif = new BlackDragonhide(itemName, leatherPerItem);
		return coif;
	}

	public static void printAllFromLeather(int leathersNeeded) {
		BlackDragonhide.Coif().printProfitFromLeather(leathersNeeded / 2);
		BlackDragonhide.Body().printProfitFromLeather(leathersNeeded / 3);
		BlackDragonhide.Chaps().printProfitFromLeather(leathersNeeded / 2);
		BlackDragonhide.Vambraces().printProfitFromLeather(leathersNeeded);
		BlackDragonhide.Shield().printProfitFromLeather(leathersNeeded / 4);
		System.out.println();
	}

}
