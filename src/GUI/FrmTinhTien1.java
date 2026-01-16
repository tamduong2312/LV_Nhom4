package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import BUS.HoaDonBus;
import DAO.TinhTienDao;
import MODEL.CTHoaDon;
import MODEL.CTToaThuoc;
import MODEL.DichVu;
import MODEL.Session;

import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import BUS.PhieuKhamBus;
import BUS.ToaThuocBus;

public class FrmTinhTien1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tableDichVu, tableThuoc;
    private DefaultTableModel modelDichVu, modelThuoc;
    private JLabel lblTenBenhNhan, lblTongTienDichVu, lblTongTienThuoc, lblTongCong;
    private JButton btnQuayLai, btnInHoaDon, btnXacNhan, btnXoaDV, btnXoaThuoc;

    private int maHoaDon, maBenhNhan;
    private int mapk;
    private String tenBenhNhanStr, check;
    private DecimalFormat df = new DecimalFormat("#,###");
    private double tongTienCuoiCung = 0;

    private TinhTienDao tinhTienDao = new TinhTienDao();
    private HoaDonBus hoaDonBus = new HoaDonBus();

    private List<CTHoaDon> listDichVuHienTai = new ArrayList<>();
    private List<CTHoaDon> listThuocHienTai = new ArrayList<>();

    public FrmTinhTien1(int maPK, String tenbn, int mahd, int mabn, String check) {
        this.tenBenhNhanStr = tenbn;
        this.maHoaDon = mahd;
        this.mapk = maPK;
        
        this.maBenhNhan = mabn;
        this.check = check;

        setLayout(null);
        setBackground(Color.WHITE);
        setSize(850, 700);

        initUI(); 
        loadData(); 
        initEvents();
    }

    private void initUI() {
        Font fontHeader = new Font("Segoe UI", Font.BOLD, 22);
        Font fontSection = new Font("Segoe UI", Font.BOLD, 14);
        Color colPrimary = new Color(0, 102, 204);

        JLabel lblTitle = new JLabel("THANH TOÁN TỔNG HỢP TRONG NGÀY");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(colPrimary);
        lblTitle.setFont(fontHeader);
        lblTitle.setBounds(0, 20, 850, 40);
        add(lblTitle);

        lblTenBenhNhan = new JLabel("Bệnh nhân: " + tenBenhNhanStr + " (Mã BN: " + maBenhNhan + ")");
        lblTenBenhNhan.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenBenhNhan.setBounds(40, 70, 600, 25);
        add(lblTenBenhNhan);

  
        JLabel lblHeaderDV = new JLabel("I. DANH SÁCH DỊCH VỤ / CẬN LÂM SÀNG");
        lblHeaderDV.setFont(fontSection);
        lblHeaderDV.setBounds(40, 110, 300, 25);
        add(lblHeaderDV);

        btnXoaDV = new JButton("Xóa dịch vụ");
        btnXoaDV.setBounds(324, 110, 120, 25);
        styleButton(btnXoaDV, new Color(220, 53, 69));
        add(btnXoaDV);

        JScrollPane spDV = new JScrollPane();
        spDV.setBounds(40, 140, 770, 150);
        add(spDV);
        modelDichVu = new DefaultTableModel(new Object[][]{}, new String[]{"Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableDichVu = new JTable(modelDichVu);
        styleTable(tableDichVu);
        spDV.setViewportView(tableDichVu);

    
        JLabel lblHeaderThuoc = new JLabel("II. DANH SÁCH THUỐC ĐÃ KÊ");
        lblHeaderThuoc.setFont(fontSection);
        lblHeaderThuoc.setBounds(40, 310, 300, 25);
        add(lblHeaderThuoc);

        btnXoaThuoc = new JButton("Xóa thuốc");
        btnXoaThuoc.setBounds(254, 312, 120, 25);
        styleButton(btnXoaThuoc, new Color(220, 53, 69));
        add(btnXoaThuoc);

        JScrollPane spThuoc = new JScrollPane();
        spThuoc.setBounds(40, 340, 770, 150);
        add(spThuoc);
        modelThuoc = new DefaultTableModel(new Object[][]{}, new String[]{"Tên thuốc", "Số lượng", "Đơn giá", "Thành tiền"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableThuoc = new JTable(modelThuoc);
        styleTable(tableThuoc);
        spThuoc.setViewportView(tableThuoc);


        int startY = 510;
        lblTongTienDichVu = createSumLabel("Tổng tiền dịch vụ: 0 VNĐ", startY);
        lblTongTienThuoc = createSumLabel("Tổng tiền thuốc: 0 VNĐ", startY + 30);
        lblTongCong = createSumLabel("TỔNG CỘNG: 0 VNĐ", startY + 60);
        lblTongCong.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongCong.setForeground(Color.RED);


        btnQuayLai = new JButton("Quay lại");
        btnQuayLai.setBounds(40, 620, 120, 40);
        styleButton(btnQuayLai, Color.GRAY);
        add(btnQuayLai);

        btnInHoaDon = new JButton("In hóa đơn PDF");
        btnInHoaDon.setBounds(430, 620, 180, 40);
        styleButton(btnInHoaDon, new Color(23, 162, 184));
        add(btnInHoaDon);

        btnXacNhan = new JButton("Xác nhận thanh toán");
        btnXacNhan.setBounds(630, 620, 180, 40);
        styleButton(btnXacNhan, new Color(40, 167, 69));
        if ("check".equals(check)) {
            btnXacNhan.setText("Đã thanh toán");
            btnXacNhan.setEnabled(false);
        }
        else if("Đã hoàn tiền".equals(check)) {
        	   btnXacNhan.setText("Đã hoàn tiền");
               btnXacNhan.setEnabled(false);
        	
        }
        else if("cần hoàn tiền".equals(check)) {
        	  btnXacNhan.setText("Xác nhận hoàn tiền");
        	  btnXacNhan.setBackground(new Color(255, 152, 0));
              btnXacNhan.setEnabled(true);
        }
        
        else if("Đã thanh toán".equals(check)) {
      	  btnXacNhan.setText("Đã thanh toán");
      	  btnXacNhan.setBackground(new Color(255, 152, 0));
            btnXacNhan.setEnabled(false);
      }
        add(btnXacNhan);
    }

    private void loadData() {
        if (maBenhNhan <= 0) return;
        modelDichVu.setRowCount(0);
        modelThuoc.setRowCount(0);
        listDichVuHienTai.clear();
        listThuocHienTai.clear();

        double sumDV = 0, sumT = 0;

        if ("check".equals(this.check) || "cần hoàn tiền".equals(this.check)) {

            List<CTHoaDon> items = hoaDonBus.getChiTietByMaHD(this.maHoaDon);
            for (CTHoaDon ct : items) {
                if ("DICH_VU".equals(ct.getLoaiMuc())) {
                    listDichVuHienTai.add(ct);
                    sumDV += ct.getThanhTien();
                    modelDichVu.addRow(new Object[]{ct.getNoiDung(), ct.getSoLuong(), df.format(ct.getDonGia()), df.format(ct.getThanhTien())});
                } else {
                    listThuocHienTai.add(ct);
                    sumT += ct.getThanhTien();
                    modelThuoc.addRow(new Object[]{ct.getNoiDung(), ct.getSoLuong(), df.format(ct.getDonGia()), df.format(ct.getThanhTien())});
                }
            }
        } else {
      
            List<DichVu> dsDV = tinhTienDao.getListDichVuSuDungTheoBN(this.maBenhNhan,  Session.maphieukham);
            for (DichVu dv : dsDV) {
                CTHoaDon item = new CTHoaDon(0, maHoaDon, dv.getTenDichVu(), "DICH_VU", dv.getMaDichVu(), 1, (double)dv.getDonGia(), (double)dv.getDonGia());
                listDichVuHienTai.add(item);
                sumDV += item.getThanhTien();
                modelDichVu.addRow(new Object[]{item.getNoiDung(), 1, df.format(item.getDonGia()), df.format(item.getThanhTien())});
            }
            List<CTToaThuoc> dsT = tinhTienDao.getListThuocSuDungTheoBN(Session.maphieukham);
            for (CTToaThuoc t : dsT) {
                double tt = t.getSoNgay() * t.getDonGia();
                CTHoaDon item = new CTHoaDon(0, maHoaDon, t.getTenThuoc(), "THUOC", t.getId(), t.getSoNgay(), (double)t.getDonGia(), tt);
                listThuocHienTai.add(item);
                sumT += tt;
                modelThuoc.addRow(new Object[]{item.getNoiDung(), item.getSoLuong(), df.format(item.getDonGia()), df.format(item.getThanhTien())});
            }
        }

        tongTienCuoiCung = sumDV + sumT;
        lblTongTienDichVu.setText(df.format(sumDV) + " VNĐ");
        lblTongTienThuoc.setText(df.format(sumT) + " VNĐ");
        lblTongCong.setText(df.format(tongTienCuoiCung) + " VNĐ");
    }

    private void initEvents() {
        btnQuayLai.addActionListener(e -> closeWindow());
        btnInHoaDon.addActionListener(e -> xuatHoaDonPDF());
        btnXacNhan.addActionListener(e -> xuLyThanhToan());

        // XỬ LÝ XÓA / HOÀN TIỀN DỊCH VỤ
        btnXoaDV.addActionListener(e -> {
            int row = tableDichVu.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!"); return; }
            if (JOptionPane.showConfirmDialog(this, "Xác nhận xóa/hoàn tiền?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                CTHoaDon item = listDichVuHienTai.get(row);
                if ("check".equals(check)) 
                	hoaDonBus.xoaChiTietHoaDon(maHoaDon, item.getIdGoc(), "DICH_VU");
                listDichVuHienTai.remove(row);
                lamMoiGiaoDienSauXoa();
            }
        });

        // XỬ LÝ XÓA / HOÀN TIỀN THUỐC
        btnXoaThuoc.addActionListener(e -> {
        	   
            int rowSelected = tableThuoc.getSelectedRow(); 
            if (rowSelected == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần xóa!");
                return;
            }

            if (JOptionPane.showConfirmDialog(this, "Xác nhận hoàn tiền thuốc này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
    
                CTHoaDon item = listThuocHienTai.get(rowSelected);
                
                String lyDoNguoiDungNhap = hienThiHopThoaiNhapLyDo(item.getNoiDung());
                if (lyDoNguoiDungNhap == null) return;
                int idCuaBangChiTietToa = item.getIdGoc(); 

       
                if (this.maHoaDon > 0) {
          
                    hoaDonBus.xoaChiTietHoaDon(this.maHoaDon, idCuaBangChiTietToa, "THUOC");
                }

  

                ToaThuocBus toaBus = new ToaThuocBus();
                String thongTinGhiChu = "Hoàn trả: " + item.getNoiDung() + " | Lý do: " + lyDoNguoiDungNhap;
                toaBus.capNhatGhiChuToaThuoc(Session.maphieukham, thongTinGhiChu);
                
                
                

                toaBus.updateTrangThaiToaThuoc(Session.maphieukham , "HỦY CẤP THUỐC");
                //toaBus.xoaThuoc(idCuaBangChiTietToa);

   
                listThuocHienTai.remove(rowSelected);
                lamMoiGiaoDienSauXoa();
                
                JOptionPane.showMessageDialog(this, "Đã xóa thuốc khỏi hệ thống và hoàn tiền thành công!");
            }
        });
    }
    private void lamMoiGiaoDienSauXoa() {
        modelDichVu.setRowCount(0);
        modelThuoc.setRowCount(0);
        double sDV = 0, sT = 0;
        for (CTHoaDon c : listDichVuHienTai) {
            sDV += c.getThanhTien();
            modelDichVu.addRow(new Object[]{c.getNoiDung(), c.getSoLuong(), df.format(c.getDonGia()), df.format(c.getThanhTien())});
        }
        for (CTHoaDon c : listThuocHienTai) {
            sT += c.getThanhTien();
            modelThuoc.addRow(new Object[]{c.getNoiDung(), c.getSoLuong(), df.format(c.getDonGia()), df.format(c.getThanhTien())});
        }
        tongTienCuoiCung = sDV + sT;
        if ("check".equals(check)) hoaDonBus.capNhatTongTien(maHoaDon, tongTienCuoiCung);
        lblTongTienDichVu.setText(df.format(sDV) + " VNĐ");
        lblTongTienThuoc.setText(df.format(sT) + " VNĐ");
        lblTongCong.setText(df.format(tongTienCuoiCung) + " VNĐ");
    }

    private void xuLyThanhToan() {
    	if("cần hoàn tiền".equals(this.check)) {
    		try {
        		hoaDonBus.updateTrangThaiByMaHD(maHoaDon);
        	      JOptionPane.showMessageDialog(this, "Xác nhận hoàn tiền thành công !");
        	      btnXacNhan.setEnabled(false);
			} catch (Exception e) {
				e.printStackTrace();
			}

    		
    	}
    	else {
        if (JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán " + df.format(tongTienCuoiCung) + " VNĐ?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
            
                for (CTHoaDon ct : listDichVuHienTai) hoaDonBus.themChiTietHoaDon(ct);
                for (CTHoaDon ct : listThuocHienTai) hoaDonBus.themChiTietHoaDon(ct);

                hoaDonBus.capNhatTrangThai(maHoaDon, "đã thanh toán", tongTienCuoiCung);
  
                hoaDonBus.thanhToanTatCaPhieuCuaBNTrongNgay(maBenhNhan);
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                closeWindow();
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    	}
    }


    private void styleButton(JButton b, Color bg) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 13)); b.setBackground(bg); b.setForeground(Color.WHITE);
        b.setFocusPainted(false); b.setBorderPainted(false); b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    private void styleTable(JTable t) {
        t.setRowHeight(30); t.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        DefaultTableCellRenderer c = new DefaultTableCellRenderer(); c.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<t.getColumnCount(); i++) t.getColumnModel().getColumn(i).setCellRenderer(c);
    }
    private JLabel createSumLabel(String txt, int y) {
        JLabel l = new JLabel(txt); l.setBounds(450, y, 350, 25); l.setHorizontalAlignment(SwingConstants.RIGHT);
        add(l); return l;
    }
    private void closeWindow() {
        Window win = SwingUtilities.getWindowAncestor(this);
        new FrmThuNgan().setVisible(true);
        if (win != null) win.dispose();
    }

    private void xuatHoaDonPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("HoaDon_" + maHoaDon + ".pdf"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();
                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font fTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fBold = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fNormal = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.NORMAL);

                document.add(new Paragraph("PHÒNG KHÁM ĐA KHOA", fBold));
                Paragraph title = new Paragraph("HÓA ĐƠN CHI TIẾT", fTitle);
                title.setAlignment(Element.ALIGN_CENTER); document.add(title);
                document.add(new Paragraph("Bệnh nhân: " + tenBenhNhanStr + " (Mã: " + maBenhNhan + ")", fNormal));
                document.add(new Paragraph("Mã hóa đơn: " + maHoaDon + " - Ngày: " + LocalDate.now(), fNormal));
                document.add(new Paragraph("-----------------------------------------------------------------------"));

                PdfPTable pdfTable = new PdfPTable(4);
                pdfTable.setWidthPercentage(100);
                pdfTable.addCell(new Phrase("Nội dung", fBold));
                pdfTable.addCell(new Phrase("SL", fBold));
                pdfTable.addCell(new Phrase("Đơn giá", fBold));
                pdfTable.addCell(new Phrase("Thành tiền", fBold));

                for (CTHoaDon ct : listDichVuHienTai) addRowToPdf(pdfTable, ct, fNormal);
                for (CTHoaDon ct : listThuocHienTai) addRowToPdf(pdfTable, ct, fNormal);

                document.add(pdfTable);
                document.add(new Paragraph("\nTỔNG CỘNG: " + lblTongCong.getText(), fTitle));
                document.close();
                Desktop.getDesktop().open(new File(filePath));
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
    
    private String hienThiHopThoaiNhapLyDo(String tenThuoc) {

        JTextArea textArea = new JTextArea(4, 25);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel("Nhập lý do hoàn trả cho thuốc [" + tenThuoc + "]:"), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);


        int result = JOptionPane.showConfirmDialog(this, panel, "Xác nhận hoàn tiền", 
                                                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String lyDo = textArea.getText().trim();
            if (lyDo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập lý do để tiếp tục!");
                return hienThiHopThoaiNhapLyDo(tenThuoc); 
            }
            return lyDo;
        }
        return null; 
    }
    private void addRowToPdf(PdfPTable t, CTHoaDon ct, com.itextpdf.text.Font f) {
        t.addCell(new Phrase(ct.getNoiDung(), f));
        t.addCell(new Phrase(String.valueOf(ct.getSoLuong()), f));
        t.addCell(new Phrase(df.format(ct.getDonGia()), f));
        t.addCell(new Phrase(df.format(ct.getThanhTien()), f));
    }
}