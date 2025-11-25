package br.com.coderico.aplicai.handler;

import br.com.coderico.aplicai.exception.DomainException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ProblemDetailFactory {

    private ProblemDetailFactory() {
    }

    public static ProblemDetail createProblemDetail(HttpStatus statusCode, String title, String detail, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(statusCode);
        problemDetail.setDetail(detail);
        problemDetail.setTitle(title);
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    public static ProblemDetail createProblemDetail(DomainException ex, HttpServletRequest request) {
        return createProblemDetail(ex.getStatus(), ex.getStatus().getReasonPhrase(), ex.getMessage(), request);
    }

    public static ProblemDetail createProblemDetail(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail error = createProblemDetail(HttpStatus.UNPROCESSABLE_ENTITY, "Parâmetros inválidos", "Dados de entrada inválidos", request);

        Map<String, List<String>> violations = createValidationErrors(ex);
        error.setProperties(Map.of("violations", violations));
        return error;
    }

    public static ProblemDetail createProblemDetail(JWTVerificationException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Acesso negado", "Token de acesso inválido", request);
    }

    private static Map<String, List<String>> createValidationErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));
    }
}
