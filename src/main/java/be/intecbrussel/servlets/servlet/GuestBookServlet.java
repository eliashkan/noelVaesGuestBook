package be.intecbrussel.servlets.servlet;

import be.intecbrussel.servlets.dao.MessageDAO;
import be.intecbrussel.servlets.model.MessageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {

    private MessageDAO messageDAO;

    @Override
    public void init() throws ServletException {
        messageDAO = new MessageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // returns list of messages from guestbook database
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        ArrayList<MessageBean> list = messageDAO.getMessagesFromGuestbook();
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<html><head><title>Guestbook");
            writer.println("</title></head><body>");
            writer.println("<table><tr>" +
                    "<th>Date & Time</th>" +
                    "<th>Name</th>" +
                    "<th>Message</th></tr>");
            list.forEach(item -> {
                String dateTime = item.getLocalDateTime().toString();
                String name = item.getName();
                String message = item.getMessage();
                writer.println("<tr><td>" + dateTime + "</td>" +
                        "<td>" + name + "</td>" +
                        "<td>" + message + "</td></tr>");
            });
            writer.println("</table></body></html>");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // posts a message to the guestbook database
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        MessageBean message = new MessageBean();
        message.setName(req.getParameter("postName"));
        message.setMessage(req.getParameter("postMessage"));

        messageDAO.createMessage(message);

        try (PrintWriter writer = resp.getWriter()) {
            writer.println("Succesfully posted!");
        }
    }
}
