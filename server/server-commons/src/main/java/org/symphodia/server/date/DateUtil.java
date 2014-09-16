package org.symphodia.server.date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by alekkart on 2014.09.01..
 */
public class DateUtil {

    public static Date toDate(int year, int month, int day) {
        return toDate(LocalDate.of(year, month, day));
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
