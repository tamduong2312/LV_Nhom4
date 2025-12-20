package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.KhoThuocBus;
import BUS.ThuocBus;
import MODEL.ChiTietPhieuNhap;
import MODEL.Thuoc;

public class FrmChiTietPhieuNhap extends JDialog {

    private JTable table;
    private DefaultTableModel model;
    private KhoThuocBus khoThuocBus = new KhoThuocBus();
    private ThuocBus thuocBus = new ThuocBus();
    private int maPhieu;
    private List<ChiTietPhieuNhap> listctphieunhap = new ArrayList<>();

    public FrmChiTietPhieuNhap(int maPhieu) {
        this.maPhieu = maPhieu;
        setTitle("Chi Tiết Phiếu Nhập Số: " + maPhieu);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());


        JLabel lblTitle = new JLabel("CHI TIẾT PHIẾU NHẬP #" + maPhieu);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 128, 128));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);


        String[] cols = {"STT", "Mã Thuốc", "Tên Thuốc", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        

        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        
        JScrollPane sc = new JScrollPane(table);
        sc.setBorder(new EmptyBorder(0, 20, 20, 20));
        add(sc, BorderLayout.CENTER);

        // Load Data
        loadData();
    }

    private void loadData() {
        listctphieunhap = khoThuocBus.getChiTietPhieuNhap(maPhieu);
        List<Thuoc> listThuoc = thuocBus.getAllTHUOC(); 

        model.setRowCount(0);
        int stt = 1;
        double tongTienCheck = 0;

        for (ChiTietPhieuNhap ct : listctphieunhap) {
     
            String tenThuoc = "Mã " + ct.getMaThuoc();
            for(Thuoc t : listThuoc) {
                if(t.getMathuoc() == ct.getMaThuoc()) {
                    tenThuoc = t.getTenthuoc();
                    break;
                }
            }

            model.addRow(new Object[] {
                stt++,
                ct.getMaThuoc(),
                tenThuoc,
                ct.getSoLuongNhap(),
                String.format("%,.0f", ct.getDonGiaNhap()),
                String.format("%,.0f", ct.getThanhTien())
            });
            tongTienCheck += ct.getThanhTien();
        }
        

        model.addRow(new Object[] {"", "", "TỔNG CỘNG:", "", "", String.format("%,.0f", tongTienCheck)});
    }
}