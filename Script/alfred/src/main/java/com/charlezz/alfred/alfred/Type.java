package com.charlezz.alfred.alfred;

import com.google.gson.annotations.SerializedName;

public enum Type {
    @SerializedName("default")
    DEFAULT,
    @SerializedName("file")
    FILE,
    @SerializedName("file:skipcheck")
    FILE_SKIP_CHECK;
}
