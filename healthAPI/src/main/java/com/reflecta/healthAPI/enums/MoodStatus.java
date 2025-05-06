package com.reflecta.healthAPI.enums;

public enum MoodStatus {
	 	HAPPY(1),
	    GOOD(2),
	    NEUTRAL(3),
	    SAD(4),
	    DEPRESSED(5);

	    private final int score;

	    MoodStatus(int score) {
	        this.score = score;
	    }

	    public int getScore() {
	        return score;
	    }

}
