package test;

import bond.Bond;
import crafting.BlackDragonhide;
import crafting.BlueDragonhide;
import crafting.GreenDragonhide;
import crafting.RedDragonhide;
import herblore.TradablePotion;
import smithing.Adamant;
import smithing.Runite;

@SuppressWarnings("unused")
public class Test {
	public static void main(String[] args) {
		// Bond.doCalculation();

//		TradablePotion.superAttack(16796).printPotionNetProfitFromUnf();
//		TradablePotion.superDefence(11197).printPotionNetProfitFromUnf();
//		TradablePotion.superStrength(13437).printPotionNetProfitFromUnf();
//		TradablePotion.prayerRenewal(8840).printPotionNetProfitFromUnf();
//		TradablePotion.saradominBrew(9331).printPotionNetProfitFromUnf();
//		TradablePotion.zamorakBrew(9598).printPotionNetProfitFromUnf();

		 Runite.MedHelm().printNetProfitFromSmithingBars(1);
		 Adamant.PlateBody().printNetProfitFromSmithingBars(1);
		 Adamant.Claw().printNetProfitFromSmithingBars(1);

		// BlackDragonhide.printAllFromLeather(24875);
		// RedDragonhide.printAllFromLeather(27327);
		// BlueDragonhide.printAllFromLeather(30450);
		// GreenDragonhide.printAllFromLeather(34379);
	}

}
