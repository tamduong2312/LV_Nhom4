package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.commons.lang3.StringUtils;

import com.custom.clockchooser.TimeClockChooser;
import com.toedter.calendar.JDateChooser;

import BUS.ChucVuBus;
import BUS.ChuyenKhoaBus;
import BUS.DangNhapBus;
import BUS.NhanVienBUS;
import MODEL.BenhNhan;
import MODEL.ChucVu;
import MODEL.ChuyenKhoa;
import MODEL.NguoiDung;
import MODEL.NhanVien;
import MODEL.PasswordEncoder;

public class frmQuanLyNhanVien extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private NhanVienBUS nvbus = new NhanVienBUS();
    private ChuyenKhoaBus ckbus = new ChuyenKhoaBus();
    private List<NhanVien> listnv = new ArrayList<>();
    private List<ChuyenKhoa> listck = new ArrayList<>();
    private JComboBox<String> cmbMaCK;
    private DangNhapBus dnbus = new DangNhapBus();
    private List<NguoiDung> listnguoidung = new ArrayList<>();

    private int index;
    private JTextField txttimkiem;

    private JTextField txtHoTen, txtCCCD, txtSDT, txtEmail, txtDiaChi, txtBangCap;

    private JComboBox<String> cmbChucVu;
    private JComboBox<String> cbGT;

    private JDateChooser dateChooserNgaySinh, DateChooseNgayVaoLam;
    private TimeClockChooser clockgiolam;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    frmQuanLyNhanVien frame = new frmQuanLyNhanVien();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String removeAccent(String s) {
        if (s == null) {
            return "";
        }
        return StringUtils.stripAccents(s).replace("đ", "d").replace("Đ", "D");
    }

    private String convertChucVuToRole(String chucVuTiengViet) {
        if (chucVuTiengViet == null || chucVuTiengViet.trim().isEmpty()) {
            return "NHAN_VIEN";
        }
        String role = removeAccent(chucVuTiengViet).toUpperCase().trim().replaceAll("\\s+", "_");
        return role;
    }

    private void loadDataComboBox() {
        listck = ckbus.getAllCK();
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("");
        for (ChuyenKhoa ck : listck) {
            boxModel.addElement(ck.getTen_chuyen_khoa());
        }
        cmbMaCK.setModel(boxModel);
    }

    private void loadDataComboBoxChucVu() {
        ChucVuBus bus = new ChucVuBus();
        List<ChucVu> listchucvu = bus.GetAllTenChucVu();
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("");
        for (ChucVu ck : listchucvu) {
            boxModel.addElement(ck.getTenChucVu());
        }
        cmbChucVu.setModel(boxModel);
    }

    private void loadDataTable() {
        listnv = nvbus.getAllNV();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (NhanVien nvs : listnv) {
            model.addRow(new Object[] {
                nvs.getMaNV(), 
                nvs.getHoTen(), 
                nvs.GioiTinh(), 
                nvs.getNgay_sinh(),
                nvs.getCCCD(), 
                nvs.getDiaChi(), 
                nvs.getSDT(),
                nvs.getEmail(), 
                nvs.getBangCap(), 
                nvs.getChucVu(),
                nvs.getMaChuyenKhoa(), 
                nvs.getNgayVaoLam(),
            });
        }
    }

    private void loadDataTIMKIEM(String id) {
        listnv = nvbus.TimkiemNV(id);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (NhanVien nvs : listnv) {
            model.addRow(new Object[] {
                nvs.getMaNV(), 
                nvs.getHoTen(), 
                nvs.GioiTinh(), 
                nvs.getNgay_sinh(),
                nvs.getCCCD(), 
                nvs.getDiaChi(), 
                nvs.getSDT(),
                nvs.getEmail(), 
                nvs.getBangCap(), 
                nvs.getChucVu(),
                nvs.getMaChuyenKhoa(), 
                nvs.getNgayVaoLam(),
            });
        }
    }

    public frmQuanLyNhanVien() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Locale.setDefault(new Locale("vi", "VN"));
        setTitle("Hệ Thống Quản Lý Nhân Viên Bệnh Viện");
        setBounds(100, 100, 1200, 750);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(0, 123, 255));
        pnlHeader.setPreferredSize(new Dimension(100, 60));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        pnlHeader.add(lblTitle);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(Color.WHITE);
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlCenter.setLayout(new BorderLayout(10, 10));
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlInput = new JPanel();
        pnlInput.setOpaque(false);
        pnlInput.setLayout(new GridBagLayout());
        pnlInput.setBorder(BorderFactory.createTitledBorder(
            null, "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14)
        ));
        pnlCenter.add(pnlInput, BorderLayout.NORTH);

        Font fontLbl = new Font("Segoe UI", Font.PLAIN, 13);
        Font fontTxt = new Font("Segoe UI", Font.PLAIN, 14);

        txtHoTen = new JTextField();
        txtHoTen.setFont(fontTxt);
        txtCCCD = new JTextField();
        txtCCCD.setFont(fontTxt);
        txtSDT = new JTextField();
        txtSDT.setFont(fontTxt);
        txtEmail = new JTextField();
        txtEmail.setFont(fontTxt);
        txtDiaChi = new JTextField();
        txtDiaChi.setFont(fontTxt);
        txtBangCap = new JTextField();
        txtBangCap.setFont(fontTxt);
        cmbChucVu = new JComboBox<>();
        cmbChucVu.setFont(fontTxt);
        cbGT = new JComboBox<>(new String[] { "Nam", "Nữ" });
        cbGT.setFont(fontTxt);
        dateChooserNgaySinh = new JDateChooser();
        dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
        DateChooseNgayVaoLam = new JDateChooser();
        DateChooseNgayVaoLam.setDateFormatString("yyyy-MM-dd");
        cmbMaCK = new JComboBox<>();
        cmbMaCK.setFont(fontTxt);

        addInputItem(pnlInput, createLabel("Họ tên:", fontLbl), 0, 0, 1, 1, 0.0);
        addInputItem(pnlInput, txtHoTen, 1, 0, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("CCCD:", fontLbl), 0, 1, 1, 1, 0.0);
        addInputItem(pnlInput, txtCCCD, 1, 1, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("SĐT:", fontLbl), 0, 2, 1, 1, 0.0);
        addInputItem(pnlInput, txtSDT, 1, 2, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Email:", fontLbl), 0, 3, 1, 1, 0.0);
        addInputItem(pnlInput, txtEmail, 1, 3, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Địa chỉ:", fontLbl), 0, 4, 1, 1, 0.0);
        addInputItem(pnlInput, txtDiaChi, 1, 4, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Giới tính:", fontLbl), 0, 5, 1, 1, 0.0);
        addInputItem(pnlInput, cbGT, 1, 5, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Ngày sinh:", fontLbl), 2, 0, 1, 1, 0.0);
        addInputItem(pnlInput, dateChooserNgaySinh, 3, 0, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Bằng cấp:", fontLbl), 2, 1, 1, 1, 0.0);
        addInputItem(pnlInput, txtBangCap, 3, 1, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Chức vụ:", fontLbl), 2, 2, 1, 1, 0.0);
        addInputItem(pnlInput, cmbChucVu, 3, 2, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Chuyên khoa:", fontLbl), 2, 3, 1, 1, 0.0);
        addInputItem(pnlInput, cmbMaCK, 3, 3, 1, 1, 1.0);
        addInputItem(pnlInput, createLabel("Ngày vào làm:", fontLbl), 2, 4, 1, 1, 0.0);
        addInputItem(pnlInput, DateChooseNgayVaoLam, 3, 4, 1, 1, 1.0);

        JPanel pnlToolBar = new JPanel(new BorderLayout());
        pnlToolBar.setOpaque(false);

        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSearch.setOpaque(false);
        pnlSearch.add(new JLabel("Tìm kiếm:"));
        txttimkiem = new JTextField(20);
        pnlSearch.add(txttimkiem);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setOpaque(false);
        JButton btnThemNV = createStyledButton("Thêm", new Color(0, 123, 255));
        JButton btnSuaNV = createStyledButton("Sửa", new Color(40, 167, 69));
        JButton btnXoaNV = createStyledButton("Xóa", new Color(220, 53, 69));
        JButton btnQuayTrLi = createStyledButton("Thoát", new Color(108, 117, 125));
        pnlButtons.add(btnThemNV);
        pnlButtons.add(btnSuaNV);
        pnlButtons.add(btnXoaNV);
        pnlButtons.add(btnQuayTrLi);

        pnlToolBar.add(pnlSearch, BorderLayout.WEST);
        pnlToolBar.add(pnlButtons, BorderLayout.EAST);

        table = new JTable();
        table.setRowHeight(28);
        table.setModel(new DefaultTableModel(
            new Object[][] {}, 
            new String[] { 
                "MaNV", "Họ Tên", "GT", "Ngày Sinh", "CCCD", "Địa Chỉ", 
                "SDT", "Email", "Bằng Cấp", "Chức Vụ", "Chuyên Khoa", "Ngày Vào Làm" 
            }
        ));
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel pnlTableContainer = new JPanel(new BorderLayout(0, 10));
        pnlTableContainer.setOpaque(false);
        pnlTableContainer.add(pnlToolBar, BorderLayout.NORTH);
        pnlTableContainer.add(scrollPane, BorderLayout.CENTER);
        pnlCenter.add(pnlTableContainer, BorderLayout.CENTER);

        // --- EVENTS ---
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                index = table.getSelectedRow();
                if (index < 0) {
                    return;
                }
                txtHoTen.setText(getString(table.getValueAt(index, 1)));
                cbGT.setSelectedItem(getString(table.getValueAt(index, 2)));
                txtCCCD.setText(getString(table.getValueAt(index, 4)));
                txtDiaChi.setText(getString(table.getValueAt(index, 5)));

              
                txtSDT.setText(getString(table.getValueAt(index, 6)));

                txtEmail.setText(getString(table.getValueAt(index, 7)));
                txtBangCap.setText(getString(table.getValueAt(index, 8)));
                cmbChucVu.setSelectedItem(getString(table.getValueAt(index, 9)));
                cmbMaCK.setSelectedItem(getString(table.getValueAt(index, 10)));
                try {
                    dateChooserNgaySinh.setDate(java.sql.Date.valueOf(getString(table.getValueAt(index, 3))));
                    DateChooseNgayVaoLam.setDate(java.sql.Date.valueOf(getString(table.getValueAt(index, 11))));
                } catch (Exception ex) {
                }
            }
        });

        btnThemNV.addActionListener(e -> {
            String sdtStr = txtSDT.getText().trim();

            if (!sdtStr.matches("0\\d{9,10}")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
                return;
            }

            thucHienThem(sdtStr);
        });

        btnSuaNV.addActionListener(e -> {
            index = table.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "Chọn nhân viên cần sửa!");
                return;
            }
            int id = (int) table.getValueAt(index, 0);
            String sdtMoi = txtSDT.getText().trim();

           
            listnv = nvbus.getAllNV();
            for (NhanVien item : listnv) {
                if (item.getMaNV() == id) {
                    continue;
                }
                if (item.getSDT().equals(sdtMoi)) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại này đã được sử dụng!");
                    return;
                }
            }
            thucHienSua(id, sdtMoi);
        });

        btnXoaNV.addActionListener(e -> {
            index = table.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!");
                return;
            }

            int id = (int) table.getValueAt(index, 0);
            String tenNV = getString(table.getValueAt(index, 1));
            
            int confirm = JOptionPane.showConfirmDialog(null, 
                    "Bạn có chắc muốn xóa nhân viên " + tenNV + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    
            if (confirm == JOptionPane.YES_OPTION) {
     
                int ketQua = nvbus.xoaNV(id);

                if (ketQua == 1) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    loadDataTable(); 
                
                } else if (ketQua == -1) {
              
                    JOptionPane.showMessageDialog(null, 
                        "Không thể xóa nhân viên này!\n" +
                        "Lý do: Nhân viên này đã có dữ liệu liên quan (Lịch trực, Phiếu khám...).\n" +
                        "Vui lòng kiểm tra lại hoặc xóa dữ liệu liên quan trước.", 
                        "Lỗi ràng buộc dữ liệu", 
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        txttimkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String t = txttimkiem.getText().trim();
                if (t.isEmpty()) {
                    loadDataTable();
                } else {
                    loadDataTIMKIEM(t);
                }
            }
        });

        btnQuayTrLi.addActionListener(e -> {
            new FrmQuanLy().setVisible(true);
            dispose();
        });

        loadDataTable();
        loadDataComboBox();
        loadDataComboBoxChucVu();
    }

   
    private void thucHienThem(String sdt) {
        try {
            LocalDate ns = dateChooserNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate nvl = DateChooseNgayVaoLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            NhanVien nv = new NhanVien(
                    txtHoTen.getText().trim(), 
                    "Nam".equals(cbGT.getSelectedItem()), 
                    ns, 
                    Long.parseLong(txtCCCD.getText().trim()), 
                    txtDiaChi.getText().trim(), 
                    sdt, 
                    txtEmail.getText().trim(), 
                    txtBangCap.getText().trim(), 
                    cmbChucVu.getSelectedItem().toString(), 
                    cmbMaCK.getSelectedItem().toString(), 
                    nvl
                );
            if(nvbus.checkTrungSDT1(sdt)) {
                JOptionPane.showMessageDialog(null, "trùng sdt!");
                return;
            }
                String role = convertChucVuToRole(cmbChucVu.getSelectedItem().toString());
                if (dnbus.themNhanVienKemTaiKhoan(nv, nv.getHoTen(), PasswordEncoder.hashPassword("123456"), role)) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                    loadDataTable();
        
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CCCD hoặc SĐT phải là định dạng số!");
            } catch (Exception ex) {
                handleSqlException(ex.getMessage()); 
            }
        }

    private void thucHienSua(int id, String sdt) {
        try {
            LocalDate ns = dateChooserNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate nvl = DateChooseNgayVaoLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            NhanVien nv = new NhanVien(
                    txtHoTen.getText().trim(), 
                    "Nam".equals(cbGT.getSelectedItem()), 
                    ns, 
                    Long.parseLong(txtCCCD.getText().trim()), 
                    txtDiaChi.getText().trim(), 
                    sdt, 
                    txtEmail.getText().trim(), 
                    txtBangCap.getText().trim(), 
                    cmbChucVu.getSelectedItem().toString(), 
                    cmbMaCK.getSelectedItem().toString(), 
                    nvl
                );
                
       if(nvbus.checkTrungSDT(sdt, id)) {
           JOptionPane.showMessageDialog(null, "trùng sdt!");
           return;
       }
                if (nvbus.SuaNV(nv, id) == 1) {
                    loadDataTable();
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CCCD hoặc SĐT không hợp lệ!");
            } catch (Exception ex) {
                handleSqlException(ex.getMessage()); 
            }
        }

    // --- UI HELPERS ---
    private JLabel createLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        return lbl;
    }

    private JButton createStyledButton(String text, Color bg) {
        RoundedButton btn = new RoundedButton(text, bg);
        btn.setPreferredSize(new Dimension(100, 35));
        return btn;
    }

    private String getString(Object v) {
        return v == null ? "" : v.toString();
    }

    private long getLong(Object v) {
        try {
            return Long.parseLong(v.toString());
        } catch (Exception e) {
            return 0;
        }
    }
    
    private void handleSqlException(String msg) {
        String lowerMsg = msg.toLowerCase();
        if (lowerMsg.contains("duplicate entry") || lowerMsg.contains("duplicate")) {
            if (lowerMsg.contains("so_dien_thoai") || lowerMsg.contains("sdt")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại này đã được sử dụng!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
            } else if (lowerMsg.contains("cccd")) {
                JOptionPane.showMessageDialog(null, "Số CCCD này đã tồn tại!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
            } else if (lowerMsg.contains("email")) {
                JOptionPane.showMessageDialog(null, "Địa chỉ Email này đã tồn tại!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào bị trùng lặp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + msg);
        }
    }

    private void addInputItem(JPanel p, javax.swing.JComponent c, int x, int y, int w, int h, double wx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = wx;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(c, gbc);
    }

    class RoundedButton extends JButton {
        private Color backgroundColor;

        public RoundedButton(String text, Color color) {
            super(text);
            this.backgroundColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? backgroundColor.darker() : backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}