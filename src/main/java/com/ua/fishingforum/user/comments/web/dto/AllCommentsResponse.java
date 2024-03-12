package com.ua.fishingforum.user.comments.web.dto;

import java.util.List;

public record AllCommentsResponse(List<CommentResponse> comments) {
}
