package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import BUS.DangNhapBus;
import MODEL.Session;

public class FrmDoiMk extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField txtMkCu;
    private JPasswordField txtMkMoi;
    private JPasswordField txtXacNhan;
    
    private DangNhapBus dnbus = new DangNhapBus();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
          
                    try {
                        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {}
                    
                    FrmDoiMk frame = new FrmDoiMk();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FrmDoiMk() {
        setTitle("Đổi Mật Khẩu Hệ Thống");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(100, 100, 500, 420); 
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(0, 128, 128)); 
        pnlHeader.setBounds(0, 0, 500, 60);
        contentPane.add(pnlHeader);
        pnlHeader.setLayout(null);

        JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(0, 10, 486, 40);
        pnlHeader.add(lblTitle);


        JLabel lblMkCu = new JLabel("Mật khẩu hiện tại:");
        lblMkCu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMkCu.setBounds(50, 90, 150, 25);
        contentPane.add(lblMkCu);

        txtMkCu = new JPasswordField();
        txtMkCu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMkCu.setBounds(200, 90, 230, 30);
        contentPane.add(txtMkCu);

 
        JLabel lblMkMoi = new JLabel("Mật khẩu mới:");
        lblMkMoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMkMoi.setBounds(50, 145, 150, 25);
        contentPane.add(lblMkMoi);

        txtMkMoi = new JPasswordField();
        txtMkMoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMkMoi.setBounds(200, 145, 230, 30);
        contentPane.add(txtMkMoi);
        
    
        JLabel lblXacNhan = new JLabel("Nhập lại mật khẩu:");
        lblXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblXacNhan.setBounds(50, 200, 150, 25);
        contentPane.add(lblXacNhan);

        txtXacNhan = new JPasswordField();
        txtXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtXacNhan.setBounds(200, 200, 230, 30);
        contentPane.add(txtXacNhan);

        JButton btnDongY = new JButton("Xác Nhận");
        btnDongY.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDongY.setBackground(new Color(40, 167, 69)); 
        //btnDongY.setForeground(Color.WHITE);
        btnDongY.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDongY.setFocusPainted(false);
        btnDongY.setContentAreaFilled(false);
        btnDongY.setOpaque(true);
        btnDongY.setBorderPainted(false);

        btnDongY.setBounds(100, 280, 130, 40);
        contentPane.add(btnDongY);

        JButton btnHuy = new JButton("Quay về");
        btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHuy.setBackground(new Color(220, 53, 69)); 
       // btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHuy.setFocusPainted(false);
        btnHuy.setBounds(260, 280, 130, 40);
        btnHuy.setContentAreaFilled(false);
        btnHuy.setOpaque(true);
        btnHuy.setBorderPainted(false);

        contentPane.add(btnHuy);


        

        btnHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
       
                dispose(); 
            }
        });


        btnDongY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLyDoiMatKhau();
            }
        });
    }


    private void xuLyDoiMatKhau() {
        String mkCu = new String(txtMkCu.getPassword());
        String mkMoi = new String(txtMkMoi.getPassword());
        String mkXacNhan = new String(txtXacNhan.getPassword());


        if (mkCu.isEmpty() || mkMoi.isEmpty() || mkXacNhan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if (!mkMoi.equals(mkXacNhan)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới và nhập lại không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mkMoi.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if (dnbus.SSMK(Session.ma_nguoi_dung, mkCu) == false) {
            JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {

            String hashedPassword = MODEL.PasswordEncoder.hashPassword(mkMoi);
            
            int ketQua = dnbus.SuaMK(Session.ma_nguoi_dung, hashedPassword);
            
            if (ketQua == 1) { 
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
          
            } else {
 
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu không thành công!");
   
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + ex.getMessage());
        }
    }
}