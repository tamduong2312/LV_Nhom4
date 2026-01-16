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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.ChiSoTongQuatBus;
import MODEL.ChiSoTongQuat;
import MODEL.Session;

public class PnlKhamNhiKhoa extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtcannang;
    private JTextField txtchieucao;
    private JTextField txtnhietdo;
    private JTextField txtNhipTim;
    private JTextField txtnhiptho;
    private JTextField textField_5;
    private JTextField textField_6;

    private JTextArea txtGhiChu;

    private JTextField txtVongDau;
    private JTextField txtTaiMuiHong;
    private JTextField txtHoHap;
    private JTextField txtDaLieu;
    private JTextField txtCoQuanKhac;

    private JButton btnLuu;
    private JButton btnInPhieu;

    private int maPhieuKham;
    private ChiSoTongQuatBus bus = new ChiSoTongQuatBus();

    private String checklichsu;

    public PnlKhamNhiKhoa(int maphieu, String checklichsu) {
        this.maPhieuKham = maphieu;
        this.checklichsu = checklichsu;

        setLayout(null);
        setBackground(new Color(245, 248, 250));
        setSize(1000, 750);

        Font fontHeader = new Font("Segoe UI", Font.BOLD, 22);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);

        Color colPrimary = new Color(0, 102, 204);
        Color colText = new Color(50, 50, 50);

        JLabel lblTitle = new JLabel("PHIẾU KHÁM CHUYÊN KHOA NHI");
        lblTitle.setBounds(0, 15, 1000, 40);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(fontHeader);
        lblTitle.setForeground(colPrimary);
        add(lblTitle);

        int xCol1 = 40;
        int xCol2 = 480;
        int widthLabel = 160;
        int widthField = 250;
        int heightField = 35;
        int startY = 80;
        int gapY = 55;

        // --- Hàng 1 ---
        JLabel lblCanNang = new JLabel("Cân nặng (kg):");
        lblCanNang.setBounds(xCol1, startY, widthLabel, 20);
        styleLabel(lblCanNang, fontLabel, colText);
        add(lblCanNang);

        txtcannang = new JTextField();
        txtcannang.setBounds(xCol1, startY + 20, widthField, heightField);
        styleTextField(txtcannang, fontInput);
        add(txtcannang);

        JLabel lblChieuCao = new JLabel("Chiều cao (cm):");
        lblChieuCao.setBounds(xCol2, startY, widthLabel, 20);
        styleLabel(lblChieuCao, fontLabel, colText);
        add(lblChieuCao);

        txtchieucao = new JTextField();
        txtchieucao.setBounds(xCol2, startY + 20, widthField, heightField);
        styleTextField(txtchieucao, fontInput);
        add(txtchieucao);

        JLabel lblVongDau = new JLabel("Vòng đầu (cm):");
        lblVongDau.setBounds(xCol2 + 270, startY, widthLabel, 20);
        styleLabel(lblVongDau, fontLabel, colText);
        add(lblVongDau);

        txtVongDau = new JTextField();
        txtVongDau.setBounds(xCol2 + 270, startY + 20, 150, heightField);
        styleTextField(txtVongDau, fontInput);
        add(txtVongDau);

        // --- Hàng 2 ---
        int y2 = startY + gapY;
        JLabel lblNhietDo = new JLabel("Nhiệt độ (°C):");
        lblNhietDo.setBounds(xCol1, y2, widthLabel, 20);
        styleLabel(lblNhietDo, fontLabel, colText);
        add(lblNhietDo);

        txtnhietdo = new JTextField();
        txtnhietdo.setBounds(xCol1, y2 + 20, widthField, heightField);
        styleTextField(txtnhietdo, fontInput);
        add(txtnhietdo);

        JLabel lblNhipTim = new JLabel("Nhịp tim (lần/phút):");
        lblNhipTim.setBounds(xCol2, y2, widthLabel, 20);
        styleLabel(lblNhipTim, fontLabel, colText);
        add(lblNhipTim);

        txtNhipTim = new JTextField();
        txtNhipTim.setBounds(xCol2, y2 + 20, widthField, heightField);
        styleTextField(txtNhipTim, fontInput);
        add(txtNhipTim);

        // --- Hàng 3 ---
        int y3 = y2 + gapY;
        JLabel lblNhipTho = new JLabel("Nhịp thở (lần/phút):");
        lblNhipTho.setBounds(xCol1, y3, widthLabel, 20);
        styleLabel(lblNhipTho, fontLabel, colText);
        add(lblNhipTho);

        txtnhiptho = new JTextField();
        txtnhiptho.setBounds(xCol1, y3 + 20, widthField, heightField);
        styleTextField(txtnhiptho, fontInput);
        add(txtnhiptho);

        JLabel lblTMH = new JLabel("Tai - Mũi - Họng:");
        lblTMH.setBounds(xCol2, y3, widthLabel, 20);
        styleLabel(lblTMH, fontLabel, colText);
        add(lblTMH);

        txtTaiMuiHong = new JTextField();
        txtTaiMuiHong.setBounds(xCol2, y3 + 20, 420, heightField);
        styleTextField(txtTaiMuiHong, fontInput);
        add(txtTaiMuiHong);

        // --- Hàng 4 ---
        int y4 = y3 + gapY;
        JLabel lblHoHap = new JLabel("Hô hấp / Phổi:");
        lblHoHap.setBounds(xCol1, y4, widthLabel, 20);
        styleLabel(lblHoHap, fontLabel, colText);
        add(lblHoHap);

        txtHoHap = new JTextField();
        txtHoHap.setBounds(xCol1, y4 + 20, 400, heightField);
        styleTextField(txtHoHap, fontInput);
        add(txtHoHap);

        JLabel lblDaLieu = new JLabel("Da / Niêm mạc:");
        lblDaLieu.setBounds(xCol2, y4, widthLabel, 20);
        styleLabel(lblDaLieu, fontLabel, colText);
        add(lblDaLieu);

        txtDaLieu = new JTextField();
        txtDaLieu.setBounds(xCol2, y4 + 20, 420, heightField);
        styleTextField(txtDaLieu, fontInput);
        add(txtDaLieu);

        // --- Hàng 5 ---
        int y5 = y4 + gapY;
        JLabel lblDinhDuong = new JLabel("Tình trạng dinh dưỡng:");
        lblDinhDuong.setBounds(xCol1, y5, widthLabel + 50, 20);
        styleLabel(lblDinhDuong, fontLabel, colText);
        add(lblDinhDuong);

        textField_5 = new JTextField();
        textField_5.setBounds(xCol1, y5 + 20, 400, heightField);
        styleTextField(textField_5, fontInput);
        add(textField_5);

        JLabel lblTamLy = new JLabel("Tâm lý hành vi:");
        lblTamLy.setBounds(xCol2, y5, widthLabel, 20);
        styleLabel(lblTamLy, fontLabel, colText);
        add(lblTamLy);

        textField_6 = new JTextField();
        textField_6.setBounds(xCol2, y5 + 20, 420, heightField);
        styleTextField(textField_6, fontInput);
        add(textField_6);

        // --- Hàng 6 ---
        int y6 = y5 + gapY;
        JLabel lblCoQuanKhac = new JLabel("Cơ quan khác (Bụng, Thần kinh...):");
        lblCoQuanKhac.setBounds(xCol1, y6, 300, 20);
        styleLabel(lblCoQuanKhac, fontLabel, colText);
        add(lblCoQuanKhac);

        txtCoQuanKhac = new JTextField();
        txtCoQuanKhac.setBounds(xCol1, y6 + 20, 860, heightField);
        styleTextField(txtCoQuanKhac, fontInput);
        add(txtCoQuanKhac);

        // --- Hàng 7: Ghi chú ---
        int y7 = y6 + gapY;
        JLabel lblGhiChu = new JLabel("Ghi chú:");
        lblGhiChu.setBounds(xCol1, y7, 250, 20);
        styleLabel(lblGhiChu, fontLabel, colText);
        add(lblGhiChu);

        txtGhiChu = new JTextArea();
        txtGhiChu.setFont(fontInput);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);

        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);
        scrollGhiChu.setBounds(xCol1, y7 + 20, 860, 80);
        scrollGhiChu.setBorder(new LineBorder(new Color(200, 200, 200)));
        add(scrollGhiChu);

        // --- Nút bấm ---
        btnLuu = new JButton("LƯU HỒ SƠ NHI");
        btnLuu.setBounds(290, y7 + 120, 200, 45);
        styleButton(btnLuu, new Color(40, 167, 69));
        add(btnLuu);

        btnInPhieu = new JButton("IN PHIẾU KHÁM NHI");
        btnInPhieu.setBounds(510, y7 + 120, 200, 45);
        styleButton(btnInPhieu, new Color(255, 193, 7));
        btnInPhieu.setForeground(Color.BLACK);
        add(btnInPhieu);

        if ("check".equals(checklichsu)) {
            setFormReadOnly(this);
        }

        if (!java.beans.Beans.isDesignTime()) {
            loadDataCu();
            initEvents();
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

    private void loadDataCu() {
        if (this.maPhieuKham <= 0)
            return;
        List<ChiSoTongQuat> list = bus.getallChiSo(this.maPhieuKham);
        if (list != null && !list.isEmpty()) {
            ChiSoTongQuat q = list.get(0);
            txtcannang.setText(formatDouble(q.getCanNang()));
            txtchieucao.setText(formatDouble(q.getChieuCao()));
            txtnhietdo.setText(formatDouble(q.getNhietDo()));
            txtNhipTim.setText(q.getNhipTim() == 0 ? "" : String.valueOf(q.getNhipTim()));
            txtnhiptho.setText(q.getNhipTho() == 0 ? "" : String.valueOf(q.getNhipTho()));
            txtVongDau.setText(formatDouble(q.getVongDau()));
            textField_5.setText(q.getTinhTrangDinhDuong());
            textField_6.setText(q.getTamLyHanhVi());
            txtTaiMuiHong.setText(q.getKhamTaiMuiHongNhi());
            txtHoHap.setText(q.getKhamHoHapNhi());
            txtDaLieu.setText(q.getKhamDaNiemMacNhi());
            txtCoQuanKhac.setText(q.getCoQuanKhacNhi());
            txtGhiChu.setText(q.getGhiChu());
        }
    }

    private void saveData() {
        if (!validateInputs())
            return;
        try {
            List<ChiSoTongQuat> list = bus.getallChiSo(this.maPhieuKham);
            ChiSoTongQuat q = new ChiSoTongQuat();
            q.setMaPhieuKham(this.maPhieuKham);
            q.setCanNang(safeParseDouble(txtcannang.getText()));
            q.setChieuCao(safeParseDouble(txtchieucao.getText()));
            q.setNhietDo(safeParseDouble(txtnhietdo.getText()));
            q.setNhipTim(safeParseInt(txtNhipTim.getText()));
            q.setNhipTho(safeParseInt(txtnhiptho.getText()));
            q.setVongDau(safeParseDouble(txtVongDau.getText()));
            q.setTinhTrangDinhDuong(textField_5.getText().trim());
            q.setTamLyHanhVi(textField_6.getText().trim());
            q.setKhamTaiMuiHongNhi(txtTaiMuiHong.getText().trim());
            q.setKhamHoHapNhi(txtHoHap.getText().trim());
            q.setKhamDaNiemMacNhi(txtDaLieu.getText().trim());
            q.setCoQuanKhacNhi(txtCoQuanKhac.getText().trim());
            q.setGhiChu(txtGhiChu.getText().trim());

            boolean success = (list != null && !list.isEmpty())
                    ? bus.updateNhiKhoa(q, list.get(0).getId())
                    : bus.luuChiSo(q);

            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu Nhi khoa thành công!");
                loadDataCu();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEvents() {
        btnLuu.addActionListener(e -> saveData());
        btnInPhieu.addActionListener(e -> xuatPhieuNhiPDF());
    }

    private void xuatPhieuNhiPDF() {
        if (maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa xác định được phiếu khám!");
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("PhieuKhamNhi_" + maPhieuKham + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf"))
                filePath += ".pdf";

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font itextTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font itextBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font itextNormal = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);

                Paragraph header = new Paragraph("PHÒNG KHÁM ĐA KHOA \nKết quả khám chuyên khoa Nhi", itextBold);
                header.setAlignment(Element.ALIGN_CENTER);
                document.add(header);
                document.add(new Paragraph("\n"));

                Paragraph title = new Paragraph("PHIẾU KHÁM CHUYÊN KHOA NHI", itextTitle);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Mã phiếu khám: " + maPhieuKham, itextBold));
                document.add(new Paragraph("Bác sĩ khám: " + Session.TenNhanVien, itextNormal));
                document.add(new Paragraph("Ngày khám: " + java.time.LocalDate.now(), itextNormal));
                document.add(new Paragraph("----------------------------------------------------------------------------------\n"));

                String content = String.format(
                        "Cân nặng: %s kg | Chiều cao: %s cm | Vòng đầu: %s cm\n" +
                                "Nhiệt độ: %s °C | Nhịp tim: %s | Nhịp thở: %s\n\n" +
                                "Tai - Mũi - Họng: %s\n" +
                                "Hô hấp / Phổi: %s\n" +
                                "Da / Niêm mạc: %s\n" +
                                "Tình trạng dinh dưỡng: %s\n" +
                                "Tâm lý hành vi: %s\n" +
                                "Cơ quan khác: %s\n\n" +
                                "Ghi chú/Kết luận: %s",
                        txtcannang.getText(), txtchieucao.getText(), txtVongDau.getText(),
                        txtnhietdo.getText(), txtNhipTim.getText(), txtnhiptho.getText(),
                        txtTaiMuiHong.getText(), txtHoHap.getText(), txtDaLieu.getText(),
                        textField_5.getText(), textField_6.getText(), txtCoQuanKhac.getText(),
                        txtGhiChu.getText());
                document.add(new Paragraph(content, itextNormal));

                document.add(new Paragraph("\n\n"));
                Paragraph sign = new Paragraph("Bác sĩ điều trị\n(Ký và ghi rõ họ tên)", itextBold);
                sign.setAlignment(Element.ALIGN_RIGHT);
                document.add(sign);

                document.close();
                JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
                if (Desktop.isDesktopSupported())
                    Desktop.getDesktop().open(new File(filePath));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi in PDF: " + ex.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        if (!checkNumber(txtcannang, "Cân nặng")) return false;
        if (!checkNumber(txtchieucao, "Chiều cao")) return false;
        if (!checkNumber(txtVongDau, "Vòng đầu")) return false;
        if (!checkNumber(txtnhietdo, "Nhiệt độ")) return false;
        if (!checkInt(txtNhipTim, "Nhịp tim")) return false;
        if (!checkInt(txtnhiptho, "Nhịp thở")) return false;
        return true;
    }

    private boolean checkNumber(JTextField txt, String title) {
        String input = txt.getText().trim();
        if (input.isEmpty()) return true;
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, title + " phải là số!");
            return false;
        }
    }

    private boolean checkInt(JTextField txt, String title) {
        String input = txt.getText().trim();
        if (input.isEmpty()) return true;
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, title + " phải là số nguyên!");
            return false;
        }
    }

    private double safeParseDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    private int safeParseInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private String formatDouble(double d) {
        return (d == 0) ? "" : String.valueOf(d);
    }

    private void styleLabel(JLabel lbl, Font font, Color color) {
        lbl.setFont(font);
        lbl.setForeground(color);
    }

    private void styleTextField(JTextField txt, Font font) {
        txt.setFont(font);
        txt.setBackground(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 10, 5, 10)));
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