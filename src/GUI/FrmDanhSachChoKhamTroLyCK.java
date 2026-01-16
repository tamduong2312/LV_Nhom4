package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
import BUS.DichVuBus; // Thêm Bus dịch vụ
import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.DangKyKhamBenh;
import MODEL.DichVu; // Thêm Model dịch vụ
import MODEL.Session;

public class FrmDanhSachChoKhamTroLyCK extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;          
    private JTable tableVangMat;  
    
    private BenhNhanBus benhNhanBus = new BenhNhanBus();
    private ChuyenKhoaBus chuyenKhoaBus = new ChuyenKhoaBus();
    private DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
    private PhieuKhamBus pkbus = new PhieuKhamBus();
    private DichVuBus dvBus = new DichVuBus(); 
    
	private JPanel pnlBottom;
	private JButton btnBack;
	private JButton btnRefresh;
	private JButton btnVangMat;
	private JButton btnDangKySTTMoi;
	private JButton btnKham;
    
    private final Color PRIMARY_COLOR = new Color(0, 123, 255); 
    private final Color BG_COLOR = new Color(245, 248, 250);
    private final Color DANGER_COLOR = new Color(220, 53, 69);
    private List<DangKyKhamBenh> listDK = new ArrayList<>(); 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                FrmDanhSachChoKhamTroLyCK frame = new FrmDanhSachChoKhamTroLyCK(); 
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmDanhSachChoKhamTroLyCK() {
        setTitle("Danh Sách Chờ Khám - Trợ lý chuyên khoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 750); 
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        pnlHeader.setBackground(PRIMARY_COLOR);
        pnlHeader.setPreferredSize(new Dimension(0, 60));
        
        JLabel lblTitle = new JLabel("DANH SÁCH CHỜ KHÁM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        contentPane.add(pnlHeader, BorderLayout.NORTH);


        JPanel pnlCenter = new JPanel(new GridLayout(2, 1, 0, 10)); 
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        String[] columns = {"ID", "Mã BN", "Bệnh nhân", "Mã CK", "Chuyên khoa", "Dịch vụ", "STT", "Thời gian", "Trạng thái", "Ghi chú"};

    
        table = new JTable();
        setupTable(table, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
     
        TitledBorder borderCho = BorderFactory.createTitledBorder(
                new LineBorder(PRIMARY_COLOR), "DANH SÁCH ĐANG CHỜ (Gọi tên)", 
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14), PRIMARY_COLOR);
        scrollPane.setBorder(borderCho);
        pnlCenter.add(scrollPane);

 
        tableVangMat = new JTable();
        setupTable(tableVangMat, columns);
        JScrollPane scrollPaneVang = new JScrollPane(tableVangMat);
        scrollPaneVang.getViewport().setBackground(Color.WHITE);

        TitledBorder borderVang = BorderFactory.createTitledBorder(
                new LineBorder(DANGER_COLOR), "DANH SÁCH VẮNG MẶT", 
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14), DANGER_COLOR);
        scrollPaneVang.setBorder(borderVang);
        pnlCenter.add(scrollPaneVang);
        
        contentPane.add(pnlCenter, BorderLayout.CENTER);


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

        // --- EVENTS ---
        tableVangMat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
            }
        });
        btnKham.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1) { JOptionPane.showMessageDialog(null, "Chọn bệnh nhân!"); return; }


            int idDK = (int) table.getValueAt(index, 0); 
		    int mack = Integer.parseInt(table.getValueAt(index, 3).toString()); 
            int maBN = Integer.parseInt(table.getValueAt(index, 1).toString());
            String tendv = table.getValueAt(index, 5).toString();
            Session.maDangKyHienTai = (int) table.getValueAt(index, 0);
            PhieuKhamBus pkbus = new PhieuKhamBus();

            int maPKCuoiCung = dkBus.getMaPhieuKhamByIdDangKy(idDK);
            
            


            if (maPKCuoiCung <= 0) {
     
                maPKCuoiCung = pkbus.TroLyTQTaoPhieuKham(maBN, Session.maNhanVien, mack);

                pkbus.lienKetPhieuKhamVaoDangKy(idDK, maPKCuoiCung);
            }
            // ------------------------------------------------

            if (maPKCuoiCung > 0) {
                Session.maphieukham = maPKCuoiCung; 
                Session.mabenhnhan = maBN;
                new DangKyKhamBenhBus().capNhatTrangThai(idDK);

      
                JDialog dialog = new JDialog();
                dialog.setTitle("Nhập Chỉ Số Sinh Tồn");
                dialog.setSize(920, 650);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setContentPane(new FrmNhapChiSoSinhTon1(maPKCuoiCung, maBN, tendv));
                dialog.setVisible(true);
                
                dispose();
            }
        });
        btnRefresh.addActionListener(e -> {
            loadTableDangKyHomNay();
            loadTableVangMatToDay();
        });


        btnBack.addActionListener(e -> {
            FrmTroLy main = new FrmTroLy();
            main.setVisible(true);
            dispose();
        });
        
   
        btnVangMat.addActionListener(e -> {
            int index = table.getSelectedRow(); 
            if(index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân trong danh sách chờ!");
                return;
            }
            int id = (int) table.getValueAt(index, 0);
            DangKyKhamBenhBus u = new DangKyKhamBenhBus();
            u.capNhatTrangThaiVangMat(id);
            loadTableVangMatToDay();
            loadTableDangKyHomNay();
        });

        btnDangKySTTMoi.addActionListener(e -> {
            int index = tableVangMat.getSelectedRow(); 
            if(index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân trong danh sách vắng mặt!");
                return;
            }
            int id = (int) tableVangMat.getValueAt(index, 0); 
            if (dkBus.DangKySTTmoidobenhnhanquaylaikham(id)) {
                JOptionPane.showMessageDialog(null, "Đăng ký STT mới thành công!");
                loadTableVangMatToDay();
                loadTableDangKyHomNay();
            } else {
                JOptionPane.showMessageDialog(null, "Thất bại! Vui lòng kiểm tra lại hệ thống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        loadTableDangKyHomNay();
        loadTableVangMatToDay();
        
        if (!java.beans.Beans.isDesignTime()) {
        	loadTableVangMatToDay();
        	loadTableDangKyHomNay();

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
    private void loadTableDangKyHomNay() {

        List<DangKyKhamBenh> listDK = dkBus.getAlltodayTroLyCK(Session.machuyenkhoa);
        List<BenhNhan> listBN = benhNhanBus.getAllBN();
        List<ChuyenKhoa> listCK = chuyenKhoaBus.getAllCK();
        List<DichVu> listDV = dvBus.getDichVu(); 

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (DangKyKhamBenh dk : listDK) {
            String tenBN = "Không rõ";
            String tenCK = "Không rõ";
            String tenDV = "Không rõ";
            String mabn = "không rõ";
            String mack = "";

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

            model.addRow(new Object[]{
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
        List<DangKyKhamBenh> listDK = dkBus.getAllVangMatCKToDay(Session.chuyenKhoa);
        List<BenhNhan> listBN = benhNhanBus.getAllBN();
        List<ChuyenKhoa> listCK = chuyenKhoaBus.getAllCK();
        List<DichVu> listDV = dvBus.getDichVu();

        DefaultTableModel model = (DefaultTableModel) tableVangMat.getModel(); 
        model.setRowCount(0);

        for (DangKyKhamBenh dk : listDK) {
            String tenBN = "Không rõ";
            String tenCK = "Không rõ";
            String tenDV = "Không rõ";
            String mabn = "không rõ";
            String mack = "";

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

            model.addRow(new Object[]{
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
      
        table.setModel(new DefaultTableModel(new Object[][]{}, columns) {
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