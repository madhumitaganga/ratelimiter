package com.team4.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.team4.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Class checks if the request has valid token.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static String User;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Method to filter the tokens.
     *
     * @param request  Http request
     * @param response Http response
     * @param chain    FilterChain variable to compress and decompress
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null) {
            jwtToken = requestTokenHeader;
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                JwtRequestFilter.User = username;
                System.out.println("usepoints=" + username);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {

            logger.warn("No proper request!!Check the requesttoken");
        }

        /**
         * Once we receive the token validate it.
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            /**
             * If token is valid configure Spring Security to manually set authentication
             */
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                /**
                 * After setting the Authentication in the context, we specify
                 * That the current user is authenticated. So it passes the Spring Security
                 * Configurations successfully.
                 * getcontext() is an instance of SecurityContextHolder
                 */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}
