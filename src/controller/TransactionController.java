package controller;

import config.DatabaseConnection;
import model.Transaction;
import util.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    // Fungsi Mencatat Transaksi
    public static void addTransaction(int idAccount, String tipe, double jumlah, String keterangan) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO transactions (id_account, tipe, jumlah, keterangan) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAccount);
            ps.setString(2, tipe);
            ps.setDouble(3, jumlah);
            ps.setString(4, keterangan);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fungsi Ambil History (List of Transaction)
    public static List<Transaction> getHistory() {
        List<Transaction> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = """
                SELECT t.* FROM transactions t
                JOIN accounts a ON t.id_account = a.id_account
                WHERE a.id_user = ?
                ORDER BY t.tanggal DESC
            """;
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Session.idUser);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setIdTransaction(rs.getInt("id_transaction"));
                t.setIdAccount(rs.getInt("id_account"));
                t.setTipe(rs.getString("tipe"));
                t.setJumlah(rs.getDouble("jumlah"));
                t.setKeterangan(rs.getString("keterangan"));
                t.setTanggal(rs.getTimestamp("tanggal"));
                
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}