package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import BUS.NhaCungCapBus;
import MODEL.NhaCungCap;

public class FrmQuanLyNCC extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTenNCC;    
    private JTextField txtDiaChi;    
    private JTextField txtSDT;      
    private JTextField txtEmail;     
    private JTextField txtGhiChu; 
    private JTextField txtTimKiem;   
    private JButton btnSua;
    
    private JTable table;
    
 
    private List<NhaCungCap> listNCC = new ArrayList<>();
    private NhaCungCapBus nccBus = new NhaCungCapBus();
    private int selectedId = -1; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FrmQuanLyNCC frame = new FrmQuanLyNCC();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void loadDataTable() {
        listNCC = nccBus.getAllNCC();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (NhaCungCap n : listNCC) {
            model.addRow(new Object[] {
                n.getMaNhaCungCap(), 
                n.getTenNhaCungCap(), 
                n.getDiaChi(), 
                n.getSDT(), 
                n.getEmail(), 
                n.getGhichu()
            });
        }
    }

    private void loadDataTableTIMKIEM(String keyword) {
        listNCC = nccBus.timKiemNCC(keyword);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (NhaCungCap n : listNCC) {
            model.addRow(new Object[] {
                n.getMaNhaCungCap(), 
                n.getTenNhaCungCap(), 
                n.getDiaChi(), 
                n.getSDT(), 
                n.getEmail(), 
                n.getGhichu()
            });
        }
    }

    private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

    private long getLong(Object value) {
        try {
            return value == null ? 0 : Long.parseLong(value.toString());
        } catch(Exception e) { return 0; }
    }


    public FrmQuanLyNCC() {
        setTitle("Quản Lý Nhà Cung Cấp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 650);
        setLocationRelativeTo(null);

        Color primaryColor = new Color(0, 123, 255);
        Color successColor = new Color(40, 167, 69);
        Color warningColor = new Color(255, 193, 7);
        Color dangerColor = new Color(220, 53, 69);
        Color bgColor = new Color(245, 248, 250);
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font boldFont = new Font("Segoe UI", Font.BOLD, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);

        contentPane = new JPanel();
        contentPane.setBackground(bgColor);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- HEADER ---
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(primaryColor);
        lblTitle.setFont(titleFont);
        lblTitle.setBounds(0, 10, 884, 40);
        contentPane.add(lblTitle);

        // --- FORM INPUT ---
        int labelX = 30;
        int fieldX = 160;
        int startY = 70;
        int gapY = 50;
        int fieldWidth = 300;
        int fieldHeight = 30;

        // Tên NCC
        JLabel lblNewLabel = new JLabel("Tên Nhà Cung Cấp:");
        lblNewLabel.setFont(boldFont);
        lblNewLabel.setBounds(labelX, startY, 130, 25);
        contentPane.add(lblNewLabel);

        txtTenNCC = new JTextField();
        styleTextField(txtTenNCC);
        txtTenNCC.setBounds(fieldX, startY, fieldWidth, fieldHeight);
        contentPane.add(txtTenNCC);

        // Địa chỉ
        JLabel lblaCh = new JLabel("Địa Chỉ:");
        lblaCh.setFont(boldFont);
        lblaCh.setBounds(labelX, startY + gapY, 130, 25);
        contentPane.add(lblaCh);

        txtDiaChi = new JTextField();
        styleTextField(txtDiaChi);
        txtDiaChi.setBounds(fieldX, startY + gapY, fieldWidth, fieldHeight);
        contentPane.add(txtDiaChi);

        // SĐT
        JLabel lblNewLabel_1_1 = new JLabel("Số Điện Thoại:");
        lblNewLabel_1_1.setFont(boldFont);
        lblNewLabel_1_1.setBounds(labelX, startY + gapY * 2, 130, 25);
        contentPane.add(lblNewLabel_1_1);

        txtSDT = new JTextField();
        styleTextField(txtSDT);
        txtSDT.setBounds(fieldX, startY + gapY * 2, fieldWidth, fieldHeight);
        contentPane.add(txtSDT);

        // Email
        JLabel lblNewLabel_1_1_1 = new JLabel("Email:");
        lblNewLabel_1_1_1.setFont(boldFont);
        lblNewLabel_1_1_1.setBounds(labelX, startY + gapY * 3, 130, 25);
        contentPane.add(lblNewLabel_1_1_1);

        txtEmail = new JTextField();
        styleTextField(txtEmail);
        txtEmail.setBounds(fieldX, startY + gapY * 3, fieldWidth, fieldHeight);
        contentPane.add(txtEmail);

     
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Ghi Chú:");
        lblNewLabel_1_1_1_1.setFont(boldFont);
        lblNewLabel_1_1_1_1.setBounds(labelX, startY + gapY * 4, 130, 25);
        contentPane.add(lblNewLabel_1_1_1_1);

        txtGhiChu = new JTextField();
        styleTextField(txtGhiChu);
        txtGhiChu.setBounds(fieldX, startY + gapY * 4, fieldWidth, fieldHeight);
        contentPane.add(txtGhiChu);

     
        int btnX = 520;
        int btnY = 70;
        int btnWidth = 140;
        int btnHeight = 40;
        int btnGap = 55;

  
        JButton btnThem = new JButton("Thêm Mới");
        styleButton(btnThem, successColor);
        btnThem.setBounds(btnX, btnY, btnWidth, btnHeight);
        contentPane.add(btnThem);

        btnSua = new JButton("Cập Nhật");
        styleButton(btnSua, primaryColor);
        btnSua.setBounds(btnX, btnY + btnGap, btnWidth, btnHeight);
        contentPane.add(btnSua);
        
        JButton btnXoa = new JButton("Xóa");
        styleButton(btnXoa, dangerColor);
        btnXoa.setBounds(btnX, btnY + btnGap * 2, btnWidth, btnHeight);
        contentPane.add(btnXoa);

     
        JButton btnQuayVe = new JButton("Quay về");
        styleButton(btnQuayVe, warningColor);
        btnQuayVe.setForeground(Color.BLACK);
        btnQuayVe.setBounds(btnX, btnY + btnGap * 3, btnWidth, btnHeight);
        contentPane.add(btnQuayVe);

    
        JLabel lblTmKim = new JLabel("Tìm kiếm:");
        lblTmKim.setFont(boldFont);
        lblTmKim.setBounds(30, 340, 80, 30);
        contentPane.add(lblTmKim);

        txtTimKiem = new JTextField();
        styleTextField(txtTimKiem);
        txtTimKiem.setBounds(110, 340, 745, 30);
        contentPane.add(txtTimKiem);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 390, 825, 200);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(mainFont);
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 230, 230));
        table.getTableHeader().setFont(boldFont);
        table.getTableHeader().setBackground(new Color(232, 236, 241));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setSelectionBackground(new Color(184, 218, 255));
        
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Mã NCC", "Tên Nhà Cung Cấp", "Địa Chỉ", "Số Điện Thoại", "Email", "Ghi chú"
            }
        ));
        scrollPane.setViewportView(table);

   
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index == -1) return;

                selectedId = Integer.parseInt(getString(table.getValueAt(index, 0)));

                String ten      = getString(table.getValueAt(index, 1));
                String diaChi   = getString(table.getValueAt(index, 2));
                long sdt        = getLong(table.getValueAt(index, 3));
                String email    = getString(table.getValueAt(index, 4));
                String ghiChu   = getString(table.getValueAt(index, 5));

                txtTenNCC.setText(ten);
                txtDiaChi.setText(diaChi);
                txtSDT.setText(sdt == 0 ? "" : "0" + sdt); 
                txtEmail.setText(email);
                txtGhiChu.setText(ghiChu);
            }
        });


        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ten = txtTenNCC.getText().trim();
                String sdtStr = txtSDT.getText().trim();
                String emailStr = txtEmail.getText().trim();
                
                if (ten.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được để trống!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    txtTenNCC.requestFocus();
                    return;
                }
                
                if (emailStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "email không được để trống!");
                    return;
                }
                
  
                try {
                    long sdt = 0;
                    if(!sdtStr.isEmpty()) sdt = Long.parseLong(sdtStr);

                    NhaCungCap ncc = new NhaCungCap();
                    ncc.setTenNhaCungCap(ten);
                    ncc.setDiaChi(txtDiaChi.getText());
                    ncc.setSDT(sdt);
                    ncc.setEmail(txtEmail.getText());
                    ncc.setGhichu(txtGhiChu.getText());

                  if(nccBus.isSDTExist(sdtStr)) {
                      JOptionPane.showMessageDialog(null, "Số điện thoại bị trùng!");
                      return;
                  }
                    if (nccBus.themNCC(ncc)) {
                        loadDataTable();
                        JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!");
                        clearForm();
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại phải là số!", "Lỗi định dạng", JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    String msg = ex.getMessage();
                
                    if (msg != null && (msg.contains("Duplicate entry") || msg.contains("Duplicate"))) {
                        if (msg.contains("so_dien_thoai")) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại trong danh sách!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                            txtSDT.requestFocus();
                        } else if (msg.contains("email")) {
                            JOptionPane.showMessageDialog(null, "Email này đã tồn tại trong hệ thống!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                            txtEmail.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(null, "Dữ liệu bị trùng lặp (Tên hoặc mã) với nhà cung cấp khác!", "Lỗi trùng", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi: " + msg, "Hệ thống", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

 
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa!");
                    return;
                }
                
                String ten = txtTenNCC.getText().trim();
                String sdtStr = txtSDT.getText().trim();
                String emailStr = txtEmail.getText().trim();
                
                if (ten.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tên không được để trống!");
                    return;
                }
                
                if (emailStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "email không được để trống!");
                    return;
                }
                if(nccBus.isSDTExist(sdtStr)) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại bị trùng!");
                    return;
                }
                try {
                    long sdt = 0;
                    if(!sdtStr.isEmpty()) sdt = Long.parseLong(sdtStr);

                    NhaCungCap ncc = new NhaCungCap();
                    ncc.setMaNhaCungCap(selectedId);
                    ncc.setTenNhaCungCap(ten);
                    ncc.setDiaChi(txtDiaChi.getText());
                    ncc.setSDT(sdt);
                    ncc.setEmail(txtEmail.getText());
                    ncc.setGhichu(txtGhiChu.getText());

  
                    if (nccBus.suaNCC(ncc)) {
                        loadDataTable();
                        JOptionPane.showMessageDialog(null, "cập nhật nhà cung cấp thành công!");
                        clearForm();
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại phải là số!", "Lỗi định dạng", JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    String msg = ex.getMessage();

                    if (msg != null && (msg.contains("Duplicate entry") || msg.contains("Duplicate"))) {
                        if (msg.contains("so_dien_thoai")) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại trong danh sách!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                            txtSDT.requestFocus();
                        } else if (msg.contains("email")) {
                            JOptionPane.showMessageDialog(null, "Email này đã tồn tại trong hệ thống!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                            txtEmail.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(null, "Dữ liệu bị trùng lặp (Tên hoặc mã) với nhà cung cấp khác!", "Lỗi trùng", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi: " + msg, "Hệ thống", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

  
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xóa!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                    null, 
                    "Bạn có chắc chắn muốn xóa nhà cung cấp này?", 
                    "Xác nhận xóa", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
             
                    int result = nccBus.xoaNCC(selectedId);

                    if (result == 1) {
                        JOptionPane.showMessageDialog(null, "Đã xóa nhà cung cấp thành công!");
                        loadDataTable();
                        clearForm();
                        selectedId = -1; 
                    } else if (result == -1) {
     
                        JOptionPane.showMessageDialog(null, 
                            "<html><b>Không thể xóa nhà cung cấp này!</b><br>" +
                            "Lý do: Nhà cung cấp này đã có các hóa đơn nhập hàng hoặc đang cung cấp thuốc cho bệnh viện.<br>" +
                            "Để giữ tính chính xác cho báo cáo tài chính, bạn không thể xóa hoàn toàn nhà cung cấp này.</html>", 
                            "Lỗi ràng buộc dữ liệu", 
                            JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại! Vui lòng thử lại sau.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

 
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtTimKiem.getText().trim();
                if (text.isEmpty()) {
                    loadDataTable();
                } else {
                    loadDataTableTIMKIEM(text);
                }
            }
        });

    
        btnQuayVe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmQuanLy q = new FrmQuanLy();
                q.setVisible(true);
                dispose();
            }
        });


        loadDataTable();
    }



    private void clearForm() {
        txtTenNCC.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtGhiChu.setText("");
        selectedId = -1;
        table.clearSelection();
    }

    private void styleTextField(JTextField txt) {
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(new MatteBorder(0, 0, 2, 0, new Color(160, 160, 160))); 
        txt.setBackground(new Color(245, 248, 250)); 
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}