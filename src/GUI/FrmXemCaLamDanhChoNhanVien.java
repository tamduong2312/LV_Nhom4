package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BangPhanCongCaLamBus;
import MODEL.BangPhanCongCaLam;
import MODEL.Session;

public class FrmXemCaLamDanhChoNhanVien extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableLichLam;
    private BangPhanCongCaLamBus bpbus = new BangPhanCongCaLamBus();

    private final Color HEADER_BLUE = new Color(0, 123, 255);
    private final Color GRAY_BTN = new Color(108, 117, 125);
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmXemCaLamDanhChoNhanVien frame = new FrmXemCaLamDanhChoNhanVien(1); 
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmXemCaLamDanhChoNhanVien(int maNV) {
        setTitle("Lịch Làm Việc Cá Nhân");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(100, 100, 1000, 500); 
        setLocationRelativeTo(null);
        
        initUI();
        loadLichLamViec(maNV);
    }

    public FrmXemCaLamDanhChoNhanVien() {
        this(1); 
    }

    private void initUI() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pnlHeader.setBackground(HEADER_BLUE);
        pnlHeader.setPreferredSize(new Dimension(100, 60));

        JLabel lblTitle = new JLabel("LỊCH LÀM VIỆC CỦA TÔI");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlCenter = new JPanel(new BorderLayout());
        pnlCenter.setBackground(Color.WHITE);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                new LineBorder(new Color(200, 200, 200)), 
                "Thời Khóa Biểu Trong Tuần"
        );
        titledBorder.setTitleFont(HEADER_FONT);
        titledBorder.setTitleColor(HEADER_BLUE);
        pnlCenter.setBorder(titledBorder);

        String[] columns = { "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" };
        
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tableLichLam = new JTable(model);
        tableLichLam.setFont(MAIN_FONT);
        tableLichLam.setRowHeight(120); 
        tableLichLam.setGridColor(new Color(220, 220, 220));
        tableLichLam.setSelectionBackground(new Color(230, 245, 255));
        tableLichLam.setSelectionForeground(Color.BLACK);
        tableLichLam.setShowVerticalLines(true);
        tableLichLam.setShowHorizontalLines(true);

        JTableHeader header = tableLichLam.getTableHeader();
        header.setFont(HEADER_FONT);
        header.setBackground(Color.WHITE);
        header.setForeground(HEADER_BLUE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        JScrollPane scrollPane = new JScrollPane(tableLichLam);
        scrollPane.getViewport().setBackground(Color.WHITE);
        pnlCenter.add(scrollPane, BorderLayout.CENTER);

        contentPane.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        pnlFooter.setBackground(Color.WHITE);

        JButton btnQuayVe = new JButton("Quay về");
        btnQuayVe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnQuayVe.setBackground(GRAY_BTN);
        btnQuayVe.setForeground(Color.WHITE);
        btnQuayVe.setPreferredSize(new Dimension(120, 40));
        btnQuayVe.setFocusPainted(false);
        btnQuayVe.setBorderPainted(false);
        btnQuayVe.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnQuayVe.addActionListener(e -> dispose());

        pnlFooter.add(btnQuayVe);
        contentPane.add(pnlFooter, BorderLayout.SOUTH);
    }

    private void loadLichLamViec(int maNV) {
        List<BangPhanCongCaLam> listCaLam = bpbus.getCaLamCuaNhanVien(Session.maNhanVien);
        
        String[] rowData = new String[7];
        Arrays.fill(rowData, ""); 

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        for (BangPhanCongCaLam pc : listCaLam) {
            int colIndex = getColumnIndexByDay(pc.getThu());
            
            if (colIndex != -1) {
                String timeStr = sdf.format(pc.getGioLam()) + " - " + sdf.format(pc.getGioKetThuc());
                String info = String.format("<html><center><b>%s</b><br>Phòng: %s</center>", timeStr, pc.getPhong());
            
                if (!rowData[colIndex].isEmpty()) {
                    String oldData = rowData[colIndex].replace("</html>", "");
                    rowData[colIndex] = oldData + "<br><br>" + info + "</html>";
                } else {
                    rowData[colIndex] = info + "</html>";
                }
            }
        }

        DefaultTableModel model = (DefaultTableModel) tableLichLam.getModel();
        model.setRowCount(0); 
        model.addRow(rowData);
    }

    private int getColumnIndexByDay(String day) {
        if(day == null) return -1;
        switch (day.trim()) {
            case "Thứ 2": return 0;
            case "Thứ 3": return 1;
            case "Thứ 4": return 2;
            case "Thứ 5": return 3;
            case "Thứ 6": return 4;
            case "Thứ 7": return 5;
            case "Chủ Nhật": return 6;
            default: return -1;
        }
    }
}