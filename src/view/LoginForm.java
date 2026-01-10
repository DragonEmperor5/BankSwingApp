package src.view;

import src.controller.LoginController;

import javax.swing.*;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Login Bank");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(30, 40, 80, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 40, 160, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(30, 80, 80, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 80, 160, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 130, 100, 30);
        add(btnLogin);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (LoginController.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login berhasil");
            new DashboardForm().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah");
        }
    }
}
