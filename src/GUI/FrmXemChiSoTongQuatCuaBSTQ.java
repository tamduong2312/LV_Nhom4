package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;

import BUS.BenhNhanBus;
import BUS.ChiSoTongQuatBus;
import BUS.ChuyenKhoaBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import DAO.PhieuKhamDao;
import MODEL.BenhNhan;
import MODEL.ChiSoTongQuat;
import MODEL.ChuyenKhoa;
import MODEL.Session;

public class FrmXemChiSoTongQuatCuaBSTQ extends JPanel {

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
    private String tendv;

	public FrmXemChiSoTongQuatCuaBSTQ(int idDangKy, int mabn) {
		this.idDangKyKham = idDangKy;
        this.mabn = mabn;
        this.tendv = tendv;
        
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
		textField.setEnabled(false);
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
		textField_1.setEnabled(false);
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
		textField_2.setEnabled(false);
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
		textField_3.setEnabled(false);
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
		textField_4.setEnabled(false);
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
		textField_5.setEnabled(false);
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
		textField_6.setEnabled(false);
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
		textField_7.setEnabled(false);
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
		textField_8.setEnabled(false);
		textField_8.setBorder(fieldBorder);
		textField_8.setBounds(50, 310, 610, 120);
		textField_8.setHorizontalAlignment(SwingConstants.LEFT);
		add(textField_8);
		
		JButton btnCpNht = new JButton("CẬP NHẬT");
		btnCpNht.setEnabled(false);
		btnCpNht.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCpNht.setBackground(new Color(255, 152, 0)); 
		btnCpNht.setForeground(Color.WHITE);
		btnCpNht.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnCpNht.setBounds(130, 460, 120, 40);
        btnCpNht.setOpaque(true);
        btnCpNht.setBorderPainted(false);
		add(btnCpNht);
		
		JButton btnLu = new JButton("LƯU DỮ LIỆU");
		btnLu.setEnabled(false);
		btnLu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLu.setBackground(new Color(40, 167, 69)); 
		btnLu.setForeground(Color.WHITE);
		btnLu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLu.setBounds(270, 460, 150, 40);
		btnLu.setOpaque(true);
		btnLu.setBorderPainted(false);
		add(btnLu);
		
		JButton btnTipTc = new JButton("TIẾP TỤC →");
		btnTipTc.setEnabled(false);
		btnTipTc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTipTc.setBackground(primaryColor); 
		btnTipTc.setForeground(Color.WHITE);
		btnTipTc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnTipTc.setOpaque(true);
		btnTipTc.setBorderPainted(false);
		btnTipTc.setBounds(440, 460, 120, 40);
		add(btnTipTc);

		loadData();


		ActionListener saveAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!kiemTraDuLieuNhap()) return;

				if (Session.maphieukham <= 0) {
					JOptionPane.showMessageDialog(null, "Chưa xác định được phiếu khám!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
					tq.setMaNhanVienThucHien(Session.maNhanVien);

					ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
					PhieuKhamBus bus1 = new PhieuKhamBus();
					PhieuKhamDao dao = new PhieuKhamDao();
					DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();

					int maPhieuKham = bus1.getMaPhieuKhamByBenhNhan(Session.mabenhnhan, Session.machuyenkhoa);
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
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + ex.getMessage());
				}
			}
		};

		btnLu.addActionListener(saveAction);
		btnCpNht.addActionListener(saveAction);


		btnTipTc.addActionListener(e -> {
			if (!kiemTraDuLieuNhap()) return; 

		    java.awt.Window win = SwingUtilities.getWindowAncestor(this);
		    BenhNhanBus busBN = new BenhNhanBus();
		    List<BenhNhan> listBN = busBN.getAllBN(); 
		    String tenbn = "Bệnh nhân";
		    for(BenhNhan bn : listBN) {
		    	if(bn.getMaBenhNhan() == mabn) { tenbn = bn.getHoTen(); break; }
		    }

		    switch (Session.machuyenkhoa) {
		        case 7: { 
		        	

		            PnlNhapKQXetNghiem q = new PnlNhapKQXetNghiem(this.idDangKyKham, tenbn); 
		            q.setVisible(true);
		            q.setLocationRelativeTo(null);
		            if (win != null)
		            	win.dispose(); 
		            break;
		        }
		        case 12: {
		            JFrame frame = new JFrame("Nhập kết quả Chẩn đoán hình ảnh");
		            PnlNhapKQCDHATroLy pnl = new PnlNhapKQCDHATroLy(idDangKyKham, tenbn, this.tendv);
		            frame.setContentPane(pnl); frame.setSize(1000, 750); frame.setLocationRelativeTo(null);
		            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            frame.setVisible(true);
		            if (win != null) win.dispose(); 
		            break;
		        }
		        default: {
		            FrmKhamLamSangTroLyRQ frameRQ = new FrmKhamLamSangTroLyRQ(mabn);
		            frameRQ.setVisible(true); frameRQ.setLocationRelativeTo(null);
		            if (win != null) win.dispose();
		            break;
		        }
		    }
		});
	}


	private boolean kiemTraDuLieuNhap() {
		resetBorders();
		String msg = "";

		if (textField.getText().trim().isEmpty()) {
			textField.setBorder(new LineBorder(Color.RED, 2));
			msg += "- Nhiệt độ không được để trống\n";
		} else {
			try {
				double val = Double.parseDouble(textField.getText().trim());
				if (val < 34 || val > 45) {
					textField.setBorder(new LineBorder(Color.RED, 2));
					msg += "- Nhiệt độ phải từ 34 đến 45°C\n";
				}
			} catch (Exception e) {
				textField.setBorder(new LineBorder(Color.RED, 2));
				msg += "- Nhiệt độ phải là số\n";
			}
		}


		if (textField_4.getText().trim().isEmpty()) {
			textField_4.setBorder(new LineBorder(Color.RED, 2));
			msg += "- Nhịp tim không được để trống\n";
		} else {
			try {
				int val = Integer.parseInt(textField_4.getText().trim());
				if (val < 30 || val > 220) {
					textField_4.setBorder(new LineBorder(Color.RED, 2));
					msg += "- Nhịp tim phải từ 30 đến 220 lần/phút\n";
				}
			} catch (Exception e) {
				textField_4.setBorder(new LineBorder(Color.RED, 2));
				msg += "- Nhịp tim phải là số nguyên\n";
			}
		}


		if (textField_1.getText().trim().isEmpty()) {
			textField_1.setBorder(new LineBorder(Color.RED, 2));
			msg += "- HA tâm thu không được để trống\n";
		} else {
			try {
				int val = Integer.parseInt(textField_1.getText().trim());
				if (val < 50 || val > 260) {
					textField_1.setBorder(new LineBorder(Color.RED, 2));
					msg += "- HA tâm thu phải từ 50 đến 260 mmHg\n";
				}
			} catch (Exception e) {
				textField_1.setBorder(new LineBorder(Color.RED, 2));
				msg += "- HA tâm thu phải là số nguyên\n";
			}
		}

	
		if (textField_5.getText().trim().isEmpty()) {
			textField_5.setBorder(new LineBorder(Color.RED, 2));
			msg += "- HA tâm trương không được để trống\n";
		} else {
			try {
				int val = Integer.parseInt(textField_5.getText().trim());
				if (val < 30 || val > 160) {
					textField_5.setBorder(new LineBorder(Color.RED, 2));
					msg += "- HA tâm trương phải từ 30 đến 160 mmHg\n";
				}
			} catch (Exception e) {
				textField_5.setBorder(new LineBorder(Color.RED, 2));
				msg += "- HA tâm trương phải là số nguyên\n";
			}
		}


		if (textField_2.getText().trim().isEmpty()) {
			textField_2.setBorder(new LineBorder(Color.RED, 2));
			msg += "- Nhịp thở không được để trống\n";
		} else {
			try {
				int val = Integer.parseInt(textField_2.getText().trim());
				if (val < 8 || val > 80) {
					textField_2.setBorder(new LineBorder(Color.RED, 2));
					msg += "- Nhịp thở phải từ 8 đến 80 lần/phút\n";
				}
			} catch (Exception e) {
				textField_2.setBorder(new LineBorder(Color.RED, 2));
				msg += "- Nhịp thở phải là số nguyên\n";
			}
		}

	
		if (textField_6.getText().trim().isEmpty()) {
			textField_6.setBorder(new LineBorder(Color.RED, 2));
			msg += "- Cân nặng không được để trống\n";
		} else {
			try {
				double val = Double.parseDouble(textField_6.getText().trim());
				if (val <= 0 || val > 500) {
					textField_6.setBorder(new LineBorder(Color.RED, 2));
					msg += "- Cân nặng không hợp lệ (0-500kg)\n";
				}
			} catch (Exception e) {
				textField_6.setBorder(new LineBorder(Color.RED, 2));
				msg += "- Cân nặng phải là số\n";
			}
		}


		if (textField_3.getText().trim().isEmpty()) {
			textField_3.setBorder(new LineBorder(Color.RED, 2));
			msg += "- Chiều cao không được để trống\n";
		} else {
			try {
				double val = Double.parseDouble(textField_3.getText().trim());
				if (val < 30 || val > 250) {
					textField_3.setBorder(new LineBorder(Color.RED, 2));
					msg += "- Chiều cao phải từ 30 đến 250 cm\n";
				}
			} catch (Exception e) {
				textField_3.setBorder(new LineBorder(Color.RED, 2));
				msg += "- Chiều cao phải là số\n";
			}
		}


		if (textField_7.getText().trim().isEmpty()) {
			textField_7.setBorder(new LineBorder(Color.RED, 2));
			msg += "- Chỉ số SpO2 không được để trống\n";
		} else {
			try {
				double val = Double.parseDouble(textField_7.getText().trim());
				if (val < 50 || val > 100) {
					textField_7.setBorder(new LineBorder(Color.RED, 2));
					msg += "- SpO2 phải từ 50 đến 100%\n";
				}
			} catch (Exception e) {
				textField_7.setBorder(new LineBorder(Color.RED, 2));
				msg += "- SpO2 phải là số\n";
			}
		}

	
		if (textField_8.getText().trim().isEmpty()) {
			textField_8.setBorder(new LineBorder(Color.RED, 2));
			msg += "- Ghi chú/Triệu chứng không được để trống\n";
		}

		if (!msg.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại các lỗi sau:\n" + msg, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
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
		textField_8.setBorder(defaultBorder);
	}

	public void loadData() {
	    if (Session.maDangKyHienTai <= 0) return;
	    ChiSoTongQuatBus bus = new ChiSoTongQuatBus();
	    List<ChiSoTongQuat> currentData = bus.getallChiSo(Session.maDangKyHienTai);
	    if (currentData != null && !currentData.isEmpty()) {
	        fillFields(currentData.get(0));
	    } else {
	        ChiSoTongQuat dataGanNhat = bus.getSinhHieuMoiNhatTrongNgay(this.mabn);
	        if (dataGanNhat != null) {
	            fillFields(dataGanNhat);
	            textField_8.setText((dataGanNhat.getGhiChu() != null ? dataGanNhat.getGhiChu() : ""));
	        }
	    }
	}

	private void fillFields(ChiSoTongQuat q) {
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