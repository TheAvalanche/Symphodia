package org.symphodia.server.commons.date;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Calendar;

public class DateUtilTest {

    @Test
    public void toDateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Assert.assertEquals(DateUtil.toDate(2000, 1, 1), calendar.getTime());

        Assert.assertEquals(DateUtil.toDate(LocalDate.of(2000, 1, 1)), calendar.getTime());
    }
}
