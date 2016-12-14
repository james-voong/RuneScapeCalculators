package parser;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	/** Returns the name of the item */
	public static String getNameOfItem(String url) {
		String name = "";
		try {
			Document doc = Jsoup.connect(url).get();
			name = doc.select("h2").first().ownText();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

	/** Returns the current grand exchange price of an item as an int */
	public static int getGrandExchangePrice(String url) {
		String grandExchangePrice = "";
		try {
			Document doc = Jsoup.connect(url).get();
			grandExchangePrice = doc.select("h3").select("span").attr("title");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parseInt(grandExchangePrice);
	}

	/**
	 * Parses a string into an integer, assuming UK number formatting is used
	 */
	private static int parseInt(String parseMe) {
		NumberFormat ukFormat = NumberFormat.getNumberInstance(Locale.UK);
		int parsed = 0;
		try {
			parsed = ukFormat.parse(parseMe).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsed;

	}
}
