package com.security.security.filters;

import com.security.security.service.CustomUserDetailService;
import com.security.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO check token
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }
        //TODO validate token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //TODO - for further user verification need to fetch the userdetails
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
            //TODO set userdetails into context
            if (jwtUtil.validateToken(username, userDetails, token)){
                //TODO create authToken with the userdetails (eg:- userDetails, roles,...)
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               //TODO give the request details for the authToken
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               //TODO set the authToken to the context
               SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        //TODO after that call to the next filter
        filterChain.doFilter(request,response);
    }
}
