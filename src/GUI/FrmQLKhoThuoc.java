package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.KhoThuocBus;
import BUS.NhaCungCapBus;
import BUS.NhanVienBUS;
import BUS.ThuocBus;
import MODEL.KhoThuoc;
import MODEL.NhaCungCap;
import MODEL.NhanVien;
import MODEL.PhieuNhapThuoc;
import MODEL.Session; // Giả sử dùng Session.role
import MODEL.Thuoc;

public class FrmQLKhoThuoc extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel pnlCards;
    private CardLayout cardLayout;
    private JButton btnTabTonKho, btnTabNhapHang, btnDangXuat, btnTabXemCaLam;

    private JTable tblKhoThuoc;
    private DefaultTableModel modelKhoThuoc;

    private JTable tblPhieuNhap;
    private DefaultTableModel modelPhieuNhap;

    private KhoThuocBus khoThuocBus = new KhoThuocBus();
    private ThuocBus thuocBus = new ThuocBus();
    private NhanVienBUS nvBus = new NhanVienBUS();
    private NhaCungCapBus nccBus = new NhaCungCapBus();

    // Màu sắc chủ đạo
    private Color colorPrimary = new Color(0, 128, 128);
    private Color colorHover = new Color(32, 178, 170);
    private Color colorActive = new Color(255, 255, 255);
    private Color colorTextActive = new Color(0, 128, 128);
    private Color colorTextDefault = Color.WHITE;

    private final int NGUONG_SO_LUONG = 10; 
    private final long NGUONG_NGAY_HH = 30; 
    

    private boolean isAdmin = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                try { javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName()); } catch (Exception ex) {}
                
      

                FrmQLKhoThuoc frame = new FrmQLKhoThuoc("NHAP_HANG", Session.role);
                frame.setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

   
    public FrmQLKhoThuoc() {
        this("TON_KHO", "NHAN_VIEN_KHO"); 
    }
    

    public FrmQLKhoThuoc(String openTab) {
        this(openTab, "QUAN_TRI_VIEN");
    }


    public FrmQLKhoThuoc(String openTab, String role) {
     
        this.isAdmin = role != null && role.trim().equals("QUAN_TRI_VIEN");
        
        setTitle(isAdmin ? "Quản Lý Kho Thuốc (CHẾ ĐỘ XEM - QUẢN TRỊ VIÊN)" : "Quản Lý Kho Thuốc (NHÂN VIÊN KHO)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 768);
        setLocationRelativeTo(null);


        if(isAdmin) colorPrimary = new Color(0, 102, 204); 

        initUI();
        loadDataKhoThuoc();
        loadDataPhieuNhap();
        
        if ("NHAP_HANG".equals(openTab)) {
            cardLayout.show(pnlCards, "card_nhap"); 
            setButtonActive(btnTabNhapHang);        
        } else {
            cardLayout.show(pnlCards, "card_kho");  
            setButtonActive(btnTabTonKho);
        }
    }

    private void initUI() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // --- SIDEBAR ---
        JPanel pnlSidebar = new JPanel();
        pnlSidebar.setBackground(colorPrimary);
        pnlSidebar.setPreferredSize(new Dimension(260, 0));
        pnlSidebar.setLayout(new BorderLayout());
        contentPane.add(pnlSidebar, BorderLayout.WEST);

        // Logo
        JPanel pnlLogo = new JPanel();
        pnlLogo.setBackground(colorPrimary);
        pnlLogo.setPreferredSize(new Dimension(260, 140));
        pnlLogo.setLayout(new GridLayout(2, 1));
        pnlLogo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 255, 255, 50)));

        JLabel lblAppIcon = new JLabel("KHO DƯỢC");
        lblAppIcon.setFont(new Font("Arial", Font.BOLD, 28));
        lblAppIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblAppIcon.setVerticalAlignment(SwingConstants.BOTTOM);
        lblAppIcon.setForeground(Color.WHITE);

        JLabel lblSubTitle = new JLabel("HỆ THỐNG QUẢN LÝ");
        lblSubTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubTitle.setVerticalAlignment(SwingConstants.TOP);
        lblSubTitle.setForeground(new Color(200, 230, 230));

        pnlLogo.add(lblAppIcon);
        pnlLogo.add(lblSubTitle);
        pnlSidebar.add(pnlLogo, BorderLayout.NORTH);

        // Menu Buttons
        JPanel pnlMenu = new JPanel();
        pnlMenu.setBackground(colorPrimary);
        pnlMenu.setLayout(new GridLayout(10, 1, 0, 5));
        pnlMenu.setBorder(new EmptyBorder(20, 10, 10, 10));

        btnTabTonKho = createSidebarButton("DANH SÁCH TỒN KHO");
        btnTabNhapHang = createSidebarButton("QUẢN LÝ NHẬP HÀNG");
        btnTabXemCaLam = createSidebarButton("XEM CA LÀM");

        pnlMenu.add(btnTabTonKho);
        pnlMenu.add(btnTabNhapHang);
        pnlMenu.add(btnTabXemCaLam);
        pnlSidebar.add(pnlMenu, BorderLayout.CENTER);

        // Logout
        JPanel pnlLogout = new JPanel();
        pnlLogout.setBackground(colorPrimary);
        pnlLogout.setBorder(new EmptyBorder(10, 10, 20, 10));
        pnlLogout.setLayout(new BorderLayout());

    
        btnDangXuat = new RoundedButton(isAdmin ? "QUAY VỀ" : "ĐĂNG XUẤT", new Color(220, 53, 69));
        btnDangXuat.setPreferredSize(new Dimension(0, 45));
        pnlLogout.add(btnDangXuat, BorderLayout.CENTER);
        pnlSidebar.add(pnlLogout, BorderLayout.SOUTH);


        pnlCards = new JPanel();
        cardLayout = new CardLayout();
        pnlCards.setLayout(cardLayout);
        contentPane.add(pnlCards, BorderLayout.CENTER);

        pnlCards.add(createPanelKhoThuoc(), "card_kho");
        pnlCards.add(createPanelNhapHang(), "card_nhap");

        btnTabTonKho.addActionListener(e -> {
            cardLayout.show(pnlCards, "card_kho");
            setButtonActive(btnTabTonKho);
            loadDataKhoThuoc();
        });

        btnTabNhapHang.addActionListener(e -> {
            cardLayout.show(pnlCards, "card_nhap");
            setButtonActive(btnTabNhapHang);
            loadDataPhieuNhap();
        });

        btnTabXemCaLam.addActionListener(e -> {
            FrmXemCaLamDanhChoNhanVien q = new FrmXemCaLamDanhChoNhanVien();
            q.setVisible(true);
        });

        btnDangXuat.addActionListener(e -> {
            if(isAdmin) {

                FrmQuanLy ql = new FrmQuanLy(); 
                ql.setVisible(true);
                dispose();
            } else {
     
                int choice = JOptionPane.showConfirmDialog(this, "Đăng xuất khỏi hệ thống?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    FrmLogin login = new FrmLogin();
                    login.setVisible(true);
                    dispose();
                }
            }
        });

        setButtonActive(btnTabTonKho);
    }

    private JPanel createPanelKhoThuoc() {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(Color.WHITE);

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel lblTitle = new JLabel("DANH SÁCH TỒN KHO");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(colorPrimary);
        pnlHeader.add(lblTitle, BorderLayout.WEST);

        JPanel pnlActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlActions.setBackground(Color.WHITE);

  
        JButton btnKiemKe = new RoundedButton("⚠️ Kiểm Kê & Cảnh Báo", new Color(255, 193, 7));
        btnKiemKe.setForeground(Color.BLACK);
        btnKiemKe.setPreferredSize(new Dimension(180, 35));
        btnKiemKe.addActionListener(e -> xuLyKiemKeKho());

        JTextField txtSearch = new JTextField(20);
        txtSearch.setPreferredSize(new Dimension(200, 35));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(0, 10, 0, 0)));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String keyword = txtSearch.getText().trim();
                if (keyword.isEmpty()) loadDataKhoThuoc();
                else loadDataKhoThuocTimKiem(keyword);
            }
        });

        pnlActions.add(btnKiemKe);
        pnlActions.add(new JLabel("Tìm kiếm: "));
        pnlActions.add(txtSearch);

        pnlHeader.add(pnlActions, BorderLayout.EAST);
        pnl.add(pnlHeader, BorderLayout.NORTH);

        String[] columns = { "Mã Thuốc", "Tên Thuốc", "Số Lượng Tồn", "Hạn Sử Dụng", "Trạng Thái" };
        modelKhoThuoc = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblKhoThuoc = new JTable(modelKhoThuoc);
        styleTable(tblKhoThuoc);
        tblKhoThuoc.setDefaultRenderer(Object.class, new CanhBaoRenderer());

        JScrollPane sc = new JScrollPane(tblKhoThuoc);
        sc.setBorder(new EmptyBorder(0, 30, 30, 30));
        sc.getViewport().setBackground(Color.WHITE);
        pnl.add(sc, BorderLayout.CENTER);

        return pnl;
    }

    // ==========================================================================
    // LOGIC KIỂM KÊ (Dùng chung cho cả Admin và NV)
    // ==========================================================================
    private void xuLyKiemKeKho() {
        List<KhoThuoc> listKho = khoThuocBus.getAllKhoThuoc();
        List<Thuoc> listThuoc = thuocBus.getAllTHUOC();
        StringBuilder sbHetHan = new StringBuilder();
        StringBuilder sbSapHetHang = new StringBuilder();
        int countHetHan = 0;
        int countSapHet = 0;
        LocalDate today = LocalDate.now();

        for (KhoThuoc k : listKho) {
            Thuoc tInfo = null;
            for (Thuoc t : listThuoc) {
                if (k.getMaThuoc() == t.getMathuoc()) { tInfo = t; break; }
            }
            if (tInfo != null) {
                if (k.getSoLuongTon() <= NGUONG_SO_LUONG) {
                    sbSapHetHang.append("- ").append(tInfo.getTenthuoc()).append(" (Còn: ").append(k.getSoLuongTon()).append(")\n");
                    countSapHet++;
                }
                LocalDate hsd = tInfo.getHansudung();
                if (hsd != null) {
                    long days = ChronoUnit.DAYS.between(today, hsd);
                    if (days <= 0) {
                        sbHetHan.append("- [HẾT HẠN] ").append(tInfo.getTenthuoc()).append("\n");
                        countHetHan++;
                    } else if (days <= NGUONG_NGAY_HH) {
                        sbHetHan.append("- [Sắp hết hạn] ").append(tInfo.getTenthuoc()).append(" (Còn ").append(days).append(" ngày)\n");
                        countHetHan++;
                    }
                }
            }
        }

        String msg = "";
        if (countSapHet > 0) msg += "⚠️ CẢNH BÁO SẮP HẾT HÀNG (" + countSapHet + "):\n" + sbSapHetHang + "\n";
        if (countHetHan > 0) msg += "⛔ CẢNH BÁO HẠN SỬ DỤNG (" + countHetHan + "):\n" + sbHetHan;
        
        if (msg.isEmpty()) JOptionPane.showMessageDialog(this, "Kho ổn định!", "Kiểm kê", JOptionPane.INFORMATION_MESSAGE);
        else {
            JTextArea ta = new JTextArea(msg); ta.setRows(15); ta.setColumns(40); ta.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Báo cáo Kiểm kê", JOptionPane.WARNING_MESSAGE);
        }
        loadDataKhoThuoc();
    }

    private void loadDataKhoThuoc() {
        List<KhoThuoc> listKho = khoThuocBus.getAllKhoThuoc();
        List<Thuoc> listThuoc = thuocBus.getAllTHUOC();
        modelKhoThuoc.setRowCount(0);
        LocalDate today = LocalDate.now();

        for (KhoThuoc k : listKho) {
            String tenthuoc = "Không rõ";
            String hansudung = "";
            String trangthai = "Bình thường";
            for (Thuoc t : listThuoc) {
                if (k.getMaThuoc() == t.getMathuoc()) {
                    tenthuoc = t.getTenthuoc();
                    LocalDate hsd = t.getHansudung();
                    if (hsd != null) {
                        hansudung = hsd.toString();
                        long days = ChronoUnit.DAYS.between(today, hsd);
                        if (days <= 0) trangthai = "Đã hết hạn";
                        else if (days <= NGUONG_NGAY_HH) trangthai = "Sắp hết hạn";
                    }
                    break;
                }
            }
            if (k.getSoLuongTon() <= NGUONG_SO_LUONG) {
                if (trangthai.equals("Bình thường")) trangthai = "Sắp hết hàng";
                else trangthai += " & Sắp hết hàng";
            }
            modelKhoThuoc.addRow(new Object[] { k.getMaThuoc(), tenthuoc, k.getSoLuongTon(), hansudung, trangthai });
        }
        tblKhoThuoc.setModel(modelKhoThuoc);
    }

    private void loadDataKhoThuocTimKiem(String keyword) {
        List<KhoThuoc> list = khoThuocBus.timKiemKhoThuoc(keyword);
        List<Thuoc> listT = thuocBus.getAllTHUOC();
        modelKhoThuoc.setRowCount(0);
        LocalDate today = LocalDate.now();
        for (KhoThuoc k : list) {
            String tenthuoc = "Không rõ";
            String hansudung = "";
            String trangthai = "Bình thường";
            for (Thuoc t : listT) {
                if (k.getMaThuoc() == t.getMathuoc()) {
                    tenthuoc = t.getTenthuoc();
                    LocalDate hsd = t.getHansudung();
                    if (hsd != null) {
                        hansudung = hsd.toString();
                        long days = ChronoUnit.DAYS.between(today, hsd);
                        if (days <= 0) trangthai = "Đã hết hạn";
                        else if (days <= NGUONG_NGAY_HH) trangthai = "Sắp hết hạn";
                    }
                    break;
                }
            }
            if (k.getSoLuongTon() <= NGUONG_SO_LUONG) {
                if (trangthai.equals("Bình thường")) trangthai = "Sắp hết hàng";
                else trangthai += " & Sắp hết hàng";
            }
            modelKhoThuoc.addRow(new Object[] { k.getMaThuoc(), tenthuoc, k.getSoLuongTon(), hansudung, trangthai });
        }
    }

    // ==========================================================================
    // PANEL NHẬP HÀNG (PHÂN QUYỀN)
    // ==========================================================================
    private JPanel createPanelNhapHang() {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(Color.WHITE);

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel lblTitle = new JLabel("LỊCH SỬ PHIẾU NHẬP");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(colorPrimary);
        pnlHeader.add(lblTitle, BorderLayout.WEST);

        JPanel pnlActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlActions.setBackground(Color.WHITE);

        JButton btnViewDetail = new RoundedButton("Xem Chi Tiết", new Color(51, 153, 255));
        btnViewDetail.setPreferredSize(new Dimension(140, 40));
        btnViewDetail.addActionListener(e -> {
            int selectedRow = tblPhieuNhap.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập cần xem!");
                return;
            }
            try {
                int maPhieu = Integer.parseInt(tblPhieuNhap.getValueAt(selectedRow, 0).toString());
                FrmChiTietPhieuNhap dlg = new FrmChiTietPhieuNhap(maPhieu);
                dlg.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi mở chi tiết phiếu!");
            }
        });
        pnlActions.add(btnViewDetail);

        // --- PHÂN QUYỀN: CHỈ NHÂN VIÊN KHO MỚI ĐƯỢC TẠO PHIẾU ---
        if (!isAdmin) { 
            JButton btnAdd = new RoundedButton("+ Tạo Phiếu", new Color(40, 167, 69));
            btnAdd.setPreferredSize(new Dimension(140, 40));
            btnAdd.addActionListener(e -> {
                int selectedRow = tblPhieuNhap.getSelectedRow();
                int maPhieuTiepTuc = -1;
                if (selectedRow != -1) {
                    String trangThai = tblPhieuNhap.getValueAt(selectedRow, 5).toString();
                    int maPhieu = Integer.parseInt(tblPhieuNhap.getValueAt(selectedRow, 0).toString());
                    if (trangThai.equalsIgnoreCase("Đang tạo")) {
                        int confirm = JOptionPane.showConfirmDialog(this,
                                "Phiếu số " + maPhieu + " đang dang dở. Tiếp tục nhập?",
                                "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) maPhieuTiepTuc = maPhieu;
                        else if (confirm == JOptionPane.CANCEL_OPTION) return;
                    }
                }
                TaoPhieuNhap dlg = new TaoPhieuNhap(this, maPhieuTiepTuc);
                dlg.setVisible(true);
                loadDataKhoThuoc();
                loadDataPhieuNhap();
            });
            pnlActions.add(btnAdd);
        }
        // --------------------------------------------------------

        pnlHeader.add(pnlActions, BorderLayout.EAST);
        pnl.add(pnlHeader, BorderLayout.NORTH);

        String[] columns = { "Mã Phiếu", "Ngày Nhập", "Mã NV", "Mã NCC", "Tổng Tiền", "Trạng Thái" };
        modelPhieuNhap = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPhieuNhap = new JTable(modelPhieuNhap);
        styleTable(tblPhieuNhap);

        JScrollPane sc = new JScrollPane(tblPhieuNhap);
        sc.setBorder(new EmptyBorder(0, 30, 30, 30));
        sc.getViewport().setBackground(Color.WHITE);
        pnl.add(sc, BorderLayout.CENTER);

        return pnl;
    }

    private void loadDataPhieuNhap() {
        List<PhieuNhapThuoc> listPN = khoThuocBus.getAllPhieuNhapThuoc();
        List<NhanVien> listNV = nvBus.getAllNV();
        List<NhaCungCap> listNCC = nccBus.getAllNCC();
        modelPhieuNhap.setRowCount(0);
        for (PhieuNhapThuoc pn : listPN) {
            String tenNV ="";
            for (NhanVien nv : listNV) {
                if ( pn.getMaNhanVienNhap() == nv.getMaNV()) { 
                	
                	tenNV = nv.getHoTen();
        
                	
                }
            }
            String tenNCC = "Mã " + pn.getMaNhaCungCap();
            for (NhaCungCap ncc : listNCC) {
                if (ncc.getMaNhaCungCap() == pn.getMaNhaCungCap()) {
                	tenNCC = ncc.getTenNhaCungCap(); 
                	
                }
            }
            modelPhieuNhap.addRow(new Object[] { pn.getMaPhieuNhapThuoc(), pn.getNgayNhap(),tenNV , tenNCC,
                    String.format("%,.0f", pn.getTongTienNhap()), pn.getTrangThai() });
        }
        tblPhieuNhap.setModel(modelPhieuNhap);
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) g.setColor(getBackground().darker());
                else if (getModel().isRollover()) g.setColor(colorHover);
                else g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 20, 10, 10));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(colorPrimary);
        btn.setForeground(colorTextDefault);
        return btn;
    }

    private void setButtonActive(JButton activeBtn) {
        btnTabTonKho.setBackground(colorPrimary);
        btnTabTonKho.setForeground(colorTextDefault);
        btnTabNhapHang.setBackground(colorPrimary);
        btnTabNhapHang.setForeground(colorTextDefault);
        activeBtn.setBackground(colorActive);
        activeBtn.setForeground(colorTextActive);
    }

    private void styleTable(JTable table) {
        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setShowVerticalLines(false);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(245, 245, 245));
        header.setForeground(new Color(50, 50, 50));
        header.setPreferredSize(new Dimension(0, 40));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    }

    class CanhBaoRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object statusObj = table.getModel().getValueAt(row, 4);
            String status = (statusObj != null) ? statusObj.toString() : "";
            if (!isSelected) {
                if (status.toLowerCase().contains("hết hạn")) {
                    c.setBackground(new Color(255, 200, 200)); c.setForeground(Color.RED);
                } else if (status.toLowerCase().contains("sắp hết") || status.toLowerCase().contains("hết hàng")) {
                    c.setBackground(new Color(255, 255, 200)); c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.WHITE); c.setForeground(Color.BLACK);
                }
            }
            return c;
        }
    }

    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color backgroundColor;
        private int radius = 10;
        public RoundedButton(String text, Color color) {
            super(text); this.backgroundColor = color;
            setContentAreaFilled(false); setFocusPainted(false);
            setBorderPainted(false); setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 14)); setCursor(new Cursor(Cursor.HAND_CURSOR));
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