package controller;

import config.DatabaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    // Return type berubah dari 'boolean' menjadi 'User'
    public static User login(String username, String password) {
        User user = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Bungkus data database ke dalam Object Model User
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setNamaLengkap(rs.getString("nama_lengkap"));
                user.setEmail(rs.getString("email"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}