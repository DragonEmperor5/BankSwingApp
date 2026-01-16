package src.main;

import com.formdev.flatlaf.FlatDarkLaf; // 1. Import ini wajib ada
import com.formdev.flatlaf.FlatLightLaf; // (Opsional) kalau mau tema terang
import src.view.LoginForm;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); 
            // Kalau mau darkmode, ganti jadi: new FlatDarkLaf()
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}