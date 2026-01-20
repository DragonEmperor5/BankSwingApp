package src.view;

import src.controller.TransferController;

import javax.swing.*;

public class TransferForm extends JFrame {

    private JTextField txtRekening;
    private JTextField txtJumlah;

    public TransferForm() {
        setTitle("Transfer Antar Rekening");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblRek = new JLabel("Rekening Tujuan");
        lblRek.setBounds(30, 40, 120, 25);
        add(lblRek);

        txtRekening = new JTextField();
        txtRekening.setBounds(160, 40, 150, 25);
        add(txtRekening);

        JLabel lblJumlah = new JLabel("Jumlah Transfer");
        lblJumlah.setBounds(30, 80, 120, 25);
        add(lblJumlah);

        txtJumlah = new JTextField();
        txtJumlah.setBounds(160, 80, 150, 25);
        add(txtJumlah);

        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(120, 140, 100, 30);
        add(btnTransfer);

        btnTransfer.addActionListener(e -> transfer());
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
