package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BenhNhanBus;
import BUS.ChuyenKhoaBus;
import BUS.DangKyKhamBenhBus;
import BUS.NhanVienBUS;
import DAO.LichTaiKhamDao;
import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.LichTaiKham;
import MODEL.NhanVien;

public class FrmTaiKham extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private static FrmDangKyKhamBenh parentForm; 

    private JTextField txtSearch;
    private JTextField txtTuNgay;
    private JTextField txtDenNgay;
    private JButton btnLoc;
    private JButton btnReset;
    private JButton btnQuayVe; 
    private JButton btnChon;   
    
    private LichTaiKhamDao dao = new LichTaiKhamDao();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public static int selectedMaBenhNhan = -1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                FrmTaiKham frame = new FrmTaiKham(parentForm);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmTaiKham(FrmDangKyKhamBenh parent) {
        setTitle("Quản Lý Lịch Tái Khám");
        this.parentForm = parent; 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(100, 100, 1000, 700); 
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(0, 102, 204));
        pnlHeader.setBounds(0, 0, 1000, 50);
        pnlHeader.setLayout(null);
        contentPane.add(pnlHeader);

        JLabel lblTitle = new JLabel("DANH SÁCH LỊCH HẸN TÁI KHÁM");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 10, 1000, 30);
        pnlHeader.add(lblTitle);
        
        btnQuayVe = new JButton("Quay Về");
        btnQuayVe.setBounds(880, 10, 100, 30);
        btnQuayVe.setBackground(new Color(255, 69, 0)); 
        btnQuayVe.setForeground(Color.WHITE);
        btnQuayVe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnQuayVe.setFocusPainted(false);
        btnQuayVe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnQuayVe.addActionListener(e -> {
        	this.dispose();
        });
        pnlHeader.add(btnQuayVe);

        int yFilter = 70;
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 13);

        JLabel lblSearch = new JLabel("Tìm kiếm (Tên/SĐT):");
        lblSearch.setFont(fontLabel);
        lblSearch.setBounds(20, yFilter, 140, 30);
        contentPane.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setFont(fontText);
        txtSearch.setBounds(160, yFilter, 220, 35);
        styleTextField(txtSearch);
        contentPane.add(txtSearch);

        JLabel lblTu = new JLabel("Từ ngày:");
        lblTu.setFont(fontLabel);
        lblTu.setBounds(400, yFilter, 60, 30);
        contentPane.add(lblTu);

        txtTuNgay = new JTextField();
        txtTuNgay.setFont(fontText);
        txtTuNgay.setBounds(460, yFilter, 120, 35);
        txtTuNgay.setText(LocalDate.now().toString()); 
        styleTextField(txtTuNgay);
        contentPane.add(txtTuNgay);

        JLabel lblDen = new JLabel("Đến ngày:");
        lblDen.setFont(fontLabel);
        lblDen.setBounds(600, yFilter, 70, 30);
        contentPane.add(lblDen);

        txtDenNgay = new JTextField();
        txtDenNgay.setFont(fontText);
        txtDenNgay.setBounds(670, yFilter, 120, 35);
        txtDenNgay.setText(LocalDate.now().toString());
        styleTextField(txtDenNgay);
        contentPane.add(txtDenNgay);

        btnLoc = new JButton("Lọc");
        btnLoc.setBounds(810, yFilter, 80, 35);
        styleButton(btnLoc, new Color(0, 102, 204));
        contentPane.add(btnLoc);

        btnReset = new JButton("Tải lại");
        btnReset.setBounds(900, yFilter, 80, 35);
        styleButton(btnReset, new Color(108, 117, 125));
        contentPane.add(btnReset);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 945, 430); 
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        contentPane.add(scrollPane);

        table = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Mã BN","Chuyên khoa" ,"Tên Bệnh Nhân", "SĐT", "SĐT Giám Hộ", "Bác Sĩ Hẹn", "Ngày Tái Khám", "Ghi Chú", "Trạng Thái" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table.setModel(model);
        styleTable(table);
        
        scrollPane.setViewportView(table);
        
        btnChon = new JButton("CHỌN BỆNH NHÂN TÁI KHÁM");
        btnChon.setBounds(350, 580, 300, 45); 
        styleButton(btnChon, new Color(40, 167, 69)); 
        btnChon.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(btnChon);
        
        btnChon.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row != -1) {
                int maBN = Integer.parseInt(table.getValueAt(row, 1).toString());
                String tenBN = table.getValueAt(row, 3).toString();
                String tenChuyenKhoa = table.getValueAt(row, 2).toString();

                int opt = JOptionPane.showConfirmDialog(this, 
                        "Bạn chọn bệnh nhân: " + tenBN + " (Mã: " + maBN + ")?", 
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                
                if(opt == JOptionPane.YES_OPTION) {
                    if (parentForm != null) {
                        parentForm.setThongTinDangKy(maBN, tenBN, tenChuyenKhoa);
                    }
                    
                    this.dispose(); 
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng!", "Chưa chọn", JOptionPane.WARNING_MESSAGE);
            }
        });

        initEvents();
        loadAllData();
    }

    private void loadAllData() {
        List<LichTaiKham> list = dao.getAll(); 
        updateTable(list);
    }

    private void searchByKeyword() {
        String keyword = txtSearch.getText().trim();
        List<LichTaiKham> list = dao.timKiemLichHen(keyword);
        updateTable(list);
    }

    private void filterByDate() {
        String tu = txtTuNgay.getText().trim();
        String den = txtDenNgay.getText().trim();
        if (tu.isEmpty() || den.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày bắt đầu và kết thúc (yyyy-MM-dd)");
            return;
        }
        List<LichTaiKham> list = dao.locLichHenTheoNgay(tu, den);
        updateTable(list);
    }

    private void updateTable(List<LichTaiKham> list) {
        model.setRowCount(0);
        BenhNhanBus bnBus = new BenhNhanBus();
        List<BenhNhan> listBN = bnBus.getAllBN();
        
        NhanVienBUS nvBus = new NhanVienBUS();
        List<NhanVien> listNV = nvBus.getAllNV();
        
	    List<ChuyenKhoa>listck = new ArrayList<>();
		ChuyenKhoaBus bus1 = new ChuyenKhoaBus();
		listck = bus1.getAllCK();
        
        for (LichTaiKham ltk : list) {
        	String tenBN = "---";
        	String sdt = "---";
        	String sdtGH = "---";
        	String tenBS = "---";
        	String tenck ="";
        	
        	for(BenhNhan bn : listBN) {
        		if(bn.getMaBenhNhan() == ltk.getMaBenhNhan()) {
        			tenBN = bn.getHoTen();
        			sdt = String.valueOf(bn.getSDT());
        			sdtGH = String.valueOf(bn.getSDTNguoiGiamHo());
        			break;
        		}
        	}
    
        	for(NhanVien nv : listNV) {
        		if(nv.getMaNV() == ltk.getMaNhanVien()) {
        			tenBS = nv.getHoTen();
        			break;
        		}
        	}
        	
		    for(ChuyenKhoa ck : listck) {
				if(ck.getMa_chuyen_khoa() == ltk.getMack()) {
					tenck = ck.getTen_chuyen_khoa();
				}
			}
        	
            model.addRow(new Object[] {
                ltk.getId(),
                ltk.getMaBenhNhan(),
                tenck,
                tenBN,
                sdt,
                sdtGH,
                tenBS,
                (ltk.getNgayTaiKham() != null) ? sdf.format(ltk.getNgayTaiKham()) : "",
                ltk.getGhiChu(),
                ltk.getTrangThai()
            });
        }
    }

    private void initEvents() {
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchByKeyword();
            }
        });

        btnLoc.addActionListener(e -> filterByDate());

        btnReset.addActionListener(e -> {
            txtSearch.setText("");
            txtTuNgay.setText(LocalDate.now().toString());
            txtDenNgay.setText(LocalDate.now().toString());
            loadAllData();
        });
    }

    private void styleTextField(JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)), 
            new EmptyBorder(5, 5, 5, 5)
        ));
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleTable(JTable table) {
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setGridColor(new Color(230, 230, 230));
        table.setShowVerticalLines(false);
        table.setSelectionBackground(new Color(232, 240, 254));
        table.setSelectionForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(245, 245, 245));
        header.setForeground(new Color(70, 70, 70));
        header.setPreferredSize(new java.awt.Dimension(0, 35));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    }
}