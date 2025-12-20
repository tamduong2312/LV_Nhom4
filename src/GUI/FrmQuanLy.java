package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import MODEL.Session;

public class FrmQuanLy extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlMainContent;

    private final Color SIDEBAR_COLOR = new Color(36, 47, 65);
    private final Color ACTIVE_COLOR = new Color(52, 152, 219);
    private final Color BG_COLOR = new Color(245, 247, 251);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmQuanLy frame = new FrmQuanLy();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmQuanLy() {
        setTitle("Hệ Thống Quản Lý Phòng Khám Đa Khoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // --- SIDEBAR ---
        JPanel pnlSidebar = new JPanel();
        pnlSidebar.setBackground(SIDEBAR_COLOR);
        pnlSidebar.setPreferredSize(new Dimension(250, 0));
        pnlSidebar.setLayout(null);
        contentPane.add(pnlSidebar, BorderLayout.WEST);

        JLabel lblBrand = new JLabel("PHÒNG KHÁM");
        lblBrand.setForeground(Color.WHITE);
        lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBrand.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrand.setBounds(0, 30, 250, 40);
        pnlSidebar.add(lblBrand);
        
        JLabel lblSlogan = new JLabel("Health Care System");
        lblSlogan.setForeground(new Color(189, 195, 199));
        lblSlogan.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
        lblSlogan.setBounds(0, 65, 250, 20);
        pnlSidebar.add(lblSlogan);

        // --- MENU BUTTONS ---
        int btnY = 110;
        int gap = 50; // Giảm khoảng cách chút để đủ chỗ

        JButton btnDashboard = createSidebarButton("Tổng quan", "", btnY);
        pnlSidebar.add(btnDashboard);

        btnY += gap;
        JButton btnNhanVien = createSidebarButton("Quản lý Nhân viên", "", btnY);
        pnlSidebar.add(btnNhanVien);
        
        btnY += gap;
        JButton btnTaiKhoan = createSidebarButton("Quản lý Tài khoản", "", btnY);
        pnlSidebar.add(btnTaiKhoan);

        btnY += gap;
        JButton btnQuanLyCaLam = createSidebarButton("Lịch làm việc", "", btnY);
        pnlSidebar.add(btnQuanLyCaLam);
        
        btnY += gap;
        JButton btnThuoc = createSidebarButton("Danh mục Thuốc", "", btnY);
        pnlSidebar.add(btnThuoc);

        // --- NÚT MỚI: KHO & NHẬP HÀNG ---
        btnY += gap;
        JButton btnKhoNhap = createSidebarButton("Kho & Nhập hàng", "", btnY);
        pnlSidebar.add(btnKhoNhap);
        // --------------------------------

        btnY += gap;
        JButton btnDichVu = createSidebarButton("Quản lý Dịch vụ", "", btnY);
        pnlSidebar.add(btnDichVu);

        btnY += gap;
        JButton btnNCC = createSidebarButton("Nhà cung cấp", "", btnY);
        pnlSidebar.add(btnNCC);

        btnY += gap;
        JButton BtnThongKe = createSidebarButton("Báo cáo Thống kê", "", btnY);
        pnlSidebar.add(BtnThongKe);
       
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setBounds(25, 700, 200, 40);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(e -> {
             FrmLogin login = new FrmLogin(); login.setVisible(true);
            dispose();
        });
        pnlSidebar.add(btnLogout);

        // --- CENTER ---
        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setLayout(new BorderLayout());
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(0, 60));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        JLabel lblTitlePage = new JLabel("TỔNG QUAN QUẢN LÝ");
        lblTitlePage.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitlePage.setForeground(SIDEBAR_COLOR);
        pnlHeader.add(lblTitlePage);
        pnlCenter.add(pnlHeader, BorderLayout.NORTH);

        pnlMainContent = new JPanel();
        pnlMainContent.setBackground(BG_COLOR);
        pnlMainContent.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        pnlCenter.add(pnlMainContent, BorderLayout.CENTER);

        // --- CARDS ---
        JPanel cardNV = createDashboardCard("Nhân sự", "Quản lý hồ sơ", new Color(52, 152, 219), "👨‍⚕️");
        cardNV.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { openFormNhanVien(); }
        });
        pnlMainContent.add(cardNV);
        
        JPanel cardThongKe = createDashboardCard("Thống kê", "Xem báo cáo", new Color(155, 89, 182), "📊");
        cardThongKe.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               FrmBaoCaoThongKe q = new FrmBaoCaoThongKe(); q.setVisible(true);
            }
        });
        pnlMainContent.add(cardThongKe);

        // --- CARD MỚI: KHO & NHẬP HÀNG ---
        JPanel cardKho = createDashboardCard("Kho & Nhập hàng", "Kiểm kê & Nhập", new Color(243, 156, 18), "📦");
        cardKho.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // MỞ TRỰC TIẾP TAB NHẬP HÀNG
                FrmQLKhoThuoc kho = new FrmQLKhoThuoc("NHAP_HANG");
                kho.setVisible(true);
                dispose();
            }
        });
        pnlMainContent.add(cardKho);
        // ---------------------------------

        JPanel cardThuoc = createDashboardCard("Danh mục Thuốc", "Thông tin & Giá", new Color(231, 76, 60), "💊");
        cardThuoc.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                FrmQuanLyThuoc t = new FrmQuanLyThuoc("ADMIN");
                t.setVisible(true);
                dispose();
            }
        });
        pnlMainContent.add(cardThuoc);

        JPanel cardDichVu = createDashboardCard("Dịch vụ", "Khám & Xét nghiệm", new Color(22, 160, 133), "💉");
        cardDichVu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                FrmQuanLyDichVu dv = new FrmQuanLyDichVu(); dv.setVisible(true); dispose();
            }
        });
        pnlMainContent.add(cardDichVu);

        // --- ACTIONS ---
        btnNhanVien.addActionListener(e -> openFormNhanVien());
        
        btnTaiKhoan.addActionListener(e -> {
        	FrmQuanLyTaiKhoan a = new FrmQuanLyTaiKhoan(); a.setVisible(true); dispose();
        });
        
        BtnThongKe.addActionListener(e -> {
            FrmBaoCaoThongKe q = new FrmBaoCaoThongKe(); q.setVisible(true);
        });
        
        btnQuanLyCaLam.addActionListener(e -> {
        	FrmPhanCongCaLam a = new FrmPhanCongCaLam(); a.setVisible(true);
        });

        btnThuoc.addActionListener(e -> {
            FrmQuanLyThuoc t = new FrmQuanLyThuoc("ADMIN"); t.setVisible(true); 
        });

        // ACTION CHO NÚT KHO MỚI
        btnKhoNhap.addActionListener(e -> {
            // Truyền tham số "NHAP_HANG" để mở thẳng tab nhập hàng
            FrmQLKhoThuoc kho = new FrmQLKhoThuoc("QUAN_TRI_VIEN"); 
            kho.setVisible(true);
     
        });

        btnDichVu.addActionListener(e -> {
            FrmQuanLyDichVu dv = new FrmQuanLyDichVu(); dv.setVisible(true); dispose();
        });

        btnNCC.addActionListener(e -> {
            FrmQuanLyNCC ncc = new FrmQuanLyNCC(); ncc.setVisible(true);
        });
    }

    private void openFormNhanVien() {
        frmQuanLyNhanVien ql = new frmQuanLyNhanVien();
        ql.setVisible(true);
        dispose();
    }

    private JButton createSidebarButton(String text, String iconPath, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(0, y, 250, 50);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(new Color(200, 200, 200));
        btn.setBackground(SIDEBAR_COLOR);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 30, 0, 0)); 
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(28, 36, 50));
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, ACTIVE_COLOR)); 
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_COLOR);
                btn.setForeground(new Color(200, 200, 200));
                btn.setBorder(new EmptyBorder(0, 30, 0, 0));
            }
        });
        return btn;
    }

    private JPanel createDashboardCard(String title, String subtitle, Color color, String iconStr) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); 
            }
        };
        card.setPreferredSize(new Dimension(250, 140));
        card.setOpaque(false);
        card.setLayout(null);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel pnlStrip = new JPanel();
        pnlStrip.setBackground(color);
        pnlStrip.setBounds(0, 0, 10, 140);
        card.add(pnlStrip); 

        JPanel pnlIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 30)); 
                g2.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        pnlIcon.setBounds(20, 20, 50, 50);
        pnlIcon.setOpaque(false);
        card.add(pnlIcon);
        
        JLabel lblIcon = new JLabel(iconStr); 
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        lblIcon.setForeground(color);
        pnlIcon.add(lblIcon);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(50, 50, 50));
        lblTitle.setBounds(20, 80, 200, 25);
        card.add(lblTitle);

        JLabel lblSub = new JLabel(subtitle);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(Color.GRAY);
        lblSub.setBounds(20, 105, 200, 20);
        card.add(lblSub);

        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createLineBorder(color, 1));
            }
            public void mouseExited(MouseEvent e) {
                card.setBorder(null);
            }
        });
        return card;
    }
}