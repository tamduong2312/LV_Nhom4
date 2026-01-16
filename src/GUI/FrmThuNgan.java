package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BenhNhanBus;
import BUS.HoaDonBus;
import BUS.NhanVienBUS;
import BUS.PhieuKhamBus;
import DAO.TinhTienDao;
import MODEL.BenhNhan;
import MODEL.HoaDon;
import MODEL.NhanVien;
import MODEL.PhieuKham;
import MODEL.Session;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class FrmThuNgan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    
    private PhieuKhamBus bus = new PhieuKhamBus();
    private NhanVienBUS nvbus = new NhanVienBUS();
    private BenhNhanBus bnbus = new BenhNhanBus();
    
    private List<PhieuKham> listphieu = new ArrayList<>();
    private List<BenhNhan> listbn = new ArrayList<>();
    private List<NhanVien> listnhanvien = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmThuNgan frame = new FrmThuNgan();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void timKiem(String keyword) {
        TinhTienDao dao = new TinhTienDao();
        List<PhieuKham> ketQua = dao.timKiemPhieuKhamThanhToan(keyword);
        loadDataTableFromList(ketQua);
    }
    
    private void loadDataTableFromList(List<PhieuKham> danhSachHienThi) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);

        for (PhieuKham nvs : danhSachHienThi) {
            String tenbn = nvs.getGhiChu() != null ? nvs.getGhiChu() : "Không rõ"; 
            String tennv = "Không rõ";
            String sdt = "";
            String trangthaiHienThi = "";
          

            if ("DA_KHAM".equals(nvs.getTrangThai())) {
                trangthaiHienThi = "Chờ thanh toán"; 
                
                
            } else if ("đã thanh toán".equals(nvs.getTrangThai())) {
                trangthaiHienThi = "Đã thanh toán";
            }
            
            
            else {
                trangthaiHienThi = nvs.getTrangThai();
            }


            for (BenhNhan bn : listbn) {
                if (bn.getMaBenhNhan() == nvs.getMaBenhNhan()) {
                    sdt = bn.getSDT()+"";
                    break; 
                }
            }


            for (NhanVien nv : listnhanvien) {
                if (nv.getMaNV() == nvs.getMaNhanVien()) {
                    tennv = nv.getHoTen();
                    break;
                }
            }


            defaultTableModel.addRow(new Object[] {
            	nvs.getMaPhieuKham(),
                nvs.getMaBenhNhan(),   
                tenbn,                
                sdt,                   
                "BS. " + tennv,        
                nvs.getNgayKham(),     
                trangthaiHienThi,      
                "Thanh toán"            
            });
        }
    }

    

    
    private void loadDataTable() {
        listphieu = bus.getDanhSachBenhNhanThuNgan();
        listbn = bnbus.getAllBN();       
        listnhanvien = nvbus.getAllNV(); 
        loadDataTableFromList(listphieu);
    }

    public FrmThuNgan() {
        setTitle("Quản Lý Thu Ngân");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 650); 
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 248, 250)); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        Font fontValue = new Font("Segoe UI", Font.BOLD, 20);
        
   
        TinhTienDao statsDao = new TinhTienDao();
        JLabel lblTotal = new JLabel("Tổng bệnh nhân");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setForeground(Color.GRAY);
        lblTotal.setBounds(40, 30, 150, 20);
        contentPane.add(lblTotal);
        
        JLabel valTotal = new JLabel(String.valueOf(statsDao.demtrangthaiTATCA())); 
        valTotal.setFont(fontValue);
        valTotal.setForeground(new Color(13, 110, 253));
        valTotal.setBounds(40, 55, 100, 30);
        contentPane.add(valTotal);

        JLabel lblWaiting = new JLabel("Chờ thanh toán");
        lblWaiting.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblWaiting.setForeground(Color.GRAY);
        lblWaiting.setBounds(250, 30, 150, 20);
        contentPane.add(lblWaiting);
        
        JLabel valWaiting = new JLabel(String.valueOf(statsDao.demtrangthaiCHOTHANHTOAN())); 
        valWaiting.setFont(fontValue);
        valWaiting.setForeground(new Color(220, 120, 0)); 
        valWaiting.setBounds(250, 55, 100, 30);
        contentPane.add(valWaiting);
        
        JLabel lblPaid = new JLabel("Đã thanh toán");
        lblPaid.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPaid.setForeground(Color.GRAY);
        lblPaid.setBounds(460, 30, 150, 20);
        contentPane.add(lblPaid);
        
        JLabel valPaid = new JLabel(String.valueOf(statsDao.demtrangthaiDATHANHTOAN()));
        valPaid.setFont(fontValue);
        valPaid.setForeground(new Color(25, 135, 84));
        valPaid.setBounds(460, 55, 100, 30);
        contentPane.add(valPaid);

        textField = new JTextField();
        textField.setBounds(40, 110, 400, 35);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200)), new EmptyBorder(5, 10, 5, 10) 
        ));
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String keyword = textField.getText().trim();
                if (keyword.isEmpty()) loadDataTable(); else timKiem(keyword);
            }
        });
        contentPane.add(textField);
        
        JButton btnAll = new JButton("Tất cả");
        btnAll.setBounds(460, 110, 100, 35);
        styleButton(btnAll, new Color(13, 110, 253), Color.WHITE);
        btnAll.addActionListener(e -> loadDataTable());
        contentPane.add(btnAll);
        btnAll.setContentAreaFilled(false);
        btnAll.setOpaque(true);
        btnAll.setBorderPainted(false);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 170, 910, 380);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        contentPane.add(scrollPane);
        
        table = new JTable();
 
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Mã Phiếu Khám","Mã BN", "Tên Bệnh Nhân", "SĐT", "Bác Sĩ", "Ngày Khám", "Trạng Thái", "Thao Tác" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; 
            }
        });
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setPreferredSize(new java.awt.Dimension(0, 35));
        
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(35);
        
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new javax.swing.JCheckBox()));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {

            if (i != 1 && i != 7) { 
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        scrollPane.setViewportView(table);
        
        JButton btnBack = new JButton("Quay về");
        btnBack.addActionListener(e -> {
          FrmDuocSiThuNgan q = new FrmDuocSiThuNgan();
          q.setVisible(true);
            dispose();
        });
        btnBack.setBounds(850, 565, 100, 30);
        styleButton(btnBack, new Color(108, 117, 125), Color.WHITE);
        contentPane.add(btnBack);

        loadDataTable();
        
        if (!java.beans.Beans.isDesignTime()) {
        	loadDataTable();

        }

    
    }
    
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBorderPainted(false);
            setFont(new Font("Segoe UI", Font.BOLD, 12));
            setForeground(Color.WHITE);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            String trangThai = table.getValueAt(row, 6).toString().trim(); 

            if (trangThai.equalsIgnoreCase("Đã thanh toán") || trangThai.equalsIgnoreCase("cần hoàn tiền")|| trangThai.equalsIgnoreCase("Đã hoàn tiền")) {
                setText("Xem lại");
                setBackground(new Color(25, 135, 84));
            } else {
                setText("Thanh toán");
                setBackground(new Color(13, 110, 253)); 
            }
            return this;
        }
    }
    class ButtonEditor extends javax.swing.DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private int currentRow;

        public ButtonEditor(javax.swing.JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFont(new Font("Segoe UI", Font.BOLD, 12));
            button.setForeground(Color.WHITE);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            currentRow = row;
            String trangThai = table.getValueAt(row, 6).toString().trim();
            
            if (trangThai.equalsIgnoreCase("Đã thanh toán") || trangThai.equalsIgnoreCase("cần hoàn tiền") || trangThai.equalsIgnoreCase("Đã hoàn tiền") ) {
                label = "Xem lại";
                button.setBackground(new Color(25, 135, 84));
            } else {
                label = "Thanh toán";
                button.setBackground(new Color(13, 110, 253));
            }
            
            button.setText(label);
            isPushed = true;
            return button;
        }
        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int mabn = Integer.parseInt(table.getValueAt(currentRow, 1).toString());
                String tenBN = table.getValueAt(currentRow, 2).toString();
                
                String trangthai = table.getValueAt(currentRow, 6).toString();
                

                PhieuKhamBus pkBus = new PhieuKhamBus();
                HoaDonBus hdBus = new HoaDonBus();
                
                int targetMaPK = -1;
                int targetMaHD = -1;
                String bienCheck = "";
                

                if (label.equals("Xem lại") || trangthai.equals("cần hoàn tiền") || trangthai.equals("Đã hoàn tiền")) {
             
                    List<PhieuKham> listPaid = pkBus.getPhieuKhamTheoBenhNhanTrongNgay(mabn);
                    if (listPaid != null && !listPaid.isEmpty()) {
                    	
                    	
                        targetMaPK = listPaid.get(0).getMaPhieuKham();
                        int index = table.getSelectedRow();
                        Session.maphieukham = (int) table.getValueAt(index, 0);
                        
                        if(trangthai.equals("cần hoàn tiền") ) {
                        	
                        	String lydohoantien = hdBus.getGhiChuByMaPK(Session.maphieukham);
                        	JOptionPane.showMessageDialog(null," Lý do hoàn tiền: " + lydohoantien);
                        	   bienCheck = "cần hoàn tiền";
                        }
                        else if(trangthai.equals("Đã hoàn tiền")) {
                     	   bienCheck = "Đã hoàn tiền";
                        }
                        
                        else if(trangthai.equals("Xem lại")){
                        
                        	   bienCheck = "check";
                        }
                        else if(trangthai.equals("Đã thanh toán")){
                            
                     	   bienCheck = "Đã thanh toán";
                     }
            
                        targetMaHD = hdBus.getMaHoaDonByMaPhieuKham(targetMaPK); 
                     
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu đã thanh toán!");
                    }
                } else {
          
                    List<PhieuKham> listWaiting = pkBus.getDanhsachBenhNhanDaKhamTrongNgay(mabn);
                    if (listWaiting != null && !listWaiting.isEmpty()) {
                 
                        targetMaHD = hdBus.getMaHoaDonByMaPhieuKham(Session.maphieukham);
                        if (targetMaHD <= 0) {
                        HoaDon hd = new HoaDon();
                        hd.setMaPhieuKham( Session.maphieukham);
                        hd.setMaNhanVien(Session.maNhanVien);
                        hd.setTongTien(0);
                        targetMaHD = hdBus.taoHoaDon(hd);
                        }
                        bienCheck = "";
                    } else {
                        JOptionPane.showMessageDialog(null, "Bệnh nhân hiện không có phiếu chờ thanh toán!");
                    }
                }


                if (targetMaHD > 0 &&  Session.maphieukham > 0) {
                    JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(button), "Chi tiết hóa đơn", true);
                    FrmTinhTien1 pnlTinhTien = new FrmTinhTien1(Session.maphieukham, tenBN, targetMaHD, mabn, bienCheck);
                    dialog.getContentPane().add(pnlTinhTien);
                    dialog.setSize(850, 720);
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);

                    loadDataTable(); 
                }
            }
            isPushed = false;
            return label;
        }
    

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
    
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false); 
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);

        
    }
}