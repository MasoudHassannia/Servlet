package com.example.Servlet;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/view")
public class ViewEmployee extends HttpServlet {
    private EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<a href='index.jsp'>Add New Employee</a>");
        out.println("<h1>Employees List</h1>");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
        Root<Employee> from = criteriaQuery.from(Employee.class);
        criteriaQuery.select(from);
        TypedQuery<Employee> typedQuery = em.createQuery(criteriaQuery);
        List<Employee> list = typedQuery.getResultList();

        out.print("<table border='2' width='50%'");
        out.print("<tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Edit</th><th>Delete</th></tr>");
        
        for (Employee emp : list ) {
            out.print("<tr><td>"+emp.getId()+"</td><td>"+emp.getFirstName()+"</td><td>"+emp.getLastName()+
                    "</td><td>"+emp.getEmail()+
                    "</td></td><td><a href='EditUser?id=\"+emp.getId()+\"'>edit</a></td>" +
                    "<td><a href='DeleteUser'>delete</a></td></tr>");
            
        }
        out.print("</table>");
    }


}
