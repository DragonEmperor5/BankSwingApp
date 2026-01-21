package controller;

import config.DatabaseConnection;
import util.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountController {

    // ambil saldo
    public static double getSaldo() {
        double saldo = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT saldo FROM accounts WHERE id_user=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Session.idUser);
            var rs = ps.executeQuery();
            if (rs.next()) saldo = rs.getDouble("saldo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saldo;
    }

    // SETOR TUNAI
    public static boolean setorTunai(double jumlah) {
        if (jumlah <= 0) return false;

        try {
            Connection conn = DatabaseConnection.getConnection();

            // update saldo
            String sqlUpdate = """
                UPDATE accounts
                SET saldo = saldo + ?
                WHERE id_user = ?
            """;
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, jumlah);
            psUpdate.setInt(2, Session.idUser);
            psUpdate.executeUpdate();

            // catat transaksi
            String sqlInsert = """
                INSERT INTO transactions (id_account, tipe, jumlah, keterangan)
                SELECT id_account, 'SETOR', ?, 'Setor Tunai'
                FROM accounts
                WHERE id_user = ?
            """;
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            psInsert.setDouble(1, jumlah);
            psInsert.setInt(2, Session.idUser);
            psInsert.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

// TARIK TUNAI
public static boolean tarikTunai(double jumlah) {
    if (jumlah <= 0) return false;

    // Cek saldo
    double saldoSekarang = getSaldo();
    if (saldoSekarang < jumlah) {
        return false; // Saldo tidak cukup
    }

    try {
        Connection conn = DatabaseConnection.getConnection();

        // Kurangi Saldo
        String sqlUpdate = "UPDATE accounts SET saldo = saldo - ? WHERE id_user = ?";
        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
        psUpdate.setDouble(1, jumlah);
        psUpdate.setInt(2, Session.idUser);
        int rows = psUpdate.executeUpdate();

        if (rows > 0) {
            // Catat Transaksi jika update saldo berhasil
            String sqlInsert = """
                INSERT INTO transactions (id_account, tipe, jumlah, keterangan)
                SELECT id_account, 'TARIK', ?, 'Tarik Tunai'
                FROM accounts WHERE id_user = ?
            """;
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            psInsert.setDouble(1, jumlah);
            psInsert.setInt(2, Session.idUser);
            psInsert.executeUpdate();
            
            return true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}
