package manager;

import db.DBConnectionProvider;
import model.Image;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    ItemManager itemManager = new ItemManager();

    public boolean addImages(Image image) {
        String sql = "insert into item_am.images(path,item_id) values(?,?)";
        List<Image> images = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, image.getPath());
            statement.setInt(2, image.getItem().getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                image.setId(resultSet.getInt(1));
                images.add(getImageFromResultSet(resultSet));
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Image> getAllImagesByItemID(int itemId) {
        String sql = "select* from item_am.images where item_id=?";
        List<Image> images = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                images.add(getImageFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    private Image getImageFromResultSet(ResultSet resultSet) {
        try {
            return Image.builder()
                    .id(resultSet.getInt(1))
                    .path(resultSet.getString(2))
                    .item(itemManager.getItemById(resultSet.getInt(3)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
