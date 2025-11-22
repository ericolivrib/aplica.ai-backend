package br.com.coderico.aplicai.security;

import br.com.coderico.aplicai.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class RequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractJwtToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String subject = jwtService.getSubject(token);

        UserAuthenticated user = (UserAuthenticated) userDetailsService.loadUserByUsername(subject);

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        var detailsSource = new WebAuthenticationDetailsSource();
        authentication.setDetails(detailsSource.buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith("Bearer ")) {
            return token.replace("Bearer", "")
                    .trim();
        }

        return null;
    }
}
