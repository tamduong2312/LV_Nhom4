package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BUS.*;
import MODEL.*;

public class FrmPhanCongCaLam extends JFrame {

    private JTable tablePhanCong;
    private DefaultTableModel model;

    private BangPhanCongCaLamBus bangPhanCongBus = new BangPhanCongCaLamBus();
    private NhanVienBUS nhanVienBus = new NhanVienBUS();
    private ChucVuBus chucVuBus = new ChucVuBus();
    private ChuyenKhoaBus chuyenKhoaBus = new ChuyenKhoaBus();

    private List<NhanVien> listNhanVien;
    private List<BangPhanCongCaLam> listPhanCong;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmPhanCongCaLam().setVisible(true));
    }

    public FrmPhanCongCaLam() {
        setTitle("Lịch Phân Công Nhân Sự");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(new Color(0, 123, 255));
        pnlHeader.setPreferredSize(new Dimension(0, 50));
        
 
        JButton btnQuayVe = new JButton("Quay về");
        btnQuayVe.setPreferredSize(new Dimension(100, 40));
        btnQuayVe.setFocusPainted(false);
        btnQuayVe.setFont(new Font("Arial", Font.BOLD, 14));
        btnQuayVe.setBackground(new Color(255, 193, 7));
        btnQuayVe.setForeground(Color.BLACK);
        
        JPanel pnlQuayVe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlQuayVe.setOpaque(false);
        pnlQuayVe.add(btnQuayVe);
        pnlHeader.add(pnlQuayVe, BorderLayout.WEST);
        

    
        btnQuayVe.addActionListener(e -> {
         FrmQuanLy q = new FrmQuanLy();
        	 q.setVisible(true);
        	 dispose();
        	 
        });
        

        JLabel lblTitle = new JLabel("BẢNG PHÂN CÔNG CA LÀM VIỆC", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle, BorderLayout.CENTER);
        mainPanel.add(pnlHeader, BorderLayout.NORTH);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablePhanCong = new JTable(model);
        tablePhanCong.setRowHeight(90);
        tablePhanCong.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablePhanCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
       
        tablePhanCong.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
           
        tablePhanCong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    xuLyClickDupChuot();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablePhanCong);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Chi tiết lịch làm việc"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLamMoi = new JButton("Làm mới dữ liệu");
        btnLamMoi.setPreferredSize(new Dimension(150, 40));
        btnLamMoi.addActionListener(e -> taiDuLieuLenBang());
        pnlBottom.add(btnLamMoi);
        mainPanel.add(pnlBottom, BorderLayout.SOUTH);
 
        taiDuLieuLenBang();
        
        if (!java.beans.Beans.isDesignTime()) {
        	   taiDuLieuLenBang();

        }

    }

    private void taiDuLieuLenBang() {
        listNhanVien = nhanVienBus.getAllNV();
        listPhanCong = bangPhanCongBus.getAllBangPhanCong();

        String[] columns = {"Chức Vụ - Chuyên Khoa", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"};
        model.setColumnIdentifiers(columns);

        taoCacDongChucVu();

        dienNhanVienVaoO();
        
        tablePhanCong.getColumnModel().getColumn(0).setPreferredWidth(250);
    }

    private void taoCacDongChucVu() {
        model.setRowCount(0);
        
        NhanVienBUS bus = new NhanVienBUS();
        List<NhanVien> listNV = bus.getAllNV();
        
        List<String> uniqueRows = new ArrayList<>();
        
        for (NhanVien nv : listNV) {
            String chucVu = nv.getChucVu().trim();
            String chuyenKhoa = nv.getMaChuyenKhoa();
            
            String tenDongHienThi = "";
            
            if (nv.getChucVu().trim().equals("Quản trị viên")) {
                continue; 
            }
            
            if (chuyenKhoa != null && !chuyenKhoa.isEmpty() && !chuyenKhoa.equalsIgnoreCase("null")) {
                tenDongHienThi = chucVu + " - " + chuyenKhoa.trim();
            } else {
                tenDongHienThi = chucVu;
            }
            
            if (!uniqueRows.contains(tenDongHienThi)) {
                uniqueRows.add(tenDongHienThi);
            }
        }

        java.util.Collections.sort(uniqueRows);

        for (String rowName : uniqueRows) {
            model.addRow(new Object[]{ rowName });
        }
    }

    private void dienNhanVienVaoO() {
        for (BangPhanCongCaLam pc : listPhanCong) {
            
            NhanVien nv = timNhanVienTheoID(pc.getMaNhanVien());
            
            if (nv == null) continue;

            int colIndex = layCotTheoThu(pc.getThu());

            String tenDongCanTim = taoTenDongTuNhanVien(nv);
            int rowIndex = timDongTheoTen(tenDongCanTim);

            if (colIndex != -1 && rowIndex != -1) {
                String noiDungMoi = taoNoiDungHienThi(nv.getHoTen(), pc.getGioLam(), pc.getGioKetThuc(), pc.getPhong());
                Object giaTriCu = model.getValueAt(rowIndex, colIndex);
                
                if (giaTriCu == null || giaTriCu.toString().isEmpty()) {
                    model.setValueAt("<html>" + noiDungMoi + "</html>", rowIndex, colIndex);
                } else {
                    String htmlCu = giaTriCu.toString().replace("</html>", "");
                    model.setValueAt(htmlCu + "<br><hr>" + noiDungMoi + "</html>", rowIndex, colIndex);
                }
            }
        }
    }

    private String taoTenDongTuNhanVien(NhanVien nv) {
        String chucVu = nv.getChucVu().trim();
        String chuyenKhoa = nv.getMaChuyenKhoa();

        if (chuyenKhoa != null && !chuyenKhoa.isEmpty()) {
            return chucVu + " - " + chuyenKhoa.trim();
        }
        
        return chucVu;
    }

    private int timDongTheoTen(String tenDong) {
        for (int i = 0; i < model.getRowCount(); i++) {
            String value = model.getValueAt(i, 0).toString();
            if (value.equalsIgnoreCase(tenDong)) {
                return i;
            }
        }
        return -1;
    }

    private NhanVien timNhanVienTheoID(int maNV) {
        for (NhanVien nv : listNhanVien) {
            if (nv.getMaNV() == maNV) return nv;
        }
        return null;
    }

    private int layCotTheoThu(String thu) {
        if(thu == null) return -1;
        switch (thu.trim()) {
            case "Thứ 2": return 1;
            case "Thứ 3": return 2;
            case "Thứ 4": return 3;
            case "Thứ 5": return 4;
            case "Thứ 6": return 5;
            case "Thứ 7": return 6;
            case "Chủ Nhật": return 7;
            default: return -1;
        }
    }

    private String taoNoiDungHienThi(String ten, Time time, Time time2, String phong) {
        return String.format(
            "<div style='text-align:center'><b>%s</b><br>" +
            "<font color='blue'>[%s - %s]</font><br>" +
            "<font color='gray'>(%s)</font></div>", 
            ten, time, time2, phong
        );
    }

    private void xuLyClickDupChuot() {
        int row = tablePhanCong.getSelectedRow();
        int col = tablePhanCong.getSelectedColumn();

        if (row != -1 && col > 0) {
            String tenDong = tablePhanCong.getValueAt(row, 0).toString();
            String thu = tablePhanCong.getColumnName(col);

            String chucVu = tenDong;
            String chuyenKhoa = "";
            
            if (tenDong.contains(" - ")) {
                String[] parts = tenDong.split(" - ", 2);
                chucVu = parts[0].trim();
                chuyenKhoa = parts[1].trim();
            }

            JDialog dialog = new JDialog(this, "Điều Chỉnh Ca Làm", true);

            FrmDieuChinhCaLam panel = new FrmDieuChinhCaLam();
         
            panel.setDuLieu(chucVu, chuyenKhoa, thu);
            
            dialog.add(panel);
            dialog.setSize(950, 500);
            dialog.setLocationRelativeTo(null);
            
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    taiDuLieuLenBang();
                }
            });
            dialog.setVisible(true);
        }
    }
    
    
}
