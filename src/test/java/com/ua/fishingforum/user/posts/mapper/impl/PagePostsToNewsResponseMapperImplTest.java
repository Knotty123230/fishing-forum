package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.model.Photo;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.NewsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PagePostsToNewsResponseMapperImplTest {
    @InjectMocks
    PagePostsToNewsResponseMapperImpl pagePostsToNewsResponseMapper;

    @Test
    void map_shouldSuccessMap(){
        List<Post> posts = List.of(
                new Post("Post 1", "Description 1", List.of(new Photo("image1.jpg"))),
                new Post("Post 2", "Description 2", List.of(new Photo("image2.jpg")))
        );
        Page<Post> page = new PageImpl<>(posts);
        NewsResponse newsResponse = pagePostsToNewsResponseMapper.map(page);
        assertTrue(newsResponse.posts().size() > 1);
        assertEquals(posts.get(0), newsResponse.posts().toArray()[0]);
    }
    @Test
    void map_ShouldReturnEmptyList_WhenPageIsEmpty(){
        Page<Post> empty = Page.empty();
        NewsResponse newsResponse = pagePostsToNewsResponseMapper.map(empty);
        assertTrue(newsResponse.posts().isEmpty());
    }

}