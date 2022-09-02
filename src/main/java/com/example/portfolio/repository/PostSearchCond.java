package com.example.portfolio.repository;

import lombok.Data;

@Data
public class PostSearchCond {

    private String titleCheck;

    private String authorCheck;

    public PostSearchCond() {
    }

    public PostSearchCond(String titleCheck, String authorCheck) {
        this.titleCheck = titleCheck;
        this.authorCheck = authorCheck;
    }
}
