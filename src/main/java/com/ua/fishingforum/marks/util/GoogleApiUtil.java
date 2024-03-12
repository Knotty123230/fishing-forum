package com.ua.fishingforum.marks.util;

import com.ua.fishingforum.marks.web.dto.Geoposition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RequiredArgsConstructor
@Component
public final class GoogleApiUtil {
    private final RestTemplate restTemplate;
    @Value("${google.api.key}")
    private String apiKey;

    public Geoposition getGeoposition(String address) {
        URI uri = URI.create("https://maps.googleapis.com/maps/api/geocode/json?address=%s&language=ua&key=%s".formatted(address, apiKey));
        return restTemplate.getForObject(uri, Geoposition.class);
    }
}
