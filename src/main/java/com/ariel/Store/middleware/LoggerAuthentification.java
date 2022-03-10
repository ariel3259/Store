package com.ariel.Store.middleware;

import com.ariel.Store.JUtils.Tokens;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggerAuthentification extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Tokens jwt = new Tokens();
        if(request.getRequestURI().equals("/api/users/auth") || request.getRequestURI().equals("api/users/register")){
            filterChain.doFilter(request, response);
            return;
        }
        if(request.getHeader("authorization") == null || request.getHeader("subject") == null){
            response.sendError(403, "You don't have access");
            return;
        }
        if(!jwt.verificationToken(request.getHeader("authorization"), request.getHeader("subject"))){
            response.sendError(403, "You don't have access");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
