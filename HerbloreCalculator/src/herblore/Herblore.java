package herblore;

public class Herblore {
	private static int amount = 10000;
	private static int grimy = 2756;
	private static int clean = 2995;
	private static int unf = 3240;
	private static int vial = 52;
	private static int secondary = 10;
	private static int dose3 = 2630;
	private static int dose4 = 3699;

	public static void fromGrimy() {
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

	public static void fromClean(int rawCostOfPrimaryIngredient) {
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

	public static int fromUnf(int rawCostOfPrimaryIngredient) {
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

	public static void doAllCalculations() {
		fromGrimy();
		fromClean(clean + vial);
		fromUnf(unf);
	}

	public static void main(String[] args) {
		doAllCalculations();
	}

}
