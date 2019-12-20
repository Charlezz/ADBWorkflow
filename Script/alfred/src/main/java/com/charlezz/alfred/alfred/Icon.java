package com.charlezz.alfred.alfred;

import com.google.gson.annotations.SerializedName;

public class Icon {
    public Type type;
    public String path;

    public Icon(Type type, String path){
        this.type = type;
        this.path = path;
    }
    public Icon(){
        this.type = Type.FILE_ICON;
        this.path = "icon.png";
    }

    enum Type{
        @SerializedName("fileicon")
        FILE_ICON,
        @SerializedName("filetype")
        FILE_TYPE
    }
}
