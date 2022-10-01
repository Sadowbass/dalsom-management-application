package com.dalsom.management.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @JsonProperty("rsp-code")
    private int rspCode;

    @JsonProperty("rsp-message")
    private String rspMessage;
}
