package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import BUS.ThuocBus;

import java.util.List;
import java.util.Locale;

import MODEL.Thuoc;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmQuanLyThuoc extends JFrame {

    private JPanel contentPane;
    private JTextField txtTenThuoc, txtHoatChat, txtHamLuong, txtDangThuoc, txtLoaiThuoc, txtDonViTinh;
    private JTextField txtDonGiaNhap, txtDonGiaBan, txtSoLuongTon, txtNhaSanXuat, txtNuocSanXuat, txtGhiChu;
    private JDateChooser dateChooserNSX, dateChooserHSD;
    private JTable table;
    private JTextField txtTimKiem;
    private List<Thuoc> listthuoc = new ArrayList<>();
    private ThuocBus thuocbus = new ThuocBus();
    private int index;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	FrmQuanLyThuoc frame = new FrmQuanLyThuoc();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void loadDataTable() {
        listthuoc = thuocbus.getAllTHUOC();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Thuoc t : listthuoc) {
            model.addRow(new Object[] {
                t.getMathuoc(),       
                t.getTenthuoc(),      
                t.getHoatchat(),      
                t.getHamluong(),      
                t.getDangthuoc(),    
                t.getLoaithuoc(),   
                t.getDonvitinh(),   
                t.getDongianhap(),   
                t.getDongiaban(),     
                t.getSoluongton(),   
                t.getNgaysanxuat(),   
                t.getHansudung(),     
                t.getNhasanxuat(),    
                t.getNuocsanxuat(),   
                t.getGhichu()         
            });
        }
    }
    private void loadDataTableTKTHUOC(String id) {
        listthuoc = thuocbus.Timkiemthuoc(id);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Thuoc t : listthuoc) {
            model.addRow(new Object[] {
                t.getMathuoc(),       
                t.getTenthuoc(),      
                t.getHoatchat(),      
                t.getHamluong(),      
                t.getDangthuoc(),    
                t.getLoaithuoc(),   
                t.getDonvitinh(),   
                t.getDongianhap(),   
                t.getDongiaban(),     
                t.getSoluongton(),   
                t.getNgaysanxuat(),   
                t.getHansudung(),     
                t.getNhasanxuat(),    
                t.getNuocsanxuat(),   
                t.getGhichu()         
            });
        }
    }
    private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

    private long getLong(Object value) {
        return value == null ? 0 : Long.parseLong(value.toString());
    }
    public FrmQuanLyThuoc() {
        setTitle("Quản lý thuốc");
        Locale.setDefault(new Locale("vi", "VN")); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1350, 720);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);



        JLabel lblTenThuoc = new JLabel("Tên thuốc:");
        lblTenThuoc.setBounds(20, 20, 120, 25);
        contentPane.add(lblTenThuoc);

        txtTenThuoc = new JTextField();
        txtTenThuoc.setBounds(140, 20, 250, 25);
        contentPane.add(txtTenThuoc);

        JLabel lblHoatChat = new JLabel("Hoạt chất:");
        lblHoatChat.setBounds(20, 60, 120, 25);
        contentPane.add(lblHoatChat);

        txtHoatChat = new JTextField();
        txtHoatChat.setBounds(140, 60, 250, 25);
        contentPane.add(txtHoatChat);

        JLabel lblHamLuong = new JLabel("Hàm lượng:");
        lblHamLuong.setBounds(20, 100, 120, 25);
        contentPane.add(lblHamLuong);

        txtHamLuong = new JTextField();
        txtHamLuong.setBounds(140, 100, 250, 25);
        contentPane.add(txtHamLuong);

        JLabel lblDangThuoc = new JLabel("Dạng thuốc:");
        lblDangThuoc.setBounds(20, 140, 120, 25);
        contentPane.add(lblDangThuoc);

        txtDangThuoc = new JTextField();
        txtDangThuoc.setBounds(140, 140, 250, 25);
        contentPane.add(txtDangThuoc);

        JLabel lblLoaiThuoc = new JLabel("Loại thuốc:");
        lblLoaiThuoc.setBounds(20, 180, 120, 25);
        contentPane.add(lblLoaiThuoc);

        txtLoaiThuoc = new JTextField();
        txtLoaiThuoc.setBounds(140, 180, 250, 25);
        contentPane.add(txtLoaiThuoc);

        JLabel lblDonViTinh = new JLabel("Đơn vị tính:");
        lblDonViTinh.setBounds(20, 220, 120, 25);
        contentPane.add(lblDonViTinh);

        txtDonViTinh = new JTextField();
        txtDonViTinh.setBounds(140, 220, 250, 25);
        contentPane.add(txtDonViTinh);




        JLabel lblDonGiaNhap = new JLabel("Đơn giá nhập:");
        lblDonGiaNhap.setBounds(420, 20, 120, 25);
        contentPane.add(lblDonGiaNhap);

        txtDonGiaNhap = new JTextField();
        txtDonGiaNhap.setBounds(550, 20, 250, 25);
        contentPane.add(txtDonGiaNhap);

        JLabel lblDonGiaBan = new JLabel("Đơn giá bán:");
        lblDonGiaBan.setBounds(420, 60, 120, 25);
        contentPane.add(lblDonGiaBan);

        txtDonGiaBan = new JTextField();
        txtDonGiaBan.setBounds(550, 60, 250, 25);
        contentPane.add(txtDonGiaBan);

        JLabel lblSoLuong = new JLabel("Số lượng tồn:");
        lblSoLuong.setBounds(420, 100, 120, 25);
        contentPane.add(lblSoLuong);

        txtSoLuongTon = new JTextField();
        txtSoLuongTon.setBounds(550, 100, 250, 25);
        contentPane.add(txtSoLuongTon);

        JLabel lblNSX = new JLabel("Ngày sản xuất:");
        lblNSX.setBounds(420, 140, 120, 25);
        contentPane.add(lblNSX);

        dateChooserNSX = new JDateChooser();
        dateChooserNSX.setBounds(550, 140, 250, 25);
        contentPane.add(dateChooserNSX);

        JLabel lblHSD = new JLabel("Hạn sử dụng:");
        lblHSD.setBounds(420, 180, 120, 25);
        contentPane.add(lblHSD);

        dateChooserHSD = new JDateChooser();
        dateChooserHSD.setBounds(550, 180, 250, 25);
        contentPane.add(dateChooserHSD);

        JLabel lblNhaSX = new JLabel("Nhà sản xuất:");
        lblNhaSX.setBounds(420, 220, 120, 25);
        contentPane.add(lblNhaSX);

        txtNhaSanXuat = new JTextField();
        txtNhaSanXuat.setBounds(550, 220, 250, 25);
        contentPane.add(txtNhaSanXuat);

        JLabel lblNuocSX = new JLabel("Nước sản xuất:");
        lblNuocSX.setBounds(420, 260, 120, 25);
        contentPane.add(lblNuocSX);

        txtNuocSanXuat = new JTextField();
        txtNuocSanXuat.setBounds(550, 260, 250, 25);
        contentPane.add(txtNuocSanXuat);

        JLabel lblGhiChu = new JLabel("Ghi chú:");
        lblGhiChu.setBounds(20, 260, 120, 25);
        contentPane.add(lblGhiChu);

        txtGhiChu = new JTextField();
        txtGhiChu.setBounds(140, 260, 250, 25);
        contentPane.add(txtGhiChu);




        JButton btnThem = new JButton("THÊM THUỐC");
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String tenthuoc = txtTenThuoc.getText().trim();
                String hoatchat = txtHoatChat.getText().trim();
                String hamluong = txtHamLuong.getText().trim();
                String dangthuoc = txtDangThuoc.getText().trim();
                String loaithuoc = txtLoaiThuoc.getText().trim();
                String donvitinh = txtDonViTinh.getText().trim();
                String nhasanxuat = txtNhaSanXuat.getText().trim();
                String nuocsanxuat = txtNuocSanXuat.getText().trim();
                String ghichu = txtGhiChu.getText().trim();

                double dongianhap = 0;
                double dongiaban = 0;
                int soluongton = 0;


                try {
                    dongianhap = Double.parseDouble(txtDonGiaNhap.getText().trim());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Đơn giá nhập phải là số!");
                    return;
                }

                try {
                    dongiaban = Double.parseDouble(txtDonGiaBan.getText().trim());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Đơn giá bán phải là số!");
                    return;
                }

                try {
                    soluongton = Integer.parseInt(txtSoLuongTon.getText().trim());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số!");
                    return;
                }

      
                LocalDate ngaySanXuat = dateChooserNSX.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate hanSuDung = dateChooserHSD.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
     
                Thuoc t = new Thuoc();
                t.setTenthuoc(tenthuoc);
                t.setHoatchat(hoatchat);
                t.setHamluong(hamluong);
                t.setDangthuoc(dangthuoc);
                t.setLoaithuoc(loaithuoc);
                t.setDonvitinh(donvitinh);
                t.setDongianhap(dongianhap);
                t.setDongiaban(dongiaban);
                t.setSoluongton(soluongton);
                t.setNgaysanxuat(ngaySanXuat);
                t.setHansudung(hanSuDung);
                t.setNhasanxuat(nhasanxuat);
                t.setNuocsanxuat(nuocsanxuat);
                t.setGhichu(ghichu);

  
                thuocbus.ThemThuoc(t);

             
                loadDataTable();

                JOptionPane.showMessageDialog(null, "Thêm thuốc thành công!");
            }
        });

        btnThem.setBounds(850, 20, 200, 50);
        contentPane.add(btnThem);

        JButton btnXoa = new JButton("XÓA THUỐC");
        btnXoa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	      try {
			            index = table.getSelectedRow();
			            if (index != -1) {
			                int id = (int) table.getValueAt(index, 0);  

			
			                int confirm = JOptionPane.showConfirmDialog(
			                        null,
			                        "Bạn có chắc chắn muốn xóa thuốc này không?",
			                        "Xác nhận xóa",
			                        JOptionPane.YES_NO_OPTION,
			                        JOptionPane.WARNING_MESSAGE
			                );

		
			                if (confirm == JOptionPane.YES_OPTION) {
			                    thuocbus.xoaThuoc(id);
			                    JOptionPane.showMessageDialog(null, "Đã xóa thuốc thành công!");
			                    loadDataTable();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Đã hủy thao tác xóa.");
			                }

			            } else {
			                JOptionPane.showMessageDialog(null, "Vui lòng chọn thuốc cần xóa!");
			            }

			        } catch (Exception e2) {
			            JOptionPane.showMessageDialog(null, "Xóa thuốc không thành công: " + e2.getMessage());
			        }
        	}
        });
        btnXoa.setBounds(850, 80, 200, 50);
        contentPane.add(btnXoa);

        JButton btnSua = new JButton("SỬA THUỐC");
        btnSua.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		index = table.getSelectedRow();
        		int id = (int) table.getValueAt(index, 0);
        		  String tenthuoc = txtTenThuoc.getText().trim();
                  String hoatchat = txtHoatChat.getText().trim();
                  String hamluong = txtHamLuong.getText().trim();
                  String dangthuoc = txtDangThuoc.getText().trim();
                  String loaithuoc = txtLoaiThuoc.getText().trim();
                  String donvitinh = txtDonViTinh.getText().trim();
                  String nhasanxuat = txtNhaSanXuat.getText().trim();
                  String nuocsanxuat = txtNuocSanXuat.getText().trim();
                  String ghichu = txtGhiChu.getText().trim();

                  double dongianhap = 0;
                  double dongiaban = 0;
                  int soluongton = 0;


                  try {
                      dongianhap = Double.parseDouble(txtDonGiaNhap.getText().trim());
                  } catch (Exception e1) {
                      JOptionPane.showMessageDialog(null, "Đơn giá nhập phải là số!");
                      return;
                  }

                  try {
                      dongiaban = Double.parseDouble(txtDonGiaBan.getText().trim());
                  } catch (Exception e1) {
                      JOptionPane.showMessageDialog(null, "Đơn giá bán phải là số!");
                      return;
                  }

                  try {
                      soluongton = Integer.parseInt(txtSoLuongTon.getText().trim());
                  } catch (Exception e1) {
                      JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số!");
                      return;
                  }

        
                  LocalDate ngaySanXuat = dateChooserNSX.getDate()
                          .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                  LocalDate hanSuDung = dateChooserHSD.getDate()
                          .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      
       
                  Thuoc t = new Thuoc();
                  t.setTenthuoc(tenthuoc);
                  t.setHoatchat(hoatchat);
                  t.setHamluong(hamluong);
                  t.setDangthuoc(dangthuoc);
                  t.setLoaithuoc(loaithuoc);
                  t.setDonvitinh(donvitinh);
                  t.setDongianhap(dongianhap);
                  t.setDongiaban(dongiaban);
                  t.setSoluongton(soluongton);
                  t.setNgaysanxuat(ngaySanXuat);
                  t.setHansudung(hanSuDung);
                  t.setNhasanxuat(nhasanxuat);
                  t.setNuocsanxuat(nuocsanxuat);
                  t.setGhichu(ghichu);

    
                  thuocbus.Suathuoc(t, id);

               
                  loadDataTable();

                  JOptionPane.showMessageDialog(null, "Sửa thuốc thành công!");
        	}
        });
        btnSua.setBounds(850, 140, 200, 50);
        contentPane.add(btnSua);

        JButton btnQuayLai = new JButton("QUAY TRỞ LẠI");
        btnQuayLai.setBounds(850, 200, 200, 50);
        contentPane.add(btnQuayLai);




        JLabel lblTim = new JLabel("Tìm kiếm thuốc:");
        lblTim.setBounds(20, 315, 150, 25);
        contentPane.add(lblTim);

        txtTimKiem = new JTextField();
        txtTimKiem.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
       		 String text = txtTimKiem.getText().trim();
		        if (text.isEmpty()) {
		            loadDataTable(); 
		            
		        } else {
		            try {
		           
		            	loadDataTableTKTHUOC(text);
		            } catch (NumberFormatException ex) {
		              
		            }
		        }
        	}
        });
        txtTimKiem.setBounds(140, 315, 250, 25);
        contentPane.add(txtTimKiem);



        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 360, 1600, 300);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                index = table.getSelectedRow();
                if (index == -1) return;

    
                String tenThuoc      = getString(table.getValueAt(index, 1));
                String hoatChat      = getString(table.getValueAt(index, 2));
                String hamLuong      = getString(table.getValueAt(index, 3));
                String dangThuoc     = getString(table.getValueAt(index, 4));
                String loaiThuoc     = getString(table.getValueAt(index, 5));
                String donViTinh     = getString(table.getValueAt(index, 6));

                String donGiaNhap = getString(table.getValueAt(index, 7));
                String donGiaBan  = getString(table.getValueAt(index, 8));
                String soLuongTon = getString(table.getValueAt(index, 9));

                String ngaySXStr     = getString(table.getValueAt(index, 10));
                String hanSDStr      = getString(table.getValueAt(index, 11));

                String nhaSX         = getString(table.getValueAt(index, 12));
                String nuocSX        = getString(table.getValueAt(index, 13));
                String ghiChu        = getString(table.getValueAt(index, 14));


            
                txtTenThuoc.setText(tenThuoc);
                txtHoatChat.setText(hoatChat);
                txtHamLuong.setText(hamLuong);
                txtDangThuoc.setText(dangThuoc);
                txtLoaiThuoc.setText(loaiThuoc);
                txtDonViTinh.setText(donViTinh);

                txtDonGiaNhap.setText(donGiaNhap);
                txtDonGiaBan.setText(donGiaBan);
                txtSoLuongTon.setText(soLuongTon);

                txtNhaSanXuat.setText(nhaSX);
                txtNuocSanXuat.setText(nuocSX);
                txtGhiChu.setText(ghiChu);


           
                try {
                    java.util.Date nsx = java.sql.Date.valueOf(ngaySXStr);
                    dateChooserNSX.setDate(nsx);
                } catch (Exception ex) {
                    dateChooserNSX.setDate(null);
                }

                try {
                    java.util.Date hsd = java.sql.Date.valueOf(hanSDStr);
                    dateChooserHSD.setDate(hsd);
                } catch (Exception ex) {
                    dateChooserHSD.setDate(null);
                }
            }
        });



        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Mã thuốc", "Tên thuốc", "Hoạt chất", "Hàm lượng", "Dạng thuốc", "Loại thuốc",
                        "Đơn vị tính", "Giá nhập", "Giá bán", "Số lượng", "NSX", "HSD",
                        "Nhà sản xuất", "Nước sản xuất", "Ghi chú"
                }
        ));

        scrollPane.setViewportView(table);
        loadDataTable();
    }
}
