package com.blog.services;

import com.blog.dtos.PublicationDTO;
import com.blog.entities.Publication;
import com.blog.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService{

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        //DTO - ENTITY -- save ENTITY
        Publication publication = new Publication();
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());
        //Save -- persisted in data base
        Publication newPublication = publicationRepository.save(publication);

        //ENTITY - DTO
        PublicationDTO publicationResponseDTO = new PublicationDTO();
        publicationResponseDTO.setId(newPublication.getId());
        publicationResponseDTO.setTitle(newPublication.getTitle());
        publicationResponseDTO.setDescription(newPublication.getDescription());
        publicationResponseDTO.setContent(newPublication.getContent());
        //return DTO
        return publicationResponseDTO;
    }
}
