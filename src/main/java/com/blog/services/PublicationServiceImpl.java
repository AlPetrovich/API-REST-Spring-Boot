package com.blog.services;

import com.blog.dtos.PublicationDTO;
import com.blog.dtos.PublicationResponse;
import com.blog.entities.Publication;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PublicationResponse getAllPublications(int pageNumber, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        //1- Pageable
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Publication> publicationsPage = publicationRepository.findAll(pageable); //objeto page
        //Obtener contenido del objeto page
        List<Publication> publicationList = publicationsPage.getContent();//lista de entidades
        List<PublicationDTO> contentListDTO = publicationList.stream().map( publication -> mapDTO(publication)).collect(Collectors.toList()); //lista dtos
        //Clase  que devuelve contenido(ARRAY) - num pagina - tamaño pagina - total de elementos - y si es la ultima
        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setContent(contentListDTO);
        publicationResponse.setPageNumber(publicationsPage.getNumber());
        publicationResponse.setPageSize(publicationsPage.getSize());
        publicationResponse.setTotalElements(publicationsPage.getTotalElements());
        publicationResponse.setTotalPages(publicationsPage.getTotalPages());
        publicationResponse.setLast(publicationsPage.isLast()); //es la ultima pagina?

        return publicationResponse;
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

    @Override
    public void deletePublication(Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Publication","id",id));
        //pasar entidad por parametro
        publicationRepository.delete(publication);
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
