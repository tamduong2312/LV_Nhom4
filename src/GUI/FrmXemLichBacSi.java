package GUI;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Import Model và BUS
import MODEL.BangPhanCongCaLam;
import MODEL.NhanVien;
import BUS.BangPhanCongCaLamBus;
import BUS.NhanVienBUS;

public class FrmXemLichBacSi extends JFrame {

    private JPanel contentPane;
    private JComboBox<String> cboNgayXem;
    private JTable tableBacSi;
    private DefaultTableModel modelBacSi;
    
    private BangPhanCongCaLamBus phanCongBus = new BangPhanCongCaLamBus();
    private DateTimeFormatter dtfShow = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FrmXemLichBacSi() {
        initGUI();
        loadNgayVaoCombobox(); 

        loadDanhSachBacSiTruc();
    }

    private void initGUI() {
        setTitle("Lịch Làm Việc Bác Sĩ");
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
        pnlHeader.setBackground(new Color(0, 102, 204));
        JLabel lblTitle = new JLabel("LỊCH LÀM VIỆC CỦA BÁC SĨ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        contentPane.add(pnlHeader, BorderLayout.NORTH);
        
        JPanel pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setOpaque(false);
        
        // --- PANEL CHỌN NGÀY ---
        JPanel pnlTopFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlTopFilter.setBackground(Color.WHITE);
        pnlTopFilter.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        pnlTopFilter.add(new JLabel("Chọn ngày xem lịch:"));
        cboNgayXem = new JComboBox<>();
        cboNgayXem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboNgayXem.setPreferredSize(new Dimension(200, 30));
        pnlTopFilter.add(cboNgayXem);
        
        JButton btnXemChiTiet = new JButton("Xem Chi Tiết TKB");
        btnXemChiTiet.setBackground(new Color(23, 162, 184));
        btnXemChiTiet.setForeground(Color.WHITE);
        btnXemChiTiet.setOpaque(true);
        btnXemChiTiet.setBorderPainted(false);
        pnlTopFilter.add(btnXemChiTiet);

        pnlCenter.add(pnlTopFilter, BorderLayout.NORTH);

        // --- BẢNG DỮ LIỆU ---
        modelBacSi = new DefaultTableModel(new Object[]{"Mã BS", "Tên Bác Sĩ", "Chuyên khoa", "Phòng", "Ca Làm", "Thứ"}, 0) {
             @Override
             public boolean isCellEditable(int row, int column) { return false; }
        };
        tableBacSi = new JTable(modelBacSi);
        tableBacSi.setRowHeight(35);
        tableBacSi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JScrollPane scrollTable = new JScrollPane(tableBacSi);
        scrollTable.getViewport().setBackground(Color.WHITE);
        
        pnlCenter.add(scrollTable, BorderLayout.CENTER);
        contentPane.add(pnlCenter, BorderLayout.CENTER);
        
        // --- BOTTOM ---
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBottom.setOpaque(false);
        JButton btnDong = new JButton("Đóng");
        btnDong.setPreferredSize(new Dimension(100, 35));
        pnlBottom.add(btnDong);
        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        // --- EVENTS ---
        cboNgayXem.addActionListener(e -> loadDanhSachBacSiTruc());
        btnXemChiTiet.addActionListener(e -> loadTKBBS());
        btnDong.addActionListener(e -> dispose());
    }
    
    private void loadTKBBS() {
    	int index = tableBacSi.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bác sĩ trên bảng!");
            return;
        }
    	int mabs = (int) tableBacSi.getValueAt(index, 0);
    	String tenbs = tableBacSi.getValueAt(index, 1).toString();
    	new FrmLoadTKBBS(mabs, tenbs).setVisible(true);
    }

    private void loadNgayVaoCombobox() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
     

            int dayValue = date.getDayOfWeek().getValue();
            String thu = (dayValue == 7) ? "Chủ nhật" : "Thứ " + (dayValue + 1);

            cboNgayXem.addItem(date.format(df) + " - " + thu);
        }
    }


    private void loadDanhSachBacSiTruc() {
        String selectedItem = (String) cboNgayXem.getSelectedItem();
        if(selectedItem == null) return;
        String thuCanTim = selectedItem.split(" - ")[1]; 
        
        modelBacSi.setRowCount(0);
        List<BangPhanCongCaLam> list = phanCongBus.getLichBacSiTheoThu(thuCanTim);
        NhanVienBUS nvBus = new NhanVienBUS();
        List<NhanVien> listNV = nvBus.getAllNV();
        
        for (BangPhanCongCaLam pc : list) {
            String tenBacSi = phanCongBus.getTenNhanVien(pc.getMaNhanVien());
            String tenCK = "Chưa rõ";
            
           	for(NhanVien nv : listNV) {
        		if(nv.getMaNV() == pc.getMaNhanVien()) {
        			tenCK = nv.getMaChuyenKhoa(); 
        			break;
        		}
        	}
            
            modelBacSi.addRow(new Object[]{ 
                pc.getMaNhanVien(), 
                tenBacSi,
                tenCK,
                pc.getPhong(), 
                pc.getGioLam() + " - " + pc.getGioKetThuc(), 
                pc.getThu() 
            });
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FrmXemLichBacSi().setVisible(true);
        });
    }
}