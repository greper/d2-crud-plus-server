package com.veryreader.d2p.api.security.vo;


import lombok.Data;

@Data
public class TokenResult {
    private Long  id;
    private String username;
    private String token;

    public TokenResult(String token, LoginUser loginUser) {
        this.id = loginUser.getId();
        this.username = loginUser.getUsername();
        this.token = token;
    }
    public String getUuid(){
        return id.toString();
    }
    public String getName(){
        return username;
    }
}
