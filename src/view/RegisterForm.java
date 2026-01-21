package view;

import controller.RegisterController;

import javax.swing.*;

public class RegisterForm extends JFrame {

    private JTextField txtUsername, txtNama, txtEmail;
    private JPasswordField txtPassword;

    public RegisterForm() {
        setTitle("Register Nasabah");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(30, 40, 100, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 40, 150, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(30, 80, 100, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 80, 150, 25);
        add(txtPassword);

        JLabel lblNama = new JLabel("Nama Lengkap");
        lblNama.setBounds(30, 120, 100, 25);
        add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150, 120, 150, 25);
        add(txtNama);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(30, 160, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 160, 150, 25);
        add(txtEmail);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(120, 220, 100, 30);
        add(btnRegister);

        btnRegister.addActionListener(e -> register());
    }

    private void register() {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());
        String nama = txtNama.getText();
        String email = txtEmail.getText();

        if (user.isEmpty() || pass.isEmpty() || nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong");
            return;
        }

        boolean success = RegisterController.register(user, pass, nama, email);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Registrasi berhasil!\nSilakan login.");
            dispose();
            new LoginForm().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Registrasi gagal! Username mungkin sudah digunakan.");
        }
    }
}
