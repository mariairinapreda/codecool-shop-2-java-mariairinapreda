package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoDB implements UserDao {
    private final DataSource dataSource;
    private static UserDaoDB instance=null;

    public static UserDaoDB getInstance(DataSource dataSource){
        if(instance==null)instance=new UserDaoDB(dataSource);
        return instance;
    }

    public UserDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO users (name, address, email, phone, password, city, state, zipcode, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getName());
            st.setString(2, user.getAddress());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPhone());
            st.setString(5, user.getPassword());
            st.setString(6, user.getCity());
            st.setString(7, user.getState());
            st.setString(8, user.getZipCode());
            st.setString(9, user.getUserStatus().toString());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new user.", throwables);
        }

    }

    @Override
    public User find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT name, address, email, phone, password, city, state, zipcode FROM users WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, String.valueOf(id));
            ResultSet rs = st.getGeneratedKeys();
            if(!rs.next()){
                return null;
            }
            rs.next();
            String name = rs.getString(1);
            String address = rs.getString(2);
            String email = rs.getString(3);
            String phone = rs.getString(4);
            String password = rs.getString(5);
            String city = rs.getString(6);
            String state = rs.getString(7);
            String zipcode = rs.getString(8);
            User user = new User(name, address, email, phone, password, city, state, zipcode);
            user.setId(id);
            return user;
        }catch (SQLException throwables) {
            throw new RuntimeException("Error while finding this user.", throwables);
        }

    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, String.valueOf(id));
            st.executeUpdate();
        }catch (SQLException throwables) {
            throw new RuntimeException("Error while removing this user.", throwables);
        }
    }

    @Override
    public boolean isLoggedIn(String email) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT status FROM users WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, String.valueOf(email));
            ResultSet rs = st.getGeneratedKeys();
            if(!rs.next()){
                return false;
            }
            rs.next();
            return Boolean.parseBoolean(rs.getString(1));
        }catch (SQLException throwables) {
            throw new RuntimeException("Error while finding user info.", throwables);
        }
    }

    @Override
    public void setPaymentMethod(String paypal) {


    }


    @Override
    public void updateStatus(String status, int id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE users SET status = ? WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, id);
            st.executeUpdate();
        }catch (SQLException throwables) {
            throw new RuntimeException("Error while updating user.", throwables);
        }
    }
}
