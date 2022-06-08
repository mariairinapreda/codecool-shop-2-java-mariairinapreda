package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDaoDB implements OrderDao {
    private final DataSource dataSource;
    private static OrderDaoDB instance=null;

    public static OrderDaoDB getInstance(DataSource dataSource){
        if(instance==null)instance=new OrderDaoDB(dataSource);
        return instance;
    }

    public OrderDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO orders (date, status, total_price, product_list) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            st.setString(1, dtf.format(now));
            st.setBoolean(2, order.isPayed());
//            st.setInt(3, order.getTotalPrice());
//            st.setString(4,);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new order.", throwables);
        }

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
