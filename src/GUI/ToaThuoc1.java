package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.Desktop;

// --- IMPORT CÁC LOGIC ---
import BUS.KhoThuocBus;
import BUS.ThuocBus;
import BUS.ToaThuocBus;
import MODEL.CTToaThuoc;
import MODEL.Session;
import MODEL.Thuoc;
import MODEL.ToaThuoc;

public class ToaThuoc1 extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField textField; 
    private JTextField textField_1; 
    private JTextField textField_2; 
    private JTextField textField_3; 
    private JTextField textField_4; 
    private JTextField textField_5; 
    private JTextField textField_6;
    private JTable table;
    
    private DefaultTableModel tableModel;
    private ThuocBus thuocBus = new ThuocBus();
    private ToaThuocBus toaBus = new ToaThuocBus();
    private KhoThuocBus khoBus = new KhoThuocBus(); 
    
    // Biến lưu mã toa hiện tại
    private int maToaHienTai = 0;
    
    private Thuoc thuocDangChon = null;
    private int idChiTietDangSua = 0;
    private int mapk; 

    // --- BIẾN TOÀN CỤC ĐỂ LƯU DANH SÁCH THUỐC HIỆN TẠI ---
    private List<CTToaThuoc> currentListThuoc = new ArrayList<>();

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

    /**
     * Create the panel.
     */
    public ToaThuoc1(int mapk) {
        this.mapk = mapk;
        
        setLayout(null);
        setBackground(new Color(245, 248, 250));
        setSize(900, 650); 

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 13);
        Font fontBtn = new Font("Segoe UI", Font.BOLD, 13);

        // --- Nút Tạo Toa ---
        JButton btnNewButton = new JButton("Tạo toa mới");
        btnNewButton.setBounds(20, 20, 140, 35);
        styleButton(btnNewButton, new Color(0, 123, 255)); 
        add(btnNewButton);
        this.btnTaoToaRef = btnNewButton;
        
        // --- Tìm kiếm ---
        textField = new JTextField();
        textField.setBounds(180, 20, 700, 35);
        textField.setFont(fontText);
        textField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200,200,200)), "Tìm kiếm thuốc..."));
        add(textField);
        
        lblThongBao = new JLabel("");
        lblThongBao.setForeground(Color.RED);
        lblThongBao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblThongBao.setBounds(180, 55, 600, 20);
        add(lblThongBao);
        
        // --- Thông tin thuốc ---
        JLabel lblNewLabel = new JLabel("Thuốc đang chọn:");
        lblNewLabel.setBounds(20, 75, 120, 25);
        lblNewLabel.setFont(fontLabel);
        add(lblNewLabel);
        
        lblTenThuocChon = new JLabel("CHƯA CHỌN THUỐC");
        lblTenThuocChon.setBounds(150, 75, 400, 25);
        lblTenThuocChon.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenThuocChon.setForeground(new Color(220, 53, 69)); 
        add(lblTenThuocChon);
        
        JLabel lblNewLabel_1_1 = new JLabel("Đơn giá (VNĐ):");
        lblNewLabel_1_1.setBounds(630, 75, 100, 25);
        lblNewLabel_1_1.setFont(fontLabel);
        add(lblNewLabel_1_1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(730, 70, 150, 30);
        textField_1.setFont(fontText);
        textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
        textField_1.setEditable(false); 
        textField_1.setBackground(Color.WHITE);
        add(textField_1);
        
        // --- Nhập Liều Lượng ---
        int yPosRow3 = 120;
        int fieldWidth = 60;

        addLabel("Sáng", 20, yPosRow3, 40, fontLabel);
        textField_2 = createNumberField(60, yPosRow3 - 5, fieldWidth);
        add(textField_2);
        
        addLabel("Trưa", 140, yPosRow3, 40, fontLabel);
        textField_3 = createNumberField(180, yPosRow3 - 5, fieldWidth);
        add(textField_3);
        
        addLabel("Chiều", 260, yPosRow3, 40, fontLabel);
        textField_4 = createNumberField(300, yPosRow3 - 5, fieldWidth);
        add(textField_4);

        addLabel("Tối", 380, yPosRow3, 40, fontLabel);
        textField_5 = createNumberField(410, yPosRow3 - 5, fieldWidth);
        add(textField_5);
        
        addLabel("Số ngày:", 500, yPosRow3, 60, fontLabel);
        spnSoNgay = new JSpinner();
        spnSoNgay.setBounds(560, yPosRow3 - 5, 60, 30);
        spnSoNgay.setFont(fontText);
        spnSoNgay.setValue(5);
        add(spnSoNgay);

        // --- Cách dùng ---
        int yPosRow4 = 170;
        addLabel("Thời điểm:", 20, yPosRow4 + 5, 80, fontLabel);
        
        cboThoiDiem = new JComboBox<>(new String[]{"Sau khi ăn", "Trước khi ăn", "Trong bữa ăn", "Trước khi ngủ", "Khi đau/sốt"});
        cboThoiDiem.setBounds(100, yPosRow4, 120, 30);
        cboThoiDiem.setBackground(Color.WHITE);
        cboThoiDiem.setEditable(true);
        add(cboThoiDiem);
        
        addLabel("Lời dặn:", 240, yPosRow4 + 5, 60, fontLabel);
        textField_6 = new JTextField();
        textField_6.setBounds(300, yPosRow4, 580, 30);
        textField_6.setFont(fontText);
        add(textField_6);
        
        // --- Nút Chức Năng ---
        int yPosRow5 = 220;
        int btnW = 110;
        int btnH = 35;
        int startXBtn = 430;
        
        btnThemRef = createButton("Thêm thuốc", startXBtn, yPosRow5, btnW, btnH, new Color(40, 167, 69));
        add(btnThemRef);
        
        btnCapNhatRef = createButton("Cập nhật", startXBtn + 115, yPosRow5, btnW, btnH, new Color(23, 162, 184));
        add(btnCapNhatRef);
        
        btnXoaRef = createButton("Xóa dòng", startXBtn + 115*2, yPosRow5, btnW, btnH, new Color(220, 53, 69));
        add(btnXoaRef);
        
        btnLamMoiRef = createButton("Làm mới", startXBtn + 115*3, yPosRow5, btnW, btnH, new Color(108, 117, 125));
        add(btnLamMoiRef);
        
        // --- Bảng Dữ Liệu ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 280, 860, 270);
        add(scrollPane);
        
        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Tên Thuốc", "Sáng", "Trưa", "Chiều", "Tối", "Số Ngày", "ĐV Tính", "Cách Dùng","Thời điểm dùng", "Thành Tiền" }
        );
        table.setModel(tableModel);
        table.setRowHeight(30);
        table.setFont(fontText);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(230, 230, 230));
        
        // Ẩn cột ID
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        
        scrollPane.setViewportView(table);

        // --- Footer ---
        addLabel("TỔNG TIỀN TOA:", 550, 570, 120, fontLabel);
        
        lblTongTienVal = new JLabel("0 VNĐ");
        lblTongTienVal.setBounds(670, 570, 200, 30);
        lblTongTienVal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTienVal.setForeground(new Color(220, 53, 69)); 
        add(lblTongTienVal);
        
        JButton btnIn = createButton("In đơn thuốc", 20, 570, 150, 40, new Color(255, 193, 7));
        btnIn.setForeground(Color.BLACK);
        btnIn.addActionListener(e -> inToaThuocPDF());
        add(btnIn);
        
        btnLichSuToa = createButton("Lịch sử toa thuốc", 279, 221, 140, 35, new Color(102, 102, 102));
        add(btnLichSuToa);
        
        this.popupMenu = new JPopupMenu();
        initLogicEvents();
        
        if (!java.beans.Beans.isDesignTime()) {
             loadDataCu(); 
        }
    }
    
    // =================================================================
    // CÁC HÀM LOGIC CHÍNH
    // =================================================================
    
    private void initLogicEvents() {
        // Logic Autocomplete
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ENTER) return;
                String text = textField.getText().trim();
                popupMenu.setVisible(false);
                popupMenu.removeAll();
                if (!text.isEmpty()) {
                    List<String> list = thuocBus.getThuocByTenthuoc(text);
                    if (list != null && !list.isEmpty()) {
                        for (String s : list) {
                            JMenuItem item = new JMenuItem(s);
                            item.addActionListener(evt -> {
                                textField.setText(s);
                                try {
                                    int id = Integer.parseInt(s.split("\\.", 2)[0]);
                                    Thuoc t = thuocBus.getThuocById(id);
                                    if(t != null) selectThuoc(t);
                                } catch (Exception ex) { ex.printStackTrace(); }
                            });
                            popupMenu.add(item);
                        }
                        popupMenu.show(textField, 0, textField.getHeight());
                        textField.requestFocus();
                    }
                }
            }
        });
        
        // Logic Tạo Toa
        btnTaoToaRef.addActionListener(e -> {
            if (this.mapk <= 0) {
                JOptionPane.showMessageDialog(this, "Chưa xác định được phiếu khám!");
                return;
            }
            int idToa = toaBus.taoToaThuoc(this.mapk, "");
            if (idToa > 0) {
                maToaHienTai = idToa;
                btnTaoToaRef.setText("Mã toa: " + idToa);
                btnTaoToaRef.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Đã tạo toa thuốc số: " + idToa);
            }
        });
        
        // Logic Lịch sử
        btnLichSuToa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                PnlLichSuToaThuoc contentPanel = new PnlLichSuToaThuoc(Session.mabenhnhan,maToaHienTai);
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(ToaThuoc1.this)); 
                dialog.setTitle("Lịch sử kê toa của bệnh nhân");
                dialog.setSize(920, 650); 
                dialog.setLocationRelativeTo(null); 
                dialog.setModal(true); 
                dialog.setContentPane(contentPanel);
                dialog.setVisible(true);
                loadDataCu();
        	}
        });

        // -------------------------------------------------------------
        // LOGIC THÊM THUỐC
        // -------------------------------------------------------------
        btnThemRef.addActionListener(e -> {
            lblThongBao.setText(""); 
            if (maToaHienTai == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng tạo toa trước!"); return;
            }
            if (thuocDangChon == null) {
                JOptionPane.showMessageDialog(this, "Chưa chọn thuốc!"); return;
            }
            if (!validateInputs()) return;

            // --- LẤY DỮ LIỆU TRỰC TIẾP ---
            CTToaThuoc ct = new CTToaThuoc();
            ct.setMaToaThuoc(maToaHienTai);
            ct.setMaThuoc(thuocDangChon.getMathuoc());
            ct.setSang(textField_2.getText());
            ct.setTrua(textField_3.getText());
            ct.setChieu(textField_4.getText());
            ct.setToi(textField_5.getText());
            ct.setSoNgay((int) spnSoNgay.getValue());
            
            // Set riêng Thời điểm
            if (cboThoiDiem.getSelectedItem() != null) {
                ct.setThoiDiemDung(cboThoiDiem.getSelectedItem().toString());
            } else {
                ct.setThoiDiemDung("");
            }
            // Set riêng Cách dùng
            ct.setCachDung(textField_6.getText());

            // --- KIỂM TRA TỒN KHO ---
            int tongSLCan = calculateTotal(ct);
            int tonKho = khoBus.getSoLuongTon(ct.getMaThuoc());
            
            if (tonKho < tongSLCan) {
                lblThongBao.setText("CẢNH BÁO: Kho chỉ còn " + tonKho + " viên (Thiếu " + (tongSLCan - tonKho) + ")");
                lblThongBao.setForeground(Color.RED);
                
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Thuốc này trong kho KHÔNG ĐỦ (Tồn: " + tonKho + ", Cần: " + tongSLCan + ").\nBạn có muốn tiếp tục kê đơn không?",
                    "Cảnh báo thiếu thuốc", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if (confirm != JOptionPane.YES_OPTION) return;
            } else {
                lblThongBao.setText("Tồn kho: " + tonKho + " (Đủ)");
                lblThongBao.setForeground(new Color(40, 167, 69));
            }

            if (toaBus.themThuocVaoToa(ct)) {
                loadTableData();
                resetForm();
                textField.requestFocus();
                loadDataCu();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi thêm thuốc!");
            }
        });

        // -------------------------------------------------------------
        // LOGIC CẬP NHẬT THUỐC
        // -------------------------------------------------------------
        btnCapNhatRef.addActionListener(e -> {
            if (idChiTietDangSua <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!"); 
                return;
            }
            if (!validateInputs()) return;
            
            // Tìm đối tượng cũ trong List toàn cục
            CTToaThuoc ctCu = null;
            if (currentListThuoc != null) {
                for (CTToaThuoc item : currentListThuoc) {
                    if (item.getId() == idChiTietDangSua) {
                        ctCu = item;
                        break;
                    }
                }
            }
            if (ctCu == null) return;

            // --- LẤY DỮ LIỆU MỚI ---
            CTToaThuoc ctMoi = new CTToaThuoc();
            ctMoi.setId(idChiTietDangSua);
            ctMoi.setMaThuoc(ctCu.getMaThuoc()); // Giữ nguyên mã thuốc cũ
            
            ctMoi.setSang(textField_2.getText());
            ctMoi.setTrua(textField_3.getText());
            ctMoi.setChieu(textField_4.getText());
            ctMoi.setToi(textField_5.getText());
            ctMoi.setSoNgay((int) spnSoNgay.getValue());
            
            if (cboThoiDiem.getSelectedItem() != null) {
                ctMoi.setThoiDiemDung(cboThoiDiem.getSelectedItem().toString());
            } else {
                ctMoi.setThoiDiemDung("");
            }
            
            ctMoi.setCachDung(textField_6.getText());

            // Kiểm tra kho
            int tongSLCan = calculateTotal(ctMoi);
            int tonKho = khoBus.getSoLuongTon(ctMoi.getMaThuoc());
            
            if (tonKho < tongSLCan) {
                lblThongBao.setText("CẢNH BÁO: Kho chỉ còn " + tonKho + " viên");
                lblThongBao.setForeground(Color.RED);
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Số lượng mới vượt quá tồn kho (" + tonKho + "). Bạn có muốn cập nhật không?",
                    "Cảnh báo thiếu thuốc", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm != JOptionPane.YES_OPTION) return;
            } else {
                lblThongBao.setText(""); 
            }
            
            if (toaBus.capNhatChiTiet(ctMoi)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadTableData();
                resetForm();
                loadDataCu();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        });
        
        // -------------------------------------------------------------
        // SỰ KIỆN CLICK BẢNG (ĐỔ DỮ LIỆU GỐC)
        // -------------------------------------------------------------
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                    idChiTietDangSua = id;
                    
                    // Tìm đối tượng gốc trong List
                    CTToaThuoc ctSelected = null;
                    if (currentListThuoc != null) {
                        for (CTToaThuoc item : currentListThuoc) {
                            if (item.getId() == id) {
                                ctSelected = item;
                                break;
                            }
                        }
                    }

                    if (ctSelected != null) {
                        lblTenThuocChon.setText(ctSelected.getTenThuoc() + " (Đang sửa)");
                        lblTenThuocChon.setForeground(new Color(255, 193, 7)); 
                        
                        textField_2.setText(ctSelected.getSang()); 
                        textField_3.setText(ctSelected.getTrua()); 
                        textField_4.setText(ctSelected.getChieu()); 
                        textField_5.setText(ctSelected.getToi()); 
                        spnSoNgay.setValue(ctSelected.getSoNgay());
                        
                        // Đổ đúng 2 trường riêng biệt
                        cboThoiDiem.setSelectedItem(ctSelected.getThoiDiemDung());
                        textField_6.setText(ctSelected.getCachDung());
                        
                        textField.setEnabled(false);
                    }
                }
            }
        });
        
        // Xóa
        btnXoaRef.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                if (JOptionPane.showConfirmDialog(this, "Xóa thuốc này khỏi toa?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (toaBus.xoaThuoc(id)) {
                        loadTableData();
                        resetForm();
                        loadDataCu();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn dòng để xóa!");
            }
        });
        
        // Làm mới
        btnLamMoiRef.addActionListener(e -> resetForm());
    }
    
    // --- HÀM KIỂM TRA RÀNG BUỘC (VALIDATE) ---
    private boolean validateInputs() {
    	if (!isNumeric(textField_2.getText()) || !isNumeric(textField_3.getText()) || 
    		!isNumeric(textField_4.getText()) || !isNumeric(textField_5.getText())) {
    		JOptionPane.showMessageDialog(this, "Số lượng Sáng/Trưa/Chiều/Tối phải là số nguyên dương (hoặc 0)!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
    		return false;
    	}
    	int s = parseAmount(textField_2.getText());
    	int tr = parseAmount(textField_3.getText());
    	int c = parseAmount(textField_4.getText());
    	int t = parseAmount(textField_5.getText());
    	int ngay = (int) spnSoNgay.getValue();
    	if (s < 0 || tr < 0 || c < 0 || t < 0) {
    		JOptionPane.showMessageDialog(this, "Số lượng thuốc không được âm!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE); return false;
    	}
    	if (ngay <= 0) {
    		JOptionPane.showMessageDialog(this, "Số ngày phải lớn hơn 0!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE); return false;
    	}
    	if (s + tr + c + t == 0) {
    		JOptionPane.showMessageDialog(this, "Tổng liều lượng một ngày phải lớn hơn 0!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE); return false;
    	}
    	return true;
    }
    
    private boolean isNumeric(String str) {
    	if (str == null || str.trim().isEmpty()) return true;
    	try { Integer.parseInt(str.trim()); return true; } catch(NumberFormatException e) { return false; }
    }
    
    private int calculateTotal(CTToaThuoc ct) {
    	int sl = (parseAmount(ct.getSang()) + parseAmount(ct.getTrua()) + 
    			  parseAmount(ct.getChieu()) + parseAmount(ct.getToi())) * ct.getSoNgay();
    	return sl == 0 ? 1 : sl;
    }

    // --- HÀM LOAD DỮ LIỆU CŨ ---
    public void loadDataCu() {
        if (this.mapk <= 0) return;
        
        // Lưu vào biến toàn cục để dùng khi click
        currentListThuoc = toaBus.getChiTietByMaPhieuKham(this.mapk);
        
        tableModel.setRowCount(0);
        double tongCong = 0; 

        if (currentListThuoc != null && !currentListThuoc.isEmpty()) {
            this.maToaHienTai = currentListThuoc.get(0).getMaToaThuoc();
            btnTaoToaRef.setText("Mã toa: " + maToaHienTai);
            btnTaoToaRef.setEnabled(false);

            for (CTToaThuoc ct : currentListThuoc) {
                int sl = calculateTotal(ct);
                double thanhTien = sl * ct.getDonGia();
                tongCong += thanhTien;

                // --- SỬA LẠI ĐOẠN NÀY ---
                // Không gộp chuỗi nữa mà tách riêng ra từng cột
                tableModel.addRow(new Object[]{
                    ct.getId(), 
                    ct.getTenThuoc(), 
                    ct.getSang(), 
                    ct.getTrua(), 
                    ct.getChieu(), 
                    ct.getToi(), 
                    ct.getSoNgay(), 
                    ct.getDonViTinh(),
                    ct.getCachDung(),      // Cột Cách dùng
                    ct.getThoiDiemDung(),  // Cột Thời điểm dùng (Đã tách riêng)
                    formatMoney(thanhTien) // Cột Thành tiền
                });
            }
        } else {
            checkTrangThaiToa();
        }
        String chuoiTien = formatMoney(tongCong);
        lblTongTienVal.setText(chuoiTien + " VNĐ");
        toaBus.updateToaThuocByPhieuKham(chuoiTien, mapk);
    }
    private void checkTrangThaiToa() {
        if (this.mapk > 0) {
            ToaThuoc t = toaBus.getToaThuocByPhieuKham(this.mapk);
            if (t != null) {
                maToaHienTai = t.getMaToaThuoc();
                btnTaoToaRef.setText("Mã toa: " + maToaHienTai);
                btnTaoToaRef.setEnabled(false);
            }
        }
    }
    
    private void loadTableData() {
        tableModel.setRowCount(0);
        if (maToaHienTai == 0) return;
        
        currentListThuoc = toaBus.getListChiTiet(maToaHienTai);
        
        double tongCong = 0;
        for (CTToaThuoc ct : currentListThuoc) {
            int sl = calculateTotal(ct);
            double thanhTien = sl * ct.getDonGia();
            tongCong += thanhTien;
            
            // --- SỬA GỌN LẠI ---
            tableModel.addRow(new Object[]{
                ct.getId(), 
                ct.getTenThuoc(), 
                ct.getSang(), 
                ct.getTrua(), 
                ct.getChieu(), 
                ct.getToi(), 
                ct.getSoNgay(), 
                ct.getDonViTinh(),
                ct.getCachDung(),      // Cột Cách dùng
                ct.getThoiDiemDung(),  // Cột Thời điểm dùng
                formatMoney(thanhTien)
            });
        }
        lblTongTienVal.setText(formatMoney(tongCong) + " VNĐ");
    }
    
    // --- Helper Methods ---
    private void selectThuoc(Thuoc t) {
        this.thuocDangChon = t;
        lblTenThuocChon.setText(t.getTenthuoc() + " (" + t.getDonvitinh() + ")");
        lblTenThuocChon.setForeground(new Color(0, 123, 255));
        textField_1.setText(formatMoney(t.getDongiaban()));
        textField.setText(t.getTenthuoc());
        popupMenu.setVisible(false);
        
        int ton = khoBus.getSoLuongTon(t.getMathuoc());
        if(ton > 0) {
        	lblThongBao.setText("Tồn kho: " + ton + " " + t.getDonvitinh());
        	lblThongBao.setForeground(new Color(40, 167, 69));
        } else {
        	lblThongBao.setText("HẾT HÀNG (Tồn: 0)");
        	lblThongBao.setForeground(Color.RED);
        }
    }
    
    private void resetForm() {
        idChiTietDangSua = 0;
        thuocDangChon = null;
        textField.setEnabled(true);
        textField.setText("");
        lblTenThuocChon.setText("CHƯA CHỌN THUỐC");
        lblTenThuocChon.setForeground(new Color(220, 53, 69));
        textField_1.setText(""); 
        textField_2.setText(""); textField_3.setText(""); 
        textField_4.setText(""); textField_5.setText(""); 
        textField_6.setText(""); 
        spnSoNgay.setValue(5);
        lblThongBao.setText("");
        table.clearSelection();
    }
    
    private int parseAmount(String t) {
        try {
            return Integer.parseInt(t.replaceAll("[^0-9]", ""));
        } catch (Exception e) { return 0; }
    }
    
    private String objToString(Object o) {
    	
    	return o == null ? "" : o.toString(); 
    	
    }
    private String formatMoney(double v) { 
    	
    	return new DecimalFormat("#,###").format(v); 
    	
    }
    private void addLabel(String t, int x, int y, int w, Font f) {
        JLabel l = new JLabel(t); l.setBounds(x, y, w, 20); l.setFont(f); add(l);
    }
    private JTextField createNumberField(int x, int y, int w) {
        JTextField t = new JTextField(); t.setBounds(x, y, w, 30);
        t.setHorizontalAlignment(SwingConstants.CENTER);
        t.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) { if (!Character.isDigit(e.getKeyChar())) e.consume(); }
        });
        return t;
    }
    private JButton createButton(String t, int x, int y, int w, int h, Color bg) {
        JButton b = new JButton(t); b.setBounds(x, y, w, h); styleButton(b, bg); return b;
    }
	private void styleButton(JButton btn, Color bgColor) {
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setBackground(bgColor);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
    // =================================================================
    // HÀM IN PDF
    // =================================================================
    private void inToaThuocPDF() {
        if (maToaHienTai <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa có toa thuốc để in!"); return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Toa Thuốc");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("ToaThuoc_" + maToaHienTai + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) filePath += ".pdf";

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                String fontPath = "C:/Windows/Fonts/arial.ttf"; 
                BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font fTitle = new com.itextpdf.text.Font(bf, 20, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fNorm = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);

                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA\nĐịa chỉ: TP.HCM\nSĐT: 0909.123.456", fBold);
                pHeader.setAlignment(Element.ALIGN_CENTER);
                document.add(pHeader);
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("ĐƠN THUỐC", fTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Mã phiếu khám: " + mapk, fNorm));
                document.add(new Paragraph("Ngày khám: " + java.time.LocalDate.now(), fNorm));
                document.add(new Paragraph("\n----------------------------------------------------------\n"));

                PdfPTable tablePDF = new PdfPTable(5); 
                tablePDF.setWidthPercentage(100);
                tablePDF.setWidths(new float[]{1, 4, 1, 1, 3}); 

                addCellToTable(tablePDF, "STT", fBold);
                addCellToTable(tablePDF, "Tên thuốc", fBold);
                addCellToTable(tablePDF, "SL", fBold);
                addCellToTable(tablePDF, "ĐVT", fBold);
                addCellToTable(tablePDF, "Cách dùng", fBold);

                int rowCount = tableModel.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    addCellToTable(tablePDF, String.valueOf(i + 1), fNorm);
                    addCellToTable(tablePDF, tableModel.getValueAt(i, 1).toString(), fNorm);
                    
                    String s = tableModel.getValueAt(i, 2).toString();
                    String tr = tableModel.getValueAt(i, 3).toString();
                    String c = tableModel.getValueAt(i, 4).toString();
                    String t = tableModel.getValueAt(i, 5).toString();
                    String ngay = tableModel.getValueAt(i, 6).toString();
                    
                    int tong = (parseAmount(s) + parseAmount(tr) + parseAmount(c) + parseAmount(t)) * parseAmount(ngay);
                    addCellToTable(tablePDF, String.valueOf(tong), fNorm);
                    addCellToTable(tablePDF, tableModel.getValueAt(i, 7).toString(), fNorm);
                    
                    String lieu = String.format("S: %s, Tr: %s, C: %s, T: %s (x %s ngày)", s, tr, c, t, ngay);
                    String dan = tableModel.getValueAt(i, 8).toString(); // Đã bao gồm thời điểm + cách dùng do hàm loadTable gộp
                    addCellToTable(tablePDF, lieu + "\n" + dan, fNorm);
                }
                document.add(tablePDF);

                document.add(new Paragraph("\nLưu ý: Tái khám nhớ mang theo đơn này.", fNorm));
                document.add(new Paragraph("\n\n"));
                
                Paragraph pSign = new Paragraph("Bác sĩ điều trị\n(Ký, ghi rõ họ tên)", fBold);
                pSign.setAlignment(Element.ALIGN_RIGHT);
                pSign.setIndentationRight(50);
                document.add(pSign);

                document.close();
                JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
                if (Desktop.isDesktopSupported()) Desktop.getDesktop().open(new File(filePath));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi in PDF: " + ex.getMessage());
            }
        }
    }
    
    private void addCellToTable(PdfPTable table, String text, com.itextpdf.text.Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }
}