package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Notification;
import com.baytree_mentoring.baytree_mentoring.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private static HttpURLConnection connection;
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void generateNotificationsService(ArrayList<String> usernameList, String message) {
        for (String username : usernameList) {
            Notification notification = new Notification(username, message);
            notificationRepository.save(notification);
        }
    }

    public List<Notification> getNotificationsForUserService(String username) {
        return notificationRepository.findNotificationsByUsername(username);
    }

    public List<Notification> getNotificationsForEveryoneService() {
        return notificationRepository.findAll();
    }
}
