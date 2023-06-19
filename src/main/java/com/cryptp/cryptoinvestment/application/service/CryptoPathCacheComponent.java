package com.cryptp.cryptoinvestment.application.service;

import com.cryptp.cryptoinvestment.domain.model.CryptoPath;
import com.cryptp.cryptoinvestment.domain.repository.CryptoPathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CryptoPathCacheComponent {

    private final CryptoPathRepository repository;

    @Cacheable(value = "allCryptoPath")
    public List<CryptoPath> findAll(){
        return repository.findAll();
    }
}
