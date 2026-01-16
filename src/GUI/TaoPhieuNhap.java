package GUI;

import BUS.KhoThuocBus;
import BUS.NhaCungCapBus;
import BUS.ThuocBus;
import MODEL.ChiTietPhieuNhap;
import MODEL.NhaCungCap;
import MODEL.PhieuNhapThuoc;
import MODEL.Session;
import MODEL.Thuoc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaoPhieuNhap extends JDialog {
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();
    private JTable tblChiTiet;
    private DefaultTableModel modelChiTiet;
    private JTextField txtSoLuong;
    private JTextField txtDonGia;
    private JTextField txtNhanVien;
    private JComboBox<String> cboNhaCungCap;
    private JComboBox<String> cboThuoc;
    private JLabel lblTongTien;

    private JButton btnTaoPhieu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnHoanTat;

    private ThuocBus thuocbus = new ThuocBus();
    private KhoThuocBus bus = new KhoThuocBus();
    private NhaCungCapBus nccbus = new NhaCungCapBus();

    private List<NhaCungCap> ncclist = new ArrayList<>();
    private List<Thuoc> thuoclist = new ArrayList<>();


    private int maPhieuNhapHienTai = -1;
    private DecimalFormat df = new DecimalFormat("#,###");

    private Color colorPrimary = new Color(0, 128, 128);
    private Color colorSuccess = new Color(40, 167, 69);
    private Color colorWarning = new Color(255, 193, 7);
    private Color colorDanger = new Color(220, 53, 69);


    public TaoPhieuNhap(JFrame parent) {
        this(parent, -1);
    }


    /**
     * @wbp.parser.constructor
     */
    public TaoPhieuNhap(JFrame parent, int maPhieuCu) {
        super(parent, "Tạo Phiếu Nhập Kho", true);
        this.maPhieuNhapHienTai = maPhieuCu;

        setBounds(100, 100, 1000, 650);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));


        initUI();
        

        loadDataComboBoxes();
        initEvents();


        if (maPhieuNhapHienTai > 0) {
     
            setTitle("Tiếp tục nhập hàng - Phiếu số: " + maPhieuNhapHienTai);
            loadThongTinPhieuCu(maPhieuNhapHienTai);
            loadDataChiTiet(); 
            
      
            cboNhaCungCap.setEnabled(false);
            btnTaoPhieu.setEnabled(false);
            toggleInput(true);
        } else {
     
            setTitle("Tạo Phiếu Nhập Kho Mới");
            toggleInput(false); 
        }
    }

    private void initUI() {
        // 1. HEADER
        JPanel pnlHeader = new JPanel();
        pnlHeader.setPreferredSize(new Dimension(10, 90));
        pnlHeader.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, new Color(160, 160, 160)),
                "Thông tin chung",
                TitledBorder.LEADING, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14), colorPrimary));
        contentPanel.add(pnlHeader, BorderLayout.NORTH);
        pnlHeader.setLayout(null);

        JLabel lblNCC = new JLabel("Nhà cung cấp:");
        lblNCC.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNCC.setBounds(20, 30, 90, 25);
        pnlHeader.add(lblNCC);

        cboNhaCungCap = new JComboBox<>();
        cboNhaCungCap.setBounds(110, 30, 200, 25);
        pnlHeader.add(cboNhaCungCap);

        JLabel lblNV = new JLabel("Nhân viên:");
        lblNV.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNV.setBounds(340, 30, 70, 25);
        pnlHeader.add(lblNV);

        txtNhanVien = new JTextField();
        txtNhanVien.setEditable(false);
        txtNhanVien.setBounds(410, 30, 180, 25);
        pnlHeader.add(txtNhanVien);

        String nvInfo = (Session.maNhanVien != 0) ? Session.maNhanVien + " - " + Session.TenNhanVien : "Admin";
        txtNhanVien.setText(nvInfo);

        btnTaoPhieu = new JButton("Bắt đầu tạo phiếu");
        styleButton(btnTaoPhieu, colorPrimary, Color.WHITE);
        btnTaoPhieu.setBounds(620, 25, 140, 35);
        pnlHeader.add(btnTaoPhieu);


        JPanel pnlCenter = new JPanel();
        contentPanel.add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.setLayout(null);

        JPanel pnlInput = new JPanel();
        pnlInput.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, new Color(160, 160, 160)),
                "Chi tiết nhập hàng",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlInput.setBounds(10, 10, 960, 140);
        pnlCenter.add(pnlInput);
        pnlInput.setLayout(null);

        JLabel lblThuoc = new JLabel("Thuốc:");
        lblThuoc.setBounds(20, 30, 60, 25); pnlInput.add(lblThuoc);
        cboThuoc = new JComboBox<>();
        cboThuoc.setBounds(80, 30, 300, 25); pnlInput.add(cboThuoc);

        JLabel lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setBounds(400, 30, 60, 25); pnlInput.add(lblSoLuong);
        txtSoLuong = new JTextField();
        txtSoLuong.setBounds(460, 30, 100, 25); pnlInput.add(txtSoLuong);

        JLabel lblDonGia = new JLabel("Đơn giá:");
        lblDonGia.setBounds(580, 30, 60, 25); pnlInput.add(lblDonGia);
        txtDonGia = new JTextField();
        txtDonGia.setBounds(640, 30, 120, 25); pnlInput.add(txtDonGia);
        pnlInput.add(new JLabel("VNĐ")).setBounds(765, 30, 30, 25);

        btnThem = new JButton("Thêm");
        styleButton(btnThem, colorSuccess, Color.WHITE);
        btnThem.setBounds(250, 80, 100, 35); pnlInput.add(btnThem);

        btnSua = new JButton("Sửa");
        styleButton(btnSua, colorWarning, Color.BLACK);
        btnSua.setBounds(370, 80, 100, 35); pnlInput.add(btnSua);

        btnXoa = new JButton("Xóa");
        styleButton(btnXoa, colorDanger, Color.WHITE);
        btnXoa.setBounds(490, 80, 100, 35); pnlInput.add(btnXoa);

        btnLamMoi = new JButton("Làm mới");
        styleButton(btnLamMoi, Color.GRAY, Color.WHITE);
        btnLamMoi.setBounds(610, 80, 100, 35); pnlInput.add(btnLamMoi);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 160, 960, 330);
        pnlCenter.add(scrollPane);

        String[] cols = { "ID", "Mã Thuốc", "Tên Thuốc", "Số Lượng", "Đơn Giá", "Thành Tiền" };
        modelChiTiet = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblChiTiet = new JTable(modelChiTiet);
        tblChiTiet.setRowHeight(25);
        tblChiTiet.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        tblChiTiet.getColumnModel().getColumn(0).setMinWidth(0);
        tblChiTiet.getColumnModel().getColumn(0).setMaxWidth(0);
        
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.RIGHT);
        tblChiTiet.getColumnModel().getColumn(3).setCellRenderer(right);
        tblChiTiet.getColumnModel().getColumn(4).setCellRenderer(right);
        tblChiTiet.getColumnModel().getColumn(5).setCellRenderer(right);
        scrollPane.setViewportView(tblChiTiet);

   
        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(pnlFooter, BorderLayout.SOUTH);
        
        lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 16));
        lblTongTien.setForeground(Color.RED);
        pnlFooter.add(lblTongTien);
        pnlFooter.add(Box.createHorizontalStrut(20));
        
        btnHoanTat = new JButton("HOÀN TẤT NHẬP KHO");
        styleButton(btnHoanTat, colorPrimary, Color.WHITE);
        btnHoanTat.setPreferredSize(new Dimension(200, 40));
        pnlFooter.add(btnHoanTat);
    }


    private void loadThongTinPhieuCu(int maPhieu) {

        PhieuNhapThuoc pn = bus.getPhieuNhapById(maPhieu);
        if (pn != null) {
    
            int maNCC = pn.getMaNhaCungCap();
            for (int i = 0; i < cboNhaCungCap.getItemCount(); i++) {
                String item = cboNhaCungCap.getItemAt(i);
       
                if (item.startsWith(maNCC + " -")) {
                    cboNhaCungCap.setSelectedIndex(i);
                    break;
                }
            }
        
        }
    }

    private void loadDataChiTiet() {
        if (maPhieuNhapHienTai == -1) return;

        List<ChiTietPhieuNhap> listDB = bus.getChiTietPhieuNhap(maPhieuNhapHienTai);
        modelChiTiet.setRowCount(0);
        double tongTien = 0;

        for (ChiTietPhieuNhap ct : listDB) {
            String tenThuoc = "Unknown";
            for (Thuoc t : thuoclist) {
                if (t.getMathuoc() == ct.getMaThuoc()) {
                    tenThuoc = t.getTenthuoc();
                    break;
                }
            }
            modelChiTiet.addRow(new Object[]{
                ct.getId(), ct.getMaThuoc(), tenThuoc, ct.getSoLuongNhap(),
                df.format(ct.getDonGiaNhap()), df.format(ct.getThanhTien())
            });
            tongTien += ct.getThanhTien();
        }
        lblTongTien.setText("Tổng tiền: " + df.format(tongTien) + " VNĐ");
    }

    private void xuLyTaoPhieu() {
        String selectedNCC = (String) cboNhaCungCap.getSelectedItem();
        if (selectedNCC == null || selectedNCC.startsWith("--")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Nhà Cung Cấp!");
            return;
        }
        try {
            String[] parts = selectedNCC.split(" - ");
            int maNCC = Integer.parseInt(parts[0].trim());

            PhieuNhapThuoc pn = new PhieuNhapThuoc();
            pn.setMaNhanVienNhap(Session.maNhanVien != 0 ? Session.maNhanVien : 1);
            pn.setMaNhaCungCap(maNCC);

            maPhieuNhapHienTai = bus.taoPhieuNhap(pn);
            if (maPhieuNhapHienTai > 0) {
                JOptionPane.showMessageDialog(this, "Đã tạo phiếu số: " + maPhieuNhapHienTai);
                cboNhaCungCap.setEnabled(false);
                btnTaoPhieu.setEnabled(false);
                toggleInput(true);
                loadDataChiTiet();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi tạo phiếu!");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void xuLyThemChiTiet() {
        ChiTietPhieuNhap ct = getChiTietFromInput();
        if (ct == null) return;

  
        for (int i = 0; i < modelChiTiet.getRowCount(); i++) {
            int maThuocTable = Integer.parseInt(modelChiTiet.getValueAt(i, 1).toString());
            if (maThuocTable == ct.getMaThuoc()) {
                JOptionPane.showMessageDialog(this, "Thuốc này đã có trong phiếu. Hãy chọn Sửa!");
                return;
            }
        }

        if (bus.themChiTiet(ct)) {
            loadDataChiTiet();
            clearInput();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
    }

    private void xuLySuaChiTiet() {
        int row = tblChiTiet.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa!"); return;
        }
        ChiTietPhieuNhap ctMoi = getChiTietFromInput();
        if (ctMoi == null) return;

     
        //int idChiTiet = Integer.parseInt(tblChiTiet.getValueAt(row, 0).toString());
        
  
        if (bus.xoactphieunhap(maPhieuNhapHienTai) > 0) {
            if (bus.themChiTiet(ctMoi)) {
                JOptionPane.showMessageDialog(this, "Đã cập nhật!");
                loadDataChiTiet();
                clearInput();
            }
        }
    }

    private void xuLyXoaChiTiet() {
        int row = tblChiTiet.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!"); return;
        }
        //int idChiTiet = Integer.parseInt(tblChiTiet.getValueAt(row, 0).toString());
        if (JOptionPane.showConfirmDialog(this, "Xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (bus.xoactphieunhap(maPhieuNhapHienTai) > 0) {
                loadDataChiTiet();
                clearInput();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi xóa!");
            }
        }
    }

    private void xuLyHoanTat() {
        if (tblChiTiet.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Phiếu rỗng!"); return;
        }
        if (JOptionPane.showConfirmDialog(this, "Hoàn tất nhập kho? Kho thuốc sẽ được cập nhật.", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (bus.hoanTatNhapKho(maPhieuNhapHienTai)) {
                JOptionPane.showMessageDialog(this, "Nhập kho thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi hoàn tất!");
            }
        }
    }

    // --- HÀM PHỤ TRỢ ---
    private void loadDataComboBoxes() {
        ncclist = nccbus.getAllNCC();
        DefaultComboBoxModel<String> modelNCC = new DefaultComboBoxModel<>();
        modelNCC.addElement("-- Chọn Nhà Cung Cấp --");
        for (NhaCungCap n : ncclist) modelNCC.addElement(n.getMaNhaCungCap() + " - " + n.getTenNhaCungCap());
        cboNhaCungCap.setModel(modelNCC);

        thuoclist = thuocbus.getAllTHUOC();
        DefaultComboBoxModel<String> modelThuoc = new DefaultComboBoxModel<>();
        modelThuoc.addElement("-- Chọn Thuốc --");
        for (Thuoc t : thuoclist) modelThuoc.addElement(t.getMathuoc() + "." + t.getTenthuoc());
        cboThuoc.setModel(modelThuoc);
    }

    private void toggleInput(boolean enable) {
        cboThuoc.setEnabled(enable);
        txtSoLuong.setEnabled(enable);
        txtDonGia.setEnabled(enable);
        btnThem.setEnabled(enable);
        btnSua.setEnabled(enable);
        btnXoa.setEnabled(enable);
        btnLamMoi.setEnabled(enable);
        btnHoanTat.setEnabled(enable);
    }

    private void clearInput() {
        cboThuoc.setSelectedIndex(0);
        txtSoLuong.setText("");
        txtDonGia.setText("");
        tblChiTiet.clearSelection();
    }

    private ChiTietPhieuNhap getChiTietFromInput() {
        if (maPhieuNhapHienTai == -1) return null;
        try {
            String selThuoc = (String) cboThuoc.getSelectedItem();
            if (selThuoc == null || selThuoc.startsWith("--")) throw new Exception();
            int maThuoc = Integer.parseInt(selThuoc.split("\\.")[0].trim());
            int sl = Integer.parseInt(txtSoLuong.getText());
            long dg = Long.parseLong(txtDonGia.getText());
            if (sl <= 0 || dg <= 0) throw new Exception();

            ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
            ct.setMaPhieuNhapThuoc(maPhieuNhapHienTai);
            ct.setMaThuoc(maThuoc);
            ct.setSoLuongNhap(sl);
            ct.setDonGiaNhap((long) dg);
            ct.setThanhTien((long) (sl * dg));
            return ct;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!");
            return null;
        }
    }

    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initEvents() {
        btnTaoPhieu.addActionListener(e -> xuLyTaoPhieu());
        btnThem.addActionListener(e -> xuLyThemChiTiet());
        btnSua.addActionListener(e -> xuLySuaChiTiet());
        btnXoa.addActionListener(e -> xuLyXoaChiTiet());
        btnLamMoi.addActionListener(e -> clearInput());
        btnHoanTat.addActionListener(e -> xuLyHoanTat());

        tblChiTiet.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tblChiTiet.getSelectedRow();
                if (row != -1) {
                    String maT = tblChiTiet.getValueAt(row, 1).toString();
                    String tenT = tblChiTiet.getValueAt(row, 2).toString();
                    cboThuoc.setSelectedItem(maT + "." + tenT);
                    txtSoLuong.setText(tblChiTiet.getValueAt(row, 3).toString());
                    txtDonGia.setText(tblChiTiet.getValueAt(row, 4).toString().replace(",", "").replace(".", ""));
                }
            }
        });
    }
}