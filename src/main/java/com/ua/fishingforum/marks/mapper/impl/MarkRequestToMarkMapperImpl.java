package com.ua.fishingforum.marks.mapper.impl;

import com.ua.fishingforum.marks.mapper.MarkRequestToMarkMapper;
import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.web.dto.MarkRequest;
import org.springframework.stereotype.Component;

@Component
public class MarkRequestToMarkMapperImpl implements MarkRequestToMarkMapper {
    @Override
    public Mark map(MarkRequest source) {
        Mark mark = new Mark();
        mark.setLat(source.lat());
        mark.setLng(source.lng());
        return mark;
    }
}
