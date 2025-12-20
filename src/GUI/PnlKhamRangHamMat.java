package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

// Import Logic
import BUS.ChiSoRangHamMatBus;
import BUS.DanhMucBenhLyBus;
import MODEL.ChiSoRangHamMat;
import MODEL.DanhMucBenhLy;

public class PnlKhamRangHamMat extends JPanel {

    private static final long serialVersionUID = 1L;
    
  
    private JTextField txtTinhTrangChung;   
    private JTextField txtSauRang;        
    private JTextField txtCaoRang;        
    private JTextField txtViemNuou;       
    private JTextField txtKhopCan;         
    private JTextField txtNiemMac;        
    
 
    private JComboBox<DanhMucBenhLy> cboChanDoan; 
    
    private JTextField txtDoLungLay;       
    private JTextField txtPhucHinhCu;     
    private JTextField txtBenhLyKhac;       
    
    private JButton btnLuu;   
    private JButton btnCapNhat; 
    
    
    private int maPhieuKham;
    private ChiSoRangHamMatBus bus = new ChiSoRangHamMatBus();

    public PnlKhamRangHamMat(int maphieu) { 
        this.maPhieuKham = maphieu;
        

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

 
        
     
        int yNote = y5 + gapY; 
        JLabel lblChanDoan = new JLabel("Chẩn đoán sơ bộ / Ghi chú:");
        lblChanDoan.setBounds(col1_X, yNote, 250, 20);
        styleLabel(lblChanDoan, fontLabel, colText);
        add(lblChanDoan);
        

        cboChanDoan = new JComboBox<>();
        cboChanDoan.setBounds(col1_X, yNote + 20, 920, 50);
        cboChanDoan.setEditable(true);
        cboChanDoan.setFont(fontInput);
        cboChanDoan.setBackground(Color.WHITE);
        
   
        cboChanDoan.setRenderer(new javax.swing.DefaultListCellRenderer() {
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
        

        setupAutoCompleteDB(cboChanDoan);
        add(cboChanDoan);
        
    
        int btnY = yNote + 100;
        int btnW = 140;
        int btnH = 40;
        
        btnLuu = new JButton("Lưu Kết Quả");
        btnLuu.setBounds(330, btnY, btnW, btnH);
        styleButton(btnLuu, new Color(40, 167, 69)); 
        add(btnLuu);
        
        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBounds(500, btnY, btnW, btnH);
        styleButton(btnCapNhat, new Color(255, 193, 7)); 
        btnCapNhat.setForeground(Color.BLACK);
        add(btnCapNhat);
 
        if (!java.beans.Beans.isDesignTime()) {
            loadDataCu();
            initEvents();
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
    
   
    private void loadDataCu() {
        if (this.maPhieuKham <= 0) return;
        
        ChiSoRangHamMat obj = bus.getChiSoByMaPhieu(this.maPhieuKham);
        if (obj != null) {
            txtTinhTrangChung.setText(obj.getTinhTrangRang());
            txtSauRang.setText(obj.getSauRang());
            txtCaoRang.setText(obj.getCaoRang());
            txtViemNuou.setText(obj.getViemNuou());
            txtKhopCan.setText(obj.getKhopCan());
            txtNiemMac.setText(obj.getNiemMacMieng());
            
         
            cboChanDoan.getEditor().setItem(obj.getGhiChu());
            
            txtDoLungLay.setText(obj.getDoLungLay());
            txtPhucHinhCu.setText(obj.getPhuHinhCu());
            txtBenhLyKhac.setText(obj.getBenhLyKhac());
        }
    }


    private void saveData() {
   
        String strChanDoan = "";
        Object item = cboChanDoan.getEditor().getItem();
        if (item != null) strChanDoan = item.toString().trim();
        
        if (strChanDoan.isEmpty() && txtTinhTrangChung.getText().trim().isEmpty()) {
             JOptionPane.showMessageDialog(this, 
                 "Vui lòng nhập 'Tình trạng chung' hoặc 'Chẩn đoán sơ bộ'!", 
                 "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
             return;
        }

        if (this.maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy phiếu khám!");
            return;
        }
        
        try {
            ChiSoRangHamMat obj = new ChiSoRangHamMat();
            obj.setMaPhieuKham(this.maPhieuKham);
            obj.setTinhTrangRang(txtTinhTrangChung.getText());
            obj.setSauRang(txtSauRang.getText());
            obj.setCaoRang(txtCaoRang.getText());
            obj.setViemNuou(txtViemNuou.getText());
            obj.setKhopCan(txtKhopCan.getText());
            obj.setNiemMacMieng(txtNiemMac.getText());
            
       
            obj.setGhiChu(strChanDoan);
            
            obj.setDoLungLay(txtDoLungLay.getText());
            obj.setPhuHinhCu(txtPhucHinhCu.getText());
            obj.setBenhLyKhac(txtBenhLyKhac.getText());
            
     
            boolean kq1 = bus.luuHoacCapNhat(obj);
            
   
            boolean kq2 = bus.updateChanDoan(this.maPhieuKham, strChanDoan);
            
            if (kq1) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu Răng Hàm Mặt thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + e.getMessage());
        }
    }
    
    private void initEvents() {
        ActionListener actionSave = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        };
        btnLuu.addActionListener(actionSave);
        btnCapNhat.addActionListener(actionSave);
    }

    // --- HÀM HỖ TRỢ STYLE ---
    
    private void styleLabel(JLabel lbl, Font font, Color color) {
        lbl.setFont(font);
        lbl.setForeground(color);
    }

    private void styleTextField(JTextField txt, Font font, String title) {
        txt.setFont(font);
        txt.setBackground(Color.WHITE);
        
        Border lineBorder = BorderFactory.createLineBorder(new Color(200, 200, 200));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, title);
        titledBorder.setTitleFont(new Font("Segoe UI", Font.ITALIC, 11)); 
        titledBorder.setTitleColor(new Color(100, 100, 100)); 
        
        txt.setBorder(titledBorder);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg);
        if (bg.equals(new Color(255, 193, 7))) {
            btn.setForeground(Color.BLACK);
        } else {
            btn.setForeground(Color.WHITE);
        }
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}