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

import BUS.ChiSoTongQuatBus;
import BUS.DangKyKhamBenhBus;
import BUS.KhamLamSangBus;
import BUS.PhieuKhamBus;
import BUS.XetNghiemBus;
import MODEL.KetQuaCDHA;
import MODEL.KetQuaXetNghiem;
import MODEL.KhamLamSang;
import MODEL.Session;

public class FrmNhapThongTinKhamLamSangTroLyTQ extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtLyDo;
    private JTextArea txtBenhSu;
    private JTextArea txtTienSu;
    private JTextArea txtKhamLamSang;
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
    
    private static int mapk;
    
    
    private List<KetQuaXetNghiem> listDataXN = new ArrayList<>();
    private List<KetQuaCDHA> listDataCDHA = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100, 100, 955, 780); 
                    FrmNhapThongTinKhamLamSangTroLyTQ panel = new FrmNhapThongTinKhamLamSangTroLyTQ( mapk);
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
    public FrmNhapThongTinKhamLamSangTroLyTQ(int mapk) {
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
        txtKhamLamSang.setEditable(false);
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
        txtKetQuaCLS.setEditable(false);
        styleTextArea(txtKetQuaCLS, fontInput);
        txtKetQuaCLS.setBackground(new Color(240, 240, 240)); 
        txtKetQuaCLS.setBounds(360, 335, 530, 110);
        this.add(txtKetQuaCLS);
        
        JLabel lblChanDoan = new JLabel("6. Chẩn đoán sơ bộ:");
        lblChanDoan.setFont(fontLabel);
        lblChanDoan.setForeground(colLabel);
        lblChanDoan.setBounds(20, 460, 200, 25);
        this.add(lblChanDoan);
        
        txtChanDoan = new JTextArea();
        txtChanDoan.setEditable(false);
        styleTextArea(txtChanDoan, fontInput);
        txtChanDoan.setBounds(20, 485, 870, 50);
        this.add(txtChanDoan);
        
        JLabel lblLoiDan = new JLabel("7. Lời dặn bác sĩ:");
        lblLoiDan.setFont(fontLabel);
        lblLoiDan.setForeground(colLabel);
        lblLoiDan.setBounds(20, 545, 200, 25);
        this.add(lblLoiDan);
        
        txtLoiDan = new JTextArea();
        txtLoiDan.setEditable(false);
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
        btnHuyKham.setEnabled(true);
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
        btnChuyenKhoa.setEnabled(false);
        btnChuyenKhoa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                PnlChuyenKhoa panelHenKham = new PnlChuyenKhoa();
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(FrmNhapThongTinKhamLamSangTroLyTQ.this));
                dialog.setContentPane(panelHenKham);
                dialog.setSize(500, 400); 
                dialog.setLocationRelativeTo(null); 
                dialog.setVisible(true);
        	}
        });
        styleButton(btnChuyenKhoa, new Color(111, 66, 193), Color.WHITE);
        btnChuyenKhoa.setBounds(250, btnY, 130, btnH);

        add(btnChuyenKhoa);


        btnHenTaiKham = new JButton("Hẹn tái khám");
        btnHenTaiKham.setEnabled(false);
        styleButton(btnHenTaiKham, new Color(23, 162, 184), Color.WHITE); 
        btnHenTaiKham.setBounds(390, btnY, 130, btnH);
        btnHenTaiKham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PnlHenKham panelHenKham = new PnlHenKham();
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(FrmNhapThongTinKhamLamSangTroLyTQ.this));
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
        btnInPhieu.setEnabled(false);
        this.add(btnInPhieu);
    
        btnLuu = new JButton("Lưu Tạm");
        styleButton(btnLuu, new Color(40, 167, 69), Color.WHITE); 
        btnLuu.setBounds(640, btnY, 100, btnH);
        this.add(btnLuu);
        
     
        btnHoanTatKham = new JButton("HOÀN TẤT KHÁM");
        btnHoanTatKham.setEnabled(false);
        styleButton(btnHoanTatKham, new Color(33, 136, 56), Color.WHITE);
        btnHoanTatKham.setFont(new Font("Segoe UI", Font.BOLD, 15)); 
        btnHoanTatKham.setBounds(750, btnY, 180, 40);
        btnHoanTatKham.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PhieuKhamBus bus = new PhieuKhamBus();
        		bus.updateTrangThaiHoantatKhamBenh(Session.maphieukham,Session.maNhanVien);
        		
        		DangKyKhamBenhBus bus1 = new DangKyKhamBenhBus();
        		bus1.capNhatDaKhamById(Session.maDangKyHienTai);
        		
        		FrmDanhSachKhamBenhCuaBSTQ q = new  FrmDanhSachKhamBenhCuaBSTQ();
        		q.setVisible(true);
        		SwingUtilities.getWindowAncestor(FrmNhapThongTinKhamLamSangTroLyTQ.this).dispose(); 
        	}
        });
        add(btnHoanTatKham);
        
        // =======================================================
        // PHẦN LOGIC 
        // =======================================================
        
        initEvents();
        loadDataCboVaiTro();
        loadDataCboCDHA();
        loadDataCu();
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
                kls.setChanDoanSoBo(txtChanDoan.getText());
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
            FrmDanhSachChoKhamTroLyTQ q = new FrmDanhSachChoKhamTroLyTQ();
            q.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose(); 
        });

        ActionListener comboListener = e -> {
            JComboBox<?> source = (JComboBox<?>) e.getSource();
            Object selectedObj = source.getSelectedItem();
            int index = source.getSelectedIndex();

            if (selectedObj != null && !selectedObj.toString().startsWith("--")) {
                
      
                String prefix = (source == cboXN) ? "- XN: " : (source == cboCDHA) ? "- CÐHA: " : "- TDCN: ";
                String lineToAdd = prefix + selectedObj.toString();
                String currentText = txtKetQuaCLS.getText();
                if (currentText.equals("Chua có kết quả.")) currentText = "";
                
                if (!currentText.contains(lineToAdd)) {
                    if (!currentText.isEmpty()) currentText += "\n";
                    txtKetQuaCLS.setText(currentText + lineToAdd);
                }

 
                
                if (source == cboXN && (index - 1) < listDataXN.size()) {
       
                    int idKetQua = listDataXN.get(index - 1).getId();
             
                    
                          
                    FrmLoadDuLieuXetNghiem frameChiTiet = new FrmLoadDuLieuXetNghiem(idKetQua,"");
                    
     
                    frameChiTiet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    
  
                    frameChiTiet.setVisible(true);

                    
                } else if (source == cboCDHA && (index - 1) < listDataCDHA.size()) {
                	   int idKetQua = listDataCDHA.get(index - 1).getId();
                	   FrmLoadDuLieuCDHA pnl = new FrmLoadDuLieuCDHA(idKetQua,"");
                	    
         
                	    JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Chi tiết Chẩn đoán hình ảnh");
                	    dialog.setContentPane(pnl);
                	    dialog.setSize(1000, 700); 
                	    dialog.setLocationRelativeTo(null); 
                	    dialog.setModal(true); 
                	    dialog.setVisible(true);
                }
            }
        };
        cboXN.addActionListener(comboListener);
        cboCDHA.addActionListener(comboListener);
    }

    private void loadDataCboVaiTro() {
        XetNghiemBus bus = new XetNghiemBus();
  listDataXN = bus.GetKqXetNghiemByMaPhieu(Session.maphieukham);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Chọn KQ Xét Nghiệm --");
        if (listDataXN != null) {
            for (KetQuaXetNghiem vt : listDataXN) model.addElement(vt.getKetLuan());
        }
        cboXN.setModel(model);
    }

    private void loadDataCboCDHA() {
        XetNghiemBus bus = new XetNghiemBus();
        listDataCDHA = bus.GetKqCDHAByMaPhieu(Session.maphieukham);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Chọn KQ CĐHA --");
        if (listDataCDHA != null) {
            for (KetQuaCDHA vt : listDataCDHA) model.addElement(vt.getKetLuan());
        }
        cboCDHA.setModel(model);
    }

    public void loadDataCu() {
        if (mapk <= 0) return;
        KhamLamSangBus busKLS = new KhamLamSangBus();
        ChiSoTongQuatBus bus = new ChiSoTongQuatBus();

        KhamLamSang klsHienTai = busKLS.getByMaPhieuKham(mapk);

        if (klsHienTai != null) {
            // TRƯỜNG HỢP: Đã có dữ liệu 
            txtLyDo.setText(klsHienTai.getLyDoKham());
            txtBenhSu.setText(klsHienTai.getBenhSu());
            txtTienSu.setText(klsHienTai.getTienSuBanThan());

        
        } else {
 
            KhamLamSang dataGanNhat = bus.getKhamLamSangMoiNhatTrongNgay(Session.mabenhnhan);

            if (dataGanNhat != null) {
   
                txtLyDo.setText(dataGanNhat.getLyDoKham());
                txtBenhSu.setText(dataGanNhat.getBenhSu());
                txtTienSu.setText(dataGanNhat.getTienSuBanThan());
                

   
            }
        }
    }
}