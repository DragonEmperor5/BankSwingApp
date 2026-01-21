package controller;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class RegisterController {

    public static boolean register(
            String username,
            String password,
            String nama,
            String email
    ) {

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // TRANSACTION

            // 1. Insert user
            String sqlUser = """
                INSERT INTO users (username, password, nama_lengkap, email)
                VALUES (?, ?, ?, ?)
            """;
            PreparedStatement psUser =
                    conn.prepareStatement(sqlUser, PreparedStatement.RETURN_GENERATED_KEYS);

            psUser.setString(1, username);
            psUser.setString(2, password);
            psUser.setString(3, nama);
            psUser.setString(4, email);
            psUser.executeUpdate();

            // 2. Ambil ID user
            ResultSet rs = psUser.getGeneratedKeys();
            if (!rs.next()) return false;

            int idUser = rs.getInt(1);

            // 3. Generate nomor rekening
            String noRekening = generateNoRekening();

            // 4. Insert account
            String sqlAccount = """
                INSERT INTO accounts (id_user, no_rekening, saldo)
                VALUES (?, ?, 0)
            """;
            PreparedStatement psAcc = conn.prepareStatement(sqlAccount);
            psAcc.setInt(1, idUser);
            psAcc.setString(2, noRekening);
            psAcc.executeUpdate();

            conn.commit(); // SUCCESS
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

        return false;
    }

    private static String generateNoRekening() {
        Random r = new Random();
        return "10" + (10000000 + r.nextInt(90000000));
    }
}
