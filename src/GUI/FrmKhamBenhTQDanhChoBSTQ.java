package GUI;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import MODEL.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FrmKhamBenhTQDanhChoBSTQ extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    private final Color COLOR_PRIMARY = new Color(0, 123, 255);
    private final Color COLOR_TAB_BG = new Color(22, 66, 88);
    private final Color COLOR_BG_GRAY = new Color(240, 242, 245);
    private final Color COLOR_BTN_HISTORY = new Color(52, 152, 219); 
    private final Color COLOR_BTN_VITAL = new Color(39, 174, 96);  
    private static String tenbn;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ex) {}
                FrmKhamBenhTQDanhChoBSTQ frame = new FrmKhamBenhTQDanhChoBSTQ(tenbn);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmKhamBenhTQDanhChoBSTQ(String tenbn) {
        setTitle("Giao diện Bác sĩ Tổng quát");
        this.tenbn = tenbn;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 900);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(COLOR_BG_GRAY);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setPreferredSize(new Dimension(980, 1200));

        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        setContentPane(scrollPane);

        JPanel pnlHeaderContainer = new JPanel();
        contentPane.add(pnlHeaderContainer, BorderLayout.NORTH);
        pnlHeaderContainer.setLayout(new BoxLayout(pnlHeaderContainer, BoxLayout.Y_AXIS));

        JPanel pnlTopBar = new JPanel(new BorderLayout());
        pnlTopBar.setBackground(Color.WHITE);
        pnlTopBar.setPreferredSize(new Dimension(0, 50));
        pnlTopBar.setBorder(new MatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        pnlHeaderContainer.add(pnlTopBar);

        JLabel lblTiepNhan = new JLabel(" Tiếp nhận");
        lblTiepNhan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTiepNhan.setForeground(new Color(50, 50, 50));
        pnlTopBar.add(lblTiepNhan, BorderLayout.WEST);

        JLabel lblBacSi = new JLabel("BS." + Session.TenNhanVien + "  "); 
        lblBacSi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBacSi.setForeground(new Color(50, 50, 50));
        pnlTopBar.add(lblBacSi, BorderLayout.EAST);

        JPanel pnlPatientInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); 
        pnlPatientInfo.setBackground(new Color(245, 245, 245));
        pnlPatientInfo.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlHeaderContainer.add(pnlPatientInfo);

        JButton btnLichSu = new CustomButton("LỊCH SỬ KHÁM", COLOR_BTN_HISTORY);
        btnLichSu.setPreferredSize(new Dimension(120, 30));
        btnLichSu.addActionListener(e -> {
            FrmLoadLichSuKham q = new FrmLoadLichSuKham(Session.mabenhnhan);
            q.setVisible(true);
        });
        pnlPatientInfo.add(btnLichSu);

        JButton btnXemChiSo = new CustomButton("XEM CHỈ SỐ SINH TỒN", COLOR_BTN_VITAL);
        btnXemChiSo.setPreferredSize(new Dimension(180, 30));
        btnXemChiSo.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Thông tin sinh tồn", false); 
            FrmXemChiSoTongQuatCuaBSTQ content = new FrmXemChiSoTongQuatCuaBSTQ(Session.maDangKyHienTai,Session.mabenhnhan);
            dialog.getContentPane().add(content);
            dialog.setSize(800, 500);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true); 
        });
        pnlPatientInfo.add(btnXemChiSo);

        String infoHtml = "<html><span style='font-size:14px; padding-left:10px;'><b>Bệnh nhân: </b> " + tenbn;
        JLabel lblInfo = new JLabel(infoHtml);
        pnlPatientInfo.add(lblInfo);

        JPanel pnlTimeInfo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlTimeInfo.setBackground(Color.WHITE);
        pnlTimeInfo.setBorder(new MatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
        pnlHeaderContainer.add(pnlTimeInfo);

        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'lúc' HH:mm"));
        JLabel lblTime = new JLabel("Hồ sơ khám hôm nay: " + currentDateTime + "  ");
        lblTime.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTime.setForeground(new Color(0, 102, 102));
        pnlTimeInfo.add(lblTime);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(COLOR_TAB_BG);
        tabbedPane.setForeground(Color.WHITE);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Khám bệnh", null, new FrmKhamLamSang1(Session.maphieukham,this.tenbn,""), null);
        tabbedPane.addTab("Chỉ Định dịch vụ", null, new PnlChiDinhDichVu(Session.maphieukham,""), null);
        JPanel panelThuoc = new JPanel(); panelThuoc.setBackground(Color.WHITE);
        tabbedPane.addTab("Kê toa thuốc", null, new ToaThuoc1(Session.maphieukham,""), null);

        if (!java.beans.Beans.isDesignTime()) {
            tabbedPane.setUI(new FlatTabbedPaneUI());
        }
    }

    class CustomButton extends JButton {
        private Color bgColor;
        private Color hoverColor;

        public CustomButton(String text, Color bg) {
            super(text);
            this.bgColor = bg;
            this.hoverColor = bg.darker(); 
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { bgColor = hoverColor; repaint(); }
                public void mouseExited(MouseEvent e) { bgColor = bg; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); 
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private class FlatTabbedPaneUI extends BasicTabbedPaneUI {
        @Override protected void installDefaults() { super.installDefaults(); contentBorderInsets = new Insets(0, 0, 0, 0); tabAreaInsets = new Insets(0, 0, 0, 0); }
        @Override protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) { g.setColor(COLOR_TAB_BG); g.fillRect(0, 0, tabPane.getWidth(), calculateMaxTabHeight(tabPlacement)); super.paintTabArea(g, tabPlacement, selectedIndex); }
        @Override protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) { Graphics2D g2 = (Graphics2D) g; if (isSelected) g2.setColor(new Color(0, 123, 255)); else g2.setColor(COLOR_TAB_BG); g2.fillRect(x, y, w, h); }
        @Override protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) { }
        @Override protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) { }
    }
}