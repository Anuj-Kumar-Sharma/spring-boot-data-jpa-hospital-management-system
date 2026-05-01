package com.codingshuttle.youtube.hospitalManagement.security;

import com.codingshuttle.youtube.hospitalManagement.entity.User;
import com.codingshuttle.youtube.hospitalManagement.repository.UserRepository;
import com.codingshuttle.youtube.hospitalManagement.util.AuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthFilter request being received for uri {}",request.getRequestURI());
        try {
            //Bearer token_1234
            final String requestTokenHeader =  request.getHeader("Authorization");
            if (requestTokenHeader ==null || !requestTokenHeader.startsWith("Bearer")){
                filterChain.doFilter(request,response);
                return;
            }

            String token = requestTokenHeader.split("Bearer")[1].trim();
            String userName = authUtil.getUsernameFromToken(token);

            if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                User user = userRepository.findUsersByUsername(userName).orElseThrow(()->
                        new UsernameNotFoundException("User not found with username: "+userName));

                //Update SecurityContextHandler with authentication token
                if (user!=null){
                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }


            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            log.error("Error processing JWT authentication", e);
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
