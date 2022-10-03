package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class LineItemDaoDB implements CartDao {
    private final DataSource datasource;
    private final UserDao userDao;
    private final ProductDao productDao;
    private static LineItemDaoDB instance = null;


    public static LineItemDaoDB getInstance(DataSource datasource, UserDao userDao, ProductDao productDao) {
        if (instance == null) instance = new LineItemDaoDB(datasource, userDao, productDao);
        return instance;
    }

    public LineItemDaoDB(DataSource datasource, UserDao userDao, ProductDao productDao) {
        this.datasource = datasource;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    @Override
    public void add(LineItem lineItem) {

    }

    public void add(List<LineItem> lineItems) {
        HashMap<LineItem, Integer> lineItemList = getAllWithUserid();
        try (Connection conn = datasource.getConnection()) {
            for (LineItem lineItem : lineItems) {
                if (lineItemList.containsKey(lineItem) && lineItemList.get(lineItem) == userDao.getLastUser().getId()) {
                } else {
                    String sql = "INSERT INTO line_items (quantity, price, productid, user_id) VALUES (?,?,?,?)";
                    PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    st.setInt(1, lineItem.getQuantity());
                    st.setString(2, lineItem.getPrice());
                    st.setInt(3, lineItem.getProduct().getId());
                    st.setInt(4, userDao.getLastUser().getId());
                    st.executeUpdate();
                    ResultSet rs = st.getGeneratedKeys();
                    rs.next();
                    lineItem.setId(rs.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new item.", throwables);
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
    public List<LineItem> getAll() {
        return null;
    }

    public HashMap<LineItem, Integer> getAllWithUserid() {
        try (Connection conn = datasource.getConnection()) {
            String sql = "SELECT * FROM line_items";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            HashMap<LineItem, Integer> result = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                int quantity = rs.getInt(2);
                String price = rs.getString(3);
                int productId = rs.getInt(4);
                int userId = rs.getInt(5);
                Product product = productDao.find(productId);
                LineItem lineItem = new LineItem(quantity, price, product);
                lineItem.setId(id);
                result.putIfAbsent(lineItem, userId);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
