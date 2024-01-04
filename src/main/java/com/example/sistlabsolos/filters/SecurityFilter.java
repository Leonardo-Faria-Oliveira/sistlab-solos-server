package com.example.sistlabsolos.filters;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.sistlabsolos.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    AuthService authService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var servLetPath = request.getServletPath();

        if(servLetPath.contains("/auth/login")){
            filterChain.doFilter(request, response);
        }
        try {

            var token = this.recoveryToken(request);

            if(token != null){

                var subject = authService.validateToken(token);
                request.setAttribute("email", subject);
                filterChain.doFilter(request, response);

            }

            response.sendError(401, "Usuário sem permissão");

            
        } catch (Exception e) {
            
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());

        }



 

    }
                
    

    private String recoveryToken(HttpServletRequest request) {
      
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;

        return authHeader.replace("Bearer", "");
        
    }
    
}
