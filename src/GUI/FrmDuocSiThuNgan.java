package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import MODEL.Session;

public class FrmDuocSiThuNgan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JPanel pnlSidebar;
    private JLabel lblBrandIcon;
    private JLabel lblBrandName;
    private JLabel lblBrandSub;
    private JButton btnMenuTrangChu;
    private JButton btnMenuDangKy;
    private JButton btnMenuThuoc;
    private JButton btnMenuTaiKhoan;
    private JButton btnXemLichCaNhan;
    private JButton btnLichSu;
    private JButton btnLogout;

    private JPanel pnlRight;
    private JPanel pnlHeader;
    private JLabel lblPageTitle;
    private JPanel pnlMainContent;

    private JPanel cardDangKy;
    private JPanel cardThuoc;
    private JPanel cardTaiKhoan;
    private JPanel cardLichSu;

    private final Color SIDEBAR_COLOR = new Color(0, 85, 170);
    private final Color BG_COLOR = new Color(240, 245, 250);
    private final Color TEXT_COLOR = new Color(60, 60, 60);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                if (Session.role == null) {
                    Session.role = "DUOC_SI";
                }
                FrmDuocSiThuNgan frame = new FrmDuocSiThuNgan();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmDuocSiThuNgan() {
        setTitle("Hệ Thống Nhân Viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        pnlSidebar = new JPanel();
        pnlSidebar.setBackground(SIDEBAR_COLOR);
        pnlSidebar.setPreferredSize(new Dimension(260, 0));
        pnlSidebar.setLayout(null);
        contentPane.add(pnlSidebar, BorderLayout.WEST);

        lblBrandIcon = new JLabel("🏥");
        lblBrandIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        lblBrandIcon.setForeground(Color.WHITE);
        lblBrandIcon.setBounds(30, 30, 60, 60);
        pnlSidebar.add(lblBrandIcon);

        lblBrandName = new JLabel("PHÒNG KHÁM");
        lblBrandName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBrandName.setForeground(Color.WHITE);
        lblBrandName.setBounds(100, 35, 170, 30);
        pnlSidebar.add(lblBrandName);

        lblBrandSub = new JLabel("Khu vực Nhân viên");
        lblBrandSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblBrandSub.setForeground(new Color(220, 220, 220));
        lblBrandSub.setBounds(100, 65, 150, 20);
        pnlSidebar.add(lblBrandSub);

        btnMenuTrangChu = new JButton("Trang chủ");
        btnMenuTrangChu.setBounds(0, 140, 260, 55);
        setupSidebarButtonStyle(btnMenuTrangChu);
        pnlSidebar.add(btnMenuTrangChu);

        btnMenuDangKy = new JButton("Danh sách khám bệnh");
        btnMenuDangKy.setBounds(0, 200, 260, 55);
        setupSidebarButtonStyle(btnMenuDangKy);
        pnlSidebar.add(btnMenuDangKy);

        btnMenuThuoc = new JButton("Quản lý Thuốc");
        btnMenuThuoc.setBounds(0, 260, 260, 55);
        setupSidebarButtonStyle(btnMenuThuoc);
        pnlSidebar.add(btnMenuThuoc);

        if (!"DUOC_SI".equals(Session.role)) {
            btnMenuThuoc.setVisible(false);
        }

        btnMenuTaiKhoan = new JButton("Đổi mật khẩu");
        btnMenuTaiKhoan.setBounds(0, 320, 260, 55);
        setupSidebarButtonStyle(btnMenuTaiKhoan);
        pnlSidebar.add(btnMenuTaiKhoan);

        btnXemLichCaNhan = new JButton("Xem ca làm");
        btnXemLichCaNhan.setBounds(0, 380, 260, 55);
        setupSidebarButtonStyle(btnXemLichCaNhan);
        pnlSidebar.add(btnXemLichCaNhan);

        btnLichSu = new JButton("Lịch sử");
        btnLichSu.setBounds(0, 440, 260, 55);
        setupSidebarButtonStyle(btnLichSu);
        pnlSidebar.add(btnLichSu);

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setBounds(30, 619, 200, 40);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setForeground(SIDEBAR_COLOR);
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setOpaque(true);
        btnLogout.setBorderPainted(false);
        pnlSidebar.add(btnLogout);

        // --- RIGHT CONTENT ---
        pnlRight = new JPanel();
        pnlRight.setBackground(BG_COLOR);
        pnlRight.setLayout(new BorderLayout());
        contentPane.add(pnlRight, BorderLayout.CENTER);

        pnlHeader = new JPanel();
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(0, 70));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        pnlRight.add(pnlHeader, BorderLayout.NORTH);

        lblPageTitle = new JLabel("TỔNG QUAN / " + (Session.role != null ? Session.role : "NHÂN VIÊN"));
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPageTitle.setForeground(SIDEBAR_COLOR);
        pnlHeader.add(lblPageTitle);

        pnlMainContent = new JPanel();
        pnlMainContent.setBackground(BG_COLOR);
        pnlMainContent.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        pnlRight.add(pnlMainContent, BorderLayout.CENTER);

        cardDangKy = createVisualCard("Danh sách Khám", "Tiếp nhận & Xử lý", new Color(0, 168, 255), "📝");
        pnlMainContent.add(cardDangKy);

        cardThuoc = createVisualCard("Quản Lý Thuốc", "Danh mục dược phẩm", new Color(46, 204, 113), "💊");
        pnlMainContent.add(cardThuoc);
        if (!"DUOC_SI".equals(Session.role)) {
            cardThuoc.setVisible(false);
        }

        cardTaiKhoan = createVisualCard("Tài Khoản", "Đổi mật khẩu & Thông tin", new Color(156, 136, 255), "🔒");
        pnlMainContent.add(cardTaiKhoan);

        cardLichSu = createVisualCard("Lịch Sử", "Tra cứu giao dịch", new Color(255, 159, 67), "📜");
        pnlMainContent.add(cardLichSu);

        initLogicActions();
    }

    private JPanel createVisualCard(String title, String sub, Color color, String icon) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(300, 180));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));

        JPanel strip = new JPanel();
        strip.setBackground(color);
        strip.setBounds(0, 0, 8, 180);
        card.add(strip);

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblIcon.setBounds(30, 20, 60, 60);
        card.add(lblIcon);

        JLabel lblT = new JLabel(title);
        lblT.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblT.setForeground(TEXT_COLOR);
        lblT.setBounds(30, 90, 250, 30);
        card.add(lblT);

        JLabel lblS = new JLabel(sub);
        lblS.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblS.setForeground(Color.GRAY);
        lblS.setBounds(30, 120, 250, 20);
        card.add(lblS);

        return card;
    }

    private void setupSidebarButtonStyle(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_COLOR);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 30, 0, 0));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initLogicActions() {

        ActionListener actionDangKy = e -> {
            if ("THU_NGAN".equals(Session.role)) {
                new FrmThuNgan().setVisible(true);
                dispose();
            } else {
                JDialog dialog = new JDialog();
                dialog.getContentPane().add(new FrmDuocSi1());
                dialog.setSize(1200, 800);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        };

        btnMenuDangKy.addActionListener(actionDangKy);

        cardDangKy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionDangKy.actionPerformed(null);
            }
        });

        btnMenuThuoc.addActionListener(e -> new FrmQuanLyThuoc(Session.role).setVisible(true));

        cardThuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FrmQuanLyThuoc(Session.role).setVisible(true);
            }
        });

        ActionListener actionLichSu = e -> {
            if ("THU_NGAN".equals(Session.role)) {
                new FrmLichSuGiaoDich().setVisible(true);
            } else {
                new FrmLichSuCapThuoc().setVisible(true);
            }
        };

        btnLichSu.addActionListener(actionLichSu);

        cardLichSu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionLichSu.actionPerformed(null);
            }
        });

        btnMenuTaiKhoan.addActionListener(e -> {
            new FrmDoiMk().setVisible(true);
            dispose();
        });

        cardTaiKhoan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FrmDoiMk().setVisible(true);
                dispose();
            }
        });

        btnXemLichCaNhan.addActionListener(e -> new FrmXemCaLamDanhChoNhanVien().setVisible(true));

        btnLogout.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            dispose();
        });
    }
}