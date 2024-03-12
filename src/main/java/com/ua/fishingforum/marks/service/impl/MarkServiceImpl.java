package com.ua.fishingforum.marks.service.impl;

import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.marks.repository.MarkRepository;
import com.ua.fishingforum.marks.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;


    @Override
    public Mark createMark(Mark mark) {
        return this.markRepository.save(mark);
    }

    public boolean existsByLatAndLng(double lat, double lng) {
        return markRepository.existsByLatAndLng(lat, lng);
    }


}
