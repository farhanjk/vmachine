package com.vending.machine.app.common.mvp.viewmodel;


public class KeyValuePair<KEY> {

    private KEY key;

    private String value;

    public KeyValuePair(KEY key, String value) {
        this.key = key;
        this.value = value;
    }

    public KEY getKey() {
        return key;
    }

    public void setKey(KEY key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
