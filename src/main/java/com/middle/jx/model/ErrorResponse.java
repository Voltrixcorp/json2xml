package com.middle.jx.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private String error;
    private String error_description;
}
