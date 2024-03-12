package com.ua.fishingforum.marks.usecase;

import com.ua.fishingforum.marks.web.dto.MarkResponse;

public interface FindMarkUseCase {

    MarkResponse find(String address);
}
