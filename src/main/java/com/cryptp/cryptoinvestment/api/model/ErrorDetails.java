package com.cryptp.cryptoinvestment.api.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {
    public ErrorDetails(String msg) {
        this.msg = msg;
        this.timestamp = new Date();
    }

    private Date timestamp;
    private String msg;
}
