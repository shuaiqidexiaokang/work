package com.lzk.dto;

public class Result<T> {
    private boolean success;

    private T date;

    private String message;

    public Result() {
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, T date, String message) {
        this.success = success;
        this.date = date;
        this.message = message;
    }
}
