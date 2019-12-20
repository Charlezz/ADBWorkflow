package com.charlezz.alfred.runner.input;

import com.charlezz.alfred.adb.Adb;
import com.charlezz.alfred.alfred.Item;
import com.charlezz.alfred.alfred.ScriptFilter;
import com.charlezz.alfred.runner.Runner;
import com.charlezz.alfred.runner.RunnerResult;
import com.charlezz.alfred.util.Env;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.regex.Pattern;

public class InputTextRunner1 implements Runner {
    ArrayList<Item> items = new ArrayList<>();
    Pattern pattern1 = Pattern.compile("List of devices attached");
    Pattern pattern2 = Pattern.compile("device");

    @Override
    public RunnerResult run(String... args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder();
        Map<String, String> env = pb.environment();
        String sdkPath = env.get(Env.ADB_PATH);
        String cmd;
        if (Boolean.getBoolean("debug")) {
            cmd = Adb.Command.DEVICES_L.getCmd(); //로컬 테스트 목적
        } else {
            cmd = sdkPath + File.separator + Adb.Command.DEVICES_L.getCmd();
        }

        String[] callCmd = {"/bin/bash", "-c", cmd};
        pb.command(callCmd);
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!pattern1.matcher(line).find()) {//List of devices attached 걸러내기
                if (pattern2.matcher(line).find()) {//연결 및 액세스가 허용된 device가 있을 경우
                    line = line.replace("device", "").trim();

                    String model = findModel(line);
                    String id = findId(line);
                    String message = args.length >= 2 ? args[1] : "";
                    InputText inputText = new InputText(id, message);
                    String inputTextToJson = new Gson().toJson(inputText);
                    Item item = new Item(model, id, Base64.getEncoder().encodeToString(inputTextToJson.getBytes()));
                    items.add(item);
                }
            }
        }
        reader.close();
        process.waitFor();
        return RunnerResult.success(new Gson().toJson(new ScriptFilter(items)));
    }

    public String findId(String line) {
        line = line.trim();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                return line.substring(0, i);
            }
        }

        return null;
    }

    public String findModel(String line) {
        line = line.trim();
        String keyword = "model:";
        int start = line.indexOf(keyword);
        for (int i = start; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                return line.substring(start + keyword.length(), i);
            }
        }
        return null;
    }
}
