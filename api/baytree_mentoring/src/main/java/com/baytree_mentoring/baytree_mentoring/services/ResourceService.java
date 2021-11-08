package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Mentee;
import com.baytree_mentoring.baytree_mentoring.models.Resource;
import com.baytree_mentoring.baytree_mentoring.repositories.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public void AddResourceService(Resource res) {
        resourceRepository.save(res);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public void deleteResourceUsingId(Long resId) {
        resourceRepository.deleteAllById(Collections.singleton(resId));
    }

    public void add(Resource res) {
        resourceRepository.save(res);
    }

    public boolean isResourceAdded(Resource res) {
        return resourceRepository.existsById(res.getResourceId());
    }
}
