package com.ecommerce.exceptions;

public class CountryException extends RuntimeException{
    public CountryException() {
    }

    public CountryException(String message) {
        super(message);
    }

    public CountryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryException(Throwable cause) {
        super(cause);
    }

    public CountryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
