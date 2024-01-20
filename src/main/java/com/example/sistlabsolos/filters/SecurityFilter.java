package com.example.sistlabsolos.filters;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.sistlabsolos.services.AuthService;
import com.example.sistlabsolos.services.RoleService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    AuthService authService;

    @Autowired
    RoleService roleService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var servLetPath = request.getServletPath();
        

        if(servLetPath.contains("/auth/login") || servLetPath.contains("/create")){
            filterChain.doFilter(request, response);
        }
        else{

            try {

                var token = this.recoveryToken(request);
    
                if(token != null){
    
                    var subject = authService.validateToken(token);
                    var role = this.roleService.getRoleByName(subject);
                    
                    if(role == null){
    
                        response.sendError(401, "Usuário sem permissão");
    
                    }
                    else if(role.getName().equals("admin")){
                
                       
                        if(this.verifyAdminPath(servLetPath)){
    
                            filterChain.doFilter(request, response);
    
                        }else{
    
                            response.sendError(401, "Usuário sem permissão");
    
                        }
    
                    }
    
                    else if(role.getName().equals("labAdminEmployee")){

                        if(this.verifyLabAdminEmployeePath(servLetPath)){
    
                            filterChain.doFilter(request, response);
    
                        }else{
    
                            response.sendError(401, "Usuário sem permissão");
    
                        }
    
                    }
    
                    else if(role.getName().equals("employee")){
    
                        if(this.verifyEmployeePath(servLetPath)){
    
                            filterChain.doFilter(request, response);
    
                        }else{
    
                            response.sendError(401, "Usuário sem permissão");
    
                        }
    
                    }
                    else{
    
                       response.sendError(401, "Usuário sem permissão");
    
                    }
                    
              
    
                }
    
                else{
    
                    response.sendError(401, "Usuário sem permissão");
                
                }
                
            } catch (Exception e) {
                
                System.out.println("e.getMessage()");
                System.out.println(e.getMessage());
                response.sendError(401, "Usuário sem permissão");
    
            }

        }
        

    }

    private String recoveryToken(HttpServletRequest request) {
      
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;

        return authHeader.replace("Bearer", "");
        
    }

    public boolean verifyAdminPath(String path){

        if(path.contains("/v1/institution") ||
        path.equals("/v1/institutions") ||
        path.contains("/v1/role/") ||
        path.equals("/v1/roles") ||
        path.contains("/v1/admin") || 
        path.contains("/v1/admin/email") ||
        path.equals("/v1/admins") ||
        path.equals("/v1/labs/") || 
        path.contains("/v1/pricing") ||
        path.equals("/v1/pricings"))
            return true;

        return false;

    }

    public boolean verifyLabAdminEmployeePath(String path){

        if(path.contains("/v1/employee") ||
        path.equals("/v1/employees") ||
        path.contains("/v1/employee/email") ||
        path.contains("/v1/subscription") ||
        path.contains("/v1/client") || 
        path.equals("/v1/clients") ||
        path.contains("/v1/lab") || 
        path.contains("/v1/pricing") ||
        path.equals("/v1/pricings"))
            return true;

        return false;

    }

    public boolean verifyEmployeePath(String path){

        if(path.contains("/v1/employee") ||
        path.contains("/v1/employee/email") ||
        path.contains("/v1/client") || 
        path.equals("/v1/clients"))
            return true;

        return false;

    }
    
}
