package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/singleItem")
public class SingleItemServlet extends HttpServlet {
    ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Item itemById = itemManager.getItemById(id);

        if (itemById== null){
            resp.sendRedirect("/home");
        }else {
            req.setAttribute("item",itemById);
            req.getRequestDispatcher("/singleItem.jsp").forward(req,resp);
        }

    }
}
