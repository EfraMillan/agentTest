package com.org.testagent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws IOException {
		fetch("http://www.google.com");
		fetch("http://www.yahoo.com");
	}

	private static void fetch(final String address) {

		try {
			final URL url = new URL(address);
			final URLConnection connection = url.openConnection();
			final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine = null;
			final StringBuffer sb = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
				System.out.println("Content" + inputLine);
			}
			System.out.println("Content size: " + sb.length());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
