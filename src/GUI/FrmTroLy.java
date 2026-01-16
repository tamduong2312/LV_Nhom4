package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import MODEL.Session;

public class FrmTroLy extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlMainContent;

	private JButton btnMenuTrangChu;
	private JButton btnMenuDSKham;
	private JButton btnMenuTaiKhoan;
	private JButton btnMenuXemCaLam;
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
				FrmTroLy frame = new FrmTroLy();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FrmTroLy() {

		String displayUser = "Nhân viên";
		if (!java.beans.Beans.isDesignTime()) {
			if (Session.TenNhanVien != null) {
				displayUser = Session.TenNhanVien;
			}
		}

		setTitle("Trợ Lý Bác Sĩ - Xin Chào: " + displayUser);
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

		JLabel lblBrandSub = new JLabel("Trợ Lý Bác Sĩ");
		lblBrandSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblBrandSub.setForeground(new Color(220, 220, 220));
		lblBrandSub.setBounds(100, 65, 150, 20);
		pnlSidebar.add(lblBrandSub);

		btnMenuTrangChu = new JButton("Trang chủ");
		btnMenuTrangChu.setBounds(0, 140, 260, 55);
		applySidebarStyle(btnMenuTrangChu);
		pnlSidebar.add(btnMenuTrangChu);

		btnMenuDSKham = new JButton("Danh sách chờ khám");
		btnMenuDSKham.setBounds(0, 200, 260, 55);
		applySidebarStyle(btnMenuDSKham);
		pnlSidebar.add(btnMenuDSKham);

		btnMenuTaiKhoan = new JButton("Tài khoản cá nhân");
		btnMenuTaiKhoan.setBounds(0, 260, 260, 55);
		applySidebarStyle(btnMenuTaiKhoan);
		pnlSidebar.add(btnMenuTaiKhoan);

		btnMenuXemCaLam = new JButton("Xem ca làm");
		btnMenuXemCaLam.setBounds(0, 320, 260, 55);
		applySidebarStyle(btnMenuXemCaLam);
		pnlSidebar.add(btnMenuXemCaLam);

		btnLogout = new JButton("Đăng xuất");
		btnLogout.setBounds(30, 575, 200, 40);
		btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogout.setBackground(RED_COLOR);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFocusPainted(false);
		btnLogout.setBorder(new LineBorder(RED_COLOR, 1, true));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setOpaque(true);
		btnLogout.setBorderPainted(false);

		pnlSidebar.add(btnLogout);

		// --- RIGHT PANEL ---
		JPanel pnlRight = new JPanel();
		pnlRight.setBackground(BG_COLOR);
		pnlRight.setLayout(new BorderLayout());
		contentPane.add(pnlRight, BorderLayout.CENTER);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(HEADER_COLOR);
		pnlHeader.setPreferredSize(new Dimension(0, 70));
		pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
		pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		pnlRight.add(pnlHeader, BorderLayout.NORTH);

		JLabel lblPageTitle = new JLabel("TỔNG QUAN CÔNG VIỆC");
		lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPageTitle.setForeground(SIDEBAR_COLOR);
		pnlHeader.add(lblPageTitle);

		pnlMainContent = new JPanel();
		pnlMainContent.setBackground(BG_COLOR);
		pnlMainContent.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		pnlRight.add(pnlMainContent, BorderLayout.CENTER);

		// Card 1
		cardDSKham = new JPanel();
		setupCard(cardDSKham, "Tiếp Nhận & Khám", "Xem danh sách bệnh nhân chờ", new Color(0, 168, 255), "📝");
		pnlMainContent.add(cardDSKham);

		// Card 2
		cardCaLam = new JPanel();
		setupCard(cardCaLam, "Xem Ca Làm", "Lịch trực trong tuần", new Color(46, 204, 113), "📅");
		pnlMainContent.add(cardCaLam);

		// Card 3
		cardTaiKhoan = new JPanel();
		setupCard(cardTaiKhoan, "Cài Đặt Tài Khoản", "Đổi mật khẩu & Thông tin", new Color(156, 136, 255), "🔒");
		pnlMainContent.add(cardTaiKhoan);

		initActions();
	}

	private void applySidebarStyle(JButton btn) {
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

	private void setupCard(JPanel card, String title, String sub, Color accent, String emoji) {
		card.setPreferredSize(new Dimension(300, 180));
		card.setBackground(Color.WHITE);
		card.setLayout(null);
		card.setCursor(new Cursor(Cursor.HAND_CURSOR));
		card.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));

		JPanel strip = new JPanel();
		strip.setBackground(accent);
		strip.setBounds(0, 0, 8, 180);
		card.add(strip);

		JLabel lblIcon = new JLabel(emoji);
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

		card.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				card.setBorder(new LineBorder(accent, 2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				card.setBorder(new LineBorder(new Color(230, 230, 230), 1));
			}
		});
	}

	private void initActions() {

		if (java.beans.Beans.isDesignTime()) {
			return;
		}

		ActionListener openDSKham = e -> {
			if ("Nội tổng quát".equals(Session.chuyenKhoa)) {
				new FrmDanhSachChoKhamTroLyTQ().setVisible(true);
			} else {
				new FrmDanhSachChoKhamTroLyCK().setVisible(true);
			}
			dispose();
		};
		btnMenuDSKham.addActionListener(openDSKham);
		cardDSKham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openDSKham.actionPerformed(null);
			}
		});

		ActionListener openDoiMK = e -> {
			new FrmDoiMk().setVisible(true);
			dispose();
		};
		btnMenuTaiKhoan.addActionListener(openDoiMK);
		cardTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openDoiMK.actionPerformed(null);
			}
		});

		ActionListener openCaLam = e -> {
			new FrmXemCaLamDanhChoNhanVien().setVisible(true);
			dispose();
		};
		btnMenuXemCaLam.addActionListener(openCaLam);
		cardCaLam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCaLam.actionPerformed(null);
			}
		});

		btnLogout.addActionListener(e -> {
			new FrmLogin().setVisible(true);
			dispose();
		});
	}
}