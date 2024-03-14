package com.ua.fishingforum.user.posts.web;

import com.ua.fishingforum.user.posts.usecase.CreatePostUseCase;
import com.ua.fishingforum.user.posts.usecase.EditPostUseCase;
import com.ua.fishingforum.user.posts.usecase.FindAllPostsUseCase;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class PostController {
    private final CreatePostUseCase createPostUseCase;
    private final FindAllPostsUseCase findAllPostsUseCase;
    private final EditPostUseCase editPostUseCase;


    @PostMapping("/post/create")
    public PostResponse createPost(@RequestBody @Valid PostRequest postRequest) {
        return createPostUseCase.create(postRequest);
    }
    @GetMapping("/posts")
    public AllPostsResponse findAllPosts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return findAllPostsUseCase.find(page, limit);
    }
    @PutMapping("/post/edit/{postId}")
    public PostResponse editPost(@PathVariable Long postId, @RequestBody PostRequest postRequest){
        return editPostUseCase.editPost(postId, postRequest);
    }
}
