package GUI;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
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
import MODEL.Session;
import MODEL.Thuoc;

public class FrmQLKhoThuoc extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlCards;
    private CardLayout cardLayout;
    private JButton btnTabTonKho;
    private JButton btnTabNhapHang;
    private JButton btnTabXemCaLam;
    private JButton btnDangXuat;
    private JPanel pnlCardKho;
    private JPanel pnlCardNhap;
    private JTable tblKhoThuoc;
    private DefaultTableModel modelKhoThuoc;
    private JTable tblPhieuNhap;
    private DefaultTableModel modelPhieuNhap;
    private JTextField txtSearchKho;
    private JLabel lblPageTitle;
    private KhoThuocBus khoThuocBus = new KhoThuocBus();
    private ThuocBus thuocBus = new ThuocBus();
    private NhanVienBUS nvBus = new NhanVienBUS();
    private NhaCungCapBus nccBus = new NhaCungCapBus();
    private Color colorPrimary = new Color(0, 128, 128);
    private final Color BG_COLOR = new Color(240, 245, 250);
    private final Color TEXT_COLOR = new Color(60, 60, 60);
    private final int NGUONG_SO_LUONG = 10;
    private final long NGUONG_NGAY_HH = 30;
    private boolean isAdmin = false;
    private String Role = Session.role;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmQLKhoThuoc frame = new FrmQLKhoThuoc("TON_KHO", "QUAN_TRI_VIEN");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmQLKhoThuoc() {
        this("TON_KHO", "NHAN_VIEN_KHO");
    }

    public FrmQLKhoThuoc(String openTab) {
        this(openTab, "QUAN_TRI_VIEN");
    }

    public FrmQLKhoThuoc(String openTab, String role) {
        role = Role;
        JOptionPane.showMessageDialog(null, role);
        this.isAdmin = role != null && role.trim().equals("QUAN_TRI_VIEN");
        setTitle(isAdmin ? "Quản Lý Kho Thuốc (ADMIN)" : "Quản Lý Kho Thuốc (NHÂN VIÊN KHO)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 800);
        setLocationRelativeTo(null);
        if (isAdmin) {
            colorPrimary = new Color(0, 102, 204);
        }
        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JPanel pnlSidebar = new JPanel();
        pnlSidebar.setBackground(colorPrimary);
        pnlSidebar.setPreferredSize(new Dimension(260, 0));
        pnlSidebar.setLayout(null);
        contentPane.add(pnlSidebar, BorderLayout.WEST);
        JLabel lblBrandIcon = new JLabel("📦");
        lblBrandIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrandIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        lblBrandIcon.setForeground(Color.WHITE);
        lblBrandIcon.setBounds(0, 20, 260, 60);
        pnlSidebar.add(lblBrandIcon);
        JLabel lblBrandName = new JLabel("KHO DƯỢC");
        lblBrandName.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrandName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBrandName.setForeground(Color.WHITE);
        lblBrandName.setBounds(0, 80, 260, 30);
        pnlSidebar.add(lblBrandName);
        JLabel lblBrandSub = new JLabel("Hệ Thống Quản Lý");
        lblBrandSub.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrandSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblBrandSub.setForeground(new Color(220, 220, 220));
        lblBrandSub.setBounds(0, 110, 260, 20);
        pnlSidebar.add(lblBrandSub);
        btnTabTonKho = new JButton("Danh sách tồn kho");
        btnTabTonKho.setBounds(0, 160, 260, 55);
        styleSidebarButton(btnTabTonKho);
        pnlSidebar.add(btnTabTonKho);
        btnTabNhapHang = new JButton("Quản lý nhập hàng");
        btnTabNhapHang.setBounds(0, 220, 260, 55);
        styleSidebarButton(btnTabNhapHang);
        pnlSidebar.add(btnTabNhapHang);
        btnTabXemCaLam = new JButton("Xem ca làm");
        btnTabXemCaLam.setBounds(0, 280, 260, 55);
        styleSidebarButton(btnTabXemCaLam);
        pnlSidebar.add(btnTabXemCaLam);
        btnDangXuat = new JButton(isAdmin ? "Quay về" : "Đăng xuất");
        btnDangXuat.setBounds(28, 580, 200, 45);
        btnDangXuat.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDangXuat.setBackground(new Color(220, 53, 69));
        btnDangXuat.setForeground(Color.WHITE);
        btnDangXuat.setOpaque(true);
        btnDangXuat.setBorderPainted(false);
        btnDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSidebar.add(btnDangXuat);
        JPanel pnlRight = new JPanel();
        pnlRight.setBackground(BG_COLOR);
        pnlRight.setLayout(new BorderLayout());
        contentPane.add(pnlRight, BorderLayout.CENTER);
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(0, 70));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        pnlRight.add(pnlHeader, BorderLayout.NORTH);
        lblPageTitle = new JLabel("QUẢN LÝ KHO");
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPageTitle.setForeground(colorPrimary);
        pnlHeader.add(lblPageTitle);
        pnlCards = new JPanel();
        cardLayout = new CardLayout();
        pnlCards.setLayout(cardLayout);
        pnlRight.add(pnlCards, BorderLayout.CENTER);
        initCardTonKho();
        initCardNhapHang();
        initController(openTab);
        if (!java.beans.Beans.isDesignTime()) {
            loadDataKhoThuoc();
            loadDataPhieuNhap();
        }
    }

    private void styleSidebarButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorPrimary);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(0, 30, 0, 0));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 100, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(colorPrimary);
            }
        });
    }

    private void initCardTonKho() {
        pnlCardKho = new JPanel(new BorderLayout());
        pnlCardKho.setBackground(Color.WHITE);
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(Color.WHITE);
        pnlTop.setBorder(new EmptyBorder(20, 30, 10, 30));
        JLabel lblTitle = new JLabel("TRẠNG THÁI TỒN KHO THỰC TẾ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pnlTop.add(lblTitle, BorderLayout.WEST);
        JPanel pnlActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        pnlActions.setOpaque(false);
        JButton btnKiemKe = new JButton("⚠️ Kiểm Kê Kho");
        btnKiemKe.setBackground(new Color(255, 193, 7));
        btnKiemKe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKiemKe.addActionListener(e -> xuLyKiemKeKho());
        txtSearchKho = new JTextField(20);
        txtSearchKho.setPreferredSize(new Dimension(200, 30));
        pnlActions.add(btnKiemKe);
        pnlActions.add(new JLabel("Tìm thuốc:"));
        pnlActions.add(txtSearchKho);
        pnlTop.add(pnlActions, BorderLayout.EAST);
        pnlCardKho.add(pnlTop, BorderLayout.NORTH);
        modelKhoThuoc = new DefaultTableModel(
                new String[] { "Mã Thuốc", "Tên Thuốc", "Số Lượng Tồn", "Hạn Sử Dụng", "Trạng Thái" }, 0);
        tblKhoThuoc = new JTable(modelKhoThuoc);
        tblKhoThuoc.setRowHeight(35);
        tblKhoThuoc.setDefaultRenderer(Object.class, new CanhBaoRenderer());
        pnlCardKho.add(new JScrollPane(tblKhoThuoc), BorderLayout.CENTER);
        pnlCards.add(pnlCardKho, "card_kho");
    }

    private void initCardNhapHang() {
        pnlCardNhap = new JPanel(new BorderLayout());
        pnlCardNhap.setBackground(Color.WHITE);
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(Color.WHITE);
        pnlTop.setBorder(new EmptyBorder(20, 30, 10, 30));
        JLabel lblTitle = new JLabel("LỊCH SỬ NHẬP HÀNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pnlTop.add(lblTitle, BorderLayout.WEST);
        JPanel pnlActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        pnlActions.setOpaque(false);
        JButton btnViewDetail = new JButton("Xem Chi Tiết");
        btnViewDetail.setBackground(new Color(51, 153, 255));
        btnViewDetail.setForeground(Color.WHITE);
        btnViewDetail.setOpaque(true);
        btnViewDetail.setBorderPainted(false);
        JButton btnAddPhieu = new JButton("+ Tạo Phiếu Nhập");
        btnAddPhieu.setBackground(new Color(40, 167, 69));
        btnAddPhieu.setForeground(Color.WHITE);
        btnAddPhieu.setOpaque(true);
        btnAddPhieu.setBorderPainted(false);
        btnViewDetail.addActionListener(e -> {
            int row = tblPhieuNhap.getSelectedRow();
            if (row != -1) {
                new FrmChiTietPhieuNhap(Integer.parseInt(tblPhieuNhap.getValueAt(row, 0).toString())).setVisible(true);
            }
        });
        if (!isAdmin) {
            pnlActions.add(btnAddPhieu);
        }
        pnlActions.add(btnViewDetail);
        pnlTop.add(pnlActions, BorderLayout.EAST);
        pnlCardNhap.add(pnlTop, BorderLayout.NORTH);
        modelPhieuNhap = new DefaultTableModel(
                new String[] { "Mã Phiếu", "Ngày Nhập", "Mã NV", "Mã NCC", "Tổng Tiền", "Trạng Thái" }, 0);
        tblPhieuNhap = new JTable(modelPhieuNhap);
        tblPhieuNhap.setRowHeight(35);
        pnlCardNhap.add(new JScrollPane(tblPhieuNhap), BorderLayout.CENTER);
        btnAddPhieu.addActionListener(e -> {
            int maPhieuTiepTuc = -1;
            int row = tblPhieuNhap.getSelectedRow();
            if (row != -1 && tblPhieuNhap.getValueAt(row, 5).toString().equalsIgnoreCase("Đang tạo")) {
                maPhieuTiepTuc = Integer.parseInt(tblPhieuNhap.getValueAt(row, 0).toString());
            }
            new TaoPhieuNhap(this, maPhieuTiepTuc).setVisible(true);
            loadDataKhoThuoc();
            loadDataPhieuNhap();
        });
        pnlCards.add(pnlCardNhap, "card_nhap");
    }

    private void initController(String openTab) {
        btnTabTonKho.addActionListener(e -> {
            cardLayout.show(pnlCards, "card_kho");
            lblPageTitle.setText("DANH SÁCH TỒN KHO");
            loadDataKhoThuoc();
        });
        btnTabNhapHang.addActionListener(e -> {
            cardLayout.show(pnlCards, "card_nhap");
            lblPageTitle.setText("QUẢN LÝ NHẬP HÀNG");
            loadDataPhieuNhap();
        });
        btnTabXemCaLam.addActionListener(e -> new FrmXemCaLamDanhChoNhanVien().setVisible(true));
        btnDangXuat.addActionListener(e -> {
            if (isAdmin) {
                new FrmQuanLy().setVisible(true);
                dispose();
            } else {
                if (JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất?", "Xác nhận", 0) == 0) {
                    new FrmLogin().setVisible(true);
                    dispose();
                }
            }
        });
        txtSearchKho.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String k = txtSearchKho.getText().trim();
                if (k.isEmpty()) {
                    loadDataKhoThuoc();
                } else {
                    loadDataKhoThuocTimKiem(k);
                }
            }
        });
        if ("NHAP_HANG".equals(openTab)) {
            cardLayout.show(pnlCards, "card_nhap");
            lblPageTitle.setText("QUẢN LÝ NHẬP HÀNG");
        }
    }

    private void xuLyKiemKeKho() {
        if (java.beans.Beans.isDesignTime()) {
            return;
        }
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
                if (k.getMaThuoc() == t.getMathuoc()) {
                    tInfo = t;
                    break;
                }
            }
            if (tInfo != null) {
                if (k.getSoLuongTon() <= NGUONG_SO_LUONG) {
                    sbSapHetHang.append("- ").append(tInfo.getTenthuoc()).append(" (Còn: ")
                            .append(k.getSoLuongTon()).append(")\n");
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
        if (countSapHet > 0) {
            msg += "⚠️ CẢNH BÁO SẮP HẾT HÀNG (" + countSapHet + "):\n" + sbSapHetHang + "\n";
        }
        if (countHetHan > 0) {
            msg += "⛔ CẢNH BÁO HẠN SỬ DỤNG (" + countHetHan + "):\n" + sbHetHan;
        }
        if (msg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kho ổn định!");
        } else {
            JOptionPane.showMessageDialog(this, new JScrollPane(new JTextArea(msg, 15, 40)), "Báo cáo Kiểm kê",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadDataKhoThuoc() {
        if (java.beans.Beans.isDesignTime()) {
            return;
        }
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
                        if (days <= 0) {
                            trangthai = "Đã hết hạn";
                        } else if (days <= NGUONG_NGAY_HH) {
                            trangthai = "Sắp hết hạn";
                        }
                    }
                    break;
                }
            }
            if (k.getSoLuongTon() <= NGUONG_SO_LUONG) {
                if (trangthai.equals("Bình thường")) {
                    trangthai = "Sắp hết hàng";
                } else {
                    trangthai += " & Sắp hết hàng";
                }
            }
            modelKhoThuoc.addRow(new Object[] { k.getMaThuoc(), tenthuoc, k.getSoLuongTon(), hansudung, trangthai });
        }
    }

    private void loadDataKhoThuocTimKiem(String keyword) {
        if (java.beans.Beans.isDesignTime()) {
            return;
        }
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
                        if (days <= 0) {
                            trangthai = "Đã hết hạn";
                        } else if (days <= NGUONG_NGAY_HH) {
                            trangthai = "Sắp hết hạn";
                        }
                    }
                    break;
                }
            }
            if (k.getSoLuongTon() <= NGUONG_SO_LUONG) {
                if (trangthai.equals("Bình thường")) {
                    trangthai = "Sắp hết hàng";
                } else {
                    trangthai += " & Sắp hết hàng";
                }
            }
            modelKhoThuoc.addRow(new Object[] { k.getMaThuoc(), tenthuoc, k.getSoLuongTon(), hansudung, trangthai });
        }
    }

    private void loadDataPhieuNhap() {
        if (java.beans.Beans.isDesignTime()) {
            return;
        }
        List<PhieuNhapThuoc> listPN = khoThuocBus.getAllPhieuNhapThuoc();
        List<NhanVien> listNV = nvBus.getAllNV();
        List<NhaCungCap> listNCC = nccBus.getAllNCC();
        modelPhieuNhap.setRowCount(0);
        for (PhieuNhapThuoc pn : listPN) {
            String tenNV = "";
            String tenNCC = "Mã " + pn.getMaNhaCungCap();
            for (NhanVien nv : listNV) {
                if (pn.getMaNhanVienNhap() == nv.getMaNV()) {
                    tenNV = nv.getHoTen();
                    break;
                }
            }
            for (NhaCungCap ncc : listNCC) {
                if (ncc.getMaNhaCungCap() == pn.getMaNhaCungCap()) {
                    tenNCC = ncc.getTenNhaCungCap();
                    break;
                }
            }
            modelPhieuNhap.addRow(new Object[] { pn.getMaPhieuNhapThuoc(), pn.getNgayNhap(), tenNV, tenNCC,
                    String.format("%,.0f", pn.getTongTienNhap()), pn.getTrangThai() });
        }
    }

    class CanhBaoRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = table.getModel().getValueAt(row, 4).toString().toLowerCase();
            if (!isSelected) {
                if (status.contains("hết hạn")) {
                    c.setBackground(new Color(255, 200, 200));
                    c.setForeground(Color.RED);
                } else if (status.contains("sắp hết")) {
                    c.setBackground(new Color(255, 255, 200));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            }
            return c;
        }
    }

    class RoundedButton extends JButton {
        private Color bg;

        public RoundedButton(String t, Color c) {
            super(t);
            this.bg = c;
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? bg.darker() : bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}