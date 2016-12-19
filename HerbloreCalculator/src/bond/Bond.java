package bond;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import parser.Parser;

public class Bond {
	private static double bondPriceGold = (Parser
			.getGrandExchangePrice("http://services.runescape.com/m=itemdb_rs/Bond/viewitem?obj=29492") / 1000000);
	private static double goldPriceNZD = parseGoldPriceIntoNZD();

	public static void doCalculation() {
		double bondPrice = bondPriceGold * goldPriceNZD;
		BigDecimal bd = new BigDecimal(bondPrice);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		bondPrice = bd.doubleValue();
		System.out.println("Bond cost in gold is $" + bondPrice);

	}

	private static double parseGoldPriceIntoNZD() {
		String value = "";
		try {
			Document doc = Jsoup.connect("http://runewealth.com/").get();
			value = doc.select("p.data-price").first().attr("data-price");
		} catch (IOException e) {
			e.printStackTrace();
		}
		double priceOfGoldNZD = Parser.parseDouble(value) * getParsedConversionRate();

		return priceOfGoldNZD;
	}

	private static double getParsedConversionRate() {
		String value = "";
		try {
			Document doc = Jsoup.connect("http://www.xe.com/currencyconverter/convert/?Amount=100&From=USD&To=NZD")
					.get();
			value = doc.select("span.uccResultUnit").first().attr("data-amount");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Parser.parseDouble(value);
	}

}
