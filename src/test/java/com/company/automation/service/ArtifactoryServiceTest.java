package com.company.automation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ArtifactoryServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ArtifactoryService artifactoryService;

    @Test
    void testGetRepoList() {
        String repoName = "example-repo";
        String expectedResponse = "{\"repositories\":[\"repo1\",\"repo2\"]}";
        String url = buildRepoListUrl(repoName);

        when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

        String actualResponse = artifactoryService.getRepoList(repoName);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetTagList() {
        String repoName = "example-repo";
        String tagName = "example-tag";
        String expectedResponse = "{\"tags\":[\"tag1\",\"tag2\"]}";
        String url = buildTagListUrl(repoName, tagName);

        when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

        String actualResponse = artifactoryService.getTagList(repoName, tagName);

        assertEquals(expectedResponse, actualResponse);
    }

    private String buildRepoListUrl(String repoName) {
        return "http://localhost:8081/artifactory/api/docker/" + repoName + "/v2/_catalog";
    }

    private String buildTagListUrl(String repoName, String tagName) {
        return "http://localhost:8081/artifactory/api/docker/" + repoName + String.format("/v2/%s/tags/list", tagName);
    }
}