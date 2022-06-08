package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ProductDaoDB implements ProductDao {
    private final DataSource dataSource;
    private static ProductDaoDB instance=null;

    public static ProductDaoDB getInstance(DataSource dataSource){
        if(instance==null)instance=new ProductDaoDB(dataSource);
        return instance;
    }

    public ProductDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO products (name, description, price, currency, categoryId, supplierId) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setBigDecimal(3, product.getDefaultPrice());
            st.setString(4, String.valueOf(product.getDefaultCurrency()));
            st.setInt(5, product.getProductCategory().getId());
            st.setInt(6, product.getSupplier().getId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new product.", throwables);
        }

    }


    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public Product getByName(String name) {
        return null;
    }
}
