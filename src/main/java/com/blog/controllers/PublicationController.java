package com.blog.controllers;


import com.blog.dtos.PublicationDTO;
import com.blog.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public List<PublicationDTO> listPublications(){
        return publicationService.getAllPublications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationById(@PathVariable("id") Long id){
        PublicationDTO publicationDTO = publicationService.getPublicationById(id);
        return new ResponseEntity<>(publicationDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PublicationDTO> updatePublication(
            @RequestBody PublicationDTO publicationDTO,
            @PathVariable("id") Long id
    ){
        PublicationDTO publicationResponse = publicationService.updatePublication(publicationDTO,id);
        return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
    }

}
