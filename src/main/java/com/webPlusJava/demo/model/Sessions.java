package com.webPlusJava.demo.model;


import java.io.IOException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.persistence.Id;

//https://www.youtube.com/watch?v=4LCZ2svvUFU&ab_channel=alishev


    @WebServlet("/authorization")
    public class Sessions extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            HttpSession session = request.getSession();
            Long id = Long.valueOf(request.getParameter("id"));

            UserInformation userInformation = (UserInformation)session.getAttribute("userInformation");
            if ( userInformation == null){
                userInformation.setIdUser(id);
            }

            session.setAttribute("userInformation", userInformation);
            System.out.println(userInformation);

         /*   PrintWriter out = response.getWriter();
            try {
                // если объект ранее не установлен
                if(name == null) {
                    // устанавливаем объект с ключом name
                    session.setAttribute("name", "Tom Soyer");
                    out.println("Session data are set");
                }
                else {
                    out.println("Name: " + name);
                    // удаляем объект с ключом name
                    session.removeAttribute("name");
                }
            }
            finally {
                out.close();
            }   */
        }
    }

