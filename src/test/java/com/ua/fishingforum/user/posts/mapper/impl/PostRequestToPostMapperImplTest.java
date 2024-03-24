package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostRequestToPostMapperImplTest {
    @InjectMocks
    PostRequestToPostMapperImpl postRequestToPostMapper;
    @Mock
    CurrentUserProfileApiService currentUserProfileApiService;

    @Test
    void map_shouldSuccessMap(){
        UserProfile userProfile = new UserProfile();
        when(currentUserProfileApiService.currentUserProfile()).thenReturn(userProfile);
        PostRequest postRequest = new PostRequest("name", "desc");
        Post map = postRequestToPostMapper.map(postRequest);
        assertNotNull(map);
        assertEquals(map.getName(), postRequest.name());
        verify(currentUserProfileApiService, times(1)).currentUserProfile();
    }

}