package com.blog.controllers;
import com.blog.dtos.LoginDTO;
import com.blog.dtos.RegisterDTO;
import com.blog.entities.Rol;
import com.blog.entities.User;
import com.blog.repositories.RolRepository;
import com.blog.repositories.UserRepository;
import com.blog.security.JWTAuthResponseDTO;
import com.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //implementamos el authenticationManager
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrMail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("That username already exists", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByMail(registerDTO.getMail())){
            return new ResponseEntity<>("That mail already exists", HttpStatus.BAD_REQUEST);
        }
        //Establecer datos al usuario
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setMail(registerDTO.getMail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        //Establecer roles al usuario
        Rol roles = rolRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles)); //1 solo rol le establece a este usuario

        userRepository.save(user);
        return new ResponseEntity<>("Successfully registered user", HttpStatus.OK);
    }


}
