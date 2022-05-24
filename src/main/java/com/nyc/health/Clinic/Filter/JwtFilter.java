package com.nyc.health.Clinic.Filter;


import com.nyc.health.Clinic.Service.CustomeUserDetailService;
import com.nyc.health.Clinic.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

 @Autowired
    JwtUtil jwtUtil;

 @Autowired
    CustomeUserDetailService customeUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authoriztion");
        String token =null;
        String username=null;

        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer")){
              token = authorizationHeader.substring(7);
              username = jwtUtil.extractUsername(token);

        }
         if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null ){
             UserDetails  userDetails =  customeUserDetailService.loadUserByUsername(username);
                       if(jwtUtil.validateToken(token, userDetails)) {

                           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                   userDetails, null, userDetails.getAuthorities());
                           usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                                   .buildDetails(request));

                           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);



                       }

         }
           filterChain.doFilter(request, response);
    }
}
