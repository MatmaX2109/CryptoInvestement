package com.cryptp.cryptoinvestment.application;

import com.cryptp.cryptoinvestment.domain.model.*;
import com.cryptp.cryptoinvestment.util.ComparatorDoubleDescending;
import com.cryptp.cryptoinvestment.util.ReadCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.cryptp.cryptoinvestment.util.DateComparison.isSameDayUsingInstant;

@RequiredArgsConstructor
@Service
public class CryptoService {
    private final ReadCsv readCsv;
    public TreeSet<CryptoNormalizedRange> parseAll(){
        return parseAll(null);
    }
    public CoinInfos parse(final CryptoEnum cryptoName){
        return parse(cryptoName, null);
    }
    public CoinInfos parse(final CryptoEnum cryptoName, final Date day){

        CryptoExtreme cryptoExtreme = new CryptoExtreme();
        CoinInfos coinInfos = CoinInfos.builder().extreme(cryptoExtreme).build();

        coinInfos.setList(readCsv.readCrypto(cryptoName).stream().map(data-> {

                    Crypto crypto = Crypto.builder()
                            .timestamp(data.getTimestamp())
                            .symbol(data.getSymbol())
                            .price(data.getPrice())
                            .build();

                    if (day != null) {
                        if (isSameDayUsingInstant(data.getTimestamp(), (day))) {
                            cryptoExtreme.update(crypto);
                            return crypto;
                        }
                        return null;
                    }

                    cryptoExtreme.update(crypto);
                    return crypto;
                })
                .collect(Collectors.toList()));

        return coinInfos;
    }
    public TreeSet<CryptoNormalizedRange> parseAll(final Date day){

        TreeSet<CryptoNormalizedRange> cryptoNormalizedRangeTreeSet = new TreeSet<>(new ComparatorDoubleDescending());

        for(CryptoEnum e : CryptoEnum.values()){
            CoinInfos coinInfos = parse(e, day);
            double normalizedRange = (coinInfos.getExtreme().getMaxValue().getPrice() - coinInfos.getExtreme().getMinValue().getPrice()) / coinInfos.getExtreme().getMinValue().getPrice();

            cryptoNormalizedRangeTreeSet.add(CryptoNormalizedRange.builder().cryptoEnum(e).normalizedRange(normalizedRange).build());
        }
        return cryptoNormalizedRangeTreeSet;
    }
}
