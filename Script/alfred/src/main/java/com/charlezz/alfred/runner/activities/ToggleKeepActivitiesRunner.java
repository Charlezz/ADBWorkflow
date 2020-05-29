package com.charlezz.alfred.runner.activities;

import java.io.IOException;

import com.charlezz.alfred.adb.Adb;
import com.charlezz.alfred.adb.AdbHandler;
import com.charlezz.alfred.runner.Runner;
import com.charlezz.alfred.runner.RunnerResult;
import com.charlezz.alfred.util.Logger;

public class ToggleKeepActivitiesRunner implements Runner {
    Logger logger = Logger.getLogger(ToggleKeepActivitiesRunner.class.getSimpleName());
    @Override
    public RunnerResult run(String... args) throws IOException, InterruptedException {
        if(args.length< 2){
            return RunnerResult.error("No Device Id");
        }
        String deviceId = args[1];
        logger.i("deviceId = "+deviceId);


        String enabled = AdbHandler.start(Adb.Command.GET_DO_KEEP_ACTIVITIES.getCmd(deviceId)).trim();
        String result = AdbHandler.start(Adb.Command.DONT_KEEP_ACTIVITIES.getCmd(deviceId,enabled.equals("0")? "1":"0"));
        return RunnerResult.success(result);
    }
}
