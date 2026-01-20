package src.view;

import src.controller.AccountController;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class WithdrawForm extends JFrame {

    private JTextField txtJumlah;

    public WithdrawForm() {
        setTitle("Tarik Tunai");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Pakai DISPOSE biar Dashboard gak ikut ketutup
        setLayout(new GridBagLayout()); // Layout rapi di tengah

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Judul
        JLabel lblTitle = new JLabel("Tarik Tunai");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Info Saldo (biar user tau sisa uang)
        double saldo = AccountController.getSaldo();
        DecimalFormat df = new DecimalFormat("#,###");
        JLabel lblSaldo = new JLabel("Saldo saat ini: Rp " + df.format(saldo));
        lblSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSaldo.setForeground(Color.GRAY);
        lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblSaldo);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Input Jumlah
        JLabel lblInput = new JLabel("Masukkan Nominal");
        lblInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblInput);
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtJumlah = new JTextField();
        txtJumlah.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtJumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Contoh: 100000");
        txtJumlah.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        txtJumlah.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtJumlah);

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Tombol Tarik
        JButton btnTarik = new JButton("TARIK UANG");
        btnTarik.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnTarik.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTarik.putClientProperty(FlatClientProperties.STYLE, 
            "background: #0D1B3E; foreground: #fff; arc: 10; border: 0");
        btnTarik.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnTarik);

        btnTarik.addActionListener(e -> prosesTarik());

        add(panel);
    }

    private void prosesTarik() {
        try {
            double jumlah = Double.parseDouble(txtJumlah.getText());

            // Panggil Controller (Pastikan method tarikTunai sudah ada di AccountController)
            if (AccountController.tarikTunai(jumlah)) {
                JOptionPane.showMessageDialog(this, "Penarikan Berhasil!");
                dispose(); // Tutup jendela ini
            } else {
                JOptionPane.showMessageDialog(this, "Saldo tidak cukup atau terjadi kesalahan", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang benar!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}