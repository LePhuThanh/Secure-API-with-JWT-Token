package com.phuthanh.SpringSecurity_JWT_Token.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthenticationRequest {
    private String email;
    String password;
}
