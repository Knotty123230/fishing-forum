package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PostToPostResponseMapperImplTest {
    @InjectMocks
    PostToPostResponseMapperImpl postToPostResponseMapper;

    @Test
    void map_shouldSuccessMap(){
        Post post = new Post("name", "desc", Set.of(new Photo("image1.jpg")));
        PostResponse postResponse = postToPostResponseMapper.map(post);
        assertNull(postResponse.createdAt());
        assertNotNull(postResponse);
    }

}