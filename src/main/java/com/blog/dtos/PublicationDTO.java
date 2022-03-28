package com.blog.dtos;
import com.blog.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublicationDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    //View Comments
    private Set<Comment> commentSet;

}
