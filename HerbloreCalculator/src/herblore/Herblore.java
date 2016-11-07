package herblore;

import parser.Parser;

public class Herblore {

	private String name;
	private int grimy, clean, unf, secondary;
	private int dose3, dose4;

	private int amount;

	private static int vial = Parser
			.getGrandExchangePrice("http://services.runescape.com/m=itemdb_rs/Vial_of_water/viewitem?obj=227");

	/** Constructor in order to do calculations on different potions */
	public Herblore(String name, String grimy, String clean, String unf, String secondary, String dose3, String dose4,
			int amount) {

		this.name = Parser.getNameOfPotion(name);
		this.grimy = Parser.getGrandExchangePrice(grimy);
		this.clean = Parser.getGrandExchangePrice(clean);
		this.unf = Parser.getGrandExchangePrice(unf);
		this.secondary = Parser.getGrandExchangePrice(secondary);
		this.dose3 = Parser.getGrandExchangePrice(dose3);
		this.dose4 = Parser.getGrandExchangePrice(dose4);

		this.amount = amount;

	}

	/** Calculates profits/losses starting from grimy herbs */
	public void fromGrimy() {

		printName();
		/** Calculate how much seed money will be required */
		int seed = amount * grimy;

		/** Calculate the profit from cleaning the herb */
		int profit = (clean - grimy) * amount;

		System.out.println("From Grimy:");
		System.out.println("Total seed money for " + amount + " herbs:" + seed);
		System.out.println("Profit from cleaning: " + profit);

		/** Call fromClean to do rest of the calculations */
		fromClean(grimy + vial);
	}

	/** Calculates profits/losses starting from clean herbs */
	public void fromClean(int rawCostOfPrimaryIngredient) {

		printName();
		if (rawCostOfPrimaryIngredient == (clean + vial)) {
			int seed = rawCostOfPrimaryIngredient * amount;
			System.out.println("\nFrom Clean:");
			System.out.println("Total seed money for " + amount + " herbs:" + seed);
		}

		/** Calculate net profit of making unf */
		int extrasFromScroll = (int) (amount * 1.11) - amount;
		int valueOfExtraVials = extrasFromScroll * vial;
		int valueOfExtraUnfsFromScroll = (int) (unf * extrasFromScroll) - valueOfExtraVials;
		int unfNetProfit = ((unf - rawCostOfPrimaryIngredient) * amount) + valueOfExtraUnfsFromScroll;
		System.out.println("Profit from making unf:" + (unfNetProfit));

		/** Call fromUnf to do the remaining calculations of profit */
		int valueFromMakingPot = fromUnf(rawCostOfPrimaryIngredient + vial);
		System.out.println("NB there will still be remaining unfs, value of which will be " + valueOfExtraUnfsFromScroll
				+ ", bringing total net profit to " + (valueFromMakingPot + valueOfExtraUnfsFromScroll));

	}

	/** Calculates profits/losses starting from unfilled potions */
	public int fromUnf(int rawCostOfPrimaryIngredient) {

		printName();
		if (rawCostOfPrimaryIngredient == unf) {
			System.out.println("\nFrom Unf:");
		}
		/** Extra secondary ingredients saved from the Scroll of Cleansing */
		double extraSecondariesFromScroll = (amount * 1.11) - amount;

		/**
		 * Total seed money required to buy all the Unfs and secondary
		 * ingredients
		 */
		int costToPurchaseRawMaterials = (rawCostOfPrimaryIngredient + secondary) * amount;
		System.out.println("Total seed money for " + amount + " potions:" + costToPurchaseRawMaterials);

		/** The amount from reselling the extra secondary ingredients */
		int valueOfSavedSecondaryFromScroll = (int) extraSecondariesFromScroll * secondary;

		/** The amount of extra 3 doses from using the well */
		int valueOfExtra3DosePotsFromWell = (int) ((amount * 0.05) * dose3);

		/**
		 * The values of 3 and 4 dose from using the factory outfit. 10% will be
		 * 4 converted to 4 dose pots
		 */
		int totalValueOf3DosePots = (int) ((amount * 0.9) * dose3);
		int totalValueOf4DosePots = (int) ((amount * 0.1) * dose4);

		/** The total value of selling the remaining potions */
		int totalValuePots = valueOfExtra3DosePotsFromWell + totalValueOf3DosePots + totalValueOf4DosePots;

		/** The net profit */
		int potProfit = totalValuePots - costToPurchaseRawMaterials + valueOfSavedSecondaryFromScroll;

		System.out.println("Profit from making potion without decanting: " + potProfit);

		/**
		 * Converting all potions into 3 dose pots and working out the gross
		 * profit
		 */
		int valueGeneratedFromConvertingInto3Dose = (int) (((amount * 0.1 * 4) / 3) * dose3);
		int totalValueAfterConvertingAllTo3Dose = totalValueOf3DosePots + valueGeneratedFromConvertingInto3Dose;

		/** Work out the net profit from converting into 3 dose pots */
		int potNetProfitFromConvertingTo3Dose = totalValueAfterConvertingAllTo3Dose + valueOfExtra3DosePotsFromWell
				+ valueOfSavedSecondaryFromScroll - costToPurchaseRawMaterials;

		/**
		 * Converting all potions into 4 dose pots and working out the gross
		 * profit
		 */
		int totalNumberOf3Dose = (int) ((amount * 0.05) + (amount * 0.9));
		int valueGeneratedFromConvertingInto4Dose = (int) (((totalNumberOf3Dose * 3) / 4) * dose4);
		int totalValueAfterConvertingAllTo4Dose = totalValueOf4DosePots + valueGeneratedFromConvertingInto4Dose;

		/** Work out the net profit from converting into 4 dose pots */
		int potNetProfitFromConvertingTo4Dose = totalValueAfterConvertingAllTo4Dose + valueOfSavedSecondaryFromScroll
				- costToPurchaseRawMaterials;

		/** Check to see which generates a greater profit and inform the user */
		if (potNetProfitFromConvertingTo3Dose > potNetProfitFromConvertingTo4Dose) {
			System.out.println("Profit is greater after converting to 3 dose by "
					+ (potNetProfitFromConvertingTo3Dose - potNetProfitFromConvertingTo4Dose)
					+ ", bringing the net profit to " + potNetProfitFromConvertingTo3Dose);

			return (potNetProfitFromConvertingTo3Dose);
		} else {
			System.out.println("Profit is greater after converting to 4 dose by "
					+ (potNetProfitFromConvertingTo4Dose - potNetProfitFromConvertingTo3Dose)
					+ ", bringing the net profit to " + potNetProfitFromConvertingTo4Dose);
			return (potNetProfitFromConvertingTo4Dose);
		}
	}

	/**
	 * Calculates profits/losses starting from grimy, clean and unfilled potions
	 */
	public void doAllCalculations() {

		printName();
		fromGrimy();
		fromClean(clean + vial);
		fromUnf(unf);
	}

	private boolean namePrinted = true;

	/** Local method to control whether the name needs to be printed */
	private void printName() {

		if (namePrinted == true) {
			System.out.println(name);
			namePrinted = false;
		}
	}

}
