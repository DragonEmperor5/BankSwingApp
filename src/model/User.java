package model;

public class User {
    private int idUser;
    private String username;
    private String password;
    private String namaLengkap;
    private String email;

    // Constructor kosong
    public User() {}

    // Constructor lengkap
    public User(int idUser, String username, String password, String namaLengkap, String email) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.email = email;
    }

    // Getter & Setter
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}