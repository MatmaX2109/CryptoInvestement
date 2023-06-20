package com.cryptp.cryptoinvestment.util;

import com.cryptp.cryptoinvestment.domain.model.Crypto;
import com.cryptp.cryptoinvestment.domain.model.CryptoPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ReadCsvTest {

    @InjectMocks
    private ReadCsv readCsv;

    @Test
    void readCrypto() {
        CryptoPath cryptoPath = new CryptoPath();
        cryptoPath.setUrl("src/test/resources/static/BTC_values.csv");
        cryptoPath.setSymbol("btc");
        List<Crypto> test = readCsv.readCrypto(cryptoPath);
        assertEquals(100, test.size());
    }
}