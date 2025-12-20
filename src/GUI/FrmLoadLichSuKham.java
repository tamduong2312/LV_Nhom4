package GUI;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.BenhNhanBus;

import javax.swing.JButton;


import DAO.PhieuKhamDao;
import MODEL.PhieuKham;
import MODEL.Session;

public class FrmLoadLichSuKham extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    
    private int maBenhNhanCanXem= 0;
    

    private PhieuKhamDao phieuKhamDao = new PhieuKhamDao();
    
    private static String tenbn;

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

    /**
     * Create the frame.
     */

    public FrmLoadLichSuKham(int maBenhNhanCanXem) {
   
    	
    	   this.maBenhNhanCanXem = maBenhNhanCanXem;
           
           BenhNhanBus bnBus = new BenhNhanBus();
           this.tenbn = bnBus.getTenBenhNhanById(maBenhNhanCanXem); 
        setTitle("LỊCH SỬ KHÁM BỆNH - BN: " + tenbn);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(100, 100, 835, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 159, 769, 244);
        contentPane.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Mã Phiếu", "Mã BN", "Mã BS", "Mã CK", "Ngày Khám", "Trạng Thái"
            }
        ));
        scrollPane.setViewportView(table);
        
        JButton btnXemChiTiet = new JButton("XEM CHI TIẾT");
        btnXemChiTiet.setBounds(669, 127, 120, 23);
        contentPane.add(btnXemChiTiet);
        
  
        loadDataToTable(maBenhNhanCanXem);
        
  
        btnXemChiTiet.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int maPhieu = Integer.parseInt(table.getValueAt(row, 0).toString());
                String mabn = table.getValueAt(row, 1).toString();
         
           FrmLoadDuLieuKham q = new  FrmLoadDuLieuKham( maPhieu,mabn);
           q.setVisible(true);
            }
        });
    }
    




    private void loadDataToTable(int maBenhNhan) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        

        model.setRowCount(0);
        

        List<PhieuKham> list = phieuKhamDao.getLichSuKhamByMaBenhNhan(maBenhNhan);
        

        for (PhieuKham pk : list) {
            model.addRow(new Object[] {
                pk.getMaPhieuKham(),
                tenbn,
                Session.TenNhanVien,
                pk.getMaChuyenKhoa(),
                pk.getNgayKham(),
                pk.getTrangThai()
            });
        }
    }
}