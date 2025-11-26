package br.com.coderico.aplicai.http.handler;

import br.com.coderico.aplicai.exception.DomainException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleDomainException(DomainException ex, HttpServletRequest request) {
        ProblemDetail error = ProblemDetailFactory.createProblemDetail(ex, request);
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
}
