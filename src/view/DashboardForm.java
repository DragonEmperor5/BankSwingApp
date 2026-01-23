package view;

import util.Session;
import controller.AccountController;
import model.Account;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {
        setTitle("Dashboard Bank - Professional");
        setSize(900, 600); // Diperlebar sedikit biar grid 3 kolom muat
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // === 1. HEADER (BAGIAN ATAS) ===
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(13, 27, 62)); // Navy Blue
        headerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Ambil Data Rekening dari Model
        Account akun = AccountController.getAccountDetail();
        String noRek = (akun != null) ? akun.getNoRekening() : "-";

        // Teks Sapaan & Info Rekening
        JLabel lblWelcome = new JLabel("<html>Halo, " + Session.namaLengkap + "<br><span style='font-size:14px; font-weight:normal; color:#bdc3c7'>No. Rek: " + noRek + "</span></html>");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblWelcome.setForeground(Color.WHITE);
        headerPanel.add(lblWelcome, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Main Menu
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout()); 
        centerPanel.setBackground(new Color(240, 242, 245)); 

        // Container Grid: Ubah jadi 2 Baris, 3 Kolom (Total 6 Slot)
        JPanel menuContainer = new JPanel(new GridLayout(2, 3, 20, 20)); 
        menuContainer.setOpaque(false); 

        // Tombol Setor Saldo
        JButton btnSaldo = createMenuButton("Cek Saldo", "Lihat sisa saldo", "#0D1B3E");
        btnSaldo.addActionListener(e -> new SaldoForm().setVisible(true));
        menuContainer.add(btnSaldo);

        // Tombol Setor Tunai
        JButton btnSetor = createMenuButton("Setor Tunai", "Tambah saldo", "#0D1B3E");
        btnSetor.addActionListener(e -> new SetorForm().setVisible(true));
        menuContainer.add(btnSetor);

        // Tombol Tarik Tunai
        JButton btnTarik = createMenuButton("Tarik Tunai", "Ambil uang", "#0D1B3E");
        btnTarik.addActionListener(e -> new WithdrawForm().setVisible(true));
        menuContainer.add(btnTarik);

        // Tombol Transfer
        JButton btnTransfer = createMenuButton("Transfer", "Kirim uang", "#0D1B3E");
        btnTransfer.addActionListener(e -> new TransferForm().setVisible(true));
        menuContainer.add(btnTransfer);

        // Tombol Mutasi
        JButton btnMutasi = createMenuButton("Mutasi", "Riwayat transaksi", "#0D1B3E");
        btnMutasi.addActionListener(e -> new HistoryForm().setVisible(true)); // Buka History
        menuContainer.add(btnMutasi);

        // Tombol Logout
        JButton btnLogout = createMenuButton("Keluar", "Tutup sesi aman", "#C0392B"); // Warna Merah
        btnLogout.addActionListener(e -> logout());
        menuContainer.add(btnLogout);

        centerPanel.add(menuContainer);
        add(centerPanel, BorderLayout.CENTER);
        
        // === 3. FOOTER ===
        JLabel lblCopy = new JLabel("Bank Swing App v1.0 - Trusted & Secure", SwingConstants.CENTER);
        lblCopy.setBorder(new EmptyBorder(15,0,15,0));
        lblCopy.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblCopy.setForeground(Color.GRAY);
        add(lblCopy, BorderLayout.SOUTH);
    }

    // Helper Button
    private JButton createMenuButton(String title, String subtitle, String colorHex) {
        JButton btn = new JButton("<html><center><span style='font-size:16px; font-weight:bold'>" + title + "</span><br><span style='font-size:11px; opacity:0.8'>" + subtitle + "</span></center></html>");
        btn.setPreferredSize(new Dimension(220, 120)); // Lebih besar
        btn.putClientProperty(FlatClientProperties.STYLE, 
            "background: " + colorHex + "; " + 
            "foreground: #FFFFFF; " +
            "arc: 20; " + 
            "borderWidth: 0; " +
            "focusWidth: 0"
        );
        // Efek Hover tangan
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Session.clear();
            new LoginForm().setVisible(true);
            dispose();
        }
    }
}