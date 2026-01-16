package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import MODEL.Session;

public class FrmQuanLy extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    private JPanel pnlSidebar;
    private JLabel lblBrand;
    private JLabel lblSlogan;
    private JButton btnDashboard;
    private JButton btnNhanVien;
    private JButton btnTaiKhoan;
    private JButton btnQuanLyCaLam;
    private JButton btnThuoc;
    private JButton btnKhoNhap;
    private JButton btnDichVu;
    private JButton btnBenhLy; 
    private JButton btnNCC;
    private JButton btnThongKe;
    private JButton btnLogout;

    private JPanel pnlCenter;
    private JPanel pnlHeader;
    private JLabel lblTitlePage;
    private JPanel pnlMainContent;

    private JPanel cardNV;
    private JPanel pnlStripNV;
    private JLabel lblIconNV, lblTitleNV, lblSubNV;

    private JPanel cardThongKe;
    private JPanel pnlStripTK;
    private JLabel lblIconTK, lblTitleTK, lblSubTK;

    private JPanel cardKho;
    private JPanel pnlStripKho;
    private JLabel lblIconKho, lblTitleKho, lblSubKho;

    private JPanel cardThuoc;
    private JPanel pnlStripThuoc;
    private JLabel lblIconThuoc, lblTitleThuoc, lblSubThuoc;

    private JPanel cardDichVu;
    private JPanel pnlStripDichVu;
    private JLabel lblIconDichVu, lblTitleDichVu, lblSubDichVu;

    private JPanel cardBenhLy; 
    private JPanel pnlStripBenhLy;
    private JLabel lblIconBenhLy, lblTitleBenhLy, lblSubBenhLy;
    
    private JButton btnPhongChucNang;
    private JPanel cardPhongChucNang;

    private final Color SIDEBAR_COLOR = new Color(36, 47, 65);
    private final Color ACTIVE_COLOR = new Color(52, 152, 219);
    private final Color BG_COLOR = new Color(245, 247, 251);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
        pnlSidebar = new JPanel();
        pnlSidebar.setBackground(SIDEBAR_COLOR);
        pnlSidebar.setPreferredSize(new Dimension(250, 0));
        pnlSidebar.setLayout(null);
        contentPane.add(pnlSidebar, BorderLayout.WEST);

        lblBrand = new JLabel("PHÒNG KHÁM");
        lblBrand.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrand.setForeground(Color.WHITE);
        lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBrand.setBounds(0, 30, 250, 40);
        pnlSidebar.add(lblBrand);

        lblSlogan = new JLabel("Health Care System");
        lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
        lblSlogan.setForeground(new Color(189, 195, 199));
        lblSlogan.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSlogan.setBounds(0, 65, 250, 20);
        pnlSidebar.add(lblSlogan);

        int btnY = 110;
        int gap = 45; 

        btnDashboard = new JButton("Tổng quan");
        btnDashboard.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnDashboard);
        pnlSidebar.add(btnDashboard);

        btnY += gap;
        btnNhanVien = new JButton("Quản lý Nhân viên");
        btnNhanVien.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnNhanVien);
        pnlSidebar.add(btnNhanVien);

        btnY += gap;
        btnTaiKhoan = new JButton("Quản lý Tài khoản");
        btnTaiKhoan.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnTaiKhoan);
        pnlSidebar.add(btnTaiKhoan);

        btnY += gap;
        btnQuanLyCaLam = new JButton("Lịch làm việc");
        btnQuanLyCaLam.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnQuanLyCaLam);
        pnlSidebar.add(btnQuanLyCaLam);

        btnY += gap;
        btnThuoc = new JButton("Danh mục Thuốc");
        btnThuoc.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnThuoc);
        pnlSidebar.add(btnThuoc);

        btnY += gap;
        btnKhoNhap = new JButton("Kho & Nhập hàng");
        btnKhoNhap.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnKhoNhap);
        pnlSidebar.add(btnKhoNhap);

        btnY += gap;
        btnDichVu = new JButton("Quản lý Dịch vụ");
        btnDichVu.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnDichVu);
        pnlSidebar.add(btnDichVu);

    
        btnY += gap;
        btnBenhLy = new JButton("Danh mục bệnh lý");
        btnBenhLy.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnBenhLy);
        pnlSidebar.add(btnBenhLy);
        
        btnY += gap;
        btnPhongChucNang = new JButton("Quản lý Phòng");
        btnPhongChucNang.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnPhongChucNang);
        pnlSidebar.add(btnPhongChucNang);

        btnY += gap;
        btnNCC = new JButton("Nhà cung cấp");
        btnNCC.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnNCC);
        pnlSidebar.add(btnNCC);

        btnY += gap;
        btnThongKe = new JButton("Báo cáo Thống kê");
        btnThongKe.setBounds(0, btnY, 250, 45);
        setupSidebarButtonStyle(btnThongKe);
        pnlSidebar.add(btnThongKe);

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setBounds(20, 593, 200, 40);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setFocusPainted(false);
        btnLogout.setOpaque(true);
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSidebar.add(btnLogout);

        // --- CENTER ---
        pnlCenter = new JPanel();
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setLayout(new BorderLayout());
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        pnlHeader = new JPanel();
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(0, 60));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        lblTitlePage = new JLabel("TỔNG QUAN QUẢN LÝ");
        lblTitlePage.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitlePage.setForeground(SIDEBAR_COLOR);
        pnlHeader.add(lblTitlePage);
        pnlCenter.add(pnlHeader, BorderLayout.NORTH);

        pnlMainContent = new JPanel();
        pnlMainContent.setBackground(BG_COLOR);
        pnlMainContent.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        pnlCenter.add(pnlMainContent, BorderLayout.CENTER);

        // NHÂN SỰ
        cardNV = createVisualPanel(new Color(52, 152, 219), "👨‍⚕️", "Nhân sự", "Quản lý hồ sơ");
        pnlMainContent.add(cardNV);

        // THỐNG KÊ
        cardThongKe = createVisualPanel(new Color(155, 89, 182), "📊", "Thống kê", "Xem báo cáo");
        pnlMainContent.add(cardThongKe);

        // KHO
        cardKho = createVisualPanel(new Color(243, 156, 18), "📦", "Kho & Nhập hàng", "Kiểm kê & Nhập");
        pnlMainContent.add(cardKho);

        // THUỐC
        cardThuoc = createVisualPanel(new Color(231, 76, 60), "💊", "Danh mục Thuốc", "Thông tin & Giá");
        pnlMainContent.add(cardThuoc);

        // DỊCH VỤ
        cardDichVu = createVisualPanel(new Color(22, 160, 133), "💉", "Dịch vụ", "Khám & Xét nghiệm");
        pnlMainContent.add(cardDichVu);

        // CARD MỚI: BỆNH LÝ
        cardBenhLy = createVisualPanel(new Color(46, 204, 113), "📋", "Danh mục bệnh", "Mã ICD & Triệu chứng");
        pnlMainContent.add(cardBenhLy);

        cardPhongChucNang = createVisualPanel(new Color(142, 68, 173), "🏢", "Phòng chức năng", "Cấu hình & Vị trí");
        pnlMainContent.add(cardPhongChucNang);

        initLogicActions();
    }

    private JPanel createVisualPanel(Color color, String icon, String title, String sub) {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(250, 140));
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        p.setCursor(new Cursor(Cursor.HAND_CURSOR));
        p.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));

        JPanel strip = new JPanel();
        strip.setBackground(color);
        strip.setBounds(0, 0, 10, 140);
        p.add(strip);

        JLabel ico = new JLabel(icon);
        ico.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        ico.setBounds(25, 20, 50, 50);
        p.add(ico);

        JLabel tit = new JLabel(title);
        tit.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tit.setForeground(new Color(50, 50, 50));
        tit.setBounds(25, 80, 200, 25);
        p.add(tit);

        JLabel s = new JLabel(sub);
        s.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        s.setForeground(Color.GRAY);
        s.setBounds(25, 105, 200, 20);
        p.add(s);

        return p;
    }

    private void setupSidebarButtonStyle(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(new Color(200, 200, 200));
        btn.setBackground(SIDEBAR_COLOR);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 30, 0, 0));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(28, 36, 50));
                btn.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_COLOR);
                btn.setForeground(new Color(200, 200, 200));
            }
        });
    }

    private void initLogicActions() {
        // Nhân viên
        ActionListener actionNV = e -> { 
            new frmQuanLyNhanVien().setVisible(true); 
            dispose(); 
        };
        btnNhanVien.addActionListener(actionNV);
        cardNV.addMouseListener(new MouseAdapter() { 
            @Override 
            public void mouseClicked(MouseEvent e) { 
                actionNV.actionPerformed(null); 
            } 
        });

        // Thống kê
        ActionListener actionTK = e -> new FrmBaoCaoThongKe().setVisible(true);
        btnThongKe.addActionListener(actionTK);
        cardThongKe.addMouseListener(new MouseAdapter() { 
            @Override 
            public void mouseClicked(MouseEvent e) { 
                actionTK.actionPerformed(null); 
            } 
        });

        // Kho
        ActionListener actionKho = e -> { 
            new FrmQLKhoThuoc("QUAN_TRI_VIEN").setVisible(true); 
            dispose(); 
        };
        btnKhoNhap.addActionListener(actionKho);
        cardKho.addMouseListener(new MouseAdapter() { 
            @Override 
            public void mouseClicked(MouseEvent e) { 
                actionKho.actionPerformed(null); 
            } 
        });

        // Thuốc
        ActionListener actionThuoc = e -> { 
            new FrmQuanLyThuoc("ADMIN").setVisible(true); 
            dispose(); 
        };
        btnThuoc.addActionListener(actionThuoc);
        cardThuoc.addMouseListener(new MouseAdapter() { 
            @Override 
            public void mouseClicked(MouseEvent e) { 
                actionThuoc.actionPerformed(null); 
            } 
        });

        // Dịch vụ
        ActionListener actionDV = e -> { 
            new FrmQuanLyDichVu().setVisible(true); 
            dispose(); 
        };
        btnDichVu.addActionListener(actionDV);
        cardDichVu.addMouseListener(new MouseAdapter() { 
            @Override 
            public void mouseClicked(MouseEvent e) { 
                actionDV.actionPerformed(null); 
            } 
        });

        // Bệnh lý
        ActionListener actionBenhLy = e -> {
             new FrmQuanLyDanhMucBenhLy().setVisible(true); 
        };
        btnBenhLy.addActionListener(actionBenhLy);
        cardBenhLy.addMouseListener(new MouseAdapter() { 
            @Override 
            public void mouseClicked(MouseEvent e) { 
                actionBenhLy.actionPerformed(null); 
            } 
        });
        
        ActionListener actionPhong = e -> {
            new FrmQuanLyPhongChucNang().setVisible(true);
            dispose();
        };
        btnPhongChucNang.addActionListener(actionPhong);
        cardPhongChucNang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionPhong.actionPerformed(null);
            }
        });

        btnTaiKhoan.addActionListener(e -> { 
            new FrmQuanLyTaiKhoan().setVisible(true); 
            dispose(); 
        });
        
        btnQuanLyCaLam.addActionListener(e -> new FrmPhanCongCaLam().setVisible(true));
        btnNCC.addActionListener(e -> new FrmQuanLyNCC().setVisible(true));
        
        btnLogout.addActionListener(e -> { 
            new FrmLogin().setVisible(true); 
            dispose(); 
        });
    }
}