package com.cryptp.cryptoinvestment.application.service;

import com.cryptp.cryptoinvestment.api.exception.CryptoSymbolException;
import com.cryptp.cryptoinvestment.domain.model.CryptoPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptoPathService {

    private final CryptoPathCacheComponent cryptoPathCacheComponent;

    public List<CryptoPath> findAll(){
        return cryptoPathCacheComponent.findAll();
    }

    public CryptoPath find(String cryptoSymbol){

        Optional<CryptoPath> cryptoPath = cryptoPathCacheComponent.findAll().stream().filter(a-> a.getSymbol().equalsIgnoreCase(cryptoSymbol)).findFirst();
        if(cryptoPath.isPresent()){
            return cryptoPath.get();
        }else {
            throw new CryptoSymbolException(cryptoSymbol);

        }
    }
}
