package com.ua.fishingforum.user.profile.web;

import com.ua.fishingforum.common.constants.MappingConstants;
import com.ua.fishingforum.user.profile.usecase.EditUserProfileUseCase;
import com.ua.fishingforum.user.profile.usecase.UploadUserProfileImageUseCase;
import com.ua.fishingforum.user.profile.usecase.UserProfileCreateUseCase;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.ua.fishingforum.common.constants.MappingConstants.EDIT_USER_PROFILE_MAPPING;


@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileCreateUseCase userProfileCreateUseCase;
    private final EditUserProfileUseCase editUserProfileUseCase;
    private final UploadUserProfileImageUseCase uploadUserProfileImageUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")}, summary = "create user profile controller", tags = {"user profile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "401", description = "User must be authorized", content = @Content)
    })
    public void createUserProfile(@RequestBody @Valid UserProfileRequest userProfileRequest) {
        userProfileCreateUseCase.createUserProfile(userProfileRequest);
    }

    @PutMapping(EDIT_USER_PROFILE_MAPPING)
    public UserProfileResponse editUserProfile(@PathVariable String nickname, @Valid @RequestBody UserProfileRequest userProfileRequest) {
        return editUserProfileUseCase.editUserProfile(nickname, userProfileRequest);
    }

    @PutMapping(MappingConstants.UPLOAD_USER_PROFILE_IMAGE)
    public UserProfileResponse uploadUserProfileImage(@RequestParam("file") MultipartFile multipartFile) {
        return uploadUserProfileImageUseCase.upload(multipartFile);
    }
}