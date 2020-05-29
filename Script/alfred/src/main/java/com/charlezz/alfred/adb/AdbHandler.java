package com.charlezz.alfred.adb;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Map;

import com.charlezz.alfred.util.Env;
import com.charlezz.alfred.util.Logger;

public class AdbHandler {
    public static String start(String command) {
        Logger logger = Logger.getLogger(AdbHandler.class.getSimpleName());
        try {
            ProcessBuilder pb = new ProcessBuilder();
            Map<String, String> env = pb.environment();
            String sdkPath = env.get(Env.ADB_PATH);
            String cmd;
            if (Env.isUnitTestMode()) {
                cmd = command; //로컬 테스트 목적
            } else {
                cmd = sdkPath + File.separator + command;
            }

            String[] callCmd = {"/bin/bash", "-c", cmd};
            pb.command(callCmd);
            Process process = null;
            logger.i("process 시작");
            process = pb.start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();

            int lineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                logger.i(lineCount+":"+line);
                sb.append(line).append("\n");
                lineCount++;
            }
            logger.i("process 끝날때까지 기다림");
            process.waitFor();
            logger.i("process 종료");
            return sb.toString();
        } catch (Exception e) {
            logger.e(e.getMessage());
        }
        return null;
    }
}
