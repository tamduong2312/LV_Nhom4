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


    private final Color SIDEBAR_COLOR = new Color(0, 85, 170); 
    private final Color BG_COLOR = new Color(240, 245, 250);  
    private final Color TEXT_COLOR = new Color(60, 60, 60);
    private final Color HEADER_COLOR = Color.WHITE;
    private final Color RED_COLOR = new Color(220, 53, 69);  

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmTroLy frame = new FrmTroLy();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmTroLy() {
    	String title = "Trợ Lý Bác Sĩ - Xin Chào: " + Session.TenNhanVien;
        setTitle(title); 
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
        
        JLabel lblIcon = new JLabel("📋");
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        lblIcon.setForeground(Color.WHITE);
        lblIcon.setBounds(0, 11, 260, 79);
        pnlSidebar.add(lblIcon);
        
        JLabel lblTitle = new JLabel("PHÒNG KHÁM");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(0, 90, 260, 30);
        pnlSidebar.add(lblTitle);

        JLabel lblSub = new JLabel("Trợ Lý Bác Sĩ");
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(220, 220, 220));
        lblSub.setBounds(0, 120, 260, 20);
        pnlSidebar.add(lblSub);

        int y = 150;


        y += 60;
        JButton btnDSKham = new SidebarButton("Danh sách chờ khám" , SIDEBAR_COLOR);
        btnDSKham.setBounds(0, y, 260, 55);
        pnlSidebar.add(btnDSKham);

        y += 60;
        JButton btnTaiKhoan = new SidebarButton("Tài khoản cá nhân", SIDEBAR_COLOR);
        btnTaiKhoan.setBounds(0, y, 260, 55);
        pnlSidebar.add(btnTaiKhoan);
        
        
        y += 60;
        JButton btnXemCaLam = new SidebarButton("Xem ca làm", SIDEBAR_COLOR);
        btnXemCaLam.setBounds(0, y, 260, 55);
        pnlSidebar.add(btnXemCaLam);

  
        JButton btnLogout = new RoundedButton("Đăng xuất", RED_COLOR);
        btnLogout.setBounds(30, 700, 200, 40);
        pnlSidebar.add(btnLogout);


        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setBackground(BG_COLOR);
        contentPane.add(pnlRight, BorderLayout.CENTER);

        // Header
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 15));
        pnlHeader.setBackground(HEADER_COLOR);
        pnlHeader.setPreferredSize(new Dimension(0, 70));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        JLabel lblPageTitle = new JLabel("TỔNG QUAN CÔNG VIỆC");
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPageTitle.setForeground(SIDEBAR_COLOR);
        pnlHeader.add(lblPageTitle);
        pnlRight.add(pnlHeader, BorderLayout.NORTH);

  
        pnlMainContent = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
        pnlMainContent.setBackground(BG_COLOR);
        pnlRight.add(pnlMainContent, BorderLayout.CENTER);

     
        JPanel cardDSKham = createDashboardCard("Tiếp Nhận & Khám", "Xem danh sách bệnh nhân chờ", new Color(0, 168, 255),"📝");
        pnlMainContent.add(cardDSKham);
        
        
        JPanel cardCaLam = createDashboardCard("Xem Ca Lam", "", new Color(0, 168, 255),"📝");
        pnlMainContent.add(cardCaLam);

   
        JPanel cardTaiKhoan = createDashboardCard("Cài Đặt Tài Khoản", "Đổi mật khẩu & Thông tin", new Color(156, 136, 255), "🔒");
        pnlMainContent.add(cardTaiKhoan);

    

        ActionListener actionOpenDSKham = e -> {
        
        	switch (Session.chuyenKhoa) {
			case "Nội tổng quát": {
				FrmDanhSachChoKhamTroLyTQ q = new FrmDanhSachChoKhamTroLyTQ();
				q.show();
				dispose();
				break;
			}
			
			
			default:
				FrmDanhSachChoKhamTroLyCK q = new FrmDanhSachChoKhamTroLyCK();
				q.show();
				dispose();
				break;
			}
//            FrmDanhSachKhamBenhCuaBSTQ q = new FrmDanhSachKhamBenhCuaBSTQ();
//            q.setVisible(true);
//            dispose();
        };
        btnDSKham.addActionListener(actionOpenDSKham);
        addCardClickEvent(cardDSKham, actionOpenDSKham);

        ActionListener actionDoiMK = e -> {
            FrmDoiMk f = new FrmDoiMk();
            f.setVisible(true);
        };
        btnTaiKhoan.addActionListener(actionDoiMK);
        addCardClickEvent(cardTaiKhoan, actionDoiMK);
        
        
        ActionListener actionXemCaLam = e -> {
            FrmXemCaLamDanhChoNhanVien mk = new FrmXemCaLamDanhChoNhanVien();
            mk.show();
            dispose();
        };
        btnXemCaLam.addActionListener(actionXemCaLam);
        addCardClickEvent(cardCaLam, actionXemCaLam);
        

        btnLogout.addActionListener(e -> {
            FrmLogin q = new FrmLogin();
            q.setVisible(true);
            dispose();
        });
    }


    private JPanel createDashboardCard(String title, String subtitle, Color accentColor, String icon) {
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
        card.setPreferredSize(new Dimension(300, 180));
        card.setOpaque(false);
        card.setLayout(null);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel strip = new JPanel();
        strip.setBackground(accentColor);
        strip.setBounds(0, 0, 8, 180);
        card.add(strip);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(TEXT_COLOR);
        lblTitle.setBounds(30, 90, 250, 30);
        card.add(lblTitle);

        JLabel lblSub = new JLabel(subtitle);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);
        lblSub.setBounds(30, 120, 250, 20);
        card.add(lblSub);

        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createLineBorder(accentColor, 1));
            }
            public void mouseExited(MouseEvent e) {
                card.setBorder(null);
            }
        });
        return card;
    }

    private void addCardClickEvent(JPanel card, ActionListener action) {
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.actionPerformed(new ActionEvent(card, ActionEvent.ACTION_PERFORMED, null));
            }
        });
    }

  
    class SidebarButton extends JButton {
        private Color bgColor;
        private Color hoverColor;

        public SidebarButton(String text, Color bg) {
            super(text);
            this.bgColor = bg;
            this.hoverColor = new Color(0, 100, 200);
            
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.PLAIN, 15));
            setHorizontalAlignment(SwingConstants.LEFT);
            setBorder(new EmptyBorder(0, 30, 0, 0));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { setFont(new Font("Segoe UI", Font.BOLD, 15)); }
                public void mouseExited(MouseEvent e) { setFont(new Font("Segoe UI", Font.PLAIN, 15)); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getModel().isRollover() ? hoverColor : bgColor);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }


    class RoundedButton extends JButton {
        private Color bgColor;
        private int radius = 15;

        public RoundedButton(String text, Color bg) {
            super(text);
            this.bgColor = bg;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (getModel().isPressed()) g2.setColor(bgColor.darker());
            else if (getModel().isRollover()) g2.setColor(bgColor.brighter());
            else g2.setColor(bgColor);

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}