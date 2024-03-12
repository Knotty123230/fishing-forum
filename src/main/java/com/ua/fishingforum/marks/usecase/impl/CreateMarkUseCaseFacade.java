package com.ua.fishingforum.marks.usecase.impl;

import com.ua.fishingforum.marks.mapper.CreatedMarkToMarkResponseMapper;
import com.ua.fishingforum.marks.mapper.MarkRequestToMarkMapper;
import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.service.MarkService;
import com.ua.fishingforum.marks.usecase.CreateMarkUseCase;
import com.ua.fishingforum.marks.web.dto.CreatedMarkResponse;
import com.ua.fishingforum.marks.web.dto.MarkRequest;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateMarkUseCaseFacade implements CreateMarkUseCase {
    private final MarkRequestToMarkMapper markRequestToMarkMapper;
    private final MarkService markService;
    private final CreatedMarkToMarkResponseMapper markToMarkResponseMapper;
    private final CurrentUserProfileApiService currentUserProfileApiService;

    @Override
    public CreatedMarkResponse create(MarkRequest markRequest) {
        boolean exists = markService.existsByLatAndLng(markRequest.lat(), markRequest.lng());
        if (exists) {
            throw new RuntimeException("Mark already exists");
        }
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Mark mark = this.markRequestToMarkMapper.map(markRequest);
        mark.setUserProfile(userProfile);
        Mark createdMark = markService.createMark(mark);
        return this.markToMarkResponseMapper.map(createdMark);
    }
}
