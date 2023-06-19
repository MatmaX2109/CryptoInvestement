package com.cryptp.cryptoinvestment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CryptoCache {

    @CacheEvict(value = { "allCryptoPath", "crypto" }, allEntries = true)
    public void evictAllCache(){}
}
