package br.com.coderico.aplicai.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityAlreadyExistsException extends DomainException {

    public EntityAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
