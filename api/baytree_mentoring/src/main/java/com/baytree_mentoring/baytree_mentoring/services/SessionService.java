package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.baytree_mentoring.baytree_mentoring.models.ViewsSessionGroup;
import com.baytree_mentoring.baytree_mentoring.models.ViewsVolunteeringRole;
import com.baytree_mentoring.baytree_mentoring.util.ViewsAPISessionIntegration;
import com.baytree_mentoring.baytree_mentoring.util.ViewsUnirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SessionService {
    private final ViewsAPISessionIntegration viewsAPISessionIntegration = new ViewsAPISessionIntegration();
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();

    public SessionService() {}

    public boolean sendCompletedSessionFormToViews(Session ses) {
        System.out.println("Sending session to Views database: " + ses.toString());
        try {
            viewsAPISessionIntegration.sendCompletedSessionFormToViews(ses);
            return true;
        } catch (UnirestException | ParseException e) {
            System.out.println("Inside sendCompletedSessionFormToViews: Failed to upload session to Views.");
            e.printStackTrace();
            return false;
        }
    }

    public List<ViewsSessionGroup> getSessionGroupsFromViews() {
        String URL = "https://app.viewsapp.net/api/restful/work/sessiongroups/search?q=";
        List<ViewsSessionGroup> sessionGroups;
        try {
            HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
            sessionGroups = parseSessionGroupsGetList(response);
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }

        return sessionGroups;
    }

    private List<ViewsSessionGroup> parseSessionGroupsGetList(HttpResponse<String> response) {
        JSONObject body = new JSONObject(response.getBody());
        String key = body.names().getString(0);
        JSONObject sessionGroups = body.getJSONObject(key);
        List<ViewsSessionGroup> retList = new ArrayList<>();
        Iterator<String> sessionGroupKeys = sessionGroups.keys();
        while (sessionGroupKeys.hasNext()) {
            key = sessionGroupKeys.next();
            Object sessionGroup = sessionGroups.get(key);
            if (sessionGroup instanceof JSONObject) {
                ViewsSessionGroup viewsSessionGroup = buildSessionGroup(key, sessionGroup);
                retList.add(viewsSessionGroup);
            }
        }
        return retList;
    }

    public ViewsSessionGroup buildSessionGroup(String key, Object sessionGroup) {
        int sessionGroupId = Integer.parseInt(parseIdFromKey(key));
        JSONObject sessionGroupJSON = new JSONObject(URLDecoder.decode(sessionGroup.toString(), StandardCharsets.UTF_8));
        String sessionGroupName = sessionGroupJSON.get("Title").toString();
        ViewsSessionGroup viewsSessionGroup = new ViewsSessionGroup(sessionGroupId, sessionGroupName);
        return viewsSessionGroup;
    }

    public String parseIdFromKey(String key) {
        String id = key.replaceAll("sessiongroup id=", "");
        id = id.replaceAll("\"", "");
        return id;
    }

    public List<ViewsVolunteeringRole> getVolunteeringRolesFromViews() throws UnirestException {
        String URL = "https://app.viewsapp.net/api/restful/admin/valuelists/sessiongroup/volunteeringtypes";
        List<ViewsVolunteeringRole> volunteeringRoles;
        try {
            HttpResponse<String> response = viewsUnirest.sendUnirestGetRequestGetStringResponse(URL);
            System.out.println("After unirest send");
            volunteeringRoles = parseResponseIntoVolunteeringRoles(response);
        } catch (UnirestException e) {
            e.printStackTrace();
            throw e;
        }
        return volunteeringRoles;
    }

    private List<ViewsVolunteeringRole> parseResponseIntoVolunteeringRoles(HttpResponse<String> response) {
        JSONObject body = new JSONObject(response.getBody());
        JSONObject roles = body.getJSONObject("items");
        List<ViewsVolunteeringRole> retList = new ArrayList<>();
        Iterator<String> iter = roles.keys();
        String roleKey;
        while(iter.hasNext()) {
            roleKey = iter.next();
            String id = parseVolunteerRoleNameFromKey(roleKey);
            ViewsVolunteeringRole viewsVolunteeringRole = new ViewsVolunteeringRole(id);
            retList.add(viewsVolunteeringRole);
        }
        return retList;
    }

    public String parseVolunteerRoleNameFromKey(String key) {
        String id = key.replaceAll("item id=", "");
        id = id.replaceAll("\"", "");
        return id;
    }
}


