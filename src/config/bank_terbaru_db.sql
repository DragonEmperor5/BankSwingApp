

CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nama_lengkap VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts (
    id_account INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    no_rekening VARCHAR(20) NOT NULL UNIQUE,
    saldo DECIMAL(15,2) DEFAULT 0,
    status ENUM('AKTIF', 'NONAKTIF') DEFAULT 'AKTIF',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_account
    FOREIGN KEY (id_user) REFERENCES users(id_user)
    ON DELETE CASCADE
);

CREATE TABLE transactions (
    id_transaction INT AUTO_INCREMENT PRIMARY KEY,
    id_account INT NOT NULL,
    tipe ENUM('SETOR', 'TARIK', 'TRANSFER_MASUK', 'TRANSFER_KELUAR') NOT NULL,
    jumlah DECIMAL(15,2) NOT NULL,
    keterangan VARCHAR(255),
    tanggal TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_account_transaction
    FOREIGN KEY (id_account) REFERENCES accounts(id_account)
    ON DELETE CASCADE
);

CREATE TABLE transfers (
    id_transfer INT AUTO_INCREMENT PRIMARY KEY,
    rekening_pengirim VARCHAR(20) NOT NULL,
    rekening_penerima VARCHAR(20) NOT NULL,
    jumlah DECIMAL(15,2) NOT NULL,
    tanggal TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('BERHASIL', 'GAGAL') DEFAULT 'BERHASIL'
);

INSERT INTO users (username, password, nama_lengkap, email)
VALUES ('admin', 'admin123', 'Admin Bank', 'admin@bank.com');

INSERT INTO accounts (id_user, no_rekening, saldo)
VALUES (1, '1234567890', 1000000);

SELECT saldo FROM accounts WHERE id_user = 1;

UPDATE accounts
SET saldo = saldo + 500000
WHERE id_account = 1;

INSERT INTO transactions (id_account, tipe, jumlah, keterangan)
VALUES (1, 'SETOR', 500000, 'Setor tunai');

UPDATE accounts
SET saldo = saldo - 200000
WHERE id_account = 1 AND saldo >= 200000;

INSERT INTO transactions (id_account, tipe, jumlah, keterangan)
VALUES (1, 'TARIK', 200000, 'Tarik tunai');

-- Kurangi saldo pengirim
UPDATE accounts
SET saldo = saldo - 300000
WHERE no_rekening = '1234567890';

-- Tambah saldo penerima
UPDATE accounts
SET saldo = saldo + 300000
WHERE no_rekening = '0987654321';

-- Catat transaksi
INSERT INTO transfers (rekening_pengirim, rekening_penerima, jumlah)
VALUES ('1234567890', '0987654321', 300000);

