package com.blog.exceptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
//------Exception GLOBAL-------
public class BlogAppException extends RuntimeException{

    private HttpStatus state;
    private String message;

    public BlogAppException(HttpStatus state, String message) {
        super();
        this.state = state;
        this.message = message;
    }

    public BlogAppException(HttpStatus state, String message,  String message1) {
        super(message);
        this.state = state;
        this.message = message;
        this.message = message1;
    }
}
