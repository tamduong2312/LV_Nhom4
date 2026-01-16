package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import BUS.XetNghiemBus;
import MODEL.KetQuaXetNghiem;
import MODEL.Session;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PnlNhapKQXetNghiem extends JFrame {

    private static final long serialVersionUID = 1L;
    private XetNghiemBus bus = new XetNghiemBus();
    private int idChiTiet;

    private JTextField txtHongCau, txtBachCau, txtTieuCau, txtHuyetSacTo, txtHCT;
    private JTextField txtNhomMau, txtTocDoLang, txtDongMauCoBan;
    private JTextField txtGlucose, txtHbA1c, txtUre, txtCreatinine;
    private JTextField txtAST, txtALT, txtGGT, txtBili;
    private JTextField txtCholesterol, txtTri, txtHDL, txtLDL, txtAcidUric;
    private JTextField txtTyTrong, txtPH, txtLeu, txtEry;
    private JTextField txtPro, txtGlu, txtNit, txtKet;
    private JTextField txtHBsAg, txtHCV, txtHIV, txtTPHA;
    private JTextField txtCRP, txtRF;

    private JTextArea txtKetLuan, txtGhiChu;
    private static String tenbn;
    private int mactchidinh;

    public PnlNhapKQXetNghiem(int idChiTietChiDinh, String tenbn) {
        this.idChiTiet = idChiTietChiDinh;
        this.tenbn = tenbn;
        XetNghiemBus busXN = new XetNghiemBus();
        
        JOptionPane.showMessageDialog(null, idChiTietChiDinh);
        mactchidinh = busXN.layIdChiTietByMaPhieuKham(Session.maDangKyHienTai);
        JOptionPane.showMessageDialog(null, mactchidinh);

        setTitle("NHẬP KẾT QUẢ XÉT NGHIỆM - BN: " + this.tenbn);
        setSize(1000, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblHeader = new JLabel("PHIẾU KẾT QUẢ CẬN LÂM SÀNG", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblHeader.setForeground(new Color(0, 102, 204));
        lblHeader.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Tab 1: Huyết Học
        JPanel p1 = new JPanel(new GridLayout(4, 4, 15, 15));
        p1.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p1, "Hồng cầu (RBC) [T/L]:", txtHongCau = new JTextField());
        addInput(p1, "Bạch cầu (WBC) [G/L]:", txtBachCau = new JTextField());
        addInput(p1, "Tiểu cầu (PLT) [G/L]:", txtTieuCau = new JTextField());
        addInput(p1, "Huyết sắc tố (HGB) [g/dL]:", txtHuyetSacTo = new JTextField());
        addInput(p1, "Hematocrit (HCT) [%]:", txtHCT = new JTextField());
        addInput(p1, "Nhóm máu (ABO/Rh):", txtNhomMau = new JTextField());
        addInput(p1, "Máu lắng (VS) [mm/h]:", txtTocDoLang = new JTextField());
        addInput(p1, "Đông máu cơ bản:", txtDongMauCoBan = new JTextField());
        tabs.addTab("1. Huyết Học", p1);

        // Tab 2: Sinh Hóa
        JPanel p2 = new JPanel(new GridLayout(5, 4, 15, 15));
        p2.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p2, "Glucose [mmol/L]:", txtGlucose = new JTextField());
        addInput(p2, "HbA1c [%]:", txtHbA1c = new JTextField());
        addInput(p2, "Ure [mmol/L]:", txtUre = new JTextField());
        addInput(p2, "Creatinine [µmol/L]:", txtCreatinine = new JTextField());
        addInput(p2, "AST (GOT) [U/L]:", txtAST = new JTextField());
        addInput(p2, "ALT (GPT) [U/L]:", txtALT = new JTextField());
        addInput(p2, "GGT [U/L]:", txtGGT = new JTextField());
        addInput(p2, "Bilirubin TP [µmol/L]:", txtBili = new JTextField());
        addInput(p2, "Cholesterol TP [mmol/L]:", txtCholesterol = new JTextField());
        addInput(p2, "Triglyceride [mmol/L]:", txtTri = new JTextField());
        addInput(p2, "HDL-C [mmol/L]:", txtHDL = new JTextField());
        addInput(p2, "LDL-C [mmol/L]:", txtLDL = new JTextField());
        addInput(p2, "Acid Uric [µmol/L]:", txtAcidUric = new JTextField());
        tabs.addTab("2. Sinh Hóa", p2);

        // Tab 3: Nước Tiểu
        JPanel p3 = new JPanel(new GridLayout(4, 4, 15, 15));
        p3.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p3, "Tỷ trọng (SG):", txtTyTrong = new JTextField());
        addInput(p3, "pH:", txtPH = new JTextField());
        addInput(p3, "Bạch cầu (LEU):", txtLeu = new JTextField());
        addInput(p3, "Hồng cầu (ERY):", txtEry = new JTextField());
        addInput(p3, "Protein (PRO):", txtPro = new JTextField());
        addInput(p3, "Đường (GLU):", txtGlu = new JTextField());
        addInput(p3, "Nitrit (NIT):", txtNit = new JTextField());
        addInput(p3, "Ketone (KET):", txtKet = new JTextField());
        tabs.addTab("3. Nước Tiểu", p3);

        // Tab 4: Miễn Dịch
        JPanel p4 = new JPanel(new GridLayout(4, 4, 15, 15));
        p4.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p4, "HBsAg (Viêm gan B):", txtHBsAg = new JTextField());
        addInput(p4, "Anti-HCV (Viêm gan C):", txtHCV = new JTextField());
        addInput(p4, "HIV Ab:", txtHIV = new JTextField());
        addInput(p4, "TPHA (Giang mai):", txtTPHA = new JTextField());
        addInput(p4, "CRP định lượng:", txtCRP = new JTextField());
        addInput(p4, "RF định lượng:", txtRF = new JTextField());
        tabs.addTab("4. Miễn Dịch", p4);

        add(tabs, BorderLayout.CENTER);

        // --- BOTTOM AREA ---
        JPanel pnlBottom = new JPanel(new BorderLayout(10, 10));
        pnlBottom.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY), new EmptyBorder(10, 15, 15, 15)));
        pnlBottom.setPreferredSize(new Dimension(0, 250));

        JPanel pnlText = new JPanel(new GridLayout(1, 2, 15, 0));
        txtKetLuan = new JTextArea();
        txtKetLuan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrKetLuan = new JScrollPane(txtKetLuan);
        scrKetLuan.setBorder(BorderFactory.createTitledBorder(null, "KẾT LUẬN (Bắt buộc)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", Font.BOLD, 12), Color.RED));
        pnlText.add(scrKetLuan);

        txtGhiChu = new JTextArea();
        txtGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrGhiChu = new JScrollPane(txtGhiChu);
        scrGhiChu.setBorder(BorderFactory.createTitledBorder(null, "Ghi chú thêm / Đề nghị", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", Font.BOLD, 12), Color.BLACK));
        pnlText.add(scrGhiChu);
        pnlBottom.add(pnlText, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new GridLayout(1, 2, 20, 0));
        pnlButtons.setPreferredSize(new Dimension(0, 50));

        JButton btnPrint = new JButton("IN PHIẾU XÉT NGHIỆM (PDF)");
        styleButton(btnPrint, new Color(255, 193, 7), Color.BLACK);
        btnPrint.addActionListener(e -> xuatPhieuXetNghiemPDF());

        JButton btnLuu = new JButton("XÁC NHẬN & LƯU KẾT QUẢ");
        styleButton(btnLuu, new Color(0, 123, 255), Color.WHITE);
        btnLuu.addActionListener(e -> save());

        pnlButtons.add(btnPrint);
        pnlButtons.add(btnLuu);
        pnlBottom.add(pnlButtons, BorderLayout.SOUTH);

        add(pnlBottom, BorderLayout.SOUTH);

        loadData();
    }

    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
    }

    private void addInput(JPanel p, String title, JTextField txt) {
        JPanel pp = new JPanel(new BorderLayout(0, 5));
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        pp.add(lbl, BorderLayout.NORTH);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(5, 5, 5, 5)));
        pp.add(txt, BorderLayout.CENTER);
        p.add(pp);
    }

    private void xuatPhieuXetNghiemPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Kết Quả Xét Nghiệm");
        fileChooser.setSelectedFile(new File("XN_" + tenbn.replace(" ", "_") + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) filePath += ".pdf";

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font fTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fBold = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fNorm = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.NORMAL);

                // Header
                Paragraph pClinic = new Paragraph("PHÒNG KHÁM ĐA KHOA \nĐịa chỉ: TP.HCM - Hotline: 1900.xxxx", fBold);
                pClinic.setAlignment(Element.ALIGN_CENTER);
                document.add(pClinic);
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("PHIẾU KẾT QUẢ XÉT NGHIỆM TỔNG HỢP", fTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Họ tên bệnh nhân: " + tenbn.toUpperCase(), fBold));
                document.add(new Paragraph("Mã phiếu: " + mactchidinh + " - Ngày: " + java.time.LocalDate.now(), fNorm));
                document.add(new Paragraph("Người thực hiện: " + Session.TenNhanVien, fNorm));
                document.add(new Paragraph("----------------------------------------------------------------------------------\n"));

                // 1. HUYẾT HỌC
                document.add(new Paragraph("I. HUYẾT HỌC", fBold));
                PdfPTable t1 = createTablePDF(fBold);
                addXNRow(t1, "Hồng cầu (RBC)", txtHongCau.getText(), "T/L", fNorm);
                addXNRow(t1, "Bạch cầu (WBC)", txtBachCau.getText(), "G/L", fNorm);
                addXNRow(t1, "Tiểu cầu (PLT)", txtTieuCau.getText(), "G/L", fNorm);
                addXNRow(t1, "Huyết sắc tố (HGB)", txtHuyetSacTo.getText(), "g/dL", fNorm);
                addXNRow(t1, "Hematocrit (HCT)", txtHCT.getText(), "%", fNorm);
                addXNRow(t1, "Nhóm máu", txtNhomMau.getText(), "", fNorm);
                document.add(t1);

                // 2. SINH HÓA
                document.add(new Paragraph("\nII. SINH HÓA MÁU", fBold));
                PdfPTable t2 = createTablePDF(fBold);
                addXNRow(t2, "Glucose", txtGlucose.getText(), "mmol/L", fNorm);
                addXNRow(t2, "HbA1c", txtHbA1c.getText(), "%", fNorm);
                addXNRow(t2, "Ure", txtUre.getText(), "mmol/L", fNorm);
                addXNRow(t2, "Creatinine", txtCreatinine.getText(), "µmol/L", fNorm);
                addXNRow(t2, "Cholesterol", txtCholesterol.getText(), "mmol/L", fNorm);
                addXNRow(t2, "Triglyceride", txtTri.getText(), "mmol/L", fNorm);
                document.add(t2);

                // 3. NƯỚC TIỂU
                document.add(new Paragraph("\nIII. TỔNG PHÂN TÍCH NƯỚC TIỂU", fBold));
                PdfPTable t3 = createTablePDF(fBold);
                addXNRow(t3, "Tỷ trọng (SG)", txtTyTrong.getText(), "", fNorm);
                addXNRow(t3, "pH", txtPH.getText(), "", fNorm);
                addXNRow(t3, "Protein (PRO)", txtPro.getText(), "", fNorm);
                addXNRow(t3, "Đường (GLU)", txtGlu.getText(), "", fNorm);
                document.add(t3);

                // 4. MIỄN DỊCH
                document.add(new Paragraph("\nIV. MIỄN DỊCH / KHÁC", fBold));
                PdfPTable t4 = createTablePDF(fBold);
                addXNRow(t4, "Viêm gan B (HBsAg)", txtHBsAg.getText(), "", fNorm);
                addXNRow(t4, "Viêm gan C (HCV)", txtHCV.getText(), "", fNorm);
                addXNRow(t4, "HIV", txtHIV.getText(), "", fNorm);
                addXNRow(t4, "Giang mai (TPHA)", txtTPHA.getText(), "", fNorm);
                document.add(t4);

                document.add(new Paragraph("\nKẾT LUẬN:", fBold));
                document.add(new Paragraph(txtKetLuan.getText(), fNorm));

                document.add(new Paragraph("\n\n"));
                Paragraph pSign = new Paragraph("Người phê duyệt\n(Ký và ghi rõ họ tên)", fBold);
                pSign.setAlignment(Element.ALIGN_RIGHT);
                pSign.setIndentationRight(50);
                document.add(pSign);

                document.close();
                JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
                Desktop.getDesktop().open(new File(filePath));
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    private PdfPTable createTablePDF(com.itextpdf.text.Font f) throws Exception {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{4, 3, 3});
        table.setSpacingBefore(5f);
        addCell(table, "TÊN XÉT NGHIỆM", f);
        addCell(table, "KẾT QUẢ", f);
        addCell(table, "ĐƠN VỊ", f);
        return table;
    }

    private void addXNRow(PdfPTable t, String name, String val, String unit, com.itextpdf.text.Font f) {
        if (!val.trim().isEmpty()) {
            t.addCell(new Phrase(name, f));
            t.addCell(new Phrase(val, f));
            t.addCell(new Phrase(unit, f));
        }
    }

    private void addCell(PdfPTable table, String text, com.itextpdf.text.Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
    }

    private void loadData() {
        KetQuaXetNghiem kq = bus.getKetQua(mactchidinh);
        if (kq != null) {
            setText(txtHongCau, kq.getHongCau());
            setText(txtBachCau, kq.getBachCau());
            setText(txtTieuCau, kq.getTieuCau());
            setText(txtHuyetSacTo, kq.getHuyetSacTo());
            setText(txtHCT, kq.getHematocrit());
            txtNhomMau.setText(kq.getNhomMau());
            setText(txtTocDoLang, kq.getTocDoMauLang());
            txtDongMauCoBan.setText(kq.getDongMauCoBan());
            setText(txtGlucose, kq.getGlucose());
            setText(txtHbA1c, kq.getHbA1c());
            setText(txtUre, kq.getUre());
            setText(txtCreatinine, kq.getCreatinine());
            setText(txtAST, kq.getAstGot());
            setText(txtALT, kq.getAltGpt());
            setText(txtGGT, kq.getGgt());
            setText(txtBili, kq.getBilirubinTp());
            setText(txtCholesterol, kq.getCholesterolTp());
            setText(txtTri, kq.getTriglyceride());
            setText(txtHDL, kq.getHdlC());
            setText(txtLDL, kq.getLdlC());
            setText(txtAcidUric, kq.getAcidUric());
            setText(txtTyTrong, kq.getTyTrong());
            setText(txtPH, kq.getPh());
            txtLeu.setText(kq.getBachCauNuocTieu());
            txtEry.setText(kq.getHongCauNuocTieu());
            txtPro.setText(kq.getProteinNuocTieu());
            txtGlu.setText(kq.getDuongNuocTieu());
            txtNit.setText(kq.getNitrit());
            txtKet.setText(kq.getKetone());
            txtHBsAg.setText(kq.getHbsag());
            txtHCV.setText(kq.getHcvAb());
            txtHIV.setText(kq.getHivAb());
            txtTPHA.setText(kq.getTphaSyphilis());
            setText(txtCRP, kq.getCrpDinhLuong());
            setText(txtRF, kq.getRfDinhLuong());
            txtKetLuan.setText(kq.getKetLuan());
            txtGhiChu.setText(kq.getGhiChuThem());
        }
    }

    private void save() {
        if (txtKetLuan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập KẾT LUẬN!"); return;
        }
        try {
            KetQuaXetNghiem kq = new KetQuaXetNghiem();
            kq.setMaChiTietChiDinh(mactchidinh);
            kq.setHongCau(parseFloat(txtHongCau.getText()));
            kq.setBachCau(parseFloat(txtBachCau.getText()));
            kq.setTieuCau(parseFloat(txtTieuCau.getText()));
            kq.setHuyetSacTo(parseFloat(txtHuyetSacTo.getText()));
            kq.setHematocrit(parseFloat(txtHCT.getText()));
            kq.setNhomMau(txtNhomMau.getText());
            kq.setTocDoMauLang((int) parseFloat(txtTocDoLang.getText()));
            kq.setDongMauCoBan(txtDongMauCoBan.getText());
            kq.setGlucose(parseFloat(txtGlucose.getText()));
            kq.setHbA1c(parseFloat(txtHbA1c.getText()));
            kq.setUre(parseFloat(txtUre.getText()));
            kq.setCreatinine(parseFloat(txtCreatinine.getText()));
            kq.setAstGot(parseFloat(txtAST.getText()));
            kq.setAltGpt(parseFloat(txtALT.getText()));
            kq.setGgt(parseFloat(txtGGT.getText()));
            kq.setBilirubinTp(parseFloat(txtBili.getText()));
            kq.setCholesterolTp(parseFloat(txtCholesterol.getText()));
            kq.setTriglyceride(parseFloat(txtTri.getText()));
            kq.setHdlC(parseFloat(txtHDL.getText()));
            kq.setLdlC(parseFloat(txtLDL.getText()));
            kq.setAcidUric(parseFloat(txtAcidUric.getText()));
            kq.setTyTrong(parseFloat(txtTyTrong.getText()));
            kq.setPh(parseFloat(txtPH.getText()));
            kq.setBachCauNuocTieu(txtLeu.getText());
            kq.setHongCauNuocTieu(txtEry.getText());
            kq.setProteinNuocTieu(txtPro.getText());
            kq.setDuongNuocTieu(txtGlu.getText());
            kq.setNitrit(txtNit.getText());
            kq.setKetone(txtKet.getText());
            kq.setHbsag(txtHBsAg.getText());
            kq.setHcvAb(txtHCV.getText());
            kq.setHivAb(txtHIV.getText());
            kq.setTphaSyphilis(txtTPHA.getText());
            kq.setCrpDinhLuong(parseFloat(txtCRP.getText()));
            kq.setRfDinhLuong(parseFloat(txtRF.getText()));
            kq.setKetLuan(txtKetLuan.getText());
            kq.setGhiChuThem(txtGhiChu.getText());
            kq.setNguoiThucHien(Session.maNhanVien);

            if (bus.luuKetQua(kq)) {
                JOptionPane.showMessageDialog(this, "Lưu thành công!");
                new PhieuKhamBus().updateTrangThaiKQChiDinhCLS(Session.maphieukham);
                FrmDanhSachChoKhamTroLyCK q = new FrmDanhSachChoKhamTroLyCK();
                q.setVisible(true);
            	DangKyKhamBenhBus bus1 = new DangKyKhamBenhBus();
         	   
        		bus1.updateTrangThaiKQChiDinhCLS(Session.maphieukham);
        		
        		

                
                
        		bus1.capNhatTrangThaiThucHienCLSById(Session.maDangKyHienTai);
        		
                PhieuKhamBus bus2 = new PhieuKhamBus();
                bus2.updateTrangThaiThucHienCLSTrongCTChiDinh(mactchidinh);
         
                dispose();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private float parseFloat(String s) {
        if (s == null || s.trim().isEmpty()) return 0;

        String clean = s.trim().replace(",", ".");
        try { return Float.parseFloat(clean); } catch (Exception e) { return 0; }
    }

    private void setText(JTextField txt, float val) {

        txt.setText(String.valueOf(val));
    }
}