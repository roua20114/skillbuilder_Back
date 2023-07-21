//package com.example.demo.config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
///**
// * @author Nabil Jarrai
// * @since 21/07/2023
// */
//public class CorsFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
//        httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With, X-Auth-Token");       chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
