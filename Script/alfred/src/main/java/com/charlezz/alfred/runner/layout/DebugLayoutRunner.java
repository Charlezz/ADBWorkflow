package com.charlezz.alfred.runner.layout;

import com.charlezz.alfred.adb.Adb;
import com.charlezz.alfred.adb.AdbHandler;
import com.charlezz.alfred.runner.Runner;
import com.charlezz.alfred.runner.RunnerResult;
import com.charlezz.alfred.util.Logger;

public class DebugLayoutRunner implements Runner {
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public RunnerResult run(String... args) {
        if (args.length < 2) {
            return RunnerResult.error("No Device Id");
        }
        String deviceId = args[1];
        String debugLayoutMode = AdbHandler.start(Adb.Command.GET_DEBUG_LAYOUT.getCmd(deviceId));
        logger.i("debugLayoutMode1=" + debugLayoutMode);
        if (debugLayoutMode.contains("true")) {
            debugLayoutMode = "false";
        } else {
            debugLayoutMode = "true";
        }
        logger.i("debugLayoutMode2=" + debugLayoutMode);
        AdbHandler.start(Adb.Command.SET_DEBUG_LAYOUT.getCmd(deviceId, debugLayoutMode));
        String result = AdbHandler.start(Adb.Command.UPDATE_DEBUG_LAYOUT.getCmd(deviceId, debugLayoutMode));
        return RunnerResult.success(result);
    }
}
