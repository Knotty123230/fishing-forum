package com.ua.fishingforum.marks.mapper.impl;

import com.ua.fishingforum.marks.mapper.CreatedMarkToMarkResponseMapper;
import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.web.dto.CreatedMarkResponse;
import org.springframework.stereotype.Component;

@Component
public class CreatedMarkToMarkResponseMapperImpl implements CreatedMarkToMarkResponseMapper {
    @Override
    public CreatedMarkResponse map(Mark source) {
        return new CreatedMarkResponse(source.getLat(), source.getLng());
    }
}
