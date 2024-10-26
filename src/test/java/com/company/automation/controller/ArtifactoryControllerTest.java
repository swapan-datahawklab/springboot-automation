package com.company.automation.controller;

import com.company.automation.service.ArtifactoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtifactoryController.class)
public class ArtifactoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtifactoryService artifactoryService;

    @Test
    public void testGetRepositories() throws Exception {
        String repoName = "test-repo";
        String repoList = "repo1, repo2";
        when(artifactoryService.getRepoList(repoName)).thenReturn(repoList);

        mockMvc.perform(get("/api/v1/artifactory/repos")
                .param("repoName", repoName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(repoList));
    }
}