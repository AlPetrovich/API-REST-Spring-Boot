package com.blog.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String usernameOrMail;
    private String password;

}
