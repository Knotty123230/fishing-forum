package com.ua.fishingforum.marks.usecase;

import com.ua.fishingforum.marks.web.dto.CreatedMarkResponse;
import com.ua.fishingforum.marks.web.dto.MarkRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CreateMarkUseCase {

    CreatedMarkResponse create(@Valid MarkRequest markRequest);
}
