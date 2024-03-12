package com.ua.fishingforum.marks.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class Geoposition {
    private List<Result> results;
}
