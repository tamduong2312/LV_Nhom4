package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension; 
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
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

import BUS.DangKyKhamBenhBus;
import BUS.KhamLamSangBus;
import BUS.PhieuKhamBus;
import BUS.XetNghiemBus;
import MODEL.KetQuaCDHA;
import MODEL.KetQuaXetNghiem;
import MODEL.KhamLamSang;
import MODEL.Session;

public class FrmKhamLamSangNhiKhoa extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtLyDo;
    private JTextArea txtBenhSu;
    private JTextArea txtTienSu;
    // Đã xóa txtKhamLamSang
    private JTextArea txtKetQuaCLS;
    private JTextArea txtChanDoan;
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
    
    private int mapk; // Mã phiếu khám được truyền vào
    
    // List lưu dữ liệu để lấy ID
    private List<KetQuaXetNghiem> listDataXN = new ArrayList<>();
    private List<KetQuaCDHA> listDataCDHA = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100, 100, 955, 780); 
                    // Test với mã phiếu 0
                    FrmKhamLamSangNhiKhoa panel = new FrmKhamLamSangNhiKhoa(0);
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
    public FrmKhamLamSangNhiKhoa(int mapk) {
        this.mapk = mapk;

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

        // --- HEADER ---
        JLabel lblTitle = new JLabel("PHIẾU KHÁM NHI KHOA (SƠ BỘ)");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(colHeader);
        lblTitle.setFont(fontHeader);
        lblTitle.setBounds(0, 10, 911, 40); 
        this.add(lblTitle); 
        
        // --- 1. LÝ DO ---
        JLabel lblLyDo = new JLabel("1. Lý do đến khám:");
        lblLyDo.setFont(fontLabel);
        lblLyDo.setForeground(colLabel);
        lblLyDo.setBounds(20, 60, 150, 25);
        this.add(lblLyDo);
        
        txtLyDo = new JTextField();
        styleTextField(txtLyDo, fontInput);
        txtLyDo.setBounds(160, 60, 730, 30);
        this.add(txtLyDo);
        
        // --- 2. BỆNH SỬ ---
        JLabel lblBenhSu = new JLabel("2. Bệnh sử:");
        lblBenhSu.setFont(fontLabel);
        lblBenhSu.setForeground(colLabel);
        lblBenhSu.setBounds(20, 100, 150, 25);
        this.add(lblBenhSu);
        
        txtBenhSu = new JTextArea();
        styleTextArea(txtBenhSu, fontInput);
        txtBenhSu.setBounds(20, 125, 420, 60);
        this.add(txtBenhSu);
        
        // --- 3. TIỀN SỬ ---
        JLabel lblTienSu = new JLabel("3. Tiền sử:");
        lblTienSu.setFont(fontLabel);
        lblTienSu.setForeground(colLabel);
        lblTienSu.setBounds(470, 100, 150, 25);
        this.add(lblTienSu);
        
        txtTienSu = new JTextArea();
        styleTextArea(txtTienSu, fontInput);
        txtTienSu.setBounds(470, 125, 420, 60);
        this.add(txtTienSu);
 
        // --- [ĐÃ XÓA MỤC 4. KHÁM LÂM SÀNG] ---
        // Vị trí cũ của Khám LS là y=200. Giờ ta đẩy các mục dưới lên.
        int currentY = 200;

        // --- 4. KẾT QUẢ CLS (Đẩy lên) ---
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
        txtKetQuaCLS.setEditable(false); // Chỉ hiển thị
        txtKetQuaCLS.setBounds(360, currentY + 35, 530, 110);
        this.add(txtKetQuaCLS);
        
        // --- 5. CHẨN ĐOÁN (Đẩy lên y = 360) ---
        currentY = 360;
        JLabel lblChanDoan = new JLabel("5. Chẩn đoán sơ bộ:");
        lblChanDoan.setFont(fontLabel);
        lblChanDoan.setForeground(colLabel);
        lblChanDoan.setBounds(20, currentY, 200, 25);
        this.add(lblChanDoan);
        
        txtChanDoan = new JTextArea();
        styleTextArea(txtChanDoan, fontInput);
        txtChanDoan.setBounds(20, currentY + 25, 870, 50);
        this.add(txtChanDoan);
        
        // --- 6. LỜI DẶN (Đẩy lên y = 445) ---
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
        
        // --- BUTTONS FOOTER ---
        int btnY = 650; // Giữ nguyên vị trí nút ở dưới cùng cho đẹp
        int btnH = 40; 
        
        // Quay lại
        btnQuayLai = new JButton("Quay lại");
        styleButton(btnQuayLai, new Color(108, 117, 125), Color.WHITE); 
        btnQuayLai.setBounds(20, btnY, 100, btnH);
        this.add(btnQuayLai);
        
        // Hủy Khám
        btnHuyKham = new JButton("Hủy khám");
        styleButton(btnHuyKham, new Color(220, 53, 69), Color.WHITE);
        btnHuyKham.setBounds(130, btnY, 110, btnH);
        this.add(btnHuyKham);
        
        // Chuyển Khoa
        btnChuyenKhoa = new JButton("Chuyển Khoa");
        styleButton(btnChuyenKhoa, new Color(111, 66, 193), Color.WHITE);
        btnChuyenKhoa.setBounds(250, btnY, 130, btnH);
        this.add(btnChuyenKhoa);

        // Hẹn tái khám
        btnHenTaiKham = new JButton("Hẹn tái khám");
        styleButton(btnHenTaiKham, new Color(23, 162, 184), Color.WHITE); 
        btnHenTaiKham.setBounds(390, btnY, 130, btnH);
        this.add(btnHenTaiKham);
        
        // In Phiếu
        btnInPhieu = new JButton("In phiếu");
        styleButton(btnInPhieu, new Color(255, 193, 7), Color.BLACK); 
        btnInPhieu.setBounds(530, btnY, 100, btnH);
        this.add(btnInPhieu);
    
        // Lưu Tạm
        btnLuu = new JButton("Lưu Tạm");
        styleButton(btnLuu, new Color(40, 167, 69), Color.WHITE); 
        btnLuu.setBounds(640, btnY, 100, btnH);
        this.add(btnLuu);
        
        // Hoàn tất
        btnHoanTatKham = new JButton("HOÀN TẤT KHÁM");
        styleButton(btnHoanTatKham, new Color(33, 136, 56), Color.WHITE);
        btnHoanTatKham.setFont(new Font("Segoe UI", Font.BOLD, 15)); 
        btnHoanTatKham.setBounds(750, btnY, 180, 40);
        this.add(btnHoanTatKham);
        
        // =======================================================
        // PHẦN LOGIC 
        // =======================================================
        
        initEvents();
        
        if (!java.beans.Beans.isDesignTime()) {
            loadDataCboXN();
            loadDataCboCDHA();
            loadDataCu();
        }
    }
    
    // ... (Các hàm Style TextField, TextArea, Button giữ nguyên) ...
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
        // Sự kiện Lưu Tạm
        btnLuu.addActionListener(e -> {
            luuPhieuKham(false);
        });

        // Sự kiện Hoàn tất
        btnHoanTatKham.addActionListener(e -> {
            if(luuPhieuKham(true)) {
                // Update trạng thái hoàn tất
                PhieuKhamBus bus = new PhieuKhamBus();
                bus.updateTrangThaiHoantatKhamBenh(this.mapk);
                
                DangKyKhamBenhBus bus1 = new DangKyKhamBenhBus();
                bus1.capNhatDaKhamByMaBenhNhan(Session.mabenhnhan);
                
                // Đóng form
                SwingUtilities.getWindowAncestor(this).dispose(); 
                FrmDanhSachKhamBenhCuaBSTQ q = new FrmDanhSachKhamBenhCuaBSTQ();
                q.setVisible(true);
            }
        });

        // Sự kiện Quay lại
        btnQuayLai.addActionListener(e -> {
            FrmDanhSachKhamBenhCuaBSCK q = new FrmDanhSachKhamBenhCuaBSCK();
            q.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose(); 
        });
        
        // Sự kiện Hủy Khám
        btnHuyKham.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Xác nhận hủy khám?", "Hủy", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new PhieuKhamBus().updateTrangThaiHUYKHAM(this.mapk);
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        });

        // Sự kiện Chuyển Khoa
        btnChuyenKhoa.addActionListener(e -> {
            PnlChuyenKhoa panel = new PnlChuyenKhoa();
            JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this));
            dialog.setContentPane(panel);
            dialog.setSize(500, 400); 
            dialog.setLocationRelativeTo(null); 
            dialog.setVisible(true);
        });

        // Sự kiện Hẹn tái khám
        btnHenTaiKham.addActionListener(e -> {
            PnlHenKham panel = new PnlHenKham();
            JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this));
            dialog.setContentPane(panel);
            dialog.setSize(1000, 800); 
            dialog.setLocationRelativeTo(null); 
            dialog.setVisible(true);
        });

        // Sự kiện ComboBox (Chọn kết quả để hiển thị)
        ActionListener comboListener = e -> {
            JComboBox<?> source = (JComboBox<?>) e.getSource();
            Object selectedObj = source.getSelectedItem();
            int index = source.getSelectedIndex();

            if (selectedObj != null && !selectedObj.toString().startsWith("--")) {
                String prefix = (source == cboXN) ? "- XN: " : "- CĐHA: ";
                String lineToAdd = prefix + selectedObj.toString();
                String currentText = txtKetQuaCLS.getText();
                
                if (currentText.equals("Chưa có kết quả.")) currentText = "";
                if (!currentText.contains(lineToAdd)) {
                    if (!currentText.isEmpty()) currentText += "\n";
                    txtKetQuaCLS.setText(currentText + lineToAdd);
                }

                // Mở form chi tiết (nếu cần)
                if (source == cboXN && (index - 1) < listDataXN.size()) {
                    int idKetQua = listDataXN.get(index - 1).getId();
                    PnlXetNghiem pnl = new PnlXetNghiem(idKetQua);
                    pnl.setVisible(true); // JFrame
                } else if (source == cboCDHA && (index - 1) < listDataCDHA.size()) {
                    int idKetQua = listDataCDHA.get(index - 1).getId();
                    FrmLoadDuLieuCDHA pnl = new FrmLoadDuLieuCDHA(idKetQua);
                    JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Chi tiết CĐHA");
                    dialog.setContentPane(pnl);
                    dialog.setSize(1000, 700); 
                    dialog.setLocationRelativeTo(null); 
                    dialog.setVisible(true);
                }
            }
        };
        cboXN.addActionListener(comboListener);
        cboCDHA.addActionListener(comboListener);
    }
    
    // Hàm lưu phiếu khám
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
            
            kls.setChanDoanSoBo(txtChanDoan.getText());
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

    private void loadDataCboXN() {
        XetNghiemBus bus = new XetNghiemBus();
        listDataXN = bus.GetKqXetNghiemByMaPhieu(this.mapk);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Chọn KQ Xét Nghiệm --");
        if (listDataXN != null) {
            for (KetQuaXetNghiem vt : listDataXN) model.addElement(vt.getKetLuan());
        }
        cboXN.setModel(model);
    }

    private void loadDataCboCDHA() {
        XetNghiemBus bus = new XetNghiemBus();
        listDataCDHA = bus.GetKqCDHAByMaPhieu(this.mapk);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Chọn KQ CĐHA --");
        if (listDataCDHA != null) {
            for (KetQuaCDHA vt : listDataCDHA) model.addElement(vt.getKetLuan());
        }
        cboCDHA.setModel(model);
    }

    public void loadDataCu() {
        if (this.mapk <= 0) return;
        KhamLamSangBus bus = new KhamLamSangBus();
        KhamLamSang kls = bus.getByMaPhieuKham(this.mapk);
        if (kls != null) {
            txtLyDo.setText(kls.getLyDoKham());
            txtBenhSu.setText(kls.getBenhSu());
            txtTienSu.setText(kls.getTienSuBanThan());
            

            
            txtChanDoan.setText(kls.getChanDoanSoBo());
            txtLoiDan.setText(kls.getLoiDanBacSi());
            txtKetQuaCLS.setText(kls.getKetQuaKhamCanLamSang());
        }
    }
}