package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import GUI.FrmDangKyKhamBenh;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import BUS.BenhNhanBus;
import MODEL.BenhNhan;
import MODEL.NguoiDung;
import MODEL.NhanVien;

import javax.swing.JScrollPane;
import com.custom.clockchooser.TimeClockChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmQlbenhnhancuaLetan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtten;
	private JTextField txtcccd;
	private JTextField txtdiachi;
	private JTextField txtsdt;
	private JTextField txtemail;
	private JTextField txtnhommau;
	private JTextField txtdiung;
	private JTextField txttimkiem;
	private JTextField txtnguoigiamho;
	private JTextField txtsdtnguoigiamho;
	private List<BenhNhan> listbenhnhen = new ArrayList<>();
	private JComboBox<String> cmbngaysinh;
	private BenhNhanBus benhnhanbus = new BenhNhanBus();
	private int index;
	
	private JTable table;
	private JTextField txtnghenghiep;
	private JTextField txtghichu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyBenhNhan frame = new FrmQuanLyBenhNhan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    private void loadDataTable() {
    	listbenhnhen = benhnhanbus.getAllBN();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (BenhNhan nd : listbenhnhen) {
            model.addRow(new Object[] {nd.getMaBenhNhan(),nd.getHoTen(),nd.GioiTinh(),nd.getNgaySinh(),nd.getDiaChi(),nd.getSDT(),nd.getEmail(),nd.getNgheNghiep(),nd.getNhomMau(),
            nd.getDiUngThuoc(),
            nd.getNguoiGiamHo(),
            nd.getSDTNguoiGiamHo(),
            nd.getGhiChu(),
            nd.getCCCD(),
            
            });
        }
    }
    private void loadDataTableTIMKIEMBN(String id) {
    	listbenhnhen = benhnhanbus.TimkiemBN(id);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (BenhNhan nd : listbenhnhen) {
            model.addRow(new Object[] {nd.getMaBenhNhan(),nd.getHoTen(),nd.GioiTinh(),nd.getNgaySinh(),nd.getDiaChi(),nd.getSDT(),nd.getEmail(),nd.getNgheNghiep(),nd.getNhomMau(),
            nd.getDiUngThuoc(),
            nd.getNguoiGiamHo(),
            nd.getSDTNguoiGiamHo(),
            nd.getGhiChu(),
            nd.getCCCD(),
            
            });
        }
    }
    private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

    private long getLong(Object value) {
        return value == null ? 0 : Long.parseLong(value.toString());
    }
	/**
	 * Create the frame.
	 */
	public FrmQlbenhnhancuaLetan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       Locale.setDefault(new Locale("vi", "VN")); 
		setBounds(100, 100, 1055, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.WHITE);
		contentPane_1.setBounds(0, 0, 2010, 1000);
		contentPane.add(contentPane_1);
		
		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblHoTen.setBounds(30, 54, 80, 25);
		contentPane_1.add(lblHoTen);
		
		txtten = new JTextField();
		txtten.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtten.setBackground(new Color(245, 245, 245));
		txtten.setBounds(120, 47, 200, 25);
		contentPane_1.add(txtten);
		
		JLabel lblGT = new JLabel("Giới tính:");
		lblGT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblGT.setBounds(330, 204, 80, 25);
		contentPane_1.add(lblGT);
		
//		JComboBox<String> cbGT = new JComboBox<String>(new Object[]{});
//		cbGT.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//		cbGT.setBackground(new Color(245, 245, 245));
//		cbGT.setBounds(430, 30, 100, 25);
//		contentPane_1.add(cbGT);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNgaySinh.setBounds(30, 284, 90, 25);
		contentPane_1.add(lblNgaySinh);
		
		JDateChooser dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.getCalendarButton().setBackground(new Color(0, 123, 255));
		dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
		dateChooserNgaySinh.setBounds(120, 284, 200, 25);
		contentPane_1.add(dateChooserNgaySinh);
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCCCD.setBounds(30, 90, 80, 25);
		contentPane_1.add(lblCCCD);
		
		txtcccd = new JTextField();
		txtcccd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtcccd.setBackground(new Color(245, 245, 245));
		txtcccd.setBounds(120, 88, 200, 25);
		contentPane_1.add(txtcccd);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDiaChi.setBounds(330, 49, 80, 25);
		contentPane_1.add(lblDiaChi);
		
		txtdiachi = new JTextField();
		txtdiachi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtdiachi.setBackground(new Color(245, 245, 245));
		txtdiachi.setBounds(408, 48, 394, 25);
		contentPane_1.add(txtdiachi);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSDT.setBounds(30, 126, 92, 25);
		contentPane_1.add(lblSDT);
		
		txtsdt = new JTextField();
		txtsdt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtsdt.setBackground(new Color(245, 245, 245));
		txtsdt.setBounds(120, 126, 200, 25);
		contentPane_1.add(txtsdt);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmail.setBounds(330, 90, 80, 25);
		contentPane_1.add(lblEmail);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtemail.setBackground(new Color(245, 245, 245));
		txtemail.setBounds(408, 88, 394, 25);
		contentPane_1.add(txtemail);
		
		JLabel lblBangCap = new JLabel("Nhóm máu:");
		lblBangCap.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblBangCap.setBounds(30, 162, 80, 25);
		contentPane_1.add(lblBangCap);
		
		txtnhommau = new JTextField();
		txtnhommau.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtnhommau.setBackground(new Color(245, 245, 245));
		txtnhommau.setBounds(120, 162, 200, 25);
		contentPane_1.add(txtnhommau);
		
		JLabel lblChucVu = new JLabel("Dị ứng thuốc:");
		lblChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblChucVu.setBounds(330, 126, 80, 25);
		contentPane_1.add(lblChucVu);
		
		txtdiung = new JTextField();
		txtdiung.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtdiung.setBackground(new Color(245, 245, 245));
		txtdiung.setBounds(408, 124, 394, 25);
		contentPane_1.add(txtdiung);
		
//		JComboBox<String> cbVaiTro = new JComboBox<String>(new Object[]{});
//		cbVaiTro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//		cbVaiTro.setBackground(new Color(245, 245, 245));
//		cbVaiTro.setBounds(850, 190, 120, 25);
//		contentPane_1.add(cbVaiTro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 367, 1900, 300);
		contentPane_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {

		        int index = table.getSelectedRow();
		        if (index == -1) return;

		        int maBN = (int) table.getValueAt(index, 0);

		        String hoTen           = getString(table.getValueAt(index, 1));
		        String gioiTinh        = getString(table.getValueAt(index, 2));
		        String ngaySinh        = getString(table.getValueAt(index, 3));
		        String diaChi          = getString(table.getValueAt(index, 4));
		        long soDT              = getLong(table.getValueAt(index, 5));
		        String email           = getString(table.getValueAt(index, 6));
		        String ngheNghiep      = getString(table.getValueAt(index, 7));
		        String nhomMau         = getString(table.getValueAt(index, 8));
		        String diUngThuoc      = getString(table.getValueAt(index, 9));
		        String nguoiGiamHo     = getString(table.getValueAt(index, 10));
		        long sdtNguoiGiamHo    = getLong(table.getValueAt(index, 11));
		        String ghiChu          = getString(table.getValueAt(index, 12));
		        long cccd              = getLong(table.getValueAt(index, 13));

	
		        txtten.setText(hoTen);
		        txtcccd.setText(cccd + "");
		        txtdiachi.setText(diaChi);
		        txtsdt.setText(soDT + "");
		        txtemail.setText(email);
		        txtnghenghiep.setText(ngheNghiep);
		        txtnhommau.setText(nhomMau);
		        txtdiung.setText(diUngThuoc);
		        txtnguoigiamho.setText(nguoiGiamHo);
		        txtsdtnguoigiamho.setText(sdtNguoiGiamHo + "");
		        txtghichu.setText(ghiChu);

		        cmbngaysinh.setSelectedItem(gioiTinh);

	
		        try {
		            java.util.Date ns = java.sql.Date.valueOf(ngaySinh);
		            dateChooserNgaySinh.setDate(ns);
		        } catch (Exception ex) {
		            dateChooserNgaySinh.setDate(null);
		        }
		    }
		});


		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"mã bệnh nhân", "họ tên", "giới tính", "ngày sinh", "địa chỉ", "số điện thoại", "email", "nghề nghiệp", "nhóm máu", "dị ứng thuốc", "người giám hộ", "SDT người giám hộ", "ghi chú", "cccd"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnThemNV = new JButton("THÊM BỆNH NHÂN");
		btnThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = txtten.getText().toString();
				String nhommau = txtnhommau.getText().toString();
				String diachi = txtdiachi.getText().toString();
				String nguoigiamho = txtnguoigiamho.getText();
				String email = txtemail.getText();
				String diungthuoc = txtdiung.getText();
				String ghichu = txtghichu.getText().toString();
				String nghenghiep = txtnghenghiep.getText();
				long SDT = 0;
				try {
					SDT = Long.parseLong(txtsdt.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				long cccd = 0;
				try {
					cccd = Long.parseLong(txtcccd.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				
				long SDTnguoigiamho = 0;
				try {
					SDTnguoigiamho = Long.parseLong(txtsdtnguoigiamho.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				LocalDate ngaySinh = dateChooserNgaySinh.getDate()
					    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				String selected = cmbngaysinh.getSelectedItem().toString();
				boolean GT;

				switch (selected) {
				    case "Nam":  GT = true; break;
				    case "Nữ":   GT = false; break;
				    default:     GT = false;
				}
	            BenhNhan bn =  new BenhNhan(ten,ngaySinh,diachi,SDT,email,nghenghiep,nhommau,diungthuoc,nguoigiamho,SDTnguoigiamho,ghichu,cccd,GT);
	            benhnhanbus.ThemBN(bn);
	    		loadDataTable();
	    		JOptionPane.showMessageDialog(null, "thêm bênh nhân thành công");
	            
				
			}
		});
		btnThemNV.setForeground(Color.WHITE);
		btnThemNV.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnThemNV.setFocusPainted(false);
		btnThemNV.setBackground(new Color(0, 123, 255));
		btnThemNV.setBounds(812, 48, 224, 72);
		contentPane_1.add(btnThemNV);
		
		JButton btnXoaNV = new JButton("XÓA BỆNH NHÂN");
		btnXoaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			      try {
			            index = table.getSelectedRow();
			            if (index != -1) {
			                int id = (int) table.getValueAt(index, 0);  

			
			                int confirm = JOptionPane.showConfirmDialog(
			                        null,
			                        "Bạn có chắc chắn muốn xóa bệnh nhân này không?",
			                        "Xác nhận xóa",
			                        JOptionPane.YES_NO_OPTION,
			                        JOptionPane.WARNING_MESSAGE
			                );

		
			                if (confirm == JOptionPane.YES_OPTION) {
			                    benhnhanbus.xoaBN(id);
			                    JOptionPane.showMessageDialog(null, "Đã xóa bệnh nhân thành công!");
			                    loadDataTable();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Đã hủy thao tác xóa.");
			                }

			            } else {
			                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân cần xóa!");
			            }

			        } catch (Exception e2) {
			            JOptionPane.showMessageDialog(null, "Xóa bệnh nhân không thành công: " + e2.getMessage());
			        }
			}
		});
		btnXoaNV.setForeground(Color.WHITE);
		btnXoaNV.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnXoaNV.setFocusPainted(false);
		btnXoaNV.setBackground(Color.RED);
		btnXoaNV.setBounds(812, 124, 224, 69);
		contentPane_1.add(btnXoaNV);
		
		JButton btnSuaNV = new JButton("SỬA BỆNH NHÂN");
		btnSuaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = table.getSelectedRow();
				int id = (int) table.getValueAt(index, 0);
				
				String ten = txtten.getText().toString();
				String nhommau = txtnhommau.getText().toString();
				String diachi = txtdiachi.getText().toString();
				String nguoigiamho = txtnguoigiamho.getText();
				String email = txtemail.getText();
				String diungthuoc = txtdiung.getText();
				String ghichu = txtghichu.getText().toString();
				String nghenghiep = txtnghenghiep.getText();
				long SDT = 0;
				try {
					SDT = Long.parseLong(txtsdt.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				long cccd = 0;
				try {
					cccd = Long.parseLong(txtcccd.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				
				long SDTnguoigiamho = 0;
				try {
					SDTnguoigiamho = Long.parseLong(txtsdtnguoigiamho.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				LocalDate ngaySinh = dateChooserNgaySinh.getDate()
					    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				String selected = cmbngaysinh.getSelectedItem().toString();
				boolean GT;

				switch (selected) {
				    case "Nam":  GT = true; break;
				    case "Nữ":   GT = false; break;
				    default:     GT = false;
				}
	            BenhNhan bn =  new BenhNhan(ten,ngaySinh,diachi,SDT,email,nghenghiep,nhommau,diungthuoc,nguoigiamho,SDTnguoigiamho,ghichu,cccd,GT);
	            benhnhanbus.SuaBN(bn, id);
	    		loadDataTable();
	    		JOptionPane.showMessageDialog(null, "sửa bênh nhân thành công");
			}
		});
		btnSuaNV.setForeground(Color.WHITE);
		btnSuaNV.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSuaNV.setFocusPainted(false);
		btnSuaNV.setBackground(new Color(0, 200, 83));
		btnSuaNV.setBounds(812, 204, 224, 69);
		contentPane_1.add(btnSuaNV);
		
		JLabel lblNewLabel = new JLabel("Tìm Kiếm bệnh nhân:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(116, 324, 106, 25);
		contentPane_1.add(lblNewLabel);
		
		txttimkiem = new JTextField();
		txttimkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				 String text = txttimkiem.getText().trim();
			        if (text.isEmpty()) {
			            loadDataTable(); 
			        } else {
			            try {
			           
			            	loadDataTableTIMKIEMBN(text);
			            } catch (NumberFormatException ex) {
			              
			            }
			        }
			    
			}
		});
		txttimkiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txttimkiem.setBackground(new Color(245, 245, 245));
		txttimkiem.setBounds(221, 315, 268, 41);
		contentPane_1.add(txttimkiem);
		
		JButton btnQuayTrLi = new JButton("QUAY TRỞ LẠI");
		btnQuayTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDangKyKhamBenh ql = new FrmDangKyKhamBenh();
				ql.show();
				dispose();
			}
		});
		btnQuayTrLi.setForeground(Color.WHITE);
		btnQuayTrLi.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnQuayTrLi.setFocusPainted(false);
		btnQuayTrLi.setBackground(new Color(0, 200, 83));
		btnQuayTrLi.setBounds(815, 284, 221, 72);
		contentPane_1.add(btnQuayTrLi);
		
		JLabel lblSinThoi = new JLabel("Người giám hộ :");
		lblSinThoi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSinThoi.setBounds(16, 198, 106, 25);
		contentPane_1.add(lblSinThoi);
		
		txtnguoigiamho = new JTextField();
		txtnguoigiamho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtnguoigiamho.setBackground(new Color(245, 245, 245));
		txtnguoigiamho.setBounds(120, 198, 200, 27);
		contentPane_1.add(txtnguoigiamho);
		
		 cmbngaysinh = new JComboBox();
		cmbngaysinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cmbngaysinh.setBounds(385, 206, 88, 22);
		contentPane_1.add(cmbngaysinh);
		
		JLabel lblGhiCh = new JLabel("SDT người giám hộ (nếu có):");
		lblGhiCh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblGhiCh.setBounds(330, 162, 168, 25);
		contentPane_1.add(lblGhiCh);
		
		txtsdtnguoigiamho = new JTextField();
		txtsdtnguoigiamho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtsdtnguoigiamho.setBackground(new Color(245, 245, 245));
		txtsdtnguoigiamho.setBounds(499, 162, 303, 25);
		contentPane_1.add(txtsdtnguoigiamho);
		
		JLabel lblGT_1 = new JLabel("Nghề nghiệp:");
		lblGT_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblGT_1.setBounds(483, 204, 88, 25);
		contentPane_1.add(lblGT_1);
		
		txtnghenghiep = new JTextField();
		txtnghenghiep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtnghenghiep.setBackground(new Color(245, 245, 245));
		txtnghenghiep.setBounds(569, 202, 233, 25);
		contentPane_1.add(txtnghenghiep);
		
		JLabel lblGhiCh_1 = new JLabel("Ghi chú:");
		lblGhiCh_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblGhiCh_1.setBounds(30, 236, 90, 25);
		contentPane_1.add(lblGhiCh_1);
		
		txtghichu = new JTextField();
		txtghichu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtghichu.setBackground(new Color(245, 245, 245));
		txtghichu.setBounds(120, 234, 200, 27);
		contentPane_1.add(txtghichu);
		loadDataTable();
	}
}
