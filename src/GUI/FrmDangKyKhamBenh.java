package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

import BUS.BenhNhanBus;
import BUS.ChiDinhBus;
import BUS.DangKyKhamBenhBus;
import BUS.ChuyenKhoaBus;
import BUS.DichVuBus; 
import BUS.PhieuKhamBus; 
import BUS.BangPhanCongCaLamBus;

import MODEL.BenhNhan;
import MODEL.ChiTietChiDinh; 
import MODEL.ChuyenKhoa;
import MODEL.DangKyKhamBenh;
import MODEL.DichVu; 
import MODEL.PhieuKham; 
import MODEL.Session;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// iText PDF
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class FrmDangKyKhamBenh extends JFrame {

    private static final long serialVersionUID = 1L;


    private JPanel contentPane;
    private JLabel lblTitleMain, lblSearch, lblChuyenKhoa, lblBacSi, lblGioiHan, lblSTT, lblGhiChu, lblDachSachCho;
    private JTextField txtTimKiem, txtBacSiTruc, txtGioiHanBN, txtSoThuTu;
    private JTextArea txtGhiChu;
    private JComboBox<String> cmbChuyenKhoa;
    private JTable tableBN, tableDK;
    private JScrollPane scrollBN, scrollDK, scrollGC;
    private JButton btnThemBN, btnTaiKham, btnHenKham, btnDangKy, btnThoat, btnInPhieu, btnHuy;


    private BenhNhanBus benhNhanBus = new BenhNhanBus();
    private ChuyenKhoaBus chuyenKhoaBus = new ChuyenKhoaBus();
    private DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
    private DichVuBus dvBus = new DichVuBus(); 
    private ChiDinhBus chiDinhBus = new ChiDinhBus(); 
    private BangPhanCongCaLamBus phanCongBus = new BangPhanCongCaLamBus();

    private List<ChuyenKhoa> listck = new ArrayList<>();
    private List<DichVu> listdv = new ArrayList<>(); 
    private DichVu dichVuDangChon = null; 
    private int maBenhNhanDangChon = -1;

    private final Color PRIMARY_COLOR = new Color(0, 123, 255); 
    private final Color BG_COLOR = new Color(245, 248, 250);    
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new FrmDangKyKhamBenh().setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public FrmDangKyKhamBenh() {
        initUI();
        initData();
        initEvents();
    }

    private void initUI() {
        setTitle("Hệ Thống Đăng Ký Khám Bệnh");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1280, 780);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(null); 
        setContentPane(contentPane);

        lblTitleMain = new JLabel("QUẦY ĐĂNG KÝ KHÁM BỆNH", SwingConstants.CENTER);
        lblTitleMain.setOpaque(true);
        lblTitleMain.setBackground(PRIMARY_COLOR);
        lblTitleMain.setForeground(Color.WHITE);
        lblTitleMain.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitleMain.setBounds(0, 0, 1264, 60);
        contentPane.add(lblTitleMain);


        JLabel lblGroupLeft = new JLabel(" 1. CHỌN BỆNH NHÂN");
        lblGroupLeft.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblGroupLeft.setBounds(20, 75, 600, 25);
        contentPane.add(lblGroupLeft);

        lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(MAIN_FONT);
        lblSearch.setBounds(20, 110, 70, 30);
        contentPane.add(lblSearch);

        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(90, 110, 200, 30);
        contentPane.add(txtTimKiem);

        btnThemBN = new RoundedButton("Thêm BN", new Color(40, 167, 69));
        btnThemBN.setBounds(300, 110, 95, 30);
        contentPane.add(btnThemBN);

        btnTaiKham = new RoundedButton("DS Tái Khám", new Color(23, 162, 184));
        btnTaiKham.setBounds(405, 110, 110, 30);
        contentPane.add(btnTaiKham);

        btnHenKham = new RoundedButton("Xem lịch BS", new Color(255, 193, 7));
        btnHenKham.setForeground(Color.BLACK);
        btnHenKham.setBounds(525, 110, 100, 30);
        contentPane.add(btnHenKham);

        tableBN = new JTable();
        setupTable(tableBN, new String[]{"Mã BN", "Họ tên", "Giới tính", "Ngày sinh", "SĐT", "CCCD"});
        scrollBN = new JScrollPane(tableBN);
        scrollBN.setBounds(20, 150, 605, 270);
        contentPane.add(scrollBN);


        JLabel lblGroupRight = new JLabel(" 2. THÔNG TIN ĐĂNG KÝ KHÁM");
        lblGroupRight.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblGroupRight.setBounds(650, 75, 590, 25);
        contentPane.add(lblGroupRight);

        lblChuyenKhoa = new JLabel("Dịch vụ khám:");
        lblChuyenKhoa.setBounds(650, 110, 120, 30);
        contentPane.add(lblChuyenKhoa);

        cmbChuyenKhoa = new JComboBox<>();
        cmbChuyenKhoa.setEditable(true);
        cmbChuyenKhoa.setBounds(770, 110, 470, 30);
        contentPane.add(cmbChuyenKhoa);

        lblBacSi = new JLabel("BS phụ trách:");
        lblBacSi.setBounds(650, 155, 120, 30);
        contentPane.add(lblBacSi);

        txtBacSiTruc = new JTextField();
        txtBacSiTruc.setEditable(false);
        txtBacSiTruc.setBackground(new Color(240, 240, 240));
        txtBacSiTruc.setBounds(770, 155, 470, 30);
        contentPane.add(txtBacSiTruc);

        lblGioiHan = new JLabel("Số lượng tối đa:");
        lblGioiHan.setBounds(650, 200, 120, 30);
        contentPane.add(lblGioiHan);

        txtGioiHanBN = new JTextField();
        txtGioiHanBN.setEditable(false);
        txtGioiHanBN.setBounds(770, 200, 150, 30);
        contentPane.add(txtGioiHanBN);

        lblSTT = new JLabel("Số thứ tự cấp:");
        lblSTT.setBounds(940, 200, 100, 30);
        contentPane.add(lblSTT);

        txtSoThuTu = new JTextField();
        txtSoThuTu.setEditable(false);
        txtSoThuTu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtSoThuTu.setForeground(Color.RED);
        txtSoThuTu.setHorizontalAlignment(JTextField.CENTER);
        txtSoThuTu.setBounds(1040, 200, 200, 30);
        contentPane.add(txtSoThuTu);

        lblGhiChu = new JLabel("Ghi chú / Triệu chứng:");
        lblGhiChu.setBounds(650, 245, 150, 30);
        contentPane.add(lblGhiChu);

        txtGhiChu = new JTextArea();
        txtGhiChu.setLineWrap(true);
        scrollGC = new JScrollPane(txtGhiChu);
        scrollGC.setBounds(650, 275, 590, 80);
        contentPane.add(scrollGC);

        btnDangKy = new RoundedButton("XÁC NHẬN ĐĂNG KÝ", PRIMARY_COLOR);
        btnDangKy.setBounds(650, 375, 300, 45);
        contentPane.add(btnDangKy);

        btnThoat = new RoundedButton("Thoát", new Color(108, 117, 125));
        btnThoat.setBounds(960, 375, 280, 45);
        contentPane.add(btnThoat);


        lblDachSachCho = new JLabel("DANH SÁCH CHỜ KHÁM HÔM NAY");
        lblDachSachCho.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDachSachCho.setForeground(PRIMARY_COLOR);
        lblDachSachCho.setBounds(20, 440, 400, 30);
        contentPane.add(lblDachSachCho);

        btnInPhieu = new RoundedButton("In Phiếu", new Color(255, 193, 7));
        btnInPhieu.setForeground(Color.BLACK);
        btnInPhieu.setBounds(1020, 440, 100, 30);
        contentPane.add(btnInPhieu);

        btnHuy = new RoundedButton("Hủy Đăng Ký", new Color(220, 53, 69));
        btnHuy.setBounds(1130, 440, 110, 30);
        contentPane.add(btnHuy);

        tableDK = new JTable();
        setupTable(tableDK, new String[]{"ID", "Bệnh nhân", "Chuyên khoa", "Tên dịch vụ", "STT", "Thời gian", "Trạng thái", "Ghi chú"});
        scrollDK = new JScrollPane(tableDK);
        scrollDK.setBounds(20, 480, 1220, 240);
        contentPane.add(scrollDK);
    }

    private void initData() {
        listdv = dvBus.getDichVu();
        listck = chuyenKhoaBus.getAllCK();
        setupAutoCompleteDB(cmbChuyenKhoa);
        loadTableBN("");
        loadTableDangKyHomNay();
    }

    private void initEvents() {
        // Tìm kiếm
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent e) { loadTableBN(txtTimKiem.getText()); }
        });

        // Click Table BN
        tableBN.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int row = tableBN.getSelectedRow();
                if (row != -1) maBenhNhanDangChon = (int) tableBN.getValueAt(row, 0);
            }
        });

        // Thêm BN
        btnThemBN.addActionListener(e -> {
            new FrmQlbenhnhancuaLetan().setVisible(true);
            loadTableBN("");
        });

        // Tái khám
        btnTaiKham.addActionListener(e -> new FrmTaiKham(this).setVisible(true));

        // Lịch BS
        btnHenKham.addActionListener(e -> new FrmXemLichBacSi().setVisible(true));


        btnDangKy.addActionListener(e -> {
            if (maBenhNhanDangChon == -1) { JOptionPane.showMessageDialog(null, "Chọn bệnh nhân!"); return; }
            String tenDVText = (String) cmbChuyenKhoa.getEditor().getItem();
            dichVuDangChon = null;
            for(DichVu dv : listdv) { if(dv.getTenDichVu().equals(tenDVText)) { dichVuDangChon = dv; break; } }
            if (dichVuDangChon == null) { JOptionPane.showMessageDialog(null, "Dịch vụ không hợp lệ!"); return; }

            int stt = Integer.parseInt(txtSoThuTu.getText());
            int maCK = dichVuDangChon.getMachuyenkhoa();
            DangKyKhamBenh dk = new DangKyKhamBenh(maBenhNhanDangChon, Session.maNhanVien, maCK, stt, LocalDateTime.now(), DangKyKhamBenh.TRANGTHAI.CHO_KHAM, txtGhiChu.getText().trim());
            
            int idDangKy = dkBus.themDangKy(dk);
            if (idDangKy > 0) {
                if (maCK == 7 || maCK == 12) {
                    try {
                        PhieuKhamBus pkBus = new PhieuKhamBus();
                        int maPK = pkBus.TaoPhieuKham(maBenhNhanDangChon, Session.maNhanVien, String.valueOf(maCK));
                        int maPCD = chiDinhBus.taoPhieuChiDinh(maPK, Session.maNhanVien);
                        ChiTietChiDinh ct = new ChiTietChiDinh();
                        ct.setMaPhieuChiDinh(maPCD); ct.setMaDichVu(dichVuDangChon.getMaDichVu());
                        ct.setDonGia(dichVuDangChon.getDonGia()); ct.setSoLuong(1); ct.setTrangThaiDV("CHUA_THUC_HIEN");
                        int idChiTiet = chiDinhBus.themChiTietDichVu(ct, Session.maNhanVien);
                        if (idChiTiet > 0) {
                            chiDinhBus.capNhatTongTienPhieuChiDinh(maPCD);
                            pkBus.lienKetPhieuKhamVaoDangKy(idDangKy, maPK);
                            chiDinhBus.lienKetChiTietDichVuVaoDangKy(idDangKy, idChiTiet);
                        }
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
                JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                loadTableDangKyHomNay();
                if(JOptionPane.showConfirmDialog(null, "In phiếu?", "In", 0) == 0) 
                    xuatPhieuDangKyPDF(idDangKy, "", tenDVText, stt, txtBacSiTruc.getText());
            }
        });

        // In phiếu danh sách chờ
        btnInPhieu.addActionListener(e -> {
            int row = tableDK.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(null, "Chọn dòng để in!"); return; }
            xuatPhieuDangKyPDF((int)tableDK.getValueAt(row, 0), tableDK.getValueAt(row, 1).toString(), tableDK.getValueAt(row, 3).toString(), Integer.parseInt(tableDK.getValueAt(row, 4).toString()), txtBacSiTruc.getText());
        });

        // Hủy đăng ký
        btnHuy.addActionListener(e -> {
            int row = tableDK.getSelectedRow();
            if (row != -1) {
                if(JOptionPane.showConfirmDialog(null, "Hủy?", "Xác nhận", 0) == 0) {
                    dkBus.capNhatTrangThaiHUY((int)tableDK.getValueAt(row, 0));
                    loadTableDangKyHomNay();
                }
            }
        });

        btnThoat.addActionListener(e -> dispose());
    }

    private void setupAutoCompleteDB(final JComboBox<String> comboBox) {
        final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        comboBox.setModel(model);
        final JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        
        editor.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ENTER) return;
                SwingUtilities.invokeLater(() -> {
                    String text = editor.getText();
                    if (text.isEmpty()) { comboBox.hidePopup(); return; }
                    List<String> res = new ArrayList<>();
                    for(DichVu dv : listdv) { if(dv.getTenDichVu().toLowerCase().contains(text.toLowerCase())) res.add(dv.getTenDichVu()); }
                    if (!res.isEmpty()) {
                        model.removeAllElements();
                        for (String s : res) model.addElement(s);
                        comboBox.showPopup(); editor.setText(text);
                    } else comboBox.hidePopup();
                });
            }
        });

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            if (selected != null) {
                for (DichVu dv : listdv) {
                    if (dv.getTenDichVu().equals(selected)) {
                        listck = chuyenKhoaBus.getAllCK();
                        String tenCK = "";
                        for(ChuyenKhoa ck : listck) { if(ck.getMa_chuyen_khoa() == dv.getMachuyenkhoa()) { tenCK = ck.getTen_chuyen_khoa(); break; } }
                        
                        int stt = dkBus.laySoThuTuTiepTheo(dv.getMachuyenkhoa());
                        int soCa = dkBus.demSoCaThucTeChuaHuy(dv.getMachuyenkhoa());
                        int gioiHan = phanCongBus.layGioiHanBNHomNay(tenCK);
                        String tenBS = phanCongBus.layTenBacSiTrucHomNay(tenCK);

                        txtSoThuTu.setText(String.valueOf(stt));
                        txtBacSiTruc.setText(tenBS != null ? tenBS : "Chưa phân công");
                        txtGioiHanBN.setText(gioiHan > 0 ? String.valueOf(gioiHan) : "0");

                        if (gioiHan > 0 && soCa >= gioiHan) {
                            txtGioiHanBN.setBackground(Color.RED); txtGioiHanBN.setForeground(Color.WHITE);
                        } else {
                            txtGioiHanBN.setBackground(new Color(245, 245, 245)); txtGioiHanBN.setForeground(Color.BLACK);
                        }

                        if (tenBS == null || tenBS.equals("Chưa có bác sĩ trực") || (gioiHan > 0 && soCa >= gioiHan)) 
                            btnDangKy.setEnabled(false); else btnDangKy.setEnabled(true);
                        break;
                    }
                }
            }
        });
    }

    private void loadTableDangKyHomNay() {
        List<DangKyKhamBenh> listDK = dkBus.getAllToday();
        List<BenhNhan> listBN = benhNhanBus.getAllBN();
        List<ChuyenKhoa> listCK = chuyenKhoaBus.getAllCK();
        DefaultTableModel model = (DefaultTableModel) tableDK.getModel();
        model.setRowCount(0);
        for (DangKyKhamBenh dk : listDK) {
            String tenBN = "Không rõ", tenCK = "Không rõ";
            for (BenhNhan bn : listBN) if (bn.getMaBenhNhan() == dk.getMabenhnhan()) { tenBN = bn.getHoTen(); break; }
            for (ChuyenKhoa ck : listCK) if (ck.getMa_chuyen_khoa() == dk.getMachuyenkhoa()) { tenCK = ck.getTen_chuyen_khoa(); break; }
            String tenDV = new ChiDinhBus().getTenDichVuThucTe(dk.getId());
            if (tenDV == null || tenDV.isEmpty()) tenDV = tenCK;
            model.addRow(new Object[]{ dk.getId(), tenBN, tenCK, tenDV, dk.getSothutu(), dk.getThoiGianHienThi(), dk.getTrangThaiTiengViet(), dk.getGhichu() });
        }
    }

    private void loadTableBN(String keyword) {
        List<BenhNhan> list = (keyword == null || keyword.isEmpty()) ? benhNhanBus.getAllBN() : benhNhanBus.TimkiemBN(keyword);
        DefaultTableModel model = (DefaultTableModel) tableBN.getModel();
        model.setRowCount(0);
        for (BenhNhan bn : list) model.addRow(new Object[]{ bn.getMaBenhNhan(), bn.getHoTen(), bn.GioiTinh(), bn.getNgaySinh(), bn.getSDT(), bn.getCCCD() });
    }

    private void xuatPhieuDangKyPDF(int id, String tenBN, String tenDV, int stt, String tenbs) {
        JFileChooser fc = new JFileChooser(); fc.setFileSelectionMode(1);
        if (fc.showSaveDialog(this) == 0) {
            String path = fc.getSelectedFile().getAbsolutePath() + File.separator + "Phieu_" + id + ".pdf";
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(path));
                doc.open();
                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, true);
                com.itextpdf.text.Font fT = new com.itextpdf.text.Font(bf, 22, 1);
                com.itextpdf.text.Font fN = new com.itextpdf.text.Font(bf, 12, 0);
                Paragraph p1 = new Paragraph("PHÒNG KHÁM ĐA KHOA", fN); p1.setAlignment(1); doc.add(p1);
                Paragraph p2 = new Paragraph("PHIẾU SỐ THỨ TỰ", fT); p2.setAlignment(1); doc.add(p2);
                Paragraph p3 = new Paragraph(String.format("%03d", stt), new com.itextpdf.text.Font(bf, 45, 1)); p3.setAlignment(1); doc.add(p3);
                doc.add(new Paragraph("\nMã ĐK: " + id + "\nBệnh nhân: " + tenBN + "\nDịch vụ: " + tenDV + "\nBác sĩ: " + tenbs + "\nThời gian: " + LocalDateTime.now(), fN));
                doc.close(); Desktop.getDesktop().open(new File(path));
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
    
    public void setThongTinDangKy(int maBenhNhan, String tenBenhNhan, String tenChuyenKhoa) {
        this.maBenhNhanDangChon = maBenhNhan;
        txtTimKiem.setText(maBenhNhan + "");
        loadTableBN(maBenhNhan + "");
        if (tableBN.getRowCount() > 0) tableBN.setRowSelectionInterval(0, 0);
        cmbChuyenKhoa.getEditor().setItem(tenChuyenKhoa != null ? tenChuyenKhoa : "");
        txtGhiChu.setText("Bệnh nhân tái khám theo lịch hẹn.");
    }

    private void setupTable(JTable t, String[] cols) {
        t.setModel(new DefaultTableModel(new Object[][]{}, cols) { public boolean isCellEditable(int r, int c) { return false; } });
        t.setRowHeight(25); t.setSelectionBackground(new Color(220, 240, 255));
        JTableHeader h = t.getTableHeader(); h.setFont(new Font("Segoe UI", Font.BOLD, 13));
    }

    private TitledBorder createTitledBorder(String title) {
        TitledBorder b = BorderFactory.createTitledBorder(new LineBorder(new Color(200, 200, 200)), title);
        b.setTitleFont(new Font("Segoe UI", Font.BOLD, 14)); b.setTitleColor(PRIMARY_COLOR);
        return b;
    }

    class RoundedButton extends JButton {
        private Color bg;
        public RoundedButton(String t, Color c) {
            super(t); this.bg = c; setContentAreaFilled(false); setFocusPainted(false); setBorderPainted(false); setForeground(Color.WHITE); setFont(new Font("Segoe UI", Font.BOLD, 13)); setCursor(new Cursor(12));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? bg.darker() : bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.dispose(); super.paintComponent(g);
        }
    }
}