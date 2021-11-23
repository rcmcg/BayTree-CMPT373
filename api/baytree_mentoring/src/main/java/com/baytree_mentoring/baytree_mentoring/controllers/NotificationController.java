package com.baytree_mentoring.baytree_mentoring.controllers;


import com.baytree_mentoring.baytree_mentoring.models.Notification;
import com.baytree_mentoring.baytree_mentoring.services.NotificationService;
import com.baytree_mentoring.baytree_mentoring.wrappers.NotificationWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/notifications/send")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    private void addNotifications(@RequestBody NotificationWrapper notificationWrapper) {
        notificationService.addNotificationsFromAdmin(notificationWrapper.getUsernameList(), notificationWrapper.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @GetMapping("/notifications/get/{username}")
    private List<Notification> getNotificationsByUsername(@PathVariable String username) {
        return notificationService.queryNotificationsByUsername(username);
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @GetMapping("/notifications/get/all")
    private List<Notification> getAllNotifications() {
        return notificationService.queryAllNotifications();
    }



}