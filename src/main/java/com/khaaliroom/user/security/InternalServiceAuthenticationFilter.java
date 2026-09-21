package com.khaaliroom.user.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class InternalServiceAuthenticationFilter
        extends OncePerRequestFilter {

    @Value("${internal.service.secret}")
    private String internalServiceSecret;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String requestUri = request.getRequestURI();

        if (!requestUri.startsWith("/api/v1/internal/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String providedSecret =
                request.getHeader("X-Internal-Service-Secret");

        if (providedSecret == null ||
                !internalServiceSecret.equals(providedSecret)) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                      "status": 401,
                      "error": "Unauthorized",
                      "message": "Invalid internal service credentials"
                    }
                    """);

            return;
        }

        filterChain.doFilter(request, response);
    }
}