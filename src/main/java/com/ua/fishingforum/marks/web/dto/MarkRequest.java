package com.ua.fishingforum.marks.web.dto;


import jakarta.validation.constraints.NotNull;

public record MarkRequest(
        @NotNull double lat,
        @NotNull double lng
) {
}
