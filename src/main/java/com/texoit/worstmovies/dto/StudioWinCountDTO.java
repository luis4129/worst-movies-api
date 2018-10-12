package com.texoit.worstmovies.dto;

public class StudioWinCountDTO {

    String name;
    Long winCount;

    public StudioWinCountDTO(String name, Long winCount) {
        this.name = name;
        this.winCount = winCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWinCount() {
        return winCount;
    }

    public void setWinCount(Long winCount) {
        this.winCount = winCount;
    }
}
