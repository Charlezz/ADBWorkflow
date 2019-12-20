package com.charlezz.alfred.runner;

import java.io.IOException;

public interface Runner  {
    RunnerResult run(String... args) throws IOException, InterruptedException;
}
