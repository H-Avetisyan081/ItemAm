package manager;

import db.DBConnectionProvider;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    UserManager userManager = new UserManager();
    CategoryManager categoryManager = new CategoryManager();

    public boolean addItem(Item item) {

        String sql = "insert into item_am.item(title,price,description,pic_url,user_id,category_id) values(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.setString(4, item.getPicUrl());
            statement.setInt(5, item.getUser().getId());
            statement.setInt(6, item.getCategory().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                item.setId(resultSet.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Item> getAllItems() {
        String sql = "select * from item_am.item";
        return getItems(sql);
    }

    public List<Item> getLast20Items() {
        String sql = "select * from item_am.item order by id desc limit 20";
        return getItems(sql);
    }

    public List<Item> getLast20ItemsByCategory(int categoryId) {
        String sql = "select * from item_am.item where category_id=? order by id desc limit 20";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    private List<Item> getItems(String sql) {
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemByCategoryId(int id) {
        String sql = "select * from item_am.item where category_id=?";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getUsersAllItems(User user) {
        String sql = "select * from item_am.item where user_id=?";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteItemById(int id) {
        String sql = "delete from item_am.item where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItemById(int id) {
        String sql = "select * from item_am.item where id =?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private Item getItemFromResultSet(ResultSet resultSet) {
        try {
            return Item.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .price(resultSet.getDouble(3))
                    .description(resultSet.getString(4))
                    .user(userManager.getUserById(resultSet.getInt(5)))
                    .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                    .picUrl(resultSet.getString(7))

                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
