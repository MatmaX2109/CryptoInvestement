package com.cryptp.cryptoinvestment.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(MockitoExtension.class)
class DateComparisonTest {

    @InjectMocks
    private DateComparison dateComparison;

    @Test
    void isSameDayUsingInstant() {
        assertTrue(DateComparison.isSameDayUsingInstant(new Date(), new Date()));

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        assertFalse(DateComparison.isSameDayUsingInstant(new Date(), dt));

    }
}