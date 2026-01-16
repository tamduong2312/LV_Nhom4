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
import java.text.DecimalFormat;
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
import BUS.DichVuBus;

import MODEL.ChuyenKhoa;
import MODEL.DichVu;
import MODEL.Phong;

public class FrmQuanLyDichVu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1; 
	private JTextField textField_2; 
	private JTextField textField_3; 
	private JTable table;
    
    private List<DichVu> listDichVu = new ArrayList<>();
    private List<ChuyenKhoa> listCK = new ArrayList<>();
    private List<Phong> listPhong = new ArrayList<>();
    
    private ChuyenKhoaBus ckBus = new ChuyenKhoaBus();
    private DichVuBus dvBus = new DichVuBus();
    private DichVuBus phongBus = new DichVuBus();
    
    private JComboBox<String> cmbMaCK;
    private JComboBox<String> cmbphong;
    private JComboBox<String> comboBox; 
    
    private DecimalFormat df = new DecimalFormat("#,###");
    private int selectedId = -1; 

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				FrmQuanLyDichVu frame = new FrmQuanLyDichVu();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

    private void loadDataTable() {
        listDichVu = dvBus.getDichVu();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        for (DichVu dv : listDichVu) {
            String tenPhong = getTenPhongById(dv.getMaphong());
            String tenCK = getTenCKById(dv.getMachuyenkhoa());
            
            model.addRow(new Object[]{
                dv.getMaDichVu(), 
                dv.getTenDichVu(), 
                df.format(dv.getDonGia()), 
                dv.getLoai_Dich_Vu(), 
                tenPhong, 
                tenCK
            });
        }
    }

    private void loadDataTableTIMKIEM(String keyword) {
        listDichVu = dvBus.timKiem(keyword);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        for (DichVu dv : listDichVu) {
            String tenPhong = getTenPhongById(dv.getMaphong());
            String tenCK = getTenCKById(dv.getMachuyenkhoa());
            
            model.addRow(new Object[]{
                dv.getMaDichVu(), 
                dv.getTenDichVu(), 
                df.format(dv.getDonGia()), 
                dv.getLoai_Dich_Vu(), 
                tenPhong, 
                tenCK
            });
        }
    }
    
    private void loadDataComboBox() {
        listCK = ckBus.getAllCK();
        DefaultComboBoxModel<String> boxModelCK = new DefaultComboBoxModel<>();
        for (ChuyenKhoa ck : listCK) {
            boxModelCK.addElement(ck.getTen_chuyen_khoa());
        }
        cmbMaCK.setModel(boxModelCK);
        
        listPhong = phongBus.getPhong();
        DefaultComboBoxModel<String> boxModelPhong = new DefaultComboBoxModel<>();
        for (Phong p : listPhong) {
            boxModelPhong.addElement(p.getTenphong());
        }
        cmbphong.setModel(boxModelPhong);
    }

    private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

    private double getDouble(Object value) {
        try {
            return value == null ? 0 : Double.parseDouble(value.toString().replace(",", ""));
        } catch(Exception e) { return 0; }
    }
    
    private String getTenPhongById(int id) {
        for (Phong p : listPhong) if (p.getMaphong() == id) return p.getTenphong();
        return "";
    }
    
    private String getTenCKById(int id) {
        for (ChuyenKhoa k : listCK) if (k.getMa_chuyen_khoa() == id) return k.getTen_chuyen_khoa();
        return "";
    }

	public FrmQuanLyDichVu() {
		setTitle("Quản Lý Dịch Vụ Khám Chữa Bệnh"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 680); 
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

		JLabel lblTitle = new JLabel("QUẢN LÝ DỊCH VỤ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(primaryColor);
		lblTitle.setFont(titleFont);
		lblTitle.setBounds(0, 10, 936, 40);
		contentPane.add(lblTitle);

		int labelX = 40;
		int inputX = 160;
		int startY = 80;
		int gapY = 50;
		int inputWidth = 280;
		int inputHeight = 30;

		JLabel lblTnDchV = new JLabel("Tên dịch vụ:");
		lblTnDchV.setFont(labelFont);
		lblTnDchV.setBounds(labelX, startY + gapY, 116, 25);
		contentPane.add(lblTnDchV);

		textField_1 = new JTextField();
		textField_1.setFont(inputFont);
		textField_1.setColumns(10);
		textField_1.setBounds(inputX, startY + gapY, inputWidth, inputHeight);
		textField_1.setBorder(new MatteBorder(0, 0, 2, 0, new Color(100, 100, 100)));
		textField_1.setBackground(backgroundColor);
		contentPane.add(textField_1);

		JLabel lblnGi = new JLabel("Đơn giá:");
		lblnGi.setFont(labelFont);
		lblnGi.setBounds(labelX, startY + gapY * 2, 116, 25);
		contentPane.add(lblnGi);

		textField_2 = new JTextField();
		textField_2.setFont(inputFont);
		textField_2.setColumns(10);
		textField_2.setBounds(inputX, startY + gapY * 2, inputWidth, inputHeight);
		textField_2.setBorder(new MatteBorder(0, 0, 2, 0, new Color(100, 100, 100)));
		textField_2.setBackground(backgroundColor);
		contentPane.add(textField_2);

		JLabel lblNewLabel_2_1 = new JLabel("Loại dịch vụ:");
		lblNewLabel_2_1.setFont(labelFont);
		lblNewLabel_2_1.setBounds(labelX, startY + gapY * 3, 116, 25);
		contentPane.add(lblNewLabel_2_1);

		comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"KHAM_BENH", "CLS_XET_NGHIEM", "CLS_CHAN_DOAN_HINH_ANH"}));
		comboBox.setFont(inputFont);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(inputX, startY + gapY * 3, inputWidth, inputHeight);
		contentPane.add(comboBox);

		JLabel lblNewLabel_2_1_1 = new JLabel("Phòng:");
		lblNewLabel_2_1_1.setFont(labelFont);
		lblNewLabel_2_1_1.setBounds(labelX, startY + gapY * 4, 116, 25);
		contentPane.add(lblNewLabel_2_1_1);

		cmbphong = new JComboBox<>();
		cmbphong.setFont(inputFont);
		cmbphong.setBackground(Color.WHITE);
		cmbphong.setBounds(inputX, startY + gapY * 4, inputWidth, inputHeight);
		contentPane.add(cmbphong);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Chuyên khoa:");
		lblNewLabel_2_1_1_1.setFont(labelFont);
		lblNewLabel_2_1_1_1.setBounds(labelX, startY + gapY * 5, 116, 25);
		contentPane.add(lblNewLabel_2_1_1_1);

		cmbMaCK = new JComboBox<>();
		cmbMaCK.setFont(inputFont);
		cmbMaCK.setBackground(Color.WHITE);
		cmbMaCK.setBounds(inputX, startY + gapY * 5, inputWidth, inputHeight);
		contentPane.add(cmbMaCK);

		int btnX = 550;
		int btnY = 80;
		int btnWidth = 140;
		int btnHeight = 40;
		int btnGap = 60;

		JButton btnNewButton = new JButton("Thêm Mới");
		btnNewButton.setBackground(primaryColor);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBounds(btnX, btnY, btnWidth, btnHeight);
		contentPane.add(btnNewButton);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);

		JButton btnXa = new JButton("Xóa");
		btnXa.setBackground(dangerColor);
		btnXa.setForeground(Color.WHITE);
		btnXa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnXa.setBounds(btnX, btnY + btnGap, btnWidth, btnHeight);
		contentPane.add(btnXa);
		btnXa.setContentAreaFilled(false);
		btnXa.setOpaque(true);
		btnXa.setBorderPainted(false);

		JButton btnNewButton_1_1 = new JButton("Cập Nhật");
		btnNewButton_1_1.setBackground(warningColor);
		btnNewButton_1_1.setForeground(Color.BLACK);
		btnNewButton_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(btnX, btnY + btnGap * 2, btnWidth, btnHeight);
		contentPane.add(btnNewButton_1_1);
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setOpaque(true);
		btnNewButton_1_1.setBorderPainted(false);

		JButton btnNewButton_1_1_1 = new JButton("Quay Về");
		btnNewButton_1_1_1.setBackground(secondaryColor);
		btnNewButton_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1.setOpaque(true);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton_1_1_1.setBounds(btnX, btnY + btnGap * 3, btnWidth, btnHeight);
		contentPane.add(btnNewButton_1_1_1);

		JLabel lblQuayV = new JLabel("Tìm kiếm:"); 
		lblQuayV.setFont(labelFont);
		lblQuayV.setBounds(40, 390, 80, 25);
		contentPane.add(lblQuayV);

		textField_3 = new JTextField();
		textField_3.setFont(inputFont);
		textField_3.setBounds(120, 388, 770, 30);
		textField_3.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 430, 850, 190);
		scrollPane.setBorder(new LineBorder(new Color(200, 200, 200), 1));
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(30); 
		
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã Dịch Vụ", "Tên Dịch Vụ", "Đơn Giá", "Loại Dịch Vụ", "Phòng", "Chuyên Khoa"
			}
		));
		scrollPane.setViewportView(table);
        
        loadDataComboBox();
        loadDataTable();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index == -1) return;

                selectedId = Integer.parseInt(getString(table.getValueAt(index, 0)));
                String tenDV = getString(table.getValueAt(index, 1));
                String donGia = getString(table.getValueAt(index, 2));
                String loai = getString(table.getValueAt(index, 3));
                String phong = getString(table.getValueAt(index, 4));
                String ck = getString(table.getValueAt(index, 5));

                textField_1.setText(tenDV);
                textField_2.setText(donGia.replace(",", ""));
                
                comboBox.setSelectedItem(loai);
                cmbphong.setSelectedItem(phong);
                cmbMaCK.setSelectedItem(ck);
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ten = textField_1.getText().trim();
                String giaStr = textField_2.getText().trim();
                
                if (ten.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tên dịch vụ không được để trống!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                double gia = 0;
                try {
                    gia = Double.parseDouble(giaStr);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Đơn giá phải là số!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String loai = comboBox.getSelectedItem().toString();
                int maPhong = 0;
                if(cmbphong.getSelectedIndex() >= 0) maPhong = listPhong.get(cmbphong.getSelectedIndex()).getMaphong();
                
                int maCK = 0;
                if(cmbMaCK.getSelectedIndex() >= 0) maCK = listCK.get(cmbMaCK.getSelectedIndex()).getMa_chuyen_khoa();
                
                DichVu dv = new DichVu(ten, (long)gia, loai, maPhong, maCK);
                
                if(dvBus.them(dv)) {
                    loadDataTable();
                    JOptionPane.showMessageDialog(null, "Thêm dịch vụ thành công!");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                }
            }
        });

        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần sửa!");
                    return;
                }
                
                String ten = textField_1.getText().trim();
                String giaStr = textField_2.getText().trim();
                double gia = Double.parseDouble(giaStr);
                
                String loai = comboBox.getSelectedItem().toString();
                int maPhong = listPhong.get(cmbphong.getSelectedIndex()).getMaphong();
                int maCK = listCK.get(cmbMaCK.getSelectedIndex()).getMa_chuyen_khoa();
                
                DichVu dv = new DichVu(selectedId, ten, (long)gia, loai);
                dv.setMaphong(maPhong);
                dv.setMachuyenkhoa(maCK);
                
                if(dvBus.sua(dv)) {
                    loadDataTable();
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                }
            }
        });

        btnXa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần xóa!");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dịch vụ này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dvBus.xoa(selectedId)) {
                        loadDataTable();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại!");
                    }
                }
            }
        });

        textField_3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = textField_3.getText().trim();
                if (text.isEmpty()) {
                    loadDataTable();
                } else {
                    loadDataTableTIMKIEM(text);
                }
            }
        });

        btnNewButton_1_1_1.addActionListener(e -> {
            clearForm();
          
            FrmQuanLy q = new FrmQuanLy();
            q.setVisible(true);
            dispose();
            loadDataTable();
        });
	}

    private void clearForm() {
        textField_1.setText("");
        textField_2.setText("");
        comboBox.setSelectedIndex(0);
        if(cmbphong.getItemCount() > 0) cmbphong.setSelectedIndex(0);
        if(cmbMaCK.getItemCount() > 0) cmbMaCK.setSelectedIndex(0);
        selectedId = -1;
        table.clearSelection();
    }
}