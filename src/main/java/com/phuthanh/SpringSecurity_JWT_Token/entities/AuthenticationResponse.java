package com.phuthanh.SpringSecurity_JWT_Token.entities;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}
