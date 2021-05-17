package com.example.rest_ee;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class UserRepository {
    Connection con = null;


    public UserRepository() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","qwerty");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                User u = new User(rs.getInt(1),rs.getString(2), rs.getString(3));
                users.add(u);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return users;
    }

    public User getUser(int id){
        String sql = "SELECT * FROM users WHERE id = "+ id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                return new User(rs.getInt(1),rs.getString(2), rs.getString(3));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return new User();
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getId());
            st.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
