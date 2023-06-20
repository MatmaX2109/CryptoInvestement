package com.cryptp.cryptoinvestment.application.service;

import com.cryptp.cryptoinvestment.api.model.CryptoExtreme;
import com.cryptp.cryptoinvestment.api.model.CryptoNormalizedRange;
import com.cryptp.cryptoinvestment.domain.model.CoinInfos;
import com.cryptp.cryptoinvestment.domain.model.Crypto;
import com.cryptp.cryptoinvestment.domain.model.CryptoPath;
import com.cryptp.cryptoinvestment.util.ComparatorDoubleDescending;
import com.cryptp.cryptoinvestment.util.ReadCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.cryptp.cryptoinvestment.util.DateComparison.isSameDayUsingInstant;

@RequiredArgsConstructor
@Service
public class CryptoService {
    private final ReadCsv readCsv;
    private final CryptoPathService cryptoPathService;

    public TreeSet<CryptoNormalizedRange> getAllCryptoNormalized(){
        return getAllCryptoNormalized(null);
    }
    public CoinInfos parse(final String cryptoName){
        return parse(cryptoName, null);
    }
    public CoinInfos parse(final String cryptoName, final Date day){

        CryptoPath cryptoPath = cryptoPathService.find(cryptoName);

        CryptoExtreme cryptoExtreme = new CryptoExtreme();
        CoinInfos coinInfos = CoinInfos.builder().extreme(cryptoExtreme).build();

        coinInfos.setList(readCsv.readCrypto(cryptoPath).stream().map(data-> {

                    if (day != null && !isSameDayUsingInstant(data.getTimestamp(), (day))) {
                        return null;
                    }

                    cryptoExtreme.update(data);
                    return data;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        return coinInfos;
    }
    public TreeSet<CryptoNormalizedRange> getAllCryptoNormalized(final Date day){

        TreeSet<CryptoNormalizedRange> cryptoNormalizedRangeTreeSet = new TreeSet<>(new ComparatorDoubleDescending());

        for(CryptoPath e : cryptoPathService.findAll()){
            CoinInfos coinInfos = parse(e.getSymbol(), day);
            double normalizedRange = (coinInfos.getExtreme().getMaxValue().getPrice() - coinInfos.getExtreme().getMinValue().getPrice()) / coinInfos.getExtreme().getMinValue().getPrice();

            cryptoNormalizedRangeTreeSet.add(CryptoNormalizedRange.builder().cryptoSymbol(e.getSymbol()).normalizedRange(normalizedRange).build());
        }
        return cryptoNormalizedRangeTreeSet;
    }
}
