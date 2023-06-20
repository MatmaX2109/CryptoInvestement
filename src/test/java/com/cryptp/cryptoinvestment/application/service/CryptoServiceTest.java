package com.cryptp.cryptoinvestment.application.service;

import com.cryptp.cryptoinvestment.api.model.CryptoExtreme;
import com.cryptp.cryptoinvestment.api.model.CryptoNormalizedRange;
import com.cryptp.cryptoinvestment.domain.model.CoinInfos;
import com.cryptp.cryptoinvestment.domain.model.Crypto;
import com.cryptp.cryptoinvestment.domain.model.CryptoPath;
import com.cryptp.cryptoinvestment.util.ReadCsv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    @InjectMocks
    private CryptoService cryptoService;
    @Mock
    private CryptoPathService cryptoPathService;
    @Mock
    private ReadCsv readCsv;

    private static String SYMBOL1 = "s1";
    private static String SYMBOL2 = "s2";
    private static String URL1 = "url1";
    private static String URL2 = "url2";

    private Date dt1;
    private Date dt2;
    private Crypto cryptoS1_1;
    private Crypto cryptoS1_2;
    private Crypto cryptoS2_1;

    @BeforeEach
    void beforeEach(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt1 = c.getTime();
        c.add(Calendar.DATE, 2);
        dt2 = c.getTime();

        cryptoS1_1 = Crypto.builder()
                .timestamp(dt1)
                .symbol(SYMBOL1)
                .price(1.0)
                .build();

        cryptoS1_2 = Crypto.builder()
                .timestamp(dt2)
                .symbol(SYMBOL1)
                .price(2.0)
                .build();

        cryptoS2_1 = Crypto.builder()
                .timestamp(dt2)
                .symbol(SYMBOL2)
                .price(2.0)
                .build();
    }

    @Test
    void parse() {

        List<Crypto> cryptos1 = Stream.of(cryptoS1_1, cryptoS1_2).collect(Collectors.toList());

        CryptoPath cryptoPath1 = new CryptoPath();
        cryptoPath1.setSymbol(SYMBOL1);
        cryptoPath1.setUrl(URL1);

        when(cryptoPathService.find(any())).thenReturn(cryptoPath1);

        when(readCsv.readCrypto(cryptoPath1)).thenReturn(cryptos1);

        CoinInfos coinInfos = cryptoService.parse(SYMBOL1, null);
        assertEquals(2, coinInfos.getList().size());
        assertEquals(cryptoS1_1, coinInfos.getExtreme().getMinValue());
        assertEquals(cryptoS1_2, coinInfos.getExtreme().getMaxValue());
        assertEquals(cryptoS1_2, coinInfos.getExtreme().getNewest());
        assertEquals(cryptoS1_1, coinInfos.getExtreme().getOldest());


        CoinInfos coinInfosDate = cryptoService.parse(SYMBOL1, dt1);
        assertEquals(1, coinInfosDate.getList().size());
        assertEquals(cryptoS1_1, coinInfosDate.getExtreme().getMinValue());
        assertEquals(cryptoS1_1, coinInfosDate.getExtreme().getMaxValue());
        assertEquals(cryptoS1_1, coinInfosDate.getExtreme().getNewest());
        assertEquals(cryptoS1_1, coinInfosDate.getExtreme().getOldest());
    }

    @Test
    void getAllCryptoNormalized() {

        CryptoPath cryptoPath1 = new CryptoPath();
        cryptoPath1.setSymbol(SYMBOL1);
        cryptoPath1.setUrl(URL1);

        CryptoPath cryptoPath2 = new CryptoPath();
        cryptoPath2.setSymbol(SYMBOL2);
        cryptoPath2.setUrl(URL2);

        List<CryptoPath> cryptoPaths = Stream.of(cryptoPath1, cryptoPath2).collect(Collectors.toList());

        when(cryptoPathService.findAll()).thenReturn(cryptoPaths);

        List<Crypto> cryptos1 = Stream.of(cryptoS1_1, cryptoS1_2).collect(Collectors.toList());
        List<Crypto> cryptos2 = Stream.of(cryptoS2_1).collect(Collectors.toList());

        when(cryptoPathService.find(SYMBOL1)).thenReturn(cryptoPath1);
        when(cryptoPathService.find(SYMBOL2)).thenReturn(cryptoPath2);

        when(readCsv.readCrypto(cryptoPath1)).thenReturn(cryptos1);
        when(readCsv.readCrypto(cryptoPath2)).thenReturn(cryptos2);

        TreeSet<CryptoNormalizedRange> test = cryptoService.getAllCryptoNormalized(null);
        assertEquals(2, test.size());
        assertEquals(cryptoS1_1.getSymbol(), test.first().getCryptoSymbol());
        assertEquals(1.0, test.first().getNormalizedRange());

    }
}