package com.ua.fishingforum.user.comments.web;

import com.ua.fishingforum.user.comments.usecase.AllCommentsUseCase;
import com.ua.fishingforum.user.comments.usecase.CreateCommentUseCase;
import com.ua.fishingforum.user.comments.usecase.EditCommentUseCase;
import com.ua.fishingforum.user.comments.web.dto.AllCommentsResponse;
import com.ua.fishingforum.user.comments.web.dto.CommentRequest;
import com.ua.fishingforum.user.comments.web.dto.CommentResponse;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/comment")
public class CommentController {
    private final CreateCommentUseCase createCommentUseCase;
    private final EditCommentUseCase editCommentUseCase;
    private final AllCommentsUseCase allCommentsUseCase;
    private final AllCommentsUseCase allCommentsByLikesUseCase;

    public CommentController(CreateCommentUseCase createCommentUseCase, EditCommentUseCase editCommentUseCase, @Qualifier("allCommentsUseCaseFacade") AllCommentsUseCase allCommentsUseCase, @Qualifier("byLikes") AllCommentsUseCase allCommentsByLikesUseCase) {
        this.createCommentUseCase = createCommentUseCase;
        this.editCommentUseCase = editCommentUseCase;
        this.allCommentsUseCase = allCommentsUseCase;
        this.allCommentsByLikesUseCase = allCommentsByLikesUseCase;
    }


    @PostMapping
    public CommentResponse createComment(@Valid @RequestBody CommentRequest commentRequest) {
        return createCommentUseCase.create(commentRequest);
    }

    @PutMapping("/edit/{commentId}")
    public CommentResponse editComment(@PathVariable Long commentId, @Valid @RequestParam("comment") @Length(min = 3, max = 150) String message) {
        return editCommentUseCase.editComment(commentId, message);
    }

    @GetMapping("/{postId}/filter/date")
    public AllCommentsResponse getAllComments(@PathVariable Long postId) {
        return allCommentsUseCase.findAllComments(postId);
    }

    @GetMapping("/{postId}/filter/likes")
    public AllCommentsResponse getAllCommentsByLikes(@PathVariable Long postId) {
        return allCommentsByLikesUseCase.findAllComments(postId);
    }
}
