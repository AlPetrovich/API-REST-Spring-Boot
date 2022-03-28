package com.blog.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//1- ErrorDetails - 2 - Exception - GlobalExceptionHandler
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;


}
