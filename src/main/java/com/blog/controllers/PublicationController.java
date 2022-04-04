package com.blog.controllers;
import com.blog.dtos.PublicationDTO;
import com.blog.dtos.PublicationResponse;
import com.blog.services.PublicationService;
import com.blog.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping("/list")
    public PublicationResponse listPublications(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.NUMBER_PAGE_DEFAULT, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.NUMBER_SIZE_DEFAULT,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.ORDER_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.ORDER_DIRECTION_DEFAULT, required = false) String sortDir
    ){
        return publicationService.getAllPublications(pageNumber, pageSize, orderBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationById(@PathVariable("id") Long id){
        PublicationDTO publicationDTO = publicationService.getPublicationById(id);
        return new ResponseEntity<>(publicationDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@Valid @RequestBody PublicationDTO publicationDTO){
        PublicationDTO newPublication = publicationService.createPublication(publicationDTO);
        return new ResponseEntity<>(newPublication, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public  ResponseEntity<PublicationDTO> updatePublication(
            @Valid @RequestBody PublicationDTO publicationDTO,
            @PathVariable("id") Long id
    ){
        PublicationDTO publicationResponse = publicationService.updatePublication(publicationDTO,id);
        return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable("id") Long id){
        publicationService.deletePublication(id);
        return new ResponseEntity<>("Publication deleted", HttpStatus.OK);
    }

}
