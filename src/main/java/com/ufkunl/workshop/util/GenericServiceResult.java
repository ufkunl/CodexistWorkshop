package com.ufkunl.workshop.util;

import lombok.Data;

@Data
public class GenericServiceResult {

    private boolean success;
    private String message;
    private Object data;

    public GenericServiceResult(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
