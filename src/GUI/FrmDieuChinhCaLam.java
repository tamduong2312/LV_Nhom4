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
import BUS.NhanVienBUS;
import MODEL.BangPhanCongCaLam;
import MODEL.NhanVien;

public class FrmDieuChinhCaLam extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tableNhanVienKhaDung;
    private JTable tableNhanVienPhanCa;
    private JTextField txtphong;
    
    private TimeClockChooser clockBatDau;
    private TimeClockChooser clockKetThuc;
    
    private BangPhanCongCaLamBus bpbus = new BangPhanCongCaLamBus();
    private List<NhanVien> listnhanvienchuacocalam = new ArrayList<>();
    
    private String currentThu = ""; 
    private String currentChucVu = "";
    private String currentChuyenKhoa = "";


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
        
        clockBatDau = new TimeClockChooser();
        clockBatDau.setBounds(80, 25, 100, 25);
        pnlHeader.add(clockBatDau);
        
        JLabel lblGioKT = createLabel("Giờ KT:", 200, 25, 60, 25);
        pnlHeader.add(lblGioKT);
        
        clockKetThuc = new TimeClockChooser();
        clockKetThuc.setBounds(260, 25, 100, 25);
        pnlHeader.add(clockKetThuc);
        
        JLabel lblPhong = createLabel("Phòng:", 380, 25, 60, 25);
        pnlHeader.add(lblPhong);
        
        txtphong = new JTextField();
        txtphong.setFont(MAIN_FONT);
        txtphong.setBounds(440, 25, 100, 25);
        pnlHeader.add(txtphong);


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

        // =====================================================================
        //  LOGIC XỬ LÝ
        // =====================================================================
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalTime localTimeBD = clockBatDau.getTime();
                LocalTime localTimeKT = clockKetThuc.getTime();
                
                if (localTimeBD == null || localTimeKT == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ giờ!"); return;
                }
                int rowSrc = tableNhanVienKhaDung.getSelectedRow();
                if (rowSrc == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên bên trái!"); return;
                }
                
                int idNhanVien = Integer.parseInt(tableNhanVienKhaDung.getValueAt(rowSrc, 0).toString());
                String tenNhanVien = tableNhanVienKhaDung.getValueAt(rowSrc, 1).toString();
                String phong = txtphong.getText();
                Time gioBatDau = Time.valueOf(localTimeBD);
                Time gioKetThuc = Time.valueOf(localTimeKT);

                BangPhanCongCaLam b = new BangPhanCongCaLam(idNhanVien, phong, gioBatDau, gioKetThuc, currentThu);
                if (bpbus.themPhanCong(b)) {
                    DefaultTableModel modelSrc = (DefaultTableModel) tableNhanVienKhaDung.getModel();
                    DefaultTableModel modelDest = (DefaultTableModel) tableNhanVienPhanCa.getModel();
                    modelDest.addRow(new Object[] { idNhanVien, tenNhanVien, phong, gioBatDau, gioKetThuc });
                    modelSrc.removeRow(rowSrc);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi thêm phân công!");
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
            // Tìm tên nhân viên để hiển thị
            String tennv = "";
            for(NhanVien nv : listAllNV) {
                if(nv.getMaNV() == pc.getMaNhanVien()) {
                    tennv = nv.getHoTen();
                    break;
                }
            }
            
            modelDest.addRow(new Object[] { 
                pc.getMaNhanVien(), 
                tennv, 
                pc.getPhong(), 
                pc.getGioLam(), 
                pc.getGioKetThuc() 
            });
        }
    }
    

    private void loadDataTablechuacocalam(String chucVu , String chuyenKhoa, String thu) {
        // lấy danh sách khả dụng (Đã dùng SQL NOT IN để loại trừ người làm hôm nay)
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


    public void setDuLieu(String chucVu, String chuyenKhoa, String thu) {
        this.currentThu = thu; 
        this.currentChucVu = chucVu;
        this.currentChuyenKhoa = chuyenKhoa;
        

        loadDataTablechuacocalam(chucVu, chuyenKhoa, thu); 
        loadDataPhanCong(chucVu, chuyenKhoa, thu);
    }
}