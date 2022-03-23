package com.blog.services;

import com.blog.dtos.PublicationDTO;
import com.blog.entities.Publication;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService{

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        //DTO parameter - ENTITY
        Publication publication = mapEntity(publicationDTO);
        //Save -- persisted in data base
        Publication newPublication = publicationRepository.save(publication);
        //ENTITY - DTO
        PublicationDTO publicationResponseDTO = mapDTO(newPublication);
        //return DTO
        return publicationResponseDTO;
    }

    @Override
    public List<PublicationDTO> getAllPublications() {
        List<Publication> publicationList = publicationRepository.findAll();
        return publicationList.stream().map( publication -> mapDTO(publication)).collect(Collectors.toList());
    }

    @Override
    public PublicationDTO getPublicationById(Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Publication","id",id));
        return mapDTO(publication);
    }

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id) {
        //obtener entidad
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Publication","id",id));
        //cambiarle valores a entidad
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());
        //guardar entidad
        Publication publicationUpdate = publicationRepository.save(publication);
        //retornar objeto de transferencia
        return mapDTO(publicationUpdate);
    }


    //----------------CONVERTERS------------------

    //Convertir entidad a dto
    private PublicationDTO mapDTO(Publication publication){
        PublicationDTO publicationDTO = new PublicationDTO();
        //Establecemos valores de la entidad a mi objeto de transferencia
        publicationDTO.setId(publication.getId());
        publicationDTO.setTitle(publication.getTitle());
        publicationDTO.setDescription(publication.getDescription());
        publicationDTO.setContent(publication.getContent());

        return publicationDTO;
    }

    //convertir dto a entidad
    private Publication mapEntity(PublicationDTO publicationDTO){
        Publication publication = new Publication();
        //Establecemos valores del objeto de transferencia a la entidad
        publication.setId(publicationDTO.getId());
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        return publication;
    }
}
