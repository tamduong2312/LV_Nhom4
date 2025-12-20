package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FrmLeTan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlMainContent;


    private final Color SIDEBAR_COLOR = new Color(0, 85, 170); 
    private final Color HEADER_COLOR = Color.WHITE;
    private final Color BG_COLOR = new Color(240, 245, 250); 
    private final Color TEXT_COLOR = new Color(60, 60, 60);
    private final Color ACTIVE_COLOR = new Color(255, 255, 255, 50); 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
       
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
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


        int yPos = 140;
        JButton btnMenuTrangChu = createSidebarButton("Trang chủ", yPos);
        pnlSidebar.add(btnMenuTrangChu);

        yPos += 60;
        JButton btnMenuDangKy = createSidebarButton("Đăng ký khám", yPos);
        pnlSidebar.add(btnMenuDangKy);

        yPos += 60;
        JButton btnMenuBenhNhan = createSidebarButton("Hồ sơ bệnh nhân", yPos);
        pnlSidebar.add(btnMenuBenhNhan);
        
        yPos += 60;
        JButton btnMenuTaiKhoan = createSidebarButton("Đổi mật khẩu", yPos);
        pnlSidebar.add(btnMenuTaiKhoan);
        
        
        yPos += 60;
        JButton btnXemLichCaNhan = createSidebarButton("Xem ca làm", yPos);
        pnlSidebar.add(btnXemLichCaNhan);


        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setBounds(30, 700, 200, 40);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setForeground(SIDEBAR_COLOR);
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSidebar.add(btnLogout);


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

        JLabel lblPageTitle = new JLabel("TỔNG QUAN / TIẾP ĐÓN");
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPageTitle.setForeground(SIDEBAR_COLOR);
        pnlHeader.add(lblPageTitle);


        pnlMainContent = new JPanel();
        pnlMainContent.setBackground(BG_COLOR);
        pnlMainContent.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        pnlRight.add(pnlMainContent, BorderLayout.CENTER);


        JPanel cardDangKy = createDashboardCard(
            "Đăng Ký Khám", 
            "Tạo phiếu khám mới", 
            new Color(0, 168, 255), 
            "📝"
        );
        pnlMainContent.add(cardDangKy);


        JPanel cardBenhNhan = createDashboardCard(
            "Quản Lý Bệnh Nhân", 
            "Tra cứu hồ sơ & Lịch sử", 
            new Color(76, 209, 55), 
            "👥"
        );
        pnlMainContent.add(cardBenhNhan);
        

        JPanel cardTaiKhoan = createDashboardCard(
            "Tài Khoản Cá Nhân", 
            "Đổi mật khẩu & Thông tin", 
            new Color(156, 136, 255), 
            "🔒"
        );
        pnlMainContent.add(cardTaiKhoan);
        
        
        JPanel cardlichsu = createDashboardCard(
                "Lịch sử ", 
                "đăng ký khám bệnh", 
                new Color(156, 136, 255), 
                ""
            );
            pnlMainContent.add(cardlichsu);


        ActionListener actionDangKy = e -> {
      
           FrmDangKyKhamBenh kb = new FrmDangKyKhamBenh();
        kb.show();
           //dispose();
        }; 
        btnMenuDangKy.addActionListener(actionDangKy);
        addCardClickEvent(cardDangKy, actionDangKy);
        
        
        ActionListener actionLichSu = e -> {
            
         FrmLichSuDangKyKhamBenh q = new FrmLichSuDangKyKhamBenh();
         q.setVisible(true);
            //dispose();
         }; 
         btnMenuDangKy.addActionListener(actionLichSu);
         addCardClickEvent(cardlichsu, actionLichSu);


        ActionListener actionBenhNhan = e -> {
        FrmQlbenhnhancuaLetan ql  = new FrmQlbenhnhancuaLetan();
        ql.show();
        dispose();
        };
        btnMenuBenhNhan.addActionListener(actionBenhNhan);
        addCardClickEvent(cardBenhNhan, actionBenhNhan);


        ActionListener actionDoiMK = e -> {
       FrmDoiMk mk = new FrmDoiMk();
       mk.show();
       dispose();
             System.out.println("Mở form Đổi Mật Khẩu");
        };
        btnMenuTaiKhoan.addActionListener(actionDoiMK);
        addCardClickEvent(cardTaiKhoan, actionDoiMK);
        
        
        ActionListener actionXemCalam = e -> {
       FrmXemCaLamDanhChoNhanVien mk = new FrmXemCaLamDanhChoNhanVien();
       mk.show();
 
             System.out.println("Mở form xem ca làm");
        };
        btnXemLichCaNhan.addActionListener(actionXemCalam);
        addCardClickEvent(cardTaiKhoan, actionXemCalam);

 
        btnLogout.addActionListener(e -> {
     FrmLogin ql  = new FrmLogin();
     ql.setVisible(true); 

  
     FrmLeTan.this.dispose();
    		 
             System.out.println("Đăng xuất");
        });
    }


    private JButton createSidebarButton(String text, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(0, y, 260, 55);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_COLOR);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 30, 0, 0)); // Padding text
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));


        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 100, 200)); 
                btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
                btn.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE)); 
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_COLOR);
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                btn.setBorder(new EmptyBorder(0, 30, 0, 0));
            }
        });
        return btn;
    }


    private JPanel createDashboardCard(String title, String subtitle, Color accentColor, String emojiIcon) {
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


        JLabel lblIcon = new JLabel(emojiIcon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblIcon.setBounds(30, 20, 60, 60);
        card.add(lblIcon);


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
}