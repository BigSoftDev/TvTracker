package com.rrdz.tvtracker.service;

import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.repository.ShowRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class TvdbService {

    private final ShowRepository showRepository;

    @Value("${tvdb.api.key}")
    private String apiKey;

    public TvdbService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> fetchAndSaveTopShows() {
        String token = getTvdbToken(apiKey);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Example: fetch trending shows (replace with real endpoint)
        String url = "https://api.thetvdb.com/trending?page=1"; // adjust query if needed
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        List<Map<String, Object>> data = (List<Map<String, Object>>) response.getBody().get("data");

        // Map JSON to Show entity
        List<Show> shows = data.stream().map(item -> {
            Show show = new Show();
            show.setName((String) item.get("seriesName"));
            return show;
        }).collect(Collectors.toList());

        // Save to DB
        showRepository.saveAll(shows);

        return shows;
    }

    // token method from step 2
    private String getTvdbToken(String apiKey) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.thetvdb.com/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of("apikey", apiKey);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        return (String) response.getBody().get("token");
    }
}