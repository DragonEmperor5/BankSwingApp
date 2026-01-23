package view;

import controller.TransactionController;
import model.Transaction;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryForm extends JFrame {

    public HistoryForm() {
        setTitle("Riwayat Transaksi");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(13, 27, 62)); // Navy Blue
        pnlHeader.setPreferredSize(new Dimension(600, 60));
        pnlHeader.setLayout(new GridBagLayout());

        JLabel lblTitle = new JLabel("Mutasi Rekening");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);

        add(pnlHeader, BorderLayout.NORTH);

        // Tabel Data
        String[] columns = {"Tanggal", "Tipe", "Keterangan", "Jumlah"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabel tidak bisa diedit manual
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Styling kolom Jumlah agar rata kanan (Alignment Right)
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        // Load Data dari Controller
        loadData(model);

        JScrollPane scrollPane = new JScrollPane(table);
        // Style Scrollbar modern
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, 
            "trackArc: 999; width: 10; thumbInsets: 0,0,0,0; trackInsets: 0,0,0,0;"
        );
        
        add(scrollPane, BorderLayout.CENTER);

        // Tombol Tutup di bawah
        JPanel pnlBottom = new JPanel();
        JButton btnClose = new JButton("Tutup");
        btnClose.addActionListener(e -> dispose());
        pnlBottom.add(btnClose);
        add(pnlBottom, BorderLayout.SOUTH);
    }

    private void loadData(DefaultTableModel model) {
        List<Transaction> list = TransactionController.getHistory();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        DecimalFormat df = new DecimalFormat("#,###");

        for (Transaction t : list) {
            String jumlahFmt = "Rp " + df.format(t.getJumlah());
            
            if (t.getTipe().equals("SETOR") || (t.getTipe().equals("TRANSFER") && t.getKeterangan().startsWith("Terima"))) {
                jumlahFmt = "+ " + jumlahFmt;
            } else {
                jumlahFmt = "- " + jumlahFmt;
            }

            model.addRow(new Object[]{
                sdf.format(t.getTanggal()),
                t.getTipe(),
                t.getKeterangan(),
                jumlahFmt
            });
        }
    }
}