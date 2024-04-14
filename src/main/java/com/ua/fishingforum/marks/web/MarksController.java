package com.ua.fishingforum.marks.web;

import com.ua.fishingforum.marks.usecase.CreateMarkUseCase;
import com.ua.fishingforum.marks.usecase.FindMarkUseCase;
import com.ua.fishingforum.marks.web.dto.CreatedMarkResponse;
import com.ua.fishingforum.marks.web.dto.MarkRequest;
import com.ua.fishingforum.marks.web.dto.MarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mark")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MarksController {
    private final FindMarkUseCase findMarkUseCase;
    private final CreateMarkUseCase createMarkUseCase;

    @GetMapping
    public MarkResponse find(@RequestParam("address") String address) {
        return findMarkUseCase.find(address);
    }

    @PostMapping
    public CreatedMarkResponse create(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
        MarkRequest markRequest = new MarkRequest(lat, lng);
        return createMarkUseCase.create(markRequest);
    }


}
