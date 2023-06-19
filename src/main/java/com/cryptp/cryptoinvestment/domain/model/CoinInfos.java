package com.cryptp.cryptoinvestment.domain.model;

import com.cryptp.cryptoinvestment.api.model.CryptoExtreme;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CoinInfos {
    private List<Crypto> list;
    private CryptoExtreme extreme;
}
