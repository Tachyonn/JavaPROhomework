package ua.kiev.prog;

import ua.kiev.prog.dao.ClientDAO;
import ua.kiev.prog.dao.ClientDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext()
                .getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        ClientDAO clientDAO = new ClientDAOImpl(em);

        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        clientDAO.add(name, age);
        PrintWriter pw = resp.getWriter();
        pw.print("<a href=\"/\">OK!");
    }
}
