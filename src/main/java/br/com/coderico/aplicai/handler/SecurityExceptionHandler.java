package br.com.coderico.aplicai.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handle(BadCredentialsException ex, HttpServletRequest request) {
        HttpStatus statusCode = HttpStatus.UNAUTHORIZED;
        var problemDetails = ProblemDetailFactory.createProblemDetail(statusCode, "Credenciais inválidas", ex.getMessage(), request);

        return ResponseEntity.status(statusCode)
                .body(problemDetails);
    }

    /* TODO: Fazer handler de SignatureVerificationException funcionar */
    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<ProblemDetail> handle(SignatureVerificationException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetailFactory.createProblemDetail(ex, request);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(problemDetail);
    }

}
