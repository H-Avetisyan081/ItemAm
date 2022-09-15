package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    StringBuilder message = new StringBuilder();
    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        if (name == null || name.length() == 0) {
            message.append("name field is required <br>");
        }
        if (surname == null || surname.length() == 0) {
            message.append("surname field is required <br>");
        }
        if (email == null || email.length() == 0) {
            message.append("email field is required <br>");
        }
        if (password == null || password.length() == 0) {
            message.append("password field is required <br>");
        }

        if (userManager.getUserByEmailAndPassword(email, password) == null) {
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .phone(phone)
                    .build();
            userManager.addUser(user);
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }

}

