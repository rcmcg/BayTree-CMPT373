package com.baytree_mentoring.baytree_mentoring.wrappers;

import java.util.List;

public class NotificationWrapper {
    private final List<String> usernameList;
    private final String message;

    public NotificationWrapper(List<String> usernameList, String message) {
        this.usernameList = usernameList;
        this.message = message;
    }

    public List<String> getUsernameList() {
        return usernameList;
    }

    public String getMessage() {
        return message;
    }
}
