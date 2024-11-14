package com.ticket.Util;

import java.util.Random;

public class RandomNumber {

	public static String randomNumber() {
		Random random = new Random();
		long minRange = 1000000000L; // Minimum range (inclusive)
		long maxRange = 9999999999L; // Maximum range (inclusive)
		long randomNumber = random.nextLong(maxRange - minRange + 1) + minRange;
		return Long.toString(randomNumber);
	}
}
