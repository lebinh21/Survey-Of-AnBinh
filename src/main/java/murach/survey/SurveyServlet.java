package murach.survey;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import murach.business.User;

public class SurveyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {
        
        // Đảm bảo request đọc UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        String url = "/index.html";

        if (action.equals("join")) {
            url = "/index.html";
        }
        else if (action.equals("add")) {
            // Lấy dữ liệu từ form
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String heardFrom = request.getParameter("heardFrom");

            boolean wantsUpdates = request.getParameter("wantsUpdates") != null;
            boolean emailOK = request.getParameter("emailOK") != null;
            String contactVia = request.getParameter("contactVia");

            // Tạo User object
            User user = new User(firstName, lastName, email, dateOfBirth,
                                 heardFrom, wantsUpdates, emailOK, contactVia);

            // Lưu vào request
            request.setAttribute("user", user);

            url = "/thanks.jsp";
        }

        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {
        doPost(request, response);
    }
}