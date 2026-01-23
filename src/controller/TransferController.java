package controller;

import config.DatabaseConnection;
import util.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransferController {

    public static boolean transfer(String rekeningTujuan, double jumlah) {
        if (jumlah <= 0) return false;

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 

            // Cek Pengirim
            String sqlSender = "SELECT id_account, no_rekening, saldo FROM accounts WHERE id_user = ?";
            PreparedStatement psSender = conn.prepareStatement(sqlSender);
            psSender.setInt(1, Session.idUser);
            ResultSet rsSender = psSender.executeQuery();

            if (!rsSender.next()) return false;

            int senderId = rsSender.getInt("id_account");
            String rekeningPengirim = rsSender.getString("no_rekening");
            double saldoPengirim = rsSender.getDouble("saldo");

            if (saldoPengirim < jumlah) return false;

            // Cek Penerima
            if (rekeningPengirim.equals(rekeningTujuan)) return false;

            String sqlReceiver = "SELECT id_account FROM accounts WHERE no_rekening = ?";
            PreparedStatement psReceiver = conn.prepareStatement(sqlReceiver);
            psReceiver.setString(1, rekeningTujuan);
            ResultSet rsReceiver = psReceiver.executeQuery();

            if (!rsReceiver.next()) return false;

            int receiverId = rsReceiver.getInt("id_account");

            // Update Saldo
            PreparedStatement psKurang = conn.prepareStatement("UPDATE accounts SET saldo = saldo - ? WHERE id_account = ?");
            psKurang.setDouble(1, jumlah);
            psKurang.setInt(2, senderId);
            psKurang.executeUpdate();

            PreparedStatement psTambah = conn.prepareStatement("UPDATE accounts SET saldo = saldo + ? WHERE id_account = ?");
            psTambah.setDouble(1, jumlah);
            psTambah.setInt(2, receiverId);
            psTambah.executeUpdate();

            // Catat History (Pake NOW() untuk tanggal)
            PreparedStatement psOut = conn.prepareStatement(
                "INSERT INTO transactions (id_account, tipe, jumlah, keterangan, tanggal) VALUES (?, 'TRANSFER', ?, ?, NOW())");
            psOut.setInt(1, senderId);
            psOut.setDouble(2, jumlah);
            psOut.setString(3, "Transfer ke " + rekeningTujuan);
            psOut.executeUpdate();

            PreparedStatement psIn = conn.prepareStatement(
                "INSERT INTO transactions (id_account, tipe, jumlah, keterangan, tanggal) VALUES (?, 'TRANSFER', ?, ?, NOW())");
            psIn.setInt(1, receiverId);
            psIn.setDouble(2, jumlah);
            psIn.setString(3, "Terima dari " + rekeningPengirim);
            psIn.executeUpdate();

            // Catat Tabel Transfer (Optional, pake try-catch biar aman kalo tabel gak ada)
            try {
                PreparedStatement psTransfer = conn.prepareStatement(
                    "INSERT INTO transfers (rekening_pengirim, rekening_penerima, jumlah, tanggal) VALUES (?, ?, ?, NOW())");
                psTransfer.setString(1, rekeningPengirim);
                psTransfer.setString(2, rekeningTujuan);
                psTransfer.setDouble(3, jumlah);
                psTransfer.executeUpdate();
            } catch (Exception e) { /* Abaikan jika tabel transfers tidak ada */ }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (Exception ex) {}
        }
        return false;
    }
}