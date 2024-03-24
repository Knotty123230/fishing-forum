package com.ua.fishingforum.user.posts.usecase.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.mapper.PostToPostWithImageResponseMapper;
import com.ua.fishingforum.user.posts.model.Photo;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.posts.usecase.UploadPostImageUseCase;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadPostImageUseCaseFacade implements UploadPostImageUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final PostService postService;
    private final ImageStorageService imageStorageService;
    private final PostToPostWithImageResponseMapper postToPostWithImageResponseMapper;

    @Override
    public PostResponse upload(MultipartFile multipartFile, Long postId) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Post post = postService.findPostByUserProfileAndId(userProfile, postId).orElseThrow(() -> new CustomException("пост з id %s не знайдено".formatted(postId)));
        String imageUrl = imageStorageService.uploadImage(multipartFile);
        Photo photo = new Photo();
        photo.setImageUrl(imageUrl);
        post.getPhotos().add(photo);
        Post createdPost = postService.create(post);
        return postToPostWithImageResponseMapper.map(createdPost);
    }
}
