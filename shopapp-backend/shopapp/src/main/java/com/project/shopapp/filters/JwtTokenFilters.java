package com.project.shopapp.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.internal.Pair;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Mỗi request đi qua cái token filter này , để nó kiểm tra
// Trừ 2 request đăng nhập và đăng ký là không bị kiểm tra
// Các request khác hầu như bị kiểm tra
// Kế thừ từ On
public class JwtTokenFilters extends OncePerRequestFilter {
    @Override
    // Ném về exception
    protected void doFilterInternal(@NotNull  HttpServletRequest request,
                                  @NotNull  HttpServletResponse response,
                                  @NotNull  FilterChain filterChain) throws ServletException, IOException {
        if(isBypassToken(request)) {
            filterChain.doFilter(request, response); //enable bypass
            return;
        }
    // Cho đi qua hết
        // filterChain.doFilter(request, response):// enable bypass
        // Đây là danh sách cần phải convert sang dạng list


    }

    private boolean isBypassToken(HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                // Keys value danh sách đi qua ai cũng có thể xem
                Pair.of("/products","GET"),
                Pair.of("/categories","GET"),
                Pair.of("/users/register","POST"),
                Pair.of("/users/login","POST")
        );
        // Duyệt theo danh sách
//        for(Pair<String, String> bypassToken: bypassTokens) {
//            if (request.getServletPath().contains(bypassToken.getFirst()) &&
//                    request.getMethod().equals(bypassToken.getSecond())) {
//                filterChain
//            }
//        }
        return false;
    }
}
