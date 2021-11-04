package com.baytree_mentoring.baytree_mentoring.wrappers;

import java.util.ArrayList;

public class NotificationWrapper {
    private final ArrayList<String> usernameList;
    private final String message;

    public NotificationWrapper(ArrayList<String> usernameList, String message) {
        this.usernameList = usernameList;
        this.message = message;
    }

    public ArrayList<String> getUsernameList() {
        return usernameList;
    }

    public String getMessage() {
        return message;
    }
}
