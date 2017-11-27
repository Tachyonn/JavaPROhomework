import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResultsServlet", urlPatterns = "/sendresults")
public class ResultsServlet extends HttpServlet {
//    private final String TEMPLATE = "<!DOCTYPE html><html><head><meta charset=utf-8>" +
//            "<title>Results</title></head><body>";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Results results = Results.getInstance();

        String q1result = req.getParameter("appsrv");
        String q2result = req.getParameter("usemethod");
        String voterName = req.getParameter("username");
        String voterAge = req.getParameter("age");
        String sessionID = req.getSession().getId();

        results.addResult(q1result);
        results.addResult(q2result);
        results.addVoter(voterName, voterAge, sessionID);

        req.setAttribute("resultsList", results.getResults());
        req.setAttribute("votersList", results.getVoters());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

//        PrintWriter pw = resp.getWriter();
//        String response = TEMPLATE + results.toString() + "<br>" + results.votersToSting() + "</body></html>";
//        pw.println(response);
//        pw.close();
    }
}
