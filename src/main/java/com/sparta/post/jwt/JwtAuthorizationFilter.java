package com.sparta.post.jwt;

import com.sparta.post.entity.User;
import com.sparta.post.entity.UserRoleEnum;
import com.sparta.post.exception.TokenNotValidException;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.security.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
//authfilter,loggingfilter 대신 편리하게 사용
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        String tokenValue = jwtUtil.getTokenFromRequest(req);

        if (StringUtils.hasText(tokenValue)) {
            String[] temp = tokenValue.split("[=,]");
            String accessTokenValue = temp[3];
            String refreshTokenValue = temp[5];
            // JWT 토큰 substring
            accessTokenValue = jwtUtil.substringToken(accessTokenValue);
            refreshTokenValue = jwtUtil.substringToken(refreshTokenValue);
            log.info(accessTokenValue);
            log.info(refreshTokenValue);

            switch (jwtUtil.validateAccessAndRefreshToken(accessTokenValue)) {
                case 1:
                    Claims info = jwtUtil.getUserInfoFromToken(accessTokenValue);
                    try {
                        setAuthentication(info.getSubject());
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        return;
                    }
                    break;
                case 2: // access 만료 토큰
                    if(jwtUtil.validateToken(refreshTokenValue)) {
                        String username = SecurityUtil.getPrincipal().get().getUsername();
                        UserRoleEnum role = SecurityUtil.getPrincipal().get().getRole();

                        String token = jwtUtil.recreateAccessToken(username,role);
                        jwtUtil.addJwtToCookie(token, res);
                        Claims info2 = jwtUtil.getUserInfoFromToken(refreshTokenValue);
                        try {
                            setAuthentication(info2.getSubject());
                        } catch (Exception e) {
                            log.error(e.getMessage());
                            return;
                        }
                    }else {
                        throw new TokenNotValidException("유효하지 않는 Token !! ㄴ >.< ㄱ !! 입니다");
                    }
                    break;
                default:
                    throw new TokenNotValidException("지원하지 않는 JWT 토큰이거나 잘못된 JWT 토큰입니다 Token !! ㄴ >.< ㄱ !! 입니다");
            }
        }
        filterChain.doFilter(req, res);
    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}