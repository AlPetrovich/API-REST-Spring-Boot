package com.blog.security;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JWTAuthResponseDTO {

    private String tokenAccess;
    private String typeToken = "Bearer";

    public JWTAuthResponseDTO(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }
}
