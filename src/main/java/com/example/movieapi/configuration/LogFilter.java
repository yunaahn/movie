package com.example.movieapi.configuration;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      log.info("LogFilter init");
    }

    //http 요청 -> doFilter가 호출됨
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        log.info("[{}] LogFilter doFilter Start", requestURI);

        try {
            chain.doFilter(request, response);
        } finally {
            log.info("[{}] LogFilter doFilter End", requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy");
    }
}
