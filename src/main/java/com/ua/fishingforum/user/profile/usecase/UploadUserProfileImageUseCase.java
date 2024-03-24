package com.ua.fishingforum.user.profile.usecase;

import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadUserProfileImageUseCase {
    UserProfileResponse upload(MultipartFile multipartFile);
}
