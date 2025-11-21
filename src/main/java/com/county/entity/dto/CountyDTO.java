package com.county.entity.dto;

import com.county.entity.County;

public record CountyDTO(String fips, String state, String name) {
    public static CountyDTO fromEntity(County c) {
        return new CountyDTO(c.getFips(), c.getState(), c.getName());
    }
}
