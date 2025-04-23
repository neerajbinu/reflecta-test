package com.reflecta.api.strava;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StravaLoginController {

    @Value("${strava.client.id}")
    private String clientId;

    @Value("${strava.redirect.uri}")
    private String redirectUri;

    @GetMapping("/login")
    public ResponseEntity<Void> stravaLoginRedirect(@RequestParam Long userId) {
        String url = "https://www.strava.com/oauth/authorize" +
                     "?client_id=" + clientId +
                     "&response_type=code" +
                     "&redirect_uri=" + redirectUri +
                     "&approval_prompt=force" +
                     "&scope=activity:read_all" +
                     "&state=" + userId;

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

}

