package com.reflecta.api.strava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/strava")
public class StravaSyncController {

	@Autowired
    private StravaService stravaService;


    @PostMapping("/sync")
    public String syncStravaData(@RequestParam String accessToken, @RequestParam Long userId) {
        stravaService.syncActivities(accessToken, userId);
        return "Strava data synced successfully!";
    }
}
