package com.cryptp.cryptoinvestment.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoNormalizedRange {
    private CryptoEnum cryptoEnum;
    private Double normalizedRange;
}
