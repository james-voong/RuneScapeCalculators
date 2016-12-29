package crafting;

import parser.Parser;

public abstract class Leather {
	private String leatherName, itemName;
	private int hidePrice, leatherPrice, itemPrice, leatherPerItem;
	private static int priceToTan = 20;

	public Leather(String leatherName, String itemName, String hidePrice, String leatherPrice, int leatherPerItem) {

		this.leatherName = Parser.getNameOfItem(leatherName);
		this.itemName = Parser.getNameOfItem(itemName);

		this.hidePrice = Parser.getGrandExchangePrice(hidePrice);
		this.leatherPrice = Parser.getGrandExchangePrice(leatherPrice);
		this.itemPrice = Parser.getGrandExchangePrice(itemName);

		this.leatherPerItem = leatherPerItem;
	}

	public void printProfitFromTanning(int amount) {
		int netProfit = calculateProfitFromTanning(amount);
		System.out.printf("\nProfit from tanning %d %s is: %d", amount, leatherName, netProfit);
	}

	public void printProfitFromHide(int amount) {
		int netProfit = calculateProfitFromHide(amount);
		System.out.printf("\nProfit from making %d %s is: %d", amount, itemName, netProfit);
	}

	public void printProfitFromLeather(int amount) {
		int netProfit = calculateProfitFromLeather(amount, null);
		System.out.printf("\nProfit from making %d %s is: %d", amount, itemName, netProfit);
	}

	private int calculateProfitFromTanning(int amount) {
		int netProfit = (leatherPrice - (hidePrice + priceToTan) * amount);
		return netProfit;
	}

	private int calculateProfitFromHide(int amount) {
		int cost = (hidePrice + priceToTan) * amount * leatherPerItem;
		int netProfit = calculateProfitFromLeather(amount, cost);
		return netProfit;

	}

	private int calculateProfitFromLeather(int amount, Integer cost) {
		if (cost == null) {
			cost = leatherPrice * amount * leatherPerItem;
		}
		int savingsFromPortable = (int) (amount * 0.1 * (leatherPrice * leatherPerItem));
		int savingsFromScroll = getSavingsFromScroll(amount);
		int totalSavings = savingsFromPortable + savingsFromScroll;
		int grossProfit = itemPrice * amount;
		int netProfit = grossProfit - cost + totalSavings;

		return netProfit;

	}

	private int getSavingsFromScroll(int amount) {
		if (leatherPerItem < 3) {
			return 0;
		} else {
			int savingsFromScroll = 0;
			double percentageOfSaving = 0;
			String leatherName = getLeatherName();
			switch (leatherName) {

			case "Royal":
				percentageOfSaving = 0.05;
				break;
			case "Black":
				percentageOfSaving = 0.1;
				break;
			case "Red":
				percentageOfSaving = 0.15;
				break;
			case "Blue":
				percentageOfSaving = 0.2;
				break;
			case "Green":
				percentageOfSaving = 0.25;
				break;
			}

			savingsFromScroll = (int) (amount * percentageOfSaving * leatherPrice);

			return savingsFromScroll;
		}
	}

	private String getLeatherName() {
		String[] leatherNameArray = new String[1];
		leatherNameArray = leatherName.split(" ");

		return leatherNameArray[0];
	}

}
