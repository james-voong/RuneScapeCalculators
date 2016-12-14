package smithing;

import parser.Parser;

public abstract class Metal {
	private String barName, itemName;
	private int orePrice, barPrice, oreNumber, barsPerItem, itemSellPrice;

	private static int coalPrice = Parser
			.getGrandExchangePrice("http://services.runescape.com/m=itemdb_rs/a=13/Coal/viewitem?obj=453");

	public Metal(String barName, String ore, int oreNumber, String itemName, int barsPerItem) {

		this.barName = Parser.getNameOfItem(barName);
		this.orePrice = Parser.getGrandExchangePrice(ore);
		this.barPrice = Parser.getGrandExchangePrice(barName);
		this.oreNumber = oreNumber;
		this.barsPerItem = barsPerItem;
		this.itemName = Parser.getNameOfItem(itemName);
		this.itemSellPrice = Parser.getGrandExchangePrice(itemName);

	}

	public void printNetProfitFromSmeltingBar(int amount) {
		int netProfit = calculateNetProfitFromSmeltingBar(amount);
		System.out.println("Net profit from smelting " + amount + " " + barName + "s: " + netProfit);
	}

	public void printNetProfitSmithingFromOre(int amount) {
		int netProfit = calculateNetProfitSmithingFromOre(amount);
		System.out.println("Net profit from smithing " + amount + " " + itemName + "s: " + netProfit);
	}

	public void printNetProfitFromSmithingBars(int amount) {
		int netProfit = calculateNetProfitFromSmithingBars(amount);
		System.out.println("Net profit from smithing " + amount + " " + itemName + "s: " + netProfit);
	}

	private int calculateNetProfitFromSmeltingBar(int amount) {
		int coalCost = oreNumber * coalPrice;
		int barCost = coalCost + orePrice;
		int netProfit = barPrice - barCost;
		int netProfitWithPortable = (int) ((netProfit + 0.05 * barPrice) * amount);
		return netProfitWithPortable;
	}

	private int calculateNetProfitSmithingFromOre(int amount) {
		int barCost = oreNumber * coalPrice + orePrice;
		int totalBarCost = barCost * amount * barsPerItem;
		double extraBarsFromSmelting = amount * 0.05 * barPrice;
		double extraBarsFromSmithing = amount * 0.1 * barPrice;
		int extraBarsTotal = (int) (extraBarsFromSmelting + extraBarsFromSmithing);
		int grossProfit = itemSellPrice * amount;
		int netProfit = grossProfit - totalBarCost + extraBarsTotal;

		return netProfit;
	}

	private int calculateNetProfitFromSmithingBars(int amount) {
		int barsCost = barPrice * amount * barsPerItem;
		int extraBars = (int) (amount * 0.1 * barPrice);
		int grossProfit = itemSellPrice * amount;
		int netProfit = grossProfit - barsCost + extraBars;

		return netProfit;
	}

}
