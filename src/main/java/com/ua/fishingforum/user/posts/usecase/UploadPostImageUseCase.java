package com.ua.fishingforum.user.posts.usecase;

import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadPostImageUseCase {

    PostResponse upload(MultipartFile multipartFile, Long postId);
}
