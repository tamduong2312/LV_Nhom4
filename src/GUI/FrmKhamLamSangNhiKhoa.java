package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension; 
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.ChiDinhBus;
import BUS.ChiSoTongQuatBus;
import BUS.DangKyKhamBenhBus;
import BUS.DanhMucBenhLyBus;
import BUS.DichVuBus;
import BUS.KhamLamSangBus;
import BUS.PhieuKhamBus;
import BUS.XetNghiemBus;
import MODEL.ChiSoTongQuat;
import MODEL.ChiTietChiDinh;
import MODEL.DanhMucBenhLy;
import MODEL.DichVu;
import MODEL.KetQuaCDHA;
import MODEL.KetQuaXetNghiem;
import MODEL.KhamLamSang;
import MODEL.Session;

public class FrmKhamLamSangNhiKhoa extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtLyDo;
    private JTextArea txtBenhSu;
    private JTextArea txtTienSu;
    private JTextArea txtKetQuaCLS;
    private JTextArea txtLoiDan;
    
    private JComboBox<String> cboXN;
    private JComboBox<String> cboCDHA;
    
    private JButton btnQuayLai;
    private JButton btnHenTaiKham;
    private JButton btnInPhieu;
    private JButton btnLuu;
    private JButton btnHoanTatKham;
    private JButton btnHuyKham;
    private JButton btnChuyenKhoa; 
    private static String tenbn;
    private static String checklichsu;
    

    private JComboBox<DanhMucBenhLy> txtChanDoan; 
    
    private int mapk; 
    private List<KetQuaXetNghiem> listDataXN = new ArrayList<>();
    private List<KetQuaCDHA> listDataCDHA = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100, 100, 955, 780); 
                    FrmKhamLamSangNhiKhoa panel = new FrmKhamLamSangNhiKhoa(0,tenbn,checklichsu);
                    frame.setContentPane(panel);
                    frame.setLocationRelativeTo(null); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmKhamLamSangNhiKhoa(int mapk,String tenbn,String checklichsu) {
        this.mapk = mapk;
      	this.tenbn = tenbn;
      	this.checklichsu = checklichsu;

        Color colBackground = new Color(245, 248, 250); 
        Color colHeader = new Color(0, 102, 204);       
        Color colLabel = new Color(70, 70, 70);         
        Font fontHeader = new Font("Segoe UI", Font.BOLD, 24);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);

        this.setBackground(colBackground);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);
        this.setPreferredSize(new Dimension(940, 750)); 


        JLabel lblTitle = new JLabel("PHIẾU KHÁM NHI KHOA (SƠ BỘ)");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(colHeader);
        lblTitle.setFont(fontHeader);
        lblTitle.setBounds(0, 10, 911, 40); 
        this.add(lblTitle); 
        

        JLabel lblLyDo = new JLabel("1. Lý do đến khám:");
        lblLyDo.setFont(fontLabel);
        lblLyDo.setForeground(colLabel);
        lblLyDo.setBounds(20, 60, 150, 25);
        this.add(lblLyDo);
        
        txtLyDo = new JTextField();
        styleTextField(txtLyDo, fontInput);
        txtLyDo.setBounds(160, 60, 730, 30);
        this.add(txtLyDo);
        

        JLabel lblBenhSu = new JLabel("2. Bệnh sử:");
        lblBenhSu.setFont(fontLabel);
        lblBenhSu.setForeground(colLabel);
        lblBenhSu.setBounds(20, 100, 150, 25);
        this.add(lblBenhSu);
        
        txtBenhSu = new JTextArea();
        styleTextArea(txtBenhSu, fontInput);
        txtBenhSu.setBounds(20, 125, 420, 60);
        this.add(txtBenhSu);
        

        JLabel lblTienSu = new JLabel("3. Tiền sử:");
        lblTienSu.setFont(fontLabel);
        lblTienSu.setForeground(colLabel);
        lblTienSu.setBounds(470, 100, 150, 25);
        this.add(lblTienSu);
        
        txtTienSu = new JTextArea();
        styleTextArea(txtTienSu, fontInput);
        txtTienSu.setBounds(470, 125, 420, 60);
        this.add(txtTienSu);
 
        int currentY = 200;


        JLabel lblKetQua = new JLabel("4. Kết quả Cận lâm sàng:");
        lblKetQua.setFont(fontLabel);
        lblKetQua.setForeground(colHeader); 
        lblKetQua.setBounds(20, currentY, 200, 25);
        this.add(lblKetQua);
        
        JLabel lblXN1 = new JLabel("- Xét nghiệm:");
        lblXN1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblXN1.setBounds(30, currentY + 35, 100, 25);
        this.add(lblXN1);
        
        JLabel lblCDHA = new JLabel("- CĐ Hình ảnh:");
        lblCDHA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCDHA.setBounds(30, currentY + 75, 100, 25);
        this.add(lblCDHA);
        
        cboXN = new JComboBox<>();
        cboXN.setBackground(Color.WHITE);
        cboXN.setBounds(120, currentY + 35, 220, 30);
        this.add(cboXN);
        
        cboCDHA = new JComboBox<>();
        cboCDHA.setBackground(Color.WHITE);
        cboCDHA.setBounds(120, currentY + 75, 220, 30);
        this.add(cboCDHA);
        
        txtKetQuaCLS = new JTextArea();
        styleTextArea(txtKetQuaCLS, fontInput);
        txtKetQuaCLS.setBackground(new Color(240, 240, 240)); 
        txtKetQuaCLS.setEditable(false); 
        txtKetQuaCLS.setBounds(360, currentY + 35, 530, 110);
        this.add(txtKetQuaCLS);
        

        currentY = 360;
        JLabel lblChanDoanLabel = new JLabel("5. Chẩn đoán sơ bộ:");
        lblChanDoanLabel.setFont(fontLabel);
        lblChanDoanLabel.setForeground(colLabel);
        lblChanDoanLabel.setBounds(20, currentY, 200, 25);
        this.add(lblChanDoanLabel);
        
        txtChanDoan = new JComboBox<>();
        txtChanDoan.setEditable(true); 
        txtChanDoan.setBackground(Color.WHITE);
        txtChanDoan.setBounds(20, currentY + 25, 870, 45); 
        

        txtChanDoan.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DanhMucBenhLy) {
                    DanhMucBenhLy item = (DanhMucBenhLy) value;
                    setText("<html><b>" + item.getTen_benh() + "</b> - <i>" + item.getTrieu_chung_goi_y() + "</i></html>");
                }
                return this;
            }
        });
        setupAutoCompleteDB(txtChanDoan);
        this.add(txtChanDoan);

        currentY = 445;
        JLabel lblLoiDan = new JLabel("6. Lời dặn bác sĩ:");
        lblLoiDan.setFont(fontLabel);
        lblLoiDan.setForeground(colLabel);
        lblLoiDan.setBounds(20, currentY, 200, 25);
        this.add(lblLoiDan);
        
        txtLoiDan = new JTextArea();
        styleTextArea(txtLoiDan, fontInput);
        txtLoiDan.setBounds(20, currentY + 25, 870, 50);
        this.add(txtLoiDan);

        int btnY = 650; 
        int btnH = 40; 
        
        btnQuayLai = new JButton("Quay lại");
        styleButton(btnQuayLai, new Color(108, 117, 125), Color.WHITE); 
        btnQuayLai.setBounds(20, btnY, 100, btnH);
        this.add(btnQuayLai);
        
        btnHuyKham = new JButton("Hủy khám");
        styleButton(btnHuyKham, new Color(220, 53, 69), Color.WHITE);
        btnHuyKham.setBounds(130, btnY, 110, btnH);
        this.add(btnHuyKham);
        
        
        btnChuyenKhoa = new JButton("Chuyển Khoa");
        btnChuyenKhoa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                PnlChuyenKhoa panelHenKham = new PnlChuyenKhoa();
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(FrmKhamLamSangNhiKhoa.this));
                dialog.setContentPane(panelHenKham);
                dialog.setSize(800, 700); 
                dialog.setLocationRelativeTo(null); 
                dialog.setVisible(true);
        	}
        });
        styleButton(btnChuyenKhoa, new Color(111, 66, 193), Color.WHITE);
        btnChuyenKhoa.setBounds(250, btnY, 130, btnH);

        add(btnChuyenKhoa);

        btnHenTaiKham = new JButton("Hẹn tái khám");
        styleButton(btnHenTaiKham, new Color(23, 162, 184), Color.WHITE); 
        btnHenTaiKham.setBounds(390, btnY, 130, btnH);
        btnHenTaiKham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PnlHenKham panelHenKham = new PnlHenKham();
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(FrmKhamLamSangNhiKhoa.this));
                dialog.setContentPane(panelHenKham);
                dialog.setSize(1000, 800); 
                dialog.setLocationRelativeTo(null); 
                dialog.setVisible(true);
            }
        });
        this.add(btnHenTaiKham);
        
        btnInPhieu = new JButton("In phiếu");
        styleButton(btnInPhieu, new Color(255, 193, 7), Color.BLACK); 
        btnInPhieu.setBounds(530, btnY, 100, btnH);
        this.add(btnInPhieu);
        btnInPhieu.addActionListener(e -> xuatPhieuKhamPDF());
    
        btnLuu = new JButton("Lưu Tạm");
        styleButton(btnLuu, new Color(40, 167, 69), Color.WHITE); 
        btnLuu.setBounds(640, btnY, 100, btnH);
        this.add(btnLuu);
        
        btnHoanTatKham = new JButton("HOÀN TẤT KHÁM");
        styleButton(btnHoanTatKham, new Color(33, 136, 56), Color.WHITE);
        btnHoanTatKham.setFont(new Font("Segoe UI", Font.BOLD, 15)); 
        btnHoanTatKham.setBounds(750, btnY, 180, 40);
        this.add(btnHoanTatKham);
        
        btnHenTaiKham = new JButton("Hẹn tái khám");
        styleButton(btnHenTaiKham, new Color(23, 162, 184), Color.WHITE); 
        btnHenTaiKham.setBounds(390, btnY, 130, btnH);
        btnHenTaiKham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PnlHenKham panelHenKham = new PnlHenKham();
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(FrmKhamLamSangNhiKhoa.this));
                dialog.setContentPane(panelHenKham);
                dialog.setSize(1000, 800); 
                dialog.setLocationRelativeTo(null); 
                dialog.setVisible(true);
            }
        });
        this.add(btnHenTaiKham);
        
        
        if ("check".equals(checklichsu)) {
            setFormReadOnly(this);
        }

        
        initEvents();
        
        
        
        if (!java.beans.Beans.isDesignTime()) {
            loadDataCboVaiTro();
            loadDataCboCDHA();
            loadDataCu();
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
            } else if (c instanceof Container) {

                setFormReadOnly((Container) c);
            }
        }
    }
    
    private void setupAutoCompleteDB(final JComboBox<DanhMucBenhLy> comboBox) {
        final DefaultComboBoxModel<DanhMucBenhLy> model = new DefaultComboBoxModel<>();
        comboBox.setModel(model);
        final JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ENTER) return;
                SwingUtilities.invokeLater(() -> {
                    String text = editor.getText();
                    if (text.isEmpty()) { comboBox.hidePopup(); return; }
                    DanhMucBenhLyBus bus = new DanhMucBenhLyBus();
                    List<DanhMucBenhLy> resultList = bus.getallDanhMucBenhLy(text);
                    if (resultList != null && !resultList.isEmpty()) {
                        model.removeAllElements();
                        for (DanhMucBenhLy s : resultList) model.addElement(s);
                        comboBox.showPopup();
                        editor.setText(text);
                    } else { comboBox.hidePopup(); }
                });
            }
        });
    }

    private void styleTextField(JTextField txt, Font font) {
        txt.setFont(font);
        txt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)), new EmptyBorder(5, 8, 5, 8)));
    }
    
    private void styleTextArea(JTextArea txt, Font font) {
        txt.setFont(font);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)), new EmptyBorder(5, 8, 5, 8)));
    }
    
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false); 
        btn.setOpaque(true);        
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initEvents() {
        btnLuu.addActionListener(e -> { luuPhieuKham(false); });

        btnHoanTatKham.addActionListener(e -> {
            if(luuPhieuKham(true)) {
                PhieuKhamBus bus = new PhieuKhamBus();
                bus.updateTrangThaiHoantatKhamBenh(this.mapk,Session.maNhanVien);
                DangKyKhamBenhBus bus1 = new DangKyKhamBenhBus();
                bus1.capNhatDaKhamById( Session.maDangKyHienTai);
                bus1.capNhatTrangThaiDaKham(mapk);
                SwingUtilities.getWindowAncestor(this).dispose(); 
                FrmDanhSachKhamBenhCuaBSTQ q = new FrmDanhSachKhamBenhCuaBSTQ();
                q.setVisible(true);
            }
        });

        btnQuayLai.addActionListener(e -> {
            FrmDanhSachKhamBenhCuaBSCK q = new FrmDanhSachKhamBenhCuaBSCK();
            q.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose(); 
        });
        
        btnHuyKham.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Xác nhận hủy khám?", "Hủy", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new PhieuKhamBus().updateTrangThaiHUYKHAM(this.mapk);
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        });

        ActionListener comboListener = e -> {
            JComboBox<?> source = (JComboBox<?>) e.getSource();
            Object selectedObj = source.getSelectedItem();

            if (selectedObj != null && selectedObj.toString().contains(".")) {
                try {
                    String selectedStr = selectedObj.toString();
                    String[] parts = selectedStr.split("\\.");
                    int idChiTiet = Integer.parseInt(parts[0]);
                    String tenDichVu = parts[1];

                    if (source == cboCDHA) {
              
                        FrmLoadDuLieuCDHA pnlCDHA = new FrmLoadDuLieuCDHA(idChiTiet,"check"); 
                        
                        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Kết quả CĐHA");
                        dialog.setContentPane(pnlCDHA);
                        dialog.setSize(1000, 750); 
                        dialog.setLocationRelativeTo(null); 
                        
         
                        dialog.setVisible(true);

            
                        String ketLuan = pnlCDHA.getKetLuanTuForm();
                        
                        if (ketLuan != null && !ketLuan.isEmpty()) {
                            String noiDungHienTai = txtKetQuaCLS.getText();
                            if (noiDungHienTai.equals("Chưa có kết quả.") || noiDungHienTai.isEmpty()) {
                                noiDungHienTai = "";
                            }
                            
                            String dongMoi = "- " + tenDichVu + ": " + ketLuan;
                            
                            if (!noiDungHienTai.contains(dongMoi)) {
                                if (!noiDungHienTai.isEmpty()) noiDungHienTai += "\n";
                                txtKetQuaCLS.setText(noiDungHienTai + dongMoi);
                            }
                        }
                    }
                    
                    if (source == cboXN) {

                    	FrmLoadDuLieuXetNghiem pnlCDHA1 = new FrmLoadDuLieuXetNghiem(idChiTiet,"check"); 
                        
              
       
                        
              
                        pnlCDHA1.setVisible(true);

                        String ketLuan = pnlCDHA1.getKetLuanTuForm();
                        
                        if (ketLuan != null && !ketLuan.isEmpty()) {
                            String noiDungHienTai = txtKetQuaCLS.getText();
                            if (noiDungHienTai.equals("Chưa có kết quả.") || noiDungHienTai.isEmpty()) {
                                noiDungHienTai = "";
                            }
                            
                            String dongMoi = "- " + tenDichVu + ": " + ketLuan;
                            
                            if (!noiDungHienTai.contains(dongMoi)) {
                                if (!noiDungHienTai.isEmpty()) noiDungHienTai += "\n";
                                txtKetQuaCLS.setText(noiDungHienTai + dongMoi);
                            }
                        }
                    }
                    
                    
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        

        cboXN.addActionListener(comboListener);
        cboCDHA.addActionListener(comboListener);
    }
    private void xuatPhieuKhamPDF() {
        if (mapk <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa có thông tin phiếu khám để in!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Phiếu Khám Lâm Sàng");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("PhieuKham_" + mapk + ".pdf"));

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

 
                Paragraph pTitle = new Paragraph("PHIẾU KHÁM LÂM SÀNG", fontTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                
                document.add(new Paragraph("\n"));
                
          
                document.add(new Paragraph("Mã phiếu khám: " + mapk, fontBold));
             
                document.add(new Paragraph("Tên bệnh nhân: " +    this.tenbn, fontBold));
                document.add(new Paragraph("Ngày khám: " + java.time.LocalDate.now(), fontNormal));
                document.add(new Paragraph("Bác sĩ khám: " + Session.TenNhanVien, fontNormal));
                
                
                
                document.add(new Paragraph("\n----------------------------------------------------------\n"));
                
        
                addSection(document, "1. Lý do đến khám:", txtLyDo.getText(), fontBold, fontNormal);
                addSection(document, "2. Bệnh sử:", txtBenhSu.getText(), fontBold, fontNormal);
                addSection(document, "3. Tiền sử:", txtTienSu.getText(), fontBold, fontNormal);

                
      
                document.add(new Paragraph("5. Kết quả Cận lâm sàng:", fontBold));
                document.add(new Paragraph(txtKetQuaCLS.getText().isEmpty() ? "Chưa có kết quả." : txtKetQuaCLS.getText(), fontNormal));
                document.add(new Paragraph("\n"));
                
         
                Object chanDoanObj = txtChanDoan.getEditor().getItem();
                String chanDoanText = (chanDoanObj != null) ? chanDoanObj.toString() : "";
                addSection(document, "6. Chẩn đoán sơ bộ:", chanDoanText, fontBold, fontNormal);
                
                addSection(document, "7. Lời dặn bác sĩ:", txtLoiDan.getText(), fontBold, fontNormal);
                
 
                document.add(new Paragraph("\n\n"));
                
                Paragraph pSign = new Paragraph("Bác sĩ điều trị\n(Ký và ghi rõ họ tên)", fontBold);
                pSign.setAlignment(Element.ALIGN_RIGHT);
                pSign.setIndentationRight(50); 
                document.add(pSign);

                document.close();
                
                JOptionPane.showMessageDialog(this, "Xuất PDF thành công!\n" + filePath);
                
    
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(filePath));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi in PDF: " + ex.getMessage());
            }
        }
    }


    private void addSection(Document doc, String title, String content, com.itextpdf.text.Font fontTitle, com.itextpdf.text.Font fontContent) throws Exception {
        doc.add(new Paragraph(title, fontTitle));
        doc.add(new Paragraph(content.isEmpty() ? "..........................................................." : content, fontContent));
        doc.add(new Paragraph("\n"));
    }
    
    private boolean luuPhieuKham(boolean isComplete) {
        if (this.mapk <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa xác định được phiếu khám!"); 
            return false;
        }
        try {
            KhamLamSangBus bus = new KhamLamSangBus();
            KhamLamSang kls = new KhamLamSang();
            kls.setMaPhieuKham(this.mapk);
            kls.setLyDoKham(txtLyDo.getText()); 
            kls.setBenhSu(txtBenhSu.getText());
            kls.setTienSuBanThan(txtTienSu.getText());
            kls.setKhamLamSang("Xem chi tiết tại phiếu chuyên khoa Nhi"); 
            
   
            Object selected = txtChanDoan.getEditor().getItem();
            kls.setChanDoanSoBo(selected != null ? selected.toString() : ""); 
            
            kls.setLoiDanBacSi(txtLoiDan.getText());
            kls.setKetQuaKhamCanLamSang(txtKetQuaCLS.getText());

            if (bus.luuHoacCapNhat(kls)) {
                if(!isComplete) JOptionPane.showMessageDialog(this, "Lưu thông tin thành công!");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void loadDataCboVaiTro() {
  	  DichVuBus dvbus = new DichVuBus();
        ChiDinhBus ctbus = new ChiDinhBus();
        

        List<DichVu> listdv = dvbus.getDichVu(); 
        int idmaphieuchidinh = ctbus.getMaPhieuChiDinhByMaPhieuKham(mapk);
        

        List<ChiTietChiDinh> listct = ctbus.getChiTietXNByMaPhieu(idmaphieuchidinh);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Chọn Xét Nghiệm --");

 
        if (listct != null) {

            for (ChiTietChiDinh ctcd : listct) {
                String tendv = "Không xác định";

       
                for (DichVu dvt : listdv) {
                    if (dvt.getMaDichVu() == ctcd.getMaDichVu()) {
                        tendv = ctcd.getId() + "." + dvt.getTenDichVu();
                        break; 
                    }
                }


                model.addElement( tendv);
            }
        }
        
        cboXN.setModel(model);
    }


  private void loadDataCboCDHA() {
      DichVuBus dvbus = new DichVuBus();
      ChiDinhBus ctbus = new ChiDinhBus();
      

      List<DichVu> listdv = dvbus.getDichVu(); 
      int idmaphieuchidinh = ctbus.getMaPhieuChiDinhByMaPhieuKham(mapk);
      List<ChiTietChiDinh> listct = ctbus.getKQCDHAChiTietByMaPhieu(idmaphieuchidinh);

      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
      model.addElement("-- Chọn CĐHA --");


      if (listct != null) {

          for (ChiTietChiDinh ctcd : listct) {
              String tendv = "Không xác định";

     
              for (DichVu dvt : listdv) {
                  if (dvt.getMaDichVu() == ctcd.getMaDichVu()) {
                      tendv = ctcd.getId() + "." + dvt.getTenDichVu();
                      break; 
                  }
              }

  
              model.addElement(tendv);
          }
      }
      
      cboCDHA.setModel(model);
  }

    public void loadDataCu() {
        if (this.mapk <= 0) return;
        KhamLamSangBus klsBus = new KhamLamSangBus();
        ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
        KhamLamSang currentKls = klsBus.getByMaPhieuKham(this.mapk);

        if (currentKls != null) {
            txtLyDo.setText(currentKls.getLyDoKham());
            txtBenhSu.setText(currentKls.getBenhSu());
            txtTienSu.setText(currentKls.getTienSuBanThan());

            if (currentKls.getChanDoanSoBo() != null) {
                txtChanDoan.getEditor().setItem(currentKls.getChanDoanSoBo());
            }
            txtLoiDan.setText(currentKls.getLoiDanBacSi());
            txtKetQuaCLS.setText(currentKls.getKetQuaKhamCanLamSang());
        } else {
            KhamLamSang klsGanNhat = bus.getKhamLamSangMoiNhatTrongNgay(Session.mabenhnhan);
            if (klsGanNhat != null) {
                txtLyDo.setText(klsGanNhat.getLyDoKham());
                txtBenhSu.setText(klsGanNhat.getBenhSu());
                txtTienSu.setText(klsGanNhat.getTienSuBanThan());
            }
        }
    }
}