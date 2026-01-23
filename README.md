# Bank Swing Professional

**Bank Swing Professional** adalah aplikasi simulasi perbankan desktop yang dibangun menggunakan **Java Swing**. Aplikasi ini dirancang dengan arsitektur **MVC (Model-View-Controller)** untuk memisahkan logika bisnis, data, dan antarmuka pengguna, serta menerapkan tampilan modern menggunakan **FlatLaf**.

![Banner Aplikasi](assets/banner.png)

---

## Fitur Utama

* **Arsitektur MVC:** Struktur kode rapi, modular, dan mudah dikembangkan.
* **Modern UI:** Antarmuka bersih dan responsif menggunakan *FlatLaf* dengan tema Navy Blue.
* **Registrasi Otomatis:** Pembuatan akun nasabah dengan generasi nomor rekening 10-digit otomatis.
* **Transaksi Aman:** Fitur Transfer menggunakan *Database Transaction (Atomic)* untuk mencegah data tidak konsisten.
* **Mutasi Rekening:** Pencatatan riwayat transaksi (Setor, Tarik, Transfer) secara *real-time*.
* **Manajemen Saldo:** Pengecekan, penambahan, dan penarikan saldo dengan validasi.

---

## Tech Stack

* **Bahasa:** Java (JDK 17+)
* **GUI:** Java Swing
* **Styling:** FlatLaf Library
* **Database:** MySQL (via Laragon/XAMPP)
* **Koneksi:** JDBC (Java Database Connectivity)
* **IDE:** VS Code

---

## Demo & Alur Penggunaan (User Flow)

Berikut adalah simulasi penggunaan aplikasi:

### 1. Registrasi & Login
Pengguna mendaftarkan akun baru, mendapatkan nomor rekening otomatis, lalu masuk ke dashboard.

![Demo Register & Login](assets/register.gif)
![Demo Register & Login](assets/login.gif)


### 2. Dashboard & Cek Saldo
Tampilan utama yang menampilkan informasi nasabah dan menu transaksi.

![Demo Dashboard](assets/cekSaldo.gif)

### 3. Setor & Tarik Tunai
Simulasi menabung (menambah saldo) dan mengambil uang (mengurangi saldo).

![Demo Transaksi](assets/setorTunaiTarikTunai.gif)

### 4. Transfer & Mutasi (History)
Mengirim uang ke rekening lain dan melihat riwayat transaksi di tabel mutasi.

![Demo Transfer History](assets/transferMutasi.gif)

---

## Cara Instalasi & Menjalankan

### Persyaratan
1.  Pastikan **Java JDK** sudah terinstall.
2.  Pastikan **Laragon** (atau XAMPP) aktif (Apache & MySQL Start).

### Langkah-langkah
1.  **Clone Repository**
    ```bash
    git clone [https://github.com/username-kamu/Bank-Swing-App.git](https://github.com/username-kamu/Bank-Swing-App.git)
    ```
2.  **Setup Database**
    * Buka **Laragon** -> Klik tombol **Database** (membuka phpMyAdmin).
    * Buat database baru dengan nama: **`bank_db`**.
    * Pilih database tersebut, lalu klik menu **Import**.
    * Pilih file **`bank_terbaru_db.sql`** yang ada di dalam folder project ini.
    * Klik **Go / Kirim**.

3.  **Konfigurasi Database**
    Buka `src/config/DatabaseConnection.java` dan sesuaikan kredensial Laragon:
    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/bank_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Kosongkan untuk Laragon default
    ```

4.  **Jalankan Aplikasi**
    Run file `src/main/MainApp.java` atau `src/view/LoginForm.java`.

---

## Struktur Folder (MVC)
```text
Bank-Swing-App/
│
├── .vscode/                    # (Opsional) Konfigurasi VS Code
│   └── settings.json
│
├── lib/                        # Folder Library Eksternal
│   ├── flatlaf-3.x.jar         # Untuk tampilan UI Modern
│   └── mysql-connector-j.jar   # Driver Database MySQL
│
├── src/                        # Folder Kode Sumber (Source Code)
│   ├── config/                 # Konfigurasi Database
│   │   └── DatabaseConnection.java
│   │
│   ├── controller/             # Logika Bisnis (Otak Aplikasi)
│   │   ├── AccountController.java
│   │   ├── LoginController.java
│   │   ├── RegisterController.java
│   │   ├── TransactionController.java
│   │   └── TransferController.java
│   │
│   ├── model/                  # Representasi Data (Wadah Data)
│   │   ├── Account.java
│   │   ├── Transaction.java
│   │   └── User.java
│   │
│   ├── util/                   # Utilitas / Helper
│   │   └── Session.java        # Menyimpan data user yang sedang login
│   │
│   ├── view/                   # Tampilan Antarmuka (GUI)
│   │   ├── DashboardForm.java
│   │   ├── HistoryForm.java
│   │   ├── LoginForm.java
│   │   ├── RegisterForm.java
│   │   ├── SaldoForm.java
│   │   ├── SetorForm.java
│   │   ├── TransferForm.java
│   │   └── WithdrawForm.java
│   │
│   └── main/                   # Titik Awal Aplikasi (Entry Point)
│       └── MainApp.java
│
├── assets/                     # Aset Gambar/GIF untuk Dokumentasi
│   ├── demo-register.gif
│   ├── demo-dashboard.gif
│   └── demo-transaksi.gif
│
├── bak_terbaru_db.sql          # File Database SQL untuk di-import
├── README.md                   # Dokumentasi Proyek
└── LICENSE                     # (Opsional) Lisensi Open Source
```