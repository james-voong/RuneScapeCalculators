package smithing;

public class Adamant extends Metal {

	private static String barURL = "http://services.runescape.com/m=itemdb_rs/a=13/Adamant_bar/viewitem?obj=2361";
	private static String oreURL = "http://services.runescape.com/m=itemdb_rs/a=13/Adamantite_ore/viewitem?obj=449";

	public Adamant(String itemName, int barsPerItem) {
		super(barURL, oreURL, 6, itemName, barsPerItem);

	}

	public static Adamant PlateBody() {
		String urlPage = "http://services.runescape.com/m=itemdb_rs/a=13/Adamant_platebody/viewitem?obj=1123";
		Adamant item = new Adamant(urlPage, 5);
		return item;
	}
	
	public static Adamant Claw(){
		String urlPage = "http://services.runescape.com/m=itemdb_rs/a=13/Adamant_claw/viewitem?obj=3100";
		Adamant item = new Adamant(urlPage, 2);
		return item;
	}

}
