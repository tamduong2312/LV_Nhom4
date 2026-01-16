package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BUS.PhongChucNangBus;
import BUS.ChuyenKhoaBus; // Mới
import BUS.ChucVuBus;    // Mới
import MODEL.PhongChucNang;
import MODEL.ChuyenKhoa;
import MODEL.ChucVu;

public class FrmQuanLyPhongChucNang extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTenPhong; 
    private JTable table;
    
    private JComboBox<String> cmbLoaiPhong; 
    private JComboBox<String> cmbChuyenKhoa; 
    private JComboBox<String> cmbChucVu;      

    private PhongChucNangBus bus = new PhongChucNangBus();
    private List<PhongChucNang> listPhong = new ArrayList<>();
    private DefaultTableModel tableModel;
    
    private int selectedId = -1; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmQuanLyPhongChucNang frame = new FrmQuanLyPhongChucNang();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    

    private void loadDataComboBoxes() {

        ChuyenKhoaBus ckBus = new ChuyenKhoaBus();
        List<ChuyenKhoa> listCK = ckBus.getAllCK();
        DefaultComboBoxModel<String> modelCK = new DefaultComboBoxModel<>();
        modelCK.addElement("0 - Không thuộc chuyên khoa");
        for (ChuyenKhoa ck : listCK) {
            modelCK.addElement(ck.getMa_chuyen_khoa() + " - " + ck.getTen_chuyen_khoa());
        }
        cmbChuyenKhoa.setModel(modelCK);

        // Load Chức vụ
        ChucVuBus cvBus = new ChucVuBus();
        List<ChucVu> listCV = cvBus.GetAllTenChucVu();
        DefaultComboBoxModel<String> modelCV = new DefaultComboBoxModel<>();
        modelCV.addElement("0 - Không thuộc chức vụ");
        for (ChucVu cv : listCV) {
            modelCV.addElement(cv.getId() + " - " + cv.getTenChucVu());
        }
        cmbChucVu.setModel(modelCV);
    }
    
    private void loadDataTable() {
        listPhong = bus.getAllPhong();
        fillTable(listPhong);
    }
    
    private void fillTable(List<PhongChucNang> list) {
        ChucVuBus cvbus = new ChucVuBus();
        List<ChucVu> listcv = cvbus.GetAllTenChucVu();
        
        ChuyenKhoaBus ckbus = new ChuyenKhoaBus();
        List<ChuyenKhoa> listck = ckbus.getAllCK();
        
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        for (PhongChucNang p : list) {
            String tenck = " trống ";
            String tencv = "";
            
    
            for(ChuyenKhoa ck : listck) {
                if(p.getMack() == ck.getMa_chuyen_khoa()) {
                    tenck = ck.getTen_chuyen_khoa();
                    break;
                }
            }
            for(ChucVu cv : listcv) {
                if(p.getMachucvu() == cv.getId()) {
                    tencv = cv.getTenChucVu();
                    break;
                }
            }
            
            tableModel.addRow(new Object[] {
                p.getMaphong(),
                p.getTenphong(),
                p.getLoaiphong(),
                tenck, 
                tencv  
            });
        }
    }
    

    private int getIdFromCombo(JComboBox<String> combo) {
        String selected = (String) combo.getSelectedItem();
        if (selected == null || selected.startsWith("0")) return 0;
        return Integer.parseInt(selected.split(" - ")[0]);
    }
    
    private PhongChucNang getModelFromForm() {
        String ten = txtTenPhong.getText().trim();
        String loai = cmbLoaiPhong.getSelectedItem().toString();
        int maCK = getIdFromCombo(cmbChuyenKhoa);
        int maCV = getIdFromCombo(cmbChucVu);
        
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên phòng không được để trống!");
            return null;
        }
        
        PhongChucNang p = new PhongChucNang();
        p.setTenphong(ten);
        p.setLoaiphong(loai);
        p.setMack(maCK);
        p.setMachucvu(maCV);
        return p;
    }
    
    private void resetForm() {
        txtTenPhong.setText("");
        cmbLoaiPhong.setSelectedIndex(0);
        cmbChuyenKhoa.setSelectedIndex(0);
        cmbChucVu.setSelectedIndex(0);
        selectedId = -1;
        table.clearSelection();
    }

    public FrmQuanLyPhongChucNang() {
        setTitle("Quản Lý Phòng Chức Năng"); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700); 
        setLocationRelativeTo(null); 

        Color primaryColor = new Color(0, 123, 255); 
        Color backgroundColor = new Color(245, 248, 250); 
        
        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("THIẾT LẬP PHÒNG CHỨC NĂNG");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(0, 10, 984, 40);
        contentPane.add(lblTitle);


        int labelX = 30, inputX = 140, inputW = 220, startY = 80, gapY = 45;

        addLabel("Tên phòng:", labelX, startY);
        txtTenPhong = new JTextField();
        txtTenPhong.setBounds(inputX, startY, inputW, 30);
        contentPane.add(txtTenPhong);

        addLabel("Loại phòng:", labelX, startY + gapY);
        cmbLoaiPhong = new JComboBox<>(new String[] { "KHOA_NOI", "CLS_CHAN_DOAN_HINH_ANH", "CLS_XET_NGHIEM", "KHAM_CHUYEN_KHOA", "LE_TAN", "NHA_THUOC", "KHO_DUOC" });
        cmbLoaiPhong.setBounds(inputX, startY + gapY, inputW, 30);
        contentPane.add(cmbLoaiPhong);


        int labelX2 = 400, inputX2 = 520;
        addLabel("Chuyên khoa:", labelX2, startY);
        cmbChuyenKhoa = new JComboBox<>();
        cmbChuyenKhoa.setBounds(inputX2, startY, inputW, 30);
        contentPane.add(cmbChuyenKhoa);

        addLabel("Chức vụ:", labelX2, startY + gapY);
        cmbChucVu = new JComboBox<>();
        cmbChucVu.setBounds(inputX2, startY + gapY, inputW, 30);
        contentPane.add(cmbChucVu);

        JButton btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(40, 167, 69)); btnThem.setForeground(Color.WHITE);
        btnThem.setBounds(800, 80, 150, 35);  
        btnThem.setContentAreaFilled(false);
        btnThem.setOpaque(true);
        btnThem.setBorderPainted(false);
        contentPane.add(btnThem);

        JButton btnSua = new JButton("Cập nhật");
        btnSua.setBackground(new Color(255, 193, 7));
        btnSua.setBounds(800, 125, 150, 35);
        btnSua.setContentAreaFilled(false);
        btnSua.setOpaque(true);
        btnSua.setBorderPainted(false);
        contentPane.add(btnSua);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBackground(new Color(220, 53, 69)); btnXoa.setForeground(Color.WHITE);
        btnXoa.setBounds(800, 170, 150, 35);
        btnXoa.setContentAreaFilled(false);
        btnXoa.setOpaque(true);
        btnXoa.setBorderPainted(false);
        contentPane.add(btnXoa);

   
     


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 270, 920, 350);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setRowHeight(30);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Tên Phòng", "Loại", "Mã CK", "Mã CV" }
        ));
        scrollPane.setViewportView(table);


        loadDataComboBoxes();
        loadDataTable();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index == -1) return;


                selectedId = Integer.parseInt(table.getValueAt(index, 0).toString());
                

                PhongChucNang pSelected = null;
                for (PhongChucNang p : listPhong) {
                    if (p.getMaphong() == selectedId) {
                        pSelected = p;
                        break;
                    }
                }

                if (pSelected != null) {
           
                    txtTenPhong.setText(pSelected.getTenphong());
                    cmbLoaiPhong.setSelectedItem(pSelected.getLoaiphong());

              
                    if (pSelected.getMack() > 0) {
                        setComboById(cmbChuyenKhoa, pSelected.getMack());
                    } else {
                        cmbChuyenKhoa.setSelectedIndex(0);
                    }

                    if (pSelected.getMachucvu() > 0) {
                        setComboById(cmbChucVu, pSelected.getMachucvu());
                    } else {
                        cmbChucVu.setSelectedIndex(0);
                    }
                }
            }
        });

        btnThem.addActionListener(e -> {
            PhongChucNang p = getModelFromForm();
            if (p != null) {
                try {
                    if (bus.themPhong(p)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công!");
                        loadDataTable(); 
                        resetForm();
                    }
                } catch (Exception ex) {
                    handleDuplicateError(ex.getMessage());
                }
            }
        });

        btnSua.addActionListener(e -> {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần sửa!");
                return;
            }
            PhongChucNang p = getModelFromForm();
            if (p != null) {
                p.setMaphong(selectedId);
                try {
                    if (bus.suaPhong(p)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                        loadDataTable(); 
                        resetForm();
                    }
                } catch (Exception ex) {
                    handleDuplicateError(ex.getMessage());
                }
            }
        });

        btnXoa.addActionListener(e -> {
            if(selectedId == -1) return;
            if(JOptionPane.showConfirmDialog(null, "Xác nhận xóa?", "Xác nhận", 0) == 0) {
                if(bus.xoaPhong(selectedId)) {
                    JOptionPane.showMessageDialog(null, "Đã xóa!");
                    loadDataTable(); 
                    resetForm();
                }
            }
        });
    }

    private void handleDuplicateError(String msg) {
        if (msg != null && msg.toLowerCase().contains("duplicate")) {
     
            if (msg.contains("ten_phong") || msg.contains("tenphong")) {
                JOptionPane.showMessageDialog(null, "Lỗi: Tên phòng này đã tồn tại trong hệ thống!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Dữ liệu bị trùng lặp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lỗi: " + msg);
        }
    }
    
    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setBounds(x, y, 100, 25);
        contentPane.add(lbl);
    }

    private void setComboById(JComboBox<String> combo, Object id) {
        if (id == null) { combo.setSelectedIndex(0); return; }
        String target = id.toString();
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).startsWith(target + " -")) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        combo.setSelectedIndex(0);
    }
}