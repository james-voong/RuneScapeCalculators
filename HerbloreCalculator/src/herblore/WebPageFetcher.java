package herblore;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WebPageFetcher {

	private URL fURL;

	private static final String HTTP = "http";
	private static final String HEADER = "header";
	private static final String CONTENT = "content";
	private static final String END_OF_INPUT = "\\Z";
	private static final String NEWLINE = System.getProperty("line.separator");

	private static void log(Object aObject) {
		System.out.println(aObject);
	}

	public static void main(String[] args) throws MalformedURLException {
		String url = args[0];
		String option = args[1];

		WebPageFetcher fetcher = new WebPageFetcher(url);

		if (HEADER.equalsIgnoreCase(option)) {
			log(fetcher.getPageHeader());
		} else if (CONTENT.equalsIgnoreCase(option)) {
			log(fetcher.getPageContent());
		} else {
			log("Unknown option.");
		}

	}

	public WebPageFetcher(URL aURL) {
		if (!HTTP.equals(aURL.getProtocol())) {
			throw new IllegalArgumentException("URL is not for HTTP Protocol: " + aURL);
		}
		fURL = aURL;
	}

	public WebPageFetcher(String aUrlName) throws MalformedURLException {
		this(new URL(aUrlName));
	}

	/** Fetch the HTML content of the page as simple text. */
	public String getPageContent() {
		String result = null;
		URLConnection connection = null;
		try {
			connection = fURL.openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			scanner.useDelimiter(END_OF_INPUT);
			result = scanner.next();
		} catch (IOException ex) {
			log("Cannot open connection to " + fURL.toString());
		}
		return result;
	}

	public String getPageHeader() {
		StringBuilder result = new StringBuilder();

		URLConnection connection = null;
		try {
			connection = fURL.openConnection();
		} catch (IOException ex) {
			log("Cannot open connection to URL: " + fURL);
		}

		// not all headers come in key-value pairs - sometimes the key is
		// null or an empty String
		int headerIdx = 0;
		String headerKey = null;
		String headerValue = null;
		while ((headerValue = connection.getHeaderField(headerIdx)) != null) {
			headerKey = connection.getHeaderFieldKey(headerIdx);
			if (headerKey != null && headerKey.length() > 0) {
				result.append(headerKey);
				result.append(" : ");
			}
			result.append(headerValue);
			result.append(NEWLINE);
			headerIdx++;
		}
		return result.toString();
	}

}
