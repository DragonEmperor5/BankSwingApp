package view;

import controller.AccountController;

import javax.swing.*;

import java.awt.*;
import java.text.DecimalFormat;

public class SaldoForm extends JFrame {

    public SaldoForm() {
        setTitle("Cek Saldo");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        
        JPanel panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        panel.setBackground(Color.WHITE); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Saldo Anda");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(33, 37, 41)); 
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panel.add(lblTitle);

        double saldo = AccountController.getSaldo();
        DecimalFormat df = new DecimalFormat("#,###");

        JLabel lblSaldo = new JLabel("Rp " + df.format(saldo));
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 20)); 
        lblSaldo.setForeground(new Color(13, 27, 62)); 
        lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panel.add(Box.createRigidArea(new Dimension(0, 15))); 
        panel.add(lblSaldo);

        JButton btnClose = new JButton("Tutup");
        btnClose.setBackground(new Color(13, 27, 62));
        btnClose.setForeground(Color.WHITE); 
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 13)); 
        btnClose.setFocusPainted(false); 
        btnClose.setBorder(BorderFactory.createEmptyBorder()); 
        btnClose.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnClose.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnClose.putClientProperty("FlatLaf.style", "arc: 8; focusWidth:0; borderWidth:0");
        btnClose.addActionListener(e -> dispose()); 
        panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        panel.add(btnClose); add(panel, BorderLayout.CENTER); 
    }
}
