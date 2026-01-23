package view;

import controller.RegisterController; 
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class RegisterForm extends JFrame {

    private JTextField txtUsername, txtNama, txtEmail;
    private JPasswordField txtPassword;

    public RegisterForm() {
        setTitle("Registrasi Nasabah Baru");
        setSize(850, 600); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setLayout(new GridLayout(1, 2));

        // Bagian Kiri
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(13, 27, 62));
        leftPanel.setLayout(new GridBagLayout()); 

        JLabel lblLogo = new JLabel("<html><center><span style='font-size:28px'>JOIN US</span><br><span style='font-size:12px; font-weight:normal'>Buat Akun Bank Swing</span></center></html>");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        leftPanel.add(lblLogo);

        add(leftPanel);

        // Bagian Kanan
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout()); 

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(300, 500)); 

        // Header Form
        JLabel lblTitle = new JLabel("Buat Akun Baru");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(33, 37, 41));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(lblTitle);

        JLabel lblSub = new JLabel("Lengkapi data diri Anda");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(lblSub);

        formContainer.add(Box.createRigidArea(new Dimension(0, 25)));

        // Input Username
        formContainer.add(createLabel("Username"));
        formContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        txtUsername = createTextField("Buat Username unik");
        formContainer.add(txtUsername);
        formContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        // Input Password
        formContainer.add(createLabel("Password"));
        formContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        
        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buat Password kuat");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "arc: 10; margin: 0,10,0,10; showRevealButton: true");
        txtPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(txtPassword);
        
        formContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        // Input Nama Lengkap
        formContainer.add(createLabel("Nama Lengkap"));
        formContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        txtNama = createTextField("Masukkan Nama Lengkap");
        formContainer.add(txtNama);
        formContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        // Input Email
        formContainer.add(createLabel("Email"));
        formContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        txtEmail = createTextField("contoh@email.com");
        formContainer.add(txtEmail);
        formContainer.add(Box.createRigidArea(new Dimension(0, 30)));

        // Tombol Register
        JButton btnRegister = new JButton("DAFTAR SEKARANG");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnRegister.putClientProperty(FlatClientProperties.STYLE, 
            "background: #0D1B3E; foreground: #fff; arc: 10; border: 0; focusWidth: 0"
        );
        btnRegister.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRegister.addActionListener(e -> register());
        
        formContainer.add(btnRegister);

        // Link Login
        formContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton btnLoginLink = new JButton("Sudah punya akun? Login disini");
        btnLoginLink.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnLoginLink.setForeground(new Color(13, 27, 62));
        btnLoginLink.setContentAreaFilled(false);
        btnLoginLink.setBorderPainted(false);
        btnLoginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLoginLink.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnLoginLink.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });
        formContainer.add(btnLoginLink);

        rightPanel.add(formContainer);
        add(rightPanel);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField createTextField(String placeholder) {
        JTextField txt = new JTextField();
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeholder);
        txt.putClientProperty(FlatClientProperties.STYLE, "arc: 10; margin: 0,10,0,10");
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        return txt;
    }

    private void register() {
        // Ambil data dari form
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());
        String nama = txtNama.getText();
        String email = txtEmail.getText();

        // Validasi kosong
        if (user.isEmpty() || pass.isEmpty() || nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi Username, Password, dan Nama!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Panggil Controller
        // Urutan: register(username, password, nama, email)
        boolean success = RegisterController.register(user, pass, nama, email);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registrasi Berhasil! Silakan Login.");
            new LoginForm().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registrasi Gagal! Username mungkin sudah terpakai.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}