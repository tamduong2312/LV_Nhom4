package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

// Import Logic
import BUS.ChiDinhBus;
import BUS.DangKyKhamBenhBus;
import BUS.DichVuBus;
import BUS.PhieuKhamBus;
import BUS.PhongChucNangBus;
import MODEL.ChiTietChiDinh;
import MODEL.DichVu;
import MODEL.PhongChucNang;
import MODEL.Session;

public class PnlChiDinhDichVu extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTable table;
    

    private DefaultTableModel tableModel;
    private DichVuBus dichVuBus = new DichVuBus();
    private ChiDinhBus chiDinhBus = new ChiDinhBus();
    private int maPhieuChiDinhHienTai = -1;
    
    private static int mapk;

    

    private JPopupMenu popupMenu; 
    private JLabel lblSoLuong, lblTongTien; 
    private JButton btnTaoPhieuRef, btnXoaRef, btnThemRef;
    private String checklichsu;

    /**
     * Create the panel.
     */
    public PnlChiDinhDichVu(int mapk,String checklichsu) {
this.mapk = mapk;
this.checklichsu=checklichsu;
        setLayout(null);
        setBackground(new Color(245, 248, 250)); 
        setSize(900, 700); 

    
        Font fontBtn = new Font("Segoe UI", Font.BOLD, 13);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);


        JButton btnNewButton = new JButton("Tạo phiếu mới");
        btnNewButton.setBounds(20, 20, 140, 35); 
        btnNewButton.setFont(fontBtn);
        btnNewButton.setBackground(new Color(0, 123, 255)); 
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBorderPainted(false); 
        btnNewButton.setFocusPainted(false);
        btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnNewButton);


        JButton btnXaDchV = new JButton("Xóa dịch vụ");
        btnXaDchV.setBounds(170, 20, 140, 35); 
        btnXaDchV.setFont(fontBtn);
        btnXaDchV.setBackground(new Color(220, 53, 69)); 
        btnXaDchV.setForeground(Color.WHITE);
        btnXaDchV.setBorderPainted(false);
        btnXaDchV.setFocusPainted(false);
        btnXaDchV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnXaDchV);

    
        JLabel lblNewLabel = new JLabel("Tìm dịch vụ:");
        lblNewLabel.setBounds(330, 20, 100, 35);
        lblNewLabel.setFont(fontLabel);
        lblNewLabel.setForeground(new Color(70, 70, 70));
        add(lblNewLabel);


        textField = new JTextField();
        textField.setBounds(420, 20, 300, 35); 
        textField.setFont(fontText);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10))); 
        textField.setColumns(10);
        add(textField);


        JButton btnThem = new JButton("THÊM VÀO PHIẾU");
        btnThem.setBounds(730, 20, 160, 35);
        btnThem.setFont(fontBtn);
        btnThem.setBackground(new Color(40, 167, 69)); 
        btnThem.setForeground(Color.WHITE);
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnThem);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 80, 870, 530);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        // --- TABLE ---
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
            		"ID CT", "Mã DV", "Tên Dịch vụ", "Thực hiện bởi", "Đơn giá", "SL", "Thành Tiền","Phòng" ,"Trạng thái"
               
            }
        ));

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30); 
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(232, 240, 254)); 
        table.setSelectionForeground(Color.BLACK);
        
  
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(240, 240, 240));
        header.setForeground(new Color(50, 50, 50));
        header.setOpaque(true);
        
        scrollPane.setViewportView(table);

  
        JLabel lblNewLabel_1 = new JLabel("Số lượng dịch vụ: 0");
        lblNewLabel_1.setBounds(20, 630, 200, 30);
        lblNewLabel_1.setFont(fontLabel);
        lblNewLabel_1.setForeground(new Color(0, 102, 204));
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Tổng cộng: 0 VNĐ");
        lblNewLabel_1_1.setBounds(650, 630, 250, 30);
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT); 
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNewLabel_1_1.setForeground(new Color(220, 53, 69)); 
        add(lblNewLabel_1_1);
        

        this.btnTaoPhieuRef = btnNewButton;
        this.btnXoaRef = btnXaDchV;
        this.btnThemRef = btnThem;
        this.lblSoLuong = lblNewLabel_1;
        this.lblTongTien = lblNewLabel_1_1;
        this.tableModel = (DefaultTableModel) table.getModel();
        
     
        popupMenu = new JPopupMenu();
        

        initLogicEvents();

        if (!java.beans.Beans.isDesignTime()) {
            checkAndLoadPhieuCu();
        }
        if ("check".equals(checklichsu)) {
            setFormReadOnly(this);
        }

    }
    


    private void initLogicEvents() {

        btnTaoPhieuRef.addActionListener(e -> {
            if (Session.maphieukham == 0) { 
                JOptionPane.showMessageDialog(this, "Chưa xác định được Phiếu Khám!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Tạo phiếu chỉ định mới?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                maPhieuChiDinhHienTai = chiDinhBus.taoPhieuChiDinh(Session.maphieukham, Session.maNhanVien);
                if (maPhieuChiDinhHienTai > 0) {
                	PhieuKhamBus bus = new PhieuKhamBus();
                	bus.updateTrangThaiChiDinhCLS(Session.maphieukham);
                	
                    JOptionPane.showMessageDialog(this, "Đã tạo Phiếu số: " + maPhieuChiDinhHienTai);
                    tableModel.setRowCount(0);
                    updateLabelTongTien(0, 0);
                    btnTaoPhieuRef.setEnabled(false); 
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi tạo phiếu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ENTER) return;
                
                String text = textField.getText().trim();
                popupMenu.setVisible(false);
                popupMenu.removeAll();
                
                if (!text.isEmpty()) {
                    List<String> list = dichVuBus.timKiemDichVu(text);
                    if (list != null && !list.isEmpty()) {
                        for (String s : list) {
                            JMenuItem item = new JMenuItem(s);
                            item.addActionListener(evt -> textField.setText(s));
                            popupMenu.add(item);
                        }
                        popupMenu.show(textField, 0, textField.getHeight());
                        textField.requestFocus();
                    }
                }
            }
        });

        // --- LOGIC: THÊM DỊCH VỤ ---
        btnThemRef.addActionListener(e -> {
            if (maPhieuChiDinhHienTai == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhấn 'Tạo phiếu mới' trước!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String tenDV = textField.getText();
            if (tenDV == null || tenDV.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên dịch vụ!");
                return;
            }

            List<DichVu> list = dichVuBus.getDichVuByTen(tenDV.trim());
            if (list != null && !list.isEmpty()) {
                DichVu dv = list.get(0);
                int maKhoaCLS = dv.getMachuyenkhoa(); 
                if (maKhoaCLS <= 0) {
                    JOptionPane.showMessageDialog(this, "Dịch vụ này chưa được phân vào chuyên khoa nào!");
                    return;
                }
                if(checkDichVuDaCoTrongBang(dv.getTenDichVu())) {
                    JOptionPane.showMessageDialog(this, "Dịch vụ này đã có trong phiếu!");
                    return;
                }

                ChiTietChiDinh ct = new ChiTietChiDinh();
                ct.setMaPhieuChiDinh(maPhieuChiDinhHienTai);
                ct.setMaDichVu(dv.getMaDichVu()); 
                
     
                
                ct.setDonGia(dv.getDonGia());     
                ct.setSoLuong(1);
        

                int idCT =  chiDinhBus.themChiTietDichVu(ct,Session.maNhanVien );
                if(idCT > 0) {
                	
                	 DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
                
                
              
                
 
                	    	
                            dkBus.taoDangKyDichVuCLS(Session.mabenhnhan, Session.maNhanVien, maKhoaCLS, "CHI_DINH_CLS", Session.maphieukham,idCT);
      
                
                    chiDinhBus.capNhatTongTienPhieu(maPhieuChiDinhHienTai);
                    loadDuLieuTuDBLenBang(maPhieuChiDinhHienTai);
                    textField.setText("");
                    textField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi thêm dịch vụ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dịch vụ: " + tenDV);
            }
        });

        // --- LOGIC: XÓA DỊCH VỤ ---
        btnXoaRef.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ cần xóa trên bảng!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa dịch vụ này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
             
                int idChiTiet = Integer.parseInt(table.getModel().getValueAt(selectedRow, 0).toString());
                

                boolean kq = chiDinhBus.xoaChiTietDichVu(idChiTiet);
                
                if (kq) {
                    chiDinhBus.capNhatTongTienPhieu(maPhieuChiDinhHienTai);
                    loadDuLieuTuDBLenBang(maPhieuChiDinhHienTai);
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void setFormReadOnly(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setEditable(false);
            } else if (c instanceof JTextArea) {
                ((JTextArea) c).setEditable(false);
            } else if (c instanceof JButton) {
                c.setEnabled(false);
            } else if (c instanceof JComboBox) {
                c.setEnabled(false);
            } else if (c instanceof Container) {

                setFormReadOnly((Container) c);
            }
        }
    }

    private void checkAndLoadPhieuCu() {
        if (mapk == 0) return;
        int maPhieuCu = chiDinhBus.getMaPhieuChiDinhByMaPhieuKham(mapk);

        if (maPhieuCu > 0) {
            this.maPhieuChiDinhHienTai = maPhieuCu;
            loadDuLieuTuDBLenBang(maPhieuChiDinhHienTai);
            btnTaoPhieuRef.setEnabled(false);
            btnThemRef.setEnabled(true);
        } else {
            btnTaoPhieuRef.setEnabled(true);
        }
    }
    private void loadDuLieuTuDBLenBang(int maPhieu) {
        tableModel.setRowCount(0);
        

        List<ChiTietChiDinh> listChiTiet = chiDinhBus.getChiTietByMaPhieu(maPhieu);
        List<DichVu> listDichVu = dichVuBus.getDichVu(); 
        List<PhongChucNang> listPhong = new PhongChucNangBus().getPhongChucNang();
        
        double tongTien = 0;

        for (ChiTietChiDinh item : listChiTiet) {
            double thanhTien = item.getSoLuong() * item.getDonGia();
            tongTien += thanhTien;
            
            String tenDV = "Không rõ";
            String phongTH = "Chưa xếp";
            

            for(DichVu dv : listDichVu) {
                if(item.getMaDichVu() == dv.getMaDichVu()) {
                    tenDV = dv.getTenDichVu();
                    
    
                    int maPhongCuaDichVu = dv.getMaphong(); 
          
                    if (maPhongCuaDichVu > 0) {
                        for(PhongChucNang p : listPhong) {
                            if(p.getMaphong() == maPhongCuaDichVu) {
                                phongTH = p.getTenphong();
                                break; 
                            }
                        }
                    }
                    break; 
                }
            }


            tableModel.addRow(new Object[]{
                item.getId(),
                item.getMaDichVu(),
                tenDV,
                "BS." + Session.getTenNhanVien(),
                formatMoney(item.getDonGia()),
                item.getSoLuong(),
                formatMoney(thanhTien),
                phongTH,
                "Đã lưu"
            });
        }
        updateLabelTongTien(listChiTiet.size(), tongTien);
    }

    
    private boolean checkDichVuDaCoTrongBang(String tenDV) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
    
            String tenTrongBang = tableModel.getValueAt(i, 2).toString(); 
            if (tenTrongBang.equalsIgnoreCase(tenDV)) return true;
        }
        return false;
    }

    private void updateLabelTongTien(int soLuong, double tongTien) {
        lblSoLuong.setText("Số lượng dịch vụ: " + soLuong);
        lblTongTien.setText("Tổng cộng: " + formatMoney(tongTien) + " VNĐ");
    }

    private String formatMoney(double amount) {
        return new DecimalFormat("#,###").format(amount);
    }
}