package view;

import controller.AccountController;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class WithdrawForm extends JFrame {

    private JTextField txtJumlah;

    public WithdrawForm() {
        setTitle("Tarik Tunai");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout()); 

        // Set Background Jendela jadi Putih
        getContentPane().setBackground(Color.WHITE);

        // Container Form
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Panel Transparan
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(320, 320));

        // Judul
        JLabel lblTitle = new JLabel("Tarik Tunai");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(13, 27, 62));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitle);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Info Saldo
        double saldo = AccountController.getSaldo();
        DecimalFormat df = new DecimalFormat("#,###");
        JLabel lblSaldo = new JLabel("Saldo saat ini: Rp " + df.format(saldo));
        lblSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSaldo.setForeground(Color.GRAY);
        lblSaldo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblSaldo);

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Label Input
        JLabel lblInput = new JLabel("Nominal Penarikan");
        lblInput.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblInput);
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Text Field
        txtJumlah = new JTextField();
        txtJumlah.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtJumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Contoh: 100000");
        txtJumlah.putClientProperty(FlatClientProperties.STYLE, "arc: 10; margin: 0,10,0,10");
        txtJumlah.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(txtJumlah);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Tombol Tarik
        JButton btnTarik = new JButton("TARIK TUNAI");
        btnTarik.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnTarik.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTarik.putClientProperty(FlatClientProperties.STYLE, 
            "background: #0D1B3E; foreground: #fff; arc: 10; border: 0; focusWidth: 0"
        );
        btnTarik.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(btnTarik);

        btnTarik.addActionListener(e -> prosesTarik());

        add(panel);
    }

    private void prosesTarik() {
        try {
            String text = txtJumlah.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Masukkan nominal penarikan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double jumlah = Double.parseDouble(text);

            // Konfirmasi keamanan
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Anda yakin ingin menarik tunai sejumlah Rp " + new DecimalFormat("#,###").format(jumlah) + "?",
                "Konfirmasi Penarikan", 
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (AccountController.tarikTunai(jumlah)) {
                    JOptionPane.showMessageDialog(this, "Penarikan Berhasil! Silakan ambil uang Anda.");
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Saldo tidak cukup atau terjadi kesalahan sistem.", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang benar!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}