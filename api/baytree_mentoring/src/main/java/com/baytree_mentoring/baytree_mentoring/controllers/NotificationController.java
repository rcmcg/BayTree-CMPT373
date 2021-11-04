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
    private void generateNotificationsController(@RequestBody NotificationWrapper notificationWrapper) {
        notificationService.generateNotificationsService(notificationWrapper.getUsernameList(), notificationWrapper.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/notifications/get/{username}")
    private List<Notification> getNotificationsForUserController(@PathVariable String username) {
        return notificationService.getNotificationsForUserService(username);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/notifications/get/all")
    private List<Notification> getNotificationsForEveryoneController() {
        return notificationService.getNotificationsForEveryoneService();
    }

}