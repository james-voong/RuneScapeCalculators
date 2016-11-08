package herblore;

/** A class where different potions are instantiated */
public class TradablePotion extends Potion {

	public TradablePotion(String grimy, String clean, String unf, String secondary, String dose3, String dose4,
			int amount) {
		super(grimy, clean, unf, secondary, dose3, dose4, amount);

	}

	public static Potion superAttack(int amount) {

		String grimy = "http://services.runescape.com/m=itemdb_rs/Grimy_irit/viewitem?obj=209";
		String clean = "http://services.runescape.com/m=itemdb_rs/Clean_irit/viewitem?obj=259";
		String unf = "http://services.runescape.com/m=itemdb_rs/Irit_potion_(unf)/viewitem?obj=101";
		String secondary = "http://services.runescape.com/m=itemdb_rs/Eye_of_newt/viewitem?obj=221";
		String dose3 = "http://services.runescape.com/m=itemdb_rs/Super_attack_(3)/viewitem?obj=145";
		String dose4 = "http://services.runescape.com/m=itemdb_rs/Super_attack_(4)/viewitem?obj=2436";

		Potion superAttack = new TradablePotion(grimy, clean, unf, secondary, dose3, dose4, amount);
		return superAttack;
	}

	public static Potion superDefence(int amount) {

		String grimy = "http://services.runescape.com/m=itemdb_rs/Grimy_cadantine/viewitem?obj=215";
		String clean = "http://services.runescape.com/m=itemdb_rs/Clean_cadantine/viewitem?obj=265";
		String unf = "http://services.runescape.com/m=itemdb_rs/Cadantine_potion_(unf)/viewitem?obj=107";
		String secondary = "http://services.runescape.com/m=itemdb_rs/White_berries/viewitem?obj=239";
		String dose3 = "http://services.runescape.com/m=itemdb_rs/Super_defence_(3)/viewitem?obj=163";
		String dose4 = "http://services.runescape.com/m=itemdb_rs/Super_defence_(4)/viewitem?obj=2442";

		Potion superDefence = new TradablePotion(grimy, clean, unf, secondary, dose3, dose4, amount);
		return superDefence;
	}

	public static Potion superStrength(int amount) {
		String grimy = "http://services.runescape.com/m=itemdb_rs/Grimy_kwuarm/viewitem?obj=213";
		String clean = "http://services.runescape.com/m=itemdb_rs/Clean_kwuarm/viewitem?obj=263";
		String unf = "http://services.runescape.com/m=itemdb_rs/Kwuarm_potion_(unf)/viewitem?obj=105";
		String secondary = "http://services.runescape.com/m=itemdb_rs/Limpwurt_root/viewitem?obj=225";
		String dose3 = "http://services.runescape.com/m=itemdb_rs/Super_strength_(3)/viewitem?obj=157";
		String dose4 = "http://services.runescape.com/m=itemdb_rs/Super_strength_(4)/viewitem?obj=2440";

		Potion superStrength = new TradablePotion(grimy, clean, unf, secondary, dose3, dose4, amount);
		return superStrength;
	}
}
