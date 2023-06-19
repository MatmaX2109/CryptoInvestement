package com.cryptp.cryptoinvestment.api.exception;

public class CryptoSymbolException extends RuntimeException{

    public final static String CRYOPT_NOT_EXIST = "Crypto coin with symbol << %s >> not exist;";

    public CryptoSymbolException(final String crypto) {
        super(String.format(CRYOPT_NOT_EXIST,crypto));
    }
}
