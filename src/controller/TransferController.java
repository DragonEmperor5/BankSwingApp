package src.controller;

import src.config.DatabaseConnection;
import src.util.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransferController {

    public static boolean transfer(String rekeningTujuan, double jumlah) {

        if (jumlah <= 0) return false;

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // TRANSACTION START

            // 1. Ambil rekening pengirim
            String sqlSender = """
                SELECT id_account, no_rekening, saldo
                FROM accounts
                WHERE id_user = ?
            """;
            PreparedStatement psSender = conn.prepareStatement(sqlSender);
            psSender.setInt(1, Session.idUser);
            ResultSet rsSender = psSender.executeQuery();

            if (!rsSender.next()) return false;

            int senderId = rsSender.getInt("id_account");
            String rekeningPengirim = rsSender.getString("no_rekening");
            double saldoPengirim = rsSender.getDouble("saldo");

            if (saldoPengirim < jumlah) return false;

            // 2. Ambil rekening penerima
            String sqlReceiver = """
                SELECT id_account
                FROM accounts
                WHERE no_rekening = ?
            """;
            PreparedStatement psReceiver = conn.prepareStatement(sqlReceiver);
            psReceiver.setString(1, rekeningTujuan);
            ResultSet rsReceiver = psReceiver.executeQuery();

            if (!rsReceiver.next()) return false;

            int receiverId = rsReceiver.getInt("id_account");

            // 3. Kurangi saldo pengirim
            PreparedStatement psKurang = conn.prepareStatement(
                    "UPDATE accounts SET saldo = saldo - ? WHERE id_account = ?");
            psKurang.setDouble(1, jumlah);
            psKurang.setInt(2, senderId);
            psKurang.executeUpdate();

            // 4. Tambah saldo penerima
            PreparedStatement psTambah = conn.prepareStatement(
                    "UPDATE accounts SET saldo = saldo + ? WHERE id_account = ?");
            psTambah.setDouble(1, jumlah);
            psTambah.setInt(2, receiverId);
            psTambah.executeUpdate();

            // 5. Catat transaksi keluar
            PreparedStatement psOut = conn.prepareStatement(
                    "INSERT INTO transactions (id_account, tipe, jumlah, keterangan) VALUES (?, 'TRANSFER_KELUAR', ?, ?)");
            psOut.setInt(1, senderId);
            psOut.setDouble(2, jumlah);
            psOut.setString(3, "Transfer ke " + rekeningTujuan);
            psOut.executeUpdate();

            // 6. Catat transaksi masuk
            PreparedStatement psIn = conn.prepareStatement(
                    "INSERT INTO transactions (id_account, tipe, jumlah, keterangan) VALUES (?, 'TRANSFER_MASUK', ?, ?)");
            psIn.setInt(1, receiverId);
            psIn.setDouble(2, jumlah);
            psIn.setString(3, "Transfer dari " + rekeningPengirim);
            psIn.executeUpdate();

            // 7. Catat tabel transfer
            PreparedStatement psTransfer = conn.prepareStatement(
                    "INSERT INTO transfers (rekening_pengirim, rekening_penerima, jumlah) VALUES (?, ?, ?)");
            psTransfer.setString(1, rekeningPengirim);
            psTransfer.setString(2, rekeningTujuan);
            psTransfer.setDouble(3, jumlah);
            psTransfer.executeUpdate();

            conn.commit(); // TRANSACTION SUCCESS
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
}
