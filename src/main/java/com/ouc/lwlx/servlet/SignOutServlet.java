 package com.ouc.lwlx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wlli
 * @date 2021/07/26
 */
 @WebServlet(urlPatterns = "/signout")
 public class SignOutServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // 从HttpSession移除用户名:
         req.getSession().removeAttribute("user");
         resp.sendRedirect("/");
     }
 }
