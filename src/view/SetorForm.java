package src.view;

import src.controller.AccountController;

import javax.swing.*;

public class SetorForm extends JFrame {

    private JTextField txtJumlah;

    public SetorForm() {
        setTitle("Setor Tunai");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl = new JLabel("Jumlah Setor");
        lbl.setBounds(30, 40, 100, 25);
        add(lbl);

        txtJumlah = new JTextField();
        txtJumlah.setBounds(130, 40, 120, 25);
        add(txtJumlah);

        JButton btnSetor = new JButton("Setor");
        btnSetor.setBounds(90, 90, 100, 30);
        add(btnSetor);

        btnSetor.addActionListener(e -> setor());
    }

    private void setor() {
        try {
            double jumlah = Double.parseDouble(txtJumlah.getText());

            if (AccountController.setorTunai(jumlah)) {
                JOptionPane.showMessageDialog(this, "Setor tunai berhasil");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Jumlah tidak valid");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang benar");
        }
    }
}
