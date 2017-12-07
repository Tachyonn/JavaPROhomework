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
import java.util.Arrays;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext()
                .getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        ClientDAO clientDAO = new ClientDAOImpl(em);

        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        String[] ids = req.getParameterValues("toDelete");
        if (ids.length == 0) {
            pw.println("No checkboxes were selected!");
            pw.println("<p><a href=\"/\">Go back</a></p>");
            return;
        }
        for (int i = 0; i < ids.length; i++) {
            Long currentID = Long.parseLong(ids[i].replaceAll("[^0-9?!\\.]", ""));
            clientDAO.delete(currentID);
        }

        pw.println(Arrays.deepToString(ids));
        pw.println("Deleted!");
        pw.println("<p><a href=\"/get\">Go back</a></p>");

    }
}
