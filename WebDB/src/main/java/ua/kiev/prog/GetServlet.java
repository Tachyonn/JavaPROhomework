package ua.kiev.prog;

import ua.kiev.prog.dao.ClientDAO;
import ua.kiev.prog.dao.ClientDAOImpl;
import ua.kiev.prog.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext()
                .getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        ClientDAO clientDAO = new ClientDAOImpl(em);

        List<Client> clientsList = clientDAO.selectAll();

//        Gson gson = new GsonBuilder().create();
//        String json = gson.toJson(clientsList);
//        resp.setContentType("application/json");
//        PrintWriter pw = resp.getWriter();
//        pw.println(json);

        req.setAttribute("clientsList", clientsList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}
