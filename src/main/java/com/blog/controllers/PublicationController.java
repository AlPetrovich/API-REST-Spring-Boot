package com.blog.controllers;


import com.blog.dtos.PublicationDTO;
import com.blog.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO){
        PublicationDTO newPublication = publicationService.createPublication(publicationDTO);
        return new ResponseEntity<>(newPublication, HttpStatus.CREATED);
    }
}
