package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.custom.clockchooser.TimeClockChooser;

import BUS.BangPhanCongCaLamBus;
import BUS.ChucVuBus;
import BUS.ChuyenKhoaBus;
import BUS.NhanVienBUS;
import BUS.PhongChucNangBus;
import MODEL.BangPhanCongCaLam;
import MODEL.ChucVu;
import MODEL.ChuyenKhoa;
import MODEL.NhanVien;
import MODEL.PhongChucNang;

public class FrmDieuChinhCaLam extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tableNhanVienKhaDung;
    private JTable tableNhanVienPhanCa;
    
    private TimeClockChooser clockBatDau;
    private TimeClockChooser clockKetThuc;
    
    private BangPhanCongCaLamBus bpbus = new BangPhanCongCaLamBus();
    private List<NhanVien> listnhanvienchuacocalam = new ArrayList<>();
    
    private String currentThu = ""; 
    private String currentChucVu = "";
    private String currentChuyenKhoa = "";
    private JComboBox<String> cmbphong;
    
    private JSpinner spinBatDau;
    private JSpinner spinKetThuc;
    private int currentMaCK_Resolved = -1; 

    private final Color PRIMARY_COLOR = new Color(0, 123, 255);      
    private final Color SUCCESS_COLOR = new Color(40, 167, 69);      
    private final Color DANGER_COLOR = new Color(220, 53, 69);       
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private final Font BOLD_FONT = new Font("Segoe UI", Font.BOLD, 13);

    public FrmDieuChinhCaLam() {
        setLayout(null);
        setBackground(Color.WHITE); 
        setSize(800, 500); 


        JPanel pnlHeader = new JPanel();
        pnlHeader.setLayout(null);
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBounds(20, 10, 740, 70);
        
        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(new Color(200, 200, 200)), "Thiết lập mặc định cho ca này");
        border.setTitleFont(BOLD_FONT);
        border.setTitleColor(PRIMARY_COLOR);
        pnlHeader.setBorder(border);
        add(pnlHeader);

        JLabel lblGioBD = createLabel("Giờ BĐ:", 20, 25, 60, 25);
        pnlHeader.add(lblGioBD);
        SpinnerDateModel modelBD = new SpinnerDateModel();
        spinBatDau = new JSpinner(modelBD);
        JSpinner.DateEditor editorBD = new JSpinner.DateEditor(spinBatDau, "HH:mm");
        spinBatDau.setEditor(editorBD);
        spinBatDau.setBounds(80, 25, 80, 25);
        pnlHeader.add(spinBatDau);

        
        JLabel lblGioKT = createLabel("Giờ KT:", 200, 25, 60, 25);
        pnlHeader.add(lblGioKT);
        
        SpinnerDateModel modelKT = new SpinnerDateModel();
        spinKetThuc = new JSpinner(modelKT);
        JSpinner.DateEditor editorKT = new JSpinner.DateEditor(spinKetThuc, "HH:mm");
        spinKetThuc.setEditor(editorKT);
        spinKetThuc.setBounds(260, 25, 80, 25);
        pnlHeader.add(spinKetThuc);
        
        JLabel lblPhong = createLabel("Phòng:", 380, 25, 60, 25);
        pnlHeader.add(lblPhong);
        
        cmbphong = new JComboBox();
        cmbphong.setBounds(493, 27, 187, 22);
        pnlHeader.add(cmbphong);


        JLabel lblTrai = new JLabel("Danh sách khả dụng");
        lblTrai.setFont(BOLD_FONT);
        lblTrai.setForeground(Color.GRAY);
        lblTrai.setBounds(20, 90, 200, 20);
        add(lblTrai);

        JScrollPane scrollTrai = new JScrollPane();
        scrollTrai.setBounds(20, 115, 250, 320);
        scrollTrai.getViewport().setBackground(Color.WHITE);
        add(scrollTrai);
        
     
        tableNhanVienKhaDung = new JTable();
        styleTable(tableNhanVienKhaDung);
        tableNhanVienKhaDung.setModel(new DefaultTableModel(
            new Object[][] {}, new String[] { "Mã NV", "Tên NV", "Chức vụ" }
        ));
        scrollTrai.setViewportView(tableNhanVienKhaDung);


        JLabel lblPhai = new JLabel("Đã phân công");
        lblPhai.setFont(BOLD_FONT);
        lblPhai.setForeground(PRIMARY_COLOR);
        lblPhai.setBounds(490, 90, 200, 20);
        add(lblPhai);

        JScrollPane scrollPhai = new JScrollPane();
        scrollPhai.setBounds(490, 115, 400, 320);
        scrollPhai.getViewport().setBackground(Color.WHITE);
        add(scrollPhai);
        

        tableNhanVienPhanCa = new JTable();
        styleTable(tableNhanVienPhanCa); 
        tableNhanVienPhanCa.setModel(new DefaultTableModel(
            new Object[][] {}, new String[] { "ID", "Tên NV", "Phòng", "Bắt đầu", "Kết thúc" }
        ));
        scrollPhai.setViewportView(tableNhanVienPhanCa);

  
        
        RoundedButton btnAdd = new RoundedButton(">> Thêm vào ca >>", SUCCESS_COLOR);
        btnAdd.setBounds(280, 200, 200, 35);
        add(btnAdd);
        
        RoundedButton btnRemove = new RoundedButton("<< Xóa khỏi ca <<", DANGER_COLOR);
        btnRemove.setBounds(280, 260, 200, 35);
        add(btnRemove);
        
        RoundedButton btnSua = new RoundedButton("<< sửa <<", DANGER_COLOR);
        btnSua.setBounds(280, 350, 200, 35);
        add(btnSua);
        loadDataComboBoxPhong() ;
        


        // =====================================================================
        //  LOGIC XỬ LÝ
        // =====================================================================
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	try {

                    spinBatDau.commitEdit();
                    spinKetThuc.commitEdit();
                } catch (java.text.ParseException pe) {
                    JOptionPane.showMessageDialog(null, "Giờ nhập vào không đúng định dạng (HH:mm)!");
                    return;
                }

                int rowSrc = tableNhanVienKhaDung.getSelectedRow();
                if (rowSrc == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên từ danh sách bên trái!");
                    return;
                }

        
                String phong = "";
                if (cmbphong.getSelectedItem() != null) {
                    phong = cmbphong.getSelectedItem().toString().trim();
                }
      
                if (phong.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng làm việc!");
                    return;
                }


                java.util.Date dateBD = (java.util.Date) spinBatDau.getValue();
                java.util.Date dateKT = (java.util.Date) spinKetThuc.getValue();

                java.sql.Time sqlTimeBD = new java.sql.Time(dateBD.getTime());
                java.sql.Time sqlTimeKT = new java.sql.Time(dateKT.getTime());

  
                java.util.Calendar cal = java.util.Calendar.getInstance();

                cal.setTime(dateBD);
                int startMin = cal.get(java.util.Calendar.HOUR_OF_DAY) * 60 + cal.get(java.util.Calendar.MINUTE);

                cal.setTime(dateKT);
                int endMin = cal.get(java.util.Calendar.HOUR_OF_DAY) * 60 + cal.get(java.util.Calendar.MINUTE);

                // KHUNG GIỜ HOẠT ĐỘNG (06:00 - 21:00) ---
                // 06:00 = 360 phút | 21:00 = 1260 phút
                if (startMin < 360 || endMin > 1260) {
                    JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ! Phòng khám chỉ mở cửa từ 06:00 đến 21:00.");
                    return;
                }

                // ---  GIỜ NGHỈ TRƯA (11:30 - 13:30) ---
                // 11:30 = 690 phút | 13:30 = 810 phút

                if ((startMin > 690 && startMin < 810) || (endMin > 690 && endMin < 810)) {
                    JOptionPane.showMessageDialog(null, "Không thể chọn giờ bắt đầu hoặc kết thúc nằm trong giờ nghỉ trưa (11:30 - 13:30)!");
                    return;
                }


                if (endMin <= startMin) {
                    JOptionPane.showMessageDialog(null, "Lỗi: Giờ kết thúc phải sau giờ bắt đầu!");
                    return;
                }

   
                int idNhanVien = Integer.parseInt(tableNhanVienKhaDung.getValueAt(rowSrc, 0).toString());
                String tenNhanVien = tableNhanVienKhaDung.getValueAt(rowSrc, 1).toString();
                
                
                
                String nguoiDangTruc = bpbus.kiemTraPhongDaCoNguoi(phong,currentChucVu, currentThu, -1);
              

                if (nguoiDangTruc != null) {
                    JOptionPane.showMessageDialog(null, 
                        "Phòng " + phong + " vào " + currentThu + " đã có nhân viên trực là: " + nguoiDangTruc + 
                        "\nVui lòng xóa nhân viên cũ trước khi thêm người mới vào phòng này!", 
                        "Phòng đã có người", JOptionPane.WARNING_MESSAGE);
                    return; 
                }
   
                BangPhanCongCaLam b = new BangPhanCongCaLam(idNhanVien, phong, sqlTimeBD, sqlTimeKT, currentThu);

                if (bpbus.themPhanCong(b)) {
             
                    bpbus.layGioiHanBNHomNay(currentChuyenKhoa);
                    
  
                    DefaultTableModel modelSrc = (DefaultTableModel) tableNhanVienKhaDung.getModel();
                    DefaultTableModel modelDest = (DefaultTableModel) tableNhanVienPhanCa.getModel();
                    

                    modelDest.addRow(new Object[] { b.getId(), tenNhanVien, phong, sqlTimeBD, sqlTimeKT });
                    modelSrc.removeRow(rowSrc);
                    
                    JOptionPane.showMessageDialog(null, "Đã thêm phân công thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi: Không thể lưu phân công!");
                }
            }
        });
        
        tableNhanVienPhanCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableNhanVienPhanCa.getSelectedRow();
                if (row != -1) {
    
                    String phongTrongBang = tableNhanVienPhanCa.getValueAt(row, 2).toString();
                    cmbphong.setSelectedItem(phongTrongBang);


                    Object objGioBD = tableNhanVienPhanCa.getValueAt(row, 3);
                    Object objGioKT = tableNhanVienPhanCa.getValueAt(row, 4);
                    
                    try {
                        if (objGioBD instanceof java.util.Date) {
                            spinBatDau.setValue(objGioBD);
                            spinKetThuc.setValue(objGioKT);
                        } else if (objGioBD != null) {
                            java.sql.Time tBD = java.sql.Time.valueOf(objGioBD.toString());
                            java.sql.Time tKT = java.sql.Time.valueOf(objGioKT.toString());
                            spinBatDau.setValue(tBD);
                            spinKetThuc.setValue(tKT);
                        }
                    } catch (Exception e) {
                        System.out.println("Lỗi đồng bộ giờ lên Spinner: " + e.getMessage());
                    }
                }
            }
        });
        
        
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	try {
            
                    spinBatDau.commitEdit();
                    spinKetThuc.commitEdit();
                } catch (java.text.ParseException pe) {
                    JOptionPane.showMessageDialog(null, "Giờ nhập vào không đúng định dạng (HH:mm)!");
                    return;
                }
       
                int row = tableNhanVienPhanCa.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng trong bảng 'Đã phân công' để sửa!");
                    return;
                }

      
                String phongMoi = "";
                if (cmbphong.getSelectedItem() != null) {
                    phongMoi = cmbphong.getSelectedItem().toString().trim();
                }
                
                if (phongMoi.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng làm việc!");
                    return;
                }


                java.util.Date dBD = (java.util.Date) spinBatDau.getValue();
                java.util.Date dKT = (java.util.Date) spinKetThuc.getValue();

                java.sql.Time sqlTimeBD = new java.sql.Time(dBD.getTime());
                java.sql.Time sqlTimeKT = new java.sql.Time(dKT.getTime());

          

                java.util.Calendar cal = java.util.Calendar.getInstance();

                cal.setTime(dBD);
                int startMin = cal.get(java.util.Calendar.HOUR_OF_DAY) * 60 + cal.get(java.util.Calendar.MINUTE);

                cal.setTime(dKT);
                int endMin = cal.get(java.util.Calendar.HOUR_OF_DAY) * 60 + cal.get(java.util.Calendar.MINUTE);

                // KHUNG GIỜ HOẠT ĐỘNG (06:00 - 21:00) ---
                // 06:00 = 360 phút | 21:00 = 1260 phút
                if (startMin < 360 || endMin > 1260) {
                    JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ! Phòng khám chỉ mở cửa từ 06:00 đến 21:00.");
                    return;
                }

                // ---  GIỜ NGHỈ TRƯA (11:30 - 13:30) ---
                // 11:30 = 690 phút | 13:30 = 810 phút

                if ((startMin > 690 && startMin < 810) || (endMin > 690 && endMin < 810)) {
                    JOptionPane.showMessageDialog(null, "Không thể chọn giờ bắt đầu hoặc kết thúc nằm trong giờ nghỉ trưa (11:30 - 13:30)!");
                    return;
                }


                if (endMin <= startMin) {
                    JOptionPane.showMessageDialog(null, "Lỗi: Giờ kết thúc phải sau giờ bắt đầu!");
                    return;
                }

                int idPhanCong = Integer.parseInt(tableNhanVienPhanCa.getValueAt(row, 0).toString());

                BangPhanCongCaLam pc = new BangPhanCongCaLam();
                pc.setId(idPhanCong); 
                pc.setPhong(phongMoi);
                pc.setGioLam(sqlTimeBD);
                pc.setGioKetThuc(sqlTimeKT);

                if (bpbus.suaPhanCong(pc)) {
         
                    bpbus.layGioiHanBNHomNay(currentChuyenKhoa);
                    
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                    loadDataPhanCong(currentChucVu, currentChuyenKhoa, currentThu);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowDest = tableNhanVienPhanCa.getSelectedRow();
                if (rowDest == -1) {
                    JOptionPane.showMessageDialog(null, "Chọn nhân viên cần xóa!"); return;
                }
                int idNhanVien = Integer.parseInt(tableNhanVienPhanCa.getValueAt(rowDest, 0).toString());
                String tenNhanVien = tableNhanVienPhanCa.getValueAt(rowDest, 1).toString();
                
                int confirm = JOptionPane.showConfirmDialog(null, "Xóa " + tenNhanVien + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                if (bpbus.xoaPhanCongTheoNV(idNhanVien, currentThu)) {
                
                    DefaultTableModel modelDest = (DefaultTableModel) tableNhanVienPhanCa.getModel();
                    modelDest.removeRow(rowDest);
                    if (currentChucVu != null) {
                        loadDataTablechuacocalam(currentChucVu, currentChuyenKhoa, currentThu);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi xóa!");
                }
            }
        });
        
        
    }


    
    private JLabel createLabel(String text, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(MAIN_FONT);
        lbl.setBounds(x, y, w, h);
        return lbl;
    }


    private void styleTable(JTable table) {
        table.setFont(MAIN_FONT);
        table.setRowHeight(28);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(220, 240, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setShowVerticalLines(false);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(BOLD_FONT);
        header.setBackground(Color.WHITE);
        header.setForeground(PRIMARY_COLOR);
    }
    
    class RoundedButton extends JButton {
        private Color color;
        public RoundedButton(String text, Color color) {
            super(text);
            this.color = color;
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(BOLD_FONT);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (getModel().isPressed()) g2.setColor(color.darker());
            else if (getModel().isRollover()) g2.setColor(color.brighter());
            else g2.setColor(color);
            
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2.dispose();
            super.paintComponent(g);
        }
    }


    private void loadDataPhanCong(String chucVu, String chuyenKhoa, String thu) {

        List<BangPhanCongCaLam> listPC = bpbus.getPhanCongTheoThu(chucVu, chuyenKhoa, thu);
        
     
        NhanVienBUS nvbus = new NhanVienBUS();
        List<NhanVien> listAllNV = nvbus.getAllNV();

        DefaultTableModel modelDest = (DefaultTableModel) tableNhanVienPhanCa.getModel();
        modelDest.setRowCount(0);

        for (BangPhanCongCaLam pc : listPC) {
         
            String tennv = "";
            for(NhanVien nv : listAllNV) {
                if(nv.getMaNV() == pc.getMaNhanVien()) {
                    tennv = nv.getHoTen();
                    break;
                }
            }
            
            modelDest.addRow(new Object[] { 
                pc.getId(),
                tennv, 
                pc.getPhong(), 
                pc.getGioLam(), 
                pc.getGioKetThuc() 
            });
        }
    }
    

    private void loadDataTablechuacocalam(String chucVu , String chuyenKhoa, String thu) {
        // lấy danh sách khả dụng ( dùng SQL NOT IN để loại trừ người làm hôm nay)
        List<NhanVien> listNV = bpbus.getNVKhadungTheoThu(chucVu, chuyenKhoa, thu);
        
        DefaultTableModel modelSrc = (DefaultTableModel) tableNhanVienKhaDung.getModel();
        modelSrc.setRowCount(0);
        
        for (NhanVien nv : listNV) {
            modelSrc.addRow(new Object[] { 
                nv.getMaNV(), 
                nv.getHoTen(), 
                nv.getChucVu() 
            });
        }
    }
    private void loadDataComboBoxPhong() {
        PhongChucNangBus bus = new PhongChucNangBus();
        List<PhongChucNang> listchucnang = bus.getAllPhong();
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("");
        for (PhongChucNang ck : listchucnang) {
            boxModel.addElement(ck.getTenphong());
        }
        cmbphong.setModel(boxModel);
    }

    public void setDuLieu(String chucVu, String chuyenKhoa, String thu) {
        this.currentThu = thu; 
        this.currentChucVu = (chucVu == null) ? "" : chucVu.trim();
        this.currentChuyenKhoa = (chuyenKhoa == null) ? "" : chuyenKhoa.trim();


        int maCK_ID = -1;
        int maCV_ID = -1;

  
        ChuyenKhoaBus ckbus = new ChuyenKhoaBus();
        List<ChuyenKhoa> listck = ckbus.getAllCK();
        for (ChuyenKhoa ck : listck) {
            if (ck.getTen_chuyen_khoa().equalsIgnoreCase(this.currentChuyenKhoa)) {
                maCK_ID = ck.getMa_chuyen_khoa();
                break;
            }
        }


        ChucVuBus cvBus = new ChucVuBus();
        List<ChucVu> listcv = cvBus.GetAllTenChucVu();
        for (ChucVu cv : listcv) {
            if (cv.getTenChucVu().equalsIgnoreCase(this.currentChucVu)) {
                maCV_ID = cv.getId();
                break;
            }
        }

        // --- BƯỚC 2: TỰ ĐỘNG CHỌN PHÒNG DỰA TRÊN LIÊN KẾT KHÓA NGOẠI (ID) ---
        PhongChucNangBus pBus = new PhongChucNangBus();
        List<PhongChucNang> dsPhong = pBus.getAllPhong();

        for (PhongChucNang p : dsPhong) {
            // TRƯỜNG HỢP A: Nếu là Bác sĩ/Trợ lý (Có mã chuyên khoa khớp với phòng)
            if (maCK_ID != -1 && p.getMack() == maCK_ID) {
                cmbphong.setSelectedItem(p.getTenphong());
                break;
            }
            
            // TRƯỜNG HỢP B: Nếu là nhân viên hành chính (So khớp mã Chức vụ với mã Chức vụ của phòng)
            // (Không còn dùng chuỗi "LE_TAN" hay "Lễ tân" nữa, so sánh ID == ID)
            if (maCK_ID == -1 && maCV_ID != -1 && p.getMachucvu() == maCV_ID) {
                cmbphong.setSelectedItem(p.getTenphong());
                break;
            }
        }

        // --- BƯỚC 3: LOAD DỮ LIỆU DANH SÁCH NHÂN VIÊN VÀ LỊCH TRỰC ---
        if (!java.beans.Beans.isDesignTime()) {
            loadDataTablechuacocalam(this.currentChucVu, this.currentChuyenKhoa, thu); 
            loadDataPhanCong(this.currentChucVu, this.currentChuyenKhoa, thu);
        }
    }

}