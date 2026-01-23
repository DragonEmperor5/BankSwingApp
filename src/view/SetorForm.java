package view;

import controller.AccountController;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class SetorForm extends JFrame {

    private JTextField txtJumlah;

    public SetorForm() {
        setTitle("Setor Tunai");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        panel.setBackground(Color.WHITE); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lbl = new JLabel("Jumlah Setor");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);

        txtJumlah = new JTextField();
        txtJumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan jumlah setor"); 
        txtJumlah.putClientProperty(FlatClientProperties.STYLE, "arc: 5; margin: 0,10,0,10"); 
        txtJumlah.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 
        txtJumlah.setAlignmentX(Component.LEFT_ALIGNMENT); panel.add(txtJumlah);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnSetor = new JButton("Setor");
        btnSetor.setBackground(new Color(13, 27, 62)); 
        btnSetor.setForeground(Color.WHITE); 
        btnSetor.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        btnSetor.setFocusPainted(false); 
        btnSetor.setBorder(BorderFactory.createEmptyBorder()); 
        btnSetor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 
        btnSetor.setAlignmentX(Component.LEFT_ALIGNMENT); 
        btnSetor.putClientProperty("FlatLaf.style", "arc: 8; focusWidth:0; borderWidth:0"); 
        btnSetor.addActionListener(e -> setor()); panel.add(btnSetor); 
        add(panel, BorderLayout.CENTER);
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
