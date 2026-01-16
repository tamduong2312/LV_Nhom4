package GUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.toedter.calendar.JDateChooser;
import BUS.ThuocBus;
import MODEL.Session;
import MODEL.Thuoc;

public class FrmQuanLyThuoc extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JPanel pnlHeader;
    private JLabel lblTitle;

    private JPanel pnlCenter;
    private JPanel pnlInput;
    private JLabel lblTenThuoc, lblHoatChat, lblHamLuong, lblDangThuoc, lblLoaiThuoc, lblDonViTinh;
    private JLabel lblDonGiaNhap, lblHeSo, lblDonGiaBan, lblNhaSanXuat, lblNuocSanXuat, lblGhiChu, lblNSX, lblHSD;
    private JTextField txtTenThuoc, txtHoatChat, txtHamLuong, txtDangThuoc, txtLoaiThuoc, txtDonViTinh;
    private JTextField txtDonGiaNhap, txtHeSo, txtDonGiaBan, txtNhaSanXuat, txtNuocSanXuat, txtGhiChu;
    private JDateChooser dateChooserNSX, dateChooserHSD;

    private JPanel pnlTableArea;
    private JPanel pnlSearch;
    private JLabel lblSearch;
    private JTextField txtTimKiem;
    private JScrollPane scrollPane;
    private JTable table;

    private JPanel pnlBottom;
    private JButton btnLamMoi, btnThem, btnSua, btnXoa, btnQuayLai;

    // Logic
    private List<Thuoc> listthuoc = new ArrayList<>();
    private ThuocBus thuocbus = new ThuocBus();
    private int index = -1;
    private DecimalFormat df = new DecimalFormat("#,###");
    private boolean isAdmin = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          
                if (Session.role == null) {
                    Session.role = "QUAN_TRI_VIEN";
                }
                FrmQuanLyThuoc frame = new FrmQuanLyThuoc(Session.role);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmQuanLyThuoc(String role) {
        this.isAdmin = role != null && role.trim().equals("QUAN_TRI_VIEN");

        setTitle(isAdmin ? "Quản Lý Thuốc (QUẢN TRỊ VIÊN)" : "Quản Lý Thuốc (DƯỢC SĨ)");
        Locale.setDefault(new Locale("vi", "VN"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1350, 750);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 245, 250));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        pnlHeader = new JPanel();
        pnlHeader.setBackground(isAdmin ? new Color(0, 123, 255) : new Color(0, 153, 102));
        pnlHeader.setPreferredSize(new Dimension(100, 60));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        lblTitle = new JLabel("DANH MỤC THUỐC ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);


        pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(240, 245, 250));
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlCenter.setLayout(new BorderLayout(10, 10));
        contentPane.add(pnlCenter, BorderLayout.CENTER);


        pnlInput = new JPanel();
        pnlInput.setBackground(Color.WHITE);
        Color borderColor = isAdmin ? new Color(0, 123, 255) : new Color(0, 153, 102);
        pnlInput.setBorder(new TitledBorder(
                new LineBorder(borderColor, 1, true),
                "Thông tin chi tiết",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                borderColor));
        pnlCenter.add(pnlInput, BorderLayout.NORTH);

        GridBagLayout gbl_pnlInput = new GridBagLayout();
        gbl_pnlInput.columnWidths = new int[]{110, 280, 110, 280, 110, 280};
        pnlInput.setLayout(gbl_pnlInput);

        // --- HÀNG 1 ---
        lblTenThuoc = new JLabel("Tên thuốc:");
        GridBagConstraints gbc_lblTen = new GridBagConstraints();
        gbc_lblTen.anchor = GridBagConstraints.WEST;
        gbc_lblTen.insets = new Insets(10, 15, 5, 5);
        gbc_lblTen.gridx = 0;
        gbc_lblTen.gridy = 0;
        pnlInput.add(lblTenThuoc, gbc_lblTen);

        txtTenThuoc = new JTextField();
        GridBagConstraints gbc_txtTen = new GridBagConstraints();
        gbc_txtTen.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtTen.insets = new Insets(10, 0, 5, 15);
        gbc_txtTen.gridx = 1;
        gbc_txtTen.gridy = 0;
        pnlInput.add(txtTenThuoc, gbc_txtTen);

        lblDonViTinh = new JLabel("Đơn vị tính:");
        GridBagConstraints gbc_lblDVT = new GridBagConstraints();
        gbc_lblDVT.anchor = GridBagConstraints.WEST;
        gbc_lblDVT.insets = new Insets(10, 15, 5, 5);
        gbc_lblDVT.gridx = 2;
        gbc_lblDVT.gridy = 0;
        pnlInput.add(lblDonViTinh, gbc_lblDVT);

        txtDonViTinh = new JTextField();
        GridBagConstraints gbc_txtDVT = new GridBagConstraints();
        gbc_txtDVT.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDVT.insets = new Insets(10, 0, 5, 15);
        gbc_txtDVT.gridx = 3;
        gbc_txtDVT.gridy = 0;
        pnlInput.add(txtDonViTinh, gbc_txtDVT);

        lblNSX = new JLabel("Ngày SX:");
        GridBagConstraints gbc_lblNSX = new GridBagConstraints();
        gbc_lblNSX.anchor = GridBagConstraints.WEST;
        gbc_lblNSX.insets = new Insets(10, 15, 5, 5);
        gbc_lblNSX.gridx = 4;
        gbc_lblNSX.gridy = 0;
        pnlInput.add(lblNSX, gbc_lblNSX);

        dateChooserNSX = new JDateChooser();
        dateChooserNSX.setDateFormatString("yyyy-MM-dd");
        GridBagConstraints gbc_dateNSX = new GridBagConstraints();
        gbc_dateNSX.fill = GridBagConstraints.HORIZONTAL;
        gbc_dateNSX.insets = new Insets(10, 0, 5, 15);
        gbc_dateNSX.gridx = 5;
        gbc_dateNSX.gridy = 0;
        pnlInput.add(dateChooserNSX, gbc_dateNSX);


        lblHoatChat = new JLabel("Hoạt chất:");
        GridBagConstraints gbc_lblHC = new GridBagConstraints();
        gbc_lblHC.anchor = GridBagConstraints.WEST;
        gbc_lblHC.insets = new Insets(5, 15, 5, 5);
        gbc_lblHC.gridx = 0;
        gbc_lblHC.gridy = 1;
        pnlInput.add(lblHoatChat, gbc_lblHC);

        txtHoatChat = new JTextField();
        GridBagConstraints gbc_txtHC = new GridBagConstraints();
        gbc_txtHC.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtHC.insets = new Insets(5, 0, 5, 15);
        gbc_txtHC.gridx = 1;
        gbc_txtHC.gridy = 1;
        pnlInput.add(txtHoatChat, gbc_txtHC);

        lblDonGiaNhap = new JLabel("Giá nhập:");
        GridBagConstraints gbc_lblGN = new GridBagConstraints();
        gbc_lblGN.anchor = GridBagConstraints.WEST;
        gbc_lblGN.insets = new Insets(5, 15, 5, 5);
        gbc_lblGN.gridx = 2;
        gbc_lblGN.gridy = 1;
        pnlInput.add(lblDonGiaNhap, gbc_lblGN);

        txtDonGiaNhap = new JTextField();
        GridBagConstraints gbc_txtGN = new GridBagConstraints();
        gbc_txtGN.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtGN.insets = new Insets(5, 0, 5, 15);
        gbc_txtGN.gridx = 3;
        gbc_txtGN.gridy = 1;
        pnlInput.add(txtDonGiaNhap, gbc_txtGN);
        if (!isAdmin) {
            txtDonGiaNhap.setEditable(false);
            txtDonGiaNhap.setBackground(new Color(245, 245, 245));
        }

        lblHSD = new JLabel("Hạn SD:");
        GridBagConstraints gbc_lblHSD = new GridBagConstraints();
        gbc_lblHSD.anchor = GridBagConstraints.WEST;
        gbc_lblHSD.insets = new Insets(5, 15, 5, 5);
        gbc_lblHSD.gridx = 4;
        gbc_lblHSD.gridy = 1;
        pnlInput.add(lblHSD, gbc_lblHSD);

        dateChooserHSD = new JDateChooser();
        dateChooserHSD.setDateFormatString("yyyy-MM-dd");
        GridBagConstraints gbc_dateHSD = new GridBagConstraints();
        gbc_dateHSD.fill = GridBagConstraints.HORIZONTAL;
        gbc_dateHSD.insets = new Insets(5, 0, 5, 15);
        gbc_dateHSD.gridx = 5;
        gbc_dateHSD.gridy = 1;
        pnlInput.add(dateChooserHSD, gbc_dateHSD);


        lblHamLuong = new JLabel("Hàm lượng:");
        GridBagConstraints gbc_lblHL = new GridBagConstraints();
        gbc_lblHL.anchor = GridBagConstraints.WEST;
        gbc_lblHL.insets = new Insets(5, 15, 5, 5);
        gbc_lblHL.gridx = 0;
        gbc_lblHL.gridy = 2;
        pnlInput.add(lblHamLuong, gbc_lblHL);

        txtHamLuong = new JTextField();
        GridBagConstraints gbc_txtHL = new GridBagConstraints();
        gbc_txtHL.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtHL.insets = new Insets(5, 0, 5, 15);
        gbc_txtHL.gridx = 1;
        gbc_txtHL.gridy = 2;
        pnlInput.add(txtHamLuong, gbc_txtHL);

        lblHeSo = new JLabel("Hệ số lãi:");
        GridBagConstraints gbc_lblHS = new GridBagConstraints();
        gbc_lblHS.anchor = GridBagConstraints.WEST;
        gbc_lblHS.insets = new Insets(5, 15, 5, 5);
        gbc_lblHS.gridx = 2;
        gbc_lblHS.gridy = 2;
        pnlInput.add(lblHeSo, gbc_lblHS);

        txtHeSo = new JTextField("1.2");
        GridBagConstraints gbc_txtHS = new GridBagConstraints();
        gbc_txtHS.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtHS.insets = new Insets(5, 0, 5, 15);
        gbc_txtHS.gridx = 3;
        gbc_txtHS.gridy = 2;
        pnlInput.add(txtHeSo, gbc_txtHS);
        if (!isAdmin) {
            txtHeSo.setEditable(false);
            txtHeSo.setBackground(new Color(245, 245, 245));
        }

        lblNhaSanXuat = new JLabel("Nhà SX:");
        GridBagConstraints gbc_lblNSX1 = new GridBagConstraints();
        gbc_lblNSX1.anchor = GridBagConstraints.WEST;
        gbc_lblNSX1.insets = new Insets(5, 15, 5, 5);
        gbc_lblNSX1.gridx = 4;
        gbc_lblNSX1.gridy = 2;
        pnlInput.add(lblNhaSanXuat, gbc_lblNSX1);

        txtNhaSanXuat = new JTextField();
        GridBagConstraints gbc_txtNSX1 = new GridBagConstraints();
        gbc_txtNSX1.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNSX1.insets = new Insets(5, 0, 5, 15);
        gbc_txtNSX1.gridx = 5;
        gbc_txtNSX1.gridy = 2;
        pnlInput.add(txtNhaSanXuat, gbc_txtNSX1);

        // --- HÀNG 4 ---
        lblDangThuoc = new JLabel("Dạng thuốc:");
        GridBagConstraints gbc_lblDT = new GridBagConstraints();
        gbc_lblDT.anchor = GridBagConstraints.WEST;
        gbc_lblDT.insets = new Insets(5, 15, 5, 5);
        gbc_lblDT.gridx = 0;
        gbc_lblDT.gridy = 3;
        pnlInput.add(lblDangThuoc, gbc_lblDT);

        txtDangThuoc = new JTextField();
        GridBagConstraints gbc_txtDT = new GridBagConstraints();
        gbc_txtDT.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDT.insets = new Insets(5, 0, 5, 15);
        gbc_txtDT.gridx = 1;
        gbc_txtDT.gridy = 3;
        pnlInput.add(txtDangThuoc, gbc_txtDT);

        lblDonGiaBan = new JLabel("Giá bán:");
        GridBagConstraints gbc_lblGB = new GridBagConstraints();
        gbc_lblGB.anchor = GridBagConstraints.WEST;
        gbc_lblGB.insets = new Insets(5, 15, 5, 5);
        gbc_lblGB.gridx = 2;
        gbc_lblGB.gridy = 3;
        pnlInput.add(lblDonGiaBan, gbc_lblGB);

        txtDonGiaBan = new JTextField();
        txtDonGiaBan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        GridBagConstraints gbc_txtGB = new GridBagConstraints();
        gbc_txtGB.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtGB.insets = new Insets(5, 0, 5, 15);
        gbc_txtGB.gridx = 3;
        gbc_txtGB.gridy = 3;
        pnlInput.add(txtDonGiaBan, gbc_txtGB);

        lblNuocSanXuat = new JLabel("Nước SX:");
        GridBagConstraints gbc_lblNuoc = new GridBagConstraints();
        gbc_lblNuoc.anchor = GridBagConstraints.WEST;
        gbc_lblNuoc.insets = new Insets(5, 15, 5, 5);
        gbc_lblNuoc.gridx = 4;
        gbc_lblNuoc.gridy = 3;
        pnlInput.add(lblNuocSanXuat, gbc_lblNuoc);

        txtNuocSanXuat = new JTextField();
        GridBagConstraints gbc_txtNuoc = new GridBagConstraints();
        gbc_txtNuoc.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNuoc.insets = new Insets(5, 0, 5, 15);
        gbc_txtNuoc.gridx = 5;
        gbc_txtNuoc.gridy = 3;
        pnlInput.add(txtNuocSanXuat, gbc_txtNuoc);


        lblLoaiThuoc = new JLabel("Loại thuốc:");
        GridBagConstraints gbc_lblLT = new GridBagConstraints();
        gbc_lblLT.anchor = GridBagConstraints.WEST;
        gbc_lblLT.insets = new Insets(5, 15, 10, 5);
        gbc_lblLT.gridx = 0;
        gbc_lblLT.gridy = 4;
        pnlInput.add(lblLoaiThuoc, gbc_lblLT);

        txtLoaiThuoc = new JTextField();
        GridBagConstraints gbc_txtLT = new GridBagConstraints();
        gbc_txtLT.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtLT.insets = new Insets(5, 0, 10, 15);
        gbc_txtLT.gridx = 1;
        gbc_txtLT.gridy = 4;
        pnlInput.add(txtLoaiThuoc, gbc_txtLT);

        lblGhiChu = new JLabel("Ghi chú:");
        GridBagConstraints gbc_lblGC = new GridBagConstraints();
        gbc_lblGC.anchor = GridBagConstraints.WEST;
        gbc_lblGC.insets = new Insets(5, 15, 10, 5);
        gbc_lblGC.gridx = 4;
        gbc_lblGC.gridy = 4;
        pnlInput.add(lblGhiChu, gbc_lblGC);

        txtGhiChu = new JTextField();
        GridBagConstraints gbc_txtGC = new GridBagConstraints();
        gbc_txtGC.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtGC.insets = new Insets(5, 0, 10, 15);
        gbc_txtGC.gridx = 5;
        gbc_txtGC.gridy = 4;
        pnlInput.add(txtGhiChu, gbc_txtGC);

     
        pnlTableArea = new JPanel(new BorderLayout(0, 10));
        pnlTableArea.setOpaque(false);
        pnlCenter.add(pnlTableArea, BorderLayout.CENTER);

        pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlSearch.setOpaque(false);
        lblSearch = new JLabel("Tìm kiếm thuốc:");
        pnlSearch.add(lblSearch);
        txtTimKiem = new JTextField(35);
        pnlSearch.add(txtTimKiem);
        pnlTableArea.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);
        scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        pnlTableArea.add(scrollPane, BorderLayout.CENTER);


        pnlBottom = new JPanel();
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(new MatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        btnQuayLai = new JButton("Quay lại");
        setupButtonStyle(btnQuayLai, new Color(108, 117, 125));
        pnlBottom.add(btnQuayLai);

        btnLamMoi = new JButton("Làm mới");
        setupButtonStyle(btnLamMoi, new Color(23, 162, 184));
        pnlBottom.add(btnLamMoi);

        btnXoa = new JButton("Xóa Thuốc");
        setupButtonStyle(btnXoa, new Color(220, 53, 69));
        pnlBottom.add(btnXoa);

        btnSua = new JButton("Cập Nhật");
        setupButtonStyle(btnSua, new Color(0, 200, 83));
        pnlBottom.add(btnSua);

        btnThem = new JButton("THÊM MỚI THUỐC");
        setupButtonStyle(btnThem, new Color(0, 123, 255));
        btnThem.setPreferredSize(new Dimension(180, 40));
        pnlBottom.add(btnThem);

        initLogicEvents();
        if (!java.beans.Beans.isDesignTime()) {
            loadDataTable();
        }
    }

    private void setupButtonStyle(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(140, 40));
    }

    private void initLogicEvents() {
        // --- LOGIC TÍNH GIÁ (ADMIN) ---
        if (isAdmin) {
            KeyAdapter kPrice = new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    calculatePrice();
                }
            };
            txtDonGiaNhap.addKeyListener(kPrice);
            txtHeSo.addKeyListener(kPrice);
        }

        // --- LOGIC CLICK BẢNG ---
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                index = table.getSelectedRow();
                if (index == -1) {
                    return;
                }
                Thuoc t = listthuoc.get(index);
                txtTenThuoc.setText(t.getTenthuoc());
                txtHoatChat.setText(t.getHoatchat());
                txtHamLuong.setText(t.getHamluong());
                txtDangThuoc.setText(t.getDangthuoc());
                txtLoaiThuoc.setText(t.getLoaithuoc());
                txtDonViTinh.setText(t.getDonvitinh());
                txtNhaSanXuat.setText(t.getNhasanxuat());
                txtNuocSanXuat.setText(t.getNuocsanxuat());
                txtGhiChu.setText(t.getGhichu());
                if (isAdmin) {
                    txtDonGiaNhap.setText(String.format("%.0f", t.getDongianhap()));
                    txtDonGiaBan.setText(String.format("%.0f", t.getDongiaban()));
                }
                try {
                    dateChooserNSX.setDate(java.sql.Date.valueOf(t.getNgaysanxuat()));
                    dateChooserHSD.setDate(java.sql.Date.valueOf(t.getHansudung()));
                } catch (Exception ex) {
                }
            }
        });

        // --- LOGIC TÌM KIẾM ---
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String t = txtTimKiem.getText().trim();
                if (t.isEmpty()) {
                    loadDataTable();
                } else {
                    loadDataTableTIMKIEM(t);
                }
            }
        });

        // ---  THÊM THUỐC ---
        btnThem.addActionListener(e -> {
            if (txtTenThuoc.getText().trim().isEmpty() || dateChooserNSX.getDate() == null || dateChooserHSD.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên và ngày hạn dùng!");
                return;
            }
            try {
                Thuoc t = new Thuoc();
                t.setTenthuoc(txtTenThuoc.getText().trim());
                t.setHoatchat(txtHoatChat.getText().trim());
                t.setHamluong(txtHamLuong.getText().trim());
                t.setDangthuoc(txtDangThuoc.getText().trim());
                t.setLoaithuoc(txtLoaiThuoc.getText().trim());
                t.setDonvitinh(txtDonViTinh.getText().trim());
                t.setNhasanxuat(txtNhaSanXuat.getText().trim());
                t.setNuocsanxuat(txtNuocSanXuat.getText().trim());
                t.setGhichu(txtGhiChu.getText().trim());
                t.setSoluongton(0);
                t.setNgaysanxuat(dateChooserNSX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                t.setHansudung(dateChooserHSD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                if (isAdmin) {
                    t.setDongianhap(Double.parseDouble(txtDonGiaNhap.getText().trim()));
                    t.setDongiaban(Double.parseDouble(txtDonGiaBan.getText().trim()));
                }
                if (thuocbus.ThemThuoc(t) == 1) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadDataTable();
                    xoaTrang();
                }
            } catch (Exception ex) {
            	handleException(ex); 
            }
        });

        // --- CẬP NHẬT ---
        btnSua.addActionListener(e -> {
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Chọn thuốc trên bảng!");
                return;
            }
            try {
                int id = (int) table.getValueAt(index, 0);
                Thuoc t = listthuoc.get(index);
                t.setTenthuoc(txtTenThuoc.getText().trim());
                t.setHoatchat(txtHoatChat.getText().trim());
                t.setHamluong(txtHamLuong.getText().trim());
                t.setDangthuoc(txtDangThuoc.getText().trim());
                t.setLoaithuoc(txtLoaiThuoc.getText().trim());
                t.setDonvitinh(txtDonViTinh.getText().trim());
                t.setNhasanxuat(txtNhaSanXuat.getText().trim());
                t.setNuocsanxuat(txtNuocSanXuat.getText().trim());
                t.setGhichu(txtGhiChu.getText().trim());
                if (isAdmin) {
                    t.setDongianhap(Double.parseDouble(txtDonGiaNhap.getText().trim()));
                    t.setDongiaban(Double.parseDouble(txtDonGiaBan.getText().trim()));
                }
                if (thuocbus.Suathuoc(t, id) == 1) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công!");
                    loadDataTable();
                }
            } catch (Exception ex) {
            	handleException(ex); 
            }
        });

        // --- XÓA ---
        btnXoa.addActionListener(e -> {
  
            index = table.getSelectedRow(); 
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn loại thuốc cần xóa!");
                return;
            }

  
            int id = (int) table.getValueAt(index, 0);
            String tenThuoc = table.getValueAt(index, 1).toString();

      
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Bạn có chắc chắn muốn xóa thuốc: " + tenThuoc + "?", 
                    "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int ketQua = thuocbus.xoaThuoc(id);

                if (ketQua == 1) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
                    loadDataTable();
                    xoaTrang();
                } else if (ketQua == -1) {
     
                    JOptionPane.showMessageDialog(this, 
                        "Không thể xóa loại thuốc này!\nLý do: Thuốc đã được sử dụng trong các đơn thuốc hoặc hóa đơn trước đó.", 
                        "Cảnh báo ràng buộc", 
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: Không thể xóa dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnLamMoi.addActionListener(e -> xoaTrang());
        btnQuayLai.addActionListener(e -> dispose());
    }

    private void handleException(Exception ex) {
        String msg = ex.getMessage();
        if (msg != null && msg.contains("Duplicate entry")) {
  
            if (msg.contains("ten_thuoc") || msg.contains("tenthuoc")) {
                JOptionPane.showMessageDialog(this, "Lỗi: Tên thuốc này đã tồn tại trong hệ thống!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                txtTenThuoc.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Dữ liệu bị trùng lặp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ex instanceof NumberFormatException) {
            JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập đúng định dạng số cho đơn giá!");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void calculatePrice() {
        try {
            double gianhap = Double.parseDouble(txtDonGiaNhap.getText().trim());
            double heso = Double.parseDouble(txtHeSo.getText().trim());
            txtDonGiaBan.setText(String.format("%.0f", gianhap * heso));
        } catch (Exception e) {
        }
    }

    private void loadDataTable() {
        listthuoc = thuocbus.getAllTHUOC();
        DefaultTableModel m = (DefaultTableModel) table.getModel();
        m.setRowCount(0);
        String[] cols = isAdmin ? new String[]{"Mã", "Tên", "Giá nhập", "Giá bán", "Nhà SX", "HSD"} : new String[]{"Mã", "Tên", "Giá bán", "Nhà SX", "HSD"};
        m.setColumnIdentifiers(cols);
        for (Thuoc t : listthuoc) {
            if (isAdmin) {
                m.addRow(new Object[]{t.getMathuoc(), t.getTenthuoc(), df.format(t.getDongianhap()), df.format(t.getDongiaban()), t.getNhasanxuat(), t.getHansudung()});
            } else {
                m.addRow(new Object[]{t.getMathuoc(), t.getTenthuoc(), df.format(t.getDongiaban()), t.getNhasanxuat(), t.getHansudung()});
            }
        }
    }

    private void loadDataTableTIMKIEM(String id) {
        listthuoc = thuocbus.Timkiemthuoc(id);
        loadDataTable(); 
    }

    private void xoaTrang() {
        txtTenThuoc.setText("");
        txtHoatChat.setText("");
        txtHamLuong.setText("");
        txtDangThuoc.setText("");
        txtLoaiThuoc.setText("");
        txtDonViTinh.setText("");
        txtDonGiaNhap.setText("");
        txtHeSo.setText("1.2");
        txtDonGiaBan.setText("");
        txtNhaSanXuat.setText("");
        txtNuocSanXuat.setText("");
        txtGhiChu.setText("");
        dateChooserNSX.setDate(null);
        dateChooserHSD.setDate(null);
        table.clearSelection();
        index = -1;
    }
}