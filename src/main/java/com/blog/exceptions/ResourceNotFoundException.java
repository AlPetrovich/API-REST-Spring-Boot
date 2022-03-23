package com.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) //valor que devuelve cuando se llama esta clase Not Found
public class ResourceNotFoundException extends RuntimeException{

    private String nameResource;
    private String nameField;
    private Long valueField;

    //Constructor simple
    public ResourceNotFoundException(String nameResource, String nameField, Long valueField) {
        super(String.format("%s NOT FOUND with : %s : '%s'", nameResource, nameField, valueField));
        this.nameResource = nameResource;
        this.nameField = nameField;
        this.valueField = valueField;
    }

    //getter & setters
    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public Long getValueField() {
        return valueField;
    }

    public void setValueField(Long valueField) {
        this.valueField = valueField;
    }
}
