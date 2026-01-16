package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BUS.ToaThuocBus;
import MODEL.CTToaThuoc;
import MODEL.Session;
import MODEL.ToaThuoc;

public class PnlLichSuToaThuoc extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tableMaster;
    private JTable tableDetail;
    private JButton btnLayToaCu;

    private ToaThuocBus toaBus = new ToaThuocBus();
    
    private int maBenhNhan;    
    private int maToaMoi;      
    private int maToaDangChon = -1; 


    public PnlLichSuToaThuoc(int maBenhNhan, int maToaMoi) {
        this.maBenhNhan = maBenhNhan;
        this.maToaMoi = maToaMoi; 

        setLayout(null);
        setSize(800, 600);
        setBackground(new Color(245, 248, 250));


        JLabel lblTitle1 = new JLabel("LỊCH SỬ KÊ TOA (Chọn toa cũ để sao chép vào Toa số " + maToaMoi + ")");
        lblTitle1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle1.setForeground(new Color(0, 102, 204));
        lblTitle1.setBounds(10, 10, 500, 20);
        add(lblTitle1);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 766, 180);
        add(scrollPane);

        tableMaster = new JTable();
        tableMaster.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Mã Toa", "Ngày Khám", "Ghi Chú / Chẩn Đoán" }
        ));
        styleTable(tableMaster);
        scrollPane.setViewportView(tableMaster);

        // --- TITLE 2 ---
        JLabel lblTitle2 = new JLabel("CHI TIẾT ĐƠN THUỐC");
        lblTitle2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle2.setForeground(new Color(0, 102, 204));
        lblTitle2.setBounds(10, 240, 300, 20);
        add(lblTitle2);

 
        btnLayToaCu = new JButton("SAO CHÉP TOA NÀY");
        btnLayToaCu.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLayToaCu.setBackground(new Color(40, 167, 69)); 
        btnLayToaCu.setForeground(Color.WHITE);
        btnLayToaCu.setBounds(580, 235, 196, 35);
        btnLayToaCu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLayToaCu.setContentAreaFilled(false);
        btnLayToaCu.setOpaque(true);
        btnLayToaCu.setBorderPainted(false);

        add(btnLayToaCu);

        // --- BẢNG DETAIL ---
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 280, 766, 250);
        add(scrollPane_1);

        tableDetail = new JTable();
        tableDetail.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Tên Thuốc", "Sáng", "Trưa", "Chiều", "Tối", "Số Ngày", "Cách Dùng" }
        ));
        styleTable(tableDetail);
        scrollPane_1.setViewportView(tableDetail);

        // --- LOAD DỮ LIỆU ---
        loadMasterData();
        initEvents();
    }

    private void loadMasterData() {
        DefaultTableModel model = (DefaultTableModel) tableMaster.getModel();
        model.setRowCount(0);

        List<ToaThuoc> list = toaBus.getListToaThuocByBenhNhan(this.maBenhNhan);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (ToaThuoc t : list) {
       
            if (t.getMaToaThuoc() == this.maToaMoi) continue;

            model.addRow(new Object[] {
                t.getMaToaThuoc(),
                (t.getNgayTao() != null) ? sdf.format(t.getNgayTao()) : "",
                t.getGhiChu()
            });
        }
    }

    private void loadDetailData(int maToa) {
        DefaultTableModel model = (DefaultTableModel) tableDetail.getModel();
        model.setRowCount(0);
        List<CTToaThuoc> list = toaBus.getListChiTiet(maToa);
        
        if (list == null) return;

        for (CTToaThuoc ct : list) {
            model.addRow(new Object[]{
                ct.getTenThuoc(), ct.getSang(), ct.getTrua(), ct.getChieu(), 
                ct.getToi(), ct.getSoNgay(), ct.getCachDung()
            });
        }
    }

    private void initEvents() {

        tableMaster.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMaster.getSelectedRow();
                if (row != -1) {
                    maToaDangChon = Integer.parseInt(tableMaster.getValueAt(row, 0).toString());
                    loadDetailData(maToaDangChon);
                }
            }
        });

     
        btnLayToaCu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLySaoChepToa();
            }
        });
    }


    private void xuLySaoChepToa() {
    
        if (maToaDangChon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một toa thuốc cũ ở bảng trên!");
            return;
        }
        if (this.maToaMoi <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi: Chưa xác định được toa thuốc mới để copy vào!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
                "Sao chép thuốc từ toa cũ (ID: " + maToaDangChon + ") vào toa hiện tại (ID: " + maToaMoi + ")?", 
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) return;


        List<CTToaThuoc> listThuocCu = toaBus.getListChiTiet(maToaDangChon);
        
        if (listThuocCu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Toa thuốc cũ này không có thuốc!");
            return;
        }


        int count = 0;
        for (CTToaThuoc item : listThuocCu) {
    
            item.setMaToaThuoc(this.maToaMoi);
            

            if (toaBus.themThuocVaoToa(item)) { 
                count++;
            }
        }

    
        JOptionPane.showMessageDialog(this, "Đã sao chép thành công " + count + " thuốc!");
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    private void styleTable(JTable table) {
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
    }
}