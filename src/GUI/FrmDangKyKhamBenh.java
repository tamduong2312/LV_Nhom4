package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
import GUI.FrmQlbenhnhancuaLetan;

public class FrmDangKyKhamBenh extends JFrame {
	private JTable tableDK;
    private JPanel contentPane;
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

    // ✅ Load chuyên khoa vào ComboBox (dạng tên)
    private void loadDataComboBox() {
        listck = chuyenKhoaBus.getAllCK();
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();

        boxModel.addElement("Chọn chuyên khoa");

        for (ChuyenKhoa ck : listck) {
            boxModel.addElement(ck.getTen_chuyen_khoa());
        }

        cmbChuyenKhoa.setModel(boxModel);
    }

    public FrmDangKyKhamBenh() {
        setTitle("ĐĂNG KÝ KHÁM BỆNH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("ĐĂNG KÝ KHÁM BỆNH", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(10, 10, 860, 40);
        contentPane.add(lblTitle);

        JLabel lblTim = new JLabel("Tìm bệnh nhân:");
        lblTim.setBounds(20, 60, 120, 25);
        contentPane.add(lblTim);

        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(140, 60, 250, 25);
        contentPane.add(txtTimKiem);

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                loadTableBN(txtTimKiem.getText());
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 100, 840, 250);
        contentPane.add(scrollPane);

        tableBN = new JTable();
        tableBN.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã BN", "Họ tên", "Giới tính", "Ngày sinh", "SĐT", "CCCD"}
        ));

        tableBN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableBN.getSelectedRow();
                if (row != -1) {
                    maBenhNhanDangChon = (int) tableBN.getValueAt(row, 0);
                }
            }
        });

        scrollPane.setViewportView(tableBN);

        JButton btnThemBN = new JButton("THÊM BỆNH NHÂN MỚI");
        btnThemBN.setBounds(410, 60, 191, 25);
        contentPane.add(btnThemBN);

        btnThemBN.addActionListener(ev -> {
        	FrmQlbenhnhancuaLetan frm = new FrmQlbenhnhancuaLetan();
            frm.setVisible(true);
            loadTableBN("");
        });

        JLabel lblCK = new JLabel("Chuyên khoa:");
        lblCK.setBounds(20, 370, 100, 25);
        contentPane.add(lblCK);

        cmbChuyenKhoa = new JComboBox<>();
        cmbChuyenKhoa.setBounds(120, 370, 200, 25);
        contentPane.add(cmbChuyenKhoa);

        // ✅ Khi chọn chuyên khoa → load số thứ tự
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

        JLabel lblSTT = new JLabel("Số thứ tự:");
        lblSTT.setBounds(350, 370, 100, 25);
        contentPane.add(lblSTT);

        txtSoThuTu = new JTextField();
        txtSoThuTu.setEditable(false);
        txtSoThuTu.setBackground(new Color(235, 235, 235));
        txtSoThuTu.setBounds(430, 370, 100, 25);
        contentPane.add(txtSoThuTu);

        JLabel lblGC = new JLabel("Ghi chú:");
        lblGC.setBounds(20, 410, 100, 25);
        contentPane.add(lblGC);

        txtGhiChu = new JTextArea();
        JScrollPane spGC = new JScrollPane(txtGhiChu);
        spGC.setBounds(120, 410, 410, 80);
        contentPane.add(spGC);
        
        JLabel lblDS = new JLabel("Danh sách đăng ký khám bệnh hôm nay:");
        lblDS.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDS.setBounds(20, 500, 350, 25);
        contentPane.add(lblDS);

        JScrollPane scrollDK = new JScrollPane();
        scrollDK.setBounds(20, 530, 840, 220);
        contentPane.add(scrollDK);

        tableDK = new JTable();
        tableDK.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Bệnh nhân", "Chuyên khoa", "STT", "Thời gian", "Trạng thái", "Ghi chú"}
        ));
        scrollDK.setViewportView(tableDK);

        JButton btnDangKy = new JButton("ĐĂNG KÝ KHÁM BỆNH");
        btnDangKy.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDangKy.setBackground(new Color(0, 123, 255));
        btnDangKy.setForeground(Color.WHITE);
        btnDangKy.setBounds(600, 370, 250, 50);
        contentPane.add(btnDangKy);
        
        JButton btnQuayTrLi = new JButton("QUAY TRỞ LẠI");
        btnQuayTrLi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FrmLeTan q = new FrmLeTan();
        		q.show();
        		dispose();
        		
        	}
        });
        btnQuayTrLi.setForeground(Color.WHITE);
        btnQuayTrLi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnQuayTrLi.setBackground(new Color(0, 123, 255));
        btnQuayTrLi.setBounds(600, 440, 250, 50);
        contentPane.add(btnQuayTrLi);

        btnDangKy.addActionListener(e -> {
            if (maBenhNhanDangChon == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân!");
                return;
            }

            int index = cmbChuyenKhoa.getSelectedIndex();
            if (index <= 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên khoa!");
                return;
            }

            ChuyenKhoa ck = listck.get(index - 1);

            int maNV = Session.maNhanVien;
            if (maNV <= 0) {
                JOptionPane.showMessageDialog(null, "Lỗi nhân viên!");
                return;
            }

            int stt = Integer.parseInt(txtSoThuTu.getText());
            String ghichu = txtGhiChu.getText();

            DangKyKhamBenh dk = new DangKyKhamBenh(
                    maBenhNhanDangChon,
                    maNV,
                    ck.getMa_chuyen_khoa(),
                    stt,
                    LocalDateTime.now(),
                    DangKyKhamBenh.TRANGTHAI.CHO_KHAM,
                    ghichu
            );

            int res = dkBus.themDangKy(dk);
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Đăng ký thành công! Số thứ tự: " + stt);

                int newStt = dkBus.laySoThuTuTiepTheo(ck.getMa_chuyen_khoa());
                txtSoThuTu.setText(String.valueOf(newStt));

            } else {
                JOptionPane.showMessageDialog(null, "Đăng ký thất bại!");
            }
        });

        // ✅ Load dữ liệu ban đầu
        loadDataComboBox();
        loadTableBN("");
    }

    private void loadTableBN(String keyword) {
        List<BenhNhan> list;
        if (keyword == null || keyword.trim().isEmpty()) {
            list = benhNhanBus.getAllBN();
        } else {
            list = benhNhanBus.TimkiemBN(keyword);
        }

        DefaultTableModel model = (DefaultTableModel) tableBN.getModel();
        model.setRowCount(0);
        for (BenhNhan bn : list) {
            model.addRow(new Object[]{
                    bn.getMaBenhNhan(),
                    bn.getHoTen(),
                    bn.GioiTinh(),
                    bn.getNgaySinh(),
                    bn.getSDT(),
                    bn.getCCCD()
            });
        }
    }
}
