package br.com.coderico.aplicai.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidAccessException extends DomainException {

    public InvalidAccessException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
