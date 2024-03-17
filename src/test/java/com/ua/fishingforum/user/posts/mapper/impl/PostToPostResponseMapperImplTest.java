package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PostToPostResponseMapperImplTest {
    @InjectMocks
    PostToPostResponseMapperImpl postToPostResponseMapper;

    @Test
    void map_shouldSuccessMap(){
        Post post = new Post("name", "desc", "imageUrl");
        PostResponse postResponse = postToPostResponseMapper.map(post);
        assertNull(postResponse.createdAt());
        assertNotNull(postResponse);
    }

}