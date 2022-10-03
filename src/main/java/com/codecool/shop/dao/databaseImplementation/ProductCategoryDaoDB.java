package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductCategoryDaoDB implements ProductCategoryDao {
    private final DataSource dataSource;
    private static ProductCategoryDaoDB instance = null;

    public static ProductCategoryDaoDB getInstance(DataSource dataSource) {
        if (instance == null) instance = new ProductCategoryDaoDB(dataSource);
        return instance;
    }

    public ProductCategoryDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        List<ProductCategory> categories = getAll();
        boolean condition = false;
        for (ProductCategory productCategory : categories) {
            if (Objects.equals(productCategory.getName(), category.getName())) {
                condition = true;
            }
        }
        try (Connection conn = dataSource.getConnection()) {
            if (!condition) {
                String sql = "INSERT INTO categories (name) VALUES (?)";
                PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setString(1, category.getName());
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
                category.setId(rs.getInt(1));
            }

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding category.", throwables);
        }

    }

    @Override
    public ProductCategory find(int id) {
        return getAll().stream().filter(productCategory -> productCategory.getId() == id).findFirst().get();

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,name FROM categories";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductCategory> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory(rs.getString(2), "department", "description");
                productCategory.setId(rs.getInt(1));
                result.add(productCategory);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all categories", e);
        }
    }
}
