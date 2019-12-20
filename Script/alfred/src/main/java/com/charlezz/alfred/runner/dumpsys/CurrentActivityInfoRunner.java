package com.charlezz.alfred.runner.dumpsys;

import com.charlezz.alfred.adb.Adb;
import com.charlezz.alfred.adb.AdbHandler;
import com.charlezz.alfred.alfred.Item;
import com.charlezz.alfred.alfred.ScriptFilter;
import com.charlezz.alfred.runner.Runner;
import com.charlezz.alfred.runner.RunnerResult;
import com.charlezz.alfred.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class CurrentActivityInfoRunner implements Runner {

    private Logger logger = Logger.getLogger(CurrentActivityInfoRunner.class.getSimpleName());

    @Override
    public RunnerResult run(String... args) throws IOException, InterruptedException {
        ArrayList<Item> items = new ArrayList<>();
        String result = AdbHandler.start(Adb.Command.DEVICES_L.getCmd());
        StringReader stringReader = new StringReader(result);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        String line;
        while((line = bufferedReader.readLine())!=null){
            if(!line.contains("List of devices attached")){
                if (line.contains("device")) {//연결 및 액세스가 허용된 device가 있을 경우
                    String model = findModel(line);
                    String id = findId(line);
                    String currentFocus = AdbHandler.start(Adb.Command.M_CURRENT_ACTIVITIY_INFO.getCmd(id));
                    int slashIndex = currentFocus.indexOf("/");
                    if(slashIndex!=-1){
                        currentFocus = currentFocus.substring(slashIndex);
                        currentFocus = currentFocus.replace("/","");
                        currentFocus = currentFocus.replace("}","");
                    }
                    logger.i("currentFocus = "+currentFocus);
                    Item item = new Item(model, currentFocus, id);
                    items.add(item);
                }
            }
        }

        bufferedReader.close();
        return RunnerResult.success(new ScriptFilter(items).toJson());
    }
    public String findId(String line){
        line = line.trim();
        for(int i=0; i<line.length();i++){
            if(line.charAt(i)==' '){
                return line.substring(0, i);
            }
        }

        return null;
    }

    public String findModel(String line){
        line = line.trim();
        String keyword = "model:";
        int start = line.indexOf(keyword);
        for(int i=start; i<line.length();i++){
            if(line.charAt(i)==' '){
                return line.substring(start+keyword.length(), i);
            }
        }
        return null;
    }
}
