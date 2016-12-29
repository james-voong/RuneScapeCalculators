package smithing;

public class Runite extends Metal {
	private static String barURL = "http://services.runescape.com/m=itemdb_rs/a=13/Rune_bar/viewitem?obj=2363";
	private static String oreURL = "http://services.runescape.com/m=itemdb_rs/a=13/Runite_ore/viewitem?obj=451";

	public Runite(String itemName, int barsPerItem) {
		super(barURL, oreURL, 8, itemName, barsPerItem);

	}

	public static Runite MedHelm() {
		String urlPage = "http://services.runescape.com/m=itemdb_rs/a=13/Rune_helm/viewitem?obj=1147";
		Runite item = new Runite(urlPage, 1);
		return item;
	}

}
