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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import BUS.ChiSoNhiKhoaBus;
import BUS.ChiSoTongQuatBus;
import BUS.DanhMucBenhLyBus;
import MODEL.ChiSoNhiKhoa;
import MODEL.ChiSoTongQuat;
import MODEL.DanhMucBenhLy;

public class PnlKhamNhiKhoa extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtcannang;   
    private JTextField txtchieucao; 
    private JTextField txtnhietdo; 
    private JTextField txtNhipTim; 
    private JTextField txtnhiptho; 
    private JTextField textField_5; 
    private JTextField textField_6; 
    
    private JComboBox<DanhMucBenhLy> cboChanDoan; 

    private JTextField txtVongDau;
    private JTextField txtTaiMuiHong;
    private JTextField txtHoHap;
    private JTextField txtDaLieu;
    private JTextField txtCoQuanKhac;
    
    private JButton btnLuu;
    private JButton btnCapNhat;

    private int maPhieuKham;
    private ChiSoNhiKhoaBus bus = new ChiSoNhiKhoaBus();

    public PnlKhamNhiKhoa(int maphieu) { 
        this.maPhieuKham = maphieu;
        
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

        int y6 = y5 + gapY;
        JLabel lblCoQuanKhac = new JLabel("Cơ quan khác (Bụng, Thần kinh...):");
        lblCoQuanKhac.setBounds(xCol1, y6, 300, 20);
        styleLabel(lblCoQuanKhac, fontLabel, colText);
        add(lblCoQuanKhac);

        txtCoQuanKhac = new JTextField();
        txtCoQuanKhac.setBounds(xCol1, y6 + 20, 860, heightField);
        styleTextField(txtCoQuanKhac, fontInput);
        add(txtCoQuanKhac);

        int y7 = y6 + gapY;
        JLabel lblGhiChu = new JLabel("Ghi chú / Chẩn đoán sơ bộ:");
        lblGhiChu.setBounds(xCol1, y7, 250, 20);
        styleLabel(lblGhiChu, fontLabel, colText);
        add(lblGhiChu);
        
        cboChanDoan = new JComboBox<>();
        cboChanDoan.setBounds(xCol1, y7 + 20, 860, 40); 
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
        
        int btnY = y7 + 100;
        
        btnLuu = new JButton("Lưu Hồ Sơ Nhi");
        btnLuu.setBounds(300, btnY, 150, 40);
        styleButton(btnLuu, new Color(40, 167, 69)); 
        add(btnLuu);
        
        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBounds(470, btnY, 150, 40);
        styleButton(btnCapNhat, new Color(255, 193, 7)); 
        btnCapNhat.setForeground(Color.BLACK); 
        add(btnCapNhat);

        if (!java.beans.Beans.isDesignTime()) {
            loadDataCu();
            loadDataFromTongQuat();
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
        ChiSoNhiKhoa nk = bus.getChiSoByMaPhieu(this.maPhieuKham);
        if (nk != null) {
            txtcannang.setText(formatFloat(nk.getCanNang()));   
            txtchieucao.setText(formatFloat(nk.getChieuCao())); 
            txtnhietdo.setText(formatFloat(nk.getNhietDo()));  
            txtNhipTim.setText(String.valueOf(nk.getNhipTim())); 
            txtnhiptho.setText(String.valueOf(nk.getNhipTho())); 
            textField_5.setText(nk.getDinhDuong());
            textField_6.setText(nk.getTamLy());
            cboChanDoan.getEditor().setItem(nk.getGhiChu());
            txtVongDau.setText(formatFloat(nk.getVongDau()));
            txtTaiMuiHong.setText(nk.getTaiMuiHong());
            txtHoHap.setText(nk.getHoHap());
            txtDaLieu.setText(nk.getDaLieu());
            txtCoQuanKhac.setText(nk.getCoQuanKhac());
        }
    }
    
    public void loadDataFromTongQuat() {
        if (this.maPhieuKham <= 0) return;
        ChiSoTongQuatBus bus1 = new ChiSoTongQuatBus();
        List<ChiSoTongQuat> list = bus1.getallChiSo(this.maPhieuKham);
        if (list != null && !list.isEmpty()) {
            ChiSoTongQuat q = list.get(0); 
            if(txtnhietdo.getText().isEmpty()) txtnhietdo.setText(String.valueOf(q.getNhietDo()));
            if(txtnhiptho.getText().isEmpty()) txtnhiptho.setText(String.valueOf(q.getNhipTho()));
            if(txtcannang.getText().isEmpty()) txtcannang.setText(String.valueOf(q.getCanNang()));
            if(txtchieucao.getText().isEmpty()) txtchieucao.setText(String.valueOf(q.getChieuCao()));
            if(txtNhipTim.getText().isEmpty()) txtNhipTim.setText(String.valueOf(q.getNhipTim()));
        }
    }

    private void saveData() {
        if (!validateInputs()) {
            return;
        }

        if (this.maPhieuKham <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy phiếu khám!");
            return;
        }

        try {
            ChiSoNhiKhoa nk = new ChiSoNhiKhoa();
            nk.setMaPhieuKham(this.maPhieuKham);

            nk.setCanNang(safeParseFloat(txtcannang.getText()));
            nk.setChieuCao(safeParseFloat(txtchieucao.getText()));
            nk.setNhietDo(safeParseFloat(txtnhietdo.getText()));
            nk.setNhipTim(safeParseInt(txtNhipTim.getText()));
            nk.setNhipTho(safeParseInt(txtnhiptho.getText()));
            nk.setDinhDuong(textField_5.getText());
            nk.setTamLy(textField_6.getText());
            
            String strChanDoan = "";
            Object item = cboChanDoan.getEditor().getItem();
            if (item != null) {
                strChanDoan = item.toString(); 
            }
            nk.setGhiChu(strChanDoan);
            
            nk.setVongDau(safeParseFloat(txtVongDau.getText()));
            nk.setTaiMuiHong(txtTaiMuiHong.getText());
            nk.setHoHap(txtHoHap.getText());
            nk.setDaLieu(txtDaLieu.getText());
            nk.setCoQuanKhac(txtCoQuanKhac.getText());

            boolean kq1 = bus.luuHoacCapNhat(nk);
            boolean kq2 = bus.updateChanDoan(this.maPhieuKham, strChanDoan);

            if (kq1) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu Nhi khoa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + e.getMessage());
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
            float val = Float.parseFloat(input);
            if (val < 0) {
                JOptionPane.showMessageDialog(this, title + " không được là số âm!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
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
    
    private boolean checkInt(JTextField txt, String title) {
        String input = txt.getText().trim();
        if (input.isEmpty()) return true;
        
        try {
            int val = Integer.parseInt(input);
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

    private void initEvents() {
        ActionListener actionSave = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        };
        btnLuu.addActionListener(actionSave);
        btnCapNhat.addActionListener(actionSave);
    }
    
    private float safeParseFloat(String s) {
        try { return Float.parseFloat(s.trim()); } catch (Exception e) { return 0; }
    }
    
    private int safeParseInt(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return 0; }
    }
    
    private String formatFloat(float f) {
        return (f == 0) ? "" : String.valueOf(f);
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
			new EmptyBorder(5, 10, 5, 10) 
		));
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