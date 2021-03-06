package smithing;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import parser.Parser;

public abstract class Metal {
	private String barName, itemName;
	private int orePrice, barPrice, coalNumber, barsPerItem, itemSellPrice;

	private static int coalPrice = Parser
			.getGrandExchangePrice("http://services.runescape.com/m=itemdb_rs/a=13/Coal/viewitem?obj=453");

	public Metal(String barName, String ore, int coalNumber, String itemName, int barsPerItem) {
		// Create service
		ExecutorService executorService = Executors.newWorkStealingPool();

		// Submit tasks to executorService.
		Future<String> barName_f = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Parser.getNameOfItem(barName);
			}
		});

		Future<String> itemName_f = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Parser.getNameOfItem(itemName);
			}
		});

		Future<Integer> orePrice_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(ore);
			}
		});

		Future<Integer> barPrice_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(barName);
			}
		});

		Future<Integer> itemSellPrice_f = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Parser.getGrandExchangePrice(itemName);
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
			this.barName = barName_f.get();
			this.itemName = itemName_f.get();
			this.barPrice = barPrice_f.get();
			this.orePrice = orePrice_f.get();
			this.itemSellPrice = itemSellPrice_f.get();

		} catch (InterruptedException e) {
			// TODO Handle it
		} catch (ExecutionException e) {
			// TODO Handle it
		}

		// this.barName = Parser.getNameOfItem(barName);
		// this.orePrice = Parser.getGrandExchangePrice(ore);
		// this.barPrice = Parser.getGrandExchangePrice(barName);

		// this.itemName = Parser.getNameOfItem(itemName);
		// this.itemSellPrice = Parser.getGrandExchangePrice(itemName);

		this.coalNumber = coalNumber;
		this.barsPerItem = barsPerItem;

	}

	public void printNetProfitFromSmeltingBar(int amount) {
		int netProfit = calculateNetProfitFromSmeltingBar(amount);
		System.out.println("\nNet profit from smelting " + amount + " " + barName + "s: " + netProfit);
	}

	public void printNetProfitSmithingFromOre(int amount) {
		int netProfit = calculateNetProfitSmithingFromOre(amount);
		System.out.println("\nNet profit from smithing " + amount + " " + itemName + "s: " + netProfit);
	}

	public void printNetProfitFromSmithingBars(int amount) {
		int netProfit = calculateNetProfitFromSmithingBars(amount, null, null);
		System.out.println("\nNet profit from smithing " + amount + " " + itemName + "s: " + netProfit);
	}

	/** Calculates net profit of buying ore then selling the bar */
	private int calculateNetProfitFromSmeltingBar(int amount) {
		int barCost = coalNumber * coalPrice + orePrice;
		int netProfit = barPrice - barCost;
		int netProfitWithPortable = (int) ((netProfit + 0.05 * barPrice) * amount);
		return netProfitWithPortable;
	}

	/** Calculates net profit of buying ore, making bars then smithing them */
	private int calculateNetProfitSmithingFromOre(int amount) {

		int barCost = coalNumber * coalPrice + orePrice;
		int totalBarCost = barCost * amount * barsPerItem;

		double extraBarsFromSmelting = amount * 0.05 * (barPrice * barsPerItem);
		// double extraBarsFromSmithing = amount * 0.1 * barPrice;

		// int savingsFromScroll = getSavingsFromScroll(amount);

		// int extraBarsTotal = (int) (extraBarsFromSmelting +
		// extraBarsFromSmithing);

		// int grossProfit = itemSellPrice * amount;
		int netProfit = calculateNetProfitFromSmithingBars(amount, totalBarCost, extraBarsFromSmelting);

		return netProfit;
	}

	/** Calculates net profit of buying bars and smithing them */
	private int calculateNetProfitFromSmithingBars(int amount, Integer totalBarCost, Double extraBarsFromSmelting) {
		if (totalBarCost == null) {
			totalBarCost = barPrice * amount * barsPerItem;
		}
		if (extraBarsFromSmelting == null) {
			extraBarsFromSmelting = 0.0;
		}

		double extraBarsFromSmithing = amount * 0.1 * (barPrice * barsPerItem);

		// This accounts for the scroll of efficiency
		int savingsFromScroll = getSavingsFromScroll(amount);

		int extraBarsTotal = (int) (extraBarsFromSmelting + extraBarsFromSmithing);
		int grossProfit = itemSellPrice * amount;
		int netProfit = grossProfit - totalBarCost + extraBarsTotal + savingsFromScroll;

		return netProfit;
	}

	private String getMetalName() {
		String[] metalName = new String[1];
		metalName = barName.split(" ");

		return metalName[0];
	}

	/** Returns the amount saved from the scroll of efficiency */
	private int getSavingsFromScroll(int amount) {
		if (barsPerItem < 3) {
			return 0;
		} else {
			int savingsFromScroll = 0;
			double percentageOfSaving = 0;
			String metalName = getMetalName();
			switch (metalName) {

			case "Rune":
				percentageOfSaving = 0.05;
				break;
			case "Adamant":
				percentageOfSaving = 0.08;
				break;
			case "Mithril":
				percentageOfSaving = 0.1;
				break;
			case "Steel":
				percentageOfSaving = 0.2;
				break;
			case "Iron":
				percentageOfSaving = 0.25;
				break;
			case "Bronze":
				percentageOfSaving = 0.5;
				break;
			}
			savingsFromScroll = (int) (amount * percentageOfSaving * barPrice);
			return savingsFromScroll;
		}
	}

}
