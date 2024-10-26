package com.company.automation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ArtifactoryServiceTest {

    @MockBean
    private RestClient restClient;

    @Autowired
    private ArtifactoryService artifactoryService;

    @Test
    public void testGetRepoList() {
        String repoName = "test-repo";
        String url = "http://localhost:8081/artifactory/api/docker/" + repoName + "/v2/_catalog";
        String repoList = "repo1, repo2";
        when(restClient.get().uri(url).retrieve().body(String.class)).thenReturn(repoList);

        String result = artifactoryService.getRepoList(repoName);
        assertEquals(repoList, result);
    }
}