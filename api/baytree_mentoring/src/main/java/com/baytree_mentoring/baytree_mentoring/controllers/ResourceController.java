package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedUserAddingException;
import com.baytree_mentoring.baytree_mentoring.models.Authentication;
import com.baytree_mentoring.baytree_mentoring.models.Resource;
import com.baytree_mentoring.baytree_mentoring.services.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {

    private static final String SUCCESS = "Resource Added";
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/resource/add")
    @CrossOrigin(origins = "http://localhost:3000")
    private String AddResource(@RequestBody Resource res){
        Resource resource = new Resource(res.getResourceId(), res.getResourceName(), res.getResourceLink());

        resourceService.AddResourceSevice(resource);

        List<Resource> resources = resourceService.getAllResources();

        for(Resource s : resources) {
            if(s.getResourceId() == resource.getResourceId()) {
                return SUCCESS;
            }
        }

        String error = "Failed to add the Resource.";
        throw new FailedResourceAddingException(error);
    }
}
