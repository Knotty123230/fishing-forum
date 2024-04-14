package com.ua.fishingforum.marks.usecase.impl;

import com.ua.fishingforum.marks.mapper.GeopositionToMarkMapper;
import com.ua.fishingforum.marks.mapper.MarkToMarkResponseMapper;
import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.usecase.FindMarkUseCase;
import com.ua.fishingforum.marks.util.GoogleApiUtil;
import com.ua.fishingforum.marks.util.UrlUtils;
import com.ua.fishingforum.marks.web.dto.Geoposition;
import com.ua.fishingforum.marks.web.dto.MarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class FindMarkUseCaseFacade implements FindMarkUseCase {

    private final GoogleApiUtil googleApiUtil;
    private final GeopositionToMarkMapper geopositionToMarkMapper;
    private final MarkToMarkResponseMapper markToMarkResponseMapper;

    @Override
    public MarkResponse find(String address) {
        String validateAddress = UrlUtils.validateParam(address);
        Geoposition geoposition = googleApiUtil.getGeoposition(validateAddress);
        Set<Mark> marks = this.geopositionToMarkMapper.map(geoposition);
        return this.markToMarkResponseMapper.map(marks);
    }
}
