package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PagePostsToAllPostsResponseMapperImplTest {
    @InjectMocks
    PagePostsToAllPostsResponseMapperImpl pagePostsToAllPostsResponseMapper;
    @Mock
    ImageStorageService imageStorageService;

    @Test
    void map_ShouldMapPageOfPostsToAllPostsResponse() {
        String url = "url";
        Mockito.when(imageStorageService.getImage(url)).thenReturn(url);
        List<Post> posts = List.of(
                new Post("Post 1", "Description 1", Set.of(new Photo(url))),
                new Post("Post 2", "Description 2", Set.of(new Photo(url))));
        Page<Post> page = new PageImpl<>(posts);

        AllPostsResponse allPostsResponse = pagePostsToAllPostsResponseMapper.map(page);

        assertEquals(posts.size(), allPostsResponse.postResponse().size());
        assertEquals(posts.get(0).getName(), allPostsResponse.postResponse().get(0).name());
        assertEquals(posts.get(0).getDescription(), allPostsResponse.postResponse().get(0).description());
        assertEquals(posts.get(0).getPhotos(), allPostsResponse.postResponse().get(0).images().get(0).photoUrl());
        assertEquals(posts.get(0).getCreatedTimestamp(), allPostsResponse.postResponse().get(0).createdAt());
        assertEquals(posts.get(1).getName(), allPostsResponse.postResponse().get(1).name());
        assertEquals(posts.get(1).getDescription(), allPostsResponse.postResponse().get(1).description());
        assertEquals(posts.get(1).getPhotos(), allPostsResponse.postResponse().get(1).images().get(0).photoUrl());
        assertEquals(posts.get(1).getCreatedTimestamp(), allPostsResponse.postResponse().get(1).createdAt());
    }

    @Test
    void map_ShouldReturnEmptyList_WhenPageIsEmpty() {
        Page<Post> emptyPage = Page.empty();
        AllPostsResponse allPostsResponse = pagePostsToAllPostsResponseMapper.map(emptyPage);
        assertEquals(0, allPostsResponse.postResponse().size());
    }

}