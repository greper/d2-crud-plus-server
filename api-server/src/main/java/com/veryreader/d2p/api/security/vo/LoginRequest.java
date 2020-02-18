package com.veryreader.d2p.api.security.vo;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
