package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Notification;
import com.baytree_mentoring.baytree_mentoring.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void addNotificationsFromAdmin(List<String> usernameList, String message) {
        assert(usernameList != null);
        assert(message != null);
        for (String username : usernameList) {
            Notification notification = new Notification(username, message);
            notificationRepository.save(notification);
        }
    }

    public List<Notification> queryNotificationsByUsername(String username) {
        return notificationRepository.findNotificationsByUsername(username);
    }

    public List<Notification> queryAllNotifications() {
        return notificationRepository.findAll();
    }

}
