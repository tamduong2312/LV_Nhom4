package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import MODEL.Session;

public class FrmLamViecBacSi extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlMainContent;

    private JButton btnHome;
    private JButton btnDSKham;
    private JButton btnTaiKhoan;
    private JButton btnXemCaLam;
    private JButton btnLogout;

    private JPanel cardDSKham;
    private JPanel cardCaLam;
    private JPanel cardTaiKhoan;

    private final Color SIDEBAR_COLOR = new Color(0, 85, 170);
    private final Color BG_COLOR = new Color(240, 245, 250);
    private final Color TEXT_COLOR = new Color(60, 60, 60);
    private final Color HEADER_COLOR = Color.WHITE;
    private final Color RED_COLOR = new Color(220, 53, 69);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmLamViecBacSi frame = new FrmLamViecBacSi();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmLamViecBacSi() {

        String tenBacSi = "Bác Sĩ";
        try {
            if (!java.beans.Beans.isDesignTime() && Session.TenNhanVien != null) {
                tenBacSi = Session.TenNhanVien;
            }
        } catch (Exception e) {
        }

        setTitle("Hệ Thống Bác sĩ");
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

        JLabel lblIconLogo = new JLabel("👨‍⚕️");
        lblIconLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        lblIconLogo.setForeground(Color.WHITE);
        lblIconLogo.setBounds(52, 11, 75, 78);
        pnlSidebar.add(lblIconLogo);

        JLabel lblSubUser = new JLabel("Bác Sĩ: " + tenBacSi);
        lblSubUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubUser.setForeground(new Color(220, 220, 220));
        lblSubUser.setBounds(30, 89, 200, 20);
        pnlSidebar.add(lblSubUser);

        btnHome = new JButton("Trang chủ");
        btnHome.setBounds(0, 140, 260, 55);
        styleButtonSidebar(btnHome);
        pnlSidebar.add(btnHome);

        btnDSKham = new JButton("Danh sách chờ khám");
        btnDSKham.setBounds(0, 200, 260, 55);
        styleButtonSidebar(btnDSKham);
        pnlSidebar.add(btnDSKham);

        btnTaiKhoan = new JButton("Tài khoản cá nhân");
        btnTaiKhoan.setBounds(0, 260, 260, 55);
        styleButtonSidebar(btnTaiKhoan);
        pnlSidebar.add(btnTaiKhoan);

        btnXemCaLam = new JButton("Xem ca làm");
        btnXemCaLam.setBounds(0, 320, 260, 55);
        styleButtonSidebar(btnXemCaLam);
        pnlSidebar.add(btnXemCaLam);

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setBounds(30, 560, 200, 40);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setBackground(RED_COLOR);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setOpaque(true);
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSidebar.add(btnLogout);

        JPanel pnlRight = new JPanel();
        pnlRight.setBackground(BG_COLOR);
        pnlRight.setLayout(new BorderLayout());
        contentPane.add(pnlRight, BorderLayout.CENTER);

        JPanel pnlHeaderTop = new JPanel();
        pnlHeaderTop.setBackground(HEADER_COLOR);
        pnlHeaderTop.setPreferredSize(new Dimension(0, 70));
        pnlHeaderTop.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        pnlHeaderTop.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        pnlRight.add(pnlHeaderTop, BorderLayout.NORTH);

        JLabel lblPageTitle = new JLabel("TỔNG QUAN CÔNG VIỆC");
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPageTitle.setForeground(SIDEBAR_COLOR);
        pnlHeaderTop.add(lblPageTitle);

        pnlMainContent = new JPanel();
        pnlMainContent.setBackground(BG_COLOR);
        pnlMainContent.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        pnlRight.add(pnlMainContent, BorderLayout.CENTER);

        cardDSKham = new JPanel();
        setupCardManual(cardDSKham, "Tiếp Nhận & Khám", "Xem danh sách bệnh nhân chờ", new Color(0, 168, 255), "📝");
        pnlMainContent.add(cardDSKham);

        cardCaLam = new JPanel();
        setupCardManual(cardCaLam, "Xem Ca Làm", "Lịch trực trong tuần", new Color(46, 204, 113), "📅");
        pnlMainContent.add(cardCaLam);

        cardTaiKhoan = new JPanel();
        setupCardManual(cardTaiKhoan, "Cài Đặt Tài Khoản", "Đổi mật khẩu & Thông tin", new Color(156, 136, 255), "🔒");
        pnlMainContent.add(cardTaiKhoan);

        initController();
    }

    private void setupCardManual(JPanel card, String title, String sub, Color accent, String emoji) {
        card.setPreferredSize(new Dimension(300, 180));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));

        JPanel strip = new JPanel();
        strip.setBackground(accent);
        strip.setBounds(0, 0, 8, 180);
        card.add(strip);

        JLabel lblEmoji = new JLabel(emoji);
        lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblEmoji.setBounds(30, 20, 60, 60);
        card.add(lblEmoji);

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
    }

    private void styleButtonSidebar(JButton btn) {
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
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_COLOR);
            }
        });
    }

    private void initController() {
        if (java.beans.Beans.isDesignTime()) {
            return;
        }

        ActionListener actionOpenDSKham = e -> {
            String ck = Session.chuyenKhoa;
            if (ck == null) {
                return;
            }
            switch (ck) {
                case "Nội tổng quát":
                    new FrmDanhSachKhamBenhCuaBSTQ().setVisible(true);
                    break;
                case "Chẩn đoán hình ảnh":
                case "Xét nghiệm":
                    new FrmDanhSachChoBacSiChanDoanHinhAnh().setVisible(true);
                    break;
                default:
                    new FrmDanhSachKhamBenhCuaBSCK().setVisible(true);
                    break;
            }
            dispose();
        };

        btnDSKham.addActionListener(actionOpenDSKham);
        cardDSKham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionOpenDSKham.actionPerformed(null);
            }
        });

        btnLogout.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            dispose();
        });

        btnXemCaLam.addActionListener(e -> new FrmXemCaLamDanhChoNhanVien().setVisible(true));
        cardCaLam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FrmXemCaLamDanhChoNhanVien().setVisible(true);
            }
        });

        btnTaiKhoan.addActionListener(e -> new FrmDoiMk().setVisible(true));
        cardTaiKhoan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FrmDoiMk().setVisible(true);
            }
        });
    }
}