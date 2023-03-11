package com.techreturners.bookmanager.customException;

import org.springframework.http.HttpStatus;

public class CustomErrorResponse {
    //private HttpStatus status;
    private String errorMessage;

    public CustomErrorResponse(String errorMessage) {
        //this.status = status;
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
