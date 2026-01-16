package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.ChiDinhBus;
import BUS.PhieuKhamBus;
import MODEL.DanhSachChoCLS;
import MODEL.Session;

public class FrmDanhSachChoBacSiChanDoanHinhAnh extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    
    private ChiDinhBus chiDinhBus = new ChiDinhBus();
    
    private final Color PRIMARY_COLOR = new Color(0, 153, 153);
    private final Color BG_COLOR = new Color(245, 248, 250);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                new FrmDanhSachChoBacSiChanDoanHinhAnh().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmDanhSachChoBacSiChanDoanHinhAnh() {
        setTitle("Danh Sách Chờ - Bác Sĩ CĐHA: " + Session.TenNhanVien);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 650);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        pnlHeader.setBackground(PRIMARY_COLOR);
        pnlHeader.setPreferredSize(new Dimension(0, 60));
        
        JLabel lblTitle = new JLabel("DANH SÁCH CHỜ THỰC HIỆN CẬN LÂM SÀNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        contentPane.add(pnlHeader, BorderLayout.NORTH);


        JPanel pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        table = new JTable();
        String[] cols = {"ID CT","Mã đăng ký", "Mã BN", "Tên Bệnh Nhân", "Năm sinh", "Giới tính", "Dịch vụ cần làm","Loại dịch vụ", "BS Chỉ định", "Thời gian", "Trạng thái"};
        setupTable(table, cols);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        pnlCenter.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        pnlBottom.setBackground(Color.WHITE);
        
        JButton btnRefresh = new JButton("Làm mới");
        styleButton(btnRefresh, new Color(23, 162, 184));
        
        JButton btnThucHien = new JButton("THỰC HIỆN & TRẢ KẾT QUẢ");
        styleButton(btnThucHien, new Color(40, 167, 69)); 
        
        JButton btnQuayV = new JButton("Quay về");
        styleButton(btnQuayV, new Color(255, 193, 7));    
        btnQuayV.setForeground(Color.BLACK); 

        pnlBottom.add(btnQuayV);
        pnlBottom.add(btnRefresh);
        pnlBottom.add(btnThucHien);
        contentPane.add(pnlBottom, BorderLayout.SOUTH);


        btnQuayV.addActionListener(e -> {
            new FrmLamViecBacSi().setVisible(true);
            dispose(); 
        });

        btnRefresh.addActionListener(e -> loadData());
        
        if (!java.beans.Beans.isDesignTime()) {
        	loadData();

        }
        
        btnThucHien.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bệnh nhân cần thực hiện!");
                return;
            }

            int idChiTiet = Integer.parseInt(table.getValueAt(index, 0).toString());
            int idDangky = Integer.parseInt(table.getValueAt(index, 1).toString());
            
            Session.maDangKyHienTai = Integer.parseInt(table.getValueAt(index, 1).toString());
            int mabn = Integer.parseInt(table.getValueAt(index, 2).toString());
            Session.mabenhnhan = Integer.parseInt(table.getValueAt(index, 2).toString());
            String tenBN = table.getValueAt(index, 3).toString();
            String tenDV = table.getValueAt(index, 6).toString();
            String loaiDV = table.getModel().getValueAt(index, 7).toString();

            JDialog dialog = new JDialog(this, "Trả Kết Quả", true);
            JPanel contentPanel = null;

            if (loaiDV.equalsIgnoreCase("CLS_CHAN_DOAN_HINH_ANH")) {
                PhieuKhamBus bus= new  PhieuKhamBus();
                Session.maphieukham = bus.getMaPhieuKhamByBenhNhan(mabn, Session.machuyenkhoa);
                contentPanel = new PnlNhapKqChuanDoanHinhAnh(idChiTiet, tenBN, tenDV,idDangky);
                dialog.setSize(950, 650);
                
            } else if (loaiDV.equalsIgnoreCase("CLS_XET_NGHIEM")) {
                PhieuKhamBus bus= new  PhieuKhamBus();
                Session.maphieukham = bus.getMaPhieuKhamByBenhNhan(mabn,Session.machuyenkhoa);
                PnlXetNghiem q = new PnlXetNghiem(idChiTiet,tenBN);
                q.setVisible(true);
                dispose();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Loại dịch vụ chưa hỗ trợ: " + loaiDV);
                return;
            }

            if (contentPanel != null) {
                dialog.setContentPane(contentPanel);
                dialog.setLocationRelativeTo(null);
                
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadData();
                    }
                });
                
                dialog.setVisible(true);
            }
        });

        loadData();
    }

    private void loadData() {
    	
    	
        List<DanhSachChoCLS> list = chiDinhBus.getDanhSachChoCLS( Session.machuyenkhoa);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        String currentUserKhoa = Session.chuyenKhoa;

        for (DanhSachChoCLS item : list) {
            String loaiDV = item.getLoaiDichVu();
            boolean hienThi = false;

            if (currentUserKhoa != null && currentUserKhoa.equalsIgnoreCase("Xét nghiệm")
                && loaiDV.equalsIgnoreCase("CLS_XET_NGHIEM")) {
                hienThi = true;
            }
            else if (currentUserKhoa != null && (currentUserKhoa.equalsIgnoreCase("Chẩn đoán hình ảnh") || currentUserKhoa.equalsIgnoreCase("Siêu âm"))
                     && loaiDV.equalsIgnoreCase("CLS_CHAN_DOAN_HINH_ANH")) {
                hienThi = true;
            }
            else if (Session.role != null && Session.role.equalsIgnoreCase("QUAN_LY")) {
                hienThi = true;
            }

            
            
            
            if (hienThi) {
                model.addRow(new Object[]{
                    item.getMaChiTietChiDinh(),
                    item.getMadangky(),
                    item.getMaBenhNhan(),
                    item.getTenBenhNhan(),
                    item.getNamSinh(),
                    item.getGioiTinh(),
                    item.getTenDichVu(),
                    item.getLoaiDichVu(),
                    item.getBacSiChiDinh(),
                    item.getNgayChiDinh(),
                    item.getTrangThai()
                  
                });
       
            }
        }
    }
    
    private void setupTable(JTable table, String[] columns) {
        table.setModel(new DefaultTableModel(new Object[][]{}, columns) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(240, 240, 240));
    }
    
    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(220, 40));
        btn.setFocusPainted(false);

        btn.setContentAreaFilled(false); 
        btn.setOpaque(true);
        btn.setBorderPainted(false);
    }
}