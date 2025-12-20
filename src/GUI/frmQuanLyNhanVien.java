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
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                    }
                    frmQuanLyNhanVien frame = new frmQuanLyNhanVien();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static String removeAccent(String s) {
        if (s == null) return "";
   
        return StringUtils.stripAccents(s)
                .replace("đ", "d")
                .replace("Đ", "D");
    }
 
    private String convertChucVuToRole(String chucVuTiengViet) {
        if (chucVuTiengViet == null || chucVuTiengViet.trim().isEmpty()) {
            return "NHAN_VIEN"; 
        }


        String role = removeAccent(chucVuTiengViet);

     
        role = role.toUpperCase();

 
      
        role = role.trim().replaceAll("\\s+", "_");

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
    	List<ChucVu> listchucvu = new ArrayList<>();
    	
    	listchucvu = bus.GetAllTenChucVu();
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("");
        for (ChucVu ck : listchucvu) {
            boxModel.addElement(ck.getTenChucVu());
        }
        cmbChucVu.setModel(boxModel);
    }
    private void loadDataTable() {
        listnv = nvbus.getAllNV();
        listnguoidung = dnbus.getalltk();
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setNumRows(0);
        for (NhanVien nvs : listnv) {
            defaultTableModel.addRow(new Object[] {
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
        table.setModel(defaultTableModel);
    }
    
    private void loadDataTIMKIEM(String id) {
        listnv = nvbus.TimkiemNV(id);
        listnguoidung = dnbus.getalltk();
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setNumRows(0);
        for (NhanVien nvs : listnv) {
             defaultTableModel.addRow(new Object[] {
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
        table.setModel(defaultTableModel);
    }

    public frmQuanLyNhanVien() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Locale.setDefault(new Locale("vi", "VN"));
        setTitle("Hệ Thống Quản Lý Nhân Viên Bệnh Viện");
        setBounds(100, 100, 1200, 750);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

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
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Thông tin nhân viên");
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        pnlInput.setBorder(titledBorder);
        pnlCenter.add(pnlInput, BorderLayout.NORTH);

        Font fontLbl = new Font("Segoe UI", Font.PLAIN, 13);
        Font fontTxt = new Font("Segoe UI", Font.PLAIN, 14);

        txtHoTen = new JTextField(); txtHoTen.setFont(fontTxt);
        txtCCCD = new JTextField(); txtCCCD.setFont(fontTxt);
        txtSDT = new JTextField(); txtSDT.setFont(fontTxt);
        txtEmail = new JTextField(); txtEmail.setFont(fontTxt);
        txtDiaChi = new JTextField(); txtDiaChi.setFont(fontTxt);
        txtBangCap = new JTextField(); txtBangCap.setFont(fontTxt);
        

        String[] danhSachChucVu = new String[] {
       
        };
        cmbChucVu = new JComboBox<>(danhSachChucVu);
        cmbChucVu.setFont(fontTxt);
        // ----------------------------------
        
        cbGT = new JComboBox<>(new String[] { "Nam", "Nữ" }); cbGT.setFont(fontTxt);

        dateChooserNgaySinh = new JDateChooser(); 
        dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
        
        DateChooseNgayVaoLam = new JDateChooser();
        DateChooseNgayVaoLam.setDateFormatString("yyyy-MM-dd");
        
        cmbMaCK = new JComboBox<>(); cmbMaCK.setFont(fontTxt);
        clockgiolam = new TimeClockChooser();

        // --- Cột bên trái ---
        addInputItem(pnlInput, createLabel("Họ tên:", fontLbl), 0, 0, 1, 1, 0.0);
        addInputItem(pnlInput, txtHoTen,                        1, 0, 1, 1, 1.0);

        addInputItem(pnlInput, createLabel("CCCD:", fontLbl),   0, 1, 1, 1, 0.0);
        addInputItem(pnlInput, txtCCCD,                         1, 1, 1, 1, 1.0);

        addInputItem(pnlInput, createLabel("SĐT:", fontLbl),    0, 2, 1, 1, 0.0);
        addInputItem(pnlInput, txtSDT,                          1, 2, 1, 1, 1.0);
        
        addInputItem(pnlInput, createLabel("Email:", fontLbl),  0, 3, 1, 1, 0.0);
        addInputItem(pnlInput, txtEmail,                        1, 3, 1, 1, 1.0);

        addInputItem(pnlInput, createLabel("Địa chỉ:", fontLbl),0, 4, 1, 1, 0.0);
        addInputItem(pnlInput, txtDiaChi,                       1, 4, 1, 1, 1.0);
        
        addInputItem(pnlInput, createLabel("Giới tính:", fontLbl),0, 5, 1, 1, 0.0);
        addInputItem(pnlInput, cbGT,                            1, 5, 1, 1, 1.0);

     
        addInputItem(pnlInput, createLabel("Ngày sinh:", fontLbl), 2, 0, 1, 1, 0.0);
        addInputItem(pnlInput, dateChooserNgaySinh,                3, 0, 1, 1, 1.0);

        addInputItem(pnlInput, createLabel("Bằng cấp:", fontLbl),  2, 1, 1, 1, 0.0);
        addInputItem(pnlInput, txtBangCap,                         3, 1, 1, 1, 1.0);

    
        addInputItem(pnlInput, createLabel("Chức vụ:", fontLbl),   2, 2, 1, 1, 0.0);
        addInputItem(pnlInput, cmbChucVu,                          3, 2, 1, 1, 1.0);

        addInputItem(pnlInput, createLabel("Chuyên khoa:", fontLbl),2, 3, 1, 1, 0.0);
        addInputItem(pnlInput, cmbMaCK,                            3, 3, 1, 1, 1.0);
        
        addInputItem(pnlInput, createLabel("Ngày vào làm:", fontLbl),2, 4, 1, 1, 0.0);
        addInputItem(pnlInput, DateChooseNgayVaoLam,                 3, 4, 1, 1, 1.0);


        JPanel pnlActions = new JPanel(new BorderLayout());
        pnlActions.setOpaque(false);
        pnlCenter.add(pnlActions, BorderLayout.CENTER);
        
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSearch.setOpaque(false);
        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(fontLbl);
        txttimkiem = new JTextField(20);
        txttimkiem.setFont(fontTxt);
        pnlSearch.add(lblSearch);
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

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(227, 242, 253));
        table.setSelectionForeground(Color.BLACK);
        table.setShowVerticalLines(false);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);

        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "MaNV", "Họ Tên", "GT", "Ngày Sinh", "CCCD", "Địa Chỉ", "SDT", "Email", "Bằng Cấp", "Chức Vụ", "Chuyên Khoa", "Ngày Vào Làm" }
        ));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scrollPane.getViewport().setBackground(Color.WHITE);

        JPanel pnlTableContainer = new JPanel(new BorderLayout(0, 10));
        pnlTableContainer.setOpaque(false);
        
        JPanel pnlToolBar = new JPanel(new BorderLayout());
        pnlToolBar.setOpaque(false);
        pnlToolBar.add(pnlSearch, BorderLayout.WEST);   
        pnlToolBar.add(pnlButtons, BorderLayout.EAST);  
        
        pnlTableContainer.add(pnlToolBar, BorderLayout.NORTH);
        pnlTableContainer.add(scrollPane, BorderLayout.CENTER);
        
        pnlCenter.add(pnlTableContainer, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index < 0) return;

                Object val1 = table.getValueAt(index, 1);
                Object val2 = table.getValueAt(index, 2);
                Object val3 = table.getValueAt(index, 3);
                Object val4 = table.getValueAt(index, 4);
                Object val5 = table.getValueAt(index, 5);
                Object val6 = table.getValueAt(index, 6);
                Object val7 = table.getValueAt(index, 7);
                Object val8 = table.getValueAt(index, 8);
                Object val9 = table.getValueAt(index, 9); // Chức vụ
                Object val10 = table.getValueAt(index, 10);
                Object val11 = table.getValueAt(index, 11);

                String hoTen = getString(val1);
                String gioiTinh = getString(val2);
                String ngaySinh = getString(val3);
                long cccd = getLong(val4);
                String diaChi = getString(val5);
                long sdt = getLong(val6);
                String email = getString(val7);
                String bangCap = getString(val8);
                String chucVu = getString(val9);
                String maCK = getString(val10);
                String ngayVaoLam = getString(val11);

                txtHoTen.setText(hoTen);
                txtCCCD.setText(cccd + "");
                txtDiaChi.setText(diaChi);
                txtSDT.setText("0" + sdt);
                if (sdt == 0) txtSDT.setText("");
                txtEmail.setText(email);
                txtBangCap.setText(bangCap);
                
         
                cmbChucVu.setSelectedItem(chucVu);
                
                try {
                    java.util.Date ns = java.sql.Date.valueOf(ngaySinh);
                    dateChooserNgaySinh.setDate(ns);
                    java.util.Date nvl = java.sql.Date.valueOf(ngayVaoLam);
                    DateChooseNgayVaoLam.setDate(nvl);
                } catch (Exception ex) {
                }

                cbGT.setSelectedItem(gioiTinh);
                cmbMaCK.setSelectedItem(maCK);
            }
        });

        btnThemNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    
                String hoten = txtHoTen.getText().trim();
                String cccdStr = txtCCCD.getText().trim();
                String sdtStr = txtSDT.getText().trim();
                String email = txtEmail.getText().trim();
                String diaChi = txtDiaChi.getText().trim();
                String bangCap = txtBangCap.getText().trim();
                String chucVuHienThi = cmbChucVu.getSelectedItem().toString();

          
                if (hoten.isEmpty() || cccdStr.isEmpty() || sdtStr.isEmpty() || email.isEmpty() || diaChi.isEmpty()
                        || bangCap.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi nhập liệu",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!cccdStr.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(null, "CCCD phải là chuỗi số gồm 12 chữ số!", "Lỗi CCCD",
                            JOptionPane.WARNING_MESSAGE);
                    txtCCCD.requestFocus();
                    return;
                }

                if (!sdtStr.matches("0\\d{9}")) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ (Phải 10 số, bắt đầu là 0)!",
                            "Lỗi SĐT", JOptionPane.WARNING_MESSAGE);
                    txtSDT.requestFocus();
                    return;
                }

                if (!email.contains("@") || !email.endsWith(".com") || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Email không đúng định dạng (ví dụ: abc@gmail.com)!",
                            "Lỗi Email", JOptionPane.WARNING_MESSAGE);
                    txtEmail.requestFocus();
                    return;
                }

                if (DateChooseNgayVaoLam.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Ngày vào làm", "Lỗi ngày tháng",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (dateChooserNgaySinh.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Ngày sinh", "Lỗi ngày tháng",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

    
                LocalDate ngayVaoLam = DateChooseNgayVaoLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate ngaySinh = dateChooserNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (ngaySinh.isAfter(ngayVaoLam)) {
                    JOptionPane.showMessageDialog(null, "Ngày sinh không được lớn hơn ngày vào làm.", "Lỗi logic",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
    
                int tuoiKhiVaoLam = java.time.Period.between(ngaySinh, ngayVaoLam).getYears();
                if (tuoiKhiVaoLam < 18) {
                    JOptionPane.showMessageDialog(null, "Nhân viên chưa đủ 18 tuổi! (Hiện tại: " + tuoiKhiVaoLam + " tuổi)", 
                            "Lỗi độ tuổi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

          
                long cccd = Long.parseLong(cccdStr);
                long sdt = Long.parseLong(sdtStr);

                String selectedGT = cbGT.getSelectedItem().toString();
                boolean gioitinh = "Nam".equals(selectedGT);
                String maChuyenKhoa = cmbMaCK.getSelectedItem() != null ? cmbMaCK.getSelectedItem().toString() : "";

  
                String mk = "123456";
                String hashedPassword = PasswordEncoder.hashPassword(mk);
                String roleCode = convertChucVuToRole(chucVuHienThi);
                JOptionPane.showMessageDialog(null, roleCode);
        
                try {
                    NhanVien nv = new NhanVien(hoten, gioitinh, ngaySinh, cccd, diaChi, sdt, email, bangCap, chucVuHienThi,
                            maChuyenKhoa, ngayVaoLam);

               
                    DangNhapBus dnbus = new DangNhapBus();
                    boolean ketQua = dnbus.themNhanVienKemTaiKhoan(nv, hoten, hashedPassword, roleCode); 
                 

                    if (ketQua) {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên và tài khoản thành công!");
                        loadDataTable();
                   
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    String msg = ex.getMessage();
                    
              
                    if (msg != null && (msg.contains("Duplicate entry") || msg.contains("Duplicate"))) {
                        if (msg.contains("email")) {
                            JOptionPane.showMessageDialog(null, "Email '" + email + "' đã tồn tại trong hệ thống!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                            txtEmail.requestFocus();
                        } else if (msg.contains("cccd")) {
                            JOptionPane.showMessageDialog(null, "Số CCCD '" + cccdStr + "' đã tồn tại!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                            txtCCCD.requestFocus();
                        } 
                        else if (msg.contains("so_dien_thoai")) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại '" + sdtStr + "' đã tồn tại!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                            txtCCCD.requestFocus();
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "Dữ liệu bị trùng lặp: " + msg, "Lỗi CSDL", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi hệ thống: " + msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnXoaNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    index = table.getSelectedRow();
                    if (index != -1) {
                        int id = (int) table.getValueAt(index, 0);

                        int confirm = JOptionPane.showConfirmDialog(null,
                                "Bạn có chắc chắn muốn xóa nhân viên này không?\n(Tài khoản liên quan cũng sẽ bị xóa)", "Xác nhận xóa",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                        if (confirm == JOptionPane.YES_OPTION) {
                            nvbus.xoaNV(id);
                            JOptionPane.showMessageDialog(null, "Đã xóa nhân viên thành công!");
                            loadDataTable();
                        } 
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!");
                    }

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Xóa nhân viên không thành công: " + e2.getMessage());
                }
            }
        });

        btnSuaNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                 
                    index = table.getSelectedRow();
                    if (index < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên trên bảng để sửa!");
                        return;
                    }

                    int id = (int) table.getValueAt(index, 0);

            
                    String hoTen = txtHoTen.getText().trim();
                    String cccdStr = txtCCCD.getText().trim();
                    String sdtStr = txtSDT.getText().trim();
                    String email = txtEmail.getText().trim();
                    String diaChi = txtDiaChi.getText().trim();
                    String bangCap = txtBangCap.getText().trim();
                    String chucVu = cmbChucVu.getSelectedItem().toString();

              
                    if (hoTen.isEmpty() || cccdStr.isEmpty() || sdtStr.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng không để trống thông tin!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (!cccdStr.matches("\\d{12}")) {
                        JOptionPane.showMessageDialog(null, "CCCD phải là 12 chữ số!", "Lỗi CCCD", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (!sdtStr.matches("0\\d{9}")) {
                        JOptionPane.showMessageDialog(null, "SĐT không hợp lệ!", "Lỗi SĐT", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    if (!email.contains("@")) {
                         JOptionPane.showMessageDialog(null, "Email không đúng định dạng!", "Lỗi Email", JOptionPane.WARNING_MESSAGE);
                         return;
                    }

                    if(DateChooseNgayVaoLam.getDate() == null || dateChooserNgaySinh.getDate() == null) {
                         JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh và ngày vào làm!");
                         return;
                    }

            
                    long cccd = Long.parseLong(cccdStr);
                    long sdt = Long.parseLong(sdtStr);
                    
                    String selectedGT = cbGT.getSelectedItem().toString();
                    boolean gioiTinh = "Nam".equals(selectedGT);

                    LocalDate ngayVaoLam = DateChooseNgayVaoLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate ngaySinh = dateChooserNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    
  
                     if (ngaySinh.isAfter(ngayVaoLam)) {
                        JOptionPane.showMessageDialog(null, "Ngày sinh không được lớn hơn ngày vào làm.", "Lỗi logic", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String maChuyenKhoa = cmbMaCK.getSelectedItem() != null ? cmbMaCK.getSelectedItem().toString() : "";

                    // 5. Tạo đối tượng và gọi BUS
                    NhanVien nv = new NhanVien(hoTen, gioiTinh, ngaySinh, cccd, diaChi, sdt, email, bangCap, chucVu, maChuyenKhoa, ngayVaoLam);
                    
              
                    nvbus.SuaNV(nv, id); 

                    JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin nhân viên thành công!");
                    loadDataTable();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    String msg = ex.getMessage();
                    
   
                    if (msg != null && (msg.contains("Duplicate entry") || msg.contains("Duplicate"))) {
                        if (msg.contains("email")) {
                            JOptionPane.showMessageDialog(null, "Email này đã được sử dụng bởi nhân viên khác!", "Trùng Email", JOptionPane.ERROR_MESSAGE);
                        } else if (msg.contains("cccd")) {
                            JOptionPane.showMessageDialog(null, "CCCD này đã tồn tại trên hệ thống!", "Trùng CCCD", JOptionPane.ERROR_MESSAGE);
                        }
                        else if (msg.contains("so_dien_thoai")) {
                            JOptionPane.showMessageDialog(null, "số điện thoại này đã tồn tại trên hệ thống!", "Trùng sdt", JOptionPane.ERROR_MESSAGE);
                        }else {
                            JOptionPane.showMessageDialog(null, "Dữ liệu trùng lặp: " + msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi sửa nhân viên: " + msg);
                    }
                }
            }
        });

        txttimkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txttimkiem.getText().trim();
                if (text.isEmpty()) {
                    loadDataTable();
                } else {
                    try {
                        loadDataTIMKIEM(text);
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        });

        btnQuayTrLi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                   FrmQuanLy ql = new FrmQuanLy();
                   ql.show();
                   dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        loadDataTable();
        loadDataComboBox();
        loadDataComboBoxChucVu();
    }
    
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
    
    private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

    private long getLong(Object value) {
        try {
            return value == null ? 0 : Long.parseLong(value.toString());
        } catch(Exception e) { return 0; }
    }

    private void addInputItem(JPanel p, javax.swing.JComponent c, int x, int y, int w, int h, double weightx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = weightx;
        gbc.insets = new Insets(5, 10, 5, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        p.add(c, gbc);
    }

    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color backgroundColor;
        private int radius = 15;

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
            if (getModel().isPressed()) {
                g2.setColor(backgroundColor.darker());
            } else {
                g2.setColor(backgroundColor);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}