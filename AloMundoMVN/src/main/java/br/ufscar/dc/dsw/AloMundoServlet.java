/*package br.ufscar.dc.dsw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/xconversao" })
public class AloMundoServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minS = req.getParameter("min");
        String maxS = req.getParameter("max");
        String stepS = req.getParameter("step");
        
        int min = ("".equals(minS)) ? -100 : Integer.parseInt(minS);
        int max = ("".equals(minS)) ? 100 : Integer.parseInt(maxS);
        int step = ("".equals(stepS)) ? 5 : Integer.parseInt(stepS);
        
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Celsius</th>");
            out.println("<th>Fahrenheit</th>");
            out.println("</tr>");
            for (int celsius = min; celsius <= max; celsius += step) {
                double fahr = 1.8 * celsius + 32;
                out.println("<tr>");
                out.println("<td>" + celsius + "</td>");
                out.println("<td>" + fahr + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
    }
}
*/