package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.ToaThuocDao;
import MODEL.CTToaThuoc;
import MODEL.DonThuocCho;

public class FrmLichSuCapThuoc extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    
    private JTextField txtSearch;
    private JTextField txtTuNgay;
    private JTextField txtDenNgay;
    private JButton btnLoc;
    private JButton btnReset;
    
    private ToaThuocDao dao = new ToaThuocDao();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
        new FrmLichSuCapThuoc().setVisible(true);
    }

    public FrmLichSuCapThuoc() {
        setTitle("Lịch Sử Cấp Phát Thuốc - Dược Sĩ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 650);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("LỊCH SỬ ĐƠN THUỐC ĐÃ CẤP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 153, 76)); 
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 15, 980, 40);
        contentPane.add(lblTitle);
        
        int yFilter = 70;
        
        JLabel lblTim = new JLabel("Tìm kiếm (Tên/Mã):");
        lblTim.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTim.setBounds(20, yFilter, 130, 25);
        contentPane.add(lblTim);
        
        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSearch.setBounds(150, yFilter, 240, 30);
        txtSearch.setToolTipText("Nhập tên bệnh nhân hoặc mã toa...");
        contentPane.add(txtSearch);
        
        JLabel lblTu = new JLabel("Từ ngày:");
        lblTu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTu.setBounds(410, yFilter, 60, 25);
        contentPane.add(lblTu);
        
        txtTuNgay = new JTextField();
        txtTuNgay.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTuNgay.setBounds(470, yFilter, 100, 30);
        txtTuNgay.setText(LocalDate.now().toString()); 
        contentPane.add(txtTuNgay);
        
        JLabel lblDen = new JLabel("Đến ngày:");
        lblDen.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDen.setBounds(580, yFilter, 70, 25);
        contentPane.add(lblDen);
        
        txtDenNgay = new JTextField();
        txtDenNgay.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDenNgay.setBounds(650, yFilter, 100, 30);
        txtDenNgay.setText(LocalDate.now().toString());
        contentPane.add(txtDenNgay);
        
        btnLoc = new JButton("Lọc ngày");
        btnLoc.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLoc.setBounds(770, yFilter, 90, 30);
        btnLoc.setBackground(new Color(0, 102, 204));
        btnLoc.setForeground(Color.WHITE);
        btnLoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLoc.setContentAreaFilled(false);
        btnLoc.setOpaque(true);
        btnLoc.setBorderPainted(false);

        contentPane.add(btnLoc);
        
        btnReset = new JButton("Tải lại");
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnReset.setBounds(870, yFilter, 80, 30);
        btnReset.setBackground(new Color(108, 117, 125));
        btnReset.setForeground(Color.WHITE);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset.setContentAreaFilled(false);
        btnReset.setOpaque(true);
        btnReset.setBorderPainted(false);

        contentPane.add(btnReset);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 120, 940, 470);
        contentPane.add(scrollPane);
        
        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Mã Toa", "Mã Phiếu", "Bệnh Nhân", "Bác Sĩ", "Thời Gian Cấp" }
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table.setModel(model);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        scrollPane.setViewportView(table);
        
        initEvents();
        loadAllData(); 
    }
    
    private void loadAllData() {
        List<DonThuocCho> list = dao.getAllLichSuCapPhat();
        updateTable(list);
    }
    
    private void searchByKeyword() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadAllData();
        } else {
            List<DonThuocCho> list = dao.timKiemLichSuTheoTuKhoa(keyword);
            updateTable(list);
        }
    }
    
    private void filterByDate() {
        String tu = txtTuNgay.getText().trim();
        String den = txtDenNgay.getText().trim();
        
        if (tu.isEmpty() || den.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày bắt đầu và kết thúc!");
            return;
        }
        
        List<DonThuocCho> list = dao.timKiemLichSuTheoNgay(tu, den);
        updateTable(list);
    }
    
    private void updateTable(List<DonThuocCho> list) {
        model.setRowCount(0);
        for (DonThuocCho dt : list) {
            model.addRow(new Object[] {
                dt.getMaToaThuoc(),
                dt.getMaPhieuKham(),
                dt.getTenBenhNhan(),
                dt.getTenBacSi(),
                sdf.format(dt.getThoiGian())
            });
        }
    }
    
    private void initEvents() {
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchByKeyword();
            }
        });
        
        btnLoc.addActionListener(e -> filterByDate());
        
        btnReset.addActionListener(e -> {
            txtSearch.setText("");
            txtTuNgay.setText(LocalDate.now().toString());
            txtDenNgay.setText(LocalDate.now().toString());
            loadAllData();
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { 
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int maToa = Integer.parseInt(table.getValueAt(row, 0).toString());
                        showChiTietThuoc(maToa);
                    }
                }
            }
        });
    }
    
    private void showChiTietThuoc(int maToa) {
        JDialog dialog = new JDialog(this, "Chi tiết Toa Thuốc #" + maToa, true);
        dialog.setSize(650, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("DANH SÁCH THUỐC ĐÃ CẤP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0, 153, 76));
        lblTitle.setBounds(20, 10, 300, 30);
        dialog.add(lblTitle);
        
        JTable tblDetail = new JTable();
        DefaultTableModel modelDetail = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Tên thuốc", "Số lượng", "Đơn giá", "Thành tiền", "Cách dùng" }
        );
        tblDetail.setModel(modelDetail);
        tblDetail.setRowHeight(30);
        tblDetail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tblDetail.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        
        JScrollPane scroll = new JScrollPane(tblDetail);
        scroll.setBounds(20, 50, 590, 330);
        dialog.add(scroll);
        
        List<CTToaThuoc> listCT = dao.getChiTietByMaToa(maToa);
        
        DecimalFormat df = new DecimalFormat("#,###");
        
        for (CTToaThuoc ct : listCT) {
            int tongSL = (parseInt(ct.getSang()) + parseInt(ct.getTrua()) 
                        + parseInt(ct.getChieu()) + parseInt(ct.getToi())) * ct.getSoNgay();
            if(tongSL == 0) tongSL = 1;
            
            double thanhTien = tongSL * ct.getDonGia();
            
            modelDetail.addRow(new Object[] {
                ct.getTenThuoc(),
                tongSL + " " + ct.getDonViTinh(),
                df.format(ct.getDonGia()) +" vnd",
                df.format(thanhTien) +" vnd",
                ct.getCachDung()
            });
        }
        
        dialog.setVisible(true);
    }
    
    private int parseInt(String s) {
        try { return Integer.parseInt(s.trim()); } catch(Exception e) { return 0; }
    }
}