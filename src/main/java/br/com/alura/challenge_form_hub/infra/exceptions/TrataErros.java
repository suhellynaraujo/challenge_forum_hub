package com.aluraone.forumHub.infra.exceptions;

import com.aluraone.forumHub.domain.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TrataErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity trataErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity trataErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        var listaDadosErros = erros.stream().map(DadosErroValidacao::new).toList();
     return ResponseEntity.badRequest().body(listaDadosErros);

    }

    //exception lançada quando tem problema de validação no Dto

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage()); }



    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity tratarErroNaAutenticacao(AuthorizationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullPointerException(NullPointerException ex) {
        String errorMessage = "Erro NullPointerException";
        return ResponseEntity.badRequest().body(errorMessage + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }



    //Record que é usado só na classe TrataErro para mostrar un Json con ese formato campo e mensagem
    private record DadosErroValidacao(String campo, String message){
        DadosErroValidacao(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}


