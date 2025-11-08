package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BUS.DangNhapBus;
import MODEL.NguoiDung;

public class FrmQuanLyTaiKhoan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtten;
    private JTextField txtmk;
    private JTable table;
    private DangNhapBus ckbus = new DangNhapBus();
    private List<NguoiDung> listnguoidung = new ArrayList<>();
    private int index;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmQuanLyTaiKhoan frame = new FrmQuanLyTaiKhoan();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void loadDataTable() {
        listnguoidung = ckbus.getalltk();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (NguoiDung nd : listnguoidung) {
            model.addRow(new Object[]{nd.getMaNguoiDung(), nd.getTenDangNhap(), nd.getMatKhau()});
        }
    }

    public FrmQuanLyTaiKhoan() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 950, 680);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        Color primaryColor = new Color(0, 123, 255);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel lblTitle = new JLabel("QUẢN LÝ TÀI KHOẢN");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        lblTitle.setForeground(primaryColor);
        lblTitle.setBounds(300, 20, 350, 40);
        contentPane.add(lblTitle);

        JLabel lblTenDN = new JLabel("Tên đăng nhập:");
        lblTenDN.setFont(labelFont);
        lblTenDN.setBounds(220, 120, 120, 25);
        contentPane.add(lblTenDN);

        txtten = new JTextField();
        txtten.setFont(textFont);
        txtten.setBounds(350, 120, 220, 30);
        txtten.setBackground(new Color(245, 245, 245));
        txtten.setBorder(new LineBorder(new Color(200, 200, 200)));
        contentPane.add(txtten);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(labelFont);
        lblMatKhau.setBounds(220, 170, 120, 25);
        contentPane.add(lblMatKhau);

        txtmk = new JTextField();
        txtmk.setFont(textFont);
        txtmk.setBounds(350, 170, 220, 30);
        txtmk.setBackground(new Color(245, 245, 245));
        txtmk.setBorder(new LineBorder(new Color(200, 200, 200)));
        contentPane.add(txtmk);

        // ===== Buttons =====
        JButton btnThem = new JButton("Thêm tài khoản");
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(Color.WHITE);
        btnThem.setBackground(primaryColor);
        btnThem.setFocusPainted(false);
        btnThem.setBounds(220, 230, 150, 40);
        btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(btnThem);

        JButton btnSua = new JButton("Sửa tài khoản");
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(Color.WHITE);
        btnSua.setBackground(new Color(0, 200, 83));
        btnSua.setFocusPainted(false);
        btnSua.setBounds(390, 230, 150, 40);
        btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(btnSua);

        JButton btnXoa = new JButton("Xóa tài khoản");
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setBackground(Color.RED);
        btnXoa.setFocusPainted(false);
        btnXoa.setBounds(560, 230, 150, 40);
        btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(btnXoa);

        // ===== Table =====
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 310, 800, 300);
        scrollPane.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(primaryColor);
        table.setSelectionForeground(Color.WHITE);
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã người dùng", "Tên đăng nhập", "Mật khẩu"}
        ));
        scrollPane.setViewportView(table);
        
        JButton btnQuayTrLi = new JButton("Quay trở lại");
        btnQuayTrLi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FrmQuanLy ql = new FrmQuanLy();
        		ql.show();
        		dispose();
        	}
        });
        btnQuayTrLi.setForeground(Color.WHITE);
        btnQuayTrLi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnQuayTrLi.setFocusPainted(false);
        btnQuayTrLi.setBackground(Color.RED);
        btnQuayTrLi.setBounds(726, 230, 150, 40);
        contentPane.add(btnQuayTrLi);

        // ===== Table Click =====
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                index = table.getSelectedRow();
                if (index != -1) {
                    int id = (int) table.getValueAt(index, 0);
                    String ten = (String) table.getValueAt(index, 1);
                    String mk = (String) table.getValueAt(index, 2);
                    txtten.setText(ten);
                    txtmk.setText(mk);
                }
            }
        });

        // ===== Button Actions =====
        btnThem.addActionListener(e -> {
            String ten = txtten.getText().trim();
            String mk = txtmk.getText().trim();
            if (ten.isEmpty() || mk.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            NguoiDung nd = new NguoiDung(ten, mk);
            ckbus.ThemTK(nd);
            loadDataTable();
            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
        });

        btnSua.addActionListener(e -> {
            index = table.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa!");
                return;
            }
            int id = (int) table.getValueAt(index, 0);
            String ten = txtten.getText().trim();
            String mk = txtmk.getText().trim();
            NguoiDung nd = new NguoiDung(ten, mk);
            ckbus.SuaTK(nd, id);
            loadDataTable();
            JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công!");
        });

        btnXoa.addActionListener(e -> {
            index = table.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xóa!");
                return;
            }
            int id = (int) table.getValueAt(index, 0);
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn xóa tài khoản này không?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ckbus.xoaTK(id);
                loadDataTable();
                JOptionPane.showMessageDialog(null, "Đã xóa tài khoản thành công!");
            }
        });

        loadDataTable();
    }
}
