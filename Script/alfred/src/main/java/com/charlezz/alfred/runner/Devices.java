package com.charlezz.alfred.runner;

import com.charlezz.alfred.adb.Adb;
import com.charlezz.alfred.adb.AdbHandler;
import com.charlezz.alfred.alfred.Item;
import com.charlezz.alfred.alfred.ScriptFilter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Devices {

    private ArrayList<Item> items = new ArrayList<>();

    public Devices() {
        try {
            String str = AdbHandler.start(Adb.Command.DEVICES_L.getCmd());
            StringReader stringReader = new StringReader(str);
            BufferedReader bufferedReader = new BufferedReader(stringReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains("List of devices attached")) {//List of devices attached 걸러내기
                    if (line.contains("device")) {//연결 및 액세스가 허용된 device가 있을 경우
                        line = line.replace("device", "").trim();
                        String model = findModel(line);
                        String id = findId(line);
                        Item item = new Item(model, id, id);
                        items.add(item);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItems(){
        return items;
    }

    private String findId(String line) {
        line = line.trim();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                return line.substring(0, i);
            }
        }

        return null;
    }

    private String findModel(String line) {
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

    public String toScriptFilterResultJson() {
        return new Gson().toJson(new ScriptFilter(items));
    }
}
