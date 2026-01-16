package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.*;

import BUS.DangNhapBus;

public class FrmQuenMK extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel cardPanel;
    private CardLayout cardLayout;


    private String generatedOTP = "";
    private String currentEmail = "";

    private JTextField txtEmail;
    private JTextField txtOTP;
    private JPasswordField txtNewPass;
    private JPasswordField txtConfirmPass;

    private final Color BG_COLOR = new Color(239, 246, 255);
    private final Color PRIMARY_COLOR = new Color(59, 130, 246); 
    private final Color TEXT_COLOR = new Color(30, 58, 138);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmQuenMK frame = new FrmQuenMK();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // ---  GỬI EMAIL  ---
    public static void sendEmail(String recipientEmail, String codeToSend) throws MessagingException {
        final String username = "dvmtam1102003@gmail.com"; 
        final String password = "rnpm neej fbfl yhbu"; 

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Mã xác nhận lấy lại mật khẩu - PHÒNG KHÁM");
        message.setText("Chào bạn,\n\nMã xác nhận của bạn là: " + codeToSend );

        Transport.send(message);
    }

    private String generateConfirmationCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) { 
            code.append((int) (Math.random() * 10));
        }
        return code.toString();
    }

    // --- GIAO DIỆN ---
    public FrmQuenMK() {
        setTitle("Quên Mật Khẩu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 500, 550, 20, 20));

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);


        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblClose = new JLabel("X");
        lblClose.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblClose.setForeground(Color.GRAY);
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { dispose(); } 
        });
        pnlHeader.add(lblClose, BorderLayout.EAST);
        contentPane.add(pnlHeader, BorderLayout.NORTH);


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.WHITE);

        cardPanel.add(createStep1_Email(), "STEP1");
        cardPanel.add(createStep2_OTP(), "STEP2");
        cardPanel.add(createStep3_NewPass(), "STEP3");

        contentPane.add(cardPanel, BorderLayout.CENTER);
    }

    // --- STEP 1: NHẬP EMAIL ---
    private JPanel createStep1_Email() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Quên Mật Khẩu?");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(TEXT_COLOR);

        JLabel lblSub = new JLabel("Nhập email đã đăng ký để nhận mã xác thực.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);

        txtEmail = new JTextField();
        styleTextField(txtEmail);

        JButton btnSend = new RoundedButton("Gửi Mã Xác Nhận", PRIMARY_COLOR);
        btnSend.setPreferredSize(new Dimension(300, 45));
        
        // Sự kiện Gửi Email
        btnSend.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            if(email.isEmpty() || !email.contains("@") || !email.contains(".com")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập email hợp lệ!");
                return;
            }
            
            // Loading giả lập
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            btnSend.setText("Đang gửi...");
            btnSend.setEnabled(false);

      
            new Thread(() -> {
                try {
                    generatedOTP = generateConfirmationCode();
                    currentEmail = email;
                    sendEmail(email, generatedOTP);
                    
                    SwingUtilities.invokeLater(() -> {
                        setCursor(Cursor.getDefaultCursor());
                        btnSend.setText("Gửi Mã Xác Nhận");
                        btnSend.setEnabled(true);
                        JOptionPane.showMessageDialog(this, "Đã gửi mã đến: " + email);
                        cardLayout.show(cardPanel, "STEP2"); 
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        setCursor(Cursor.getDefaultCursor());
                        btnSend.setText("Gửi Mã Xác Nhận");
                        btnSend.setEnabled(true);
                        JOptionPane.showMessageDialog(this, "Lỗi gửi mail: " + ex.getMessage());
                    });
                }
            }).start();
        });

        // Layout components
        addItem(pnl, lblTitle, 0, 0, new Insets(0, 0, 10, 0));
        addItem(pnl, lblSub, 0, 1, new Insets(0, 0, 30, 0));
        addItem(pnl, createLabel("Địa chỉ Email"), 0, 2, new Insets(0, 0, 5, 0));
        addItem(pnl, txtEmail, 0, 3, new Insets(0, 0, 20, 0));
        addItem(pnl, btnSend, 0, 4, new Insets(10, 0, 0, 0));
        
        JButton btnBackLogin = new JButton("Quay lại Đăng nhập");
        styleLinkButton(btnBackLogin);
        btnBackLogin.addActionListener(e -> {
             new FrmLogin().setVisible(true);
             dispose();
        });
        addItem(pnl, btnBackLogin, 0, 5, new Insets(10, 0, 0, 0));

        return pnl;
    }

    // --- STEP 2: NHẬP OTP ---
    private JPanel createStep2_OTP() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Xác Thực OTP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(TEXT_COLOR);

        JLabel lblSub = new JLabel("Nhập mã 6 số chúng tôi vừa gửi cho bạn.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);

        txtOTP = new JTextField();
        styleTextField(txtOTP);
        txtOTP.setHorizontalAlignment(JTextField.CENTER);
        txtOTP.setFont(new Font("Segoe UI", Font.BOLD, 24));
        txtOTP.setForeground(PRIMARY_COLOR);

        JButton btnVerify = new RoundedButton("Xác Nhận", new Color(16, 185, 129)); 
        btnVerify.setPreferredSize(new Dimension(300, 45));

        btnVerify.addActionListener(e -> {
            String inputOTP = txtOTP.getText().trim();
            if(inputOTP.equals(generatedOTP)) {
                cardLayout.show(cardPanel, "STEP3"); 
            } else {
                JOptionPane.showMessageDialog(this, "Mã xác nhận không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnBack = new JButton("Quay lại nhập Email");
        styleLinkButton(btnBack);
        btnBack.addActionListener(e -> cardLayout.show(cardPanel, "STEP1"));

        addItem(pnl, lblTitle, 0, 0, new Insets(0, 0, 10, 0));
        addItem(pnl, lblSub, 0, 1, new Insets(0, 0, 30, 0));
        addItem(pnl, createLabel("Mã OTP"), 0, 2, new Insets(0, 0, 5, 0));
        addItem(pnl, txtOTP, 0, 3, new Insets(0, 0, 20, 0));
        addItem(pnl, btnVerify, 0, 4, new Insets(10, 0, 0, 0));
        addItem(pnl, btnBack, 0, 5, new Insets(10, 0, 0, 0));

        return pnl;
    }

    // --- STEP 3: ĐỔI MẬT KHẨU ---
    private JPanel createStep3_NewPass() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Đặt Lại Mật Khẩu");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(TEXT_COLOR);

        txtNewPass = new JPasswordField();
        styleTextField(txtNewPass);
        
        txtConfirmPass = new JPasswordField();
        styleTextField(txtConfirmPass);

        JButton btnChange = new RoundedButton("Đổi Mật Khẩu", PRIMARY_COLOR);
        btnChange.setPreferredSize(new Dimension(300, 45));

        btnChange.addActionListener(e -> {
            String pass = new String(txtNewPass.getPassword());
            String confirm = new String(txtConfirmPass.getPassword());

     
            if(pass.length() < 6) {
                JOptionPane.showMessageDialog(this, "Mật khẩu phải từ 6 ký tự trở lên!");
                return;
            }
            
  
            if(!pass.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!");
                return;
            }

  
            String hashedPassword = MODEL.PasswordEncoder.hashPassword(pass); 


            DangNhapBus dnbus = new DangNhapBus(); 
            boolean isSuccess = dnbus.doiMatKhauTheoEmail(currentEmail, hashedPassword);

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công! Vui lòng đăng nhập lại.");
                new FrmLogin().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi hệ thống hoặc Email không tồn tại!", "Thất bại", JOptionPane.ERROR_MESSAGE);
            }
        });

        addItem(pnl, lblTitle, 0, 0, new Insets(0, 0, 30, 0));
        
        addItem(pnl, createLabel("Mật khẩu mới"), 0, 1, new Insets(0, 0, 5, 0));
        addItem(pnl, txtNewPass, 0, 2, new Insets(0, 0, 15, 0));
        
        addItem(pnl, createLabel("Nhập lại mật khẩu"), 0, 3, new Insets(0, 0, 5, 0));
        addItem(pnl, txtConfirmPass, 0, 4, new Insets(0, 0, 30, 0));
        
        addItem(pnl, btnChange, 0, 5, new Insets(0, 0, 0, 0));

        return pnl;
    }


    private void addItem(JPanel p, Component c, int x, int y, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.insets = insets;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(c, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(TEXT_COLOR);
        return lbl;
    }

    private void styleTextField(JTextField txt) {
        txt.setPreferredSize(new Dimension(300, 40));
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)), 
            new EmptyBorder(5, 10, 5, 10)
        ));
    }
    
    private void styleLinkButton(JButton btn) {
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setForeground(Color.GRAY);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(PRIMARY_COLOR); }
            public void mouseExited(MouseEvent e) { btn.setForeground(Color.GRAY); }
        });
    }

    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color bgColor;
        public RoundedButton(String text, Color color) {
            super(text);
            this.bgColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 15));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? bgColor.darker() : bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}