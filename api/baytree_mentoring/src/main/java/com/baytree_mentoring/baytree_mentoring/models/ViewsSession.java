package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewsSession {
    private long mentorId;
    private String sessionGroup;
    private String attendance;
    private String dateTime;
    private String duration;
    private String note;
}
