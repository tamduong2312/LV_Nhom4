package GUI;

import javax.swing.*;

import BUS.BenhNhanBus;
import MODEL.BenhNhan;
import MODEL.Session;

import java.awt.*;

public class FrmHienThiTTBN extends JPanel {

    private static final long serialVersionUID = 1L;
private BenhNhanBus bnbus = new BenhNhanBus();

    private JTextField txtHoTen, txtNgaySinh, txtDiaChi, txtSoDienThoai, txtEmail;
    private JTextField txtNgheNghiep, txtNhomMau, txtDiUngThuoc, txtNguoiGiamHo;
    private JTextField txtSDTNguoiGiamHo, txtGhiChu, txtCCCD, txtGioiTinh;
public void loaddata() {
	
	
	BenhNhan bn = bnbus.get1BN(Session.mabenhnhan);
	if (bn != null) {
	    txtHoTen.setText(bn.getHoTen());
	    txtNgaySinh.setText(bn.getNgaySinh().toString());
	    txtDiaChi.setText(bn.getDiaChi());
	    txtSoDienThoai.setText(String.valueOf(bn.getSDT()));
	    txtEmail.setText(bn.getEmail());
	    txtNgheNghiep.setText(bn.getNgheNghiep());
	    txtNhomMau.setText(bn.getNhomMau());
	    txtDiUngThuoc.setText(bn.getDiUngThuoc());
	    txtNguoiGiamHo.setText(bn.getNguoiGiamHo());
	    txtSDTNguoiGiamHo.setText(String.valueOf(bn.getSDTNguoiGiamHo()));
	    txtGhiChu.setText(bn.getGhiChu());
	    txtCCCD.setText(String.valueOf(bn.getCCCD()));
	    txtGioiTinh.setText(bn.getGT() ? "Nam" : "Nữ");
	}

}
    public FrmHienThiTTBN() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(850, 500));

        JLabel lblTitle = new JLabel("THÔNG TIN BỆNH NHÂN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);


        JLabel lblHoTen = new JLabel("Họ tên:");
        txtHoTen = new JTextField(20);

        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        txtNgaySinh = new JTextField(20);

        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        txtDiaChi = new JTextField(20);

        JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
        txtSoDienThoai = new JTextField(20);

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);

        JLabel lblNgheNghiep = new JLabel("Nghề nghiệp:");
        txtNgheNghiep = new JTextField(20);

        JLabel lblNhomMau = new JLabel("Nhóm máu:");
        txtNhomMau = new JTextField(20);

        JLabel lblDiUngThuoc = new JLabel("Dị ứng thuốc:");
        txtDiUngThuoc = new JTextField(20);

        JLabel lblNguoiGiamHo = new JLabel("Người giám hộ:");
        txtNguoiGiamHo = new JTextField(20);

        JLabel lblSDTGiamHo = new JLabel("SĐT người giám hộ:");
        txtSDTNguoiGiamHo = new JTextField(20);

        JLabel lblGhiChu = new JLabel("Ghi chú:");
        txtGhiChu = new JTextField(20);

        JLabel lblCCCD = new JLabel("CCCD:");
        txtCCCD = new JTextField(20);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        txtGioiTinh = new JTextField(20);


        for (JLabel lbl : new JLabel[]{lblHoTen, lblNgaySinh, lblDiaChi, lblSoDienThoai, lblEmail,
                lblNgheNghiep, lblNhomMau, lblDiUngThuoc, lblNguoiGiamHo,
                lblSDTGiamHo, lblGhiChu, lblCCCD, lblGioiTinh}) {
            lbl.setFont(font);
        }

        for (JTextField txt : new JTextField[]{txtHoTen, txtNgaySinh, txtDiaChi, txtSoDienThoai, txtEmail,
                txtNgheNghiep, txtNhomMau, txtDiUngThuoc, txtNguoiGiamHo,
                txtSDTNguoiGiamHo, txtGhiChu, txtCCCD, txtGioiTinh}) {
            txt.setFont(font);
        }
        loaddata();

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitle)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblHoTen)
                                .addComponent(lblNgaySinh)
                                .addComponent(lblDiaChi)
                                .addComponent(lblSoDienThoai)
                                .addComponent(lblEmail)
                                .addComponent(lblNgheNghiep)
                                .addComponent(lblNhomMau))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtHoTen)
                                .addComponent(txtNgaySinh)
                                .addComponent(txtDiaChi)
                                .addComponent(txtSoDienThoai)
                                .addComponent(txtEmail)
                                .addComponent(txtNgheNghiep)
                                .addComponent(txtNhomMau))
                        .addGap(40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDiUngThuoc)
                                .addComponent(lblNguoiGiamHo)
                                .addComponent(lblSDTGiamHo)
                                .addComponent(lblGhiChu)
                                .addComponent(lblCCCD)
                                .addComponent(lblGioiTinh))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtDiUngThuoc)
                                .addComponent(txtNguoiGiamHo)
                                .addComponent(txtSDTNguoiGiamHo)
                                .addComponent(txtGhiChu)
                                .addComponent(txtCCCD)
                                .addComponent(txtGioiTinh)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHoTen).addComponent(txtHoTen)
                        .addComponent(lblDiUngThuoc).addComponent(txtDiUngThuoc))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNgaySinh).addComponent(txtNgaySinh)
                        .addComponent(lblNguoiGiamHo).addComponent(txtNguoiGiamHo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDiaChi).addComponent(txtDiaChi)
                        .addComponent(lblSDTGiamHo).addComponent(txtSDTNguoiGiamHo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSoDienThoai).addComponent(txtSoDienThoai)
                        .addComponent(lblGhiChu).addComponent(txtGhiChu))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEmail).addComponent(txtEmail)
                        .addComponent(lblCCCD).addComponent(txtCCCD))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNgheNghiep).addComponent(txtNgheNghiep)
                        .addComponent(lblGioiTinh).addComponent(txtGioiTinh))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNhomMau).addComponent(txtNhomMau))
        );
    }
}
