package src.view;

import src.controller.TransferController;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class TransferForm extends JFrame {

    private JTextField txtRekening;
    private JTextField txtJumlah;

    public TransferForm() {
        setTitle("Transfer Dana");
        setSize(400, 400);
        setLocationRelativeTo(null);
        // Menggunakan GridBagLayout agar form berada persis di tengah
        setLayout(new GridBagLayout()); 
        
        // Set background Jendela utama jadi Putih bersih
        getContentPane().setBackground(Color.WHITE);

        // Container Form
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Panel ini transparan (menyatu dengan background)
        panel.setOpaque(false); 
        panel.setPreferredSize(new Dimension(320, 320));

        // Judul
        JLabel lblTitle = new JLabel("Transfer Dana");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Font diperbesar dikit
        lblTitle.setForeground(new Color(13, 27, 62));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitle);

        panel.add(Box.createRigidArea(new Dimension(0, 25))); // Jarak

        // Label Rekening
        JLabel lblRek = new JLabel("Rekening Tujuan");
        lblRek.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblRek.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblRek);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Input Rekening
        txtRekening = new JTextField();
        txtRekening.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        // Style: Arc (lengkungan) tetap ada, tapi border lebih tipis
        txtRekening.putClientProperty(FlatClientProperties.STYLE, "arc: 10; margin: 0,10,0,10");
        txtRekening.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(txtRekening);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Label Jumlah
        JLabel lblJumlah = new JLabel("Jumlah Transfer");
        lblJumlah.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblJumlah.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblJumlah);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Input Jumlah
        txtJumlah = new JTextField();
        txtJumlah.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtJumlah.putClientProperty(FlatClientProperties.STYLE, "arc: 10; margin: 0,10,0,10");
        txtJumlah.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(txtJumlah);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Tombol Transfer
        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTransfer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnTransfer.putClientProperty(FlatClientProperties.STYLE, 
            "background: #0D1B3E; foreground: #fff; border: 0; arc: 10; focusWidth: 0"
        );
        btnTransfer.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(btnTransfer);

        btnTransfer.addActionListener(e -> transfer());

        add(panel);
    }

    private void transfer() {
        try {
            String rekening = txtRekening.getText();
            double jumlah = Double.parseDouble(txtJumlah.getText());

            boolean success = TransferController.transfer(rekening, jumlah);

            if (success) {
                JOptionPane.showMessageDialog(this, "Transfer berhasil");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Transfer gagal!\nCek saldo atau rekening tujuan");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka");
        }
    }
}