package com.flock.TP_server.interceptors;

import com.flock.TP_server.models.AuthToken;
import com.flock.TP_server.repositories.AuthTokenRepository;
import com.flock.TP_server.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (Objects.equals(request.getMethod(), "OPTIONS")) {
            return true;
        }

        String token = request.getHeader("Authorization");
        boolean isPresent = authenticationService.checkToken(token);
        if (isPresent) {
            Integer userId = authenticationService.getUserId(token);
            request.setAttribute("userId", userId);
            return true;
        }

        response.setStatus(401);
        return false;
    }
}
