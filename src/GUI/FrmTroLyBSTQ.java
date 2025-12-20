package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FrmTroLyBSTQ extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlDesktop;


    private final Color SIDEBAR_COLOR = new Color(0, 85, 170); 
    private final Color BG_COLOR = new Color(245, 248, 250);   
    private final Color HEADER_COLOR = Color.WHITE;
    private final Color PRIMARY_COLOR = new Color(0, 123, 255); 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmTroLyBSTQ frame = new FrmTroLyBSTQ();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmTroLyBSTQ() {
        setTitle("Hệ Thống Trợ Lý Bác Sĩ Tổng Quát");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
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
        lblIcon.setBounds(0, 30, 260, 60);
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

        JButton btnHome = new JButton("Trang chủ");
        styleSidebarButton(btnHome);
        btnHome.setBounds(0, 160, 260, 50);
        pnlSidebar.add(btnHome);

        JButton btnDSKham = new JButton("Danh sách chờ");
        styleSidebarButton(btnDSKham);
        btnDSKham.setBounds(0, 210, 260, 50);
 
        btnDSKham.addActionListener(e -> openDanhSachKham());
        pnlSidebar.add(btnDSKham);


        JButton btnLogout = new JButton("Đăng xuất");
        styleMainButton(btnLogout, new Color(220, 53, 69));
        btnLogout.setBounds(30, 700, 200, 40);
        btnLogout.addActionListener(e -> {
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
            dispose();
        });
        pnlSidebar.add(btnLogout);


        JPanel pnlRight = new JPanel();
        pnlRight.setLayout(new BorderLayout(0, 0));
        contentPane.add(pnlRight, BorderLayout.CENTER);

        // Header
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(HEADER_COLOR);
        pnlHeader.setPreferredSize(new Dimension(10, 60));
        pnlHeader.setBorder(new LineBorder(new Color(230, 230, 230)));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 12));
        pnlRight.add(pnlHeader, BorderLayout.NORTH);

        JLabel lblHeaderTitle = new JLabel("HỖ TRỢ KHÁM BỆNH TỔNG QUÁT");
        lblHeaderTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeaderTitle.setForeground(SIDEBAR_COLOR);
        pnlHeader.add(lblHeaderTitle);


        pnlDesktop = new JPanel();
        pnlDesktop.setBackground(BG_COLOR);
        pnlDesktop.setLayout(null); 
        pnlRight.add(pnlDesktop, BorderLayout.CENTER);

        JButton btnXemDS = new JButton("DANH SÁCH CHỜ KHÁM");
        styleMainButton(btnXemDS, PRIMARY_COLOR);
        btnXemDS.setText("<html><center><font size='6'> </font><br>DANH SÁCH CHỜ KHÁM</center></html>");
        btnXemDS.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnXemDS.setBounds(50, 50, 280, 160);
        
        btnXemDS.addActionListener(e -> openDanhSachKham());
        pnlDesktop.add(btnXemDS);
        
     
        JButton btnHoTro = new JButton("HỖ TRỢ KHÁC");
        styleMainButton(btnHoTro, new Color(40, 167, 69)); 
        btnHoTro.setText("<html><center><font size='6'></font><br>CÁC CHỨC NĂNG KHÁC</center></html>");
        btnHoTro.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnHoTro.setBounds(360, 50, 280, 160);
        pnlDesktop.add(btnHoTro);
    }


    private void openDanhSachKham() {
   
        FrmDanhSachKhamBenhCuaBSTQ ds = new FrmDanhSachKhamBenhCuaBSTQ();
        ds.setVisible(true);
        dispose();
    }



    private void styleSidebarButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_COLOR);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBorder(new EmptyBorder(0, 30, 0, 0));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
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

    private void styleMainButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}