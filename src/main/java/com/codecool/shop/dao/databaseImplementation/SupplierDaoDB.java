package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoDB implements SupplierDao {
    private final DataSource dataSource;
    private static SupplierDaoDB instance = null;

    public static SupplierDaoDB getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new SupplierDaoDB(dataSource);
        }
        return instance;

    }

    public SupplierDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        boolean condition = false;
        for (Supplier supplier1 : getAll()) {
            if (supplier1.getName() == supplier.getName()) {
                condition = true;
            }
        }
        try (Connection conn = dataSource.getConnection()) {
            if (!condition) {
                String sql = "INSERT INTO suppliers (name) VALUES (?)";
                PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setString(1, supplier.getName());
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.next();
                supplier.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new supplier.", throwables);
        }

    }

    @Override
    public Supplier find(int id) {
        return getAll().stream().filter(supplier -> supplier.getId() == id).findFirst().get();
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name FROM suppliers";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Supplier supplier = new Supplier(name, "");
                supplier.setId(id);
                result.add(supplier);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
