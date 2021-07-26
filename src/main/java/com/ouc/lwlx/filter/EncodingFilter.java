 package com.ouc.lwlx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author wlli
 * @date 2021/07/26
 */
 @WebFilter(urlPatterns = "/*")
 public class EncodingFilter implements Filter {
     @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {
         System.out.println("EncodingFilter:doFilter");
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         chain.doFilter(request, response);
     }
 }
