package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BUS.ChiSoTongQuatBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import DAO.PhieuKhamDao;
import MODEL.ChiSoTongQuat;
import MODEL.Session;

public class FrmNhapChiSoSinhTon extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtnhietdo;  
    private JTextField txthuyetap; 
    private JTextField txtnhiptho; 
    private JTextField txtchieucao; 
    private JTextField txtnhiptim; 
    private JTextField txthuyetaptruong;
    private JTextField txtcannang; 
    private JTextField txtghichu; 
    
    private JLabel lblErrNhietDo;
    private JLabel lblErrNhipTim;
    private JLabel lblErrHuyetApThu;
    private JLabel lblErrHuyetApTruong;
    private JLabel lblErrNhipTho;
    private JLabel lblErrCanNang;
    private JLabel lblErrChieuCao;
    
    private int idDangKyKham; 
    private static int mabn;

    public FrmNhapChiSoSinhTon(int idDangKy , int mabn) { 
        this.idDangKyKham = idDangKy;
        this.mabn = mabn;

        setLayout(null);
        setBackground(new Color(245, 248, 250)); 
        setSize(900, 600);

        Font fontHeader = new Font("Segoe UI", Font.BOLD, 22);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontUnit = new Font("Segoe UI", Font.ITALIC, 12);
        Font fontError = new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11); 
        
        Color colHeader = new Color(0, 102, 204);
        Color colLabel = new Color(70, 70, 70);
        Color colBorder = new Color(200, 200, 200);
        Color colError = Color.RED; 

        JLabel lblTitle = new JLabel("THÔNG TIN CHỈ SỐ SINH TỒN");
        lblTitle.setBounds(0, 20, 900, 40); 
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(fontHeader);
        lblTitle.setForeground(colHeader);
        add(lblTitle);
        
        int col1_X = 50; int field1_X = 180; int unit1_X = 350; 
        int col2_X = 480; int field2_X = 630; int unit2_X = 800; 
        int row_H = 35; int row_Start = 90; int gap = 60;      

        int y1 = row_Start;
        
        addLabel(col1_X, y1, "Nhiệt độ:", fontLabel, colLabel);
        txtnhietdo = createTextField(field1_X, y1, row_H, fontText, colBorder);
        add(txtnhietdo);
        addLabelUnit(unit1_X, y1, "°C", fontUnit);
        lblErrNhietDo = createErrorLabel(field1_X, y1 + row_H, 250, fontError, colError);
        add(lblErrNhietDo);
        
        addLabel(col2_X, y1, "Nhịp tim:", fontLabel, colLabel);
        txtnhiptim = createTextField(field2_X, y1, row_H, fontText, colBorder);
        add(txtnhiptim);
        addLabelUnit(unit2_X, y1, "lần/phút", fontUnit);
        lblErrNhipTim = createErrorLabel(field2_X, y1 + row_H, 250, fontError, colError);
        add(lblErrNhipTim);

        int y2 = y1 + gap;
        addLabel(col1_X, y2, "Huyết áp (Thu):", fontLabel, colLabel);
        txthuyetap = createTextField(field1_X, y2, row_H, fontText, colBorder);
        add(txthuyetap);
        addLabelUnit(unit1_X, y2, "mmHg", fontUnit);
        lblErrHuyetApThu = createErrorLabel(field1_X, y2 + row_H, 250, fontError, colError);
        add(lblErrHuyetApThu);
        
        addLabel(col2_X, y2, "Huyết áp (Trương):", fontLabel, colLabel);
        txthuyetaptruong = createTextField(field2_X, y2, row_H, fontText, colBorder);
        add(txthuyetaptruong);
        addLabelUnit(unit2_X, y2, "mmHg", fontUnit);
        lblErrHuyetApTruong = createErrorLabel(field2_X, y2 + row_H, 250, fontError, colError);
        add(lblErrHuyetApTruong);

        int y3 = y2 + gap;
        addLabel(col1_X, y3, "Nhịp thở:", fontLabel, colLabel);
        txtnhiptho = createTextField(field1_X, y3, row_H, fontText, colBorder);
        add(txtnhiptho);
        addLabelUnit(unit1_X, y3, "lần/phút", fontUnit);
        lblErrNhipTho = createErrorLabel(field1_X, y3 + row_H, 250, fontError, colError);
        add(lblErrNhipTho);
        
        addLabel(col2_X, y3, "Cân nặng:", fontLabel, colLabel);
        txtcannang = createTextField(field2_X, y3, row_H, fontText, colBorder);
        add(txtcannang);
        addLabelUnit(unit2_X, y3, "kg", fontUnit);
        lblErrCanNang = createErrorLabel(field2_X, y3 + row_H, 250, fontError, colError);
        add(lblErrCanNang);

        int y4 = y3 + gap;
        addLabel(col1_X, y4, "Chiều cao:", fontLabel, colLabel);
        txtchieucao = createTextField(field1_X, y4, row_H, fontText, colBorder);
        add(txtchieucao);
        addLabelUnit(unit1_X, y4, "cm", fontUnit);
        lblErrChieuCao = createErrorLabel(field1_X, y4 + row_H, 250, fontError, colError);
        add(lblErrChieuCao);
        
        int y5 = y4 + gap;
        JLabel lblGhiChu = new JLabel("Ghi chú / Triệu chứng:");
        lblGhiChu.setBounds(col1_X, y5, 200, 30);
        lblGhiChu.setFont(fontLabel);
        lblGhiChu.setForeground(colLabel);
        add(lblGhiChu);
        
        txtghichu = new JTextField();
        txtghichu.setBounds(col1_X, y5 + 35, 740, 120);
        styleTextField(txtghichu, fontText, colBorder);
        txtghichu.setHorizontalAlignment(JTextField.LEFT); 
        add(txtghichu);
        
        JButton btnCpNht = createButton("Cập nhật", 261, 543);
        add(btnCpNht);
        JButton btnLuu = createButton("Lưu", 419, 543);
        add(btnLuu);
        JButton btnTiepTuc = createButton("Tiếp tục", 630, 543);
        add(btnTiepTuc);
        
        loadData();
        
        ActionListener saveAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateInputs()) {
                    JOptionPane.showMessageDialog(FrmNhapChiSoSinhTon.this, 
                        "Dữ liệu nhập vào không hợp lệ hoặc sai định dạng!\nVui lòng kiểm tra các dòng báo đỏ.", 
                        "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Session.maphieukham <= 0) {
                    JOptionPane.showMessageDialog(FrmNhapChiSoSinhTon.this, 
                        "Chưa xác định được bệnh nhân!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double nhietDo = Double.parseDouble(txtnhietdo.getText().trim());
                    int huyetApThu = Integer.parseInt(txthuyetap.getText().trim());
                    int huyetApTruong = Integer.parseInt(txthuyetaptruong.getText().trim());
                    int nhipTim = Integer.parseInt(txtnhiptim.getText().trim());
                    int nhipTho = Integer.parseInt(txtnhiptho.getText().trim());
                    double canNang = Double.parseDouble(txtcannang.getText().trim());
                    double chieuCao = Double.parseDouble(txtchieucao.getText().trim());
                    String ghiChu = txtghichu.getText().trim();

                    ChiSoTongQuat tq = new ChiSoTongQuat(Session.maphieukham, nhietDo, huyetApThu, huyetApTruong, nhipTim, nhipTho, canNang, chieuCao, ghiChu);
                    
                    ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
                    PhieuKhamBus bus1 = new PhieuKhamBus();
                    PhieuKhamDao dao = new PhieuKhamDao();
                    DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
                    
                    int maPhieuKham = bus1.getMaPhieuKhamByBenhNhan(Session.mabenhnhan);
                    boolean isUpdate = e.getSource() == btnCpNht;
                    boolean success;

                    if (isUpdate) {
                        success = bus.capnhatChiSo(tq, maPhieuKham);
                    } else {
                        success = bus.luuChiSo(tq);
                    }

                    if (success) {
                        if (isUpdate && maPhieuKham > 0) {
                             dkBus.updateTrangThaiChoKhamBS(maPhieuKham);
                        } else if (!isUpdate) {
                            dao.updateTrangThai(maPhieuKham);
                            dkBus.updateTrangThaiChoKhamBS(idDangKyKham);
                        }
                        JOptionPane.showMessageDialog(FrmNhapChiSoSinhTon.this, isUpdate ? "Cập nhật thành công!" : "Lưu thành công!");
                    } else {
                        JOptionPane.showMessageDialog(FrmNhapChiSoSinhTon.this, "Thao tác thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FrmNhapChiSoSinhTon.this, "Lỗi hệ thống: " + ex.getMessage());
                }
            }
        };

        btnLuu.addActionListener(saveAction);
        btnCpNht.addActionListener(saveAction);
        
        btnTiepTuc.addActionListener(e -> {
            java.awt.Window win = SwingUtilities.getWindowAncestor(FrmNhapChiSoSinhTon.this);
            FrmKhamLamSangTroLyRQ q = new FrmKhamLamSangTroLyRQ(mabn);
            q.setVisible(true);
            if (win != null) win.dispose();
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;
        clearErrors();

        if (!checkVitalDouble(txtnhietdo, lblErrNhietDo, 34.0, 43.0, 36.0, 37.5, "Nhiệt độ")) isValid = false;
        if (!checkVitalInt(txtnhiptim, lblErrNhipTim, 30, 220, 60, 100, "Nhịp tim")) isValid = false;
        if (!checkVitalInt(txthuyetap, lblErrHuyetApThu, 50, 260, 90, 140, "Huyết áp")) isValid = false;
        if (!checkVitalInt(txthuyetaptruong, lblErrHuyetApTruong, 30, 160, 60, 90, "Huyết áp")) isValid = false;
        if (!checkVitalInt(txtnhiptho, lblErrNhipTho, 8, 80, 12, 20, "Nhịp thở")) isValid = false;
        if (!checkVitalDouble(txtcannang, lblErrCanNang, 2.0, 300.0, 0, 999, "Cân nặng")) isValid = false;
        if (!checkVitalDouble(txtchieucao, lblErrChieuCao, 50.0, 250.0, 0, 999, "Chiều cao")) isValid = false;

        return isValid;
    }

    private void clearErrors() {
        lblErrNhietDo.setText(""); lblErrNhipTim.setText("");
        lblErrHuyetApThu.setText(""); lblErrHuyetApTruong.setText("");
        lblErrNhipTho.setText(""); lblErrCanNang.setText(""); lblErrChieuCao.setText("");
    }

    private boolean checkVitalDouble(JTextField txt, JLabel lbl, 
            double hardMin, double hardMax, double normalMin, double normalMax, String name) {
        
        String input = txt.getText().trim();
        if (input.isEmpty()) {
            lbl.setText("Vui lòng nhập " + name);
            return false;
        }
        try {
            double val = Double.parseDouble(input);
            
            if (val < hardMin || val > hardMax) {
                lbl.setText("Giá trị không hợp lệ (Phải từ " + hardMin + " - " + hardMax + ")");
                return false; 
            }
            
            if (normalMax > normalMin) { 
                if (val > normalMax) {
                    lbl.setText("Cảnh báo: " + name + " CAO bất thường!");
                    return true; 
                }
                if (val < normalMin) {
                    lbl.setText("Cảnh báo: " + name + " THẤP bất thường!");
                    return true; 
                }
            }
        } catch (NumberFormatException e) {
            lbl.setText(name + " phải là số");
            return false;
        }
        lbl.setText(""); 
        return true;
    }

    private boolean checkVitalInt(JTextField txt, JLabel lbl, 
            int hardMin, int hardMax, int normalMin, int normalMax, String name) {
        
        String input = txt.getText().trim();
        if (input.isEmpty()) {
            lbl.setText("Vui lòng nhập " + name);
            return false;
        }
        try {
            int val = Integer.parseInt(input);
            
            if (val < hardMin || val > hardMax) {
                lbl.setText("Giá trị không hợp lệ (Phải từ " + hardMin + " - " + hardMax + ")");
                return false;
            }
            
            if (val > normalMax) {
                lbl.setText("Cảnh báo: " + name + " CAO bất thường!");
                return true;
            }
            if (val < normalMin) {
                lbl.setText("Cảnh báo: " + name + " THẤP bất thường!");
                return true;
            }
        } catch (NumberFormatException e) {
            lbl.setText(name + " phải là số nguyên");
            return false;
        }
        lbl.setText("");
        return true;
    }

    private void addLabel(int x, int y, String text, Font font, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 140, 30);
        lbl.setFont(font);
        lbl.setForeground(color);
        add(lbl);
    }
    private void addLabelUnit(int x, int y, String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 60, 30);
        lbl.setFont(font);
        add(lbl);
    }
    private JLabel createErrorLabel(int x, int y, int width, Font font, Color color) {
        JLabel lbl = new JLabel("");
        lbl.setBounds(x, y, width, 20); 
        lbl.setFont(font);
        lbl.setForeground(color);
        return lbl;
    }
    private JTextField createTextField(int x, int y, int height, Font font, Color color) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 160, height);
        styleTextField(txt, font, color);
        return txt;
    }
    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 40);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(40, 167, 69));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    private void styleTextField(JTextField txt, Font font, Color borderColor) {
        txt.setFont(font);
        txt.setBackground(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(borderColor, 1, true),
                new EmptyBorder(5, 10, 5, 10)        
        ));
    }

    public void loadData() {
        if (Session.maphieukham <= 0) {
            txtghichu.setText("Chưa xác định được phiếu khám.");
            return;
        }
        ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
        List<ChiSoTongQuat> list = bus.getallChiSo(Session.maphieukham);
        if (list != null && !list.isEmpty()) {
            ChiSoTongQuat q = list.get(0); 
            txtnhietdo.setText(String.valueOf(q.getNhietDo()));
            txthuyetap.setText(String.valueOf(q.getHuyetApThu())); 
            txthuyetaptruong.setText(String.valueOf(q.getHuyetApTruong()));
            txtnhiptim.setText(String.valueOf(q.getNhipTim()));
            txtnhiptho.setText(String.valueOf(q.getNhipTho()));
            txtcannang.setText(String.valueOf(q.getCanNang()));
            txtchieucao.setText(String.valueOf(q.getChieuCao()));
            txtghichu.setText(q.getGhiChu() == null ? "" : q.getGhiChu());
            clearErrors();
        }
    }
}