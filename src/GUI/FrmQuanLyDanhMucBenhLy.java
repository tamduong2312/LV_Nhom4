package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import BUS.ChuyenKhoaBus;
import BUS.DanhMucBenhLyBus;
import MODEL.ChuyenKhoa;
import MODEL.DanhMucBenhLy;

public class FrmQuanLyDanhMucBenhLy extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtma;  
	private JTextField textField_1; 
	private JTextField textField_2; 
	private JTable table;
	private JTextField textField_3; 
	

	private JComboBox<String> comboBox; 
	private DanhMucBenhLyBus benhLyBus = new DanhMucBenhLyBus();
	private ChuyenKhoaBus ckBus = new ChuyenKhoaBus();
	
	private List<DanhMucBenhLy> listBenhLy = new ArrayList<>();
	private List<ChuyenKhoa> listCK = new ArrayList<>();
	private DefaultTableModel tableModel;
	
	private int selectedId = -1; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FrmQuanLyDanhMucBenhLy frame = new FrmQuanLyDanhMucBenhLy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	private void loadDataComboBox() {
		listCK = ckBus.getAllCK();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		for (ChuyenKhoa ck : listCK) {
			model.addElement(ck.getTen_chuyen_khoa());
		}
		comboBox.setModel(model);
	}
	
	private void loadDataTable() {
		listBenhLy = benhLyBus.getAll();
		fillTable(listBenhLy);
	}
	
	private void loadDataTableTIMKIEM(String keyword) {
		listBenhLy = benhLyBus.timKiem(keyword);
		fillTable(listBenhLy);
	}
	
	private void fillTable(List<DanhMucBenhLy> list) {
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for (DanhMucBenhLy b : list) {
			tableModel.addRow(new Object[] {
				b.getId(),                 
				b.getMa_icd(),             
				b.getTen_benh(),              
				b.getTrieu_chung_goi_y(),    
				b.getChuyen_khoa_lien_quan()  
			});
		}
	}
	
	private DanhMucBenhLy getModelFromForm() {
		String maStr = txtma.getText().trim();
		String ten = textField_1.getText().trim();
		String tc = textField_2.getText().trim();
		String maICD = txtma.getText().trim();
		
		if(maStr.isEmpty() || ten.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Mã ICD và Tên bệnh không được để trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
	
	
	
	
		String tenCK = "";
		if (comboBox.getSelectedItem() != null) {
			tenCK = comboBox.getSelectedItem().toString();
		}
		
		return new DanhMucBenhLy(maICD, ten, tc, tenCK);
	}
	
	private void resetForm() {
		txtma.setText("");
		textField_1.setText("");
		textField_2.setText("");
		
		if(comboBox.getItemCount() > 0) comboBox.setSelectedIndex(0);
		
		selectedId = -1;
		table.clearSelection();
	}
	
	private void setSelectedComboItem(JComboBox<String> box, String value) {
		for (int i = 0; i < box.getItemCount(); i++) {
			if (box.getItemAt(i).equals(value)) {
				box.setSelectedIndex(i);
				return;
			}
		}
	}
	
	private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

	/**
	 * Create the frame.
	 */
	public FrmQuanLyDanhMucBenhLy() {
		setTitle("Quản Lý Danh Mục Bệnh Lý");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650); 
		setLocationRelativeTo(null); 

		Color primaryColor = new Color(0, 123, 255); 
		Color dangerColor = new Color(220, 53, 69);
		Color warningColor = new Color(255, 193, 7); 
		Color secondaryColor = new Color(108, 117, 125); 
		Color backgroundColor = new Color(245, 248, 250); 
		
		Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
		Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
		Font titleFont = new Font("Segoe UI", Font.BOLD, 24);

		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("DANH MỤC BỆNH LÝ (ICD)");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(primaryColor);
		lblTitle.setFont(titleFont);
		lblTitle.setBounds(0, 10, 884, 40);
		contentPane.add(lblTitle);


		int labelX = 40;
		int inputX = 180;
		int startY = 80;
		int gapY = 50;
		int inputWidth = 250;
		int inputHeight = 30;


		JLabel lblNewLabel = new JLabel("Mã ICD:");
		lblNewLabel.setFont(labelFont);
		lblNewLabel.setBounds(labelX, startY, 130, 25);
		contentPane.add(lblNewLabel);

		txtma = new JTextField();
		txtma.setFont(inputFont);
		txtma.setBounds(inputX, startY, inputWidth, inputHeight);
		txtma.setBorder(new MatteBorder(0, 0, 2, 0, new Color(100, 100, 100))); 
		txtma.setBackground(backgroundColor);
		contentPane.add(txtma);
		txtma.setColumns(10);


		JLabel lblTenbenh = new JLabel("Tên bệnh:");
		lblTenbenh.setFont(labelFont);
		lblTenbenh.setBounds(labelX, startY + gapY, 130, 25);
		contentPane.add(lblTenbenh);

		textField_1 = new JTextField();
		textField_1.setFont(inputFont);
		textField_1.setColumns(10);
		textField_1.setBounds(inputX, startY + gapY, inputWidth, inputHeight);
		textField_1.setBorder(new MatteBorder(0, 0, 2, 0, new Color(100, 100, 100)));
		textField_1.setBackground(backgroundColor);
		contentPane.add(textField_1);


		JLabel lblTrieuchunggoiy = new JLabel("Triệu chứng gợi ý:");
		lblTrieuchunggoiy.setFont(labelFont);
		lblTrieuchunggoiy.setBounds(labelX, startY + gapY * 2, 130, 25);
		contentPane.add(lblTrieuchunggoiy);

		textField_2 = new JTextField();
		textField_2.setFont(inputFont);
		textField_2.setColumns(10);
		textField_2.setBounds(inputX, startY + gapY * 2, inputWidth, inputHeight);
		textField_2.setBorder(new MatteBorder(0, 0, 2, 0, new Color(100, 100, 100)));
		textField_2.setBackground(backgroundColor);
		contentPane.add(textField_2);


		JLabel lblChuyenkhoalienquan = new JLabel("Chuyên khoa:");
		lblChuyenkhoalienquan.setFont(labelFont);
		lblChuyenkhoalienquan.setBounds(labelX, startY + gapY * 3, 130, 25);
		contentPane.add(lblChuyenkhoalienquan);

		comboBox = new JComboBox<>(); 
		comboBox.setFont(inputFont);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(inputX, startY + gapY * 3, inputWidth, inputHeight);
		contentPane.add(comboBox);


		int btnX = 500;
		int btnY = 80;
		int btnWidth = 120;
		int btnHeight = 40;
		int btnGap = 60;


		JButton btnNewButton = new JButton("Thêm Mới");
		btnNewButton.setBackground(primaryColor);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(btnX, btnY, btnWidth, btnHeight);
		contentPane.add(btnNewButton);

		// Xóa
		JButton btnXa = new JButton("Xóa");
		btnXa.setBackground(dangerColor);
		btnXa.setForeground(Color.WHITE);
		btnXa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnXa.setFocusPainted(false);
		btnXa.setBorderPainted(false);
		btnXa.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnXa.setBounds(btnX, btnY + btnGap, btnWidth, btnHeight);
		contentPane.add(btnXa);

		// Sửa
		JButton btnNewButton_1_1 = new JButton("Cập Nhật");
		btnNewButton_1_1.setBackground(warningColor);
		btnNewButton_1_1.setForeground(Color.BLACK); 
		btnNewButton_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.setBounds(btnX, btnY + btnGap * 2, btnWidth, btnHeight);
		contentPane.add(btnNewButton_1_1);

		// Quay về
		JButton btnNewButton_1_1_1 = new JButton("Quay Về");
		btnNewButton_1_1_1.setBackground(secondaryColor);
		btnNewButton_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton_1_1_1.setFocusPainted(false);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1_1.setBounds(btnX, btnY + btnGap * 3, btnWidth, btnHeight);
		contentPane.add(btnNewButton_1_1_1);

		// --- TÌM KIẾM ---
		int searchY = 320;
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm:");
		lblNewLabel_1.setFont(labelFont);
		lblNewLabel_1.setBounds(40, searchY, 80, 30);
		contentPane.add(lblNewLabel_1);

		textField_3 = new JTextField();
		textField_3.setFont(inputFont);
		textField_3.setBounds(120, searchY, 730, 30);
		textField_3.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		// --- BẢNG DỮ LIỆU ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 370, 810, 220);
		scrollPane.setBorder(new LineBorder(new Color(200, 200, 200), 1));
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setGridColor(new Color(230, 230, 230));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		table.getTableHeader().setBackground(new Color(232, 236, 241));
		table.getTableHeader().setForeground(Color.BLACK);
		table.setSelectionBackground(new Color(184, 218, 255));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] { "ID", "Mã ICD", "Tên Bệnh", "Triệu Chứng Gợi Ý", "Chuyên Khoa" }
		));
		scrollPane.setViewportView(table);
		
		// ====================== LOGIC EVENTS ======================
		
		loadDataComboBox();
		loadDataTable();
		
		//  Click Bảng
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if(index == -1) return;
				
				selectedId = Integer.parseInt(getString(table.getValueAt(index, 0))); 
				String maICD = getString(table.getValueAt(index, 1));
				String ten = getString(table.getValueAt(index, 2));
				String tc = getString(table.getValueAt(index, 3));
				String tenCK = getString(table.getValueAt(index, 4));
				
				txtma.setText(maICD);
				textField_1.setText(ten);
				textField_2.setText(tc);
				setSelectedComboItem(comboBox, tenCK);
			}
		});
		
		// Thêm
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DanhMucBenhLy b = getModelFromForm();
				if(b != null) {
					if(benhLyBus.them(b)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công!");
						loadDataTable();
						resetForm();
					} else {
						JOptionPane.showMessageDialog(null, "Thêm thất bại!");
					}
				}
			}
		});
		
		// Xóa
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedId == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa!");
					return;
				}
				if(JOptionPane.showConfirmDialog(null, "Xóa bệnh lý này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
					if(benhLyBus.xoa(selectedId)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
						loadDataTable();
						resetForm();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại!");
					}
				}
			}
		});
		
		// Sửa
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedId == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần sửa!");
					return;
				}
				DanhMucBenhLy b = getModelFromForm();
				if(b != null) {
					b.setId(selectedId);
					if(benhLyBus.sua(b)) {
						JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
						loadDataTable();
						resetForm();
					} else {
						JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
					}
				}
			}
		});
		
		
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
				loadDataTable();
			}
		});
		
		// Tìm kiếm
		textField_3.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String text = textField_3.getText().trim();
				if(text.isEmpty()) loadDataTable();
				else loadDataTableTIMKIEM(text);
			}
		});
	}
}