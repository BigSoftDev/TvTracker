package com.rrdz.tvtracker.service;

import com.rrdz.tvtracker.entity.Episode;
import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.repository.ShowRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    headers.setBearerAuth(token);

    HttpEntity<Void> entity = new HttpEntity<>(headers);

    String url = "https://api.thetvdb.com/v4/series/filter?country=usa&lang=eng";

    String urlTest = "https://api4.thetvdb.com/v4/series";

    ResponseEntity<Map> response = restTemplate.exchange(
            urlTest,
            HttpMethod.GET,
            entity,
            Map.class
    );

    List<Map<String, Object>> data =
            (List<Map<String, Object>>) response.getBody().get("data");

     List<Show> shows = new ArrayList<>();
    for (Map<String, Object> item : data) {

    Show show = new Show();

    Object nameObj = item.get("name");
    Object idObj = item.get("id");
    Object scoreObj = item.get("score");
    Object imgObj = item.get("image");

    String name = nameObj != null ? nameObj.toString() : null;
    Long tvdbId = idObj != null ? ((Number) idObj).longValue() : null;
    Long score = scoreObj != null ? ((Number) scoreObj).longValue() : null;
    String imgLink = imgObj != null ? imgObj.toString() : null;

    show.setName(name);
    show.setTvdbId(tvdbId);
    show.setScore(score);
    show.setImgLink(imgLink);

    shows.add(show);
}

   // showRepository.saveAll(shows);

    return shows;
}

public List<Episode> fetchEpisodesForShow(Long tvdbId) {
    //still needs work. 
    String token = getTvdbToken(apiKey);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<Void> entity = new HttpEntity<>(headers);
    String url = "https://api.thetvdb.com/v4/series/" + tvdbId + "/episodes";
    ResponseEntity<Map> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            Map.class
    );
    List<Map<String, Object>> data =
            (List<Map<String, Object>>) response.getBody().get("data");
    List<Episode> episodes = new ArrayList<>();
    for (Map<String, Object> item : data) {
        Episode episode = new Episode();
        Object nameObj = item.get("name");
        Object idObj = item.get("id");
        Object seasonObj = item.get("season");
        Object numberObj = item.get("number");
        String name = nameObj != null ? nameObj.toString() : null;
        Long episodeId = idObj != null ? ((Number) idObj).longValue() : null;
        int seasonNumber = seasonObj != null ? ((Number) seasonObj).intValue() : null;
        int episodeNumber = numberObj != null ? ((Number) numberObj).intValue() : null;
        episode.setName(name);
        episode.setSeasonNumber(seasonNumber);
        episode.setEpisodeNumber(episodeNumber);
        episodes.add(episode);
    }
    return episodes;
}



    // token method from step 2
    private String getTvdbToken(String apiKey) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api4.thetvdb.com/v4/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("apikey", apiKey);

        HttpEntity<Map<String, String>> entity =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Map.class
        );

        Map<String, Object> data =
                (Map<String, Object>) response.getBody().get("data");

        return (String) data.get("token");
    }
}