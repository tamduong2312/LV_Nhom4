package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import BUS.PhongChucNangBus;
import MODEL.PhongChucNang;

public class FrmQuanLyPhongChucNang extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField; 
    private JTable table;
    private JTextField textField_1; 
    
    private JComboBox<String> comboBox; 
    private PhongChucNangBus bus = new PhongChucNangBus();
    private List<PhongChucNang> listPhong = new ArrayList<>();
    private DefaultTableModel tableModel;
    
    private int selectedId = -1; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FrmQuanLyPhongChucNang frame = new FrmQuanLyPhongChucNang();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void loadDataTable() {
        listPhong = bus.getAllPhong();
        fillTable(listPhong);
    }
    
    private void loadDataTableTIMKIEM(String keyword) {
        listPhong = bus.timKiemPhong(keyword);
        fillTable(listPhong);
    }
    
    private void fillTable(List<PhongChucNang> list) {
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (PhongChucNang p : list) {
            tableModel.addRow(new Object[] {
                p.getMaphong(),
                p.getTenphong(),
                p.getLoaiphong()
            });
        }
    }
    
    private PhongChucNang getModelFromForm() {
        String ten = textField.getText().trim();
        String loai = "";
        if (comboBox.getSelectedItem() != null) {
            loai = comboBox.getSelectedItem().toString();
        }
        
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên phòng không được để trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        PhongChucNang p = new PhongChucNang();
        p.setTenphong(ten);
        p.setLoaiphong(loai);
        return p;
    }
    
    private void resetForm() {
        textField.setText("");
        if (comboBox.getItemCount() > 0) comboBox.setSelectedIndex(0);
        selectedId = -1;
        table.clearSelection();
    }
    
    private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

    public FrmQuanLyPhongChucNang() {
        setTitle("Quản Lý Phòng Chức Năng"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600); 
        setLocationRelativeTo(null); 

        Color primaryColor = new Color(0, 123, 255); 
        Color dangerColor = new Color(220, 53, 69); 
        Color warningColor = new Color(255, 193, 7); 
        Color secondaryColor = new Color(108, 117, 125); 
        Color backgroundColor = new Color(245, 248, 250); 
        
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);

        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("QUẢN LÝ PHÒNG CHỨC NĂNG");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(primaryColor);
        lblTitle.setFont(titleFont);
        lblTitle.setBounds(0, 10, 884, 40);
        contentPane.add(lblTitle);

        int labelX = 50;
        int inputX = 160;
        int startY = 80;
        int gapY = 50;
        int inputWidth = 250;
        int inputHeight = 30;

        JLabel lblNewLabel = new JLabel("Tên phòng:");
        lblNewLabel.setFont(labelFont);
        lblNewLabel.setBounds(labelX, startY, 100, 25);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(inputFont);
        textField.setBounds(inputX, startY, inputWidth, inputHeight);
        textField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(100, 100, 100))); 
        textField.setBackground(backgroundColor);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblLoaiphong = new JLabel("Loại phòng:");
        lblLoaiphong.setFont(labelFont);
        lblLoaiphong.setBounds(labelX, startY + gapY, 100, 25);
        contentPane.add(lblLoaiphong);

        comboBox = new JComboBox<>();
        comboBox.setFont(inputFont);
        comboBox.setBackground(Color.WHITE);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "KHOA_NOI", "CLS_CHAN_DOAN_HINH_ANH", "CLS_XET_NGHIEM", "KHAM_CHUYEN_KHOA" }));
        comboBox.setBounds(inputX, startY + gapY, inputWidth, inputHeight);
        contentPane.add(comboBox);

        int btnX = 500;
        int btnY = 80;
        int btnWidth = 120;
        int btnHeight = 40;
        int btnGap = 15;

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.setBackground(primaryColor);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNewButton.setBounds(btnX, btnY, btnWidth, btnHeight);
        contentPane.add(btnNewButton);

        JButton btnNewButton_2 = new JButton("Sửa");
        btnNewButton_2.setBackground(warningColor);
        btnNewButton_2.setForeground(Color.BLACK);
        btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setBorderPainted(false);
        btnNewButton_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNewButton_2.setBounds(btnX + btnWidth + btnGap, btnY, btnWidth, btnHeight);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_1 = new JButton("Xóa");
        btnNewButton_1.setBackground(dangerColor);
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNewButton_1.setBounds(btnX, btnY + btnHeight + 20, btnWidth, btnHeight);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_3 = new JButton("Quay về");
        btnNewButton_3.setBackground(secondaryColor);
        btnNewButton_3.setForeground(Color.WHITE);
        btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNewButton_3.setFocusPainted(false);
        btnNewButton_3.setBorderPainted(false);
        btnNewButton_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNewButton_3.setBounds(btnX + btnWidth + btnGap, btnY + btnHeight + 20, btnWidth, btnHeight);
        contentPane.add(btnNewButton_3);

        int searchY = 220;
        JLabel lblTmKim = new JLabel("Tìm kiếm:");
        lblTmKim.setFont(labelFont);
        lblTmKim.setBounds(50, searchY, 80, 25);
        contentPane.add(lblTmKim);

        textField_1 = new JTextField();
        textField_1.setFont(inputFont);
        textField_1.setBounds(130, searchY, 700, 30);
        textField_1.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 270, 825, 260);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30); 
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 230, 230));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(232, 236, 241));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setSelectionBackground(new Color(184, 218, 255));
        
        table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Mã Phòng", "Tên Phòng", "Loại Phòng" 
            }
        ));
        scrollPane.setViewportView(table);
        
        loadDataTable();
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if(index == -1) return;
                
                selectedId = Integer.parseInt(getString(table.getValueAt(index, 0)));
                String ten = getString(table.getValueAt(index, 1));
                String loai = getString(table.getValueAt(index, 2));
                
                textField.setText(ten);
                comboBox.setSelectedItem(loai);
            }
        });
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PhongChucNang p = getModelFromForm();
                if(p != null) {
                    if(bus.themPhong(p)) {
                        JOptionPane.showMessageDialog(null, "Thêm phòng thành công!");
                        loadDataTable();
                        resetForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                    }
                }
            }
        });
        
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selectedId == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần sửa!");
                    return;
                }
                PhongChucNang p = getModelFromForm();
                if(p != null) {
                    p.setMaphong(selectedId);
                    if(bus.suaPhong(p)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                        loadDataTable();
                        resetForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                    }
                }
            }
        });
        
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selectedId == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần xóa!");
                    return;
                }
                if(JOptionPane.showConfirmDialog(null, "Xóa phòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                    if(bus.xoaPhong(selectedId)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
                        loadDataTable();
                        resetForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại!");
                    }
                }
            }
        });
        
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
                loadDataTable();
            }
        });
        
        textField_1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = textField_1.getText().trim();
                if(text.isEmpty()) loadDataTable();
                else loadDataTableTIMKIEM(text);
            }
        });
    }
}