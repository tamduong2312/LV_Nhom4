package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.sql.Date;
import java.io.File;
import java.awt.Desktop;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.filechooser.FileNameExtensionFilter;

// --- IMPORT LOGIC ---
import BUS.KhoThuocBus;
import BUS.ThuocBus;
import BUS.ToaThuocBus;
import MODEL.CTToaThuoc;
import MODEL.Session;
import MODEL.Thuoc;
import MODEL.ToaThuoc;

public class ToaThuoc1 extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtSearch;
    private JTextField txtDonGia;
    private JTextField txtSang;
    private JTextField txtTrua;
    private JTextField txtChieu;
    private JTextField txtToi;
    private JTextField txtLoiDan;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private JPopupMenu popupMenu;
    private JLabel lblTenThuocChon;
    private JLabel lblTongTienVal;
    private JLabel lblThongBao;
    private JSpinner spnSoNgay;
    private JComboBox<String> cboThoiDiem;

    private JButton btnTaoToaRef;
    private JButton btnThemRef;
    private JButton btnCapNhatRef;
    private JButton btnXoaRef;
    private JButton btnLamMoiRef;
    private JButton btnLichSuToa;
    private JButton btnIn;


    private ThuocBus thuocBus = new ThuocBus();
    private ToaThuocBus toaBus = new ToaThuocBus();
    private KhoThuocBus khoBus = new KhoThuocBus();
    private int maToaHienTai = 0;
    private Thuoc thuocDangChon = null;
    private int idChiTietDangSua = 0;
    private int mapk;
    private String checklichsu;
    private List<CTToaThuoc> currentListThuoc = new ArrayList<>();

    public ToaThuoc1(int mapk, String checklichsu) {
        this.mapk = mapk;
        this.checklichsu = checklichsu;

  
        setLayout(null);
        setBackground(new Color(245, 248, 250));
        setSize(900, 650);

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 13);

   
        btnTaoToaRef = new JButton("Tạo toa mới");
        btnTaoToaRef.setBounds(20, 20, 140, 35);
        btnTaoToaRef.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTaoToaRef.setBackground(new Color(0, 123, 255));
        btnTaoToaRef.setForeground(Color.WHITE);
        btnTaoToaRef.setOpaque(true);
        btnTaoToaRef.setBorderPainted(false);
        add(btnTaoToaRef);

        txtSearch = new JTextField();
        txtSearch.setBounds(180, 20, 700, 35);
        txtSearch.setFont(fontText);
        txtSearch.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(200, 200, 200)), "Tìm kiếm thuốc..."));
        add(txtSearch);

        lblThongBao = new JLabel("");
        lblThongBao.setForeground(Color.RED);
        lblThongBao.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblThongBao.setBounds(180, 55, 600, 20);
        add(lblThongBao);

        JLabel lbl1 = new JLabel("Thuốc đang chọn:");
        lbl1.setBounds(20, 75, 120, 25);
        lbl1.setFont(fontLabel);
        add(lbl1);

        lblTenThuocChon = new JLabel("CHƯA CHỌN THUỐC");
        lblTenThuocChon.setBounds(150, 75, 400, 25);
        lblTenThuocChon.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenThuocChon.setForeground(new Color(220, 53, 69));
        add(lblTenThuocChon);

        JLabel lbl2 = new JLabel("Đơn giá (VNĐ):");
        lbl2.setBounds(630, 75, 100, 25);
        lbl2.setFont(fontLabel);
        add(lbl2);

        txtDonGia = new JTextField();
        txtDonGia.setBounds(730, 70, 150, 30);
        txtDonGia.setEditable(false);
        txtDonGia.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDonGia.setBackground(Color.WHITE);
        add(txtDonGia);


        JLabel lblS = new JLabel("Sáng"); lblS.setBounds(20, 120, 40, 20); lblS.setFont(fontLabel); add(lblS);
        txtSang = new JTextField(); txtSang.setBounds(60, 115, 60, 30); txtSang.setHorizontalAlignment(SwingConstants.CENTER); add(txtSang);

        JLabel lblTr = new JLabel("Trưa"); lblTr.setBounds(140, 120, 40, 20); lblTr.setFont(fontLabel); add(lblTr);
        txtTrua = new JTextField(); txtTrua.setBounds(180, 115, 60, 30); txtTrua.setHorizontalAlignment(SwingConstants.CENTER); add(txtTrua);

        JLabel lblC = new JLabel("Chiều"); lblC.setBounds(260, 120, 40, 20); lblC.setFont(fontLabel); add(lblC);
        txtChieu = new JTextField(); txtChieu.setBounds(300, 115, 60, 30); txtChieu.setHorizontalAlignment(SwingConstants.CENTER); add(txtChieu);

        JLabel lblT = new JLabel("Tối"); lblT.setBounds(380, 120, 40, 20); lblT.setFont(fontLabel); add(lblT);
        txtToi = new JTextField(); txtToi.setBounds(410, 115, 60, 30); txtToi.setHorizontalAlignment(SwingConstants.CENTER); add(txtToi);

        JLabel lblN = new JLabel("Số ngày:"); lblN.setBounds(500, 120, 60, 20); lblN.setFont(fontLabel); add(lblN);
        spnSoNgay = new JSpinner(new SpinnerNumberModel(5, 1, 100, 1));
        spnSoNgay.setBounds(560, 115, 60, 30);
        add(spnSoNgay);


        JLabel lblTd = new JLabel("Thời điểm:"); lblTd.setBounds(20, 175, 80, 20); lblTd.setFont(fontLabel); add(lblTd);
        cboThoiDiem = new JComboBox<>(new String[]{"Sau khi ăn", "Trước khi ăn", "Trong bữa ăn", "Trước khi ngủ", "Khi đau/sốt"});
        cboThoiDiem.setEditable(true);
        cboThoiDiem.setBounds(100, 170, 120, 30);
        cboThoiDiem.setBackground(Color.WHITE);
        add(cboThoiDiem);

        JLabel lblLd = new JLabel("Lời dặn:"); lblLd.setBounds(240, 175, 60, 20); lblLd.setFont(fontLabel); add(lblLd);
        txtLoiDan = new JTextField();
        txtLoiDan.setBounds(300, 170, 580, 30);
        add(txtLoiDan);


        btnLichSuToa = new JButton("Lịch sử toa thuốc");
        btnLichSuToa.setBounds(279, 220, 140, 35);
        btnLichSuToa.setBackground(new Color(102, 102, 102));
        btnLichSuToa.setForeground(Color.WHITE);
        btnLichSuToa.setFont(fontLabel);
        btnLichSuToa.setOpaque(true);
        btnLichSuToa.setBorderPainted(false);
        add(btnLichSuToa);

        btnThemRef = new JButton("Thêm thuốc");
        btnThemRef.setBounds(430, 220, 110, 35);
        btnThemRef.setBackground(new Color(40, 167, 69));
        btnThemRef.setForeground(Color.WHITE);
        btnThemRef.setFont(fontLabel);
        btnThemRef.setOpaque(true);
        btnThemRef.setBorderPainted(false);
        add(btnThemRef);

        btnCapNhatRef = new JButton("Cập nhật");
        btnCapNhatRef.setBounds(545, 220, 110, 35);
        btnCapNhatRef.setBackground(new Color(23, 162, 184));
        btnCapNhatRef.setForeground(Color.WHITE);
        btnCapNhatRef.setFont(fontLabel);
        btnCapNhatRef.setOpaque(true);
        btnCapNhatRef.setBorderPainted(false);
        add(btnCapNhatRef);

        btnXoaRef = new JButton("Xóa dòng");
        btnXoaRef.setBounds(660, 220, 110, 35);
        btnXoaRef.setBackground(new Color(220, 53, 69));
        btnXoaRef.setForeground(Color.WHITE);
        btnXoaRef.setFont(fontLabel);
        btnXoaRef.setOpaque(true);
        btnXoaRef.setBorderPainted(false);
        add(btnXoaRef);

        btnLamMoiRef = new JButton("Làm mới");
        btnLamMoiRef.setBounds(775, 220, 110, 35);
        btnLamMoiRef.setBackground(new Color(108, 117, 125));
        btnLamMoiRef.setForeground(Color.WHITE);
        btnLamMoiRef.setFont(fontLabel);
        btnLamMoiRef.setOpaque(true);
        btnLamMoiRef.setBorderPainted(false);
        add(btnLamMoiRef);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 280, 860, 270);
        add(scrollPane);

        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Tên Thuốc", "Sáng", "Trưa", "Chiều", "Tối", "Số Ngày", "ĐV Tính", "Cách Dùng", "Thời điểm dùng", "Thành Tiền" }
        );
        table.setModel(tableModel);
        table.setRowHeight(30);
   
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        scrollPane.setViewportView(table);

        JLabel lblTien = new JLabel("TỔNG TIỀN TOA:");
        lblTien.setBounds(550, 570, 120, 30);
        lblTien.setFont(fontLabel);
        add(lblTien);

        lblTongTienVal = new JLabel("0 VNĐ");
        lblTongTienVal.setBounds(670, 570, 200, 30);
        lblTongTienVal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTienVal.setForeground(new Color(220, 53, 69));
        add(lblTongTienVal);

        btnIn = new JButton("In đơn thuốc");
        btnIn.setBounds(20, 570, 150, 40);
        btnIn.setBackground(new Color(255, 193, 7));
        btnIn.setForeground(Color.BLACK);
        btnIn.setFont(fontLabel);
        btnIn.setOpaque(true);
        btnIn.setBorderPainted(false);
        add(btnIn);


        this.popupMenu = new JPopupMenu();
        initLogicEvents();

   
        if (!java.beans.Beans.isDesignTime()) {
            loadDataCu();
        }

        if ("check".equals(checklichsu)) {
            setFormReadOnly(this);
        }
    }

    // =================================================================
    // LOGIC XỬ LÝ
    // =================================================================

    private void initLogicEvents() {

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ENTER) return;
                String text = txtSearch.getText().trim();
                popupMenu.setVisible(false);
                popupMenu.removeAll();
                if (!text.isEmpty()) {
                    List<String> list = thuocBus.getThuocByTenthuoc(text);
                    if (list != null && !list.isEmpty()) {
                        for (String s : list) {
                            JMenuItem item = new JMenuItem(s);
                            item.addActionListener(evt -> {
                                txtSearch.setText(s);
                                try {
                                    int id = Integer.parseInt(s.split("\\.", 2)[0]);
                                    Thuoc t = thuocBus.getThuocById(id);
                                    if (t != null) selectThuoc(t);
                                } catch (Exception ex) { ex.printStackTrace(); }
                            });
                            popupMenu.add(item);
                        }
                        popupMenu.show(txtSearch, 0, txtSearch.getHeight());
                        txtSearch.requestFocus();
                    }
                }
            }
        });


        btnTaoToaRef.addActionListener(e -> {
            if (this.mapk <= 0) {
                JOptionPane.showMessageDialog(this, "Chưa xác định phiếu khám!"); return;
            }
            int idToa = toaBus.taoToaThuoc(this.mapk, "");
            if (idToa > 0) {
                maToaHienTai = idToa;
                btnTaoToaRef.setText("Mã toa: " + idToa);
                btnTaoToaRef.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Đã tạo toa thuốc số: " + idToa);
            }
        });

        // Thêm thuốc
        btnThemRef.addActionListener(e -> {
     
            if (thuocDangChon == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng tìm và chọn một loại thuốc trước khi thêm!");
                return;
            }
            if (maToaHienTai == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng bấm 'Tạo toa mới' trước!");
                return;
            }
            if (!validateInputs()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số lượng uống!");
                return;
            }

            int s = parse(txtSang.getText());
            int tr = parse(txtTrua.getText());
            int c = parse(txtChieu.getText());
            int t = parse(txtToi.getText());
            int n = (int) spnSoNgay.getValue();
            int tongKe = (s + tr + c + t) * n;

    
            int tonKho = khoBus.getSoLuongTon(thuocDangChon.getMathuoc());
  
            String hsdString = khoBus.getHsd(thuocDangChon.getMathuoc());

    
            LocalDate ngayHetHan = LocalDate.parse(hsdString); 

      
            LocalDate ngayHienTai = LocalDate.now();


            if (ngayHetHan.isAfter(ngayHienTai)) {
     
  
            } else {
          
     
                JOptionPane.showMessageDialog(null, "Cảnh báo: Thuốc đã hết hạn!");
            }
            if (tongKe > tonKho) {
                int choice = JOptionPane.showConfirmDialog(this, 
                    "Thuốc này chỉ còn " + tonKho + " " + thuocDangChon.getDonvitinh() + 
                    " trong kho.\nBạn vẫn muốn kê đơn vượt số lượng này chứ?", 
                    "Cảnh báo hết thuốc", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.NO_OPTION) return; 
            }

            CTToaThuoc ct = new CTToaThuoc();
            ct.setMaToaThuoc(maToaHienTai);
            ct.setMaThuoc(thuocDangChon.getMathuoc());
            ct.setSang(txtSang.getText());
            ct.setTrua(txtTrua.getText());
            ct.setChieu(txtChieu.getText());
            ct.setToi(txtToi.getText());
            ct.setSoNgay(n);
            ct.setThoiDiemDung(cboThoiDiem.getSelectedItem().toString());
            ct.setCachDung(txtLoiDan.getText());

            if (toaBus.themThuocVaoToa(ct)) {
                loadTableData();
                resetForm();
                loadDataCu();
            }
        });
        // Cập nhật
        btnCapNhatRef.addActionListener(e -> {
            if (idChiTietDangSua <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng để cập nhật!");
                return;
            }
            if (thuocDangChon == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin thuốc cần cập nhật!");
                return;
            }

            int s = parse(txtSang.getText());
            int tr = parse(txtTrua.getText());
            int c = parse(txtChieu.getText());
            int t = parse(txtToi.getText());
            int n = (int) spnSoNgay.getValue();
            int tongKe = (s + tr + c + t) * n;

            int tonKho = khoBus.getSoLuongTon(thuocDangChon.getMathuoc());

            if (tongKe > tonKho) {
                int choice = JOptionPane.showConfirmDialog(this, 
                    "Tổng số lượng mới (" + tongKe + ") vượt quá tồn kho (" + tonKho + ").\nVẫn tiếp tục?", 
                    "Cảnh báo", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) return;
            }

            CTToaThuoc ct = new CTToaThuoc();
            ct.setId(idChiTietDangSua);
            ct.setSang(txtSang.getText());
            ct.setTrua(txtTrua.getText());
            ct.setChieu(txtChieu.getText());
            ct.setToi(txtToi.getText());
            ct.setSoNgay(n);
            ct.setThoiDiemDung(cboThoiDiem.getSelectedItem().toString());
            ct.setCachDung(txtLoiDan.getText());

            if (toaBus.capNhatChiTiet(ct)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadTableData(); 
                resetForm(); 
                loadDataCu();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    idChiTietDangSua = Integer.parseInt(table.getValueAt(row, 0).toString());
                    CTToaThuoc ct = null;
                    for (CTToaThuoc item : currentListThuoc) {
                        if (item.getId() == idChiTietDangSua) { 
                            ct = item; 
                            break; 
                        }
                    }
                    if (ct != null) {
                        // QUAN TRỌNG: Phải lấy lại đối tượng Thuoc để phục vụ việc kiểm tra tồn kho khi Update
                        thuocDangChon = thuocBus.getThuocById(ct.getMaThuoc()); 
                        
                        lblTenThuocChon.setText(ct.getTenThuoc() + " (Đang sửa)");
                        txtDonGia.setText(new DecimalFormat("#,###").format(ct.getDonGia()));
                        txtSang.setText(ct.getSang()); 
                        txtTrua.setText(ct.getTrua());
                        txtChieu.setText(ct.getChieu()); 
                        txtToi.setText(ct.getToi());
                        spnSoNgay.setValue(ct.getSoNgay());
                        cboThoiDiem.setSelectedItem(ct.getThoiDiemDung());
                        txtLoiDan.setText(ct.getCachDung());
                        txtSearch.setEnabled(false);
                        
                        // Hiển thị tồn kho hiện tại
                        int ton = khoBus.getSoLuongTon(ct.getMaThuoc());
                        String hsd = khoBus.getHsd(ct.getMaThuoc());
                        lblThongBao.setText("Tồn kho hiện tại: " + ton +" - "+ " Hạn sử dụng thuốc: " + hsd );
                    }
                }
            }
        });
        btnXoaRef.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                if (toaBus.xoaThuoc(id)) { loadTableData(); resetForm(); loadDataCu(); }
            }
        });

        btnLamMoiRef.addActionListener(e -> resetForm());
        
        btnLichSuToa.addActionListener(e -> {
            JDialog d = new JDialog((Window)SwingUtilities.getWindowAncestor(this), "Lịch sử toa thuốc", Dialog.ModalityType.APPLICATION_MODAL);
            d.getContentPane().add(new PnlLichSuToaThuoc(Session.mabenhnhan, maToaHienTai));
            d.setSize(920, 650); d.setLocationRelativeTo(null); d.setVisible(true);
            loadDataCu();
        });
        
        btnIn.addActionListener(e -> inToaThuocPDF());
    }

    private void selectThuoc(Thuoc t) {
        this.thuocDangChon = t;
        lblTenThuocChon.setText(t.getTenthuoc() + " (" + t.getDonvitinh() + ")");
        txtDonGia.setText(new DecimalFormat("#,###").format(t.getDongiaban()));
        int ton = khoBus.getSoLuongTon(t.getMathuoc());
        String hsd = khoBus.getHsd(t.getMathuoc());
        lblThongBao.setText("Tồn kho hiện tại: " + ton +" - "+ " Hạn sử dụng thuốc: " + hsd );
    }

    private void resetForm() {
        idChiTietDangSua = 0; thuocDangChon = null;
        txtSearch.setEnabled(true); txtSearch.setText("");
        lblTenThuocChon.setText("CHƯA CHỌN THUỐC");
        txtDonGia.setText(""); txtSang.setText(""); txtTrua.setText("");
        txtChieu.setText(""); txtToi.setText(""); txtLoiDan.setText("");
        spnSoNgay.setValue(5); lblThongBao.setText("");
    }

    private boolean validateInputs() {
        try {
            Integer.parseInt(txtSang.getText().isEmpty() ? "0" : txtSang.getText());
            return true;
        } catch (Exception e) { return false; }
    }

    public void loadDataCu() {
        if (this.mapk <= 0) return;

        ToaThuoc ktToa = toaBus.getToaThuocByPhieuKham(this.mapk);
        if (ktToa != null) {
            maToaHienTai = ktToa.getMaToaThuoc();
            btnTaoToaRef.setText("Mã toa: " + maToaHienTai);
            btnTaoToaRef.setEnabled(false);
        } else {
            maToaHienTai = 0;
            btnTaoToaRef.setText("Tạo toa mới");
            btnTaoToaRef.setEnabled(true);
        }

        currentListThuoc = toaBus.getChiTietByMaPhieuKham(this.mapk);
        tableModel.setRowCount(0);
        double tong = 0;
        int maxDays = 0; // Biến lưu số ngày thuốc lớn nhất

        if (currentListThuoc != null && !currentListThuoc.isEmpty()) {
            for (CTToaThuoc ct : currentListThuoc) {
                int sl = (parse(ct.getSang()) + parse(ct.getTrua()) + parse(ct.getChieu()) + parse(ct.getToi())) * ct.getSoNgay();
                double tt = sl * ct.getDonGia(); 
                tong += tt;
                
                tableModel.addRow(new Object[]{ 
                    ct.getId(), ct.getTenThuoc(), ct.getSang(), ct.getTrua(), 
                    ct.getChieu(), ct.getToi(), ct.getSoNgay(), ct.getDonViTinh(), 
                    ct.getCachDung(), ct.getThoiDiemDung(), new DecimalFormat("#,###").format(tt) 
                });
                
                // Tìm số ngày lớn nhất
                if (ct.getSoNgay() > maxDays) {
                    maxDays = ct.getSoNgay();
                }
            }
        }
        
        lblTongTienVal.setText(new DecimalFormat("#,###").format(tong) + " VNĐ");

        Session.maxDaysThuoc = maxDays;
    }

 
    private void loadTableData() {
        loadDataCu();
    }

    private int parse(String s) { try { return Integer.parseInt(s); } catch (Exception e) { return 0; } }

    private void setFormReadOnly(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) ((JTextField) c).setEditable(false);
            else if (c instanceof JTextArea) ((JTextArea) c).setEditable(false);
            else if (c instanceof JButton) c.setEnabled(false);
            else if (c instanceof JComboBox) c.setEnabled(false);
            else if (c instanceof JSpinner) c.setEnabled(false);
            else if (c instanceof Container) setFormReadOnly((Container) c);
        }
    }

    private void inToaThuocPDF() {
        if (maToaHienTai <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa có toa thuốc để in!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Toa Thuốc");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("ToaThuoc_" + maToaHienTai + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

        
                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font fTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fBold = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fNorm = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.NORMAL);

    
                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA \nĐịa chỉ: TP.HCM - Hotline: 1900.xxxx", fBold);
                pHeader.setAlignment(Element.ALIGN_CENTER);
                document.add(pHeader);
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("ĐƠN THUỐC", fTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                document.add(new Paragraph("\n"));

      
                document.add(new Paragraph("Mã phiếu khám: " + mapk, fNorm));
                document.add(new Paragraph("Ngày kê đơn: " + java.time.LocalDate.now() + " - Mã toa: " + maToaHienTai, fNorm));
                document.add(new Paragraph("Bác sĩ điều trị: " + Session.TenNhanVien, fNorm));
                document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------\n\n"));


                PdfPTable pdfTable = new PdfPTable(5); 
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[]{1, 5, 1.5f, 1.5f, 4}); 

         
                addCellToTable(pdfTable, "STT", fBold);
                addCellToTable(pdfTable, "Tên thuốc", fBold);
                addCellToTable(pdfTable, "SL", fBold);
                addCellToTable(pdfTable, "ĐVT", fBold);
                addCellToTable(pdfTable, "Liều dùng & Cách dùng", fBold);


                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    addCellToTable(pdfTable, String.valueOf(i + 1), fNorm); 
                    addCellToTable(pdfTable, tableModel.getValueAt(i, 1).toString(), fNorm); 
                    
                    // Tính tổng số lượng: (Sáng + Trưa + Chiều + Tối) * Số ngày
                    int s = parse(tableModel.getValueAt(i, 2).toString());
                    int tr = parse(tableModel.getValueAt(i, 3).toString());
                    int c = parse(tableModel.getValueAt(i, 4).toString());
                    int t = parse(tableModel.getValueAt(i, 5).toString());
                    int n = parse(tableModel.getValueAt(i, 6).toString());
                    int tongSL = (s + tr + c + t) * n;
                    
                    addCellToTable(pdfTable, String.valueOf(tongSL), fNorm); 
                    addCellToTable(pdfTable, tableModel.getValueAt(i, 7).toString(), fNorm); 
                    
        
                    String chiTietLieu = String.format("Sáng:%s, Trưa:%s, Chiều:%s, Tối:%s (%s)\nHD: %s", 
                            tableModel.getValueAt(i, 2), tableModel.getValueAt(i, 3), 
                            tableModel.getValueAt(i, 4), tableModel.getValueAt(i, 5),
                            tableModel.getValueAt(i, 9),
                            tableModel.getValueAt(i, 8)); 
                    
                    PdfPCell cellHD = new PdfPCell(new Phrase(chiTietLieu, fNorm));
                    cellHD.setPadding(5);
                    pdfTable.addCell(cellHD);
                }
                document.add(pdfTable);

            
                document.add(new Paragraph("\n----------------------------------------------------------------------------------------------------------------------------------"));
                Paragraph pTotal = new Paragraph("Cộng tiền thuốc dự kiến: " + lblTongTienVal.getText(), fBold);
                pTotal.setAlignment(Element.ALIGN_RIGHT);
                document.add(pTotal);

       
                document.add(new Paragraph("\nLời dặn: Tái khám nhớ mang theo đơn thuốc này.", fNorm));
                document.add(new Paragraph("\n\n"));
                
                Paragraph pSign = new Paragraph("Bác sĩ khám bệnh\n(Ký và ghi rõ họ tên)", fBold);
                pSign.setAlignment(Element.ALIGN_RIGHT);
                pSign.setIndentationRight(50);
                document.add(pSign);

                document.close();
                JOptionPane.showMessageDialog(this, "Đã xuất đơn thuốc PDF thành công!");
                
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(filePath));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi in PDF: " + ex.getMessage());
            }
        }
    }


    private void addCellToTable(PdfPTable table, String text, com.itextpdf.text.Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

  

}