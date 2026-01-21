package view;

import controller.AccountController;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class DepositForm extends JFrame {
    private JTextField txtJumlah;

    public DepositForm() {
        setTitle("Deposit Tunai");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Gunakan BorderLayout agar panel isi memenuhi frame
        setLayout(new BorderLayout());

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding biar rapi

        // Label judul
        JLabel lbl = new JLabel("Jumlah Deposit");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);

        // Input jumlah
        txtJumlah = new JTextField();
        txtJumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan jumlah deposit");
        txtJumlah.putClientProperty(FlatClientProperties.STYLE, "arc: 5; margin: 0,10,0,10");
        txtJumlah.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtJumlah.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(txtJumlah);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Tombol deposit
        JButton btnDeposit = new JButton("DEPOSIT");
        btnDeposit.setBackground(new Color(13, 27, 62));
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.putClientProperty(FlatClientProperties.STYLE, "arc: 5; border:0; focusWidth:0");
        btnDeposit.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnDeposit.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnDeposit.addActionListener(e -> deposit());
        panel.add(btnDeposit);

        // Tambahkan panel ke frame
        add(panel, BorderLayout.CENTER);
    }

    private void deposit() {
        try {
            double jumlah = Double.parseDouble(txtJumlah.getText());
            if (AccountController.setorTunai(jumlah)) {
                JOptionPane.showMessageDialog(this, "Deposit berhasil");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Jumlah tidak valid");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang benar");
        }
    }
}
