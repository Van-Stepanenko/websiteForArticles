package com.webPlusJava.demo.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCookiesServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
    protected void doGet (HttpServletRequest request, HttpServletResponse response){
       Cookie[] cookies = request.getCookies();

       for (Cookie cookie : cookies){

       }

    }
}
