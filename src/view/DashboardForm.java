package src.view;

import src.util.Session;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {
        setTitle("Dashboard Bank - Professional");
        setSize(800, 500); // Ukuran window diperbesar biar lega
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Layout utama dibagi: Atas, Tengah, Bawah

        // Header (Bagian Atas)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(13, 27, 62)); // Warna Navy Blue
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20)); // Padding

        // Teks Sapaan
        JLabel lblWelcome = new JLabel("Halo, " + Session.namaLengkap);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblWelcome.setForeground(Color.WHITE);
        headerPanel.add(lblWelcome, BorderLayout.WEST);

        // Tombol Logout di Header Kanan
        JButton btnLogout = new JButton("Logout");
        btnLogout.putClientProperty(FlatClientProperties.STYLE, 
            "background: #d9534f; foreground: #fff; border: 0; arc: 999; focusWidth: 0; padding: 5,15,5,15");
        btnLogout.addActionListener(e -> logout());
        headerPanel.add(btnLogout, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Main Menu (Grid Tombol)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout()); // Biar menu pas di tengah layar
        centerPanel.setBackground(new Color(240, 242, 245)); // Background abu-abu muda

        // Container untuk tombol menu (Grid 2x2)
        JPanel menuContainer = new JPanel(new GridLayout(2, 2, 20, 20)); // Gap antar tombol 20px
        menuContainer.setOpaque(false); // Transparan biar ikut background belakang

        // Tombol Menu
        JButton btnSaldo = createMenuButton("Cek Saldo", "Lihat sisa saldo tabungan");
        btnSaldo.addActionListener(e -> new SaldoForm().setVisible(true));
        menuContainer.add(btnSaldo);

        JButton btnSetor = createMenuButton("Setor Tunai", "Tambah saldo rekening");
        btnSetor.addActionListener(e -> new SetorForm().setVisible(true));
        menuContainer.add(btnSetor);

        JButton btnTransfer = createMenuButton("Transfer", "Kirim uang ke rekening lain");
        btnTransfer.addActionListener(e -> new TransferForm().setVisible(true));
        menuContainer.add(btnTransfer);

        // Tombol Tambahan (Tarik Tunai) biar Grid-nya Genap (4 kotak)
        JButton btnTarik = createMenuButton("Tarik Tunai", "Ambil uang tunai");
        btnTarik.addActionListener(e -> new WithdrawForm().setVisible(true));
        menuContainer.add(btnTarik);

        centerPanel.add(menuContainer);
        add(centerPanel, BorderLayout.CENTER);
        
        // Footer
        JLabel lblCopy = new JLabel("Bank Swing App v1.0", SwingConstants.CENTER);
        lblCopy.setBorder(new EmptyBorder(10,0,10,0));
        lblCopy.setForeground(Color.GRAY);
        add(lblCopy, BorderLayout.SOUTH);
    }

    // Helper untuk membuat tombol Menu yang seragam
    private JButton createMenuButton(String title, String subtitle) {
        JButton btn = new JButton("<html><center><span style='font-size:14px; font-weight:bold'>" + title + "</span><br><span style='font-size:10px; color:#cccccc'>" + subtitle + "</span></center></html>");
        btn.setPreferredSize(new Dimension(200, 100)); // Ukuran tombol besar
        btn.putClientProperty(FlatClientProperties.STYLE, 
            "background: #0D1B3E; " + 
            "foreground: #FFFFFF; " +
            "arc: 15; " + // Sudut membulat
            "borderWidth: 0; " +
            "focusWidth: 0"
        );
        return btn;
    }

    private void logout() {
        Session.clear();
        new LoginForm().setVisible(true);
        dispose();
    }
}