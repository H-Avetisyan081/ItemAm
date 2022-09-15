package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10 * 5)
public class AddItemServlet extends HttpServlet {
    CategoryManager categoryManager = new CategoryManager();
    ItemManager itemManager = new ItemManager();
    private final String UPLOAD_DIR;

    {
        UPLOAD_DIR = "C:\\Users\\Hovo\\IdeaProjects\\Item.AM\\web\\image";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addItem.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        Category category = categoryManager.getCategoryById(Integer.parseInt(req.getParameter("category")));
        User user =(User) req.getSession().getAttribute("user");

        Item item = new Item();

        item.setTitle(title);
        item.setPrice(price);
        item.setDescription(description);
        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                item.setPicUrl(fileName);
            }
        }
        item.setUser(user);
        item.setCategory(category);

        if (itemManager.addItem(item)) {
            resp.sendRedirect("/userHome");
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }
}
