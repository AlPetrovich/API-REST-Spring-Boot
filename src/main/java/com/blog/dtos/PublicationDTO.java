package com.blog.dtos;
import com.blog.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublicationDTO {


    private Long id;

    @NotEmpty(message = "Title cannot be empty") @Size(min = 2, message = "The title of the publication must have at least two characters.")
    private String title;

    @NotEmpty @Size(min = 10, message = "The description of the publication must have at least ten characters")
    private String description;

    @NotEmpty(message = "Content cannot be empty")
    private String content;

    //View Comments
    private Set<Comment> commentSet;

}
