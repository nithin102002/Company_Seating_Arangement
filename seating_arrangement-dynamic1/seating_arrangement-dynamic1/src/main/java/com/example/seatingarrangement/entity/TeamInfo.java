package com.example.seatingarrangement.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeamInfo {

    private String teamName;

    private int teamCount;

    @JsonProperty("teamCode")
    private String teamCode;
}
