package src.view;

import src.controller.LoginController;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Bank App Login");
        setSize(400, 350); // Ukuran sedikit diperbesar biar lega
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Pakai Layout center

        // Panel utama (semacam "Card" di tengah)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(300, 250));
        // Opsional: Kasih border putih tipis kalau background gelap
        panel.putClientProperty(FlatClientProperties.STYLE, "arc: 20; background: lighten(@background, 5%)");
        add(panel);

        // JUDUL
        JLabel lblTitle = new JLabel("Welcome Back", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(0, 20, 300, 30);
        panel.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Please login to your account", SwingConstants.CENTER);
        lblSubtitle.putClientProperty(FlatClientProperties.STYLE, "font: -2; foreground: $Label.disabledForeground");
        lblSubtitle.setBounds(0, 50, 300, 20);
        panel.add(lblSubtitle);

        // USERNAME INPUT
        txtUsername = new JTextField();
        txtUsername.setBounds(30, 90, 240, 35);
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username");
        txtUsername.putClientProperty(FlatClientProperties.STYLE, "arc: 10"); // Membulatkan sudut
        panel.add(txtUsername);

        // PASSWORD INPUT 
        txtPassword = new JPasswordField();
        txtPassword.setBounds(30, 140, 240, 35);
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "arc: 10; showRevealButton: true"); // Tombol Show Password (Ikon Mata)
        panel.add(txtPassword);

        // TOMBOL LOGIN (Gradient/Bold)
        btnLogin = new JButton("Login");
        btnLogin.setBounds(30, 200, 240, 40);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.putClientProperty(FlatClientProperties.STYLE, 
            "arc: 10; " +
            "background: #007bff; " +
            "foreground: #ffffff; " +
            "borderWidth: 0"
        );
        panel.add(btnLogin);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (LoginController.login(username, password)) {
            // Animasi dikit pas sukses 
            JOptionPane.showMessageDialog(this, "Login Berhasil!");
            new DashboardForm().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Ups, Username/Password salah!", "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
}