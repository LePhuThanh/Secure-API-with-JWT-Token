package com.phuthanh.SpringSecurity_JWT_Token.payloads.responses;

import lombok.Data;

@Data
public class DataResponse {
    private String status;
    private String message;
    public DataResponse (String status, String message) {
        this.status = status;
        this.message = message;
    }

}
