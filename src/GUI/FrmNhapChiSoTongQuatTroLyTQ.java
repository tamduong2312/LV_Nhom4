package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;

import BUS.ChiSoTongQuatBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import DAO.PhieuKhamDao;
import MODEL.ChiSoTongQuat;
import MODEL.Session;

public class FrmNhapChiSoTongQuatTroLyTQ extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;    
	private JTextField textField_1; 
	private JTextField textField_2;  
	private JTextField textField_3; 
	private JTextField textField_4;  
	private JTextField textField_5;  
	private JTextField textField_6;  
	private JTextField textField_7;
	private JTextField textField_8;  
	
	private int idDangKyKham; 
    private int mabn;

	/**
	 * Create the panel.
	 */
	public FrmNhapChiSoTongQuatTroLyTQ( int mabn) {
		//this.idDangKyKham = idDangKy;
        this.mabn = mabn;
        

		setBackground(new Color(248, 249, 250));
		setLayout(null);
		
		Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
		Font textFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontHeader = new Font("Segoe UI", Font.BOLD, 22);
        Color colHeader = new Color(0, 102, 204);
		Color primaryColor = new Color(0, 102, 204);
		Color textColor = new Color(50, 50, 50);
		LineBorder fieldBorder = new LineBorder(new Color(200, 200, 200), 1);

		JLabel lblNewLabel = new JLabel("Nhiệt độ (°C)");
		lblNewLabel.setForeground(textColor);
		lblNewLabel.setFont(labelFont);
		lblNewLabel.setBounds(50, 70, 100, 25);
		add(lblNewLabel);
		
	    JLabel lblTitle = new JLabel("THÔNG TIN CHỈ SỐ SINH TỒN");
	    lblTitle.setBounds(0, 20, 900, 40); 
	    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitle.setFont(fontHeader);
	    lblTitle.setForeground(colHeader);
	    add(lblTitle);

		textField = new JTextField();
		textField.setFont(textFont);
		textField.setBorder(fieldBorder);
		textField.setBounds(160, 67, 150, 32); 
		add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Huyết áp thu");
		lblNewLabel_1.setForeground(textColor);
		lblNewLabel_1.setFont(labelFont);
		lblNewLabel_1.setBounds(50, 120, 100, 25);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setFont(textFont);
		textField_1.setBorder(fieldBorder);
		textField_1.setBounds(160, 117, 150, 32);
		add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nhịp thở");
		lblNewLabel_2.setForeground(textColor);
		lblNewLabel_2.setFont(labelFont);
		lblNewLabel_2.setBounds(50, 170, 100, 25);
		add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setFont(textFont);
		textField_2.setBorder(fieldBorder);
		textField_2.setBounds(160, 167, 150, 32);
		add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("Chiều cao (cm)");
		lblNewLabel_3.setForeground(textColor);
		lblNewLabel_3.setFont(labelFont);
		lblNewLabel_3.setBounds(50, 220, 100, 25);
		add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setFont(textFont);
		textField_3.setBorder(fieldBorder);
		textField_3.setBounds(160, 217, 150, 32);
		add(textField_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nhịp tim");
		lblNewLabel_4.setForeground(textColor);
		lblNewLabel_4.setFont(labelFont);
		lblNewLabel_4.setBounds(400, 70, 100, 25);
		add(lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setFont(textFont);
		textField_4.setBorder(fieldBorder);
		textField_4.setBounds(510, 67, 150, 32);
		add(textField_4);
		
		JLabel lblNewLabel_5 = new JLabel("Huyết áp trương");
		lblNewLabel_5.setForeground(textColor);
		lblNewLabel_5.setFont(labelFont);
		lblNewLabel_5.setBounds(400, 120, 110, 25);
		add(lblNewLabel_5);

		textField_5 = new JTextField();
		textField_5.setFont(textFont);
		textField_5.setBorder(fieldBorder);
		textField_5.setBounds(510, 117, 150, 32);
		add(textField_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cân nặng (kg)");
		lblNewLabel_6.setForeground(textColor);
		lblNewLabel_6.setFont(labelFont);
		lblNewLabel_6.setBounds(400, 170, 100, 25);
		add(lblNewLabel_6);

		textField_6 = new JTextField();
		textField_6.setFont(textFont);
		textField_6.setBorder(fieldBorder);
		textField_6.setBounds(510, 167, 150, 32);
		add(textField_6);
		
		JLabel lblNewLabel_7 = new JLabel("Chỉ số SpO2 (%)");
		lblNewLabel_7.setForeground(textColor);
		lblNewLabel_7.setFont(labelFont);
		lblNewLabel_7.setBounds(400, 220, 100, 25);
		add(lblNewLabel_7);

		textField_7 = new JTextField();
		textField_7.setFont(textFont);
		textField_7.setBorder(fieldBorder);
		textField_7.setBounds(510, 217, 150, 32);
		add(textField_7);
		
		JLabel lblNewLabel_3_1 = new JLabel("Ghi chú / Triệu chứng");
		lblNewLabel_3_1.setForeground(textColor);
		lblNewLabel_3_1.setFont(labelFont);
		lblNewLabel_3_1.setBounds(50, 280, 200, 25);
		add(lblNewLabel_3_1);

		textField_8 = new JTextField();
		textField_8.setFont(textFont);
		textField_8.setBorder(fieldBorder);
		textField_8.setBounds(50, 310, 610, 120);
		textField_8.setHorizontalAlignment(SwingConstants.LEFT);
		add(textField_8);
		
		JButton btnCpNht = new JButton("CẬP NHẬT");
		btnCpNht.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCpNht.setBackground(new Color(255, 152, 0)); 
		btnCpNht.setForeground(Color.WHITE);
		btnCpNht.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnCpNht.setFocusPainted(false);
		btnCpNht.setBorder(BorderFactory.createEmptyBorder());
		btnCpNht.setBounds(130, 460, 120, 40);
        btnCpNht.setContentAreaFilled(false);
        btnCpNht.setOpaque(true);
        btnCpNht.setBorderPainted(false);

		add(btnCpNht);
		
		JButton btnLu = new JButton("LƯU DỮ LIỆU");
		btnLu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLu.setBackground(new Color(40, 167, 69)); 
		btnLu.setForeground(Color.WHITE);
		btnLu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLu.setFocusPainted(false);
		btnLu.setBorder(BorderFactory.createEmptyBorder());
		btnLu.setBounds(270, 460, 150, 40);
		btnLu.setContentAreaFilled(false);
		btnLu.setOpaque(true);
		btnLu.setBorderPainted(false);

		add(btnLu);
		
		JButton btnTipTc = new JButton("TIẾP TỤC →");
		btnTipTc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTipTc.setBackground(primaryColor); 
		btnTipTc.setForeground(Color.WHITE);
		btnTipTc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnTipTc.setFocusPainted(false);
		btnTipTc.setBorder(BorderFactory.createEmptyBorder());
		btnTipTc.setContentAreaFilled(false);
		btnTipTc.setOpaque(true);
		btnTipTc.setBorderPainted(false);

		btnTipTc.setBounds(440, 460, 120, 40);
		add(btnTipTc);


		
		loadData();

		ActionListener saveAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetBorders(); 
				
				if (!validateAllInputs()) {
					JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào không hợp lệ hoặc sai định dạng!\nVui lòng kiểm tra các ô viền đỏ.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (Session.maphieukham <= 0) {
					JOptionPane.showMessageDialog(null, "Chưa xác định được bệnh nhân!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					ChiSoTongQuat tq = new ChiSoTongQuat();
					tq.setMaPhieuKham(Session.maphieukham);
					tq.setNhietDo(Double.parseDouble(textField.getText().trim()));
					tq.setHuyetApTamThu(Integer.parseInt(textField_1.getText().trim()));
					tq.setNhipTho(Integer.parseInt(textField_2.getText().trim()));
					tq.setChieuCao(Double.parseDouble(textField_3.getText().trim()));
					tq.setNhipTim(Integer.parseInt(textField_4.getText().trim()));
					tq.setHuyetApTamTruong(Integer.parseInt(textField_5.getText().trim()));
					tq.setCanNang(Double.parseDouble(textField_6.getText().trim()));
					tq.setSpo2(Double.parseDouble(textField_7.getText().trim()));
					tq.setGhiChu(textField_8.getText().trim());

					ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
					PhieuKhamBus bus1 = new PhieuKhamBus();
					PhieuKhamDao dao = new PhieuKhamDao();
					DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();

					int maPhieuKham = bus1.getMaPhieuKhamByBenhNhan(Session.mabenhnhan,Session.machuyenkhoa);
					boolean isUpdate = (e.getSource() == btnCpNht);
					boolean success = isUpdate ? bus.updateSinhHieu(tq, maPhieuKham) : bus.luuChiSo(tq);

					if (success) {
						if (isUpdate && maPhieuKham > 0) {
							dkBus.updateTrangThaiChoKhamBS(maPhieuKham);
						} else {
							dao.updateTrangThai(maPhieuKham);
							dkBus.updateTrangThaiChoKhamBS(idDangKyKham);
						}
						JOptionPane.showMessageDialog(null, isUpdate ? "Cập nhật thành công!" : "Lưu thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Thao tác thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + ex.getMessage());
				}
			}
		};

		btnLu.addActionListener(saveAction);
		btnCpNht.addActionListener(saveAction);

		btnTipTc.addActionListener(e -> {
			java.awt.Window win = SwingUtilities.getWindowAncestor(this);
			new FrmKhamLamSangTroLyRQ(mabn).setVisible(true);
			if (win != null) win.dispose();
		});
	}


	public void loadData() {
		if (Session.maphieukham <= 0) return;
		ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
		List<ChiSoTongQuat> list = bus.getallChiSo(Session.maphieukham);
		if (list != null && !list.isEmpty()) {
			ChiSoTongQuat q = list.get(0);
			textField.setText(String.valueOf(q.getNhietDo()));
			textField_1.setText(String.valueOf(q.getHuyetApTamThu()));
			textField_2.setText(String.valueOf(q.getNhipTho()));
			textField_3.setText(String.valueOf(q.getChieuCao()));
			textField_4.setText(String.valueOf(q.getNhipTim()));
			textField_5.setText(String.valueOf(q.getHuyetApTamTruong()));
			textField_6.setText(String.valueOf(q.getCanNang()));
			textField_7.setText(String.valueOf(q.getSpo2()));
			textField_8.setText(q.getGhiChu() == null ? "" : q.getGhiChu());
		}
	}


	private boolean validateAllInputs() {
		boolean isValid = true;
		if (!checkConstraint(textField, 34.0, 45.0, "Nhiệt độ")) isValid = false;
		if (!checkConstraint(textField_4, 30, 220, "Nhịp tim")) isValid = false;
		if (!checkConstraint(textField_1, 50, 260, "HA tâm thu")) isValid = false;
		if (!checkConstraint(textField_5, 30, 160, "HA tâm trương")) isValid = false;
		if (!checkConstraint(textField_2, 8, 80, "Nhịp thở")) isValid = false;
		if (!checkConstraint(textField_6, 1.0, 500.0, "Cân nặng")) isValid = false;
		if (!checkConstraint(textField_3, 30.0, 250.0, "Chiều cao")) isValid = false;
		if (!checkConstraint(textField_7, 50.0, 100.0, "SpO2")) isValid = false;
		return isValid;
	}

	private boolean checkConstraint(JTextField field, double min, double max, String name) {
		try {
			String valStr = field.getText().trim();
			if (valStr.isEmpty()) throw new Exception();
			double val = Double.parseDouble(valStr);
			if (val < min || val > max) throw new Exception();
			return true;
		} catch (Exception e) {
			field.setBorder(new LineBorder(Color.RED, 2)); 
			return false;
		}
	}

	private void resetBorders() {
		LineBorder defaultBorder = new LineBorder(new Color(200, 200, 200), 1);
		textField.setBorder(defaultBorder);
		textField_1.setBorder(defaultBorder);
		textField_2.setBorder(defaultBorder);
		textField_3.setBorder(defaultBorder);
		textField_4.setBorder(defaultBorder);
		textField_5.setBorder(defaultBorder);
		textField_6.setBorder(defaultBorder);
		textField_7.setBorder(defaultBorder);
	}
}