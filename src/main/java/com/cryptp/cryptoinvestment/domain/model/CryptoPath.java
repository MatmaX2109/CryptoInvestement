package com.cryptp.cryptoinvestment.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= "CRYPTO_CSV_PATH" )
public class CryptoPath {

    @Id
    @Column
    private int ID;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "PATH")
    private String url;
}
