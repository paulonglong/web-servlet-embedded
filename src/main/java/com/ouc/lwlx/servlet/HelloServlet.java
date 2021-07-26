 package com.ouc.lwlx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wlli
 * @date 2021/07/26
 */
@WebServlet(urlPatterns = "/hello")
 public class HelloServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         resp.setContentType("text/html");
         String name = req.getParameter("name");
         if (name == null) {
             name = "world";
         }
         PrintWriter pw = resp.getWriter();
         pw.write("<h1>Hello, " + name + "!</h1>");
         pw.flush();
     }
 }
