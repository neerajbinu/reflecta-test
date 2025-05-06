package com.reflecta.service;

import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthAPIClientService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8082/health";

    public HealthAPIClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Map<String, Object> getForMap(String path) {
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            BASE_URL + path,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Map<String, Object>>() {}
        );
        return response.getBody();
    }

    public Map<String, Object> getFoodData() {
        return getForMap("/food");
    }

    public Map<String, Object> getSleepData() {
        return getForMap("/sleep");
    }

    public Map<String, Object> getMoodData() {
        return getForMap("/mood");
    }

    public Map<String, Object> getExerciseData() {
        return getForMap("/exercise");
    }

    public Map<String, Object> getWaterData() {
        return getForMap("/water");
    }
}
