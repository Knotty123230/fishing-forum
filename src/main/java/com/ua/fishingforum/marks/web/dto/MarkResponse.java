package com.ua.fishingforum.marks.web.dto;

import com.ua.fishingforum.marks.model.Mark;

import java.util.Set;

public record MarkResponse(
        Set<Mark> marks
) {
}
