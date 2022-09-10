package com.example.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class Timestamp {
    private Date regDt;
    private Date updDt;
}
