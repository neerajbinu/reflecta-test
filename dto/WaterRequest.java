package com.reflecta.dto;

public class WaterRequest {
    private Long userId;
    private Integer ml;

    public WaterRequest() {}

    public WaterRequest(Long userId, Integer ml) {
        this.userId = userId;
        this.ml = ml;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMl() {
        return ml;
    }

    public void setMl(Integer ml) {
        this.ml = ml;
    }
}
