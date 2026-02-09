package com.example.boardv1._core.filter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class FirstFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // 요청 전 처리
        // System.out.println("Before Filter---- first");
        // PrintWriter pw = response.getWriter();
        // pw.println("block");

        chain.doFilter(request, response);
    }

}
