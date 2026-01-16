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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.ChiSoTongQuatBus;
import MODEL.ChiSoTongQuat;
import MODEL.Session;

public class PnlKhamTaiMuiHong extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtTaiPhai;    
    private JTextField txtTaiTrai;    
    private JTextField txtOngTai;     
    private JTextField txtMui;        
    private JTextField txtVachNgan;   
    private JTextField txtCuonMui;    
    private JTextField txtKheMui;     
    private JTextField txtHong;       
    private JTextField txtAmidan;     
    private JTextField txtThanhQuan;  
    
    private JTextArea txtGhiChu; 

    private JButton btnLuu; 
    private JButton btnInPhieu; 
    
    private int maPhieuKham;
    private ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
    private String checklichsu;

    public PnlKhamTaiMuiHong(int maphieu,String checklichsu) {
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

        JLabel lblTitle = new JLabel("PHIẾU NỘI SOI TAI MŨI HỌNG");
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
        JLabel lblTaiP = new JLabel("Tai Phải (Thính lực/Màng nhĩ):");
        lblTaiP.setBounds(col1_X, startY, 250, 20);
        styleLabel(lblTaiP, fontLabel, colText);
        add(lblTaiP);
        
        txtTaiPhai = new JTextField();
        txtTaiPhai.setBounds(col1_X, startY + 20, widthField, heightField);
        styleTextField(txtTaiPhai, fontInput, "Màng nhĩ sáng/đục, nón sáng, thính lực...");
        add(txtTaiPhai);
        
        int y2 = startY + gapY;
        JLabel lblOngTai = new JLabel("Ống tai (T/P):");
        lblOngTai.setBounds(col1_X, y2, 200, 20);
        styleLabel(lblOngTai, fontLabel, colText);
        add(lblOngTai);
        
        txtOngTai = new JTextField();
        txtOngTai.setBounds(col1_X, y2 + 20, widthField, heightField);
        styleTextField(txtOngTai, fontInput, "Sạch, có nút ráy, viêm ống tai ngoài...");
        add(txtOngTai);
        
        int y3 = y2 + gapY;
        JLabel lblVachNgan = new JLabel("Vách ngăn mũi:");
        lblVachNgan.setBounds(col1_X, y3, 200, 20);
        styleLabel(lblVachNgan, fontLabel, colText);
        add(lblVachNgan);
        
        txtVachNgan = new JTextField();
        txtVachNgan.setBounds(col1_X, y3 + 20, widthField, heightField);
        styleTextField(txtVachNgan, fontInput, "Thẳng, vẹo trái/phải, mào vách ngăn...");
        add(txtVachNgan);
        
        int y4 = y3 + gapY;
        JLabel lblKheMui = new JLabel("Khe mũi / Dịch:");
        lblKheMui.setBounds(col1_X, y4, 200, 20);
        styleLabel(lblKheMui, fontLabel, colText);
        add(lblKheMui);
        
        txtKheMui = new JTextField();
        txtKheMui.setBounds(col1_X, y4 + 20, widthField, heightField);
        styleTextField(txtKheMui, fontInput, "Thoáng, đọng dịch nhầy, mủ, polyp...");
        add(txtKheMui);
        
        int y5 = y4 + gapY;
        JLabel lblAmidan = new JLabel("Amidan:");
        lblAmidan.setBounds(col1_X, y5, 200, 20);
        styleLabel(lblAmidan, fontLabel, colText);
        add(lblAmidan);
        
        txtAmidan = new JTextField();
        txtAmidan.setBounds(col1_X, y5 + 20, widthField, heightField);
        styleTextField(txtAmidan, fontInput, "To độ 1-3, hốc mủ, xơ teo...");
        add(txtAmidan);

        // --- Cột 2 ---
        JLabel lblTaiT = new JLabel("Tai Trái (Thính lực/Màng nhĩ):");
        lblTaiT.setBounds(col2_X, startY, 250, 20);
        styleLabel(lblTaiT, fontLabel, colText);
        add(lblTaiT);
        
        txtTaiTrai = new JTextField();
        txtTaiTrai.setBounds(col2_X, startY + 20, widthField, heightField);
        styleTextField(txtTaiTrai, fontInput, "Màng nhĩ sáng/đục, nón sáng, thính lực...");
        add(txtTaiTrai);
        
        JLabel lblMui = new JLabel("Tình trạng mũi chung:");
        lblMui.setBounds(col2_X, y2, 200, 20);
        styleLabel(lblMui, fontLabel, colText);
        add(lblMui);
        
        txtMui = new JTextField();
        txtMui.setBounds(col2_X, y2 + 20, widthField, heightField);
        styleTextField(txtMui, fontInput, "Niêm mạc hồng, sung huyết, nhợt nhạt...");
        add(txtMui);
        
        JLabel lblCuonMui = new JLabel("Cuốn mũi:");
        lblCuonMui.setBounds(col2_X, y3, 200, 20);
        styleLabel(lblCuonMui, fontLabel, colText);
        add(lblCuonMui);
        
        txtCuonMui = new JTextField();
        txtCuonMui.setBounds(col2_X, y3 + 20, widthField, heightField);
        styleTextField(txtCuonMui, fontInput, "Quá phát, phù nề, thoái hóa...");
        add(txtCuonMui);

        JLabel lblHong = new JLabel("Niêm mạc họng:");
        lblHong.setBounds(col2_X, y4, 200, 20);
        styleLabel(lblHong, fontLabel, colText);
        add(lblHong);
        
        txtHong = new JTextField();
        txtHong.setBounds(col2_X, y4 + 20, widthField, heightField);
        styleTextField(txtHong, fontInput, "Hồng, sung huyết, hạt sau thành họng...");
        add(txtHong);

        JLabel lblThanhQuan = new JLabel("Thanh quản:");
        lblThanhQuan.setBounds(col2_X, y5, 200, 20);
        styleLabel(lblThanhQuan, fontLabel, colText);
        add(lblThanhQuan);
        
        txtThanhQuan = new JTextField();
        txtThanhQuan.setBounds(col2_X, y5 + 20, widthField, heightField);
        styleTextField(txtThanhQuan, fontInput, "Dây thanh khép kín, hạt xơ, u nang...");
        add(txtThanhQuan);

        // --- Ghi chú ---
        int yNote = y5 + gapY;
        JLabel lblKetLuan = new JLabel("Ghi chú :");
        lblKetLuan.setBounds(col1_X, yNote, 250, 20);
        styleLabel(lblKetLuan, fontLabel, colText);
        add(lblKetLuan);
        
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

        btnInPhieu = new JButton("IN PHIẾU NỘI SOI");
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
        if (this.maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy phiếu khám!");
            return;
        }

        try {
            List<ChiSoTongQuat> currentList = bus.getallChiSo(this.maPhieuKham);
            ChiSoTongQuat obj = new ChiSoTongQuat();
            obj.setMaPhieuKham(this.maPhieuKham);
            
            obj.setThinhLucTaiTrai(txtTaiTrai.getText().trim());
            obj.setThinhLucTaiPhai(txtTaiPhai.getText().trim());
            obj.setOngTai(txtOngTai.getText().trim());
            obj.setTinhTrangMui(txtMui.getText().trim());
            obj.setVachNgan(txtVachNgan.getText().trim());
            obj.setCuonMui(txtCuonMui.getText().trim());
            obj.setKheMui(txtKheMui.getText().trim());
            obj.setTinhTrangHong(txtHong.getText().trim());
            obj.setAmidan(txtAmidan.getText().trim());
            obj.setThanhQuan(txtThanhQuan.getText().trim());
            obj.setGhiChu(txtGhiChu.getText().trim());

            boolean success;
            if (currentList != null && !currentList.isEmpty()) {
                int currentId = currentList.get(0).getId();
                success = bus.updateTaiMuiHong(obj, currentId);
            } else {
                success = bus.luuChiSo(obj);
            }
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu Tai Mũi Họng thành công!");
                loadDataFromBus();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void loadDataFromBus() {
        if (maPhieuKham <= 0) return;
        List<ChiSoTongQuat> list = bus.getallChiSo(maPhieuKham);
        if (list != null && !list.isEmpty()) {
            ChiSoTongQuat obj = list.get(0);
            txtTaiTrai.setText(obj.getThinhLucTaiTrai()); 
            txtTaiPhai.setText(obj.getThinhLucTaiPhai()); 
            txtOngTai.setText(obj.getOngTai());
            txtMui.setText(obj.getTinhTrangMui());
            txtVachNgan.setText(obj.getVachNgan());
            txtCuonMui.setText(obj.getCuonMui());
            txtKheMui.setText(obj.getKheMui());
            txtHong.setText(obj.getTinhTrangHong());
            txtAmidan.setText(obj.getAmidan());
            txtThanhQuan.setText(obj.getThanhQuan());
            txtGhiChu.setText(obj.getGhiChu());
        }
    }
    
    private void initEvents() {
        btnLuu.addActionListener(e -> saveData());
        btnInPhieu.addActionListener(e -> xuatPhieuENTPDF());
    }

    private void xuatPhieuENTPDF() {
        if (maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa có thông tin phiếu khám để in!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Phiếu Nội Soi Tai Mũi Họng");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("PhieuNoiSoi_" + maPhieuKham + ".pdf"));

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

                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA \nKết quả Nội soi chuyên khoa", fontBold);
                pHeader.setAlignment(Element.ALIGN_CENTER);
                document.add(pHeader);
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("PHIẾU KẾT QUẢ NỘI SOI TAI MŨI HỌNG", fontTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Mã phiếu khám: " + maPhieuKham, fontBold));
                document.add(new Paragraph("Ngày soi: " + java.time.LocalDate.now(), fontNormal));
                document.add(new Paragraph("----------------------------------------------------------\n"));

                addSectionPDF(document, "Tai Phải:", txtTaiPhai.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Tai Trái:", txtTaiTrai.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Ống tai:", txtOngTai.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Mũi:", txtMui.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Vách ngăn:", txtVachNgan.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Cuốn mũi:", txtCuonMui.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Khe mũi:", txtKheMui.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Họng:", txtHong.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Amidan:", txtAmidan.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Thanh quản:", txtThanhQuan.getText(), fontBold, fontNormal);
                addSectionPDF(document, "Ghi chú/Chẩn đoán:", txtGhiChu.getText(), fontBold, fontNormal);

                document.add(new Paragraph("\n\n"));
                Paragraph pSign = new Paragraph("Bác sĩ chuyên khoa\n(Ký và ghi rõ họ tên)", fontBold);
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
        doc.add(new Paragraph(content.isEmpty() ? "Bình thường" : content, fC));
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
        txt.setBorder(titledBorder);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }
}