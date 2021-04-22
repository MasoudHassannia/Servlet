package com.example.Servlet;

import entities.EmployeeDao;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteUser", urlPatterns = "/DeleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));

        response.getWriter().println("id");
        response.getWriter().println(id);
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        EmployeeDao employeeDao = new EmployeeDao(em);
        employeeDao.remove(employeeDao.loadById(id));
        request.getRequestDispatcher("/view").forward(request,response);

    }


}
