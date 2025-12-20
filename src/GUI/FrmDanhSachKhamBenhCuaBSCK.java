package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BenhNhanBus;
import BUS.ChuyenKhoaBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.DangKyKhamBenh;
import MODEL.Session;

public class FrmDanhSachKhamBenhCuaBSCK extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    
    private BenhNhanBus benhNhanBus = new BenhNhanBus();
    private ChuyenKhoaBus chuyenKhoaBus = new ChuyenKhoaBus();
    private DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
    private PhieuKhamBus pkbus = new PhieuKhamBus();
    
    private final Color PRIMARY_COLOR = new Color(0, 123, 255); 
    private final Color BG_COLOR = new Color(245, 248, 250);    
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmDanhSachKhamBenhCuaBSCK frame = new FrmDanhSachKhamBenhCuaBSCK();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmDanhSachKhamBenhCuaBSCK() {
        setTitle("Danh Sách Chờ Khám - Bác Sĩ chuyên khoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 650);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        pnlHeader.setBackground(PRIMARY_COLOR);
        pnlHeader.setPreferredSize(new Dimension(0, 60));
        
        JLabel lblTitle = new JLabel("DANH SÁCH CHỜ KHÁM TỔNG QUÁT");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        table = new JTable();
        setupTable(table, new String[]{"ID", "Mã BN", "Bệnh nhân", "Mã CK", "Chuyên khoa", "STT", "Thời gian", "Trạng thái", "Ghi chú"});
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
        pnlCenter.add(scrollPane, BorderLayout.CENTER);
        
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(new LineBorder(new Color(230, 230, 230), 1));

 
        JButton btnBack = new RoundedButton("Quay lại", new Color(108, 117, 125));
        btnBack.setPreferredSize(new Dimension(120, 40));

        JButton btnRefresh = new RoundedButton("Làm mới", new Color(23, 162, 184)); 
        btnRefresh.setPreferredSize(new Dimension(120, 40));
        
        JButton btnKham = new RoundedButton("TIẾN HÀNH KHÁM", new Color(40, 167, 69)); 
        btnKham.setPreferredSize(new Dimension(180, 40));

        pnlBottom.add(btnBack);
        pnlBottom.add(btnRefresh);
        pnlBottom.add(btnKham);
        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        // EVENTS
        btnKham.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân để khám!");
                return;
            }
            String tenbn = table.getValueAt(index, 2).toString();
            Session.mabenhnhan = Integer.parseInt(table.getValueAt(index, 1).toString());
            Session.chuyenKhoa = table.getValueAt(index, 3).toString();
            
            JOptionPane.showMessageDialog(null,  Session.chuyenKhoa);
            PhieuKhamBus bus = new PhieuKhamBus();
            Session.maphieukham = bus.getMaPhieuKhamByBenhNhan(Session.mabenhnhan);
            
            JOptionPane.showMessageDialog(null,  Session.maphieukham);
            
         
            
            
            bus.updateTrangThaiBSKHAMBENH(Session.maphieukham);
            
            FrmKhamBenhCKDanhChoBSCK q = new FrmKhamBenhCKDanhChoBSCK(tenbn);
            q.setVisible(true);
            dispose();
        });

        btnRefresh.addActionListener(e -> loadTableDangKyHomNay());

        btnBack.addActionListener(e -> {
        	FrmLamViecBacSi main = new FrmLamViecBacSi();
            main.setVisible(true);
            dispose();
        });

        loadTableDangKyHomNay();
    }

    private void loadTableDangKyHomNay() {
      
        List<DangKyKhamBenh> listDK = dkBus.getAllTodayByBSChuyenKhoa(Session.machuyenkhoa);
        
        List<BenhNhan> listBN = benhNhanBus.getAllBN();
        List<ChuyenKhoa> listCK = chuyenKhoaBus.getAllCK();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (DangKyKhamBenh dk : listDK) {
            String tenBN = "Không rõ";
            String tenCK = "Không rõ";
            String mabn = "không rõ";
            String mack = "";


            String rawStatus = dk.getTrangThaiHienThi(); 
            String hienThiTrangThai = "Chưa xác định";


            if (rawStatus != null) {
                switch (rawStatus) {
                    case "CHO_KHAM_BS":
                        hienThiTrangThai = "Chờ bác sĩ";
                        break;
                    case "BS_DANG_KHAM":
                        hienThiTrangThai = "bác sĩ đang khám";
                        break;
                    case "DANG_KHAM":
                        hienThiTrangThai = "Đang khám";
                        break;
                    case "DOI_KET_QUA_CLS":
                        hienThiTrangThai = "đợi kết quả Cận lâm sàng";
                        break;
                    case "DA_CO_KET_QUA_CLS": 
                        hienThiTrangThai = "Đã có KQ  - Chờ kết luận";
                        break;
                        
                    case "TIEP_NHAN_KHAM_CHUYEN_KHOA": 
                        hienThiTrangThai = "đã tiếp nhận khám chuyên khoa ";
                        break;
                   case "DA_KHAM":
                        hienThiTrangThai = "Hoàn tất";
                        break;
                    case "HUY_KHAM":
                        hienThiTrangThai = "Đã hủy";
                        break;
                    default:
                        hienThiTrangThai = rawStatus; 
                        break;
                }
            }
    

   
            for (BenhNhan bn : listBN) {
                if (bn.getMaBenhNhan() == dk.getMabenhnhan()) {
                    tenBN = bn.getHoTen();
                    mabn = String.valueOf(bn.getMaBenhNhan());
                    break;
                }
            }

  
            for (ChuyenKhoa ck : listCK) {
                if (ck.getMa_chuyen_khoa() == dk.getMachuyenkhoa()) {
                    tenCK = ck.getTen_chuyen_khoa();
                    mack = String.valueOf(ck.getMa_chuyen_khoa());
                    break;
                }
            }

            model.addRow(new Object[]{
                dk.getId(),
                mabn,
                tenBN,
                mack,
                tenCK,
                dk.getSothutu(),
                dk.getThoiGianHienThi(),
                hienThiTrangThai,
                dk.getGhichu()
            });
        }
    }

    private void setupTable(JTable table, String[] columns) {
        table.setModel(new DefaultTableModel(new Object[][]{}, columns));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(220, 240, 255));
        table.setSelectionForeground(Color.BLACK);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(240, 240, 240));
        header.setForeground(Color.BLACK);
    }

    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color backgroundColor;
        private int radius = 15;

        public RoundedButton(String text, Color color) {
            super(text);
            this.backgroundColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (getModel().isPressed()) g2.setColor(backgroundColor.darker());
            else g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}