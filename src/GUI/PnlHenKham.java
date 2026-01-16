package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

// --- IMPORT LOGIC ---
import BUS.LichTaiKhamBus;
import BUS.BenhNhanBus;
import BUS.ChuyenKhoaBus;
import BUS.NhanVienBUS;
import MODEL.LichTaiKham;
import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.NhanVien;
import MODEL.Session;

public class PnlHenKham extends JPanel {

	private static final long serialVersionUID = 1L;
	private JDateChooser dateChooserNgayHen;
	private JTextField textField_1; 
	private JTable table;

	private DefaultTableModel tableModel;
	private LichTaiKhamBus lichHenBus = new LichTaiKhamBus();
	private BenhNhanBus bnBus = new BenhNhanBus();
	private NhanVienBUS nvBus = new NhanVienBUS();
	
	private int idLichHenDangChon = -1;
	
	private JButton btnHenTaiKham;
	private JButton btnSua;
	private JButton btnXoa;

	/**
	 * Create the panel.
	 */
	public PnlHenKham() {
		
		setLayout(null);
		setBackground(new Color(240, 248, 255)); 
		setSize(900, 600);

	
		Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
		Font fontText = new Font("Segoe UI", Font.PLAIN, 13);
		Color colPrimary = new Color(0, 123, 255);
		Color colDanger = new Color(220, 53, 69);  
		Color colWarning = new Color(255, 193, 7); 

		
		JLabel lblTitle = new JLabel("QUẢN LÝ LỊCH HẸN TÁI KHÁM");
		lblTitle.setBounds(0, 10, 900, 30);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(new Color(0, 102, 204));
		add(lblTitle);


		int xLabel = 40;
		int xInput = 140;
		int widthInput = 250;
		int heightComp = 30;
		int gap = 50;


		JLabel lblNgay = new JLabel("Chọn ngày:");
		lblNgay.setBounds(xLabel, 60 + gap, 100, heightComp);
		lblNgay.setFont(fontLabel);
		add(lblNgay);

		dateChooserNgayHen = new JDateChooser();
		dateChooserNgayHen.setBounds(xInput, 60 + gap, widthInput, heightComp);
		dateChooserNgayHen.setDateFormatString("dd/MM/yyyy");
		dateChooserNgayHen.setFont(fontText);
		add(dateChooserNgayHen);


		JLabel lblGhiChu = new JLabel("Ghi Chú:");
		lblGhiChu.setBounds(xLabel, 60 + gap * 2, 100, heightComp);
		lblGhiChu.setFont(fontLabel);
		add(lblGhiChu);

		textField_1 = new JTextField();
		textField_1.setBounds(xInput, 60 + gap * 2, widthInput, heightComp);
		textField_1.setFont(fontText);
		textField_1.setBorder(new LineBorder(new Color(200, 200, 200)));
		add(textField_1);


		int btnY = 60 + gap * 3 + 10;
		int btnH = 35;

		btnHenTaiKham = new JButton("Hẹn tái khám");
		btnHenTaiKham.setBounds(67, 201, 120, btnH);
		styleButton(btnHenTaiKham, colPrimary);
		add(btnHenTaiKham);

		btnSua = new JButton("Sửa");
		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(224, 201, 80, btnH);
		styleButton(btnSua, colWarning);
		btnSua.setForeground(Color.BLACK); 
		add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(328, 201, 80, btnH);
		styleButton(btnXoa, colDanger);
		add(btnXoa);

	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 250, 820, 300); 
		scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"STT", "Mã BN","Mã chuyên khoa", "Tên Bệnh Nhân", "Ngày Hẹn", "Ghi Chú", "Bác Sĩ", "Hidden_ID"
			}
		));
		

		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setWidth(0);
		

		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setGridColor(new Color(230, 230, 230));
		table.setSelectionBackground(new Color(232, 242, 254));
		table.setSelectionForeground(Color.BLACK);
		

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 14));
		header.setBackground(new Color(240, 240, 240));
		header.setForeground(new Color(50, 50, 50));
		header.setOpaque(true);
		
		scrollPane.setViewportView(table);
		
	
		this.tableModel = (DefaultTableModel) table.getModel();
		initEvents();
		
		if (!java.beans.Beans.isDesignTime()) {
			loadData();
			capNhatNgayHenTuSession();
		}
	}
	

	
	private void initEvents() {

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
			
					try {
						idLichHenDangChon = Integer.parseInt(table.getValueAt(row, 7).toString());
						
					
						String dateStr = table.getValueAt(row, 4).toString();
						Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
						dateChooserNgayHen.setDate(date);
						
					
						textField_1.setText(table.getValueAt(row, 5) != null ? table.getValueAt(row, 5).toString() : "");
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		

		btnHenTaiKham.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Session.mabenhnhan <= 0) {
					JOptionPane.showMessageDialog(null, "Chưa xác định được bệnh nhân (Phiên làm việc trống)!");
					return;
				}
				if (dateChooserNgayHen.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày tái khám!");
					return;
				}
				
	
				LichTaiKham ltk = new LichTaiKham();
				ltk.setMaBenhNhan(Session.mabenhnhan);
				ltk.setMaNhanVien(Session.maNhanVien);
				ltk.setNgayTaiKham(new java.sql.Date(dateChooserNgayHen.getDate().getTime()));
				ltk.setGhiChu(textField_1.getText());
				ltk.setMack(Session.machuyenkhoa);
				ltk.setTrangThai("CHUA_DEN");
				if(Session.maphieukham > 0) ltk.setMaPhieuKham(Session.maphieukham);
				
				if (lichHenBus.themLichHen(ltk)) {
					JOptionPane.showMessageDialog(null, "Hẹn lịch thành công!");
					loadData();
					resetForm();
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thất bại!");
				}
			}
		});
		
		// 3. NÚT SỬA
		btnSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idLichHenDangChon == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn lịch hẹn trong bảng để sửa!");
					return;
				}
				if (dateChooserNgayHen.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Ngày hẹn không được để trống!");
					return;
				}
				
				LichTaiKham ltk = new LichTaiKham();
				ltk.setId(idLichHenDangChon);
				ltk.setNgayTaiKham(new java.sql.Date(dateChooserNgayHen.getDate().getTime()));
				ltk.setGhiChu(textField_1.getText());
			
				ltk.setTrangThai("CHUA_DEN"); 
				
				if (lichHenBus.suaLichHen(ltk)) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
					loadData();
					resetForm();
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
				}
			}
		});
		

		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idLichHenDangChon == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn lịch hẹn cần xóa!");
					return;
				}
				
				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa lịch hẹn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					if (lichHenBus.xoaLichHen(idLichHenDangChon)) {
						JOptionPane.showMessageDialog(null, "Đã xóa lịch hẹn!");
						loadData();
						resetForm();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại!");
					}
				}
			}
		});
	}

    public void capNhatNgayHenTuSession() {
        int soNgayMeds = Session.maxDaysThuoc;
        JOptionPane.showMessageDialog(null, soNgayMeds);
        int khoangCach = (soNgayMeds > 0) ? soNgayMeds : 4;

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(new Date()); 
        cal.add(java.util.Calendar.DAY_OF_YEAR, khoangCach);

        // Né Thứ 7, Chủ Nhật
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        if (dayOfWeek == java.util.Calendar.SATURDAY) cal.add(java.util.Calendar.DAY_OF_YEAR, 2);
        else if (dayOfWeek == java.util.Calendar.SUNDAY) cal.add(java.util.Calendar.DAY_OF_YEAR, 1);

        dateChooserNgayHen.setDate(cal.getTime());
        dateChooserNgayHen.setEnabled(false);
        
    }
	private void loadData() {
		tableModel.setRowCount(0);

		List<LichTaiKham> list = lichHenBus.getByMaBenhNhan(Session.mabenhnhan);
		BenhNhanBus bus = new BenhNhanBus();
		
		List<BenhNhan> listbn = new ArrayList<>();
		listbn = bus.getAllBN();
		
		
		List<ChuyenKhoa>listck = new ArrayList<>();
		
		ChuyenKhoaBus bus1 = new ChuyenKhoaBus();
		
		listck = bus1.getAllCK();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int stt = 1;
		String tenbn= "";
		String tenck= "";
		
		for (LichTaiKham ltk : list) {
			for(BenhNhan bn : listbn) {
				
				if(bn.getMaBenhNhan() == ltk.getMaBenhNhan()) {
					tenbn = bn.getHoTen();
					
				}
			}
			
		for(ChuyenKhoa ck : listck) {
				
				if(ck.getMa_chuyen_khoa() == ltk.getMack()) {
					tenck = ck.getTen_chuyen_khoa();
					
				}
			}
		
		
			tableModel.addRow(new Object[] {
				stt++,
				ltk.getMaBenhNhan(),
				tenck,
				tenbn,
				sdf.format(ltk.getNgayTaiKham()),
				ltk.getGhiChu(),
			Session.TenNhanVien,
				ltk.getId() 
			});
		}
	}
	
	private void resetForm() {
		idLichHenDangChon = -1;
		dateChooserNgayHen.setDate(null);
		textField_1.setText("");
		table.clearSelection();
	}


	private void styleButton(JButton btn, Color bg) {
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setBackground(bg);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}