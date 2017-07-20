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

	public static Potion prayerRestore(int amount) {
		String grimy = "http://services.runescape.com/m=itemdb_rs/Grimy_ranarr/viewitem?obj=207";
		String clean = "http://services.runescape.com/m=itemdb_rs/Clean_ranarr/viewitem?obj=257";
		String unf = "http://services.runescape.com/m=itemdb_rs/Ranarr_potion_(unf)/viewitem?obj=99";
		String secondary = "http://services.runescape.com/m=itemdb_rs/Snape_grass/viewitem?obj=231";
		String dose3 = "http://services.runescape.com/m=itemdb_rs/Prayer_potion_(3)/viewitem?obj=139";
		String dose4 = "http://services.runescape.com/m=itemdb_rs/Prayer_potion_(4)/viewitem?obj=2434";

		Potion prayerRestore = new TradablePotion(grimy, clean, unf, secondary, dose3, dose4, amount);
		return prayerRestore;
	}

	public static Potion prayerRenewal(int amount) {
		String grimy = "http://services.runescape.com/m=itemdb_rs/Grimy_fellstalk/viewitem?obj=21626";
		String clean = "http://services.runescape.com/m=itemdb_rs/Clean_fellstalk/viewitem?obj=21624";
		String unf = "http://services.runescape.com/m=itemdb_rs/Fellstalk_potion_(unf)/viewitem?obj=21628";
		String secondary = "http://services.runescape.com/m=itemdb_rs/Morchella_mushroom/viewitem?obj=21622";
		String dose3 = "http://services.runescape.com/m=itemdb_rs/Prayer_renewal_(3)/viewitem?obj=21632";
		String dose4 = "http://services.runescape.com/m=itemdb_rs/Prayer_renewal_(4)/viewitem?obj=21630";

		Potion prayerRenewal = new TradablePotion(grimy, clean, unf, secondary, dose3, dose4, amount);
		return prayerRenewal;
	}

	public static Potion saradominBrew(int amount) {
		String grimy = "http://services.runescape.com/m=itemdb_rs/Grimy_toadflax/viewitem?obj=3049";
		String clean = "http://services.runescape.com/m=itemdb_rs/Clean_toadflax/viewitem?obj=2998";
		String unf = "http://services.runescape.com/m=itemdb_rs/Toadflax_potion_(unf)/viewitem?obj=3002";
		String secondary = "http://services.runescape.com/m=itemdb_rs/Crushed_nest/viewitem?obj=6693";
		String dose3 = "http://services.runescape.com/m=itemdb_rs/Saradomin_brew_(3)/viewitem?obj=6687";
		String dose4 = "http://services.runescape.com/m=itemdb_rs/Saradomin_brew_(4)/viewitem?obj=6685";

		Potion saradominBrew = new TradablePotion(grimy, clean, unf, secondary, dose3, dose4, amount);
		return saradominBrew;
	}

	public static Potion zamorakBrew(int amount) {
		String grimy = "http://services.runescape.com/m=itemdb_rs/Grimy_torstol/viewitem?obj=219";
		String clean = "http://services.runescape.com/m=itemdb_rs/Clean_torstol/viewitem?obj=269";
		String unf = "http://services.runescape.com/m=itemdb_rs/Torstol_potion_(unf)/viewitem?obj=111";
		String secondary = "http://services.runescape.com/m=itemdb_rs/Jangerberries/viewitem?obj=247";
		String dose3 = "http://services.runescape.com/m=itemdb_rs/Zamorak_brew_(3)/viewitem?obj=189";
		String dose4 = "http://services.runescape.com/m=itemdb_rs/Zamorak_brew_(4)/viewitem?obj=2450";

		Potion zamorakBrew = new TradablePotion(grimy, clean, unf, secondary, dose3, dose4, amount);
		return zamorakBrew;
	}
}
