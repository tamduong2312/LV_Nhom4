package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BenhNhanBus;
import BUS.DangKyKhamBenhBus;
import BUS.ChuyenKhoaBus;

import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.DangKyKhamBenh;
import MODEL.Session;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FrmDangKyKhamBenh extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableDK;
    private JTable tableBN;
    private JTextField txtTimKiem;
    private JComboBox<String> cmbChuyenKhoa;
    private JTextField txtSoThuTu;
    private JTextArea txtGhiChu;


    private BenhNhanBus benhNhanBus = new BenhNhanBus();
    private ChuyenKhoaBus chuyenKhoaBus = new ChuyenKhoaBus();
    private DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();

    private List<ChuyenKhoa> listck = new ArrayList<>();
    private int maBenhNhanDangChon = -1;

    private final Color PRIMARY_COLOR = new Color(0, 123, 255); 
    private final Color BG_COLOR = new Color(245, 248, 250);    
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmDangKyKhamBenh frame = new FrmDangKyKhamBenh();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmDangKyKhamBenh() {
        setTitle("Hệ Thống Đăng Ký Khám Bệnh");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1280, 760); 
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // --- 1. HEADER ---
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlHeader.setBackground(PRIMARY_COLOR);
        JLabel lblTitle = new JLabel("QUẦY ĐĂNG KÝ KHÁM BỆNH");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        contentPane.add(pnlHeader, BorderLayout.NORTH);


        JPanel pnlCenter = new JPanel(new GridLayout(1, 2, 15, 0));
        pnlCenter.setOpaque(false);
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlLeft = new JPanel(new BorderLayout(0, 10));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(createTitledBorder("1. Chọn Bệnh Nhân"));

        JPanel pnlSearch = new JPanel(new GridBagLayout()); 
        pnlSearch.setBackground(Color.WHITE);
        pnlSearch.setBorder(new EmptyBorder(5, 10, 5, 10));
        
     
        JLabel lblTim = new JLabel("Tìm kiếm: ");
        lblTim.setFont(MAIN_FONT);
        

        GridBagConstraints gbcLbl = new GridBagConstraints();
        gbcLbl.gridx = 0; 
        gbcLbl.gridy = 0;
        gbcLbl.weightx = 0; 
        gbcLbl.insets = new Insets(0, 0, 0, 5);
        gbcLbl.fill = GridBagConstraints.NONE;
        pnlSearch.add(lblTim, gbcLbl);

 
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(MAIN_FONT);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Nhập tên hoặc SĐT...");
        

        GridBagConstraints gbcTxt = new GridBagConstraints();
        gbcTxt.gridx = 1; 
        gbcTxt.weightx = 1.0; 
        gbcTxt.fill = GridBagConstraints.HORIZONTAL;
        gbcTxt.insets = new Insets(0, 0, 0, 5);
        pnlSearch.add(txtTimKiem, gbcTxt);
        

        JPanel pnlBtnLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pnlBtnLeft.setBackground(Color.WHITE);
        
        JButton btnThemBN = new RoundedButton("Thêm BN", new Color(40, 167, 69)); 
        btnThemBN.setPreferredSize(new Dimension(95, 35));
        
        JButton btnTaiKham = new RoundedButton("DS Tái Khám", new Color(23, 162, 184));
        btnTaiKham.setPreferredSize(new Dimension(115, 35));

        JButton btnHenKham = new RoundedButton("Đặt Lịch Hẹn", new Color(255, 193, 7)); 
        btnHenKham.setForeground(Color.BLACK);
        btnHenKham.setPreferredSize(new Dimension(120, 35));

        pnlBtnLeft.add(btnThemBN);
        pnlBtnLeft.add(btnTaiKham);
        pnlBtnLeft.add(btnHenKham);

   
        GridBagConstraints gbcBtn = new GridBagConstraints();
        gbcBtn.gridx = 2; 
        gbcBtn.weightx = 0; // Không co giãn
        gbcBtn.fill = GridBagConstraints.NONE;
        gbcBtn.insets = new Insets(0, 0, 0, 0);
        pnlSearch.add(pnlBtnLeft, gbcBtn);
        
        pnlLeft.add(pnlSearch, BorderLayout.NORTH);

     
        tableBN = new JTable();
        setupTable(tableBN, new String[]{"Mã BN", "Họ tên", "Giới tính", "Ngày sinh", "SĐT", "CCCD"});
        JScrollPane scrollBN = new JScrollPane(tableBN);
        scrollBN.getViewport().setBackground(Color.WHITE);
        pnlLeft.add(scrollBN, BorderLayout.CENTER);

        pnlCenter.add(pnlLeft);

 
        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setBackground(Color.WHITE);
        pnlRight.setBorder(createTitledBorder("2. Thông Tin Đăng Ký Khám Ngay"));

        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBackground(Color.WHITE);


        JLabel lblChuyenKhoa = new JLabel("Chuyên khoa:");
        lblChuyenKhoa.setFont(MAIN_FONT);
        GridBagConstraints gbcLblCK = new GridBagConstraints();
        gbcLblCK.insets = new Insets(10, 10, 10, 10);
        gbcLblCK.anchor = GridBagConstraints.WEST;
        gbcLblCK.gridx = 0; gbcLblCK.gridy = 0;
        pnlForm.add(lblChuyenKhoa, gbcLblCK);

        cmbChuyenKhoa = new JComboBox<>();
        cmbChuyenKhoa.setFont(MAIN_FONT);
        cmbChuyenKhoa.setBackground(Color.WHITE);
        GridBagConstraints gbcCmb = new GridBagConstraints();
        gbcCmb.insets = new Insets(10, 10, 10, 10);
        gbcCmb.fill = GridBagConstraints.HORIZONTAL;
        gbcCmb.weightx = 1.0; gbcCmb.gridx = 1; gbcCmb.gridy = 0;
        pnlForm.add(cmbChuyenKhoa, gbcCmb);

        // Row 1: Số thứ tự
        JLabel lblSTT = new JLabel("Số thứ tự cấp:");
        lblSTT.setFont(MAIN_FONT);
        GridBagConstraints gbcLblSTT = new GridBagConstraints();
        gbcLblSTT.insets = new Insets(10, 10, 10, 10);
        gbcLblSTT.anchor = GridBagConstraints.WEST;
        gbcLblSTT.gridx = 0; gbcLblSTT.gridy = 1;
        pnlForm.add(lblSTT, gbcLblSTT);
        
        txtSoThuTu = new JTextField();
        txtSoThuTu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtSoThuTu.setForeground(Color.RED);
        txtSoThuTu.setHorizontalAlignment(JTextField.CENTER);
        txtSoThuTu.setEditable(false);
        txtSoThuTu.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbcTxtSTT = new GridBagConstraints();
        gbcTxtSTT.insets = new Insets(10, 10, 10, 10);
        gbcTxtSTT.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtSTT.weightx = 1.0; gbcTxtSTT.gridx = 1; gbcTxtSTT.gridy = 1;
        pnlForm.add(txtSoThuTu, gbcTxtSTT);

        // Row 2: Ghi chú
        JLabel lblGhiChu = new JLabel("Ghi chú / Triệu chứng:");
        lblGhiChu.setFont(MAIN_FONT);
        GridBagConstraints gbcLblGC = new GridBagConstraints();
        gbcLblGC.insets = new Insets(10, 10, 10, 10);
        gbcLblGC.anchor = GridBagConstraints.NORTHWEST;
        gbcLblGC.gridx = 0; gbcLblGC.gridy = 2;
        pnlForm.add(lblGhiChu, gbcLblGC);
        
        txtGhiChu = new JTextArea();
        txtGhiChu.setFont(MAIN_FONT);
        txtGhiChu.setLineWrap(true);
        JScrollPane spGC = new JScrollPane(txtGhiChu);
        GridBagConstraints gbcTxtGC = new GridBagConstraints();
        gbcTxtGC.insets = new Insets(10, 10, 10, 10);
        gbcTxtGC.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtGC.weightx = 1.0; gbcTxtGC.ipady = 40;
        gbcTxtGC.gridx = 1; gbcTxtGC.gridy = 2;
        pnlForm.add(spGC, gbcTxtGC);

        // Buttons Action
        JPanel pnlActions = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pnlActions.setBackground(Color.WHITE);
        JButton btnDangKy = new RoundedButton("XÁC NHẬN ĐĂNG KÝ", PRIMARY_COLOR);
        btnDangKy.setPreferredSize(new Dimension(200, 45));
        JButton btnQuayLai = new RoundedButton("Thoát", new Color(108, 117, 125));
        btnQuayLai.setPreferredSize(new Dimension(100, 45));
        pnlActions.add(btnDangKy);
        pnlActions.add(btnQuayLai);

        pnlRight.add(pnlForm, BorderLayout.CENTER);
        pnlRight.add(pnlActions, BorderLayout.SOUTH);
        pnlCenter.add(pnlRight);


        JPanel pnlBottom = new JPanel(new BorderLayout(0, 5));
        pnlBottom.setOpaque(false);
        pnlBottom.setPreferredSize(new Dimension(0, 280)); 
        
        JPanel pnlBottomHeader = new JPanel(new BorderLayout());
        pnlBottomHeader.setOpaque(false);
        JLabel lblDS = new JLabel("DANH SÁCH CHỜ KHÁM HÔM NAY");
        lblDS.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDS.setForeground(PRIMARY_COLOR);
        lblDS.setBorder(new EmptyBorder(0, 10, 0, 0));
        
        JButton btnHuy = new RoundedButton("Hủy Đăng Ký", new Color(220, 53, 69)); 
        btnHuy.setPreferredSize(new Dimension(120, 35));
        
        pnlBottomHeader.add(lblDS, BorderLayout.WEST);
        pnlBottomHeader.add(btnHuy, BorderLayout.EAST);
        
        tableDK = new JTable();
        setupTable(tableDK, new String[]{"ID", "Bệnh nhân", "Chuyên khoa", "STT", "Thời gian", "Trạng thái", "Ghi chú"});
        JScrollPane scrollDK = new JScrollPane(tableDK);
        scrollDK.getViewport().setBackground(Color.WHITE);
        pnlBottom.add(pnlBottomHeader, BorderLayout.NORTH);
        pnlBottom.add(scrollDK, BorderLayout.CENTER);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);

  

  
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                loadTableBN(txtTimKiem.getText());
            }
        });

 
        tableBN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableBN.getSelectedRow();
                if (row != -1) {
                    maBenhNhanDangChon = (int) tableBN.getValueAt(row, 0);
                }
            }
        });

        // Nút Thêm mới
        btnThemBN.addActionListener(ev -> {
            FrmQlbenhnhancuaLetan frm = new FrmQlbenhnhancuaLetan();
            frm.setVisible(true);
            loadTableBN(""); 
        });
        
        // Nút Tái khám
        btnTaiKham.addActionListener(ev -> {
            FrmTaiKham q = new FrmTaiKham(this);
            q.setVisible(true);
        });

  
        btnHenKham.addActionListener(e -> {
            if (maBenhNhanDangChon == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân từ danh sách bên trái để đặt hẹn!", "Chưa chọn bệnh nhân", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int row = tableBN.getSelectedRow();
            String tenBN = tableBN.getValueAt(row, 1).toString();
  
            FrmDangKyHenKham frmHen = new FrmDangKyHenKham(maBenhNhanDangChon, tenBN);
            frmHen.setVisible(true);
        });

        // Chọn chuyên khoa -> Tự nhảy STT
        cmbChuyenKhoa.addActionListener(e -> {
            int index = cmbChuyenKhoa.getSelectedIndex();
            if (index <= 0) {
                txtSoThuTu.setText("");
                return;
            }
            ChuyenKhoa ck = listck.get(index - 1);
            int stt = dkBus.laySoThuTuTiepTheo(ck.getMa_chuyen_khoa());
            txtSoThuTu.setText(String.valueOf(stt));
        });

        // Nút Đăng ký khám ngay
        btnDangKy.addActionListener(e -> {
            if (maBenhNhanDangChon == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int index = cmbChuyenKhoa.getSelectedIndex();
            if (index <= 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên khoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ChuyenKhoa ck = listck.get(index - 1);
            int stt = Integer.parseInt(txtSoThuTu.getText());
            DangKyKhamBenh dk = new DangKyKhamBenh(
                    maBenhNhanDangChon, Session.maNhanVien, ck.getMa_chuyen_khoa(), stt,
                    LocalDateTime.now(), DangKyKhamBenh.TRANGTHAI.CHO_KHAM, txtGhiChu.getText()
            );
            if (dkBus.themDangKy(dk) > 0) {
                JOptionPane.showMessageDialog(null, "Đăng ký thành công! STT: " + stt + "\nChuyên khoa: " + ck.getTen_chuyen_khoa());
                // Cập nhật lại STT tiếp theo
                txtSoThuTu.setText(String.valueOf(dkBus.laySoThuTuTiepTheo(ck.getMa_chuyen_khoa())));
                loadTableDangKyHomNay();
                txtGhiChu.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Nút Hủy
        btnHuy.addActionListener(e -> {
            int row = tableDK.getSelectedRow();
            if (row != -1) {
                int id = (int) tableDK.getValueAt(row, 0);
                if(JOptionPane.showConfirmDialog(null, "Hủy phiếu đăng ký này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    dkBus.capNhatTrangThaiHUY(id); 
                    loadTableDangKyHomNay();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần hủy!");
            }
        });

        btnQuayLai.addActionListener(e -> { 
            dispose(); 
        });

        // Load dữ liệu ban đầu
        loadDataComboBox();
        loadTableBN("");
        loadTableDangKyHomNay();
    }

    // =================================================================
    // CÁC HÀM HỖ TRỢ (LOGIC)
    // =================================================================

    private void loadDataComboBox() {
        listck = chuyenKhoaBus.getAllCK();
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("-- Chọn chuyên khoa --");
        for (ChuyenKhoa ck : listck) boxModel.addElement(ck.getTen_chuyen_khoa());
        cmbChuyenKhoa.setModel(boxModel);
    }

    private void loadTableDangKyHomNay() {
        List<DangKyKhamBenh> listDK = dkBus.getAllToday();
        List<BenhNhan> listBN = benhNhanBus.getAllBN();
        List<ChuyenKhoa> listCK = chuyenKhoaBus.getAllCK();
        DefaultTableModel model = (DefaultTableModel) tableDK.getModel();
        model.setRowCount(0);
        for (DangKyKhamBenh dk : listDK) {
            String tenBN = "Không rõ", tenCK = "Không rõ";
            for (BenhNhan bn : listBN) if (bn.getMaBenhNhan() == dk.getMabenhnhan()) { tenBN = bn.getHoTen(); break; }
            for (ChuyenKhoa ck : listCK) if (ck.getMa_chuyen_khoa() == dk.getMachuyenkhoa()) { tenCK = ck.getTen_chuyen_khoa(); break; }
            model.addRow(new Object[]{ dk.getId(), tenBN, tenCK, dk.getSothutu(), dk.getThoiGianHienThi(), dk.SetTrangThai(), dk.getGhichu() });
        }
    }

    private void loadTableBN(String keyword) {
        List<BenhNhan> list = (keyword == null || keyword.isEmpty()) ? benhNhanBus.getAllBN() : benhNhanBus.TimkiemBN(keyword);
        DefaultTableModel model = (DefaultTableModel) tableBN.getModel();
        model.setRowCount(0);
        for (BenhNhan bn : list) model.addRow(new Object[]{ bn.getMaBenhNhan(), bn.getHoTen(), bn.GioiTinh(), bn.getNgaySinh(), bn.getSDT(), bn.getCCCD() });
    }


    public void setThongTinDangKy(int maBenhNhan, String tenBenhNhan, String tenChuyenKhoa) {
        this.maBenhNhanDangChon = maBenhNhan;
        txtTimKiem.setText(maBenhNhan + "");
        loadTableBN(maBenhNhan + "");
        if (tableBN.getRowCount() > 0) tableBN.setRowSelectionInterval(0, 0);
        if (tenChuyenKhoa != null) cmbChuyenKhoa.setSelectedItem(tenChuyenKhoa);
        txtGhiChu.setText("Bệnh nhân tái khám theo lịch hẹn.");
    }

    private TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(new LineBorder(new Color(200, 200, 200)), title);
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 16));
        border.setTitleColor(PRIMARY_COLOR);
        return border;
    }

    private void setupTable(JTable table, String[] columns) {
        table.setModel(new DefaultTableModel(new Object[][]{}, columns));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(220, 240, 255));
        table.setSelectionForeground(Color.BLACK);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(240, 240, 240));
    }


    class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color backgroundColor;
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
            g2.setColor(getModel().isPressed() ? backgroundColor.darker() : backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}