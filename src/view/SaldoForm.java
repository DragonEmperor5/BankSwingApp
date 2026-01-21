package view;

import controller.AccountController;

import javax.swing.*;
import java.text.DecimalFormat;

public class SaldoForm extends JFrame {

    public SaldoForm() {
        setTitle("Cek Saldo");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("Saldo Anda");
        lblTitle.setBounds(100, 20, 150, 25);
        add(lblTitle);

        double saldo = AccountController.getSaldo();
        DecimalFormat df = new DecimalFormat("#,###");

        JLabel lblSaldo = new JLabel("Rp " + df.format(saldo));
        lblSaldo.setBounds(80, 60, 200, 30);
        lblSaldo.setFont(lblSaldo.getFont().deriveFont(16f));
        add(lblSaldo);

        JButton btnClose = new JButton("Tutup");
        btnClose.setBounds(90, 110, 100, 30);
        add(btnClose);

        btnClose.addActionListener(e -> dispose());
    }
}
