package herblore;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import parser.Parser;

/** Abstract class that contains calculation methods */
public abstract class Potion {

	private String name;
	private int grimy, clean, unf, secondary;

	private int dose3, dose4;

	private int amount;

	private static int vial = Parser
			.getGrandExchangePrice("http://services.runescape.com/m=itemdb_rs/Vial_of_water/viewitem?obj=227");

	/** Constructor in order to do calculations on tradable potions */
	public Potion(String grimy, String clean, String unf, String secondary, String dose3, String dose4, int amount) {
		// Create service
		ExecutorService executorService = Executors.newWorkStealingPool();

		// Submit tasks to executorService.
		Future<String> name_f = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Parser.getNameOfItem(dose3);
			}
		});

		Future<Integer> grimy_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(grimy);
			}
		});

		Future<Integer> clean_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(clean);
			}
		});

		Future<Integer> unf_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(unf);
			}
		});

		Future<Integer> secondary_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(secondary);
			}
		});

		Future<Integer> dose3_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(dose3);
			}
		});

		Future<Integer> dose4_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(dose4);
			}
		});

		/**
		 * Shutdown executorService. (It will no longer accept tasks, but will
		 * complete the ones in progress.)
		 */
		executorService.shutdown();

		// Handle results of the tasks.
		try {
			// Note: get() will block until the task is complete
			this.name = name_f.get();
			this.grimy = grimy_f.get();
			this.clean = clean_f.get();
			this.unf = unf_f.get();
			this.secondary = secondary_f.get();
			this.dose3 = dose3_f.get();
			this.dose4 = dose4_f.get();
			this.amount = amount;

		} catch (InterruptedException e) {
			// TODO Handle it
		} catch (ExecutionException e) {
			// TODO Handle it
		}

	}

	/** Constructor for untradable potions */
	public Potion(String name, String grimy, String clean, String unf, String secondary, int amount) {

		this.name = name;
		this.grimy = Parser.getGrandExchangePrice(grimy);
		this.clean = Parser.getGrandExchangePrice(clean);
		this.unf = Parser.getGrandExchangePrice(unf);
		this.secondary = Parser.getGrandExchangePrice(secondary);
		this.dose3 = 0;
		this.dose4 = 0;

		this.amount = amount;

	}

	/** Works out the net profit starting from grimy herbs */
	public void printPotionNetProfitFromGrimy() {
		calculatePotionNetProfitFromGrimy();
	}

	/** Works out the net profit starting from clean herbs */
	public void printPotionNetProfitFromClean() {
		calculatePotionNetProfitFromClean(clean + vial);
	}

	/** Works out the net profit starting from unfilled potions */
	public void printPotionNetProfitFromUnf() {
		calculatePotionNetProfitFromUnf(unf);
	}

	/** Calculates profits/losses starting from grimy herbs */
	private void calculatePotionNetProfitFromGrimy() {

		printName();
		/** Calculate how much seed money will be required */
		int seed = amount * grimy;

		/** Calculate the profit from cleaning the herb */
		int profit = (clean - grimy) * amount;

		System.out.println("From Grimy:");
		System.out.println("Total seed money for " + amount + " herbs:" + seed);
		System.out.println("Profit from cleaning: " + profit + "\n");

		/** Call fromClean to do rest of the calculations */
		calculatePotionNetProfitFromClean(grimy + vial);
	}

	/** Calculates profits/losses starting from clean herbs */
	private void calculatePotionNetProfitFromClean(int rawCostOfPrimaryIngredient) {

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
		int valueFromMakingPot = calculatePotionNetProfitFromUnf(rawCostOfPrimaryIngredient + vial);
		System.out.println("NB there will still be remaining unfs, value of which will be " + valueOfExtraUnfsFromScroll
				+ ", bringing total net profit to " + (valueFromMakingPot + valueOfExtraUnfsFromScroll));

	}

	/** Calculates profits/losses starting from unfilled potions */
	private int calculatePotionNetProfitFromUnf(int rawCostOfPrimaryIngredient) {

		printName();
		if (rawCostOfPrimaryIngredient == unf) {
			System.out.println("From Unf:");
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
		 * converted to 4 dose pots
		 * 
		 * Also converting due to botanist's amulet. 5% will be converted to 4
		 * dose pots.
		 * 
		 * Totalling 15% of pots being converted to 4 dose pots
		 */
		int totalValueOf3DosePots = (int) ((amount * 0.85) * dose3);
		int totalValueOf4DosePots = (int) ((amount * 0.15) * dose4);

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
		printPotionNetProfitFromGrimy();
		printPotionNetProfitFromClean();
		printPotionNetProfitFromUnf();
	}

	private boolean namePrinted = true;

	/** Local method to control whether the name needs to be printed */
	private void printName() {

		if (namePrinted == true) {
			System.out.println();
			System.out.println(name);
			namePrinted = false;
		}
	}

	public void setGrimy(int grimy) {
		this.grimy = grimy;
	}

	public void setClean(int clean) {
		this.clean = clean;
	}

	public void setUnf(int unf) {
		this.unf = unf;
	}

	public void setSecondary(int secondary) {
		this.secondary = secondary;
	}

	public void setDose3(int dose3) {
		this.dose3 = dose3;
	}

	public void setDose4(int dose4) {
		this.dose4 = dose4;
	}

	public static void setVial(int vial) {
		Potion.vial = vial;
	}

}
