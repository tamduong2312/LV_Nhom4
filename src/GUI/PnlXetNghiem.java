package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import BUS.PhieuKhamBus;
import BUS.XetNghiemBus;
import MODEL.KetQuaXetNghiem;
import MODEL.Session;

public class PnlXetNghiem extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private XetNghiemBus bus = new XetNghiemBus();
    private int idChiTiet; 


    private JTextField txtHongCau, txtBachCau, txtTieuCau, txtHuyetSacTo, txtHCT;
    private JTextField txtNhomMau, txtTocDoLang, txtDongMauCoBan;

  
    private JTextField txtGlucose, txtHbA1c, txtUre, txtCreatinine;
    private JTextField txtAST, txtALT, txtGGT, txtBili;
    private JTextField txtCholesterol, txtTri, txtHDL, txtLDL, txtAcidUric;

    private JTextField txtTyTrong, txtPH, txtLeu, txtEry;
    private JTextField txtPro, txtGlu, txtNit, txtKet;


    private JTextField txtHBsAg, txtHCV, txtHIV, txtTPHA;
    private JTextField txtCRP, txtRF;
    

    private JTextArea txtKetLuan, txtGhiChu;


    public PnlXetNghiem(int idChiTietChiDinh) {
        this.idChiTiet = idChiTietChiDinh;
        
        setTitle("NHẬP KẾT QUẢ XÉT NGHIỆM TỔNG HỢP");
        setSize(1000, 750); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        JLabel lblHeader = new JLabel("PHIẾU KẾT QUẢ CẬN LÂM SÀNG", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblHeader.setForeground(new Color(0, 102, 204));
        lblHeader.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);


        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 14));


        JPanel p1 = new JPanel(new GridLayout(4, 4, 15, 15));
        p1.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p1, "Hồng cầu (RBC) [T/L]:", txtHongCau = new JTextField());
        addInput(p1, "Bạch cầu (WBC) [G/L]:", txtBachCau = new JTextField());
        addInput(p1, "Tiểu cầu (PLT) [G/L]:", txtTieuCau = new JTextField());
        addInput(p1, "Huyết sắc tố (HGB) [g/dL]:", txtHuyetSacTo = new JTextField());
        addInput(p1, "Hematocrit (HCT) [%]:", txtHCT = new JTextField());
        addInput(p1, "Nhóm máu (ABO/Rh):", txtNhomMau = new JTextField());
        addInput(p1, "Máu lắng (VS) [mm/h]:", txtTocDoLang = new JTextField());
        addInput(p1, "Đông máu cơ bản:", txtDongMauCoBan = new JTextField());
        tabs.addTab("1. Huyết Học", p1);


        JPanel p2 = new JPanel(new GridLayout(5, 4, 15, 15)); 
        p2.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p2, "Glucose [mmol/L]:", txtGlucose = new JTextField());
        addInput(p2, "HbA1c [%]:", txtHbA1c = new JTextField());
        addInput(p2, "Ure [mmol/L]:", txtUre = new JTextField());
        addInput(p2, "Creatinine [µmol/L]:", txtCreatinine = new JTextField());
        addInput(p2, "AST (GOT) [U/L]:", txtAST = new JTextField());
        addInput(p2, "ALT (GPT) [U/L]:", txtALT = new JTextField());
        addInput(p2, "GGT [U/L]:", txtGGT = new JTextField());
        addInput(p2, "Bilirubin TP [µmol/L]:", txtBili = new JTextField());
        addInput(p2, "Cholesterol TP [mmol/L]:", txtCholesterol = new JTextField());
        addInput(p2, "Triglyceride [mmol/L]:", txtTri = new JTextField());
        addInput(p2, "HDL-C [mmol/L]:", txtHDL = new JTextField());
        addInput(p2, "LDL-C [mmol/L]:", txtLDL = new JTextField());
        addInput(p2, "Acid Uric [µmol/L]:", txtAcidUric = new JTextField());
        tabs.addTab("2. Sinh Hóa", p2);

    
        JPanel p3 = new JPanel(new GridLayout(4, 4, 15, 15));
        p3.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p3, "Tỷ trọng (SG):", txtTyTrong = new JTextField());
        addInput(p3, "pH:", txtPH = new JTextField());
        addInput(p3, "Bạch cầu (LEU):", txtLeu = new JTextField());
        addInput(p3, "Hồng cầu (ERY):", txtEry = new JTextField());
        addInput(p3, "Protein (PRO):", txtPro = new JTextField());
        addInput(p3, "Đường (GLU):", txtGlu = new JTextField());
        addInput(p3, "Nitrit (NIT):", txtNit = new JTextField());
        addInput(p3, "Ketone (KET):", txtKet = new JTextField());
        tabs.addTab("3. Nước Tiểu", p3);

        JPanel p4 = new JPanel(new GridLayout(4, 4, 15, 15));
        p4.setBorder(new EmptyBorder(20, 20, 20, 20));
        addInput(p4, "HBsAg (Viêm gan B):", txtHBsAg = new JTextField());
        addInput(p4, "Anti-HCV (Viêm gan C):", txtHCV = new JTextField());
        addInput(p4, "HIV Ab:", txtHIV = new JTextField());
        addInput(p4, "TPHA (Giang mai):", txtTPHA = new JTextField());
        addInput(p4, "CRP định lượng:", txtCRP = new JTextField());
        addInput(p4, "RF định lượng:", txtRF = new JTextField());
        tabs.addTab("4. Miễn Dịch", p4);

        add(tabs, BorderLayout.CENTER);


        JPanel pnlBottom = new JPanel(new BorderLayout(10, 10));
        pnlBottom.setBorder(new CompoundBorder(
                new MatteBorder(1,0,0,0,Color.LIGHT_GRAY), 
                new EmptyBorder(10,15,15,15)));
        pnlBottom.setPreferredSize(new Dimension(0, 200));


        JPanel pnlText = new JPanel(new GridLayout(1, 2, 15, 0));
        
        txtKetLuan = new JTextArea();
        txtKetLuan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrKetLuan = new JScrollPane(txtKetLuan);
        scrKetLuan.setBorder(BorderFactory.createTitledBorder(
                null, "KẾT LUẬN (Bắt buộc)", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Segoe UI", Font.BOLD, 12), Color.RED));
        pnlText.add(scrKetLuan);
        
        txtGhiChu = new JTextArea();
        txtGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrGhiChu = new JScrollPane(txtGhiChu);
        scrGhiChu.setBorder(BorderFactory.createTitledBorder(
                null, "Ghi chú thêm / Đề nghị", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Segoe UI", Font.BOLD, 12), Color.BLACK));
        pnlText.add(scrGhiChu);
        
        pnlBottom.add(pnlText, BorderLayout.CENTER);

        JButton btnLuu = new JButton("XÁC NHẬN & LƯU KẾT QUẢ");
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLuu.setBackground(new Color(0, 123, 255));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setPreferredSize(new Dimension(0, 45));
        btnLuu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLuu.setContentAreaFilled(false);
        btnLuu.setOpaque(true);
        btnLuu.setBorderPainted(false);
        
        btnLuu.addActionListener(e -> save());
        pnlBottom.add(btnLuu, BorderLayout.SOUTH);

        add(pnlBottom, BorderLayout.SOUTH);


        loadData();
    }


    private void addInput(JPanel p, String title, JTextField txt) {
        JPanel pp = new JPanel(new BorderLayout(0, 5));
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(new Color(80, 80, 80));
        pp.add(lbl, BorderLayout.NORTH);
        
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 5, 5, 5)));
        pp.add(txt, BorderLayout.CENTER);
        
        p.add(pp);
    }


    private void loadData() {
        KetQuaXetNghiem kq = bus.getKetQua(this.idChiTiet);
        if (kq != null) {
            // Tab 1
            setText(txtHongCau, kq.getHongCau());
            setText(txtBachCau, kq.getBachCau());
            setText(txtTieuCau, kq.getTieuCau());
            setText(txtHuyetSacTo, kq.getHuyetSacTo());
            setText(txtHCT, kq.getHematocrit());
            txtNhomMau.setText(kq.getNhomMau());
            setText(txtTocDoLang, kq.getTocDoMauLang());
            txtDongMauCoBan.setText(kq.getDongMauCoBan());

            // Tab 2
            setText(txtGlucose, kq.getGlucose());
            setText(txtHbA1c, kq.getHbA1c());
            setText(txtUre, kq.getUre());
            setText(txtCreatinine, kq.getCreatinine());
            setText(txtAST, kq.getAstGot());
            setText(txtALT, kq.getAltGpt());
            setText(txtGGT, kq.getGgt());
            setText(txtBili, kq.getBilirubinTp());
            setText(txtCholesterol, kq.getCholesterolTp());
            setText(txtTri, kq.getTriglyceride());
            setText(txtHDL, kq.getHdlC());
            setText(txtLDL, kq.getLdlC());
            setText(txtAcidUric, kq.getAcidUric());

            // Tab 3
            setText(txtTyTrong, kq.getTyTrong());
            setText(txtPH, kq.getPh());
            txtLeu.setText(kq.getBachCauNuocTieu());
            txtEry.setText(kq.getHongCauNuocTieu());
            txtPro.setText(kq.getProteinNuocTieu());
            txtGlu.setText(kq.getDuongNuocTieu());
            txtNit.setText(kq.getNitrit());
            txtKet.setText(kq.getKetone());

            // Tab 4
            txtHBsAg.setText(kq.getHbsag());
            txtHCV.setText(kq.getHcvAb());
            txtHIV.setText(kq.getHivAb());
            txtTPHA.setText(kq.getTphaSyphilis());
            setText(txtCRP, kq.getCrpDinhLuong());
            setText(txtRF, kq.getRfDinhLuong());


            txtKetLuan.setText(kq.getKetLuan());
            txtGhiChu.setText(kq.getGhiChuThem());
        }
    }


    private void save() {
        if(txtKetLuan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập KẾT LUẬN tổng quát trước khi lưu!", 
                    "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            KetQuaXetNghiem kq = new KetQuaXetNghiem();
            kq.setMaChiTietChiDinh(this.idChiTiet);
            
            // Tab 1
            kq.setHongCau(parseFloat(txtHongCau.getText()));
            kq.setBachCau(parseFloat(txtBachCau.getText()));
            kq.setTieuCau(parseFloat(txtTieuCau.getText()));
            kq.setHuyetSacTo(parseFloat(txtHuyetSacTo.getText()));
            kq.setHematocrit(parseFloat(txtHCT.getText()));
            kq.setNhomMau(txtNhomMau.getText());
            kq.setTocDoMauLang((int)parseFloat(txtTocDoLang.getText()));
            kq.setDongMauCoBan(txtDongMauCoBan.getText());

            // Tab 2
            kq.setGlucose(parseFloat(txtGlucose.getText()));
            kq.setHbA1c(parseFloat(txtHbA1c.getText()));
            kq.setUre(parseFloat(txtUre.getText()));
            kq.setCreatinine(parseFloat(txtCreatinine.getText()));
            kq.setAstGot(parseFloat(txtAST.getText()));
            kq.setAltGpt(parseFloat(txtALT.getText()));
            kq.setGgt(parseFloat(txtGGT.getText()));
            kq.setBilirubinTp(parseFloat(txtBili.getText()));
            kq.setCholesterolTp(parseFloat(txtCholesterol.getText()));
            kq.setTriglyceride(parseFloat(txtTri.getText()));
            kq.setHdlC(parseFloat(txtHDL.getText()));
            kq.setLdlC(parseFloat(txtLDL.getText()));
            kq.setAcidUric(parseFloat(txtAcidUric.getText()));

            // Tab 3
            kq.setTyTrong(parseFloat(txtTyTrong.getText()));
            kq.setPh(parseFloat(txtPH.getText()));
            kq.setBachCauNuocTieu(txtLeu.getText());
            kq.setHongCauNuocTieu(txtEry.getText());
            kq.setProteinNuocTieu(txtPro.getText());
            kq.setDuongNuocTieu(txtGlu.getText());
            kq.setNitrit(txtNit.getText());
            kq.setKetone(txtKet.getText());

            // Tab 4
            kq.setHbsag(txtHBsAg.getText());
            kq.setHcvAb(txtHCV.getText());
            kq.setHivAb(txtHIV.getText());
            kq.setTphaSyphilis(txtTPHA.getText());
            kq.setCrpDinhLuong(parseFloat(txtCRP.getText()));
            kq.setRfDinhLuong(parseFloat(txtRF.getText()));

       
            kq.setKetLuan(txtKetLuan.getText());
            kq.setGhiChuThem(txtGhiChu.getText());
            
            kq.setNguoiThucHien(Session.maNhanVien);

            if (bus.luuKetQua(kq)) {
                JOptionPane.showMessageDialog(this, "Đã lưu kết quả xét nghiệm thành công!");
                
                PhieuKhamBus bus= new  PhieuKhamBus();
                //Session.maphieukham = bus.getMaPhieuKhamByBenhNhan(Session.mabenhnhan);
                
                //JOptionPane.showMessageDialog(null,   Session.maphieukham );
                bus.updateTrangThaiKQChiDinhCLS(Session.maphieukham);
                dispose();
                FrmDanhSachChoBacSiChanDoanHinhAnh q = new  FrmDanhSachChoBacSiChanDoanHinhAnh();
                q.show();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + e.getMessage());
        }
    }


    private float parseFloat(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        try { return Float.parseFloat(s.trim()); } catch (Exception e) { return 0; }
    }
    

    private void setText(JTextField txt, float val) {
        if(val != 0) txt.setText(String.valueOf(val));
        else txt.setText("");
    }
}