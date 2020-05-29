package com.charlezz.alfred;

import org.junit.Before;
import org.junit.Test;

import com.charlezz.alfred.alfred.Alfred;

public class AlfredTest {

    private Alfred alfred;
    private String deviceId = "22d0ad507d0d7ece";//디바이스 아이디 S9

    @Before
    public void setUpAlfred() {
        alfred = new Alfred();
    }

    @Test
    public void toggleKeepActivitiesTest(){
        String[] args = new String[]{Alfred.Workflow.TOGGLE_KEEP_ACTIVITIES.name(), deviceId};
        alfred.start(args);
    }

    @Test
    public void test(){
        System.out.println(String.format("%1$s %1$s","Test"));
    }
}