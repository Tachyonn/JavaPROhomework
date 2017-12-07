package ua.kiev.prog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //...
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext()
                .getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        try {
            //...
        } finally {
            em.close();
        }
    }
}
