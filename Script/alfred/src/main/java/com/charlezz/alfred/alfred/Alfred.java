package com.charlezz.alfred.alfred;

import java.io.IOException;

import com.charlezz.alfred.runner.DeviceListRunner;
import com.charlezz.alfred.runner.Runner;
import com.charlezz.alfred.runner.RunnerResult;
import com.charlezz.alfred.runner.activities.ToggleKeepActivitiesRunner;
import com.charlezz.alfred.runner.dumpsys.CurrentActivityInfoRunner;
import com.charlezz.alfred.runner.input.InputTextRunner1;
import com.charlezz.alfred.runner.input.InputTextRunner2;
import com.charlezz.alfred.runner.layout.DebugLayoutRunner;
import com.charlezz.alfred.util.Logger;

public class Alfred {
    private Logger logger = Logger.getLogger(Alfred.class.getSimpleName());

    public enum Workflow {
        DEBUG_LAYOUT(DebugLayoutRunner.class),
        DEVICE_LIST(DeviceListRunner.class),
        CURRENT_ACTIVITY_INFO(CurrentActivityInfoRunner.class),
        INPUT_TEXT_STEP1(InputTextRunner1.class),
        INPUT_TEXT_STEP2(InputTextRunner2.class),
        TOGGLE_KEEP_ACTIVITIES(ToggleKeepActivitiesRunner.class)
        ;

        private final Class<? extends Runner> runnerClass;

        Workflow(Class<? extends Runner> runnerClass) {
            this.runnerClass = runnerClass;
        }

        public Class<? extends Runner> getRunnerClass() {
            return runnerClass;
        }

        public static Alfred.Workflow parse(String strType) {
            for (Workflow workflow : Workflow.values()) {
                if (workflow.name().equalsIgnoreCase(strType)) {
                    return workflow;
                }
            }
            return null;
        }
    }


    public void start(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println();
            logger.e("커맨드 타입인수가 없음");
            return;
        }
        Alfred.Workflow commandType = Alfred.Workflow.parse(args[0]);
        if (commandType == null) {
            logger.e("커맨드 타입 파싱 실패:" + args[0]);
            return;
        }

        try {
            Runner runner = commandType.getRunnerClass().newInstance();
            RunnerResult runnerResult = runner.run(args);
            if (runnerResult.isSuccess()) {
                System.out.println(runnerResult.getMessage());
            } else {
                logger.e(runnerResult.getMessage());
            }

        } catch (IllegalAccessException |
                InstantiationException |
                InterruptedException |
                IOException e) {
            e.printStackTrace();
        }
    }
}
