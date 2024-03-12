package com.ua.fishingforum.marks.mapper.impl;

import com.ua.fishingforum.marks.mapper.MarkToMarkResponseMapper;
import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.web.dto.MarkResponse;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MarkToMarkResponseMapperImpl implements MarkToMarkResponseMapper {


    @Override
    public MarkResponse map(Set<Mark> source) {
        return new MarkResponse(source);
    }
}
