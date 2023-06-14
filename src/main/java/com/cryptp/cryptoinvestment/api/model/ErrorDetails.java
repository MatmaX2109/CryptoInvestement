package com.cryptp.cryptoinvestment.api.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(builderClassName = "ErrorDetailsBuilder")
@Getter
public class ErrorDetails {

    private Date timestamp;
    private String msg;

    public static class ErrorDetailsBuilder {
        ErrorDetailsBuilder timestamp() {
            this.timestamp=new Date();
            return this;
        }
    }

}
