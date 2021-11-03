package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Notification;
import com.baytree_mentoring.baytree_mentoring.repositories.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private static HttpURLConnection connection;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void generateNotifications(List<String> usernameList, String message) {
        for (String username : usernameList) {
            Notification notification = new Notification(username, message);
            notificationRepository.save(notification);
        }
    }

    public List<Notification> getAllNotifications() {

    }
}
