package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

// Import các panel con (Đảm bảo các class này extends JPanel)
import GUI.FrmNhapThongTinChiSoBenhBacSiTongQuat;
import GUI.FrmHienThiTTBN;

public class FrmXemNhapChiSoTongQuat extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private static int id;
    

    private final Color PRIMARY_COLOR = new Color(0, 123, 255); 
    private final Color BG_COLOR = new Color(245, 248, 250);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmXemNhapChiSoTongQuat frame = new FrmXemNhapChiSoTongQuat(id);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmXemNhapChiSoTongQuat(int id) {
    	this.id = id;
        setTitle("trợ lý Tổng Quát - Khám Bệnh");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // --- 1. HEADER (PHẦN TRÊN) ---
 
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(PRIMARY_COLOR);
        pnlHeader.setPreferredSize(new Dimension(10, 60));
        pnlHeader.setLayout(new BorderLayout(0, 0));
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("  PHIẾU KHÁM BỆNH TỔNG QUÁT");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        pnlHeader.add(lblTitle, BorderLayout.WEST);

        // --- 2. CENTER (TABBED PANE) ---
        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlCenter.setLayout(new BorderLayout(0, 0));
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(Color.WHITE);
        

        try {
            tabbedPane.addTab("Thông tin Bệnh nhân", new FrmHienThiTTBN()); 
        } catch (Exception e) {
            JPanel pnlError = new JPanel();
            pnlError.add(new JLabel("Lỗi: FrmHienThiTTBN phải extends JPanel"));
            tabbedPane.addTab("Thông tin Bệnh nhân", pnlError);
        }


        try {
             tabbedPane.addTab("Đo chỉ số sinh tồn", new FrmNhapChiSoTongQuatTroLyTQ(id));
        } catch (Exception e) {
            JPanel pnlError = new JPanel();
            pnlError.add(new JLabel("Lỗi: FrmNhapThongTinChiSoBenhBacSiTongQuat phải extends JPanel"));
            tabbedPane.addTab("Khám Lâm Sàng", pnlError);
        }


        
        pnlCenter.add(tabbedPane, BorderLayout.CENTER);



        JPanel pnlBottom = new JPanel();
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(new LineBorder(new Color(230, 230, 230), 1));
        pnlBottom.setPreferredSize(new Dimension(10, 60));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        JButton btnQuayLai = new RoundedButton("Quay lại", new Color(108, 117, 125));
        btnQuayLai.setPreferredSize(new Dimension(120, 35));
 
        btnQuayLai.addActionListener(e -> {
             FrmDanhSachChoKhamTroLyTQ ds = new FrmDanhSachChoKhamTroLyTQ();
             ds.setVisible(true);
             dispose();
        });
        pnlBottom.add(btnQuayLai);

    }


    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color backgroundColor;
        private int radius = 15;

        public RoundedButton(String text, Color color) {
            super(text);
            this.backgroundColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (getModel().isPressed()) g2.setColor(backgroundColor.darker());
            else g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}