package com.charlezz.alfred.runner;

public class RunnerResult {
    private boolean isSuccess;
    private String message;

    public RunnerResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public static RunnerResult success(String result){
        return new RunnerResult(true, result);
    }

    public static RunnerResult error(String msg){
        return new RunnerResult(false, msg);
    }

}
