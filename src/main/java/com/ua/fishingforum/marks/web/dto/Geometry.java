package com.ua.fishingforum.marks.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.fishingforum.marks.model.Mark;
import lombok.Data;

@Data
public class Geometry {
    @JsonProperty("location")
    private Mark mark;
}
