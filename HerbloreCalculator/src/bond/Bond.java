package bond;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import parser.Parser;

public class Bond {
	private static double bondPriceGold;
	private static double goldPriceNZD;

	public static void doCalculation() {
		setFields();
		double bondPrice = bondPriceGold * goldPriceNZD;
		BigDecimal bd = new BigDecimal(bondPrice);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		bondPrice = bd.doubleValue();
		System.out.println("Bond cost in black market gold is $" + bondPrice);
	}

	private static void setFields() {
		String valueRuneWealth = "";
		String valueXE = "";
		ExecutorService executorService = Executors.newWorkStealingPool();

		Future<String> valueRuneWealth_f = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				String value = "";
				try {
					Document doc = Jsoup.connect("http://runewealth.com/").get();
					value = doc.select("p.data-price").first().attr("data-price");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return value;
			}
		});

		Future<String> valueXE_f = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				String value = "";
				try {
					Document doc = Jsoup
							.connect("http://www.xe.com/currencyconverter/convert/?Amount=100&From=USD&To=NZD").get();
					value = doc.select("span.uccResultUnit").first().attr("data-amount");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return value;
			}
		});

		Future<Double> bondPriceGold_f = executorService.submit(new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				return (double) (Parser.getGrandExchangePrice(
						"http://services.runescape.com/m=itemdb_rs/Bond/viewitem?obj=29492") / 1000000);
			}
		});

		executorService.shutdown();
		try {
			// Note: get() will block until the task is complete
			valueRuneWealth = valueRuneWealth_f.get();
			valueXE = valueXE_f.get();
			bondPriceGold = bondPriceGold_f.get();

		} catch (InterruptedException e) {
			// TODO Handle it
		} catch (ExecutionException e) {
			// TODO Handle it
		}

		double conversionRate = Parser.parseDouble(valueXE);
		double priceOfGoldNZD = Parser.parseDouble(valueRuneWealth) * conversionRate;
		goldPriceNZD = priceOfGoldNZD;

	}

}
