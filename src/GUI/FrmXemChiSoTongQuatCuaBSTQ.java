package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

import BUS.ChiSoTongQuatBus;
import MODEL.ChiSoTongQuat;

public class FrmXemChiSoTongQuatCuaBSTQ extends JPanel { 

    private static final long serialVersionUID = 1L;


    private JTextField txtNhietDo, txtHuyetApThu, txtHuyetApTruong, txtNhipTim, txtNhipTho;
    private JTextField txtCanNang, txtChieuCao;
    private JTextArea txtGhiChu;
    

    private int maPhieuKham;
    private ChiSoTongQuatBus bus = new ChiSoTongQuatBus();


    public FrmXemChiSoTongQuatCuaBSTQ(int maPhieuKham) {
        this.maPhieuKham = maPhieuKham;
        

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));


        JLabel lblTitle = new JLabel("THÔNG TIN CHỈ SỐ SINH TỒN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0, 102, 204));
        add(lblTitle, BorderLayout.NORTH);

        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new Color(200, 200, 200)),
                "Kết quả đo từ Trợ lý",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14),
                Color.DARK_GRAY
        ));
        add(pnlForm, BorderLayout.CENTER);


        initFields();
        

        setEditable(false);

 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;


        addInputRow(pnlForm, gbc, 0, "Nhiệt độ:", "°C", txtNhietDo, "Nhịp tim:", "lần/phút", txtNhipTim);
 
        addInputRow(pnlForm, gbc, 1, "Huyết áp (Thu):", "mmHg", txtHuyetApThu, "Huyết áp (Trương):", "mmHg", txtHuyetApTruong);
 
        addInputRow(pnlForm, gbc, 2, "Nhịp thở:", "lần/phút", txtNhipTho, "Cân nặng:", "kg", txtCanNang);
        

        gbc.gridy = 3; gbc.gridx = 0;
        pnlForm.add(createLabel("Chiều cao:"), gbc);
        gbc.gridx = 1;
        pnlForm.add(createInputWithUnit(txtChieuCao, "cm"), gbc);


        gbc.gridy = 4; gbc.gridx = 0; gbc.anchor = GridBagConstraints.NORTHWEST;
        pnlForm.add(createLabel("Ghi chú:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        pnlForm.add(new JScrollPane(txtGhiChu), gbc);


        loadData();
    }

 
    public void loadData() {
        if (maPhieuKham <= 0) {
            txtGhiChu.setText("Chưa xác định được phiếu khám.");
            return;
        }

   
        List<ChiSoTongQuat> list = bus.getallChiSo(maPhieuKham);


        if (list != null && !list.isEmpty()) {
            ChiSoTongQuat q = list.get(0); 

            txtNhietDo.setText(String.valueOf(q.getNhietDo()));
            txtHuyetApThu.setText(String.valueOf(q.getHuyetApThu())); 
            txtHuyetApTruong.setText(String.valueOf(q.getHuyetApTruong()));
            txtNhipTim.setText(String.valueOf(q.getNhipTim()));
            txtNhipTho.setText(String.valueOf(q.getNhipTho()));
            txtCanNang.setText(String.valueOf(q.getCanNang()));
            txtChieuCao.setText(String.valueOf(q.getChieuCao()));
            txtGhiChu.setText(q.getGhiChu() == null ? "" : q.getGhiChu());
        } else {
   
            txtGhiChu.setText("Trợ lý chưa nhập chỉ số sinh tồn cho phiếu này.");
      
            txtNhietDo.setText("");
            txtHuyetApThu.setText("");
            // ...
        }
    }


    private void initFields() {
        txtNhietDo = new JTextField();
        txtNhipTim = new JTextField();
        txtHuyetApThu = new JTextField();
        txtHuyetApTruong = new JTextField();
        txtNhipTho = new JTextField();
        txtCanNang = new JTextField();
        txtChieuCao = new JTextField();
        txtGhiChu = new JTextArea(3, 20);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
    }
    
    private void setEditable(boolean allow) {
        txtNhietDo.setEditable(allow);
        txtNhipTim.setEditable(allow);
        txtHuyetApThu.setEditable(allow);
        txtHuyetApTruong.setEditable(allow);
        txtNhipTho.setEditable(allow);
        txtCanNang.setEditable(allow);
        txtChieuCao.setEditable(allow);
        txtGhiChu.setEditable(allow);
    }

    private void addInputRow(JPanel p, GridBagConstraints gbc, int row, 
                             String l1, String u1, JTextField t1, 
                             String l2, String u2, JTextField t2) {
        gbc.gridy = row;
        gbc.gridx = 0; p.add(createLabel(l1), gbc);
        gbc.gridx = 1; p.add(createInputWithUnit(t1, u1), gbc);
        gbc.gridx = 2; p.add(createLabel(l2), gbc);
        gbc.gridx = 3; p.add(createInputWithUnit(t2, u2), gbc);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(80, 80, 80));
        return lbl;
    }

    private JPanel createInputWithUnit(JTextField txt, String unit) {
        JPanel p = new JPanel(new BorderLayout(5, 0));
        p.setBackground(Color.WHITE);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        p.add(txt, BorderLayout.CENTER);
        
        JLabel lblUnit = new JLabel(unit);
        lblUnit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblUnit.setForeground(Color.GRAY);
        lblUnit.setPreferredSize(new Dimension(40, 0));
        lblUnit.setHorizontalAlignment(SwingConstants.RIGHT);
        p.add(lblUnit, BorderLayout.EAST);
        return p;
    }
}