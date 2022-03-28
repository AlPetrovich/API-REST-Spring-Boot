package com.blog.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private Long id;

    @NotEmpty(message = "Name cannot be empty or null")
    private String name;

    @NotEmpty(message = "Mail cannot be empty or null")
    @Email(message = "The mail must have a valid format")
    private String mail;

    @NotEmpty(message = "Body cannot be empty or null")
    @Size(min = 10, message = "The body of the comment must have at least ten characters.")
    private String body;
}
