package com.company.automation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ArtifactoryService {
    private final RestClient restClient;

    @Autowired
    public ArtifactoryService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public String getRepoList(String repoName) {
        String url = "http://localhost:8081/artifactory/api/docker/" + repoName + "/v2/_catalog";
        return restClient.get().uri(url).retrieve().body(String.class);
    }

    public String getTagList(String repoName, String tagName) {
        String url = "http://localhost:8081/artifactory/api/docker/" + repoName + "/v2/" + tagName + "/tags/list";
        return restClient.get().uri(url).retrieve().body(String.class);
    }
}