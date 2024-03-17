package com.ua.fishingforum.user.profile.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.fishingforum.user.profile.usecase.EditUserProfileUseCase;
import com.ua.fishingforum.user.profile.usecase.UserProfileCreateUseCase;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(UserProfileController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserProfileControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserProfileCreateUseCase userProfileCreateUseCase;
    @MockBean
    EditUserProfileUseCase editUserProfileUseCase;

    @Test
    @WithMockUser
    void editUserProfile_shouldSuccessEditProfile() throws Exception {
        UserProfileRequest userProfileRequest = new UserProfileRequest("nickname1", "imageLink");
        var requestBuilder = MockMvcRequestBuilders.put("/api/v1/user-profiles/edit/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userProfileRequest))
                .with(csrf());
        ResultActions perform = mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser
    void createUserProfile_shouldSuccessCreateUserProfile() throws Exception {
        UserProfileRequest userProfileRequest = new UserProfileRequest("nickname", "imageLink");
        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/user-profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userProfileRequest))
                .with(csrf());
        ResultActions perform = mockMvc.perform(requestBuilder);
        perform.andExpect(status().isCreated());
    }
    @Test
    @WithMockUser
    void createUserProfile_shouldFailValidation() throws Exception {
        UserProfileRequest userProfileRequest = new UserProfileRequest("", "imageLink");
        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/user-profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userProfileRequest))
                .with(csrf());
        ResultActions perform = mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser
    void createUserProfile_shouldHandleValidationErrors() throws Exception {
        UserProfileRequest userProfileRequest = new UserProfileRequest("", "imageLink");
        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/user-profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userProfileRequest))
                .with(csrf());
        ResultActions perform = mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("nickname must not be blank"));
    }
}