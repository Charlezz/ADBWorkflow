package com.charlezz.alfred.adb;

public class Adb {
    public enum Command{
        GET_DEBUG_LAYOUT("adb -s %s shell getprop debug.layout"),
        SET_DEBUG_LAYOUT("adb -s %1$s shell setprop debug.layout %2$s > /dev/null 2>&1"),
        UPDATE_DEBUG_LAYOUT("adb -s %1$s shell service call activity 1599295570 > /dev/null 2>&1"),
        M_CURRENT_ACTIVITIY_INFO("adb -s %s shell dumpsys window windows | grep -E 'mCurrentFocus'"),
        DEVICES_L("adb devices -l"),
        DEVICES("adb devices"),
        INPUT_TEXT("adb -s %s shell input text %s"),
        GET_DO_KEEP_ACTIVITIES("adb -s %s shell settings get global always_finish_activities"),
        DONT_KEEP_ACTIVITIES("adb -s %1$s shell settings put global always_finish_activities %2$s");

        private String cmd;

        Command(String cmd){
            this.cmd = cmd;
        }

        public String getCmd(Object... arg) {
            return String.format(cmd, arg);
        }
    }
}
