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
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class MainServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catIdStr = req.getParameter("catId");
        List<Item> items;
        if (catIdStr == null || catIdStr.equals("")) {
            items = itemManager.getLast20Items();
        } else {
            int id = Integer.parseInt(catIdStr);
            items = itemManager.getLast20ItemsByCategory(id);
        }
        req.setAttribute("items", items);
        req.setAttribute("cat_id", categoryManager.getAllCategories());

        req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }
}
