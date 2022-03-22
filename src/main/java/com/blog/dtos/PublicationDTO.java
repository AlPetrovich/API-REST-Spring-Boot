package com.blog.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublicationDTO {

    private Long id;
    private String title;
    private String description;
    private String content;


}
