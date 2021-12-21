package com.crypto.investment.sim.exceptions;

import java.net.URL;

@SuppressWarnings("unused")
public class GET_Exception extends Exception{
    private String message;
    private String error_code;
    private String url;

    /**
     * Creates a new GET Exception, converts the error code into a String
     * @param message the error message e.g. Unauthorized
     * @param url the URL of the attempted GET request
     * @param error_code the error code e.g. 401
     */
    public GET_Exception(String message, URL url, int error_code){
        this.message = message;
        this.error_code = String.valueOf(error_code);
        this.url = url.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
