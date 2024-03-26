package com.ua.fishingforum.user.posts.web;

import com.ua.fishingforum.common.constants.MappingConstants;
import com.ua.fishingforum.user.posts.usecase.CreatePostUseCase;
import com.ua.fishingforum.user.posts.usecase.EditPostUseCase;
import com.ua.fishingforum.user.posts.usecase.FindAllPostsUseCase;
import com.ua.fishingforum.user.posts.usecase.UploadPostImageUseCase;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.ua.fishingforum.common.constants.MappingConstants.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class PostController {
    private final CreatePostUseCase createPostUseCase;
    private final FindAllPostsUseCase findAllPostsUseCase;
    private final EditPostUseCase editPostUseCase;
    private final UploadPostImageUseCase uploadPostImageUseCase;


    @PostMapping(CREATE_POST_MAPPING)
    public PostResponse createPost(@RequestBody @Valid PostRequest postRequest) {
        return createPostUseCase.create(postRequest);
    }

    @PutMapping(MappingConstants.UPLOAD_IMAGE)
    public PostResponse uploadImage(@RequestParam("file") MultipartFile multipartFile, @PathVariable Long postId) {
        return uploadPostImageUseCase.upload(multipartFile, postId);
    }


    @GetMapping(ALL_POSTS_FOR_CURRENT_USER_MAPPING)
    public AllPostsResponse findAllPosts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return findAllPostsUseCase.find(page, limit);
    }

    @PutMapping(EDIT_POST_MAPPING)
    public PostResponse editPost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        return editPostUseCase.editPost(postId, postRequest);
    }
}
