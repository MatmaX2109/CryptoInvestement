package com.cryptp.cryptoinvestment.util;

import com.cryptp.cryptoinvestment.api.model.CryptoNormalizedRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class ComparatorDoubleDescendingTest {

    @InjectMocks
    private ComparatorDoubleDescending comparatorDoubleDescending;
    @Test
    void compare() {

        CryptoNormalizedRange cnr1 = CryptoNormalizedRange.builder().cryptoSymbol("s1").normalizedRange(1.0).build();
        CryptoNormalizedRange cnr2 = CryptoNormalizedRange.builder().cryptoSymbol("s2").normalizedRange(2.0).build();

        assertEquals(1,comparatorDoubleDescending.compare(cnr1, cnr2));
        assertEquals(-1,comparatorDoubleDescending.compare(cnr2, cnr1));
        assertEquals(0,comparatorDoubleDescending.compare(cnr1, cnr1));

    }
}