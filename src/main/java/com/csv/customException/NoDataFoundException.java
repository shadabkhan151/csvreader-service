package com.csv.customException;

public class NoDataFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public NoDataFoundException(String message) {
        this.message = message;
    }

    public NoDataFoundException(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}