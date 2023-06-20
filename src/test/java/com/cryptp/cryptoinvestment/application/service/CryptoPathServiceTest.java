package com.cryptp.cryptoinvestment.application.service;

import com.cryptp.cryptoinvestment.domain.model.CryptoPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoPathServiceTest {

    @InjectMocks
    private CryptoPathService cryptoPathService;

    @Mock
    private CryptoPathCacheComponent cryptoPathCacheComponent;

    @Test
    void find() {
        CryptoPath cryptoPath1 = new CryptoPath();
        cryptoPath1.setSymbol("s1");
        cryptoPath1.setUrl("url1");

        CryptoPath cryptoPath2 = new CryptoPath();
        cryptoPath2.setSymbol("s2");
        cryptoPath2.setUrl("url2");

        List<CryptoPath> cryptoPaths = Stream.of(cryptoPath1, cryptoPath2).toList();

        when(cryptoPathCacheComponent.findAll()).thenReturn(cryptoPaths);

        assertEquals(cryptoPathService.find("s1"), cryptoPath1);
    }
}