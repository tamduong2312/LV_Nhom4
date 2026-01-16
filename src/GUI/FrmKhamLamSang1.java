package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension; 
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import javax.swing.text.JTextComponent;

import BUS.ChiDinhBus;
import BUS.ChiSoTongQuatBus;
import BUS.DangKyKhamBenhBus;
import BUS.DanhMucBenhLyBus;
import BUS.DichVuBus;
import BUS.KhamLamSangBus;
import BUS.PhieuKhamBus;
import BUS.XetNghiemBus;
import MODEL.ChiTietChiDinh;
import MODEL.DanhMucBenhLy;
import MODEL.DichVu;
import MODEL.KetQuaCDHA;
import MODEL.KetQuaXetNghiem;
import MODEL.KhamLamSang;
import MODEL.Session;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Desktop;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class FrmKhamLamSang1 extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtLyDo;
    private JTextArea txtBenhSu;
    private JTextArea txtTienSu;
    private JTextArea txtKhamLamSang;
    private JTextArea txtKetQuaCLS;
    
    // --- SỬA Ở ĐÂY: Đổi JTextArea thành JComboBox ---
    private JComboBox<DanhMucBenhLy> txtChanDoan; 
    
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
    private static String checklichsu;
    
    private static int mapk;
    private static String tenbn;
    
    private List<KetQuaXetNghiem> listDataXN = new ArrayList<>();
    private List<KetQuaCDHA> listDataCDHA = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100, 100, 855, 680); 
                    FrmKhamLamSang1 panel = new FrmKhamLamSang1( mapk,tenbn,checklichsu);
                    frame.setContentPane(panel);
                    frame.setLocationRelativeTo(null); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the panel.
     */
    public FrmKhamLamSang1(int mapk, String tenbn,String checklichsu) {
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


        JLabel lblTitle = new JLabel("PHIẾU KHÁM LÂM SÀNG");
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
 
        JLabel lblKhamLS = new JLabel("4. Khám lâm sàng:");
        lblKhamLS.setFont(fontLabel);
        lblKhamLS.setForeground(colLabel);
        lblKhamLS.setBounds(20, 200, 150, 25);
        this.add(lblKhamLS);
        
        txtKhamLamSang = new JTextArea();
        styleTextArea(txtKhamLamSang, fontInput);
        txtKhamLamSang.setBounds(20, 225, 870, 60);
        this.add(txtKhamLamSang);
        
        
        JLabel lblKetQua = new JLabel("5. Kết quả Cận lâm sàng:");
        lblKetQua.setFont(fontLabel);
        lblKetQua.setForeground(colHeader); 
        lblKetQua.setBounds(20, 300, 200, 25);
        this.add(lblKetQua);
        
        JLabel lblXN1 = new JLabel("- Xét nghiệm:");
        lblXN1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblXN1.setBounds(30, 335, 100, 25);
        this.add(lblXN1);
        
        JLabel lblCDHA = new JLabel("- CĐ Hình ảnh:");
        lblCDHA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCDHA.setBounds(30, 375, 100, 25);
        this.add(lblCDHA);
        
        cboXN = new JComboBox<>();
        cboXN.setBackground(Color.WHITE);
        cboXN.setBounds(120, 335, 220, 30);
        this.add(cboXN);
        
        cboCDHA = new JComboBox<>();
        cboCDHA.setBackground(Color.WHITE);
        cboCDHA.setBounds(120, 375, 220, 30);
        this.add(cboCDHA);
        
        txtKetQuaCLS = new JTextArea();
        styleTextArea(txtKetQuaCLS, fontInput);
        txtKetQuaCLS.setBackground(new Color(240, 240, 240)); 
        txtKetQuaCLS.setBounds(360, 335, 530, 110);
        this.add(txtKetQuaCLS);
        
    
        

        JLabel lblChanDoan = new JLabel("6. Chẩn đoán sơ bộ:");
        lblChanDoan.setFont(fontLabel);
        lblChanDoan.setForeground(colLabel);
        lblChanDoan.setBounds(20, 460, 200, 25);
        this.add(lblChanDoan);
        
  
        txtChanDoan = new JComboBox<>();
        txtChanDoan.setEditable(true);
        txtChanDoan.setBackground(Color.WHITE);
        txtChanDoan.setBounds(20, 485, 870, 50); 
        
  
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
        // --------------------------------------------------------
        
        JLabel lblLoiDan = new JLabel("7. Lời dặn bác sĩ:");
        lblLoiDan.setFont(fontLabel);
        lblLoiDan.setForeground(colLabel);
        lblLoiDan.setBounds(20, 545, 200, 25);
        this.add(lblLoiDan);
        
        txtLoiDan = new JTextArea();
        styleTextArea(txtLoiDan, fontInput);
        txtLoiDan.setBounds(20, 570, 870, 50);
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
        btnHuyKham.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn hủy khám bệnh?", "Xác nhận hủy",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                	PhieuKhamBus bus = new PhieuKhamBus();
            		bus.updateTrangThaiHUYKHAM(Session.maphieukham);
                }
        	}
        });
        add(btnHuyKham);
        
    
        btnChuyenKhoa = new JButton("Chuyển Khoa");
        btnChuyenKhoa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                PnlChuyenKhoa panelHenKham = new PnlChuyenKhoa();
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(FrmKhamLamSang1.this));
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
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(FrmKhamLamSang1.this));
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

        btnHoanTatKham = new JButton("HOÀN TẤT KHÁM");
        styleButton(btnHoanTatKham, new Color(33, 136, 56), Color.WHITE);
        btnHoanTatKham.setFont(new Font("Segoe UI", Font.BOLD, 15)); 
        btnHoanTatKham.setBounds(750, btnY, 180, 40);

        btnHoanTatKham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           
                String lyDo = txtLyDo.getText().trim();
                String benhSu = txtBenhSu.getText().trim();
                String tienSu = txtTienSu.getText().trim();
                String khamLS = txtKhamLamSang.getText().trim();
                
       
                Object objCD = txtChanDoan.getEditor().getItem();
                String chanDoan = (objCD == null) ? "" : objCD.toString().trim();
                
                String loiDan = txtLoiDan.getText().trim();

                if (lyDo.isEmpty() || benhSu.isEmpty() || tienSu.isEmpty() || 
                    khamLS.isEmpty() || chanDoan.isEmpty() || loiDan.isEmpty()) {
                    
                    JOptionPane.showMessageDialog(FrmKhamLamSang1.this, 
                        "Vui lòng nhập đầy đủ các thông tin bắt buộc:\n" +
                        "- Lý do khám\n- Bệnh sử\n- Tiền sử\n- Khám lâm sàng\n- Chẩn đoán sơ bộ\n- Lời dặn bác sĩ", 
                        "Yêu cầu nhập liệu", JOptionPane.WARNING_MESSAGE);
                    return; 
                }

      
                int confirm = JOptionPane.showConfirmDialog(FrmKhamLamSang1.this, 
                    "Xác nhận hoàn tất ca khám này? Sau khi hoàn tất, bạn sẽ không thể chỉnh sửa thông tin.", 
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
                    
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                  
                        btnLuu.doClick(); 

            
                        PhieuKhamBus bus = new PhieuKhamBus();
                        bus.updateTrangThaiHoantatKhamBenh(Session.maphieukham , Session.maNhanVien);
                        
             
                        DangKyKhamBenhBus bus1 = new DangKyKhamBenhBus();
                        bus1.capNhatDaKhamById(Session.maDangKyHienTai);
                        
                        JOptionPane.showMessageDialog(null, "Ca khám đã được hoàn tất thành công!");
                        
  
                        FrmDanhSachKhamBenhCuaBSTQ q = new FrmDanhSachKhamBenhCuaBSTQ();
                        q.setVisible(true);
                        SwingUtilities.getWindowAncestor(FrmKhamLamSang1.this).dispose(); 
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi hoàn tất: " + ex.getMessage());
                    }
                }
            }
        });
        add(btnHoanTatKham);
        
        // =======================================================
        // PHẦN LOGIC 
        // =======================================================
        if ("check".equals(checklichsu)) {
            setFormReadOnly(this);
        }

        initEvents();
        loadDataCboVaiTro();
        loadDataCboCDHA();
        loadDataCu();
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
                
  
                
 
                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA \nĐịa chỉ: 123 Đường ABC, Quận XYZ, TP.HCM\nHotline: 1900.1234", fontHeader);
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
                addSection(document, "4. Khám lâm sàng:", txtKhamLamSang.getText(), fontBold, fontNormal);
                

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
                    if (text.isEmpty()) {
                        comboBox.hidePopup();
                        return;
                    }

                    DanhMucBenhLyBus bus = new DanhMucBenhLyBus();
                    List<DanhMucBenhLy> resultList = bus.getallDanhMucBenhLy(text);

                    if (resultList != null && !resultList.isEmpty()) {
                        model.removeAllElements();
                        for (DanhMucBenhLy s : resultList) {
                            model.addElement(s);
                        }
                        comboBox.showPopup();
                        editor.setText(text);
                    } else {
                        comboBox.hidePopup();
                    }
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
        btn.setContentAreaFilled(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initEvents() {
        btnLuu.addActionListener(e -> {
            if (Session.maphieukham <= 0) {
                JOptionPane.showMessageDialog(this, "Chưa chọn bệnh nhân để lưu!"); 
                return;
            }
            try {
                KhamLamSangBus bus = new KhamLamSangBus();
                KhamLamSang kls = new KhamLamSang();
                kls.setMaPhieuKham(Session.maphieukham);
                
                kls.setLyDoKham(txtLyDo.getText()); 
                kls.setBenhSu(txtBenhSu.getText());
                kls.setTienSuBanThan(txtTienSu.getText());
                kls.setKhamLamSang(txtKhamLamSang.getText());
                
        
                Object selected = txtChanDoan.getEditor().getItem();
                kls.setChanDoanSoBo(selected != null ? selected.toString() : ""); 
                // ---------------------------------------------------------
                
                kls.setLoiDanBacSi(txtLoiDan.getText());
                kls.setKetQuaKhamCanLamSang(txtKetQuaCLS.getText());

                if (bus.luuHoacCapNhat(kls)) {
                    JOptionPane.showMessageDialog(this, "Lưu thông tin khám thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + ex.getMessage());
            }
        });

        btnQuayLai.addActionListener(e -> {
            FrmDanhSachKhamBenhCuaBSTQ q = new FrmDanhSachKhamBenhCuaBSTQ();
            q.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose(); 
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
        if (mapk <= 0) return;
        KhamLamSangBus busKLS = new KhamLamSangBus();
        ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
    
        KhamLamSang klsHienTai = busKLS.getByMaPhieuKham(mapk);

        if (klsHienTai != null) {
    
            txtLyDo.setText(klsHienTai.getLyDoKham());
            txtBenhSu.setText(klsHienTai.getBenhSu());
            txtTienSu.setText(klsHienTai.getTienSuBanThan());
            txtKhamLamSang.setText(klsHienTai.getKhamLamSang());
            
  
            if (klsHienTai.getChanDoanSoBo() != null) {
                txtChanDoan.getEditor().setItem(klsHienTai.getChanDoanSoBo());
            }
            
            txtLoiDan.setText(klsHienTai.getLoiDanBacSi());
            txtKetQuaCLS.setText(klsHienTai.getKetQuaKhamCanLamSang());
            
        } else {

            KhamLamSang dataGanNhat = bus.getKhamLamSangMoiNhatTrongNgay(Session.mabenhnhan);

            if (dataGanNhat != null) {

                txtLyDo.setText(dataGanNhat.getLyDoKham());
                txtBenhSu.setText(dataGanNhat.getBenhSu());
                txtTienSu.setText(dataGanNhat.getTienSuBanThan());
                

                txtKetQuaCLS.setText("");
                txtKetQuaCLS.setForeground(Color.GRAY);
            }
        }
    }
}