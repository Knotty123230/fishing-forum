package com.ua.fishingforum.user.likes.web;

import com.ua.fishingforum.user.likes.usecase.*;
import com.ua.fishingforum.user.likes.web.dto.AllLikesResponse;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/like")
@RequiredArgsConstructor
public class LikesController {
    private final AddLikeUseCase addLikeUseCase;
    private final AllLikesUseCase allLikesUseCase;
    private final AddCommentLikeUseCase addCommentLikeUseCase;
    private final AllCommentLikesUseCase allCommentLikesUseCase;
    private final DeletePostLikeUseCase deletePostLikeUseCase;
    private final DeleteCommentLikeUseCase deleteCommentLikeUseCase;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponse addPostLike(@RequestParam("postId") Long postId) {
        return addLikeUseCase.addLike(postId);
    }


    @GetMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public AllLikesResponse findAllPostLikes(@RequestParam("postId") Long postId) {
        return allLikesUseCase.findAll(postId);
    }

    @PostMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponse addCommentLike(@PathVariable Long id) {
        return this.addCommentLikeUseCase.addCommentLike(id);
    }

    @GetMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public AllLikesResponse findAllCommentLikes(@RequestParam("commentId") Long commentId) {
        return this.allCommentLikesUseCase.findAllCommentsLikes(commentId);
    }

    @DeleteMapping("/post")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePostLike(@RequestParam("postId") Long postId) {
        deletePostLikeUseCase.delete(postId);
    }

    @DeleteMapping("/comment")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCommentLike(@RequestParam("commentId") Long commentId) {
        this.deleteCommentLikeUseCase.deleteCommentLike(commentId);
    }
}
