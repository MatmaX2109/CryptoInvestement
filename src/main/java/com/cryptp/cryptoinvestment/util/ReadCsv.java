package com.cryptp.cryptoinvestment.util;

import com.cryptp.cryptoinvestment.domain.model.Crypto;
import com.cryptp.cryptoinvestment.domain.model.CryptoEnum;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReadCsv {

    private final Environment env;
    private String getSomeKey(final @NotNull CryptoEnum cryptoName){
        return env.getProperty("crypto."+cryptoName.toString().toLowerCase());
    }
    @Cacheable(value = "crypto", key = "#cryptoName")
    public List<Crypto> readCrypto(final CryptoEnum cryptoName){

        List<Crypto> cryptoList;

        try {

            CSVReader reader =
                    new CSVReaderBuilder(new FileReader(getSomeKey(cryptoName))).
                            withSkipLines(1). // Skiping firstline as it is header
                            build();

            cryptoList = (reader.readAll().stream().map(data -> Crypto.builder()
                            .timestamp(new Date(Long.parseLong(data[0])))
                            .symbol(data[1])
                            .price(Double.valueOf(data[2]))
                            .build())
                    .collect(Collectors.toList()));

            return cryptoList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
