package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

// Import Logic
import BUS.ChiSoTongQuatBus;
import MODEL.ChiSoTongQuat;
import MODEL.Session;

public class PnlKhamRangHamMat extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtTinhTrangChung;   
    private JTextField txtSauRang;        
    private JTextField txtCaoRang;        
    private JTextField txtViemNuou;       
    private JTextField txtKhopCan;         
    private JTextField txtNiemMac;        
    
    private JTextArea txtGhiChu; 
    
    private JTextField txtDoLungLay;       
    private JTextField txtPhucHinhCu;     
    private JTextField txtBenhLyKhac;       
    private String checklichsu;
    
    private JButton btnLuu;   
    private JButton btnInPhieu;
    
    private int maPhieuKham;
    private ChiSoTongQuatBus bus = new ChiSoTongQuatBus();

    public PnlKhamRangHamMat(int maphieu,String checklichsu) { 
        this.maPhieuKham = maphieu;
this.checklichsu = checklichsu;
        setLayout(null);
        setBackground(new Color(245, 248, 250)); 
        setSize(1000, 750);

        Font fontHeader = new Font("Segoe UI", Font.BOLD, 22);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);
        Color colPrimary = new Color(0, 102, 204); 
        Color colText = new Color(70, 70, 70);

        JLabel lblTitle = new JLabel("PHIẾU KHÁM CHUYÊN KHOA RĂNG HÀM MẶT");
        lblTitle.setBounds(0, 15, 1000, 40);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(fontHeader);
        lblTitle.setForeground(colPrimary);
        add(lblTitle); 

        int col1_X = 40;
        int col2_X = 520;
        int widthField = 440;
        int heightField = 55;
        int startY = 80;
        int gapY = 70;

        // --- Cột 1 ---
        JLabel lblTinhTrang = new JLabel("Tình trạng chung:"); 
        lblTinhTrang.setBounds(col1_X, startY, 200, 20);
        styleLabel(lblTinhTrang, fontLabel, colText);
        add(lblTinhTrang);
        
        txtTinhTrangChung = new JTextField();
        txtTinhTrangChung.setBounds(col1_X, startY + 20, widthField, heightField);
        styleTextField(txtTinhTrangChung, fontInput, "Mô tả tổng quát (Vd: Răng ố vàng, mọc lệch...)");
        add(txtTinhTrangChung);

        int y2 = startY + gapY;
        JLabel lblSauRang = new JLabel("Sâu răng:");
        lblSauRang.setBounds(col1_X, y2, 200, 20);
        styleLabel(lblSauRang, fontLabel, colText);
        add(lblSauRang);
        
        txtSauRang = new JTextField();
        txtSauRang.setBounds(col1_X, y2 + 20, widthField, heightField);
        styleTextField(txtSauRang, fontInput, "Vị trí răng sâu, mức độ (S1, S2, S3)");
        add(txtSauRang);
        
        int y3 = y2 + gapY;
        JLabel lblCaoRang = new JLabel("Cao răng:");
        lblCaoRang.setBounds(col1_X, y3, 200, 20);
        styleLabel(lblCaoRang, fontLabel, colText);
        add(lblCaoRang);
        
        txtCaoRang = new JTextField();
        txtCaoRang.setBounds(col1_X, y3 + 20, widthField, heightField);
        styleTextField(txtCaoRang, fontInput, "Mức độ (Độ 1, 2, 3), trên nướu/dưới nướu");
        add(txtCaoRang);
        
        int y4 = y3 + gapY;
        JLabel lblKhopCan = new JLabel("Khớp cắn:");
        lblKhopCan.setBounds(col1_X, y4, 200, 20);
        styleLabel(lblKhopCan, fontLabel, colText);
        add(lblKhopCan);
        
        txtKhopCan = new JTextField();
        txtKhopCan.setBounds(col1_X, y4 + 20, widthField, heightField);
        styleTextField(txtKhopCan, fontInput, "Hạng I, II, III / Cắn chéo / Cắn hở");
        add(txtKhopCan);
        
        int y5 = y4 + gapY;
        JLabel lblPhucHinh = new JLabel("Phục hình cũ:");
        lblPhucHinh.setBounds(col1_X, y5, 200, 20);
        styleLabel(lblPhucHinh, fontLabel, colText);
        add(lblPhucHinh);
        
        txtPhucHinhCu = new JTextField();
        txtPhucHinhCu.setBounds(col1_X, y5 + 20, widthField, heightField);
        styleTextField(txtPhucHinhCu, fontInput, "Cầu răng, Mão sứ, Hàm tháo lắp...");
        add(txtPhucHinhCu);

        // --- Cột 2 ---
        JLabel lblViemNuou = new JLabel("Tình trạng nướu:");
        lblViemNuou.setBounds(col2_X, startY, 200, 20);
        styleLabel(lblViemNuou, fontLabel, colText);
        add(lblViemNuou);
        
        txtViemNuou = new JTextField();
        txtViemNuou.setBounds(col2_X, startY + 20, widthField, heightField);
        styleTextField(txtViemNuou, fontInput, "Viêm đỏ, sưng nề, chảy máu, túi nha chu");
        add(txtViemNuou);
        
        JLabel lblNiemMac = new JLabel("Niêm mạc miệng:");
        lblNiemMac.setBounds(col2_X, y2, 200, 20);
        styleLabel(lblNiemMac, fontLabel, colText);
        add(lblNiemMac);
        
        txtNiemMac = new JTextField();
        txtNiemMac.setBounds(col2_X, y2 + 20, widthField, heightField);
        styleTextField(txtNiemMac, fontInput, "Lưỡi, má, sàn miệng (Loét, U, Nấm...)");
        add(txtNiemMac);
        
        JLabel lblLungLay = new JLabel("Độ lung lay:");
        lblLungLay.setBounds(col2_X, y3, 200, 20);
        styleLabel(lblLungLay, fontLabel, colText);
        add(lblLungLay);
        
        txtDoLungLay = new JTextField();
        txtDoLungLay.setBounds(col2_X, y3 + 20, widthField, heightField);
        styleTextField(txtDoLungLay, fontInput, "Độ 1, 2, 3, 4 (Ghi rõ vị trí răng)");
        add(txtDoLungLay);

        JLabel lblBenhLyKhac = new JLabel("Bệnh lý khác:");
        lblBenhLyKhac.setBounds(col2_X, y4, 200, 20);
        styleLabel(lblBenhLyKhac, fontLabel, colText);
        add(lblBenhLyKhac);
        
        txtBenhLyKhac = new JTextField();
        txtBenhLyKhac.setBounds(col2_X, y4 + 20, widthField, heightField);
        styleTextField(txtBenhLyKhac, fontInput, "U nang, răng khôn, thắng lưỡi...");
        add(txtBenhLyKhac);

        // --- Ghi chú ---
        int yNote = y5 + gapY; 
        JLabel lblChanDoan = new JLabel("Ghi chú ");
        lblChanDoan.setBounds(col1_X, yNote, 250, 20);
        styleLabel(lblChanDoan, fontLabel, colText);
        add(lblChanDoan);
        
        txtGhiChu = new JTextArea();
        txtGhiChu.setFont(fontInput);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        
        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);
        scrollGhiChu.setBounds(col1_X, yNote + 20, 920, 80);
        scrollGhiChu.setBorder(new LineBorder(new Color(200, 200, 200)));
        add(scrollGhiChu);
        
        // --- Nút bấm ---
        btnLuu = new JButton("LƯU KẾT QUẢ");
        btnLuu.setBounds(290, yNote + 120, 200, 45); 
        styleButton(btnLuu, new Color(40, 167, 69)); 
        add(btnLuu);

        btnInPhieu = new JButton("IN PHIẾU KHÁM RHM");
        btnInPhieu.setBounds(510, yNote + 120, 200, 45);
        styleButton(btnInPhieu, new Color(255, 193, 7));
        btnInPhieu.setForeground(Color.BLACK);
        add(btnInPhieu);
 
        if (!java.beans.Beans.isDesignTime()) {
            loadDataCu();
            initEvents();
        }
        
        if ("check".equals(checklichsu)) {
            setFormReadOnly(this);
        }
    }
    
    private void setFormReadOnly(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setEditable(false);
            } else if (c instanceof JTextArea) {
                ((JTextArea) c).setEditable(false);
            } else if (c instanceof JButton) {
                c.setEnabled(false);
            } else if (c instanceof JComboBox) {
                c.setEnabled(false);
            } else if (c instanceof Container) {
           
                setFormReadOnly((Container) c);
            }
        }
    }

    private void saveData() {
        if (this.maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy phiếu khám!");
            return;
        }

        try {
            List<ChiSoTongQuat> currentList = bus.getallChiSo(this.maPhieuKham);
            ChiSoTongQuat obj = new ChiSoTongQuat();
            obj.setMaPhieuKham(this.maPhieuKham);
            
            obj.setTinhTrangRang(txtTinhTrangChung.getText().trim());
            obj.setSauRang(txtSauRang.getText().trim());
            obj.setCaoRang(txtCaoRang.getText().trim());
            obj.setViemNuou(txtViemNuou.getText().trim());
            obj.setKhopCan(txtKhopCan.getText().trim());
            obj.setNiemMacMieng(txtNiemMac.getText().trim());
            obj.setDoLungLay(txtDoLungLay.getText().trim());
            obj.setPhuHinhCu(txtPhucHinhCu.getText().trim());
            obj.setBenhLyKhacRHM(txtBenhLyKhac.getText().trim());
            obj.setGhiChu(txtGhiChu.getText().trim());

            boolean success;
            if (currentList != null && !currentList.isEmpty()) {
                int currentId = currentList.get(0).getId();
                success = bus.updateRangHamMat(obj, currentId);
            } else {
                success = bus.luuChiSo(obj);
            }
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu kết quả Răng Hàm Mặt thành công!");
                loadDataCu();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void loadDataCu() {
        if (this.maPhieuKham <= 0) return;
        List<ChiSoTongQuat> list = bus.getallChiSo(this.maPhieuKham);
        if (list != null && !list.isEmpty()) {
            ChiSoTongQuat obj = list.get(0);
            txtTinhTrangChung.setText(obj.getTinhTrangRang());
            txtSauRang.setText(obj.getSauRang());
            txtCaoRang.setText(obj.getCaoRang());
            txtViemNuou.setText(obj.getViemNuou());
            txtKhopCan.setText(obj.getKhopCan());
            txtNiemMac.setText(obj.getNiemMacMieng());
            txtDoLungLay.setText(obj.getDoLungLay());
            txtPhucHinhCu.setText(obj.getPhuHinhCu());
            txtBenhLyKhac.setText(obj.getBenhLyKhacRHM());
            txtGhiChu.setText(obj.getGhiChu());
        }
    }

    private void initEvents() {
        btnLuu.addActionListener(e -> saveData());
        btnInPhieu.addActionListener(e -> xuatPhieuRHMPDF());
    }

    private void xuatPhieuRHMPDF() {
        if (maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa xác định được phiếu khám để in!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Phiếu Khám Răng Hàm Mặt");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("PhieuKhamRHM_" + maPhieuKham + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) filePath += ".pdf";

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 20, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);

                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA \nChuyên khoa Răng Hàm Mặt", fontBold);
                pHeader.setAlignment(Element.ALIGN_CENTER);
                document.add(pHeader);
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("PHIẾU KHÁM RĂNG HÀM MẶT", fontTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Mã phiếu khám: " + maPhieuKham, fontBold));
                document.add(new Paragraph("Bác sĩ khám: " + Session.TenNhanVien, fontNormal));
                document.add(new Paragraph("Ngày khám: " + java.time.LocalDate.now(), fontNormal));
                document.add(new Paragraph("----------------------------------------------------------------------------------\n"));

                addSectionPDF(document, "Tình trạng chung:", txtTinhTrangChung.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Sâu răng:", txtSauRang.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Cao răng:", txtCaoRang.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Tình trạng nướu:", txtViemNuou.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Khớp cắn:", txtKhopCan.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Niêm mạc miệng:", txtNiemMac.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Độ lung lay:", txtDoLungLay.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Phục hình cũ:", txtPhucHinhCu.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Bệnh lý khác:", txtBenhLyKhac.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Ghi chú/Kết luận:", txtGhiChu.getText(), fontBold, fontNormal);

                document.add(new Paragraph("\n\n"));
                Paragraph pSign = new Paragraph("Bác sĩ điều trị\n(Ký và ghi rõ họ tên)", fontBold);
                pSign.setAlignment(Element.ALIGN_RIGHT);
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

    private void addSectionPDF(Document doc, String title, String content, com.itextpdf.text.Font fT, com.itextpdf.text.Font fC) throws Exception {
        doc.add(new Paragraph(title, fT));
        doc.add(new Phrase(content.isEmpty() ? "Bình thường/Không có" : content, fC));
        doc.add(new Paragraph(" "));
    }

    private void styleLabel(JLabel lbl, Font font, Color color) {
        lbl.setFont(font); lbl.setForeground(color);
    }

    private void styleTextField(JTextField txt, Font font, String title) {
        txt.setFont(font); txt.setBackground(Color.WHITE);
        Border lineBorder = BorderFactory.createLineBorder(new Color(200, 200, 200));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, title);
        titledBorder.setTitleFont(new Font("Segoe UI", Font.ITALIC, 11)); 
        titledBorder.setTitleColor(new Color(100, 100, 100)); 
        txt.setBorder(titledBorder);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }
}