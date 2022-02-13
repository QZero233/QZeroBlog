package com.qzero.blog.exception;

public class ResponsiveException extends Exception {

    private int errorCode;
    private String errorMessage;

    public ResponsiveException(int errorCode, String errorMessage) {
        super(String.format("code: %d ; message: %s", errorCode,errorMessage));
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
