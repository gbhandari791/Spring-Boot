package com.blog.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static final DateTimeFormatter FORMAT_ddMMyyyy_HHmm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
	
	public static String formatDate(Instant instant) {
        if (instant == null) return null;
        return FORMAT_ddMMyyyy_HHmm.format(instant);
    }
}

