package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import BUS.ChiSoTongQuatBus;
import BUS.DangKyKhamBenhBus;
import DAO.PhieuKhamDao;
import MODEL.ChiSoTongQuat;
import MODEL.Session;

public class FrmNhapChiSoTongQuatTroLyTQ extends JPanel {

    private static final long serialVersionUID = 1L;

    public JTextField txtNhietDo, txtHuyetApThu, txtHuyetApTruong, txtNhipTim, txtNhipTho;
    public JTextField txtCanNang, txtChieuCao;
    public JTextArea txtGhiChu;
    public JButton btnLuu;
    private static int id;

    public FrmNhapChiSoTongQuatTroLyTQ(int id) {
        this.id = id;
        
    
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

    
        JLabel lblTitle = new JLabel("NHẬP CHỈ SỐ SINH TỒN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 102, 204));
        add(lblTitle, BorderLayout.NORTH);

        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Thông tin chi tiết",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(80, 80, 80)
        ));
        
        add(pnlForm, BorderLayout.CENTER);


        GridBagConstraints gbcBase = new GridBagConstraints();
        gbcBase.insets = new Insets(8, 10, 8, 10);
        gbcBase.fill = GridBagConstraints.HORIZONTAL;
        gbcBase.weightx = 0.5;


        txtNhietDo = new JTextField();
        txtNhipTim = new JTextField();
        txtHuyetApThu = new JTextField();
        txtHuyetApTruong = new JTextField();
        txtNhipTho = new JTextField();
        txtCanNang = new JTextField();
        txtChieuCao = new JTextField();
        txtGhiChu = new JTextArea(4, 20);


        

        addInputRow(pnlForm, gbcBase, 0, 
            "Nhiệt độ:", "°C", txtNhietDo, 
            "Nhịp tim:", "lần/phút", txtNhipTim
        );

     
        addInputRow(pnlForm, gbcBase, 1, 
            "Huyết áp (Tâm thu):", "mmHg", txtHuyetApThu, 
            "Huyết áp (Tâm trương):", "mmHg", txtHuyetApTruong
        );

        // DÒNG 3: Nhịp thở & Cân nặng
        addInputRow(pnlForm, gbcBase, 2, 
            "Nhịp thở:", "lần/phút", txtNhipTho, 
            "Cân nặng:", "kg", txtCanNang
        );

  
        GridBagConstraints gbcRow4 = (GridBagConstraints) gbcBase.clone(); 
        gbcRow4.gridy = 3;
        
        gbcRow4.gridx = 0;
        pnlForm.add(createLabel("Chiều cao:"), (GridBagConstraints) gbcRow4.clone());
        
        gbcRow4.gridx = 1;
        pnlForm.add(createInputWithUnit(txtChieuCao, "cm"), (GridBagConstraints) gbcRow4.clone());


        GridBagConstraints gbcRow5 = (GridBagConstraints) gbcBase.clone();
        gbcRow5.gridy = 4;
        gbcRow5.gridx = 0;
        gbcRow5.anchor = GridBagConstraints.NORTHWEST;
        pnlForm.add(createLabel("Ghi chú:"), (GridBagConstraints) gbcRow5.clone());

        txtGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);
        
        gbcRow5.gridx = 1;
        gbcRow5.gridwidth = 3; 
        gbcRow5.weighty = 1.0; 
        gbcRow5.fill = GridBagConstraints.BOTH;
        pnlForm.add(scrollGhiChu, (GridBagConstraints) gbcRow5.clone());


        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton.setBackground(Color.WHITE);
        pnlButton.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLuu = new JButton("LƯU ĐO CHỈ SỐ TỔNG QUÁT");
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLuu.setBackground(new Color(40, 167, 69)); // Xanh lá
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFocusPainted(false);
        btnLuu.setPreferredSize(new Dimension(250, 45));
        btnLuu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        pnlButton.add(btnLuu);
        add(pnlButton, BorderLayout.SOUTH);

   
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLyLuuDuLieu();
            }
        });
    }

 

    private void addInputRow(JPanel panel, GridBagConstraints baseGbc, int row, 
                             String lbl1, String unit1, JTextField txt1,
                             String lbl2, String unit2, JTextField txt2) {

        GridBagConstraints gbc = (GridBagConstraints) baseGbc.clone();
        gbc.gridy = row;
        
     
        gbc.gridx = 0; 
        panel.add(createLabel(lbl1), (GridBagConstraints) gbc.clone());
        
     
        gbc.gridx = 1; 
        panel.add(createInputWithUnit(txt1, unit1), (GridBagConstraints) gbc.clone());
        
   
        gbc.gridx = 2; 
        panel.add(createLabel(lbl2), (GridBagConstraints) gbc.clone());
        
   
        gbc.gridx = 3; 
        panel.add(createInputWithUnit(txt2, unit2), (GridBagConstraints) gbc.clone());
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(60, 60, 60));
        return lbl;
    }

    private JPanel createInputWithUnit(JTextField txt, String unit) {
        JPanel p = new JPanel(new BorderLayout(5, 0));
        p.setBackground(Color.WHITE);
        
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        p.add(txt, BorderLayout.CENTER);
        
        JLabel lblUnit = new JLabel(unit);
        lblUnit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblUnit.setForeground(Color.GRAY);
        lblUnit.setPreferredSize(new Dimension(55, 0)); 
        p.add(lblUnit, BorderLayout.EAST);
        
        return p;
    }

 
    private void xuLyLuuDuLieu() {
        String strNhietDo = txtNhietDo.getText().trim();
        String strHuyetApThu = txtHuyetApThu.getText().trim();
        String strHuyetApTruong = txtHuyetApTruong.getText().trim();
        String strNhipTim = txtNhipTim.getText().trim();
        String strNhipTho = txtNhipTho.getText().trim();
        String strCanNang = txtCanNang.getText().trim();
        String strChieuCao = txtChieuCao.getText().trim();
        String ghiChu = txtGhiChu.getText().trim();

        if (strNhietDo.isEmpty() || strHuyetApThu.isEmpty() || strHuyetApTruong.isEmpty() ||
            strNhipTim.isEmpty() || strNhipTho.isEmpty() || strCanNang.isEmpty() || strChieuCao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ các chỉ số!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double nhietDo = Double.parseDouble(strNhietDo);
            if (nhietDo < 34 || nhietDo > 45) {
                JOptionPane.showMessageDialog(null, "Nhiệt độ không hợp lý (34 - 45°C)!"); 
                txtNhietDo.requestFocus(); return;
            }
            int huyetApThu = Integer.parseInt(strHuyetApThu);
            int huyetApTruong = Integer.parseInt(strHuyetApTruong);
            int nhipTim = Integer.parseInt(strNhipTim);
            int nhipTho = Integer.parseInt(strNhipTho);
            double canNang = Double.parseDouble(strCanNang);
            double chieuCao = Double.parseDouble(strChieuCao);

            ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
            PhieuKhamDao d = new PhieuKhamDao();
            DangKyKhamBenhBus c = new DangKyKhamBenhBus();

            ChiSoTongQuat tq = new ChiSoTongQuat(Session.maphieukham, nhietDo, huyetApThu, huyetApTruong, nhipTim, nhipTho, canNang, chieuCao, ghiChu);
            
            if (bus.luuChiSo(tq)) { 
                d.updateTrangThai(Session.maphieukham);
                c.updateTrangThaiChoKhamBS(id);
                JOptionPane.showMessageDialog(null, "Lưu chỉ số sinh tồn thành công!");
                
            
                
            } else {
                JOptionPane.showMessageDialog(null, "Lưu thất bại, vui lòng kiểm tra lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập số (không nhập chữ)!", "Sai định dạng", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + e2.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}