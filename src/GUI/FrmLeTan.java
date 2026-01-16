package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import MODEL.Session;

public class FrmLeTan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlMainContent;

    private JButton btnMenuTrangChu;
    private JButton btnMenuDangKy;
    private JButton btnMenuBenhNhan;
    private JButton btnMenuTaiKhoan;
    private JButton btnXemLichCaNhan;
    private JButton btnLogout;

    private DashboardCard cardDangKy;
    private DashboardCard cardBenhNhan;
    private DashboardCard cardTaiKhoan;
    private DashboardCard cardLichSu;

    private final Color SIDEBAR_COLOR = new Color(0, 85, 170);
    private final Color BG_COLOR = new Color(240, 245, 250);
    private final Color TEXT_COLOR = new Color(60, 60, 60);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmLeTan frame = new FrmLeTan();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmLeTan() {
        setTitle("Hệ Thống Lễ Tân - Tiếp Đón Bệnh Nhân");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel pnlSidebar = new JPanel();
        pnlSidebar.setBackground(SIDEBAR_COLOR);
        pnlSidebar.setPreferredSize(new Dimension(260, 0));
        pnlSidebar.setLayout(null);
        contentPane.add(pnlSidebar, BorderLayout.WEST);

        JLabel lblBrandIcon = new JLabel("🏥");
        lblBrandIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        lblBrandIcon.setForeground(Color.WHITE);
        lblBrandIcon.setBounds(30, 30, 60, 60);
        pnlSidebar.add(lblBrandIcon);

        JLabel lblBrandName = new JLabel("PHÒNG KHÁM");
        lblBrandName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBrandName.setForeground(Color.WHITE);
        lblBrandName.setBounds(100, 35, 170, 30);
        pnlSidebar.add(lblBrandName);

        JLabel lblBrandSub = new JLabel("Khu vực Lễ Tân");
        lblBrandSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblBrandSub.setForeground(new Color(220, 220, 220));
        lblBrandSub.setBounds(100, 65, 150, 20);
        pnlSidebar.add(lblBrandSub);

        btnMenuTrangChu = new JButton("Trang chủ");
        btnMenuTrangChu.setBounds(0, 140, 260, 55);
        styleSidebarButton(btnMenuTrangChu);
        pnlSidebar.add(btnMenuTrangChu);

        btnMenuDangKy = new JButton("Đăng ký khám");
        btnMenuDangKy.setBounds(0, 200, 260, 55);
        styleSidebarButton(btnMenuDangKy);
        pnlSidebar.add(btnMenuDangKy);

        btnMenuBenhNhan = new JButton("Hồ sơ bệnh nhân");
        btnMenuBenhNhan.setBounds(0, 260, 260, 55);
        styleSidebarButton(btnMenuBenhNhan);
        pnlSidebar.add(btnMenuBenhNhan);

        btnMenuTaiKhoan = new JButton("Đổi mật khẩu");
        btnMenuTaiKhoan.setBounds(0, 320, 260, 55);
        styleSidebarButton(btnMenuTaiKhoan);
        pnlSidebar.add(btnMenuTaiKhoan);

        btnXemLichCaNhan = new JButton("Xem ca làm");
        btnXemLichCaNhan.setBounds(0, 380, 260, 55);
        styleSidebarButton(btnXemLichCaNhan);
        pnlSidebar.add(btnXemLichCaNhan);

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setBounds(30, 625, 200, 40);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setForeground(SIDEBAR_COLOR);
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setOpaque(true);
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSidebar.add(btnLogout);

        JPanel pnlRight = new JPanel();
        pnlRight.setBackground(BG_COLOR);
        pnlRight.setLayout(new BorderLayout());
        contentPane.add(pnlRight, BorderLayout.CENTER);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(0, 70));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        pnlRight.add(pnlHeader, BorderLayout.NORTH);

        JLabel lblPageTitle = new JLabel("TỔNG QUAN / TIẾP ĐÓN");
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPageTitle.setForeground(SIDEBAR_COLOR);
        pnlHeader.add(lblPageTitle);

        pnlMainContent = new JPanel();
        pnlMainContent.setBackground(BG_COLOR);
        pnlMainContent.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        pnlRight.add(pnlMainContent, BorderLayout.CENTER);

        cardDangKy = new DashboardCard("Đăng Ký Khám", "Tạo phiếu khám mới", new Color(0, 168, 255), "📝");
        pnlMainContent.add(cardDangKy);

        cardBenhNhan = new DashboardCard("Quản Lý Bệnh Nhân", "Tra cứu hồ sơ & Lịch sử", new Color(76, 209, 55), "👥");
        pnlMainContent.add(cardBenhNhan);

        cardTaiKhoan = new DashboardCard("Tài Khoản Cá Nhân", "Đổi mật khẩu & Thông tin", new Color(156, 136, 255), "🔒");
        pnlMainContent.add(cardTaiKhoan);

        cardLichSu = new DashboardCard("Lịch sử", "đăng ký khám bệnh", new Color(255, 159, 67), "📜");
        pnlMainContent.add(cardLichSu);

        initActions();
    }

    class DashboardCard extends JPanel {
        private Color accent;

        public DashboardCard(String title, String subtitle, Color accentColor, String icon) {
            this.accent = accentColor;
            setPreferredSize(new Dimension(300, 180));
            setOpaque(false);
            setLayout(null);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            JPanel strip = new JPanel();
            strip.setBackground(accentColor);
            strip.setBounds(0, 0, 8, 180);
            add(strip);

            JLabel lblIcon = new JLabel(icon);
            lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
            lblIcon.setBounds(30, 20, 60, 60);
            add(lblIcon);

            JLabel lblTitle = new JLabel(title);
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTitle.setForeground(TEXT_COLOR);
            lblTitle.setBounds(30, 90, 250, 30);
            add(lblTitle);

            JLabel lblSub = new JLabel(subtitle);
            lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblSub.setForeground(Color.GRAY);
            lblSub.setBounds(30, 120, 250, 20);
            add(lblSub);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBorder(BorderFactory.createLineBorder(accent, 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBorder(null);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        }
    }

    private void styleSidebarButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_COLOR);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 30, 0, 0));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 100, 200));
                btn.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE));
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_COLOR);
                btn.setBorder(new EmptyBorder(0, 30, 0, 0));
            }
        });
    }

    private void initActions() {

        ActionListener actionDangKy = e -> new FrmDangKyKhamBenh().setVisible(true);
        btnMenuDangKy.addActionListener(actionDangKy);

        cardDangKy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionDangKy.actionPerformed(null);
            }
        });

        ActionListener actionBenhNhan = e -> {
            new FrmQlbenhnhancuaLetan().setVisible(true);
            dispose();
        };

        btnMenuBenhNhan.addActionListener(actionBenhNhan);

        cardBenhNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBenhNhan.actionPerformed(null);
            }
        });

        cardLichSu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FrmLichSuDangKyKhamBenh().setVisible(true);
            }
        });

        ActionListener actionDoiMK = e -> {
            new FrmDoiMk().setVisible(true);
            dispose();
        };

        btnMenuTaiKhoan.addActionListener(actionDoiMK);

        cardTaiKhoan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionDoiMK.actionPerformed(null);
            }
        });

        btnXemLichCaNhan.addActionListener(e -> new FrmXemCaLamDanhChoNhanVien().setVisible(true));

        btnLogout.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            dispose();
        });
    }
}