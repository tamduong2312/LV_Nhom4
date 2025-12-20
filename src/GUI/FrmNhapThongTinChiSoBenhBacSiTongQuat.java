package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmNhapThongTinChiSoBenhBacSiTongQuat extends JPanel {

    private static final long serialVersionUID = 1L;

    public JTextField txtNhietDo, txtHuyetApThu, txtHuyetApTruong, txtNhipTim, txtNhipTho;
    public JTextField txtCanNang, txtChieuCao;
    public JTextArea txtGhiChu;
    public JButton btnLuu;

    public FrmNhapThongTinChiSoBenhBacSiTongQuat() {
        setBackground(Color.WHITE);

        Color primary = new Color(0, 153, 255);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel lblNhietDo = new JLabel("Nhiệt độ (°C):");
        JLabel lblHuyetApThu = new JLabel("Huyết áp (tâm thu):");
        JLabel lblHuyetApTruong = new JLabel("Huyết áp (tâm trương):");
        JLabel lblNhipTim = new JLabel("Nhịp tim:");
        JLabel lblNhipTho = new JLabel("Nhịp thở:");
        JLabel lblCanNang = new JLabel("Cân nặng (kg):");
        JLabel lblChieuCao = new JLabel("Chiều cao (cm):");
        JLabel lblGhiChu = new JLabel("Ghi chú:");


        txtNhietDo = new JTextField();
        txtHuyetApThu = new JTextField();
        txtHuyetApTruong = new JTextField();
        txtNhipTim = new JTextField();
        txtNhipTho = new JTextField();
        txtCanNang = new JTextField();
        txtChieuCao = new JTextField();
        txtGhiChu = new JTextArea(4, 20);
        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);


        JLabel[] labels = {lblNhietDo, lblHuyetApThu, lblHuyetApTruong, lblNhipTim, lblNhipTho, lblCanNang, lblChieuCao, lblGhiChu};
        for (JLabel l : labels) {
            l.setFont(labelFont);
            l.setForeground(new Color(40, 40, 40));
        }
        JTextField[] fields = {txtNhietDo, txtHuyetApThu, txtHuyetApTruong, txtNhipTim, txtNhipTho, txtCanNang, txtChieuCao};
        for (JTextField f : fields) f.setFont(textFont);
        txtGhiChu.setFont(textFont);

        btnLuu = new JButton("LƯU KHÁM LÂM SÀNG");
        btnLuu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        	}
        });
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLuu.setBackground(primary);
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFocusPainted(false);


        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(
                    layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNhietDo)
                            .addComponent(lblHuyetApThu)
                            .addComponent(lblHuyetApTruong)
                            .addComponent(lblNhipTim)
                            .addComponent(lblNhipTho)
                            .addComponent(lblCanNang)
                            .addComponent(lblChieuCao)
                            .addComponent(lblGhiChu)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtNhietDo, 200, 220, Short.MAX_VALUE)
                            .addComponent(txtHuyetApThu, 200, 220, Short.MAX_VALUE)
                            .addComponent(txtHuyetApTruong, 200, 220, Short.MAX_VALUE)
                            .addComponent(txtNhipTim, 200, 220, Short.MAX_VALUE)
                            .addComponent(txtNhipTho, 200, 220, Short.MAX_VALUE)
                            .addComponent(txtCanNang, 200, 220, Short.MAX_VALUE)
                            .addComponent(txtChieuCao, 200, 220, Short.MAX_VALUE)
                            .addComponent(scrollGhiChu, 200, 220, Short.MAX_VALUE)
                        )
                )
                .addComponent(btnLuu, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
        );

        // Vertical group
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNhietDo).addComponent(txtNhietDo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHuyetApThu).addComponent(txtHuyetApThu))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHuyetApTruong).addComponent(txtHuyetApTruong))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNhipTim).addComponent(txtNhipTim))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNhipTho).addComponent(txtNhipTho))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCanNang).addComponent(txtCanNang))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChieuCao).addComponent(txtChieuCao))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblGhiChu)
                    .addComponent(scrollGhiChu))
                .addGap(14)
                .addComponent(btnLuu)
        );
    }

    // optional: allow external code gán action listener cho nút Lưu
    public void setSaveAction(ActionListener al) {
        btnLuu.addActionListener(al);
    }
}
