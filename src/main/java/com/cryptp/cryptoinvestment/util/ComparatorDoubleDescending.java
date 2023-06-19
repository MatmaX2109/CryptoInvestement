package com.cryptp.cryptoinvestment.util;

import com.cryptp.cryptoinvestment.api.model.CryptoNormalizedRange;

import java.util.Comparator;

public class ComparatorDoubleDescending implements Comparator<CryptoNormalizedRange> {

    @Override
    public int compare(CryptoNormalizedRange o1, CryptoNormalizedRange o2) {
        return o2.getNormalizedRange().compareTo(o1.getNormalizedRange());
    }
}
