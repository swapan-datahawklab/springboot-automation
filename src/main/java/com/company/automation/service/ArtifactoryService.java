package com.company.automation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArtifactoryService {
    private final RestTemplate restTemplate;

    @Autowired
    public ArtifactoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRepoList(String repoName) {
        String url = "http://localhost:8081/artifactory/api/docker/" + repoName + "/v2/_catalog";
        return restTemplate.getForObject(url, String.class);
    }
}