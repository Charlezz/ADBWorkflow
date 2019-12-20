package com.charlezz.alfred;

import com.charlezz.alfred.alfred.Alfred;

import org.junit.Before;
import org.junit.Test;

public class AlfredTest {

    private Alfred alfred;

    @Before
    public void setUpAlfred() {
        alfred = new Alfred();
    }

    @Test
    public void firstTest() {
//        String[] args = new String[]{Alfred.Workflow.CURRENT_ACTIVITY_INFO.name()};
//        String[] args = new String[]{Alfred.Workflow.INPUT_TEXT_STEP1.name(), "안녕, 찰스"};
        String[] args = new String[]{Alfred.Workflow.INPUT_TEXT_STEP2.name(), "eyJpZCI6IjIyZDBhZDUwN2QwZDdlY2UiLCJtc2ciOiLslYjrhZUsIOywsOyKpCJ9"};
        alfred.start(args);
    }

    @Test
    public void test(){
        System.out.println(String.format("%1$s %1$s","Test"));
    }
}