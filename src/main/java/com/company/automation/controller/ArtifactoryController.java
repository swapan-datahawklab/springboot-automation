package com.company.automation.controller;

import com.company.automation.service.ArtifactoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<String> getRepositories(@Valid @RequestParam String repoName) {
        String repoList = artifactoryService.getRepoList(repoName);
        return ResponseEntity.ok(repoList);
    }
}