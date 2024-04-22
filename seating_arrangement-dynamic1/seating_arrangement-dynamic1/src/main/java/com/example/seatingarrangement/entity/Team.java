package com.example.seatingarrangement.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Team")
@Data
public class Team {


    private String teamId;

    private String layoutId;

    private List<TeamInfo> teams;

    @CreatedDate

    private Date createdDated;

    @LastModifiedDate

    private Date lastModifiedDate;

}