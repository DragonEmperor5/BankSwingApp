package model;

import java.sql.Timestamp;

public class Transaction {
    private int idTransaction;
    private int idAccount;
    private String tipe;
    private double jumlah;
    private String keterangan;
    private Timestamp tanggal;

    public Transaction() {}

    public Transaction(int idTransaction, int idAccount, String tipe, double jumlah, String keterangan, Timestamp tanggal) {
        this.idTransaction = idTransaction;
        this.idAccount = idAccount;
        this.tipe = tipe;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    // Getter & Setter
    public int getIdTransaction() { return idTransaction; }
    public void setIdTransaction(int idTransaction) { this.idTransaction = idTransaction; }

    public int getIdAccount() { return idAccount; }
    public void setIdAccount(int idAccount) { this.idAccount = idAccount; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    public Timestamp getTanggal() { return tanggal; }
    public void setTanggal(Timestamp tanggal) { this.tanggal = tanggal; }
}