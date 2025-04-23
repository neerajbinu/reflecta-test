package com.reflecta.api.strava;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StravaClient {

	    private final WebClient webClient;

	    public StravaClient(WebClient.Builder webClientBuilder) {
	        this.webClient = webClientBuilder.baseUrl("https://www.strava.com/api/v3").build();
	    }

	    public List<StravaActivityDTO> getRecentActivities(String accessToken) {
	        return webClient.get()
	                .uri("/athlete/activities")
	                .header("Authorization", "Bearer " + accessToken)
	                .retrieve()
	                .bodyToFlux(StravaActivityDTO.class)
	                .collectList()
	                .block();
	    }

}
