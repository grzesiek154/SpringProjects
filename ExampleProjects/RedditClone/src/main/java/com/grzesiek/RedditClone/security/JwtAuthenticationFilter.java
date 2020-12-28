package com.grzesiek.RedditClone.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    So the JwtAuthenticationFilter class extends the class OncePerRequestFilter, by extending this class we can implement our validation logic inside the doFilterInternal() method.

    private JwtProvider jwtProvider;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, @Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       // Inside the doFilterInternal method, we are calling the getJwtFromRequest method which retrieves the Bearer Token (ie. JWT) from the HttpServletRequest object we are passing as input.
        String jwt = getJwtFromRequest(httpServletRequest);

        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            //Once we retrieve the token, we pass it to the validateToken() method of the JwtProvider class.
            String username = jwtProvider.getUserNameFromJWT(jwt);
            //Once the JWT is validated, we retrieve the username from the token by calling the getUsernameFromJWT() method.

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //Once we get the username, we retrieve the user using the UserDetailsService class and store the user inside the SecurityContext
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authentication");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }
}
