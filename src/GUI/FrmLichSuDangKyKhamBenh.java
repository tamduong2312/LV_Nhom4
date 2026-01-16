package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.BenhNhanBus;
import BUS.ChuyenKhoaBus;
import BUS.NhanVienBUS;
import DAO.DangKyKhamBenhDao;
import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.DangKyKhamBenh;
import MODEL.NhanVien;

public class FrmLichSuDangKyKhamBenh extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    
    private JTextField txtSearch;
    private JTextField txtTuNgay;
    private JTextField txtDenNgay;
    private JButton btnLoc;
    private JButton btnReset;
    
    private DangKyKhamBenhDao dao = new DangKyKhamBenhDao();

    private BenhNhanBus bnBus = new BenhNhanBus(); 
    private ChuyenKhoaBus ckBus = new ChuyenKhoaBus();
    
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
        EventQueue.invokeLater(() -> {
            try {
                new FrmLichSuDangKyKhamBenh().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmLichSuDangKyKhamBenh() {
        setTitle("Lịch Sử Đăng Ký Khám Bệnh - Lễ Tân");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 650);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        

        JLabel lblTitle = new JLabel("LỊCH SỬ TIẾP NHẬN BỆNH NHÂN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 102, 204));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 15, 980, 40);
        contentPane.add(lblTitle);
  
        int yFilter = 70;
        
        JLabel lblTim = new JLabel("Tìm kiếm (Tên/SĐT):");
        lblTim.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTim.setBounds(20, yFilter, 150, 25);
        contentPane.add(lblTim);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(160, yFilter, 240, 30);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPane.add(txtSearch);
        
        JLabel lblTu = new JLabel("Từ ngày:");
        lblTu.setBounds(420, yFilter, 60, 25);
        lblTu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        contentPane.add(lblTu);
        
        txtTuNgay = new JTextField(LocalDate.now().toString());
        txtTuNgay.setBounds(480, yFilter, 100, 30);
        contentPane.add(txtTuNgay);
        
        JLabel lblDen = new JLabel("Đến ngày:");
        lblDen.setBounds(600, yFilter, 70, 25);
        lblDen.setFont(new Font("Segoe UI", Font.BOLD, 13));
        contentPane.add(lblDen);
        
        txtDenNgay = new JTextField(LocalDate.now().toString());
        txtDenNgay.setBounds(670, yFilter, 100, 30);
        contentPane.add(txtDenNgay);
        
        btnLoc = new JButton("Lọc ngày");
        btnLoc.setBounds(790, yFilter, 90, 30);
        btnLoc.setBackground(new Color(0, 102, 204));
        btnLoc.setForeground(Color.WHITE);
        btnLoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLoc.setContentAreaFilled(false);
        btnLoc.setOpaque(true);
        btnLoc.setBorderPainted(false);

        contentPane.add(btnLoc);
        
        btnReset = new JButton("Tải lại");
        btnReset.setBounds(890, yFilter, 80, 30);
        btnReset.setBackground(new Color(108, 117, 125));
        btnReset.setForeground(Color.WHITE);
        btnReset.setContentAreaFilled(false);
        btnReset.setOpaque(true);
        btnReset.setBorderPainted(false);

        contentPane.add(btnReset);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 120, 950, 470);
        contentPane.add(scrollPane);
        
        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Mã ĐK", "Bệnh Nhân","SDT","SDT người giám hộ (nếu có)", "Chuyên Khoa", "STT", "Thời Gian ĐK", "Trạng Thái" }
        ) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table.setModel(model);
        

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        scrollPane.setViewportView(table);
 
        initEvents();
        loadAllData();
    }
    

    
    private void loadAllData() {
        List<DangKyKhamBenh> list = dao.getAllLichSuDangKy();
        updateTable(list);
    }
    
    private void searchByText() {
        String key = txtSearch.getText().trim();
        if(key.isEmpty()) {
            loadAllData();
        } else {
            List<DangKyKhamBenh> list = dao.timKiemLichSuTheoText(key);
            updateTable(list);
        }
    }
    
    private void filterByDate() {
        String tu = txtTuNgay.getText().trim();
        String den = txtDenNgay.getText().trim();
        
        List<DangKyKhamBenh> list = dao.locLichSuTheoNgay(tu, den);
        updateTable(list);
    }
    
    private void updateTable(List<DangKyKhamBenh> list) {
        model.setRowCount(0);
        for(DangKyKhamBenh dk : list) {

        	
            BenhNhanBus bus = new BenhNhanBus();
            List<BenhNhan> listbn = new ArrayList<>();
            ChuyenKhoaBus bus1 = new ChuyenKhoaBus();
            List<ChuyenKhoa> listck = new ArrayList<>();
            listck = bus1.getAllCK();
            listbn = bus.getAllBN();
            String tenbn = "";
            String sdt = "";
            String sdtgh = "";
            String tenbs = "";
          	for(BenhNhan bn : listbn) {
        		if(dk.getMabenhnhan() == bn.getMaBenhNhan()) {
        			tenbn = bn.getHoTen();
        			sdt = bn.getSDT()+"";
        			sdtgh = bn.getSDTNguoiGiamHo()+"";
        		}
        		
        	}
        	
         	for(ChuyenKhoa nv : listck) {
        		if(dk.getMachuyenkhoa() == nv.getMa_chuyen_khoa()) {
        			tenbs = nv.getTen_chuyen_khoa();
     
        		}
        		
        	}
            model.addRow(new Object[] {
                dk.getId(),
    
                tenbn,
                sdt,
                sdtgh,
                tenbs,
                dk.getSothutu(),
                dk.getThoigiandangky().format(dtf),
                dk.getTrangthai()
            });
        }
    }
    
    private void initEvents() {
        txtSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchByText();
            }
        });
        
        btnLoc.addActionListener(e -> filterByDate());
        
        btnReset.addActionListener(e -> {
            txtSearch.setText("");
            txtTuNgay.setText(LocalDate.now().toString());
            txtDenNgay.setText(LocalDate.now().toString());
            loadAllData();
        });
    }
}