package com.cryptp.cryptoinvestment.application;

import com.cryptp.cryptoinvestment.domain.model.*;
import com.cryptp.cryptoinvestment.util.ComparatorDoubleDescending;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.cryptp.cryptoinvestment.util.DateComparison.isSameDayUsingInstant;

@RequiredArgsConstructor
@Service
public class CryptoService {

    private final Environment env;
    public String getSomeKey(final @NotNull CryptoEnum cryptoName){
        return env.getProperty("crypto."+cryptoName.toString().toLowerCase());
    }

    public TreeSet<CryptoNormalizedRange> parseAll(){
        return parseAll(null);
    }

    public CoinInfos parse(final CryptoEnum cryptoName){
        return parse(cryptoName, null);
    }

    public CoinInfos parse(final CryptoEnum cryptoName, final Date day){

        CryptoExtreme cryptoExtreme = new CryptoExtreme();
        CoinInfos coinInfos = CoinInfos.builder().extreme(cryptoExtreme).build();

        try{
            CSVReader reader=
                    new CSVReaderBuilder(new FileReader(getSomeKey(cryptoName))).
                            withSkipLines(1). // Skiping firstline as it is header
                            build();

            coinInfos.setList(reader.readAll().stream().map(data-> {

                        Crypto crypto =Crypto.builder()
                                .timestamp(new Date(Long.parseLong(data[0])))
                                .symbol(data[1])
                                .price(Double.valueOf(data[2]))
                                .build();

                        if (day != null) {
                            if (isSameDayUsingInstant(new Date(Long.parseLong(data[0])), (day))) {
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
