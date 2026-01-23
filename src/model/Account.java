package model;

public class Account {
    private int idAccount;
    private int idUser;
    private String noRekening;
    private double saldo;
    private String status;

    public Account() {}

    public Account(int idAccount, int idUser, String noRekening, double saldo, String status) {
        this.idAccount = idAccount;
        this.idUser = idUser;
        this.noRekening = noRekening;
        this.saldo = saldo;
        this.status = status;
    }

    // Getter & Setter
    public int getIdAccount() { return idAccount; }
    public void setIdAccount(int idAccount) { this.idAccount = idAccount; }

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getNoRekening() { return noRekening; }
    public void setNoRekening(String noRekening) { this.noRekening = noRekening; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}