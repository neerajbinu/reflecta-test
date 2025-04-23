package com.reflecta.api.strava;

import java.time.Instant;

public class StravaActivityDTO {

	    private Long id;
	    private String name;
	    private String type; // Example: "Run", "Ride"
	    private float distance; // in meters
	    private int moving_time; // in seconds
	    private Instant start_date; // UTC time
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public float getDistance() {
			return distance;
		}
		public void setDistance(float distance) {
			this.distance = distance;
		}
		public int getMoving_time() {
			return moving_time;
		}
		public void setMoving_time(int moving_time) {
			this.moving_time = moving_time;
		}
		public Instant getStart_date() {
			return start_date;
		}
		public void setStart_date(Instant start_date) {
			this.start_date = start_date;
		}
	    
	    
	    
	}


