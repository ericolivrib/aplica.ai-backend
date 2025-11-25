package br.com.coderico.aplicai.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                               HttpServletRequest request) {
        HttpStatus statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
        ProblemDetail problemDetail = ProblemDetailFactory.createProblemDetail(ex, request);

        return ResponseEntity.status(statusCode)
                .body(problemDetail);
    }
}
