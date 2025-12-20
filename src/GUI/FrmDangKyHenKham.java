package GUI;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Import Model và BUS
import MODEL.BangPhanCongCaLam;
import MODEL.LichTaiKham;
import MODEL.NhanVien;
import BUS.BangPhanCongCaLamBus;
import BUS.LichTaiKhamBus;
import BUS.NhanVienBUS;
import java.awt.event.ActionListener; 

public class FrmDangKyHenKham extends JFrame {

    private JPanel contentPane;
    private JTextField txtBenhNhan;
    private JComboBox<String> cboNgayHen;
    private JTable tableBacSi;
    private DefaultTableModel modelBacSi;
    private JTextArea txtGhiChu;
    

    private BangPhanCongCaLamBus phanCongBus = new BangPhanCongCaLamBus();
    private LichTaiKhamBus lichTaiKhamBus = new LichTaiKhamBus(); 


    private int maBenhNhan;
    private String tenBenhNhan;
    

    private DateTimeFormatter dtfShow = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FrmDangKyHenKham(int maBN, String tenBN) {
        this.maBenhNhan = maBN;
        this.tenBenhNhan = tenBN;
        initGUI();
        loadNgayLamViec(); 
    }
    

    public FrmDangKyHenKham() {
        this(0, "Test Mode");
    }

    private void initGUI() {
        setTitle("Đăng Ký Lịch Hẹn Khám Bệnh");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 248, 250));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));
        
        // --- HEADER ---
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlHeader.setBackground(new Color(0, 123, 255));
        JLabel lblTitle = new JLabel("ĐẶT LỊCH HẸN KHÁM BỆNH");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        contentPane.add(pnlHeader, BorderLayout.NORTH);
        
     
        JPanel pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setOpaque(false);
        
    
        JPanel pnlInput = new JPanel(new GridLayout(2, 4, 15, 15));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin đặt hẹn"));
        pnlInput.setBackground(Color.WHITE);

        pnlInput.add(new JLabel("Bệnh nhân:"));
        txtBenhNhan = new JTextField(tenBenhNhan);
        txtBenhNhan.setEditable(false);
        txtBenhNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pnlInput.add(txtBenhNhan);
        
        pnlInput.add(new JLabel("Chọn ngày hẹn:"));
        cboNgayHen = new JComboBox<>();
        cboNgayHen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnlInput.add(cboNgayHen);
        
        // Dòng 2
        pnlInput.add(new JLabel("Ghi chú:"));
        txtGhiChu = new JTextArea("Bệnh nhân đăng ký hẹn khám");
        txtGhiChu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pnlInput.add(txtGhiChu);
        
        pnlInput.add(new JLabel("")); // Dummy spacer
        JButton btnXemLich = new JButton("Xem Lịch Bác Sĩ");
 
        btnXemLich.setBackground(new Color(23, 162, 184));
        btnXemLich.setForeground(Color.WHITE);
        btnXemLich.setContentAreaFilled(false);
        btnXemLich.setOpaque(true);
        btnXemLich.setBorderPainted(false);

        pnlInput.add(btnXemLich);
        
        
  
        

        pnlCenter.add(pnlInput, BorderLayout.NORTH);

        modelBacSi = new DefaultTableModel(new Object[]{"Mã BS", "Tên Bác Sĩ","Chuyên khoa", "Phòng", "Ca Làm", "Thứ"}, 0) {
             @Override
             public boolean isCellEditable(int row, int column) { return false; }
        };
        tableBacSi = new JTable(modelBacSi);
        tableBacSi.setRowHeight(30);
        tableBacSi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JScrollPane scrollTable = new JScrollPane(tableBacSi);
        scrollTable.setBorder(BorderFactory.createTitledBorder("Danh sách Bác sĩ trực ca (Dữ liệu từ Bảng phân công)"));
        scrollTable.getViewport().setBackground(Color.WHITE);
        
        pnlCenter.add(scrollTable, BorderLayout.CENTER);
        contentPane.add(pnlCenter, BorderLayout.CENTER);
        
        // --- BOTTOM ---
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBottom.setOpaque(false);
        
        JButton btnHuy = new JButton("Hủy Bỏ");
        btnHuy.setPreferredSize(new Dimension(100, 35));
        
        
        JButton btnXacNhan = new JButton("XÁC NHẬN ĐẶT HẸN");
        btnXacNhan.setBackground(new Color(40, 167, 69));
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXacNhan.setPreferredSize(new Dimension(200, 35));
        btnXacNhan.setContentAreaFilled(false);
        btnXacNhan.setOpaque(true);
        btnXacNhan.setBorderPainted(false);

        
        pnlBottom.add(btnHuy);
        pnlBottom.add(btnXacNhan);
        contentPane.add(pnlBottom, BorderLayout.SOUTH);
        

        cboNgayHen.addActionListener(e -> loadDanhSachBacSiTruc());
   
        btnXemLich.addActionListener(e -> loadDanhSachBacSiTruc());
        
        btnHuy.addActionListener(e -> dispose());
        btnXacNhan.addActionListener(e -> xuLyDatLich());
        
        btnXemLich.addActionListener(e -> loadTKBBS()) ;
        
    }
    
    private void loadTKBBS() {

    	int index = tableBacSi.getSelectedRow();
    	int mabs = (int) tableBacSi.getValueAt(index, 0);
    	
    	String tenbs = tableBacSi.getValueAt(index, 1).toString();
    	
    	FrmLoadTKBBS q = new FrmLoadTKBBS(mabs, tenbs);
    	q.setVisible(true);
    	
        }

    
    


    private void loadNgayLamViec() {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            String thu = convertDayOfWeek(date.getDayOfWeek().toString());

            String item = date.format(dtfShow) + " - " + thu;
            cboNgayHen.addItem(item);
        }
    }
    

    private String convertDayOfWeek(String englishDay) {
        switch (englishDay) {
            case "MONDAY": return "Thứ 2";
            case "TUESDAY": return "Thứ 3";
            case "WEDNESDAY": return "Thứ 4";
            case "THURSDAY": return "Thứ 5";
            case "FRIDAY": return "Thứ 6";
            case "SATURDAY": return "Thứ 7";
            case "SUNDAY": return "Chủ nhật";
            default: return "";
        }
    }


    private void loadDanhSachBacSiTruc() {
        String selectedItem = (String) cboNgayHen.getSelectedItem();
        if(selectedItem == null) return;
        
  
        String thuCanTim = selectedItem.split(" - ")[1]; 
        
        modelBacSi.setRowCount(0);

        List<BangPhanCongCaLam> list = phanCongBus.getLichBacSiTheoThu(thuCanTim);
        
        
        NhanVienBUS nvBus = new NhanVienBUS();
        List<NhanVien> listNV = nvBus.getAllNV();
        
        String ck = "";
        
        for (BangPhanCongCaLam pc : list) {
 
        	
        	
        	
            String tenBacSi = phanCongBus.getTenNhanVien(pc.getMaNhanVien());
            
            
           	for(NhanVien nv : listNV) {
        		if(nv.getMaNV() == pc.getMaNhanVien()) {
        			ck = nv.getMaChuyenKhoa();
        			break;
        		}
        	}
        	
            
            modelBacSi.addRow(new Object[]{ 
                pc.getMaNhanVien(), 
                tenBacSi,
                ck,
                pc.getPhong(), 
                pc.getGioLam() + " - " + pc.getGioKetThuc(), 
                pc.getThu() 
            });
        }
    }
    

    private void xuLyDatLich() {
        int row = tableBacSi.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một Bác sĩ / Ca làm việc trong bảng!", "Chưa chọn bác sĩ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
   
            int maBacSi = Integer.parseInt(tableBacSi.getValueAt(row, 0).toString());
            
    
            String selectedItem = (String) cboNgayHen.getSelectedItem();
            String dateString = selectedItem.split(" - ")[0]; 
            LocalDate ld = LocalDate.parse(dateString, dtfShow);
            Date sqlDate = Date.valueOf(ld);
            
            String ghiChu = txtGhiChu.getText();
            
        
            LichTaiKham lich = new LichTaiKham();
            lich.setMaBenhNhan(maBenhNhan);
            lich.setMaNhanVien(maBacSi);
            lich.setNgayTaiKham(sqlDate);
            lich.setGhiChu(ghiChu);
            lich.setTrangThai("CHUA_DEN");
            
  
            boolean result = lichTaiKhamBus.themLichHen(lich);
            
            if (result) {
                JOptionPane.showMessageDialog(this, "Đã đặt lịch hẹn thành công ngày " + dateString);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu lịch hẹn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
        }
    }
}