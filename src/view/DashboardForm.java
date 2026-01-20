package src.view;

import src.util.Session;

import javax.swing.*;
import java.awt.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {
        setTitle("Dashboard Bank");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE); //background putih

        //Label Welcome
        JLabel lblWelcome = new JLabel("Selamat Datang, " + Session.namaLengkap);
        lblWelcome.setBounds(30, 30, 300, 25);
        add(lblWelcome);

        //Tombol Cek Saldo
        JButton btnSaldo = new JButton("Cek Saldo");
        btnSaldo.setBounds(30, 80, 150, 30);
        styleButton(btnSaldo);
        add(btnSaldo);

        btnSaldo.addActionListener(e -> {
            new SaldoForm().setVisible(true);
        });

        //Tombol Logout
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(30, 180, 150, 30);
        add(btnLogout);

        btnLogout.addActionListener(e -> logout());

        //Tombol Setor Tunai
        JButton btnSetor = new JButton("Setor Tunai");
        btnSetor.setBounds(30, 130, 150, 30);
        add(btnSetor);

        btnSetor.addActionListener(e -> {
            new DepositForm().setVisible(true);
        });
        //Tombol Transfer
        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(30, 160, 150, 30);
        add(btnTransfer);

        btnTransfer.addActionListener(e -> {
            new TransferForm().setVisible(true);
        });


    }

    private void logout() {
        Session.clear();
        new LoginForm().setVisible(true);
        dispose();
    }

    // Utility styling button
    private void styleButton(JButton button) { 
        button.setBackground(new Color(13, 27, 62)); // Navy Blue 
        button.setForeground(Color.WHITE); 
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
    }
}
