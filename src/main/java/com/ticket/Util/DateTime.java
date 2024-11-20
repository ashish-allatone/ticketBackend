package com.ticket.Util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

	public static String dateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd yyyy hh:mm");
		ZonedDateTime indiaTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		String formattedDate = indiaTime.format(formatter);
		return formattedDate;
	}

}
