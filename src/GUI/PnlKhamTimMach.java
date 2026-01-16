package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.ChiSoTongQuatBus;
import BUS.DanhMucBenhLyBus;
import MODEL.ChiSoTongQuat;
import MODEL.DanhMucBenhLy;
import MODEL.Session;

public class PnlKhamTimMach extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtNhipTim;
    private JTextField txtHuyetApTamThu;
    private JTextField txtHuyetApTamTruong;
    private JTextField txtCholesterol;
    private JTextField txtTriglyceride;
    private JTextField txtDuongHuyet;
    private JTextField txtEcg;

    private JTextArea txtGhiChu;

    private JButton btnLuu;
    private JButton btnInPhieu;
    private String checklichsu;
    private int maPhieuKham;
    private ChiSoTongQuatBus bus = new ChiSoTongQuatBus();

    public PnlKhamTimMach(int maphieu, String checklichsuu) {
        this.maPhieuKham = maphieu;
        this.checklichsu = checklichsuu;
        
        setLayout(null);
        setBackground(new Color(245, 248, 250));
        setSize(1000, 750);

        Font fontHeader = new Font("Segoe UI", Font.BOLD, 22);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);
        Color colPrimary = new Color(0, 102, 204);
        Color colText = new Color(70, 70, 70);

        JLabel lblTitle = new JLabel("PHIẾU KHÁM CHUYÊN KHOA TIM MẠCH");
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
        JLabel lblNhipTim = new JLabel("Nhịp tim (BPM):");
        lblNhipTim.setBounds(col1_X, startY, 250, 20);
        styleLabel(lblNhipTim, fontLabel, colText);
        add(lblNhipTim);

        txtNhipTim = new JTextField();
        txtNhipTim.setBounds(col1_X, startY + 20, widthField, heightField);
        styleTextField(txtNhipTim, fontInput, "Nhịp đập/phút (Vd: 75, 80)");
        add(txtNhipTim);

        int y2 = startY + gapY;
        JLabel lblHuyetApThu = new JLabel("Huyết áp tâm thu (mmHg):");
        lblHuyetApThu.setBounds(col1_X, y2, 250, 20);
        styleLabel(lblHuyetApThu, fontLabel, colText);
        add(lblHuyetApThu);

        txtHuyetApTamThu = new JTextField();
        txtHuyetApTamThu.setBounds(col1_X, y2 + 20, widthField, heightField);
        styleTextField(txtHuyetApTamThu, fontInput, "Chỉ số trên (Vd: 120)");
        add(txtHuyetApTamThu);

        int y3 = y2 + gapY;
        JLabel lblHuyetApTruong = new JLabel("Huyết áp tâm trương (mmHg):");
        lblHuyetApTruong.setBounds(col1_X, y3, 250, 20);
        styleLabel(lblHuyetApTruong, fontLabel, colText);
        add(lblHuyetApTruong);

        txtHuyetApTamTruong = new JTextField();
        txtHuyetApTamTruong.setBounds(col1_X, y3 + 20, widthField, heightField);
        styleTextField(txtHuyetApTamTruong, fontInput, "Chỉ số dưới (Vd: 80)");
        add(txtHuyetApTamTruong);

        int y4 = y3 + gapY;
        JLabel lblEcg = new JLabel("Điện tâm đồ (ECG):");
        lblEcg.setBounds(col1_X, y4, 250, 20);
        styleLabel(lblEcg, fontLabel, colText);
        add(lblEcg);

        txtEcg = new JTextField();
        txtEcg.setBounds(col1_X, y4 + 20, widthField, heightField * 2 + 15);
        styleTextField(txtEcg, fontInput, "Kết luận điện tim (Nhịp xoang, Thiếu máu cơ tim...)");
        add(txtEcg);

        // --- Cột 2 ---
        JLabel lblCholesterol = new JLabel("Cholesterol (mmol/L):");
        lblCholesterol.setBounds(col2_X, startY, 250, 20);
        styleLabel(lblCholesterol, fontLabel, colText);
        add(lblCholesterol);

        txtCholesterol = new JTextField();
        txtCholesterol.setBounds(col2_X, startY + 20, widthField, heightField);
        styleTextField(txtCholesterol, fontInput, "Chỉ số mỡ máu tổng phần");
        add(txtCholesterol);

        JLabel lblTriglyceride = new JLabel("Triglyceride (mmol/L):");
        lblTriglyceride.setBounds(col2_X, y2, 250, 20);
        styleLabel(lblTriglyceride, fontLabel, colText);
        add(lblTriglyceride);

        txtTriglyceride = new JTextField();
        txtTriglyceride.setBounds(col2_X, y2 + 20, widthField, heightField);
        styleTextField(txtTriglyceride, fontInput, "Chỉ số chất béo trung tính");
        add(txtTriglyceride);

        JLabel lblDuongHuyet = new JLabel("Đường huyết (mmol/L):");
        lblDuongHuyet.setBounds(col2_X, y3, 250, 20);
        styleLabel(lblDuongHuyet, fontLabel, colText);
        add(lblDuongHuyet);

        txtDuongHuyet = new JTextField();
        txtDuongHuyet.setBounds(col2_X, y3 + 20, widthField, heightField);
        styleTextField(txtDuongHuyet, fontInput, "Chỉ số Glucose lúc đói/bất kỳ");
        add(txtDuongHuyet);

        // --- Ghi chú ---
        int yNote = y4 + (heightField * 2 + 15) + 30;
        JLabel lblGhiChuLabel = new JLabel("Ghi Chú:");
        lblGhiChuLabel.setBounds(col1_X, yNote, 300, 20);
        styleLabel(lblGhiChuLabel, fontLabel, colText);
        add(lblGhiChuLabel);

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

        btnInPhieu = new JButton("IN PHIẾU KHÁM");
        btnInPhieu.setBounds(510, yNote + 120, 200, 45);
        styleButton(btnInPhieu, new Color(255, 193, 7));
        btnInPhieu.setForeground(Color.BLACK);
        add(btnInPhieu);

        if (!java.beans.Beans.isDesignTime()) {
            loadDataFromBus();
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
        if (!validateInputs()) return;
        if (maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy phiếu khám!");
            return;
        }

        try {
            List<ChiSoTongQuat> currentList = bus.getallChiSo(maPhieuKham);
            if (currentList == null || currentList.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Lỗi: Bệnh nhân chưa được trợ lý đo sinh hiệu ban đầu!");
                return;
            }
            int currentId = currentList.get(0).getId();

            ChiSoTongQuat obj = new ChiSoTongQuat();
            obj.setMaPhieuKham(this.maPhieuKham);
            obj.setNhipTim(safeParseInt(txtNhipTim.getText()));
            obj.setHuyetApTamThu(safeParseInt(txtHuyetApTamThu.getText()));
            obj.setHuyetApTamTruong(safeParseInt(txtHuyetApTamTruong.getText()));
            obj.setCholesterol(safeParseDouble(txtCholesterol.getText()));
            obj.setTriglyceride(safeParseDouble(txtTriglyceride.getText()));
            obj.setDuongHuyet(safeParseDouble(txtDuongHuyet.getText()));
            obj.setEcgKetQua(txtEcg.getText().trim());
            obj.setGhiChu(txtGhiChu.getText().trim());

            if (bus.updateTimMach(obj, currentId)) {
                JOptionPane.showMessageDialog(this, "Lưu kết quả khám Tim Mạch thành công!");
                loadDataFromBus();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
        }
    }

    private void loadDataFromBus() {
        if (maPhieuKham <= 0) return;
        List<ChiSoTongQuat> list = bus.getallChiSo(maPhieuKham);
        if (list != null && !list.isEmpty()) {
            ChiSoTongQuat obj = list.get(0);
            txtNhipTim.setText(obj.getNhipTim() == 0 ? "" : String.valueOf(obj.getNhipTim()));
            txtHuyetApTamThu.setText(obj.getHuyetApTamThu() == 0 ? "" : 
                String.valueOf(obj.getHuyetApTamThu()));
            txtHuyetApTamTruong.setText(obj.getHuyetApTamTruong() == 0 ? "" : 
                String.valueOf(obj.getHuyetApTamTruong()));
            txtCholesterol.setText(formatValue(obj.getCholesterol()));
            txtTriglyceride.setText(formatValue(obj.getTriglyceride()));
            txtDuongHuyet.setText(formatValue(obj.getDuongHuyet()));
            txtEcg.setText(obj.getEcgKetQua());
            txtGhiChu.setText(obj.getGhiChu());
        }
    }

    private void initEvents() {
        btnLuu.addActionListener(e -> saveData());
        btnInPhieu.addActionListener(e -> xuatPhieuTimMachPDF());
    }

    private void xuatPhieuTimMachPDF() {
        if (maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa xác định được phiếu khám để in!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Phiếu Khám Tim Mạch");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("PhieuKhamTimMach_" + maPhieuKham + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) filePath += ".pdf";

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", 
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 20, 
                    com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(bf, 12, 
                    com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(bf, 12, 
                    com.itextpdf.text.Font.NORMAL);

                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA \nChuyên khoa Tim Mạch", fontBold);
                pHeader.setAlignment(Element.ALIGN_CENTER);
                document.add(pHeader);
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("PHIẾU KHÁM CHUYÊN KHOA TIM MẠCH", fontTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Mã phiếu khám: " + maPhieuKham, fontBold));
                document.add(new Paragraph("Ngày khám: " + java.time.LocalDate.now(), fontNormal));
                document.add(new Paragraph("Bác sĩ khám: " + Session.TenNhanVien, fontNormal));
                document.add(new Paragraph("----------------------------------------------------------------------------------\n"));

                addSectionPDF(document, "Nhịp tim:", txtNhipTim.getText() + " BPM", fontBold, fontNormal);
                addSectionPDF(document, "Huyết áp:", txtHuyetApTamThu.getText() + "/" + 
                    txtHuyetApTamTruong.getText() + " mmHg", fontBold, fontNormal);
                addSectionPDF(document, "Cholesterol:", txtCholesterol.getText() + " mmol/L", fontBold, fontNormal);
                addSectionPDF(document, "Triglyceride:", txtTriglyceride.getText() + " mmol/L", fontBold, fontNormal);
                addSectionPDF(document, "Đường huyết:", txtDuongHuyet.getText() + " mmol/L", fontBold, fontNormal);
                addSectionPDF(document, "Điện tâm đồ (ECG):", txtEcg.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Chẩn đoán/Ghi chú:", txtGhiChu.getText(), fontBold, fontNormal);

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

    private void addSectionPDF(Document doc, String title, String content, 
        com.itextpdf.text.Font fT, com.itextpdf.text.Font fC) throws Exception {
        doc.add(new Paragraph(title, fT));
        doc.add(new Paragraph(content.isEmpty() ? "Chưa có thông tin" : content, fC));
        doc.add(new Paragraph(" "));
    }

    private boolean validateInputs() {
        if (!checkInt(txtNhipTim, "Nhịp tim")) return false;
        if (!checkInt(txtHuyetApTamThu, "Huyết áp tâm thu")) return false;
        if (!checkInt(txtHuyetApTamTruong, "Huyết áp tâm trương")) return false;
        return true;
    }

    private boolean checkInt(JTextField txt, String title) {
        String s = txt.getText().trim();
        if (s.isEmpty()) return true;
        try {
            if (Integer.parseInt(s) < 0) throw new Exception();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, title + " phải là số nguyên dương!");
            return false;
        }
    }

    private double safeParseDouble(String s) { 
        try { return Double.parseDouble(s.trim()); } catch (Exception e) { return 0.0; } 
    }
    
    private int safeParseInt(String s) { 
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return 0; } 
    }
    
    private String formatValue(double d) { 
        return (d == 0) ? "" : String.valueOf(d); 
    }
    
    private void styleLabel(JLabel lbl, Font font, Color color) { 
        lbl.setFont(font); lbl.setForeground(color); 
    }
    
    private void styleTextField(JTextField txt, Font font, String title) {
        txt.setFont(font); 
        txt.setBackground(Color.WHITE);
        Border line = BorderFactory.createLineBorder(new Color(200, 200, 200));
        TitledBorder tb = BorderFactory.createTitledBorder(line, title);
        tb.setTitleFont(new Font("Segoe UI", Font.ITALIC, 11));
        txt.setBorder(tb);
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