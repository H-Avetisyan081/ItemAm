package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private final Connection connection= DBConnectionProvider.getInstance().getConnection();

    public Category getCategoryById(int id){

            String sql = "SELECT * FROM my_item_am.category WHERE id=?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next())
                    return Category.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        String sql = "select * from item_am.category";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                categories.add(getCategoryFromResultSet(resultSet));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet){
        try {
            return Category.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
