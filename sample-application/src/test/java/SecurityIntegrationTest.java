//package com.fcmb.gabriel;

import com.fcmb.gabriel.sampleapplication.sampleapplication.SampleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = SampleApplication.class)
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publicEndpoint_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/public/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    void userEndpoint_WithoutAuth_ShouldReturn401() throws Exception {
        mockMvc.perform(get("/api/user/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "gabriel", roles = {"USER"})
    void userEndpoint_WithUserRole_ShouldReturn200() throws Exception {
        mockMvc.perform(get("/api/user/me"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "gabriel", roles = {"USER"})
    void adminEndpoint_WithUserRole_ShouldReturn403() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "gabriel-admin", roles = {"ADMIN"})
    void adminEndpoint_WithAdminRole_ShouldReturn200() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk());
    }
}
