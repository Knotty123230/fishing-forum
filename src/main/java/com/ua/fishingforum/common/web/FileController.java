package com.ua.fishingforum.common.web;

import com.ua.fishingforum.common.service.ImageStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final ImageStorageService imageStorageService;
    @GetMapping
    public byte[] getFile(@RequestParam("imageUrl") String imageUrl){
        return imageStorageService.getImage(imageUrl);
    }
}
