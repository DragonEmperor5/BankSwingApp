package view;

import controller.LoginController;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Bank Swing - Secure Login");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // Bagi 2 layar: Kiri & Kanan

        // Bagian Kiri (BRANDING SIDE)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(13, 27, 62)); // Warna Navy Blue
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        // Spacer biar konten turun ke tengah
        leftPanel.add(Box.createVerticalGlue());

        JLabel lblLogo = new JLabel("BANK SWING");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblLogo);

        JLabel lblSlogan = new JLabel("Trusted. Secure. Professional.");
        lblSlogan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSlogan.setForeground(new Color(255, 255, 255, 150)); // Putih transparan
        lblSlogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblSlogan);

        // Spacer bawah
        leftPanel.add(Box.createVerticalGlue());
        
        // Copyright kecil di bawah kiri
        JLabel lblCopy = new JLabel("© 2026 Bank Swing Corp.   ");
        lblCopy.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblCopy.setForeground(new Color(255, 255, 255, 80));
        lblCopy.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblCopy);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(leftPanel);

        // Bagian Kanan (FORM SIDE)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout()); // Biar form pas di tengah vertikal

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(300, 350));

        // Judul Login
        JLabel lblTitle = new JLabel("Internet Banking");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(33, 37, 41));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(lblTitle);

        JLabel lblSub = new JLabel("Silakan masuk untuk melanjutkan");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(lblSub);
        
        formContainer.add(Box.createRigidArea(new Dimension(0, 30))); // Jarak

        // Input Username
        JLabel lblUser = new JLabel("Username ID");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(lblUser);
        
        formContainer.add(Box.createRigidArea(new Dimension(0, 5)));

        txtUsername = new JTextField();
        txtUsername.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan User ID Anda");
        txtUsername.putClientProperty(FlatClientProperties.STYLE, "arc: 5; margin: 0,10,0,10"); // Kotak tegas (arc kecil)
        txtUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(txtUsername);

        formContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        // Input Password
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(lblPass);

        formContainer.add(Box.createRigidArea(new Dimension(0, 5)));

        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan Password Anda");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "arc: 5; margin: 0,10,0,10; showRevealButton: true");
        txtPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(txtPassword);

        formContainer.add(Box.createRigidArea(new Dimension(0, 25)));

        // Tombol Login
        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(13, 27, 62)); // Samakan dengan warna kiri
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        // Tombol kotak tegas khas enterprise
        btnLogin.putClientProperty(FlatClientProperties.STYLE, "arc: 5; border: 0; focusWidth: 0");
        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(btnLogin);
        
        btnLogin.addActionListener(e -> login());

        rightPanel.add(formContainer);
        add(rightPanel);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(120, 170, 100, 30);
        add(btnRegister);

        btnRegister.addActionListener(e -> {
        new RegisterForm().setVisible(true);
        dispose();
        });

    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (LoginController.login(username, password)) {
            new DashboardForm().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Akses Ditolak: Username/Password Salah", "Security Alert", JOptionPane.ERROR_MESSAGE);
        }
    }
}