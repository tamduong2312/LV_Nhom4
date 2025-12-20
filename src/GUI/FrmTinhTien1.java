package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.HoaDonBus;
import DAO.TinhTienDao;
import MODEL.CTHoaDon;
import MODEL.CTToaThuoc;
import MODEL.DichVu;

import java.io.File;
import java.io.FileOutputStream;
import java.awt.Desktop;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FrmTinhTien1 extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTable tableDichVu;
    private DefaultTableModel modelDichVu;
    
    private JTable tableThuoc;
    private DefaultTableModel modelThuoc;
    
    private JLabel lblTenBenhNhan;
    private JLabel lblTongTienDichVu;
    private JLabel lblTongTienThuoc;
    private JLabel lblTongCong;
    
    private JButton btnQuayLai;
    private JButton btnInHoaDon;
    private JButton btnXacNhan;

    private int maPhieuKham;
    private int maHoaDon;
    private String tenBenhNhanStr;
    
    private TinhTienDao tinhTienDao = new TinhTienDao();
    private HoaDonBus hoaDonBus = new HoaDonBus();
    
    private DecimalFormat df = new DecimalFormat("#,###");
    private double tongTienCuoiCung = 0;
    
    private List<DichVu> listDichVu;
    private List<CTToaThuoc> listThuoc;

    public FrmTinhTien1(int maPK, String tenbn, int mahd) {
        this.maPhieuKham = maPK;
        this.tenBenhNhanStr = tenbn;
        this.maHoaDon = mahd;

        setLayout(null);
        setBackground(Color.WHITE); 
        setSize(850, 700); 

        Font fontHeader = new Font("Segoe UI", Font.BOLD, 24);
        Font fontSection = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);
        Color colPrimary = new Color(0, 102, 204); 
        Color colSuccess = new Color(40, 167, 69); 
        Color colGray = new Color(108, 117, 125);  
        Color colTableHead = new Color(240, 242, 245); 

        JLabel lblTitle = new JLabel("CHI TIẾT THANH TOÁN");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(colPrimary);
        lblTitle.setFont(fontHeader);
        lblTitle.setBounds(0, 20, 850, 40); 
        add(lblTitle);
        
        lblTenBenhNhan = new JLabel("Tên bệnh nhân: " + this.tenBenhNhanStr); 
        lblTenBenhNhan.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenBenhNhan.setForeground(new Color(50, 50, 50));
        lblTenBenhNhan.setBounds(40, 70, 600, 25);
        add(lblTenBenhNhan);

        JLabel lblHeaderDV = new JLabel("I. DỊCH VỤ / CẬN LÂM SÀNG");
        lblHeaderDV.setFont(fontSection);
        lblHeaderDV.setForeground(colPrimary);
        lblHeaderDV.setBounds(40, 110, 300, 25);
        add(lblHeaderDV);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 140, 770, 150); 
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220))); 
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);
        
        tableDichVu = new JTable();
        modelDichVu = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Tên dịch vụ", "Số lượng", "Thành tiền" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableDichVu.setModel(modelDichVu);
        styleTable(tableDichVu, fontText, colTableHead); 
        scrollPane.setViewportView(tableDichVu);
        
        JLabel lblHeaderThuoc = new JLabel("II. CHI TIẾT ĐƠN THUỐC");
        lblHeaderThuoc.setFont(fontSection);
        lblHeaderThuoc.setForeground(colPrimary);
        lblHeaderThuoc.setBounds(40, 310, 300, 25);
        add(lblHeaderThuoc);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(40, 340, 770, 150);
        scrollPane_1.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane_1.getViewport().setBackground(Color.WHITE);
        add(scrollPane_1);
        
        tableThuoc = new JTable();
        modelThuoc = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Tên thuốc", "Số lượng", "Đơn giá", "Thành tiền" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableThuoc.setModel(modelThuoc);
        styleTable(tableThuoc, fontText, colTableHead);
        scrollPane_1.setViewportView(tableThuoc);
        
        int labelX = 450; 
        int widthLabel = 160;
        int startY = 510;
        int gap = 30;

        JLabel lblLabelDV = new JLabel("Tổng tiền dịch vụ:");
        lblLabelDV.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLabelDV.setFont(fontText);
        lblLabelDV.setBounds(labelX, startY, widthLabel, 25);
        add(lblLabelDV);
        
        lblTongTienDichVu = new JLabel("0 VNĐ");
        lblTongTienDichVu.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongTienDichVu.setFont(fontText);
        lblTongTienDichVu.setBounds(labelX + 160, startY, 200, 25);
        add(lblTongTienDichVu);
        
        JLabel lblLabelThuoc = new JLabel("Tổng tiền thuốc:");
        lblLabelThuoc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLabelThuoc.setFont(fontText);
        lblLabelThuoc.setBounds(labelX, startY + gap, widthLabel, 25);
        add(lblLabelThuoc);
        
        lblTongTienThuoc = new JLabel("0 VNĐ");
        lblTongTienThuoc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongTienThuoc.setFont(fontText);
        lblTongTienThuoc.setBounds(labelX + 160, startY + gap, 200, 25);
        add(lblTongTienThuoc);
        
        JLabel lblLabelTotal = new JLabel("TỔNG CỘNG:");
        lblLabelTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLabelTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblLabelTotal.setForeground(new Color(220, 53, 69)); 
        lblLabelTotal.setBounds(labelX, startY + gap * 2, widthLabel, 30);
        add(lblLabelTotal);
        
        lblTongCong = new JLabel("0 VNĐ");
        lblTongCong.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongCong.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongCong.setForeground(new Color(220, 53, 69));
        lblTongCong.setBounds(labelX + 160, startY + gap * 2, 200, 30);
        add(lblTongCong);
        
        int btnY = 620;
        int btnHeight = 40;
        
        btnQuayLai = new JButton("Quay lại");
        btnQuayLai.setBounds(40, btnY, 120, btnHeight);
        styleButton(btnQuayLai, colGray);
        add(btnQuayLai);
        
        btnInHoaDon = new JButton("In hóa đơn");
        btnInHoaDon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuatHoaDonPDF();
            }
        });
        btnInHoaDon.setBounds(430, btnY, 180, btnHeight);
        styleButton(btnInHoaDon, new Color(23, 162, 184)); 
        add(btnInHoaDon);
        
        btnXacNhan = new JButton("Xác nhận thanh toán");
        btnXacNhan.setBounds(630, btnY, 180, btnHeight);
        styleButton(btnXacNhan, colSuccess);
        add(btnXacNhan);

        if (!java.beans.Beans.isDesignTime()) {
            loadData();
            initEvents();
        }
    }

    private void loadData() {
        if (this.maPhieuKham <= 0) return;

        double sumDichVu = 0;
        double sumThuoc = 0;

        listDichVu = tinhTienDao.getListDichVuSuDung(this.maPhieuKham);
        modelDichVu.setRowCount(0);
        
        if (listDichVu != null) {
            for (DichVu dv : listDichVu) {
                double thanhTien = dv.getDonGia(); 
                sumDichVu += thanhTien;
                
                modelDichVu.addRow(new Object[]{
                    dv.getTenDichVu(),
                    "1",
                    df.format(thanhTien)
                });
            }
        }

        listThuoc = tinhTienDao.getListThuocSuDung(this.maPhieuKham);
        modelThuoc.setRowCount(0);
        
        if (listThuoc != null) {
            for (CTToaThuoc ct : listThuoc) {
                double thanhTien = ct.getSoNgay() * ct.getDonGia(); 
                sumThuoc += thanhTien;
                
                modelThuoc.addRow(new Object[]{
                    ct.getTenThuoc(),
                    ct.getSoNgay(),
                    df.format(ct.getDonGia()),
                    df.format(thanhTien)
                });
            }
        }

        this.tongTienCuoiCung = sumDichVu + sumThuoc;
        
        lblTongTienDichVu.setText(df.format(sumDichVu) + " VNĐ");
        lblTongTienThuoc.setText(df.format(sumThuoc) + " VNĐ");
        lblTongCong.setText(df.format(tongTienCuoiCung) + " VNĐ");
    }

    private void initEvents() {
        btnQuayLai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        btnXacNhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLyThanhToan();
            }
        });
    }

    private void xuLyThanhToan() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Xác nhận thanh toán hóa đơn #" + this.maHoaDon + "\nTổng tiền: " + df.format(tongTienCuoiCung) + " VNĐ?", 
            "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (listDichVu != null) {
                    for (DichVu dv : listDichVu) {
                        CTHoaDon ct = new CTHoaDon();
                        ct.setMaHoaDon(this.maHoaDon);
                        ct.setNoiDung(dv.getTenDichVu());
                        ct.setLoaiMuc("DICH_VU");
                        ct.setIdGoc(dv.getMaDichVu());
                        ct.setSoLuong(1);
                        ct.setDonGia(dv.getDonGia());
                        ct.setThanhTien(dv.getDonGia());
                        hoaDonBus.themChiTietHoaDon(ct);
                    }
                }

                if (listThuoc != null) {
                    for (CTToaThuoc t : listThuoc) {
                        CTHoaDon ct = new CTHoaDon();
                        ct.setMaHoaDon(this.maHoaDon);
                        ct.setNoiDung(t.getTenThuoc());
                        ct.setLoaiMuc("THUOC");
                        ct.setIdGoc(t.getMaThuoc());
                        ct.setSoLuong(t.getSoNgay());     
                        ct.setDonGia(t.getDonGia());
                        double thanhTien = t.getSoNgay() * t.getDonGia();
                        ct.setThanhTien(thanhTien);
                        hoaDonBus.themChiTietHoaDon(ct);
                    }
                }

                hoaDonBus.capNhatTrangThai(this.maHoaDon, "đã thanh toán", this.tongTienCuoiCung);
                tinhTienDao.thanhToanPhieuKham(this.maPhieuKham, this.tongTienCuoiCung);

                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
            }
        }
    }

    private void closeWindow() {
        Window win = SwingUtilities.getWindowAncestor(this);
        if (win != null) {
            win.dispose();
        }
    }

    private void xuatHoaDonPDF() {
        if (this.maHoaDon <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa xác định được hóa đơn!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Hóa Đơn");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("HoaDon_" + maHoaDon + ".pdf"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                String fontPath = "C:/Windows/Fonts/arial.ttf"; 
                BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                
                com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 20, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);
                com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);

                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA HẠNH PHÚC\nĐịa chỉ: 123 Đường ABC, Quận XYZ, TP.HCM\nHotline: 1900.1234", fontHeader);
                pHeader.setAlignment(Element.ALIGN_CENTER);
                document.add(pHeader);
                
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("HÓA ĐƠN THANH TOÁN", fontTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                
                document.add(new Paragraph("\n"));
                
                document.add(new Paragraph("Số Hóa Đơn: " + maHoaDon, fontBold));
                document.add(new Paragraph("Ngày lập: " + java.time.LocalDate.now(), fontNormal));
                document.add(new Paragraph("Khách hàng: " + tenBenhNhanStr, fontNormal));
                document.add(new Paragraph("\n----------------------------------------------------------\n"));

                document.add(new Paragraph("I. DỊCH VỤ / CẬN LÂM SÀNG", fontBold));
                document.add(new Paragraph("\n")); 
                
                PdfPTable tableDV = new PdfPTable(3); 
                tableDV.setWidthPercentage(100);
                tableDV.setWidths(new float[]{5, 2, 3}); 

                addCellToTable(tableDV, "Tên dịch vụ", fontBold);
                addCellToTable(tableDV, "SL", fontBold);
                addCellToTable(tableDV, "Thành tiền", fontBold);

                for (int i = 0; i < modelDichVu.getRowCount(); i++) {
                    addCellToTable(tableDV, modelDichVu.getValueAt(i, 0).toString(), fontNormal);
                    addCellToTable(tableDV, modelDichVu.getValueAt(i, 1).toString(), fontNormal);
                    addCellToTable(tableDV, modelDichVu.getValueAt(i, 2).toString(), fontNormal);
                }
                document.add(tableDV);
                
                Paragraph pSumDV = new Paragraph("Tổng dịch vụ: " + lblTongTienDichVu.getText(), fontNormal);
                pSumDV.setAlignment(Element.ALIGN_RIGHT);
                document.add(pSumDV);

                document.add(new Paragraph("\n"));

                document.add(new Paragraph("II. CHI TIẾT ĐƠN THUỐC", fontBold));
                document.add(new Paragraph("\n")); 
                
                PdfPTable tableT = new PdfPTable(4); 
                tableT.setWidthPercentage(100);
                tableT.setWidths(new float[]{4, 1, 2, 3});

                addCellToTable(tableT, "Tên thuốc", fontBold);
                addCellToTable(tableT, "SL", fontBold);
                addCellToTable(tableT, "Đơn giá", fontBold);
                addCellToTable(tableT, "Thành tiền", fontBold);

                for (int i = 0; i < modelThuoc.getRowCount(); i++) {
                    addCellToTable(tableT, modelThuoc.getValueAt(i, 0).toString(), fontNormal);
                    addCellToTable(tableT, modelThuoc.getValueAt(i, 1).toString(), fontNormal);
                    addCellToTable(tableT, modelThuoc.getValueAt(i, 2).toString(), fontNormal);
                    addCellToTable(tableT, modelThuoc.getValueAt(i, 3).toString(), fontNormal);
                }
                document.add(tableT);
                
                Paragraph pSumT = new Paragraph("Tổng thuốc: " + lblTongTienThuoc.getText(), fontNormal);
                pSumT.setAlignment(Element.ALIGN_RIGHT);
                document.add(pSumT);

                document.add(new Paragraph("\n----------------------------------------------------------\n"));

                Paragraph pTotal = new Paragraph("TỔNG CỘNG THANH TOÁN: " + lblTongCong.getText(), fontBold);
                pTotal.setAlignment(Element.ALIGN_RIGHT);
                document.add(pTotal);
                
                document.add(new Paragraph("\n(Cảm ơn quý khách và hẹn gặp lại!)", fontNormal));
                
                document.add(new Paragraph("\n\n"));
                Paragraph pSign = new Paragraph("Thu Ngân\n(Ký tên)", fontBold);
                pSign.setAlignment(Element.ALIGN_RIGHT);
                pSign.setIndentationRight(50);
                document.add(pSign);

                document.close();
                
                JOptionPane.showMessageDialog(this, "Xuất Hóa Đơn PDF thành công!\n" + filePath);
                
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
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    private void styleTable(JTable table, Font font, Color headerColor) {
        table.setRowHeight(30); 
        table.setFont(font);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowVerticalLines(false); 
        table.setSelectionBackground(new Color(232, 240, 254));
        table.setSelectionForeground(Color.BLACK);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(headerColor);
        header.setForeground(new Color(70, 70, 70));
        header.setPreferredSize(new java.awt.Dimension(0, 35));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        
        int lastCol = table.getColumnCount() - 1;
        table.getColumnModel().getColumn(lastCol).setCellRenderer(rightRenderer);
        
        if(table.getColumnCount() > 2) {
             table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        }
    }
    
    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }
}