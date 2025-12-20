package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.commons.lang3.StringUtils; // Đảm bảo bạn đã có thư viện này

import BUS.ChuyenKhoaBus;
import BUS.DangNhapBus;
import BUS.NhanVienBUS;
import BUS.VaiTroBus; // Import BUS Vai Trò
import MODEL.ChuyenKhoa;
import MODEL.NguoiDung;
import MODEL.NhanVien;
import MODEL.VaiTro;  // Import Model Vai Trò
import MODEL.PasswordEncoder;

public class FrmQuanLyTaiKhoan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    

    private JTextField txtten;
    private JTextField txtmk;
    private JTextField txtemail;
    private JComboBox<String> cboVaiTro;
    private JTable tableTaiKhoan; 
    private JTable tableNhanVienChuaCoTK; 
    
  
    private DangNhapBus dnBus = new DangNhapBus();
    private NhanVienBUS nvBus = new NhanVienBUS();
    private VaiTroBus vaiTroBus = new VaiTroBus(); 
    
    private List<NguoiDung> listTaiKhoan = new ArrayList<>();
    private List<NhanVien> listNhanVien = new ArrayList<>();
    private List<VaiTro> listVaiTro = new ArrayList<>(); 
    
    private int selectedRowTK = -1;
    private int selectedRowNV = -1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmQuanLyTaiKhoan frame = new FrmQuanLyTaiKhoan();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void resetForm() {
        txtemail.setText("");
        txtten.setText("");
        txtmk.setText("");
        tableTaiKhoan.clearSelection();
        tableNhanVienChuaCoTK.clearSelection();
        selectedRowTK = -1;
        selectedRowNV = -1;
    }

    private void loadTableTaiKhoan() {
        listTaiKhoan = dnBus.getalltk();
        DefaultTableModel model = (DefaultTableModel) tableTaiKhoan.getModel();
        model.setRowCount(0);
        for (NguoiDung nd : listTaiKhoan) {
            model.addRow(new Object[]{
                nd.getMaNguoiDung(), 
                nd.getTenDangNhap(), 
                nd.getEmail(), 
                "********", 
                nd.getRole(), 
                nd.getNhanvienid()
            });
        }
    }

    private void loadTableNhanVienChuaCoTK() {
        listNhanVien = nvBus.getAllNV(); 
        DefaultTableModel model = (DefaultTableModel) tableNhanVienChuaCoTK.getModel();
        model.setRowCount(0);

        for (NhanVien nv : listNhanVien) {
            boolean daCoTK = false;
            for(NguoiDung nd : listTaiKhoan) {
                if(nd.getNhanvienid() == nv.getMaNV()) {
                    daCoTK = true;
                    break;
                }
            }
            
            if(!daCoTK) {
                model.addRow(new Object[]{
                    nv.getMaNV(), 
                    nv.getHoTen(), 
                    nv.getEmail(), 
                    nv.getChucVu(),
                    nv.getMaChuyenKhoa()
                });
            }
        }
    }


    private void loadDataCboVaiTro() {
        listVaiTro = vaiTroBus.GetAllTenVaiTro(); 
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        for (VaiTro vt : listVaiTro) {
    
            model.addElement(vt.getMaVaiTro());
        }
        cboVaiTro.setModel(model);
    }

    public FrmQuanLyTaiKhoan() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hệ Thống Quản Lý Tài Khoản");
        setBounds(100, 100, 1250, 750); 
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // HEADER
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(0, 123, 255));
        pnlHeader.setPreferredSize(new Dimension(100, 60));
        pnlHeader.setLayout(new GridBagLayout());
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("QUẢN LÝ TÀI KHOẢN NGƯỜI DÙNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);

        // CENTER
        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(240, 242, 245));
        pnlCenter.setLayout(new BorderLayout(10, 10));
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        // INPUT AREA
        JPanel pnlInputContainer = new JPanel(new BorderLayout());
        pnlInputContainer.setOpaque(false);
        
        JPanel pnlInput = new JPanel();
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(15, 20, 15, 20)
        ));
        pnlInput.setLayout(new GridBagLayout());
        pnlInputContainer.add(pnlInput, BorderLayout.CENTER);
        pnlCenter.add(pnlInputContainer, BorderLayout.NORTH);

        Font fontLbl = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontTxt = new Font("Segoe UI", Font.PLAIN, 14);

        txtten = new JTextField(); txtten.setFont(fontTxt);
        txtmk = new JTextField(); txtmk.setFont(fontTxt);
        txtemail = new JTextField(); txtemail.setFont(fontTxt);
        
  
        cboVaiTro = new JComboBox<>();
        cboVaiTro.setFont(fontTxt);


        addInputItem(pnlInput, createLabel("Username:", fontLbl), 0, 0);
        addInputItem(pnlInput, txtten, 1, 0);
        addInputItem(pnlInput, createLabel("Mật khẩu:", fontLbl), 2, 0);
        addInputItem(pnlInput, txtmk, 3, 0);
        addInputItem(pnlInput, createLabel("Email:", fontLbl), 0, 1);
        addInputItem(pnlInput, txtemail, 1, 1);
        addInputItem(pnlInput, createLabel("Vai trò:", fontLbl), 2, 1);
        addInputItem(pnlInput, cboVaiTro, 3, 1);

        // BUTTONS
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(new MatteBorder(1, 0, 0, 0, new Color(230,230,230)));
        pnlInputContainer.add(pnlButtons, BorderLayout.SOUTH);

        JButton btnCapLai = createStyledButton("Cấp Lại TK", new Color(0, 123, 255)); 
        JButton btnSua = createStyledButton("Cập nhật", new Color(0, 200, 83));
        JButton btnKhoa = createStyledButton("Xóa", new Color(220, 53, 69)); 
        JButton btnQuayTrLi = createStyledButton("Thoát", new Color(108, 117, 125));

        pnlButtons.add(btnCapLai);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnKhoa);
        pnlButtons.add(btnQuayTrLi);

        // TABLES
        JPanel pnlTables = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlTables.setOpaque(false);
        pnlCenter.add(pnlTables, BorderLayout.CENTER);

        JPanel pnlLeftTable = new JPanel(new BorderLayout());
        pnlLeftTable.setBackground(Color.WHITE);
        pnlLeftTable.setBorder(BorderFactory.createTitledBorder(null, "DANH SÁCH TÀI KHOẢN ĐANG CÓ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", Font.BOLD, 14), new Color(0, 123, 255)));
        
        tableTaiKhoan = createCustomTable();
        tableTaiKhoan.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Username", "Email", "Pass", "Vai trò", "Mã NV"}
        ));
        pnlLeftTable.add(new JScrollPane(tableTaiKhoan), BorderLayout.CENTER);
        pnlTables.add(pnlLeftTable);

        JPanel pnlRightTable = new JPanel(new BorderLayout());
        pnlRightTable.setBackground(Color.WHITE);
        pnlRightTable.setBorder(BorderFactory.createTitledBorder(null, "NHÂN VIÊN CHƯA CÓ TÀI KHOẢN (Chọn để cấp)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", Font.BOLD, 14), new Color(255, 87, 34)));

        tableNhanVienChuaCoTK = createCustomTable();
        tableNhanVienChuaCoTK.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID NV", "Họ tên", "Email", "Chức vụ", "Chuyên khoa"}
        ));
        pnlRightTable.add(new JScrollPane(tableNhanVienChuaCoTK), BorderLayout.CENTER);
        pnlTables.add(pnlRightTable);

        // --- SỰ KIỆN ---
        
        tableTaiKhoan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedRowTK = tableTaiKhoan.getSelectedRow();
                if (selectedRowTK != -1) {
                    txtten.setText(getString(tableTaiKhoan.getValueAt(selectedRowTK, 1)));
                    txtemail.setText(getString(tableTaiKhoan.getValueAt(selectedRowTK, 2)));
                    txtmk.setText(""); 
                    cboVaiTro.setSelectedItem(getString(tableTaiKhoan.getValueAt(selectedRowTK, 4)));
                    
                    tableNhanVienChuaCoTK.clearSelection(); 
                    selectedRowNV = -1;
                }
            }
        });
        
        tableNhanVienChuaCoTK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRowNV = tableNhanVienChuaCoTK.getSelectedRow();
                if(selectedRowNV != -1) {
                    String tenNV = getString(tableNhanVienChuaCoTK.getValueAt(selectedRowNV, 1));
                    String email = getString(tableNhanVienChuaCoTK.getValueAt(selectedRowNV, 2));
                    String chucVu = getString(tableNhanVienChuaCoTK.getValueAt(selectedRowNV, 3));
                    
                    txtemail.setText(email);
                    txtten.setText(convertTenToUsername(tenNV)); 
                    txtmk.setText("123456"); 
                    cboVaiTro.setSelectedItem(convertChucVuToRole(chucVu)); 

                    tableTaiKhoan.clearSelection();
                    selectedRowTK = -1;
                }
            }
        });

        btnCapLai.addActionListener(e -> {
            if (selectedRowNV == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên chưa có tài khoản ở bảng bên phải!", "Chưa chọn", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int maNV = Integer.parseInt(tableNhanVienChuaCoTK.getValueAt(selectedRowNV, 0).toString());
            String hoTen = tableNhanVienChuaCoTK.getValueAt(selectedRowNV, 1).toString();
            String username = txtten.getText().trim();
            String email = txtemail.getText().trim();
            String pass = txtmk.getText().trim();
            String role = cboVaiTro.getSelectedItem().toString();

            if(username.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            boolean success = dnBus.capLaiTaiKhoan(maNV, hoTen, email, role); 

            if (success) {
                JOptionPane.showMessageDialog(null, "Cấp tài khoản thành công!");
                loadTableTaiKhoan();
                loadTableNhanVienChuaCoTK();
                resetForm();
            } else {
                JOptionPane.showMessageDialog(null, "Thất bại! Có thể username hoặc email đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSua.addActionListener(e -> {
            if (selectedRowTK == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa ở bảng bên trái!");
                return;
            }
            int idTK = Integer.parseInt(tableTaiKhoan.getValueAt(selectedRowTK, 0).toString());
            
            String ten = txtten.getText().trim();
            String email = txtemail.getText().trim();
            String vaitro = cboVaiTro.getSelectedItem().toString();
            String passMoi = txtmk.getText().trim();
            String mkmahoa = PasswordEncoder.hashPassword(passMoi);
            NguoiDung nd = new NguoiDung(ten, mkmahoa, vaitro, email);
            int kq = dnBus.SuaTK(nd, idTK); 
            
            if(kq > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                loadTableTaiKhoan();
                resetForm();
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại.");
            }
        });

        btnKhoa.addActionListener(e -> {
            if (selectedRowTK == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản bên trái!");
                return;
            }
            int idTK = Integer.parseInt(tableTaiKhoan.getValueAt(selectedRowTK, 0).toString());
            
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dnBus.xoaTK(idTK);
                loadTableTaiKhoan();
                loadTableNhanVienChuaCoTK(); 
                resetForm();
                JOptionPane.showMessageDialog(null, "Đã xóa tài khoản!");
            }
        });
        
        btnQuayTrLi.addActionListener(e -> {
             FrmQuanLy ql = new FrmQuanLy();
             ql.setVisible(true);
             dispose();
        });


        loadDataCboVaiTro(); 
        loadTableTaiKhoan();
        loadTableNhanVienChuaCoTK();
    }
    

    private String convertTenToUsername(String hoTen) {
        if (hoTen == null) return "";
        return StringUtils.stripAccents(hoTen)
                .replace("đ", "d")
                .replace("Đ", "D")
                .replace(" ", "")
                .toLowerCase();
    }
    
    private String convertChucVuToRole(String chucVu) {
        if(chucVu == null) return "NHAN_VIEN";
        String role = StringUtils.stripAccents(chucVu)
                .replace("đ", "d")
                .replace("Đ", "D");
        role = role.toUpperCase();
        role = role.trim().replaceAll("\\s+", "_");
        return role;
    }

    private void addInputItem(JPanel p, JComponent c, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        if(x % 2 != 0) gbc.weightx = 1.0; 
        p.add(c, gbc);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        return lbl;
    }
    
    private JButton createStyledButton(String text, Color bg) {
        RoundedButton btn = new RoundedButton(text, bg);
        btn.setPreferredSize(new Dimension(120, 35));
        return btn;
    }
    
    private String getString(Object obj) {
        return obj == null ? "" : obj.toString();
    }
    
    private JTable createCustomTable() {
        JTable tbl = new JTable();
        tbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tbl.setRowHeight(25);
        tbl.setGridColor(new Color(230, 230, 230));
        tbl.setSelectionBackground(new Color(227, 242, 253));
        tbl.setSelectionForeground(Color.BLACK);
        tbl.setShowVerticalLines(false);
        JTableHeader header = tbl.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);
        return tbl;
    }
    
    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color backgroundColor;
        private int radius = 15;
        public RoundedButton(String text, Color color) {
            super(text);
            this.backgroundColor = color;
            setContentAreaFilled(false); setFocusPainted(false); setBorderPainted(false);
            setForeground(Color.WHITE); setFont(new Font("Segoe UI", Font.BOLD, 13));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (getModel().isPressed()) g2.setColor(backgroundColor.darker());
            else g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}