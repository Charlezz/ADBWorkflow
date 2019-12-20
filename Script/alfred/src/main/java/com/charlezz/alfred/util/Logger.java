package com.charlezz.alfred.util;

public class Logger {

    private String tag;

    public static Logger getLogger(String tag){
        return new Logger(tag);
    }

    private Logger(String tag) {
        this.tag = tag;
    }


    public void i(String msg){
        if(Env.isDebugMode()){
            System.out.println(String.format("%s: %s", tag, msg));
        }
    }
    public void e(String msg, Throwable throwable){
        System.out.println(String.format("%s: %s\n%s", tag, msg, throwable.getMessage()));
    }
    public void e(String msg){
        System.out.println(String.format("%s: %s", tag, msg));
    }
}
