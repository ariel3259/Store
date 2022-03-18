package com.ariel.Store.Middlewares;

import com.ariel.Store.Utils.Tokens;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Component
public class LoggerAuthentification extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Tokens jwt = new Tokens();

        if(request.getRequestURI().equals("/api/users/auth") || request.getRequestURI().equals("/api/users/register")){
            filterChain.doFilter(request, response);
            return;
        }

        if(!jwt.verificationToken(request.getHeader("authorization"), request.getHeader("subject"))){
            response.sendError(401, "You don't have access");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
