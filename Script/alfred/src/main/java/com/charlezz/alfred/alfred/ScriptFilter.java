package com.charlezz.alfred.alfred;

import com.google.gson.Gson;

import java.util.List;

public class ScriptFilter {
    private List<Item> items;

    public ScriptFilter(List<Item> items) {
        this.items = items;
    }
    public String toJson(){
        return new Gson().toJson(this);
    }
}
