package com.charlezz.alfred.util;

public class Env {
    public static boolean isDebugMode(){
        return Boolean.getBoolean("debug");
    }
    public static String ADB_PATH = "adb_path";
}
