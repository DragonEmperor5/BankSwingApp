package src.view;

import src.util.Session;

import javax.swing.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {
        setTitle("Dashboard Bank");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblWelcome = new JLabel("Selamat Datang, " + Session.namaLengkap);
        lblWelcome.setBounds(30, 30, 300, 25);
        add(lblWelcome);

        JButton btnSaldo = new JButton("Cek Saldo");
        btnSaldo.setBounds(30, 80, 150, 30);
        add(btnSaldo);

        btnSaldo.addActionListener(e -> {
            new SaldoForm().setVisible(true);
        });


        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(30, 130, 150, 30);
        add(btnLogout);

        btnLogout.addActionListener(e -> logout());

                JButton btnSetor = new JButton("Setor Tunai");
        btnSetor.setBounds(30, 120, 150, 30);
        add(btnSetor);

        btnSetor.addActionListener(e -> {
            new SetorForm().setVisible(true);
        });

    }

    private void logout() {
        Session.clear();
        new LoginForm().setVisible(true);
        dispose();
    }
}
