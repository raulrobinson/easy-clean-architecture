package com.example.infrastructure.inputAdapters.dto;

import lombok.*;

import static com.example.common.utils.Shield.blindStr;

@Getter
@Setter
@Builder
public class RequestDto {
    private String name;
    private String country;

    public RequestDto(String name, String country) {
        this.name = blindStr(name);
        this.country = blindStr(country);
    }
}
