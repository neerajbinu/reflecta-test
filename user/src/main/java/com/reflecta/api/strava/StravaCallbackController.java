package com.reflecta.api.strava;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.reflecta.service.ExerciseDataService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/strava")
public class StravaCallbackController {

    @Value("${strava.client.id}")
    private String clientId;

    @Value("${strava.client.secret}")
    private String clientSecret;

    @Autowired
    private StravaService stravaService;

    @Autowired
    private ExerciseDataService exerciseDataService;

    @GetMapping("/callback")
    public String handleStravaCallback(@RequestParam("code") String code, @RequestParam("state") String userIdStr) {
        Long userId = Long.parseLong(userIdStr); // Passed from front-end or session
        RestTemplate restTemplate = new RestTemplate();

        String tokenUrl = "https://www.strava.com/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=" + clientId +
                      "&client_secret=" + clientSecret +
                      "&code=" + code +
                      "&grant_type=authorization_code";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);

        String accessToken = (String) response.getBody().get("access_token");

        stravaService.syncActivities(accessToken, userId);  // Fetch and store in ExerciseData

        return "Strava activities synced successfully!";
    }
    
    @GetMapping("/login")
    public void redirectToStrava(@RequestParam("userId") Long userId, HttpServletResponse response) throws Exception {
        String redirectUrl = "https://www.strava.com/oauth/authorize" +
                "?client_id=" + clientId +
                "&response_type=code" +
                "&redirect_uri=" + "http://localhost:8081/api/strava/callback" +
                "&approval_prompt=force" +
                "&scope=activity:read_all" +
                "&state=" + userId; // this will be used in the callback

        response.sendRedirect(redirectUrl);
    }
}

