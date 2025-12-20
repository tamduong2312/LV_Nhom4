package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.BenhNhanBus;
import BUS.ChuyenKhoaBus;
import BUS.HoaDonBus;
import BUS.NhanVienBUS;
import BUS.PhieuKhamBus;
import DAO.TinhTienDao;
import MODEL.BenhNhan;
import MODEL.ChuyenKhoa;
import MODEL.HoaDon;
import MODEL.NhanVien;
import java.text.DecimalFormat;
import MODEL.PhieuKham;
import MODEL.Session;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmThuNgan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private PhieuKhamBus bus = new PhieuKhamBus();
	private NhanVienBUS nvbus = new NhanVienBUS();
	private BenhNhanBus bnbus = new BenhNhanBus();
	private ChuyenKhoaBus ckbus = new ChuyenKhoaBus();
	
	private List<PhieuKham> listphieu = new ArrayList<>();
	private List<BenhNhan> listbn = new ArrayList<>();
	private List<NhanVien> listnhanvien = new ArrayList<>();
	private List<ChuyenKhoa> listck = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThuNgan frame = new FrmThuNgan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    private void timKiem(String keyword) {
        TinhTienDao dao = new TinhTienDao();
        List<PhieuKham> ketQua = dao.timKiemPhieuKhamThanhToan(keyword);
        this.listphieu = ketQua;
        loadDataTableFromList(this.listphieu);
    }
    
    private void loadDataTableFromList(List<PhieuKham> danhSachHienThi) {
        TinhTienDao tinhTienDao = new TinhTienDao(); 
        DecimalFormat df = new DecimalFormat("#,###"); 

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);

        for (PhieuKham nvs : danhSachHienThi) {
            String tenbn = "Không tìm thấy"; 
            String tennv = "Không tìm thấy";
            String trangthai = "";
            String sdt = "";
            
            if (nvs.getTrangThai().equals("DA_KHAM")) {
                trangthai = "Chờ thanh toán"; 
            } else if (nvs.getTrangThai().equals("DA_THANH_TOAN")) {
                trangthai = "Đã thanh toán";
            } else {
                trangthai = nvs.getTrangThai();
            }

            for (BenhNhan bn : listbn) {
                if (bn.getMaBenhNhan() == nvs.getMaBenhNhan()) {
                    tenbn = bn.getHoTen();
                    sdt = bn.getSDT()+"";
                    break; 
                }
            }

            for (NhanVien nv : listnhanvien) {
                if (nv.getMaNV() == nvs.getMaNhanVien()) {
                    tennv = nv.getHoTen();
                    break;
                }
            }

            double tongTienCanThu = tinhTienDao.tinhTongTien(nvs.getMaPhieuKham());
            String hienThiTien = (tongTienCanThu > 0) ? df.format(tongTienCanThu) : "0";

            defaultTableModel.addRow(new Object[] {
                nvs.getMaPhieuKham(),
                tenbn,
                sdt,
                "BS."+tennv,
                nvs.getMaChuyenKhoa(), 
                nvs.getNgayKham(),
                hienThiTien, 
                trangthai,
                "Thanh toán" 
            });
        }
        table.setModel(defaultTableModel);
    }

	private void loadDataTable() {
	    listphieu = bus.getAllPhieuKhamDaKhamHomNay();
	    listbn = bnbus.getAllBN();       
	    listnhanvien = nvbus.getAllNV(); 

	    TinhTienDao tinhTienDao = new TinhTienDao(); 
	    DecimalFormat df = new DecimalFormat("#,###"); 

	    DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
	    defaultTableModel.setRowCount(0);

	    for (PhieuKham nvs : listphieu) {
	        String tenbn = "Không tìm thấy"; 
	        String tennv = "Không tìm thấy";
	        String trangthai = "";
	        String sdt = "";
	        
	        if (nvs.getTrangThai().equals("DA_KHAM")) {
	            trangthai = "Chờ thanh toán";
	        } else if (nvs.getTrangThai().equals("DA_THANH_TOAN")) {
	            trangthai = "Đã thanh toán";
	        } else {
	            trangthai = nvs.getTrangThai();
	        }

	        for (BenhNhan bn : listbn) {
	            if (bn.getMaBenhNhan() == nvs.getMaBenhNhan()) {
	                tenbn = bn.getHoTen();
	                sdt = bn.getSDT()+"";
	                break; 
	            }
	        }

	        for (NhanVien nv : listnhanvien) {
	            if (nv.getMaNV() == nvs.getMaNhanVien()) {
	                tennv = nv.getHoTen();
	                break;
	            }
	        }

	        double tongTienCanThu = tinhTienDao.tinhTongTien(nvs.getMaPhieuKham());
	        String hienThiTien = (tongTienCanThu > 0) ? df.format(tongTienCanThu) : "0";

	        defaultTableModel.addRow(new Object[] {
	            nvs.getMaPhieuKham(),
	            tenbn,
	            sdt,
	            "BS."+tennv,
	            nvs.getMaChuyenKhoa(), 
	            nvs.getNgayKham(),
	            hienThiTien, 
	            trangthai,
	            "Thanh toán" 
	        });
	    }
	    loadDataTableFromList(listphieu);
	    table.setModel(defaultTableModel);
	}

	public FrmThuNgan() {
		setTitle("Quản Lý Thu Ngân");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650); 
		setLocationRelativeTo(null); 

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 248, 250)); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font fontValue = new Font("Segoe UI", Font.BOLD, 20);
		Color colPrimary = new Color(13, 110, 253); 
		Color colSuccess = new Color(25, 135, 84);  
		
		JLabel lblNewLabel = new JLabel("Tổng bệnh nhân");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(40, 30, 150, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("25"); 
		lblNewLabel_3.setFont(fontValue);
		lblNewLabel_3.setForeground(colPrimary);
		lblNewLabel_3.setBounds(40, 55, 100, 30);
		contentPane.add(lblNewLabel_3);
		TinhTienDao dao2 = new TinhTienDao();
		lblNewLabel_3.setText(dao2.demtrangthaiTATCA() +"");

		JPanel line1 = new JPanel();
		line1.setBackground(new Color(220, 220, 220));
		line1.setBounds(220, 30, 1, 50);
		contentPane.add(line1);

		JLabel lblNewLabel_1 = new JLabel("Chờ thanh toán");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBounds(250, 30, 150, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("5"); 
		lblNewLabel_3_1.setFont(fontValue);
		lblNewLabel_3_1.setForeground(new Color(220, 120, 0)); 
		lblNewLabel_3_1.setBounds(250, 55, 100, 30);
		contentPane.add(lblNewLabel_3_1);
		TinhTienDao dao = new TinhTienDao();
		lblNewLabel_3_1.setText(dao.demtrangthaiCHOTHANHTOAN() +"");
		
		JPanel line2 = new JPanel();
		line2.setBackground(new Color(220, 220, 220));
		line2.setBounds(430, 30, 1, 50);
		contentPane.add(line2);

		JLabel lblNewLabel_2 = new JLabel("Đã thanh toán");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setBounds(460, 30, 150, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblPaidCount = new JLabel("20");
		lblPaidCount.setFont(fontValue);
		lblPaidCount.setForeground(colSuccess);
		lblPaidCount.setBounds(460, 55, 100, 30);
		contentPane.add(lblPaidCount);
		
		TinhTienDao dao1 = new TinhTienDao();
		lblPaidCount.setText(dao1.demtrangthaiDATHANHTOAN() +"");

		int filterY = 110;
		int btnHeight = 35;

		textField = new JTextField();
		textField.setBounds(40, filterY, 400, btnHeight);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(200, 200, 200)), 
				new EmptyBorder(5, 10, 5, 10) 
		));
		
		textField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String keyword = textField.getText().trim();
                if (keyword.isEmpty()) {
                    loadDataTable(); 
                } else {
                    timKiem(keyword);
                }
            }
        });
		textField.setToolTipText("Tìm kiếm theo tên hoặc mã bệnh nhân..."); 
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tất cả");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataTable();
			}
		});
		btnNewButton.setBounds(460, filterY, 100, btnHeight);
		styleButton(btnNewButton, colPrimary, Color.WHITE);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Chờ thanh toán");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dakham = "DA_KHAM";
			    listphieu = bus.getAllPhieuKhamDaKhamHomNayByDK(dakham);
			    listbn = bnbus.getAllBN();       
			    listnhanvien = nvbus.getAllNV(); 

			    TinhTienDao tinhTienDao = new TinhTienDao(); 
			    DecimalFormat df = new DecimalFormat("#,###"); 

			    DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			    defaultTableModel.setRowCount(0);

			    for (PhieuKham nvs : listphieu) {
			        String tenbn = "Không tìm thấy"; 
			        String tennv = "Không tìm thấy";
			        String trangthai = "";
			        String sdt = "";
			        
			        if (nvs.getTrangThai().equals("DA_KHAM")) {
			            trangthai = "Chờ thanh toán"; 
			        } else if (nvs.getTrangThai().equals("DA_THANH_TOAN")) {
			            trangthai = "Đã thanh toán";
			        } else {
			            trangthai = nvs.getTrangThai();
			        }

			        for (BenhNhan bn : listbn) {
			            if (bn.getMaBenhNhan() == nvs.getMaBenhNhan()) {
			                tenbn = bn.getHoTen();
			                sdt = bn.getSDT()+"";
			                break; 
			            }
			        }

			        for (NhanVien nv : listnhanvien) {
			            if (nv.getMaNV() == nvs.getMaNhanVien()) {
			                tennv = nv.getHoTen();
			                break;
			            }
			        }

			        double tongTienCanThu = tinhTienDao.tinhTongTien(nvs.getMaPhieuKham());
			        String hienThiTien = (tongTienCanThu > 0) ? df.format(tongTienCanThu) : "0";

			        defaultTableModel.addRow(new Object[] {
			            nvs.getMaPhieuKham(),
			            tenbn,
			            sdt,
			            "BS."+tennv,
			            nvs.getMaChuyenKhoa(), 
			            nvs.getNgayKham(),
			            hienThiTien, 
			            trangthai,
			            "Thanh toán" 
			        });
			    }
			    loadDataTableFromList(listphieu);
			    table.setModel(defaultTableModel);
			}
		});
		btnNewButton_1.setBounds(570, filterY, 140, btnHeight);
		styleButton(btnNewButton_1, Color.WHITE, Color.DARK_GRAY);
		btnNewButton_1.setBorder(new LineBorder(new Color(200, 200, 200))); 
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBorderPainted(false);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Đã thanh toán");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dakham = "DA_THANH_TOAN";
			    listphieu = bus.getAllPhieuKhamDaKhamHomNayByDK(dakham);
			    listbn = bnbus.getAllBN();       
			    listnhanvien = nvbus.getAllNV(); 

			    TinhTienDao tinhTienDao = new TinhTienDao(); 
			    DecimalFormat df = new DecimalFormat("#,###"); 

			    DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			    defaultTableModel.setRowCount(0);

			    for (PhieuKham nvs : listphieu) {
			        String tenbn = "Không tìm thấy"; 
			        String tennv = "Không tìm thấy";
			        String trangthai = "";
			        String sdt = "";
			        
			        if (nvs.getTrangThai().equals("DA_KHAM")) {
			            trangthai = "Chờ thanh toán"; 
			        } else if (nvs.getTrangThai().equals("DA_THANH_TOAN")) {
			            trangthai = "Đã thanh toán";
			        } else {
			            trangthai = nvs.getTrangThai();
			        }

			        for (BenhNhan bn : listbn) {
			            if (bn.getMaBenhNhan() == nvs.getMaBenhNhan()) {
			                tenbn = bn.getHoTen();
			                sdt = bn.getSDT()+"";
			                break; 
			            }
			        }

			        for (NhanVien nv : listnhanvien) {
			            if (nv.getMaNV() == nvs.getMaNhanVien()) {
			                tennv = nv.getHoTen();
			                break;
			            }
			        }

			        double tongTienCanThu = tinhTienDao.tinhTongTien(nvs.getMaPhieuKham());
			        String hienThiTien = (tongTienCanThu > 0) ? df.format(tongTienCanThu) : "0";

			        defaultTableModel.addRow(new Object[] {
			            nvs.getMaPhieuKham(),
			            tenbn,
			            sdt,
			            "BS."+tennv,
			            nvs.getMaChuyenKhoa(), 
			            nvs.getNgayKham(),
			            hienThiTien, 
			            trangthai,
			            "Thanh toán" 
			        });
			    }
			    loadDataTableFromList(listphieu);
			    table.setModel(defaultTableModel);
			}
		});
		btnNewButton_2.setBounds(720, filterY, 140, btnHeight);
		styleButton(btnNewButton_2, Color.WHITE, Color.DARK_GRAY);
		btnNewButton_2.setBorder(new LineBorder(new Color(200, 200, 200)));
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 170, 900, 400);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		scrollPane.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		
		table = new JTable();
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		header.setBackground(new Color(240, 240, 240));
		header.setForeground(new Color(50, 50, 50));
		header.setPreferredSize(new java.awt.Dimension(0, 35));
		
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setGridColor(new Color(230, 230, 230));
		table.setShowVerticalLines(false); 
		table.setSelectionBackground(new Color(230, 240, 255));
		table.setSelectionForeground(Color.BLACK);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã phiếu khám", "Tên Bệnh Nhân","Số điện thoại", "Bác Sĩ", "Dịch Vụ", "Thời Gian", "Tổng Tiền", "Trạng Thái", "Thao Tác"
			}
		)
		{
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8; 
            }
		});
		
	    table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
	    table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new javax.swing.JCheckBox()));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("quay về");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDuocSiThuNgan q = new FrmDuocSiThuNgan();
				q.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(757, 577, 89, 23);
		contentPane.add(btnNewButton_3);
		loadDataTable();
	}
	
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setFont(new Font("Segoe UI", Font.BOLD, 12));
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            setText((value == null) ? "" : value.toString());
            setBackground(new Color(13, 110, 253)); 
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            return this;
        }
    }

    class ButtonEditor extends javax.swing.DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private int currentRow; 

        public ButtonEditor(javax.swing.JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setFont(new Font("Segoe UI", Font.BOLD, 12));
            button.setBackground(new Color(13, 110, 253));
            button.setForeground(Color.WHITE);
            
            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    fireEditingStopped(); 
                }
            });
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            currentRow = row; 
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int maPK = Integer.parseInt(table.getValueAt(currentRow, 0).toString());
                String tenBN = table.getValueAt(currentRow, 1).toString();
                
                HoaDon hd = new HoaDon();
                hd.setMaPhieuKham(maPK);
                hd.setMaNhanVien(Session.maNhanVien); 
                hd.setTongTien(0); 
                
                HoaDonBus bus = new HoaDonBus();
                int maHD = bus.taoHoaDon(hd); 

                if (maHD > 0) {
                    JDialog dialog = new JDialog();
                    FrmTinhTien1 pnl = new FrmTinhTien1(maPK, tenBN,maHD);
                    dialog.getContentPane().add(pnl);
                    dialog.setSize(900, 750);
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true); 
                    loadDataTable(); 
                }
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
	
	private void styleButton(JButton btn, Color bg, Color fg) {
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setBackground(bg);
		btn.setForeground(fg);
		btn.setFocusPainted(false); 
		btn.setBorderPainted(false); 
		if(bg.equals(Color.WHITE)) {
			btn.setOpaque(true); 
		}
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
	}
}