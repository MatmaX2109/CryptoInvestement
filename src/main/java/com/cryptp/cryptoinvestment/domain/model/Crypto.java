package com.cryptp.cryptoinvestment.domain.model;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class Crypto {

    private Date timestamp;
    private String symbol;
    private Double price;


}
