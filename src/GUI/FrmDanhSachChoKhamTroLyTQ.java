package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BenhNhanBus;
import BUS.ChuyenKhoaBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import BUS.DichVuBus;
import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.DangKyKhamBenh;
import MODEL.DichVu;
import MODEL.Session;

public class FrmDanhSachChoKhamTroLyTQ extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTable tableVangMat;

    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JPanel pnlCenter;
    private JScrollPane scrollPane;
    private JScrollPane scrollPaneVang;
    private JPanel pnlBottom;
    private JButton btnBack;
    private JButton btnRefresh;
    private JButton btnVangMat;
    private JButton btnDangKySTTMoi;
    private JButton btnKham;

    private BenhNhanBus benhNhanBus = new BenhNhanBus();
    private ChuyenKhoaBus chuyenKhoaBus = new ChuyenKhoaBus();
    private DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
    private PhieuKhamBus pkbus = new PhieuKhamBus();
    private DichVuBus dvBus = new DichVuBus();

    private final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private final Color BG_COLOR = new Color(245, 248, 250);
    private final Color DANGER_COLOR = new Color(220, 53, 69);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmDanhSachChoKhamTroLyTQ frame = new FrmDanhSachChoKhamTroLyTQ();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmDanhSachChoKhamTroLyTQ() {
        setTitle("Danh Sách Chờ Khám - Trợ lý Tổng Quát");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 750);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        pnlHeader = new JPanel();
        pnlHeader.setBackground(PRIMARY_COLOR);
        pnlHeader.setPreferredSize(new Dimension(0, 60));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        lblTitle = new JLabel("DANH SÁCH CHỜ KHÁM TỔNG QUÁT");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);

        pnlCenter = new JPanel();
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlCenter.setLayout(new GridLayout(2, 1, 0, 10));
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        String[] columns = { 
            "ID", "Mã BN", "Bệnh nhân", "Mã CK", "Chuyên khoa", 
            "Dịch vụ", "STT", "Thời gian", "Trạng thái", "Ghi chú" 
        };

        table = new JTable();
        setupTable(table, columns);
        scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(PRIMARY_COLOR), 
            "DANH SÁCH ĐANG CHỜ (Gọi tên)", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), 
            PRIMARY_COLOR
        ));
        pnlCenter.add(scrollPane);

        tableVangMat = new JTable();
        setupTable(tableVangMat, columns);
        scrollPaneVang = new JScrollPane(tableVangMat);
        scrollPaneVang.getViewport().setBackground(Color.WHITE);
        scrollPaneVang.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(DANGER_COLOR), 
            "DANH SÁCH VẮNG MẶT", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), 
            DANGER_COLOR
        ));
        pnlCenter.add(scrollPaneVang);

        pnlBottom = new JPanel();
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(new LineBorder(new Color(230, 230, 230), 1));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        btnBack = new JButton("Quay lại");
        styleButton(btnBack, new Color(108, 117, 125));
        btnBack.setPreferredSize(new Dimension(120, 40));
        pnlBottom.add(btnBack);

        btnRefresh = new JButton("Làm mới");
        styleButton(btnRefresh, new Color(23, 162, 184));
        btnRefresh.setPreferredSize(new Dimension(120, 40));
        pnlBottom.add(btnRefresh);

        btnVangMat = new JButton("Đánh dấu vắng mặt");
        styleButton(btnVangMat, DANGER_COLOR);
        btnVangMat.setPreferredSize(new Dimension(180, 40));
        pnlBottom.add(btnVangMat);

        btnDangKySTTMoi = new JButton("Đăng ký STT Mới");
        styleButton(btnDangKySTTMoi, new Color(255, 215, 0));
        btnDangKySTTMoi.setForeground(Color.BLACK);
        btnDangKySTTMoi.setPreferredSize(new Dimension(180, 40));
        pnlBottom.add(btnDangKySTTMoi);

        btnKham = new JButton("TIẾN HÀNH KHÁM");
        styleButton(btnKham, new Color(40, 167, 69));
        btnKham.setPreferredSize(new Dimension(180, 40));
        pnlBottom.add(btnKham);

        initEvents();

        if (!java.beans.Beans.isDesignTime()) {
            loadTableDangKyHomNay();
            loadTableVangMatToDay();
        }
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
    }

    private void initEvents() {
        btnKham.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1) { 
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân!"); 
                return; 
            }

            int idDK = (int) table.getValueAt(index, 0); 
            int maBN = Integer.parseInt(table.getValueAt(index, 1).toString());
            String tendv = table.getValueAt(index, 2).toString();
            int mack = Integer.parseInt(table.getValueAt(index, 3).toString()); 
            
            Session.maDangKyHienTai = idDK; 
            
            PhieuKhamBus bus = new PhieuKhamBus();
            int maPK = dkBus.getMaPhieuKhamByIdDangKy(idDK); 

            if (maPK <= 0) {
                maPK = bus.getMaPhieuKhamTrongNgay(maBN,mack);
                
                if (maPK <= 0) {
                    maPK = bus.TroLyTQTaoPhieuKham(maBN, Session.maNhanVien, mack);
                }
                
                if (maPK > 0) {
                    bus.lienKetPhieuKhamVaoDangKy(idDK, maPK);
                }
            }

            if (maPK > 0) {
                Session.maphieukham = maPK;
                Session.mabenhnhan = maBN;
                new DangKyKhamBenhBus().capNhatTrangThai(idDK); 

                JDialog dialog = new JDialog();
                dialog.setTitle("Nhập Chỉ Số Sinh Tồn");
                dialog.setSize(920, 650);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setContentPane(new FrmNhapChiSoSinhTon1(maPK, maBN, tendv));
                dialog.setVisible(true);
                dispose();
            }
        });

        btnRefresh.addActionListener(e -> {
            loadTableDangKyHomNay();
            loadTableVangMatToDay();
        });

        btnBack.addActionListener(e -> {
            new FrmTroLy().setVisible(true);
            dispose();
        });

        btnVangMat.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân trong danh sách chờ!");
                return;
            }
            int id = (int) table.getValueAt(index, 0);
            new DangKyKhamBenhBus().capNhatTrangThaiVangMat(id);
            loadTableVangMatToDay();
            loadTableDangKyHomNay();
        });

        btnDangKySTTMoi.addActionListener(e -> {
            int index = tableVangMat.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân trong danh sách vắng mặt!");
                return;
            }
            int id = (int) tableVangMat.getValueAt(index, 0);
            if (dkBus.DangKySTTmoidobenhnhanquaylaikham(id)) {
                JOptionPane.showMessageDialog(null, "Đăng ký STT mới thành công!");
                loadTableVangMatToDay();
                loadTableDangKyHomNay();
            }
        });
        
        if (!java.beans.Beans.isDesignTime()) {
        	loadTableVangMatToDay();
        	loadTableDangKyHomNay();

        }
    }

    private void loadTableDangKyHomNay() {
        List<DangKyKhamBenh> listDK = dkBus.getAlltodayTroLyCK(Session.machuyenkhoa);
        List<BenhNhan> listBN = benhNhanBus.getAllBN();
        List<ChuyenKhoa> listCK = chuyenKhoaBus.getAllCK();
        List<DichVu> listDV = dvBus.getDichVu();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (DangKyKhamBenh dk : listDK) {
            String tenBN = "Không rõ", tenCK = "Không rõ", tenDV = "Không rõ", mabn = "", mack = "";
            
            for (BenhNhan bn : listBN) {
                if (bn.getMaBenhNhan() == dk.getMabenhnhan()) { 
                    tenBN = bn.getHoTen();
                    mabn = bn.getMaBenhNhan() + "";
                    break; 
                }
            }
            
            for (ChuyenKhoa ck : listCK) {
                if (ck.getMa_chuyen_khoa() == dk.getMachuyenkhoa()) {
                    tenCK = ck.getTen_chuyen_khoa();
                    mack = ck.getMa_chuyen_khoa() + ""; 
                    break;
                }
            }
            
            for (DichVu dv : listDV) {
                if (dv.getMachuyenkhoa() == dk.getMachuyenkhoa()) {
                    tenDV = dv.getTenDichVu(); 
                    break; 
                }
            }
            
            model.addRow(new Object[] {
                dk.getId(),
                mabn, 
                tenBN, 
                mack, 
                tenCK,
                dk.getTenDichVu() != null ? dk.getTenDichVu() : "Khám chuyên khoa", 
                dk.getSothutu(), 
                dk.getThoiGianHienThi(),
                dk.getTrangThaiTiengViet(), 
                dk.getGhichu() 
            });
        }
    }

    private void loadTableVangMatToDay() {
        List<DangKyKhamBenh> listDK = dkBus.getAllVangMatToDay();
        List<BenhNhan> listBN = benhNhanBus.getAllBN();
        List<ChuyenKhoa> listCK = chuyenKhoaBus.getAllCK();
        List<DichVu> listDV = dvBus.getDichVu();

        DefaultTableModel model = (DefaultTableModel) tableVangMat.getModel();
        model.setRowCount(0);

        for (DangKyKhamBenh dk : listDK) {
            String tenBN = "Không rõ", tenCK = "Không rõ", tenDV = "Không rõ", mabn = "", mack = "";
            
            for (BenhNhan bn : listBN) {
                if (bn.getMaBenhNhan() == dk.getMabenhnhan()) { 
                    tenBN = bn.getHoTen(); 
                    mabn = bn.getMaBenhNhan() + ""; 
                    break; 
                }
            }
            
            for (ChuyenKhoa ck : listCK) {
                if (ck.getMa_chuyen_khoa() == dk.getMachuyenkhoa()) { 
                    tenCK = ck.getTen_chuyen_khoa(); 
                    mack = ck.getMa_chuyen_khoa() + ""; 
                    break; 
                }
            }
            
            for (DichVu dv : listDV) {
                if (dv.getMachuyenkhoa() == dk.getMachuyenkhoa()) { 
                    tenDV = dv.getTenDichVu(); 
                    break; 
                }
            }
            
            model.addRow(new Object[] { 
                dk.getId(), 
                mabn, 
                tenBN, 
                mack, 
                tenCK, 
                tenDV, 
                dk.getSothutu(), 
                dk.getThoiGianHienThi(), 
                "Vắng mặt", 
                dk.getGhichu() 
            });
        }
    }

    private void setupTable(JTable table, String[] columns) {
        table.setModel(new DefaultTableModel(new Object[][] {}, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
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
}