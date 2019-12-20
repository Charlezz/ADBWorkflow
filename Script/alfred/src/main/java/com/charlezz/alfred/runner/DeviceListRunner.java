package com.charlezz.alfred.runner;

import java.io.IOException;

public class DeviceListRunner implements Runner {

    @Override
    public RunnerResult run(String... args) throws IOException, InterruptedException {
        Devices devices = new Devices();
        return RunnerResult.success(devices.toScriptFilterResultJson());
    }
}
