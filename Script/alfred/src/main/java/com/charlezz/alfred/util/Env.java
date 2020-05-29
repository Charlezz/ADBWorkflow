package com.charlezz.alfred.util;

import java.util.Arrays;
import java.util.List;

public class Env {
    public static boolean isUnitTestMode(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        List<StackTraceElement> list = Arrays.asList(stackTrace);
        for (StackTraceElement element : list) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }
    public static String ADB_PATH = "adb_path";
}
