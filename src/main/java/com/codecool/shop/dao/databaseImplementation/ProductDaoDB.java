package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDaoDB implements ProductDao {
    private final DataSource dataSource;
    private final ProductCategoryDaoDB productCategoryDaoDB;
    private final SupplierDaoDB supplier;
    private static ProductDaoDB instance = null;

    public static ProductDaoDB getInstance(DataSource dataSource, ProductCategoryDaoDB productCategoryDaoDB, SupplierDaoDB supplier) {
        if (instance == null) instance = new ProductDaoDB(dataSource, productCategoryDaoDB, supplier);
        return instance;
    }

    public ProductDaoDB(DataSource dataSource, ProductCategoryDaoDB productCategoryDaoDB, SupplierDaoDB supplier) {
        this.dataSource = dataSource;
        this.productCategoryDaoDB = productCategoryDaoDB;
        this.supplier = supplier;
    }

    @Override
    public void add(Product product) {
        boolean condition = false;
        for (Product product1 : getAll()) {
            if (Objects.equals(product1.getName(), product.getName())) {
                condition = true;
            }
        }
        try (Connection conn = dataSource.getConnection()) {
            if (!condition) {
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
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new product.", throwables);
        }

    }


    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description, price, currency, categoryid, supplierid FROM products WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String name = rs.getString(1);
            String description = rs.getString(2);
            BigDecimal price = rs.getBigDecimal(3);
            String currency = rs.getString(4);
            int categoryId = rs.getInt(5);
            int supplierId = rs.getInt(6);
            ProductCategory category = productCategoryDaoDB.find(categoryId);
            Supplier theSupplier = supplier.find(supplierId);
            Product product = new Product(name, price, currency, description, category, theSupplier);
            product.setId(id);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name,  price,currency,description,  categoryid, supplierid FROM products";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                Product product = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5), productCategoryDaoDB.find(rs.getInt(6)), supplier.find(rs.getInt(7)));
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all products", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier theSupplier) {
        List<Product> products = getAll();
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getSupplier().getId() == theSupplier.getId()) {
                results.add(product);
            }
        }
        return results;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = getAll();
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductCategory().getId() == productCategory.getId()) {
                results.add(product);
            }
        }
        return results;
    }

    @Override
    public Product getByName(String name) {
        return getAll().stream().filter(product -> product.getName().equals(name)).findAny().get();

    }
}
