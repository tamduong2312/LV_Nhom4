package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.text.DecimalFormat;

import com.toedter.calendar.JDateChooser;

import BUS.ThuocBus;
import MODEL.Session; // Giả sử bạn đã có class Session chứa biến static role
import MODEL.Thuoc;

import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class FrmQuanLyThuoc extends JFrame {

    private JPanel contentPane;
    private JTextField txtTenThuoc, txtHoatChat, txtHamLuong, txtDangThuoc, txtLoaiThuoc, txtDonViTinh;
   
    private JTextField txtDonGiaNhap, txtHeSo, txtDonGiaBan, txtNhaSanXuat, txtNuocSanXuat, txtGhiChu;
    private JDateChooser dateChooserNSX, dateChooserHSD;
    private JTable table;
    private JTextField txtTimKiem;
    
    private List<Thuoc> listthuoc = new ArrayList<>();
    private ThuocBus thuocbus = new ThuocBus();
    private int index;
    private DecimalFormat df = new DecimalFormat("#,###");

  
    private boolean isAdmin = false; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                
           
                 Session.role = "QUAN_TRI_VIEN"; 
       
                Session.role = "DUOC_SI"; 
                
               
                FrmQuanLyThuoc frame = new FrmQuanLyThuoc(Session.role);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public FrmQuanLyThuoc(String role) {

        this.isAdmin = role != null && role.trim().equals("QUAN_TRI_VIEN");

 
        setTitle(isAdmin ? "Quản Lý Thuốc (QUẢN TRỊ VIÊN)" : "Quản Lý Thuốc (DƯỢC SĨ)");
        Locale.setDefault(new Locale("vi", "VN")); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1350, 750);
        setLocationRelativeTo(null);
        
        initUI(); 
        loadDataTable(); 
    }

 
    private void calculatePrice() {
        if (!isAdmin) return; 
        try {
            String nhapStr = txtDonGiaNhap.getText().trim().replace(",", "");
            String hesoStr = txtHeSo.getText().trim().replace(",", "."); 
            
            if (!nhapStr.isEmpty() && !hesoStr.isEmpty()) {
                double gianhap = Double.parseDouble(nhapStr);
                double heso = Double.parseDouble(hesoStr);
                double giaban = gianhap * heso;
                txtDonGiaBan.setText(String.format("%.0f", giaban));
            }
        } catch (NumberFormatException e) {}
    }

    private void loadDataTable() {
        listthuoc = thuocbus.getAllTHUOC();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Thuoc t : listthuoc) {
            if (isAdmin) {
          
                model.addRow(new Object[] {
                    t.getMathuoc(), t.getTenthuoc(), t.getHoatchat(), t.getHamluong(), 
                    t.getDangthuoc(), t.getLoaithuoc(), t.getDonvitinh(),
                    df.format(t.getDongianhap()), 
                    df.format(t.getDongiaban()), 
                    t.getNgaysanxuat(), t.getHansudung(), t.getNhasanxuat(), t.getNuocsanxuat(), t.getGhichu()
                });
            } else {
              
                model.addRow(new Object[] {
                    t.getMathuoc(), t.getTenthuoc(), t.getHoatchat(), t.getHamluong(), 
                    t.getDangthuoc(), t.getLoaithuoc(), t.getDonvitinh(),
                    df.format(t.getDongiaban()), 
                    t.getNgaysanxuat(), t.getHansudung(), t.getNhasanxuat(), t.getNuocsanxuat(), t.getGhichu()
                });
            }
        }
    }

    private void loadDataTableTIMKIEM(String id) {
        listthuoc = thuocbus.Timkiemthuoc(id);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Thuoc t : listthuoc) {
            if (isAdmin) {
                model.addRow(new Object[] {
                    t.getMathuoc(), t.getTenthuoc(), t.getHoatchat(), t.getHamluong(), 
                    t.getDangthuoc(), t.getLoaithuoc(), t.getDonvitinh(),
                    df.format(t.getDongianhap()), df.format(t.getDongiaban()), 
                    t.getNgaysanxuat(), t.getHansudung(), t.getNhasanxuat(), t.getNuocsanxuat(), t.getGhichu()
                });
            } else {
                model.addRow(new Object[] {
                    t.getMathuoc(), t.getTenthuoc(), t.getHoatchat(), t.getHamluong(), 
                    t.getDangthuoc(), t.getLoaithuoc(), t.getDonvitinh(),
                    df.format(t.getDongiaban()), 
                    t.getNgaysanxuat(), t.getHansudung(), t.getNhasanxuat(), t.getNuocsanxuat(), t.getGhichu()
                });
            }
        }
    }

    private void xoaTrang() {
        txtTenThuoc.setText(""); txtHoatChat.setText(""); txtHamLuong.setText("");
        txtDangThuoc.setText(""); txtLoaiThuoc.setText(""); txtDonViTinh.setText("");
        
        if(isAdmin) {
            txtDonGiaNhap.setText("");
            txtHeSo.setText("1.2"); 
            txtDonGiaBan.setText("");
        } else {
            txtDonGiaBan.setText("Liên hệ Quản lý");
        }
        
        txtNhaSanXuat.setText(""); txtNuocSanXuat.setText(""); txtGhiChu.setText("");
        dateChooserNSX.setDate(null); dateChooserHSD.setDate(null);
        txtTenThuoc.requestFocus();
        table.clearSelection();
        index = -1;
    }


    private void initUI() {
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 245, 250));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(isAdmin ? new Color(0, 123, 255) : new Color(0, 153, 102));
        pnlHeader.setPreferredSize(new Dimension(100, 60));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("DANH MỤC THUỐC & DƯỢC PHẨM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(240, 245, 250));
        pnlCenter.setLayout(new BorderLayout(10, 10));
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(pnlCenter, BorderLayout.CENTER);


        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBackground(Color.WHITE);
        Color borderColor = isAdmin ? new Color(0, 123, 255) : new Color(0, 153, 102);
        pnlInput.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(borderColor, 1, true),
                "Thông tin thuốc", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new Font("Segoe UI", Font.BOLD, 14), 
                borderColor
        ));
        pnlCenter.add(pnlInput, BorderLayout.NORTH);

        Font fontLbl = new Font("Segoe UI", Font.PLAIN, 13);
        Font fontTxt = new Font("Segoe UI", Font.PLAIN, 14);

        txtTenThuoc = new JTextField(); txtTenThuoc.setFont(fontTxt);
        txtHoatChat = new JTextField(); txtHoatChat.setFont(fontTxt);
        txtHamLuong = new JTextField(); txtHamLuong.setFont(fontTxt);
        txtDangThuoc = new JTextField(); txtDangThuoc.setFont(fontTxt);
        txtLoaiThuoc = new JTextField(); txtLoaiThuoc.setFont(fontTxt);
        txtDonViTinh = new JTextField(); txtDonViTinh.setFont(fontTxt);
        
  
        txtDonGiaNhap = new JTextField(); txtDonGiaNhap.setFont(fontTxt);
        txtHeSo = new JTextField("1.2"); txtHeSo.setFont(fontTxt);
        txtHeSo.setToolTipText("Nhập 1.2 để lãi 20%, 1.5 để lãi 50%");
        
        txtDonGiaBan = new JTextField(); 
        txtDonGiaBan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        if (isAdmin) {
            txtDonGiaBan.setEditable(true); 
            txtDonGiaNhap.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) { calculatePrice(); }
            });
            txtHeSo.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) { calculatePrice(); }
            });
        } else {
      
            txtDonGiaBan.setEditable(false);
            txtDonGiaBan.setFocusable(false);
            txtDonGiaBan.setBackground(new Color(240, 240, 240));
            txtDonGiaBan.setForeground(new Color(0, 100, 0));
            txtDonGiaBan.setText("Liên hệ Quản lý");
        }

        dateChooserNSX = new JDateChooser(); dateChooserNSX.setDateFormatString("yyyy-MM-dd");
        dateChooserHSD = new JDateChooser(); dateChooserHSD.setDateFormatString("yyyy-MM-dd");
        
        txtNhaSanXuat = new JTextField(); txtNhaSanXuat.setFont(fontTxt);
        txtNuocSanXuat = new JTextField(); txtNuocSanXuat.setFont(fontTxt);
        txtGhiChu = new JTextField(); txtGhiChu.setFont(fontTxt);

  
        addInput(pnlInput, createLabel("Tên thuốc:", fontLbl), 0, 0);
        addInput(pnlInput, txtTenThuoc, 1, 0);
        addInput(pnlInput, createLabel("Hoạt chất:", fontLbl), 0, 1);
        addInput(pnlInput, txtHoatChat, 1, 1);
        addInput(pnlInput, createLabel("Hàm lượng:", fontLbl), 0, 2);
        addInput(pnlInput, txtHamLuong, 1, 2);
        addInput(pnlInput, createLabel("Dạng thuốc:", fontLbl), 0, 3);
        addInput(pnlInput, txtDangThuoc, 1, 3);
        addInput(pnlInput, createLabel("Loại thuốc:", fontLbl), 0, 4);
        addInput(pnlInput, txtLoaiThuoc, 1, 4);
        
        addInput(pnlInput, createLabel("Đơn vị tính:", fontLbl), 2, 0);
        addInput(pnlInput, txtDonViTinh, 3, 0);
        
 
        if (isAdmin) {
            addInput(pnlInput, createLabel("Giá nhập:", fontLbl), 2, 1);
            addInput(pnlInput, txtDonGiaNhap, 3, 1);
            
            addInput(pnlInput, createLabel("Hệ số lãi:", fontLbl), 2, 2);
            addInput(pnlInput, txtHeSo, 3, 2);
            
            addInput(pnlInput, createLabel("Giá bán:", fontLbl), 2, 3);
            addInput(pnlInput, txtDonGiaBan, 3, 3);
        } else {
            addInput(pnlInput, createLabel("Giá niêm yết:", fontLbl), 2, 1);
            addInput(pnlInput, txtDonGiaBan, 3, 1);
        }
        
        addInput(pnlInput, createLabel("Ngày SX:", fontLbl), 4, 0);
        addInput(pnlInput, dateChooserNSX, 5, 0);
        addInput(pnlInput, createLabel("Hạn SD:", fontLbl), 4, 1);
        addInput(pnlInput, dateChooserHSD, 5, 1);
        addInput(pnlInput, createLabel("Nhà SX:", fontLbl), 4, 2);
        addInput(pnlInput, txtNhaSanXuat, 5, 2);
        addInput(pnlInput, createLabel("Nước SX:", fontLbl), 4, 3);
        addInput(pnlInput, txtNuocSanXuat, 5, 3);
        addInput(pnlInput, createLabel("Ghi chú:", fontLbl), 4, 4);
        addInput(pnlInput, txtGhiChu, 5, 4);

        // ACTIONS PANEL
        JPanel pnlActions = new JPanel(new BorderLayout());
        pnlActions.setOpaque(false);
        
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSearch.setOpaque(false);
        pnlSearch.add(createLabel("Tìm kiếm thuốc:", fontLbl));
        txtTimKiem = new JTextField(25); txtTimKiem.setFont(fontTxt);
        pnlSearch.add(txtTimKiem);
        
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setOpaque(false);
        
        JButton btnLamMoi = createStyledButton("Làm Mới", new Color(23, 162, 184));
        JButton btnThem = createStyledButton("Thêm Thuốc", new Color(0, 123, 255));
        JButton btnSua = createStyledButton("Cập Nhật", new Color(0, 200, 83));
        JButton btnXoa = createStyledButton("Xóa Thuốc", new Color(220, 53, 69));
        JButton btnQuayLai = createStyledButton("Thoát", new Color(108, 117, 125));
        
        pnlButtons.add(btnLamMoi);
        pnlButtons.add(btnThem);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnQuayLai);
        
        pnlActions.add(pnlSearch, BorderLayout.WEST);
        pnlActions.add(pnlButtons, BorderLayout.EAST);

        // TABLE SETUP
        String[] columnNames;
        if (isAdmin) {
            columnNames = new String[] {
                "Mã", "Tên thuốc", "Hoạt chất", "Hàm lượng", "Dạng", "Loại",
                "ĐVT", "Giá nhập", "Giá bán", "NSX", "HSD", "Nhà SX", "Nước SX", "Ghi chú"
            };
        } else {
            columnNames = new String[] {
                "Mã", "Tên thuốc", "Hoạt chất", "Hàm lượng", "Dạng", "Loại",
                "ĐVT", "Giá bán", "NSX", "HSD", "Nhà SX", "Nước SX", "Ghi chú"
            };
        }

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.setModel(new DefaultTableModel(new Object[][] {}, columnNames));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        JPanel pnlBottom = new JPanel(new BorderLayout(0, 10));
        pnlBottom.setOpaque(false);
        pnlBottom.add(pnlActions, BorderLayout.NORTH);
        pnlBottom.add(scrollPane, BorderLayout.CENTER);
        
        pnlCenter.add(pnlBottom, BorderLayout.CENTER);

        // --- EVENTS ---
        
        btnLamMoi.addActionListener(e -> xoaTrang());

        btnThem.addActionListener(e -> {
            if (dateChooserNSX.getDate() == null || dateChooserHSD.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Chọn ngày!"); return;
            }
            Thuoc t = new Thuoc();
            t.setTenthuoc(txtTenThuoc.getText().trim());
            t.setHoatchat(txtHoatChat.getText());
            t.setHamluong(txtHamLuong.getText());
            t.setDangthuoc(txtDangThuoc.getText());
            t.setLoaithuoc(txtLoaiThuoc.getText());
            t.setDonvitinh(txtDonViTinh.getText());
            
            if (isAdmin) {
                try {
                    double gn = Double.parseDouble(txtDonGiaNhap.getText().replace(",", ""));
                    double gb = Double.parseDouble(txtDonGiaBan.getText().replace(",", ""));
                    t.setDongianhap(gn); t.setDongiaban(gb);
                } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Giá phải là số!"); return; }
            } else {
                t.setDongianhap(0); t.setDongiaban(0);
            }
            
            t.setSoluongton(0);
            t.setNgaysanxuat(dateChooserNSX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            t.setHansudung(dateChooserHSD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            t.setNhasanxuat(txtNhaSanXuat.getText());
            t.setNuocsanxuat(txtNuocSanXuat.getText());
            t.setGhichu(txtGhiChu.getText());

            thuocbus.ThemThuoc(t);
            loadDataTable();
            xoaTrang();
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        });

        btnXoa.addActionListener(e -> {
            try {
                index = table.getSelectedRow();
                if (index != -1 && JOptionPane.showConfirmDialog(null, "Xóa thuốc này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int id = (int) table.getValueAt(index, 0);
                    thuocbus.xoaThuoc(id);
                    loadDataTable();
                    xoaTrang();
                }
            } catch (Exception ex) {}
        });

        btnSua.addActionListener(e -> {
            index = table.getSelectedRow();
            if (index == -1) { JOptionPane.showMessageDialog(null, "Chọn thuốc để sửa!"); return; }
            
            int id = (int) table.getValueAt(index, 0);
            Thuoc thuocCu = listthuoc.get(index); 
            Thuoc t = new Thuoc();
            t.setTenthuoc(txtTenThuoc.getText());
            t.setHoatchat(txtHoatChat.getText());
            t.setHamluong(txtHamLuong.getText());
            t.setDangthuoc(txtDangThuoc.getText());
            t.setLoaithuoc(txtLoaiThuoc.getText());
            t.setDonvitinh(txtDonViTinh.getText());
            
            if (isAdmin) {
                try {
                    double gn = Double.parseDouble(txtDonGiaNhap.getText().replace(",", ""));
                    double gb = Double.parseDouble(txtDonGiaBan.getText().replace(",", ""));
                    t.setDongianhap(gn); t.setDongiaban(gb);
                } catch (Exception ex) { return; }
            } else {
                t.setDongianhap(thuocCu.getDongianhap());
                t.setDongiaban(thuocCu.getDongiaban());
            }
            
            t.setSoluongton(0);
            if(dateChooserNSX.getDate()!=null) t.setNgaysanxuat(dateChooserNSX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if(dateChooserHSD.getDate()!=null) t.setHansudung(dateChooserHSD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            t.setNhasanxuat(txtNhaSanXuat.getText());
            t.setNuocsanxuat(txtNuocSanXuat.getText());
            t.setGhichu(txtGhiChu.getText());

            thuocbus.Suathuoc(t, id);
            loadDataTable();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
        });

        btnQuayLai.addActionListener(e -> dispose());

        txtTimKiem.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = txtTimKiem.getText().trim();
                if (text.isEmpty()) loadDataTable(); else loadDataTableTIMKIEM(text);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                index = table.getSelectedRow();
                if (index == -1) return;
                Thuoc t = listthuoc.get(index);

                txtTenThuoc.setText(t.getTenthuoc());
                txtHoatChat.setText(t.getHoatchat());
                txtHamLuong.setText(t.getHamluong());
                txtDangThuoc.setText(t.getDangthuoc());
                txtLoaiThuoc.setText(t.getLoaithuoc());
                txtDonViTinh.setText(t.getDonvitinh());

                if (isAdmin) {
                    txtDonGiaNhap.setText(String.format("%.0f", t.getDongianhap()));
                    txtDonGiaBan.setText(String.format("%.0f", t.getDongiaban()));
                    if(t.getDongianhap() > 0) {
                        double heso = t.getDongiaban() / t.getDongianhap();
                        txtHeSo.setText(String.format("%.1f", heso));
                    }
                } else {
                    txtDonGiaBan.setText(df.format(t.getDongiaban()));
                }

                try { dateChooserNSX.setDate(java.sql.Date.valueOf(t.getNgaysanxuat().toString())); } catch (Exception ex) {}
                try { dateChooserHSD.setDate(java.sql.Date.valueOf(t.getHansudung().toString())); } catch (Exception ex) {}
                txtNhaSanXuat.setText(t.getNhasanxuat());
                txtNuocSanXuat.setText(t.getNuocsanxuat());
                txtGhiChu.setText(t.getGhichu());
            }
        });
    }
    
    // UI Helpers
    private void addInput(JPanel p, JComponent c, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = (x % 2 != 0) ? 1.0 : 0.0;
        p.add(c, gbc);
    }
    
    private JLabel createLabel(String text, Font font) {
        JLabel lbl = new JLabel(text); lbl.setFont(font); return lbl;
    }
    
    private JButton createStyledButton(String text, Color bg) {
        RoundedButton btn = new RoundedButton(text, bg);
        btn.setPreferredSize(new Dimension(120, 35)); return btn;
    }
    
    class RoundedButton extends JButton {
        private Color backgroundColor;
        public RoundedButton(String text, Color color) {
            super(text); this.backgroundColor = color;
            setContentAreaFilled(false); setFocusPainted(false);
            setBorderPainted(false); setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? backgroundColor.darker() : backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.dispose(); super.paintComponent(g);
        }
    }
}