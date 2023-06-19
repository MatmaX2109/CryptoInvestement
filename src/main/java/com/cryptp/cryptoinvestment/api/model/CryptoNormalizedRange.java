package com.cryptp.cryptoinvestment.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoNormalizedRange {
    private String cryptoSymbol;
    private Double normalizedRange;
}
