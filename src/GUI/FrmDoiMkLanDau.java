package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import BUS.DangNhapBus;
import MODEL.PasswordEncoder;
import MODEL.Session;

public class FrmDoiMkLanDau extends JDialog { 

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JPasswordField txtMkMoi;
    private JPasswordField txtXacNhan;
    
    private DangNhapBus dnbus = new DangNhapBus();
    private boolean isSuccess = false;

    public FrmDoiMkLanDau(JFrame parent) {
        super(parent, "Yêu Cầu Đổi Mật Khẩu", true); 
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
        setBounds(100, 100, 500, 350); 
        setLocationRelativeTo(parent); 
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(220, 53, 69)); 
        pnlHeader.setBounds(0, 0, 500, 60);
        contentPane.add(pnlHeader);
        pnlHeader.setLayout(null);

        JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU LẦN ĐẦU");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(0, 10, 486, 40);
        pnlHeader.add(lblTitle);


        JLabel lblNote = new JLabel("<html><i>(Vì lý do bảo mật, bạn vui lòng đổi mật khẩu mặc định<br>trước khi sử dụng hệ thống)</i></html>");
        lblNote.setHorizontalAlignment(SwingConstants.CENTER);
        lblNote.setForeground(Color.GRAY);
        lblNote.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblNote.setBounds(50, 70, 400, 40);
        contentPane.add(lblNote);


        JLabel lblMkMoi = new JLabel("Mật khẩu mới:");
        lblMkMoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMkMoi.setBounds(50, 130, 150, 25);
        contentPane.add(lblMkMoi);

        txtMkMoi = new JPasswordField();
        txtMkMoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMkMoi.setBounds(200, 130, 230, 30);
        contentPane.add(txtMkMoi);
        

        JLabel lblXacNhan = new JLabel("Nhập lại mật khẩu:");
        lblXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblXacNhan.setBounds(50, 180, 150, 25);
        contentPane.add(lblXacNhan);

        txtXacNhan = new JPasswordField();
        txtXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtXacNhan.setBounds(200, 180, 230, 30);
        contentPane.add(txtXacNhan);


        JButton btnDongY = new JButton("Đổi Mật Khẩu");
        btnDongY.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDongY.setBackground(new Color(40, 167, 69)); 
        btnDongY.setForeground(Color.WHITE);
        btnDongY.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDongY.setFocusPainted(false);
        btnDongY.setContentAreaFilled(false);
        btnDongY.setOpaque(true);
        btnDongY.setBorderPainted(false);

        btnDongY.setBounds(100, 240, 140, 40);
        contentPane.add(btnDongY);

        JButton btnHuy = new JButton("Thoát");
        btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHuy.setBackground(new Color(108, 117, 125)); 
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHuy.setFocusPainted(false);
        btnHuy.setContentAreaFilled(false);
        btnHuy.setOpaque(true);
        btnHuy.setBorderPainted(false);

        
        btnHuy.setBounds(260, 240, 130, 40);
        contentPane.add(btnHuy);

 
        btnHuy.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn chưa đổi mật khẩu. Hệ thống sẽ thoát!", 
                "Cảnh báo", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!isSuccess) {
                    JOptionPane.showMessageDialog(null, "Bắt buộc phải đổi mật khẩu!");
                }
            }
        });

        btnDongY.addActionListener(e -> xuLyDoiMatKhau());
    }

    private void xuLyDoiMatKhau() {
        String mkMoi = new String(txtMkMoi.getPassword());
        String mkXacNhan = new String(txtXacNhan.getPassword());

        if (mkMoi.isEmpty() || mkXacNhan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!mkMoi.equals(mkXacNhan)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mkMoi.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải từ 6 ký tự trở lên!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        

        if (mkMoi.equals("123456")) {
             JOptionPane.showMessageDialog(this, "Vui lòng chọn mật khẩu khác mật khẩu mặc định!", "Lỗi", JOptionPane.WARNING_MESSAGE);
             return;
        }

        try {
        
            String hashedPassword = PasswordEncoder.hashPassword(mkMoi);
            
        
            int ketQua = dnbus.doiMatKhauLanDau(Session.ma_nguoi_dung, hashedPassword);
            
            if (ketQua > 0) { 
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công! Bạn có thể bắt đầu làm việc.");
                isSuccess = true;
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi hệ thống, vui lòng thử lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }
    

    public boolean isDoiThanhCong() {
        return isSuccess;
    }
}