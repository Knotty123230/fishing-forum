package com.ua.fishingforum.user.posts.usecase.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.image.service.PhotoService;
import com.ua.fishingforum.user.posts.mapper.PostToPostWithImageResponseMapper;
import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.posts.usecase.UploadPostImageUseCase;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadPostImageUseCaseFacade implements UploadPostImageUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final PostService postService;
    private final ImageStorageService imageStorageService;
    private final PostToPostWithImageResponseMapper postToPostWithImageResponseMapper;
    private final PhotoService photoService;

    @Override
    public PostResponse upload(MultipartFile multipartFile, Long postId) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Post post = postService.findPostByUserProfileAndId(userProfile, postId).orElseThrow(() -> new CustomException("пост з id %s не знайдено".formatted(postId)));
        Photo photo = photoService.uploadPhoto(multipartFile);
        post.getPhotos().add(photo);
        Post createdPost = postService.create(post);
        return postToPostWithImageResponseMapper.map(createdPost);
    }


}
