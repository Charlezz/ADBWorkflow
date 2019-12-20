package com.charlezz.alfred.runner.input;

import com.charlezz.alfred.adb.Adb;
import com.charlezz.alfred.adb.AdbHandler;
import com.charlezz.alfred.runner.Runner;
import com.charlezz.alfred.runner.RunnerResult;
import com.google.gson.Gson;

import java.util.Base64;

public class InputTextRunner2 implements Runner {
    @Override
    public RunnerResult run(String... args) {
        if (args.length < 2) {
            return RunnerResult.error("InputTextRunner2 must have 2 args or more");
        }
        String arg = new String(Base64.getDecoder().decode(args[1]));
        InputText inputText = new Gson().fromJson(arg, InputText.class);
        String message = inputText.getMsg().replace(" ", "%s");
        try {
            AdbHandler.start(Adb.Command.INPUT_TEXT.getCmd(inputText.getId(), message));
        } catch (Exception e) {
            e.printStackTrace();
            return RunnerResult.error("Unknown Error");
        }

        return RunnerResult.success(inputText.getId() + "\n" + inputText.getMsg());
    }
}
