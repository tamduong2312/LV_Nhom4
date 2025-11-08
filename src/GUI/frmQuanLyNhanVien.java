package GUI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.custom.clockchooser.TimeClockChooser;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

import BUS.ChuyenKhoaBus;
import BUS.DangNhapBus;
import BUS.NhanVienBUS;
import MODEL.ChuyenKhoa;
import MODEL.NguoiDung;
import MODEL.NhanVien;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class frmQuanLyNhanVien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private NhanVienBUS nvbus =  new NhanVienBUS();
	private ChuyenKhoaBus ckbus =  new ChuyenKhoaBus();
	private List<NhanVien> listnv = new ArrayList<>();
	private List<ChuyenKhoa> listck = new ArrayList<>();
	private JComboBox<String> cmbMaCK;
	private JComboBox<String> cmbMaND ;
	private DangNhapBus dnbus =  new DangNhapBus();
	private List<NguoiDung> listnguoidung = new ArrayList<>();

	
	private int index;
	private JTextField txttimkiem;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmQuanLyNhanVien frame = new frmQuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 private void loadDataComboBoxMaND() {
		 listnguoidung = dnbus.getalltk();
	        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
	        for (NguoiDung ck : listnguoidung) {
	            boxModel.addElement(ck.getMaNguoiDung() +"-" + ck.getTenDangNhap());
	        }
	        cmbMaND.setModel(boxModel);
	    }
	
	
	
	 private void loadDataComboBox() {
		 listck = ckbus.getAllCK();
	        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
	        boxModel.addElement("");
	        for (ChuyenKhoa ck : listck) {
	            boxModel.addElement(ck.getTen_chuyen_khoa());
	        }
	        cmbMaCK.setModel(boxModel);
	    }
	
    private void loadDataTable() {
    	listnv = nvbus.getAllNV();
   	 listnguoidung = dnbus.getalltk();

    	  DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
          defaultTableModel.setNumRows(0);
    	  for(NhanVien nvs : listnv) {
    		  for(NguoiDung nd: listnguoidung) {
    			  if(nvs.getMaNguoiDung() == nd.getMaNguoiDung()) {
    				  
    				  defaultTableModel.addRow(new Object[]{
      			        	
        	        		  nvs.getMaNV(),
        	        		  nvs.getHoTen(),
        	        		  nvs.GioiTinh(),
        	        		  nvs.getNgay_sinh(),
        	          		  nvs.getCCCD(),
        	          		  nvs.getDiaChi(),
        	          		  nvs.getSDT(),
        	          		  nvs.getEmail(),
        	          		  nvs.getBangCap(),
        	          		  nvs.getChucVu(),
        	          		  nvs.getMaChuyenKhoa(),
        	          		  nvs.getNgayVaoLam(),
        	 
        	          		  nd.getMaNguoiDung()+"-"+ nd.getTenDangNhap(),
        	          		  nvs.getCalam(),
        	          		  nvs.getRole(),
        	          				  
        	        		
        	   
        	              });
    			  }
    		
    		  }
        
          }
          table.setModel(defaultTableModel);
    	
    }
    
    
    private void loadDataTIMKIEM(String id) {
    	listnv = nvbus.TimkiemNV(id);
   	 listnguoidung = dnbus.getalltk();


    	  DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
          defaultTableModel.setNumRows(0);
    	  for(NhanVien nvs : listnv) {
    		  for(NguoiDung nd: listnguoidung) {
    			  if(nvs.getMaNguoiDung() == nd.getMaNguoiDung()) {
    				  
    				  defaultTableModel.addRow(new Object[]{
      			        	
        	        		  nvs.getMaNV(),
        	        		  nvs.getHoTen(),
        	        		  nvs.GioiTinh(),
        	        		  nvs.getNgay_sinh(),
        	          		  nvs.getCCCD(),
        	          		  nvs.getDiaChi(),
        	          		  nvs.getSDT(),
        	          		  nvs.getEmail(),
        	          		  nvs.getBangCap(),
        	          		  nvs.getChucVu(),
        	          		  nvs.getMaChuyenKhoa(),
        	          		  nvs.getNgayVaoLam(),
        	 
        	          		  nd.getMaNguoiDung()+"-"+ nd.getTenDangNhap(),
        	          		  nvs.getCalam(),
        	          		  nvs.getRole(),
        	          				  
        	        		
        	   
        	              });
    			  }
    		
    		  }
        
          }
          table.setModel(defaultTableModel);
    	
    }
	/**
	 * Create the frame.
	 */
	public frmQuanLyNhanVien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       Locale.setDefault(new Locale("vi", "VN")); 
		setBounds(100, 100, 1026, 700);
		contentPane = new JPanel();

        contentPane.setBackground(Color.WHITE); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	       Font labelFont = new Font("Segoe UI", Font.PLAIN, 13);
	        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);
	        Color primaryColor = new Color(0, 123, 255); 
		
	        JLabel lblHoTen = new JLabel("Họ tên:");
	        lblHoTen.setFont(labelFont);
	        lblHoTen.setBounds(30, 112, 80, 25);
	        contentPane.add(lblHoTen);

	        JTextField txtHoTen = new JTextField();
	        txtHoTen.setFont(textFont);
	        txtHoTen.setBounds(103, 110, 200, 25);
	        txtHoTen.setBackground(new Color(245, 245, 245));
	        contentPane.add(txtHoTen);
	        
	        JLabel lblGT = new JLabel("Giới tính:");
	        lblGT.setFont(labelFont);
	        lblGT.setBounds(568, 190, 80, 25);
	        contentPane.add(lblGT);

	        JComboBox<String> cbGT = new JComboBox<>(new String[]{"Nam", "Nữ"});
	        cbGT.setFont(textFont);
	        cbGT.setBounds(630, 189, 120, 25);
	        cbGT.setBackground(new Color(245, 245, 245));
	        contentPane.add(cbGT);

	        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
	        lblNgaySinh.setFont(labelFont);
	        lblNgaySinh.setBounds(317, 258, 80, 25);
	        contentPane.add(lblNgaySinh);
	        
	        JDateChooser dateChooserNgaySinh = new JDateChooser();
	        dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd"); 
	        dateChooserNgaySinh.setBounds(417, 258, 169, 29);
	        dateChooserNgaySinh.getCalendarButton().setBackground(primaryColor);
	        contentPane.add(dateChooserNgaySinh);
	
	        
	        JDateChooser DateChooseNgayVaoLam = new JDateChooser();
	        DateChooseNgayVaoLam.setDateFormatString("yyyy-MM-dd"); 
	        DateChooseNgayVaoLam.setBounds(103, 254, 150, 29);
	        DateChooseNgayVaoLam.getCalendarButton().setBackground(primaryColor);
	        contentPane.add(DateChooseNgayVaoLam);
		
	        JLabel lblCCCD = new JLabel("CCCD:");
	        lblCCCD.setFont(labelFont);
	        lblCCCD.setBounds(30, 148, 80, 25);
	        contentPane.add(lblCCCD);

	        JTextField txtCCCD = new JTextField();
	        txtCCCD.setFont(textFont);
	        txtCCCD.setBounds(103, 146, 200, 25);
	        txtCCCD.setBackground(new Color(245, 245, 245));
	        contentPane.add(txtCCCD);

	        JLabel lblDiaChi = new JLabel("Địa chỉ:");
	        lblDiaChi.setFont(labelFont);
	        lblDiaChi.setBounds(317, 76, 80, 25);
	        contentPane.add(lblDiaChi);

	        JTextField txtDiaChi = new JTextField();
	        txtDiaChi.setFont(textFont);
	        txtDiaChi.setBounds(415, 74, 335, 25);
	        txtDiaChi.setBackground(new Color(245, 245, 245));
	        contentPane.add(txtDiaChi);

	        JLabel lblSDT = new JLabel("SĐT:");
	        lblSDT.setFont(labelFont);
	        lblSDT.setBounds(30, 184, 80, 25);
	        contentPane.add(lblSDT);

	        JTextField txtSDT = new JTextField();
	        txtSDT.setFont(textFont);
	        txtSDT.setBounds(103, 182, 200, 25);
	        txtSDT.setBackground(new Color(245, 245, 245));
	        contentPane.add(txtSDT);

	        JLabel lblEmail = new JLabel("Email:");
	        lblEmail.setFont(labelFont);
	        lblEmail.setBounds(317, 112, 80, 25);
	        contentPane.add(lblEmail);

	        JTextField txtEmail = new JTextField();
	        txtEmail.setFont(textFont);
	        txtEmail.setBounds(415, 110, 335, 25);
	        txtEmail.setBackground(new Color(245, 245, 245));
	        contentPane.add(txtEmail);

	        JLabel lblBangCap = new JLabel("Bằng cấp:");
	        lblBangCap.setFont(labelFont);
	        lblBangCap.setBounds(30, 218, 80, 25);
	        contentPane.add(lblBangCap);

	        JTextField txtBangCap = new JTextField();
	        txtBangCap.setFont(textFont);
	        txtBangCap.setBounds(103, 218, 200, 25);
	        txtBangCap.setBackground(new Color(245, 245, 245));
	        contentPane.add(txtBangCap);

	        JLabel lblChucVu = new JLabel("Chức vụ:");
	        lblChucVu.setFont(labelFont);
	        lblChucVu.setBounds(317, 148, 80, 25);
	        contentPane.add(lblChucVu);

	        JTextField txtChucVu = new JTextField();
	        txtChucVu.setFont(textFont);
	        txtChucVu.setBounds(417, 150, 332, 25);
	        txtChucVu.setBackground(new Color(245, 245, 245));
	        contentPane.add(txtChucVu);

	        JLabel lblMaCK = new JLabel("Chuyên khoa(nếu có):");
	        lblMaCK.setFont(labelFont);
	        lblMaCK.setBounds(317, 222, 124, 25);
	        contentPane.add(lblMaCK);

	        JLabel lblNgayVao = new JLabel("Ngày vào:");
	        lblNgayVao.setFont(labelFont);
	        lblNgayVao.setBounds(30, 258, 80, 25);
	        contentPane.add(lblNgayVao);

	        JLabel lblLuong = new JLabel("Ca làm Việc:");
	        lblLuong.setFont(labelFont);
	        lblLuong.setBounds(317, 186, 80, 25);
	        contentPane.add(lblLuong);

	        JLabel lblMaND = new JLabel("Mã ND:");
	        lblMaND.setFont(labelFont);
	        lblMaND.setBounds(30, 76, 74, 25);
	        contentPane.add(lblMaND);

	        JLabel lblVaiTro = new JLabel("Vai trò:");
	        lblVaiTro.setFont(labelFont);
	        lblVaiTro.setBounds(588, 226, 42, 25);
	        contentPane.add(lblVaiTro);

	        JComboBox<String> cbVaiTro = new JComboBox<>(new String[]{
	                "QUAN_TRI", "BAC_SI", "LE_TAN", "THU_NGAN", "DUOC_SI", "KHO", "QUAN_LY"
	        });
	        cbVaiTro.setFont(textFont);
	        cbVaiTro.setBounds(640, 225, 110, 25);
	        cbVaiTro.setBackground(new Color(245, 245, 245));
	        contentPane.add(cbVaiTro);
		
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(10, 367, 1900, 300);
	        scrollPane.getViewport().setBackground(Color.WHITE);
	        contentPane.add(scrollPane);
		

//	        table = new JTable();
//	        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
//	        table.setRowHeight(25);
//	        table.setGridColor(Color.LIGHT_GRAY);
//	        table.setBackground(Color.WHITE);
//	        table.setSelectionBackground(primaryColor);
//	        table.setSelectionForeground(Color.WHITE);
//	        table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//	        scrollPane.setViewportView(table);
		
	         cmbMaCK = new JComboBox();
	        cmbMaCK.setModel(new DefaultComboBoxModel(new String[] {""}));
	        cmbMaCK.setBounds(451, 225, 135, 22);
	        contentPane.add(cmbMaCK);
	        
	        TimeClockChooser clockgiolam = new TimeClockChooser();
	        clockgiolam.setBounds(419, 187, 139, 28);
	        contentPane.add(clockgiolam);
	 
	        
	        
	        
		 cmbMaND = new JComboBox();
		cmbMaND.setModel(new DefaultComboBoxModel(new String[] {"1"}));
		cmbMaND.setBounds(103, 79, 200, 22);
		contentPane.add(cmbMaND);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int index = table.getSelectedRow();
		            int maNV = (int) table.getValueAt(index, 0);
		            String hoTen = (String) table.getValueAt(index, 1);
		            String gioiTinh = (String) table.getValueAt(index, 2);
		            String ngaySinh = table.getValueAt(index, 3).toString();
		            long cccd = (long) table.getValueAt(index, 4);
		            String diaChi = (String) table.getValueAt(index, 5);
		            long sdt = (long) table.getValueAt(index, 6);
		            String email = (String) table.getValueAt(index, 7);
		            String bangCap = (String) table.getValueAt(index, 8);
		            String chucVu = (String) table.getValueAt(index, 9);
		            String maCK = table.getValueAt(index, 10).toString();
		            String ngayVaoLam =  table.getValueAt(index, 11).toString();
		   
		            String maND = table.getValueAt(index, 12).toString();
		            String giolam = table.getValueAt(index, 13).toString();
		            LocalTime t = LocalTime.parse(giolam);
		
		          String vaiTro =  table.getValueAt(index, 14).toString();
		          NhanVien.Role role = NhanVien.Role.valueOf(vaiTro);
		            txtHoTen.setText(hoTen);
		            txtCCCD.setText(cccd+"");
		            txtDiaChi.setText(diaChi);
		            txtSDT.setText(sdt+"");
		            txtEmail.setText(email);
		            txtBangCap.setText(bangCap);
		            txtChucVu.setText(chucVu);
		            clockgiolam.setTime(t);
		

		
		            try {
		                java.util.Date ns = java.sql.Date.valueOf(ngaySinh);
		                dateChooserNgaySinh.setDate(ns);
		                java.util.Date nvl = java.sql.Date.valueOf(ngayVaoLam);
		                DateChooseNgayVaoLam.setDate(nvl);
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }

		     
		            cbGT.setSelectedItem(gioiTinh.toString());

		 
		            cmbMaCK.setSelectedItem(maCK);
		            cmbMaND.setSelectedItem(maND);
		            cbVaiTro.setSelectedItem(role.toString());
		        
		    }
		});

		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
			 "MaNV", "Họ Tên", "GT", "Ngày Sinh", "CCCD", "Địa Chỉ", "SDT", "Email", "Bằng Cấp", "Chức Vụ", "Chuyên Khoa", "Ngày Vào Làm", "MaND", "Giờ Làm Việc", "Vai Trò"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnThemNV = new JButton("THÊM NHÂN VIÊN");
		  btnThemNV.setFont(new Font("Segoe UI", Font.BOLD, 13));
		   btnThemNV.setForeground(Color.WHITE);
		      btnThemNV.setBackground(primaryColor);
		        btnThemNV.setFocusPainted(false);
		btnThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Hoten = txtHoTen.getText().toString();
				long cccd = 0;
				try {
					cccd = Long.parseLong(txtCCCD.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				
				
				long SDT = 0;
				try {
					SDT = Long.parseLong(txtSDT.getText().trim());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"xin hãy nhập số");
				}
				
				LocalTime localTime = clockgiolam.getTime();
				if (localTime == null) {
				    JOptionPane.showMessageDialog(null, "Bạn chưa chọn giờ làm!");
				    return;
				}

				Time giolam = Time.valueOf(localTime);
				
				
				
				String BC = txtBangCap.getText().toString();
			
				String selected = cbGT.getSelectedItem().toString();
				boolean GT;

				switch (selected) {
				    case "Nam":  GT = true; break;
				    case "Nữ":   GT = false; break;
				    default:     GT = false;
				}
	            
	            
				String DiaChi = txtDiaChi.getText().toString();
				String Email = txtEmail.getText().toString();
				String ChucVu = txtChucVu.getText().toString();
				
				
			
				
				
				LocalDate ngayVaoLam = DateChooseNgayVaoLam.getDate()
					    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

					LocalDate ngaySinh = dateChooserNgaySinh.getDate()
					    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					
					
					String selectedCMBMAND = cmbMaND.getSelectedItem().toString();
					int maNguoiDung = Integer.parseInt(selectedCMBMAND.split("-")[0].trim());
					
	            String maChuyenKhoa = cmbMaCK.getSelectedItem().toString();
	            
	            String StringVaiTro = cbVaiTro.getSelectedItem().toString();
	            NhanVien.Role role = NhanVien.Role.valueOf(StringVaiTro);
	            
	         
				NhanVien nv = new NhanVien(Hoten,GT,ngaySinh,cccd,DiaChi,SDT,Email,BC,ChucVu,maChuyenKhoa,ngayVaoLam,giolam,maNguoiDung,role);
				nvbus.ThemNV(nv);
			      JOptionPane.showMessageDialog(null, "đã thêm nhân viên thành công");
				loadDataTable();
				
				
				
			
			}
		});
        btnThemNV.setBounds(760, 76, 218, 67);
		contentPane.add(btnThemNV);
		
		JButton btnXoaNV = new JButton("XÓA NHÂN VIÊN");
	    btnXoaNV.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaNV.setForeground(Color.WHITE);
        btnXoaNV.setBackground(new Color(255, 0, 102));
        btnXoaNV.setFocusPainted(false);
		btnXoaNV.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            index = table.getSelectedRow();
		            if (index != -1) {
		                int id = (int) table.getValueAt(index, 0);  

		
		                int confirm = JOptionPane.showConfirmDialog(
		                        null,
		                        "Bạn có chắc chắn muốn xóa nhân viên này không?",
		                        "Xác nhận xóa",
		                        JOptionPane.YES_NO_OPTION,
		                        JOptionPane.WARNING_MESSAGE
		                );

	
		                if (confirm == JOptionPane.YES_OPTION) {
		                    nvbus.xoaNV(id);
		                    JOptionPane.showMessageDialog(null, "Đã xóa nhân viên thành công!");
		                    loadDataTable();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Đã hủy thao tác xóa.");
		                }

		            } else {
		                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!");
		            }

		        } catch (Exception e2) {
		            JOptionPane.showMessageDialog(null, "Xóa nhân viên không thành công: " + e2.getMessage());
		        }
		    }
		});

		btnXoaNV.setBounds(760, 148, 218, 67);
		contentPane.add(btnXoaNV);
		
		JButton btnSuaNV = new JButton("SỬA NHÂN VIÊN");
		btnSuaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					index = table.getSelectedRow();
					 
			        int id = (int) table.getValueAt(index, 0); 
					String Hoten = txtHoTen.getText().toString();
					long cccd = 0;
					try {
						cccd = Long.parseLong(txtCCCD.getText().trim());
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null,"xin hãy nhập số");
					}
					
					
					long SDT = 0;
					try {
						SDT = Long.parseLong(txtSDT.getText().trim());
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null,"xin hãy nhập số");
					}
					
			
					LocalTime localTime = clockgiolam.getTime();
					if (localTime == null) {
					    JOptionPane.showMessageDialog(null, "Bạn chưa chọn giờ làm!");
					    return;
					}

					Time giolam = Time.valueOf(localTime);
					
					
					String BC = txtBangCap.getText().toString();
				
					String selected = cbGT.getSelectedItem().toString();
					boolean GT;

					switch (selected) {
					    case "Nam":  GT = true; break;
					    case "Nữ":   GT = false; break;
					    default:     GT = false;
					}
		            
		            
					String DiaChi = txtDiaChi.getText().toString();
					String Email = txtEmail.getText().toString();
					String ChucVu = txtChucVu.getText().toString();
					
					
				
					
					
					LocalDate ngayVaoLam = DateChooseNgayVaoLam.getDate()
						    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

						LocalDate ngaySinh = dateChooserNgaySinh.getDate()
						    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						
						
						
						String selectedCMBMAND = cmbMaND.getSelectedItem().toString();
						int maNguoiDung = Integer.parseInt(selectedCMBMAND.split("-")[0].trim());
		            String maChuyenKhoa = cmbMaCK.getSelectedItem().toString();
		            
		            String StringVaiTro = cbVaiTro.getSelectedItem().toString();
		            NhanVien.Role role = NhanVien.Role.valueOf(StringVaiTro);
		            
		            
					NhanVien nv = new NhanVien(Hoten,GT,ngaySinh,cccd,DiaChi,SDT,Email,BC,ChucVu,maChuyenKhoa,ngayVaoLam,giolam,maNguoiDung,role);
					nvbus.SuaNV(nv, id);
				      JOptionPane.showMessageDialog(null, "đã sửa nhân viên thành công");
					loadDataTable();
					
		
				
				
			}
		});
        btnSuaNV.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaNV.setForeground(Color.WHITE);
        btnSuaNV.setBackground(new Color(0, 200, 83));
        btnSuaNV.setFocusPainted(false);
		btnSuaNV.setBounds(760, 226, 218, 57);
		contentPane.add(btnSuaNV);
		
		JLabel lblNewLabel = new JLabel("Tìm Kiếm nhân viên:");
		lblNewLabel.setBounds(30, 329, 117, 14);
		contentPane.add(lblNewLabel);
		
		txttimkiem = new JTextField();
		txttimkiem.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        String text = txttimkiem.getText().trim();
		        if (text.isEmpty()) {
		            loadDataTable(); 
		        } else {
		            try {
		           
		                loadDataTIMKIEM(text);
		            } catch (NumberFormatException ex) {
		              
		            }
		        }
		    }
		});

		

	
		txttimkiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txttimkiem.setBackground(new Color(245, 245, 245));
		txttimkiem.setBounds(157, 315, 340, 41);
		contentPane.add(txttimkiem);
		
		JButton btnQuayTrLi = new JButton("QUAY TRỞ LẠI");
		btnQuayTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmQuanLy ql = new FrmQuanLy();
				ql.show();
				dispose();
			}
		});
		btnQuayTrLi.setForeground(Color.WHITE);
		btnQuayTrLi.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnQuayTrLi.setFocusPainted(false);
		btnQuayTrLi.setBackground(new Color(0, 200, 83));
		btnQuayTrLi.setBounds(760, 287, 218, 69);
		contentPane.add(btnQuayTrLi);
		
		JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.ITALIC, 20));
		lblNewLabel_1.setBounds(301, 11, 382, 54);
		contentPane.add(lblNewLabel_1);
		
	
		loadDataTable();
		loadDataComboBox();
		loadDataComboBoxMaND();


	}
}
