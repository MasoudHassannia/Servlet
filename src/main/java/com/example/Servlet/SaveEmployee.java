package com.example.Servlet;

import entities.Employee;
import entities.EmployeeDao;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SaveEmployee",urlPatterns = "/save")
public class SaveEmployee extends HttpServlet {
    private EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("fName");
        String lastName = request.getParameter("lName");
        String email = request.getParameter("email");

        em.getTransaction().begin();
        EmployeeDao employeeDao = new EmployeeDao(em);
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employeeDao.save(employee);
        em.getTransaction().commit();



        out.println("<p> Employee Saves!!</p>");
        request.getRequestDispatcher("/index.jsp").include(request,response);


        out.close();
    }


}
