package com.ua.fishingforum.user.likes.web;

import com.ua.fishingforum.user.likes.usecase.*;
import com.ua.fishingforum.user.likes.web.dto.AllLikesResponse;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.ua.fishingforum.common.constants.MappingConstants.*;

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

    @PostMapping(ADD_LIKE_TO_POST_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponse addPostLike(@PathVariable Long postId) {
        return addLikeUseCase.addLike(postId);
    }


    @GetMapping(FIND_ALL_POST_LIKES_MAPPING)
    @ResponseStatus(HttpStatus.OK)
    public AllLikesResponse findAllPostLikes(@PathVariable Long postId) {
        return allLikesUseCase.findAll(postId);
    }

    @PostMapping(ADD_LIKE_TO_COMMENT_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponse addCommentLike(@PathVariable Long id) {
        return this.addCommentLikeUseCase.addCommentLike(id);
    }

    @GetMapping(FIND_ALL_COMMENT_LIKES_MAPPING)
    @ResponseStatus(HttpStatus.OK)
    public AllLikesResponse findAllCommentLikes(@PathVariable Long commentId) {
        return this.allCommentLikesUseCase.findAllCommentsLikes(commentId);
    }

    @DeleteMapping(DELETE_POST_LIKE_MAPPING)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePostLike(@PathVariable("postId") Long postId) {
        deletePostLikeUseCase.delete(postId);
    }

    @DeleteMapping(DELETE_COMMENT_LIKE_MAPPING)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCommentLike(@PathVariable Long commentId) {
        this.deleteCommentLikeUseCase.deleteCommentLike(commentId);
    }
}
