package br.com.api.produtos.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class ResponseModel {

    private int status;
    private String message;

}
