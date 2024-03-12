package com.ua.fishingforum.marks.service;


import com.ua.fishingforum.marks.model.Mark;

public interface MarkService {
    Mark createMark(Mark mark);

    boolean existsByLatAndLng(double lat, double lng);


}
