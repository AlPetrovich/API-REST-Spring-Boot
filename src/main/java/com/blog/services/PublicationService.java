package com.blog.services;

import com.blog.dtos.PublicationDTO;

import java.util.List;

public interface PublicationService {

    public PublicationDTO createPublication(PublicationDTO publicationDTO);

    public List<PublicationDTO> getAllPublications(int pageNumber, int pageSize);

    public PublicationDTO getPublicationById(Long id);

    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);

    public void deletePublication(Long id);
}
