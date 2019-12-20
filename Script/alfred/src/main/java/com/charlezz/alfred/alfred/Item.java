package com.charlezz.alfred.alfred;


public class Item {
    private String uid;
    private String title;
    private String subtitle;
    private String arg;
    private Icon icon;
    private boolean valid = true;
    private String match;
    private String autocomplete;
    private Type type = Type.DEFAULT;
    private Object mods;
    private Object text;
    private String quicklookurl;

    public Item(String title, String subtitle,String arg){
        this.title = title;
        this.subtitle = subtitle;
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getAutocomplete() {
        return autocomplete;
    }

    public void setAutocomplete(String autocomplete) {
        this.autocomplete = autocomplete;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getMods() {
        return mods;
    }

    public void setMods(Object mods) {
        this.mods = mods;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public String getQuicklookurl() {
        return quicklookurl;
    }

    public void setQuicklookurl(String quicklookurl) {
        this.quicklookurl = quicklookurl;
    }
}
