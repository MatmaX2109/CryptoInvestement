package com.cryptp.cryptoinvestment.domain.model;

import lombok.Data;

@Data
public class CryptoExtreme {

    private Crypto oldest;
    private Crypto newest;
    private Crypto minValue;
    private Crypto maxValue;

    public void update(final Crypto crypto) {

        if (this.oldest == null){
            oldest = crypto;
        }

        if (this.newest == null){
            newest = crypto;
        }

        if (this.minValue == null){
            minValue = crypto;
        }

        if (this.maxValue == null){
            maxValue = crypto;
        }


        if (this.oldest.getTimestamp().compareTo(crypto.getTimestamp()) > 0) {
            oldest = crypto;
        }else {
            if (this.newest.getTimestamp().compareTo(crypto.getTimestamp()) < 0) {
                newest = crypto;
            }
        }

        if (this.minValue.getPrice() > crypto.getPrice()) {
            minValue = crypto;
        }else{
            if (this.maxValue.getPrice() < crypto.getPrice()) {
                maxValue = crypto;
            }
        }
    }
}
