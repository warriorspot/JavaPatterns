package com.brennan.notificationcenter;

public class Notification {

    private String name;
    private Object data;

    Notification(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public Object getData() {
		return this.data;
    }

    public String getName() {
        return this.name;
    }
}
