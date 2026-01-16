package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BenhNhanBus;
import BUS.ToaThuocBus;
import DAO.PhieuKhamDao;
import MODEL.BenhNhan;
import MODEL.PhieuKham;
import MODEL.Session;
import MODEL.ToaThuoc;

import javax.swing.JTextField;

public class FrmLoadLichSuKham extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel txtSoDienThoai;
    private JTable table;
    private int maBenhNhanCanXem = 0;
    private PhieuKhamDao phieuKhamDao = new PhieuKhamDao();
    private static String tenbn;
    private JTextField txtDiUngThuoc;
    private JTextField txtNgaySinh;
    private JTextField txtNhomMau;
    private JTextField txtGhiChu;
    private JTextField txtSoDienThoai2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmLoadLichSuKham frame = new FrmLoadLichSuKham(11); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public FrmLoadLichSuKham(int maBenhNhanCanXem) {
        this.maBenhNhanCanXem = maBenhNhanCanXem;
        BenhNhanBus bnBus = new BenhNhanBus();
        this.tenbn = bnBus.getTenBenhNhanById(maBenhNhanCanXem); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
     
        Color primaryColor = new Color(0, 123, 255); 
        Color secondaryColor = new Color(245, 248, 250);
        Color accentColor = new Color(40, 167, 69); 
        Font headerFont = new Font("Segoe UI", Font.BOLD, 22);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        setTitle("LỊCH SỬ KHÁM BỆNH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(100, 100, 850, 685);
        setLocationRelativeTo(null); 
        
        txtSoDienThoai = new JPanel();
        txtSoDienThoai.setBackground(secondaryColor);
        txtSoDienThoai.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(txtSoDienThoai);
        txtSoDienThoai.setLayout(null);


        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(primaryColor);
        pnlHeader.setBounds(0, 0, 834, 70);
        txtSoDienThoai.add(pnlHeader);
        pnlHeader.setLayout(null);

        JLabel lblTitle = new JLabel("HỒ SƠ LỊCH SỬ KHÁM BỆNH");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(headerFont);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 11, 834, 48);
        pnlHeader.add(lblTitle);

     
        JLabel lblLabelBN = new JLabel("Bệnh nhân:");
        lblLabelBN.setFont(labelFont);
        lblLabelBN.setBounds(30, 90, 80, 25);
        txtSoDienThoai.add(lblLabelBN);

        JLabel lblValueBN = new JLabel(tenbn != null ? tenbn.toUpperCase() : "N/A");
        lblValueBN.setForeground(primaryColor);
        lblValueBN.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblValueBN.setBounds(115, 90, 400, 25);
        txtSoDienThoai.add(lblValueBN);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 293, 794, 280);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        txtSoDienThoai.add(scrollPane);
        
        table = new JTable();
        table.setRowHeight(30);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(220, 235, 252));
        table.setSelectionForeground(Color.BLACK);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Mã Phiếu", "Tên Bệnh Nhân", "Bác Sĩ Thực Hiện", "Mã Chuyên Khoa", "Ngày Khám", "Trạng Thái","Toa Thuốc"
            }
        ));
        
   
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(240, 240, 240));
        header.setForeground(new Color(50, 50, 50));
        header.setReorderingAllowed(false);
        
    
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        scrollPane.setViewportView(table);
        
        // Action Button
        JButton btnXemChiTiet = new JButton("XEM CHI TIẾT");
        btnXemChiTiet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXemChiTiet.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXemChiTiet.setForeground(Color.WHITE);
        btnXemChiTiet.setBackground(accentColor);
        btnXemChiTiet.setBorderPainted(false);
        btnXemChiTiet.setFocusPainted(false);
        btnXemChiTiet.setBounds(664, 579, 150, 40);
        txtSoDienThoai.add(btnXemChiTiet);
        
        JButton btnThoat = new JButton("ĐÓNG");
        btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnThoat.setBackground(new Color(108, 117, 125));
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setBorderPainted(false);
        btnThoat.setFocusPainted(false);
        btnThoat.setBounds(20, 595, 100, 40);
        txtSoDienThoai.add(btnThoat);
        
        txtDiUngThuoc = new JTextField();
        txtDiUngThuoc.setEditable(false);
        txtDiUngThuoc.setBounds(125, 126, 126, 20);
        txtSoDienThoai.add(txtDiUngThuoc);
        txtDiUngThuoc.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Dị ứng thuốc:");
        lblNewLabel.setBounds(30, 129, 80, 14);
        txtSoDienThoai.add(lblNewLabel);
        
        JLabel lblSinThoi = new JLabel("Số điện thoại:");
        lblSinThoi.setBounds(302, 129, 80, 14);
        txtSoDienThoai.add(lblSinThoi);
        
        JLabel lblNgySinh = new JLabel("Ngày sinh: ");
        lblNgySinh.setBounds(30, 178, 80, 14);
        txtSoDienThoai.add(lblNgySinh);
        
        txtNgaySinh = new JTextField();
        txtNgaySinh.setEditable(false);
        txtNgaySinh.setColumns(10);
        txtNgaySinh.setBounds(125, 175, 126, 20);
        txtSoDienThoai.add(txtNgaySinh);
        
        JLabel lblNhmMu = new JLabel("Nhóm máu: ");
        lblNhmMu.setBounds(302, 178, 80, 14);
        txtSoDienThoai.add(lblNhmMu);
        
        txtNhomMau = new JTextField();
        txtNhomMau.setEditable(false);
        txtNhomMau.setColumns(10);
        txtNhomMau.setBounds(392, 175, 139, 20);
        txtSoDienThoai.add(txtNhomMau);
        
        JLabel lblGhiCh = new JLabel("Ghi chú: ");
        lblGhiCh.setBounds(30, 245, 80, 14);
        txtSoDienThoai.add(lblGhiCh);
        
        txtGhiChu = new JTextField();
        txtGhiChu.setEditable(false);
        txtGhiChu.setColumns(10);
        txtGhiChu.setBounds(125, 222, 585, 60);
        txtSoDienThoai.add(txtGhiChu);
        
        txtSoDienThoai2 = new JTextField();
        txtSoDienThoai2.setEditable(false);
        txtSoDienThoai2.setColumns(10);
        txtSoDienThoai2.setBounds(392, 126, 139, 20);
        txtSoDienThoai.add(txtSoDienThoai2);
        
        loaddatabenhnhan();
        loadDataToTable(maBenhNhanCanXem);
        
    
        btnXemChiTiet.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int maPhieu = Integer.parseInt(table.getValueAt(row, 0).toString());
                int mack = Integer.parseInt(table.getValueAt(row, 3).toString());
                String mabnStr = table.getValueAt(row, 1).toString();
                FrmLoadDuLieuKham q = new FrmLoadDuLieuKham(maPhieu, mabnStr,mack);
                q.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu khám từ danh sách!");
            }
        });

        btnThoat.addActionListener(e -> dispose());
    }

    
    private void loaddatabenhnhan() {
	BenhNhanBus  bnbus = new  BenhNhanBus();
	BenhNhan bn = bnbus.get1BN(maBenhNhanCanXem);
	if (bn != null) {

	    txtNgaySinh.setText(bn.getNgaySinh().toString());

	    txtSoDienThoai2.setText(bn.getSDT());

	    txtNhomMau.setText(bn.getNhomMau());
	    txtDiUngThuoc.setText(bn.getDiUngThuoc());

	    txtGhiChu.setText(bn.getGhiChu());

	}
    }
    private void loadDataToTable(int maBenhNhan) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<PhieuKham> list = phieuKhamDao.getLichSuKhamByMaBenhNhan(maBenhNhan);
        ToaThuocBus toaBus = new ToaThuocBus(); 
        for (PhieuKham pk : list) {
          ToaThuoc ttInfo = toaBus.getTrangThaiVaGhiChuByMaPK(pk.getMaPhieuKham());
          
          String thongTinDonThuoc = "Không có toa";
          if (ttInfo != null) {
              String trangThai = (ttInfo.getTrangThai() != null) ? ttInfo.getTrangThai() : "Đang chờ";
              String ghiChu = (ttInfo.getGhiChu() != null) ? ttInfo.getGhiChu() : "";
              
    
              if (!ghiChu.isEmpty()) {
                  thongTinDonThuoc = "<html>" + trangThai + "<br><font color='red' size='2'>" + ghiChu + "</font></html>";
              } else {
                  thongTinDonThuoc = trangThai;
              }
          }
            model.addRow(new Object[] {
                pk.getMaPhieuKham(),
                tenbn,
                Session.TenNhanVien,
                pk.getMaChuyenKhoa(),
                pk.getNgayKham(),
                pk.getTrangThai(),

                thongTinDonThuoc,
            });
        }
    }
}