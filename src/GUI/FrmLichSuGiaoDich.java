package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.NhanVienBUS;
import DAO.HoaDonDao;
import MODEL.CTHoaDon;
import MODEL.HoaDon;
import MODEL.NhanVien;

public class FrmLichSuGiaoDich extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    

    private JTextField txtSearch;
    private JTextField txtTuNgay;
    private JTextField txtDenNgay;
    private JButton btnLocNgay;
    private JButton btnLamMoi;
    
    private HoaDonDao dao = new HoaDonDao();
    private DecimalFormat df = new DecimalFormat("#,###");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        new FrmLichSuGiaoDich().setVisible(true);
    }

    public FrmLichSuGiaoDich() {
        setTitle("Lịch Sử Giao Dịch");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 650);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        

        JLabel lblTitle = new JLabel("DANH SÁCH HÓA ĐƠN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 102, 204));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 10, 980, 40);
        contentPane.add(lblTitle);
        
        int yFilter = 60;
        

        JLabel lblTim = new JLabel("Tìm kiếm (Mã HĐ/PK):");
        lblTim.setBounds(20, yFilter, 150, 25);
        contentPane.add(lblTim);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(160, yFilter, 200, 25);
        txtSearch.setToolTipText("Nhập mã hóa đơn hoặc mã phiếu khám...");
        contentPane.add(txtSearch);
        

        JLabel lblTu = new JLabel("Từ ngày:");
        lblTu.setBounds(400, yFilter, 60, 25);
        contentPane.add(lblTu);
        
        txtTuNgay = new JTextField();
        txtTuNgay.setBounds(460, yFilter, 100, 25);
        txtTuNgay.setText(LocalDate.now().toString()); 
        contentPane.add(txtTuNgay);
        
        JLabel lblDen = new JLabel("Đến ngày:");
        lblDen.setBounds(580, yFilter, 70, 25);
        contentPane.add(lblDen);
        
        txtDenNgay = new JTextField();
        txtDenNgay.setBounds(650, yFilter, 100, 25);
        txtDenNgay.setText(LocalDate.now().toString());
        contentPane.add(txtDenNgay);
        

        btnLocNgay = new JButton("Lọc ngày");
        btnLocNgay.setBounds(760, yFilter, 90, 25);
        btnLocNgay.setBackground(new Color(0, 102, 204));
        btnLocNgay.setForeground(Color.WHITE);
        btnLocNgay.setContentAreaFilled(false);
        btnLocNgay.setOpaque(true);
        btnLocNgay.setBorderPainted(false);

        contentPane.add(btnLocNgay);
        

        btnLamMoi = new JButton("Reset");
        btnLamMoi.setBounds(860, yFilter, 80, 25);
        btnLamMoi.setContentAreaFilled(false);
        btnLamMoi.setOpaque(true);
        btnLamMoi.setBorderPainted(false);

        contentPane.add(btnLamMoi);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 100, 940, 480);
        contentPane.add(scrollPane);
        
        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Mã HĐ", "Mã Phiếu Khám", "Mã NV","Tên bệnh nhân", "Tổng Tiền", "Ngày Thanh Toán", "Ghi Chú", "Trạng Thái" }
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table.setModel(model);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        
        scrollPane.setViewportView(table);
        

        initEvents();
        

        loadAllData();
    }
    
    private void initEvents() {
    
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchByText();
            }
        });
        

        btnLocNgay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByDate(); 
            }
        });
        
     
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSearch.setText("");
                txtTuNgay.setText(LocalDate.now().toString());
                txtDenNgay.setText(LocalDate.now().toString());
                loadAllData();
            }
        });

    
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int maHD = Integer.parseInt(table.getValueAt(row, 0).toString());
                        showChiTiet(maHD);
                    }
                }
            }
        });
    }


    private void loadAllData() {
        List<HoaDon> list = dao.getAllHoaDon();
        updateTable(list);
    }
    

    private void searchByText() {
        String keyword = txtSearch.getText().trim();
        if(keyword.isEmpty()) {
            loadAllData();
            return;
        }
        List<HoaDon> list = dao.timKiemTheoText(keyword);
        updateTable(list);
    }
    

    private void searchByDate() {
        String tu = txtTuNgay.getText().trim();
        String den = txtDenNgay.getText().trim();
        
        List<HoaDon> list = dao.timKiemTheoNgay(tu, den);
        updateTable(list);
    }


    private void updateTable(List<HoaDon> list) {
        model.setRowCount(0);
        NhanVienBUS bus1 = new NhanVienBUS();
        List<NhanVien> listbs = new ArrayList<>();
        listbs = bus1.getAllNV();
        String tennv="";
        for (HoaDon hd : list) {
        	
           	for(NhanVien nv : listbs) {
        		if(hd.getMaNhanVien() == nv.getMaNV()) {
        			tennv = nv.getHoTen();
     
        		}
        		
        	}
        	
      
            model.addRow(new Object[] {
                hd.getMaHoaDon(),
                hd.getMaPhieuKham(),
                tennv,
                hd.getTenBenhNhan(),
                df.format(hd.getTongTien()),
                (hd.getNgayThanhToan() != null) ? sdf.format(hd.getNgayThanhToan()) : "",
                hd.getGhiChu(),
                hd.getTrangThai()
            });
        }
    }
    
    private void showChiTiet(int maHD) {
        JDialog dialog = new JDialog(this, "Chi tiết hóa đơn #" + maHD, true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JLabel lblHeader = new JLabel("CHI TIẾT DỊCH VỤ & THUỐC");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblHeader.setBounds(20, 10, 400, 30);
        dialog.add(lblHeader);
        
        JTable tblDetail = new JTable();
        DefaultTableModel modelDetail = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Nội dung", "Số lượng", "Đơn giá", "Thành tiền" }
        );
        tblDetail.setModel(modelDetail);
        tblDetail.setRowHeight(25);
        
        JScrollPane scroll = new JScrollPane(tblDetail);
        scroll.setBounds(20, 50, 540, 280);
        dialog.add(scroll);
        
        List<CTHoaDon> listCT = dao.getChiTietByMaHD(maHD);
        for (CTHoaDon ct : listCT) {
            modelDetail.addRow(new Object[] {
                ct.getNoiDung(), 
                ct.getSoLuong(),
                df.format(ct.getDonGia()),
                df.format(ct.getThanhTien())
            });
        }
        dialog.setVisible(true);
    }
}