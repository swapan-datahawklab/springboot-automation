package com.company.automation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtifactoryController.class)
class ArtifactoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetArtifactory() throws Exception {
        mockMvc.perform(get("/artifactory"))
                .andExpect(status().isOk());
    }
}