package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import BUS.DangNhapBus;
import MODEL.PhanQuyenLogin;
import MODEL.Session;
public class FrmLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txttenlogin;
    private JPasswordField txtmk;
    private DangNhapBus dnbus = new DangNhapBus();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmLogin frame = new FrmLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmLogin() {
        setTitle("Phòng khám đa khoa - Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 520);
        setLocationRelativeTo(null);
        setResizable(false);


        contentPane = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 191, 255), 0, getHeight(), new Color(0, 128, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);


        JLabel lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG PHÒNG KHÁM");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255, 0, 0));
        lblTitle.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 18));
        lblTitle.setBounds(30, 30, 400, 30);
        contentPane.add(lblTitle);

     
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/clinic (2).png")));
        lblLogo.setBounds(146, 79, 240, 130);
        contentPane.add(lblLogo);


        JPanel pnlForm = new JPanel();
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(new LineBorder(new Color(173, 216, 230), 2, true));
        pnlForm.setBounds(70, 220, 340, 200);
        pnlForm.setLayout(null);
        contentPane.add(pnlForm);


        JLabel lblUser = new JLabel("Tên đăng nhập:");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setBounds(40, 30, 120, 25);
        pnlForm.add(lblUser);

        txttenlogin = new JTextField();
        txttenlogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txttenlogin.setBounds(160, 30, 130, 25);
        txttenlogin.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        pnlForm.add(txttenlogin);


        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPass.setBounds(40, 80, 120, 25);
        pnlForm.add(lblPass);

        txtmk = new JPasswordField();
        txtmk.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtmk.setBounds(160, 80, 130, 25);
        txtmk.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        pnlForm.add(txtmk);


        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 153, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(new RoundBorder(10));
        btnLogin.setBounds(100, 130, 140, 35);

 
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 102, 204));
            }
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 153, 255));
            }
        });

        pnlForm.add(btnLogin);

   
        btnLogin.addActionListener(e -> {
            String ten = txttenlogin.getText().trim();
            String mk = new String(txtmk.getPassword()).trim();

            if (ten.isEmpty() || mk.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            PhanQuyenLogin login = dnbus.BSlogin(ten, mk);

            if (login != null) {

            	Session.maNhanVien = login.getMaNhanVien();
            	Session.role = login.getRole();
            	Session.chuyenKhoa = login.getChuyenKhoa();

//                JOptionPane.showMessageDialog(null,
//                    "Đăng nhập thành công!\nVai trò: " + role + "\nChuyên khoa: " + ck);

                switch (	Session.role.toUpperCase()) {

                    case "BAC_SI":

                        if ("Nhi khoa".equalsIgnoreCase(Session.chuyenKhoa)) {
                            new FrmLamViecBSNhiKhoa().setVisible(true);
                        } else if ("Tim mạch".equalsIgnoreCase(Session.chuyenKhoa)) {
                            new FrmLamViecBSTimMach().setVisible(true);
                        } 
                        
                        else {
                            new FrmLamViecBacSiTongQuat().setVisible(true);  
                        }
                        break;

                    case "LE_TAN":
                        new FrmLeTan().setVisible(true);
                        break;

                    case "QUAN_LY":
                        new FrmQuanLy().setVisible(true);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,
                            "Không có giao diện cho vai trò: " + Session.role);
                }

                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu!");
            }
        });

    }


    class RoundBorder extends LineBorder {
        private int radius;
        public RoundBorder(int radius) {
            super(Color.WHITE, 1, true);
            this.radius = radius;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(lineColor);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
