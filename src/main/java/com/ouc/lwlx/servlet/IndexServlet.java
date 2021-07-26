 package com.ouc.lwlx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wlli
 * @date 2021/07/26
 */
 @WebServlet(urlPatterns = "/")
 public class IndexServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // 从HttpSession获取当前用户名:
         String cookie = parseVarFromCookie(req);
         String user = (String)req.getSession().getAttribute("user");
         if (!cookie.equals(user)) {
             resp.sendRedirect("/signin");
             return;
         }

         resp.setContentType("text/html");
         resp.setCharacterEncoding("UTF-8");
         resp.setHeader("X-Powered-By", "JavaEE Servlet");
         PrintWriter pw = resp.getWriter();
         pw.write("<h1>Welcome, " + (user != null ? user : "Guest") + "</h1>");
         if (user == null) {
             // 未登录，显示登录链接:
             pw.write("<p><a href=\"/signin\">Sign In</a></p>");
         } else {
             // 已登录，显示登出链接:
             pw.write("<p><a href=\"/signout\">Sign Out</a></p>");
         }
         pw.flush();
     }

     private String parseVarFromCookie(HttpServletRequest req) {
         // 获取请求附带的所有Cookie:
         Cookie[] cookies = req.getCookies();
         // 如果获取到Cookie:
         if (cookies != null) {
             // 循环每个Cookie:
             for (Cookie cookie : cookies) {
                 // 如果Cookie名称为lang:
                 if (cookie.getName().equals("user")) {
                     // 返回Cookie的值:
                     return cookie.getValue();
                 }
             }
         }
         // 返回默认值:
         return "";
     }
 }
