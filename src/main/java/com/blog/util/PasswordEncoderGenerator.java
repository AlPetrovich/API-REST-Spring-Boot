package com.blog.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
        //$2a$10$VAF6h.XvWYPir4wGrdbFRuJrMAEP1K3.FD8SEdchZKbDaXeLyQR8y
    }
}
