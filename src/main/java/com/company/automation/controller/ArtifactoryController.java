package com.company.automation.controller;

import com.company.automation.dto.RepoRequest;
import com.company.automation.service.ArtifactoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artifactory")
public class ArtifactoryController {
    private final ArtifactoryService artifactoryService;

    @Autowired
    public ArtifactoryController(ArtifactoryService artifactoryService) {
        this.artifactoryService = artifactoryService;
    }

    @GetMapping("/repos")
    public ResponseEntity<String> getRepositories(@Valid @RequestBody RepoRequest repoRequest) {
        String repoList = artifactoryService.getRepoList(repoRequest.getRepoName());
        return ResponseEntity.ok(repoList);
    }
}