package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import BUS.DangNhapBus;
import MODEL.PhanQuyenLogin;
import MODEL.Session;

public class FrmLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    

    private JComboBox<String> txtEmail; 
    private JPasswordField txtPassword;
    
    private DangNhapBus dnbus = new DangNhapBus();

    private final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private final Color GRADIENT_START = new Color(0, 100, 230);
    private final Color GRADIENT_END = new Color(0, 200, 255);
    private final Color TEXT_COLOR = new Color(100, 100, 100);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmLogin frame = new FrmLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmLogin() {
        setTitle("Đăng Nhập Hệ Thống");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 500); 
        setLocationRelativeTo(null);
        setUndecorated(true); 
        setShape(new RoundRectangle2D.Double(0, 0, 850, 500, 20, 20)); 

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(1, 2)); 
        setContentPane(contentPane);

        JPanel pnlLeft = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, GRADIENT_START, 0, getHeight(), GRADIENT_END);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        pnlLeft.setLayout(new GridBagLayout());
        contentPane.add(pnlLeft);

        JPanel pnlBrand = new JPanel();
        pnlBrand.setOpaque(false);
        pnlBrand.setLayout(new BoxLayout(pnlBrand, BoxLayout.Y_AXIS));
        
        JLabel lblLogo = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/image/clinic (2).png"));
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblLogo.setText("🏥");
            lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 90));
            lblLogo.setForeground(Color.WHITE);
        }
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitleApp = new JLabel("PHÒNG KHÁM");
        lblTitleApp.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitleApp.setForeground(Color.WHITE);
        lblTitleApp.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSlogan = new JLabel("Tận tâm - Uy tín - Chuyên nghiệp");
        lblSlogan.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblSlogan.setForeground(new Color(240, 240, 240));
        lblSlogan.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlBrand.add(lblLogo);
        pnlBrand.add(Box.createVerticalStrut(20));
        pnlBrand.add(lblTitleApp);
        pnlBrand.add(Box.createVerticalStrut(10));
        pnlBrand.add(lblSlogan);
        pnlLeft.add(pnlBrand);

    
        JPanel pnlRight = new JPanel();
        pnlRight.setBackground(Color.WHITE);
        pnlRight.setLayout(new BorderLayout());
        contentPane.add(pnlRight);


        JPanel pnlClose = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlClose.setOpaque(false);
        JLabel lblClose = new JLabel("X");
        lblClose.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblClose.setForeground(new Color(150, 150, 150));
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblClose.setBorder(new EmptyBorder(10, 0, 0, 15));
        lblClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { System.exit(0); }
            public void mouseEntered(MouseEvent e) { lblClose.setForeground(Color.RED); }
            public void mouseExited(MouseEvent e) { lblClose.setForeground(new Color(150, 150, 150)); }
        });
        pnlClose.add(lblClose);
        pnlRight.add(pnlClose, BorderLayout.NORTH);


        JPanel pnlForm = new JPanel();
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setLayout(new GridBagLayout());
        pnlRight.add(pnlForm, BorderLayout.CENTER);

   
        JLabel lblLoginTitle = new JLabel("ĐĂNG NHẬP");
        lblLoginTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblLoginTitle.setForeground(PRIMARY_COLOR);
        lblLoginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        addItem(pnlForm, lblLoginTitle, 0, 0, new Insets(0, 0, 30, 0));

   
        addItem(pnlForm, createLabel("Email"), 0, 1, new Insets(5, 0, 5, 0));
        
        txtEmail = new JComboBox<>();
        txtEmail.setEditable(true); 
        styleComboBox(txtEmail);    
        

        setupAutoCompleteDB(txtEmail); 
        
        addItem(pnlForm, txtEmail, 0, 2, new Insets(0, 0, 0, 0));


        addItem(pnlForm, createLabel("Mật khẩu"), 0, 3, new Insets(15, 0, 5, 0));
        txtPassword = new JPasswordField(20);
        styleTextField(txtPassword);
        addItem(pnlForm, txtPassword, 0, 4, new Insets(0, 0, 0, 0));


        JButton btnLogin = new RoundedButton("ĐĂNG NHẬP", PRIMARY_COLOR);
        btnLogin.setPreferredSize(new Dimension(300, 45));
        addItem(pnlForm, btnLogin, 0, 5, new Insets(30, 0, 10, 0));


        JButton btnForgot = new JButton("Quên mật khẩu?");
        btnForgot.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnForgot.setForeground(TEXT_COLOR);
        btnForgot.setContentAreaFilled(false);
        btnForgot.setBorderPainted(false);
        btnForgot.setFocusPainted(false);
        btnForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btnForgot.setForeground(PRIMARY_COLOR); }
            @Override
            public void mouseExited(MouseEvent e) { btnForgot.setForeground(TEXT_COLOR); }
        });
        btnForgot.addActionListener(e -> new FrmQuenMK().setVisible(true));
        addItem(pnlForm, btnForgot, 0, 6, new Insets(5, 0, 0, 0));

        // ====================================================================
        // XỬ LÝ SỰ KIỆN LOGIN
        // ====================================================================
        ActionListener loginAction = e -> {
        
            String email = (String) txtEmail.getEditor().getItem(); 
            
            if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.endsWith(".com")) {
                JOptionPane.showMessageDialog(null, "Email phải đúng định dạng!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            email = email.trim();

            String mk = new String(txtPassword.getPassword()).trim();
            if (mk.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }


            PhanQuyenLogin login = dnbus.BSlogin(email, mk);

            if (login != null) {
                Session.maNhanVien = login.getMaNhanVien();
                Session.role = login.getRole();
                Session.chuyenKhoa = login.getChuyenKhoa();
                Session.ma_nguoi_dung = login.getMa_nguoi_dung();
                Session.TenNhanVien = login.getTenNV();
                Session.machuyenkhoa = login.getMachuyenkhoa();
                //JOptionPane.showMessageDialog(null,  Session.machuyenkhoa );
            	  //System.out.println("Giá trị lan_dau_dang_nhap nhận được: " + login.isLanDauDangNhap());
            	if (login.isLanDauDangNhap()) { 
            		
            	    FrmDoiMkLanDau frm = new FrmDoiMkLanDau(this);
            	    frm.setVisible(true); 
            	    
            	    if (!frm.isDoiThanhCong()) {
            	        return; 
            	    }
            	}


           
                String roleUpper = Session.role.toUpperCase();
                switch (roleUpper) {
                case "BAC_SI":
                    new FrmLamViecBacSi().setVisible(true);
                    break;

                case "LE_TAN":
                    new FrmLeTan().setVisible(true);
                    break;

                case "DUOC_SI":
                case "THU_NGAN":
                    new FrmDuocSiThuNgan().setVisible(true);
                    break;

                case "NHAN_VIEN_KHO":
                    new FrmQLKhoThuoc("").setVisible(true);
                    break;

                case "QUAN_TRI_VIEN":
                    new FrmQuanLy().setVisible(true);
                    break;

                case "TRO_LY_BAC_SI_CHUYEN_KHOA":
                case "TRO_LY_BAC_SI_TONG_QUAT":
                case "KY_THUAT_VIEN_XET_NGHIEM":
                case "KY_THUAT_VIEN_CHAN_DOAN_HINH_ANH":
                    new FrmTroLy().setVisible(true);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Không có giao diện cho vai trò: " + Session.role);
                    return;
            }
            dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Sai Email hoặc Mật khẩu!", "Thất bại", JOptionPane.ERROR_MESSAGE);
            }
        };

        btnLogin.addActionListener(loginAction);
        txtPassword.addActionListener(loginAction);
        

        txtEmail.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtPassword.requestFocus();
 
                    txtEmail.hidePopup();
                }
            }
        });
    }


    private void setupAutoCompleteDB(final JComboBox<String> comboBox) {
        final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        comboBox.setModel(model);
        
        final JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ENTER) return;
                
                SwingUtilities.invokeLater(() -> {
                    String text = editor.getText();
                    
               
                    if (text.isEmpty()) {
                        comboBox.hidePopup();
                        return;
                    }

        
                    List<String> resultList = dnbus.timKiemEmail(text);

                    if (resultList != null && !resultList.isEmpty()) {
                
                        model.removeAllElements();
                        for (String s : resultList) {
                            model.addElement(s);
                        }
                        
              
                        comboBox.showPopup();
                        
                   
                        editor.setText(text);
                   
                    } else {
                        comboBox.hidePopup();
                    }
                });
            }
        });
    }


    private void styleComboBox(JComboBox<String> box) {
        box.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        box.setBackground(Color.WHITE);
        box.setPreferredSize(new Dimension(300, 35));
        
        Component editorComp = box.getEditor().getEditorComponent();
        if(editorComp instanceof JTextField) {
            JTextField txt = (JTextField) editorComp;
            txt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200)));
            txt.setBackground(Color.WHITE);
            
            txt.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) { txt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR)); }
                public void focusLost(FocusEvent e) { txt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200))); }
            });
        }
    }

    private void addItem(JPanel p, Component c, int x, int y, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y; gbc.insets = insets; gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(c, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(100, 100, 100));
        return lbl;
    }

    private void styleTextField(JTextField txt) {
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200)));
        txt.setBackground(Color.WHITE);
        txt.setPreferredSize(new Dimension(300, 35));
        
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR));
            }
            @Override
            public void focusLost(FocusEvent e) {
                txt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200)));
            }
        });
    }

    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color backgroundColor;
        private int radius = 20;

        public RoundedButton(String text, Color color) {
            super(text);
            this.backgroundColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 15));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (getModel().isPressed()) {
                g2.setColor(backgroundColor.darker());
            } else if (getModel().isRollover()) {
                g2.setColor(new Color(Math.min(backgroundColor.getRed() + 20, 255), 
                                      Math.min(backgroundColor.getGreen() + 20, 255), 
                                      Math.min(backgroundColor.getBlue() + 20, 255)));
            } else {
                g2.setColor(backgroundColor);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}