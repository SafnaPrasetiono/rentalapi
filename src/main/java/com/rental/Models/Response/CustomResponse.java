package com.rental.Models.Response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CustomResponse {
    private HttpStatus status;
    private String message;
    private Object data;

    public void MakeResponsed(HttpStatus status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
