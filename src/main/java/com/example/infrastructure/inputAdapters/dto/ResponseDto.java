package com.example.infrastructure.inputAdapters.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private int status;
    private String message;
    private Object data;
}
