package com.webPlusJava.demo.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookiesServlet  extends HttpServlet{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
    protected void doGet (HttpServletRequest request, HttpServletResponse response){
        Cookie cookie1 = new Cookie("some_id", "123");
        cookie1.setMaxAge(100); //время хранения куки на браузере

        response.addCookie(cookie1);// передает браузеру куки
    }



}
