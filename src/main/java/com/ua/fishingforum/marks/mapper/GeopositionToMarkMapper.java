package com.ua.fishingforum.marks.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.web.dto.Geoposition;

import java.util.Set;

public interface GeopositionToMarkMapper extends Mapper<Set<Mark>, Geoposition> {
}
