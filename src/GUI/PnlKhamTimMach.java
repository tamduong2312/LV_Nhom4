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

import BUS.ChiSoTimMachBus;
import BUS.ChiSoTongQuatBus;
import BUS.DanhMucBenhLyBus;
import MODEL.ChiSoTimMach;
import MODEL.ChiSoTongQuat;
import MODEL.DanhMucBenhLy;

public class PnlKhamTimMach extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtNhipTim;          
    private JTextField txtHuyetApTamThu;    
    private JTextField txtHuyetApTamTruong;
    private JTextField txtCholesterol;     
    private JTextField txtTriglyceride;     
    private JTextField txtDuongHuyet;       
    private JTextField txtEcg;              
    
    private JComboBox<DanhMucBenhLy> cboChanDoan; 

    private JButton btnLuu;
    private JButton btnCapNhat;
    
    private int maPhieuKham;
    private ChiSoTimMachBus bus = new ChiSoTimMachBus();

    public PnlKhamTimMach(int maphieu) {
        this.maPhieuKham = maphieu;
        
        setLayout(null);
        setBackground(new Color(245, 248, 250)); 
        setSize(1000, 700);

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
        txtEcg.setHorizontalAlignment(JTextField.LEFT);
        add(txtEcg);

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

        int yNote = y4 + (heightField * 2 + 15) + 30;
        
        JLabel lblGhiChu = new JLabel("Chẩn đoán / Ghi chú của Bác sĩ:");
        lblGhiChu.setBounds(col1_X, yNote, 300, 20);
        styleLabel(lblGhiChu, fontLabel, colText);
        add(lblGhiChu);
        
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
            loadDataFromBus(); 
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
        ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
        List<ChiSoTongQuat> list = bus.getallChiSo(this.maPhieuKham);
        if (list != null && !list.isEmpty()) { 
            ChiSoTongQuat obj = list.get(0);
            if(txtNhipTim.getText().isEmpty()) txtNhipTim.setText(String.valueOf(obj.getNhipTim())); 
            if(txtHuyetApTamThu.getText().isEmpty()) txtHuyetApTamThu.setText(String.valueOf(obj.getHuyetApThu()));
            if(txtHuyetApTamTruong.getText().isEmpty()) txtHuyetApTamTruong.setText(String.valueOf(obj.getHuyetApTruong()));
        }
    }
    
    private void loadDataFromBus() {
        if (maPhieuKham <= 0) return;
        ChiSoTimMach obj = bus.getChiSoByMaPhieu(maPhieuKham);
        if (obj != null) {
            txtNhipTim.setText(obj.getNhipTim());
            txtHuyetApTamThu.setText(obj.getHuyetApTamThu());
            txtHuyetApTamTruong.setText(obj.getHuyetApTamTruong());
            txtCholesterol.setText(obj.getCholesterol());
            txtTriglyceride.setText(String.valueOf(obj.getTriglyceride())); 
            txtDuongHuyet.setText(String.valueOf(obj.getDuongHuyet()));     
            txtEcg.setText(obj.getEcgKetQua());
            
            cboChanDoan.getEditor().setItem(obj.getGhiChu());
        }
    }

    private void saveData() {
        if (!validateInputs()) {
            return; 
        }

        if (maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy phiếu khám!");
            return;
        }
        
        try {
            ChiSoTimMach obj = new ChiSoTimMach();
            obj.setMaPhieuKham(this.maPhieuKham);
            
            obj.setNhipTim(txtNhipTim.getText().trim());
            obj.setHuyetApTamThu(txtHuyetApTamThu.getText().trim());
            obj.setHuyetApTamTruong(txtHuyetApTamTruong.getText().trim());
            obj.setCholesterol(txtCholesterol.getText().trim());
            
            obj.setTriglyceride(safeParseFloat(txtTriglyceride.getText()));
            obj.setDuongHuyet(safeParseFloat(txtDuongHuyet.getText()));
            
            obj.setEcgKetQua(txtEcg.getText().trim());
            
            String strChanDoan = "";
            Object item = cboChanDoan.getEditor().getItem();
            if (item != null) strChanDoan = item.toString().trim();
            obj.setGhiChu(strChanDoan);
            
            boolean kq = bus.luuHoacCapNhat(obj);
            
            bus.updateChanDoan(maPhieuKham, strChanDoan);
            
            if (kq) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu Tim Mạch thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
        }
    }
    
    private boolean validateInputs() {
        if (txtNhipTim.getText().trim().isEmpty() || 
            txtHuyetApTamThu.getText().trim().isEmpty() || 
            txtHuyetApTamTruong.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, 
                "Vui lòng nhập đầy đủ: Nhịp tim và Huyết áp!", 
                "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (!checkInt(txtNhipTim, "Nhịp tim")) return false;
        if (!checkInt(txtHuyetApTamThu, "Huyết áp tâm thu")) return false;
        if (!checkInt(txtHuyetApTamTruong, "Huyết áp tâm trương")) return false;
        
        if (!checkFloat(txtTriglyceride, "Triglyceride")) return false;
        if (!checkFloat(txtDuongHuyet, "Đường huyết")) return false;
        
        return true;
    }
    
    private boolean checkInt(JTextField txt, String title) {
        String s = txt.getText().trim();
        if (s.isEmpty()) return true; 
        try {
            int val = Integer.parseInt(s);
            if (val < 0) {
                JOptionPane.showMessageDialog(this, title + " không được âm!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
                txt.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, title + " phải là số nguyên!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
            txt.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean checkFloat(JTextField txt, String title) {
        String s = txt.getText().trim();
        if (s.isEmpty()) return true;
        try {
            float val = Float.parseFloat(s);
            if (val < 0) {
                JOptionPane.showMessageDialog(this, title + " không được âm!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
                txt.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, title + " phải là số!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
            txt.requestFocus();
            return false;
        }
        return true;
    }
    
    private float safeParseFloat(String s) {
        try { return Float.parseFloat(s.trim()); } catch (Exception e) { return 0; }
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