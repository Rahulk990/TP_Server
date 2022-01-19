package com.flock.TP_server.Interceptors;

import com.flock.TP_server.Models.AuthToken;
import com.flock.TP_server.Repositories.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("In the Pre Handle Interceptor");

        String token = request.getHeader("Authorization");
        System.out.println(token);

        boolean isPresent = authTokenRepository.checkToken(token);
        if(isPresent) {
            AuthToken authToken = authTokenRepository.getAuthTokenByToken(token);
            request.setAttribute("userId", authToken.getUserId());
            return true;
        }

        response.setStatus(401);
        return false;
    }
}
