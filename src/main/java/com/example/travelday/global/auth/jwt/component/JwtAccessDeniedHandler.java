package com.example.travelday.global.auth.jwt.component;

import com.example.travelday.global.exception.CustomException;
import com.example.travelday.global.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
//@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Qualifier("handlerExceptionResolver")
    private  HandlerExceptionResolver resolver;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        resolver.resolveException(request, response, null, new CustomException(ErrorCode.FORBIDDEN));
    }
}
