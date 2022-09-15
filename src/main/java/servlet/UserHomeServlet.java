package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {
    ItemManager itemManager = new ItemManager();
    CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");


        List<Item> itemsOfUserId = itemManager.getUsersAllItems(user);

        if (itemsOfUserId != null) {
            req.setAttribute("name", user.getName());
            req.setAttribute("surname", user.getSurname());
            req.setAttribute("items", itemsOfUserId);
            req.setAttribute("categories", categoryManager.getAllCategories());
        } else {
            req.setAttribute("name", user.getName());
            req.setAttribute("surname", user.getSurname());
            req.setAttribute("categories", categoryManager.getAllCategories());
        }

        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}