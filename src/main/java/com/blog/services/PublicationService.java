package com.blog.services;
import com.blog.dtos.PublicationDTO;
import com.blog.dtos.PublicationResponse;


public interface PublicationService {

    public PublicationDTO createPublication(PublicationDTO publicationDTO);

    //modify list<PublicationDTO>
    public PublicationResponse getAllPublications(int pageNumber, int pageSize, String orderBy, String sortDir);

    public PublicationDTO getPublicationById(Long id);

    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);

    public void deletePublication(Long id);
}
