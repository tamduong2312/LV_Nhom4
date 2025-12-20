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

import BUS.ChiSoTaiMuiHongBus;
import BUS.DanhMucBenhLyBus;
import MODEL.ChiSoTaiMuiHong;
import MODEL.DanhMucBenhLy;

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
    
    private JComboBox<DanhMucBenhLy> cboChanDoan; 

    private JButton btnLuu;
    private JButton btnCapNhat;
    
    private int maPhieuKham;
    private ChiSoTaiMuiHongBus bus = new ChiSoTaiMuiHongBus();

    public PnlKhamTaiMuiHong(int maphieu) {
        this.maPhieuKham = maphieu;
        
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

        int yNote = y5 + gapY;
        JLabel lblKetLuan = new JLabel("Kết luận / Chẩn đoán:");
        lblKetLuan.setBounds(col1_X, yNote, 250, 20);
        styleLabel(lblKetLuan, fontLabel, colText);
        add(lblKetLuan);
        
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
    
    private void loadDataFromBus() {
        if (maPhieuKham <= 0) return;
        
        ChiSoTaiMuiHong obj = bus.getChiSoByMaPhieu(maPhieuKham);
        if (obj != null) {
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
            
            cboChanDoan.getEditor().setItem(obj.getGhiChu());
        }
    }

    private void saveData() {

        String strChanDoan = "";
        Object item = cboChanDoan.getEditor().getItem();
        if (item != null) strChanDoan = item.toString().trim();

        if (strChanDoan.isEmpty() && 
            txtTaiPhai.getText().trim().isEmpty() && 
            txtMui.getText().trim().isEmpty() && 
            txtHong.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, 
                "Vui lòng nhập ít nhất một chỉ số hoặc kết luận chẩn đoán!", 
                "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy phiếu khám!");
            return;
        }
        
        try {
            ChiSoTaiMuiHong obj = new ChiSoTaiMuiHong();
            obj.setMaPhieuKham(this.maPhieuKham);
            
            obj.setThinhLucTaiTrai(txtTaiTrai.getText().trim());
            obj.setThinhLucTaiPhai(txtTaiPhai.getText().trim());
            obj.setOngTai(txtOngTai.getText().trim());
            obj.setMangNhiPhai(""); 
            obj.setMangNhiTrai("");
            
            obj.setTinhTrangMui(txtMui.getText().trim());
            obj.setVachNgan(txtVachNgan.getText().trim());
            obj.setCuonMui(txtCuonMui.getText().trim());
            obj.setKheMui(txtKheMui.getText().trim());
            obj.setSoiTaiMuiHong(""); 
            
            obj.setTinhTrangHong(txtHong.getText().trim());
            obj.setAmidan(txtAmidan.getText().trim());
            obj.setThanhQuan(txtThanhQuan.getText().trim());
            
            obj.setGhiChu(strChanDoan);
            
            boolean kq = bus.luuHoacCapNhat(obj);
            
            bus.updateChanDoan(maPhieuKham, strChanDoan);
            
            if (kq) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu Tai Mũi Họng thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private boolean checkTooLong(JTextField txt, String fieldName) {
        if (txt.getText().length() > 255) { 
            JOptionPane.showMessageDialog(this, 
                "Mục '" + fieldName + "' quá dài (tối đa 255 ký tự)!", 
                "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
            txt.requestFocus();
            return true; 
        }
        return false;
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