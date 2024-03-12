package com.ua.fishingforum.marks.mapper.impl;

import com.ua.fishingforum.marks.mapper.GeopositionToMarkMapper;
import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.web.dto.Geoposition;
import com.ua.fishingforum.marks.web.dto.Result;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GeopositionToMarkMapperImpl implements GeopositionToMarkMapper {
    @Override
    public Set<Mark> map(Geoposition source) {
        Set<Result> result = new HashSet<>(source.getResults());
        return result.stream().map(result1 -> result1.getGeometry().getMark()).collect(Collectors.toSet());

    }
}
