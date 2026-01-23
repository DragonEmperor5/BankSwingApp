package controller;

import config.DatabaseConnection;
import model.Account; // Import Model Account
import util.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountController {

    // Ambil Data Account
    public static Account getAccountDetail() {
        Account acc = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM accounts WHERE id_user = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Session.idUser); // Ambil berdasarkan user yg login

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setIdAccount(rs.getInt("id_account"));
                acc.setIdUser(rs.getInt("id_user"));
                acc.setNoRekening(rs.getString("no_rekening"));
                acc.setSaldo(rs.getDouble("saldo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    // Method getSaldo
    public static double getSaldo() {
        Account acc = getAccountDetail();
        return (acc != null) ? acc.getSaldo() : 0;
    }

    // Setor Tunai
    public static boolean setorTunai(double jumlah) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET saldo = saldo + ? WHERE id_user = ?");
            ps.setDouble(1, jumlah);
            ps.setInt(2, Session.idUser);
            
            if (ps.executeUpdate() > 0) {
                Account acc = getAccountDetail();
                TransactionController.addTransaction(acc.getIdAccount(), "SETOR", jumlah, "Setor Tunai");
                return true;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // Tarik Tunai
    public static boolean tarikTunai(double jumlah) {
        try {
            Account acc = getAccountDetail();
            if (acc == null || acc.getSaldo() < jumlah) return false;

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET saldo = saldo - ? WHERE id_user = ?");
            ps.setDouble(1, jumlah);
            ps.setInt(2, Session.idUser);

            if (ps.executeUpdate() > 0) {
                TransactionController.addTransaction(acc.getIdAccount(), "TARIK", jumlah, "Tarik Tunai");
                return true;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}