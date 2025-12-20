package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Desktop;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.DuocSiBus;
import BUS.LichTaiKhamBus;
import BUS.PhieuKhamBus;
import BUS.ToaThuocBus;
import DAO.KhoThuocDao;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// Import Models
import MODEL.CTToaThuoc;
import MODEL.DonThuocCho;
import MODEL.NhanVien;
import MODEL.ToaThuoc;

public class FrmDuocSi1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table_1; 
	
	// --- COMPONENT CÔNG KHAI ---
	public JTable table;         
	public JTable table_2;       
	
	public JLabel lblMaDon;      
	public JLabel lblNgayKe;     
	public JLabel lblTenBenhNhan; 
	public JLabel lblTenBacSi;    
	
	public JLabel lbltenbn;
	public JLabel lblsdt;
	public JLabel lbltennv;
	
	public JLabel lbltaikham;
	public JLabel lbltaikham1;
	
	public JLabel lblTongTienVal;
	public JButton btnXacNhan;
	public JTextField textField; 
	
	public JButton btnTatCa;
	public JButton btnDaCap;
	public JButton btnChuaCap;
	public JButton btnQuayVe; 
	
	   private BUS.KhoThuocBus khoBus = new BUS.KhoThuocBus();
	    private List<CTToaThuoc> listChiTietHienTai = new ArrayList<>();
	    public JLabel lblTonKho;
	
	private List<DonThuocCho> list = new ArrayList<>();
	private DuocSiBus Bus = new DuocSiBus();

	/**
	 * Create the panel.
	 */
	
    private void loadDataTable() {
    	list = Bus.getDsChoCapPhat();
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setNumRows(0);

        for (DonThuocCho nvs : list) {
        	String tt ="";
        	if(nvs.getTrangThai().equals("DA_CAP_THUOC")) {
        		tt = "Đã cấp thuốc";
        	}
        	else {
        		tt = "Chờ cấp thuốc";
        	}
        		
            defaultTableModel.addRow(new Object[] {
                    nvs.getMaPhieuKham(),
                    nvs.getMaToaThuoc(),
                    nvs.getTenBenhNhan(),
                    nvs.getSDT(),
                    nvs.getTenBacSi(),
                    nvs.getThoiGian(),
                    tt
            });
        }
    }
    
	public FrmDuocSi1() {
		// --- CẤU HÌNH PANEL CHÍNH ---
		setLayout(null);
		setBackground(new Color(245, 247, 250)); 
		setSize(1200, 800);

		// --- KHAI BÁO FONT & MÀU SẮC CHUẨN ---
		Font fontLabel = new Font("Segoe UI", Font.PLAIN, 13);
		Font fontValue = new Font("Segoe UI", Font.BOLD, 26);
		Font fontHeader = new Font("Segoe UI", Font.BOLD, 18);
		Font fontText = new Font("Segoe UI", Font.PLAIN, 14);
		Font fontData = new Font("Segoe UI", Font.BOLD, 15);
		
		Color colWhite = Color.WHITE;
		Color colText = new Color(50, 50, 50);
		Color colGray = new Color(120, 120, 120);
		Color colPrimary = new Color(13, 110, 253);   
		Color colWarning = new Color(253, 126, 20);   
		Color colDanger = new Color(220, 53, 69);     
		Color colSuccess = new Color(25, 135, 84);    
		Color colBorder = new Color(220, 220, 220);


				
				int cardY = 20;
				int cardH = 100;
				int startX = 30;
				int gap = 20;
				
		
				int cardW = (1200 - (startX * 2) - gap) / 2; 

	
				JPanel pnlCard1 = new JPanel();
				pnlCard1.setLayout(null);
				pnlCard1.setBackground(colWhite);
				pnlCard1.setBounds(startX, cardY, cardW, cardH);
				pnlCard1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, colPrimary)); 
				add(pnlCard1);

				JLabel lblNewLabel = new JLabel("Đơn thuốc chờ");
				lblNewLabel.setFont(fontLabel);
				lblNewLabel.setForeground(colGray);
				lblNewLabel.setBounds(30, 15, 200, 20); 
				pnlCard1.add(lblNewLabel); 
				
				JLabel lblNewLabel_1 = new JLabel("0");
				lblNewLabel_1.setFont(fontValue);
				lblNewLabel_1.setForeground(colPrimary);
				lblNewLabel_1.setBounds(30, 45, 100, 35);
				pnlCard1.add(lblNewLabel_1);
				
				DuocSiBus bus1 = new DuocSiBus();
				lblNewLabel_1.setText(bus1.demtrangthaichocapthuoc()+"");

	
				JPanel pnlCard4 = new JPanel();
				pnlCard4.setLayout(null);
				pnlCard4.setBackground(colWhite);
				

				pnlCard4.setBounds(startX + cardW + gap, cardY, cardW, cardH); 
				
				pnlCard4.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, colSuccess));
				add(pnlCard4);
				
				JLabel lblNewLabel_2_1_1 = new JLabel("Đã cấp phát hôm nay");
				lblNewLabel_2_1_1.setFont(fontLabel);
				lblNewLabel_2_1_1.setForeground(colGray);
				lblNewLabel_2_1_1.setBounds(30, 15, 200, 20);
				pnlCard4.add(lblNewLabel_2_1_1);

				JLabel lblNewLabel_1_1_1_1 = new JLabel("0");
				lblNewLabel_1_1_1_1.setFont(fontValue);
				lblNewLabel_1_1_1_1.setForeground(colSuccess);
				lblNewLabel_1_1_1_1.setBounds(30, 45, 100, 35);
				pnlCard4.add(lblNewLabel_1_1_1_1);
				
				DuocSiBus bus = new DuocSiBus();
				lblNewLabel_1_1_1_1.setText(bus.demtrangthaidacapthuoc()+"");


		int leftX = 30;
		int leftY = 140;
		int leftW = 480; 
		int leftH = 630;
		
		// --- Ô TÌM KIẾM ---
		textField = new JTextField();
		textField.setBounds(leftX, leftY, leftW, 40); 
		textField.setFont(fontText);
		textField.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(colBorder, 1, true), 
				new javax.swing.border.EmptyBorder(5, 10, 5, 5) 
		));
		add(textField);
		textField.setColumns(10);
		
		textField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String keyword = textField.getText().trim();
                if (keyword.isEmpty()) {
                    loadDataTable(); 
                } else {
                	DuocSiBus Bus1 = new DuocSiBus();           
                	list = Bus1.timKiemPhieuKhamThanhToan(keyword);
                    DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
                    defaultTableModel.setNumRows(0);
                    for (DonThuocCho nvs : list) {
                        defaultTableModel.addRow(new Object[] {
                                nvs.getMaPhieuKham(),
                                nvs.getMaToaThuoc(),
                                nvs.getTenBenhNhan(),
                                nvs.getSDT(),
                                nvs.getTenBacSi(),
                                nvs.getThoiGian(),
                                nvs.getTrangThai()
                        });
                    }
                }
            }
		});

		// --- CÁC NÚT LỌC ---
		int btnY = leftY + 50;
		int btnW = 100;
		int btnH = 35;
		
		btnTatCa = new JButton("Tất cả");
		btnTatCa.setBounds(leftX, btnY, btnW, btnH);
		styleButton(btnTatCa, colPrimary, colWhite);
		btnTatCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataTable();
			}
		});
		add(btnTatCa);
		
		btnChuaCap = new JButton("Chờ cấp");
		btnChuaCap.setBounds(leftX + btnW + 10, btnY, btnW, btnH);
		styleButton(btnChuaCap, colWhite, colText);
		btnChuaCap.setBorder(new LineBorder(colBorder));
		btnChuaCap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String da = "DA_THANH_TOAN";
			   	list = Bus.getDsChoTheoDK(da);
		        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		        defaultTableModel.setNumRows(0);
		        for (DonThuocCho nvs : list) {
		         	String tt ="";
		        	if(nvs.getTrangThai().equals("DA_CAP_THUOC")) {
		        		tt = "Đã cấp thuốc";
		        	} else {
		        		tt = "Chờ cấp thuốc";
		        	}
		            defaultTableModel.addRow(new Object[] {
		                    nvs.getMaPhieuKham(),
		                    nvs.getMaToaThuoc(),
		                    nvs.getTenBenhNhan(),
		                    nvs.getSDT(),
		                    nvs.getTenBacSi(),
		                    nvs.getThoiGian(),
		                  tt
		            });
		        }
			}
		});
		add(btnChuaCap);
		
		btnDaCap = new JButton("Đã cấp");
		btnDaCap.setBounds(leftX + (btnW + 10) * 2, btnY, btnW, btnH);
		styleButton(btnDaCap, colWhite, colText);
		btnDaCap.setBorder(new LineBorder(colBorder));
		btnDaCap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String da = "DA_CAP_THUOC";
			   	list = Bus.getDsChoTheoDK(da);
		        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		        defaultTableModel.setNumRows(0);
		        for (DonThuocCho nvs : list) {
		         	String tt ="";
		        	if(nvs.getTrangThai().equals("DA_CAP_THUOC")) {
		        		tt = "Đã cấp thuốc";
		        	} else {
		        		tt = "Chờ cấp thuốc";
		        	}
		            defaultTableModel.addRow(new Object[] {
		                    nvs.getMaPhieuKham(),
		                    nvs.getMaToaThuoc(),
		                    nvs.getTenBenhNhan(),
		                    nvs.getSDT(),
		                    nvs.getTenBacSi(),
		                    nvs.getThoiGian(),
		                  tt
		            });
		        }
			}
		});
		add(btnDaCap);
		
		// --- BẢNG DANH SÁCH ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(leftX, btnY + 45, leftW, 520);
		scrollPane.setBorder(new LineBorder(colBorder));
		scrollPane.getViewport().setBackground(colWhite);
		add(scrollPane);
		
		table = new JTable();
		styleTable(table); 
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					int id = (int) table.getValueAt(index, 1);
					int mapk = (int) table.getValueAt(index, 0);
					String tenbn = table.getValueAt(index, 2).toString();
					String sdt = table.getValueAt(index, 3).toString();
					String tennv = table.getValueAt(index, 4).toString();
					
					lbltenbn.setText(tenbn);
					lblsdt.setText(sdt);
					lbltennv.setText(tennv);
					
					LichTaiKhamBus lichbus = new LichTaiKhamBus();
					java.sql.Date ngayTaiKham = lichbus.getNgayTaiKhamByMaPK(mapk);
					if (ngayTaiKham != null) {
					    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					    lbltaikham1.setText(sdf.format(ngayTaiKham));
					} else {
					    lbltaikham1.setText("Không có");
					}
					
					List<CTToaThuoc> list1 = new ArrayList<>();
					ToaThuocBus bus = new ToaThuocBus();
					list1 = bus.getListChiTiet(id);
	
					
			        listChiTietHienTai = list1; 
					System.out.print(listChiTietHienTai);
	                lblTonKho.setText("..."); // Reset label tồn kho
					
			        DefaultTableModel defaultTableModel = (DefaultTableModel) table_2.getModel();
			        defaultTableModel.setNumRows(0);
			        for (CTToaThuoc nvs : list1) {
			            defaultTableModel.addRow(new Object[] {
			                    nvs.getTenThuoc(),
			                    nvs.getSang(),
			                    nvs.getTrua(),
			                    nvs.getChieu(),
			                    nvs.getToi(),
			                    nvs.getSoNgay(),
			                    nvs.getCachDung(),
			                    nvs.getThoiDiemDung(),
			            });
			        }
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] { "Mã phiếu", "Mã toa", "Tên bệnh nhân", "SĐT", "Bác Sĩ", "Ngày kê", "Trạng thái" }
		) {
			public boolean isCellEditable(int row, int column) { return false; }
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		
		scrollPane.setViewportView(table);
		

		int rightX = leftX + leftW + 20;
		int rightW = 640;
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(colWhite);
		layeredPane.setBounds(rightX, leftY, rightW, 615);
		layeredPane.setBorder(new LineBorder(colBorder, 1));
		layeredPane.setOpaque(true);
		add(layeredPane);
		
		JLabel lblDetailTitle = new JLabel("THÔNG TIN CHI TIẾT");
		lblDetailTitle.setBounds(30, 20, 300, 30);
		lblDetailTitle.setFont(fontHeader);
		lblDetailTitle.setForeground(colPrimary);
		layeredPane.add(lblDetailTitle);

		btnQuayVe = new JButton("Quay về");
		btnQuayVe.setBounds(rightW - 120, 20, 100, 30);
		styleButton(btnQuayVe, new Color(108, 117, 125)); 
		btnQuayVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(FrmDuocSi1.this);
				
				FrmDuocSiThuNgan q = new  FrmDuocSiThuNgan();
				q.setVisible(true);
				if(win != null) win.dispose();
			}
		});
		layeredPane.add(btnQuayVe);

		int infoY = 70;
		int col1 = 30, col2 = 250, col3 = 450;
		
		JLabel l1 = new JLabel("Tên bệnh nhân:"); l1.setFont(fontLabel); l1.setForeground(colGray); l1.setBounds(col1, infoY, 150, 20); layeredPane.add(l1);
		JLabel l2 = new JLabel("Số điện thoại:"); l2.setFont(fontLabel); l2.setForeground(colGray); l2.setBounds(col2, infoY, 150, 20); layeredPane.add(l2);
		JLabel l3 = new JLabel("Tên bác sĩ:"); l3.setFont(fontLabel); l3.setForeground(colGray); l3.setBounds(col3, infoY, 150, 20); layeredPane.add(l3);

		lbltenbn = new JLabel("..."); lbltenbn.setFont(fontData); lbltenbn.setForeground(colText); lbltenbn.setBounds(col1, infoY + 20, 200, 25); layeredPane.add(lbltenbn);
		lblsdt = new JLabel("..."); lblsdt.setFont(fontData); lblsdt.setForeground(colText); lblsdt.setBounds(col2, infoY + 20, 150, 25); layeredPane.add(lblsdt);
		lbltennv = new JLabel("..."); lbltennv.setFont(fontData); lbltennv.setForeground(colText); lbltennv.setBounds(col3, infoY + 20, 150, 25); layeredPane.add(lbltennv);

		lbltaikham = new JLabel("Ngày Tái khám:"); lbltaikham.setFont(fontLabel); lbltaikham.setForeground(colGray); lbltaikham.setBounds(col1, infoY + 55, 150, 20); layeredPane.add(lbltaikham);
		lbltaikham1 = new JLabel("..."); lbltaikham1.setFont(fontData); lbltaikham1.setForeground(colText); lbltaikham1.setBounds(col1, infoY + 75, 200, 25); layeredPane.add(lbltaikham1);
		
	
        JLabel lTonKho = new JLabel("Tồn kho:");
        lTonKho.setFont(fontLabel);
        lTonKho.setForeground(colGray);
        lTonKho.setBounds(col2, infoY + 55, 150, 20); // Cột 2
        layeredPane.add(lTonKho);

        lblTonKho = new JLabel("...");
        lblTonKho.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTonKho.setForeground(new Color(220, 53, 69)); // Màu đỏ
        lblTonKho.setBounds(col2, infoY + 75, 150, 25);
        layeredPane.add(lblTonKho);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 180, 580, 340);
		scrollPane_1.setBorder(new LineBorder(colBorder));
		scrollPane_1.getViewport().setBackground(colWhite);
		layeredPane.add(scrollPane_1);
		
		table_2 = new JTable();
		styleTable(table_2); 
		table_2.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] { "Tên Thuốc", "Sáng", "Trưa", "Chiều", "Tối", "Số Ngày", "Cách dùng", "Thời điểm" }
		));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=1; i<=5; i++) {
			table_2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table_2.getColumnModel().getColumn(i).setPreferredWidth(40);
		}
		
		scrollPane_1.setViewportView(table_2);
		
		
	
        table_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table_2.getSelectedRow();

                    int maThuoc = listChiTietHienTai.get(row).getMaThuoc();
          
                    int slTon = khoBus.getSoLuongTon(maThuoc);
                    lblTonKho.setText(String.valueOf(slTon));
                
            }
        });
		
		int btnFY = 540;
		JButton btnNewButton = new JButton("Xác nhận cấp thuốc");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  int index = table.getSelectedRow();
                  if(index < 0) {
                      JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn thuốc!");
                      return;
                  }
                  
                  String trangThai = table.getValueAt(index, 6).toString();
                  if(trangThai.equals("Đã cấp thuốc")) {
                      JOptionPane.showMessageDialog(null, "Đơn thuốc này đã được cấp rồi!");
                      return;
                  }

                  boolean duThuoc = true;
                  for(CTToaThuoc ct : listChiTietHienTai) {
                      int sang = toInt(ct.getSang());
                      int trua = toInt(ct.getTrua());
                      int chieu = toInt(ct.getChieu());
                      int toi = toInt(ct.getToi());
                      int tongCan = (sang + trua + chieu + toi) * ct.getSoNgay();
                      int tonKho = khoBus.getSoLuongTon(ct.getMaThuoc());
                      
                      if(tonKho < tongCan) {
                          duThuoc = false;
                          JOptionPane.showMessageDialog(null, "Không đủ thuốc: " + ct.getTenThuoc() + 
                                  "\n(Cần: " + tongCan + ", Tồn: " + tonKho + ")");
                          return;
                      }
                  }
                  
                  if(duThuoc) {
                      KhoThuocDao dao = new KhoThuocDao();
                      for(CTToaThuoc ct : listChiTietHienTai) {
                          int sang = toInt(ct.getSang());
                          int trua = toInt(ct.getTrua());
                          int chieu = toInt(ct.getChieu());
                          int toi = toInt(ct.getToi());
                          int tongCan = (sang + trua + chieu + toi) * ct.getSoNgay();
                          
                          int tonHienTai = khoBus.getSoLuongTon(ct.getMaThuoc());
                          int tonMoi = tonHienTai - tongCan;
                          
              
                          dao.capNhatSoLuongTon(ct.getMaThuoc(), tonMoi);
                      }
                      
                      int id = (int) table.getValueAt(index, 0);
                      PhieuKhamBus bus = new PhieuKhamBus();
                      bus.updateTrangThaiHoanTatCapThuoc(id);
                      
                      JOptionPane.showMessageDialog(null, "Đã xác nhận cấp thuốc và trừ kho thành công!");
                      loadDataTable();
                      lblTonKho.setText("...");
                      ((DefaultTableModel) table_2.getModel()).setNumRows(0);
                  }
              }
        });
		styleButton(btnNewButton, colSuccess, colWhite);
		btnNewButton.setBounds(30, btnFY, 280, 45);
		layeredPane.add(btnNewButton);
		
		JButton btnInHan = new JButton("In hóa đơn");
		btnInHan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		 xuatPhieuCapThuocPDF();
			}
		});
		styleButton(btnInHan, new Color(13, 202, 240), colWhite); // Cyan
		btnInHan.setBounds(320, btnFY, 290, 45);
		layeredPane.add(btnInHan);
		
		loadDataTable();
	}

	
	
	   private int toInt(String s) {
	        try {
	            if(s == null || s.trim().isEmpty()) return 0;
	            return Integer.parseInt(s.replaceAll("[^0-9]", ""));
	        } catch (Exception e) { return 0; }
	    }
	// =========================================================================
    // HÀM IN PDF - PHIẾU CẤP THUỐC
    // =========================================================================
    private void xuatPhieuCapThuocPDF() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn thuốc để in!");
            return;
        }

        // Lấy thông tin cơ bản từ bảng danh sách
        String maPhieu = table.getValueAt(selectedRow, 0).toString();
        String tenBN = lbltenbn.getText();
        String sdt = lblsdt.getText();
        String bacSi = lbltennv.getText();
        String ngayTaiKham = lbltaikham1.getText();

        // 1. Chọn nơi lưu file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu Phiếu Cấp Thuốc");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setSelectedFile(new File("PhieuCapThuoc_" + maPhieu + ".pdf"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                // 2. Tạo Document
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // 3. Cấu hình Font Tiếng Việt
                String fontPath = "C:/Windows/Fonts/arial.ttf"; 
                BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                
                com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 20, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);
                com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);

                // 4. Header
                Paragraph pHeader = new Paragraph("PHÒNG KHÁM ĐA KHOA HẠNH PHÚC\nNhà Thuốc Đạt Chuẩn GPP", fontHeader);
                pHeader.setAlignment(Element.ALIGN_CENTER);
                document.add(pHeader);
                document.add(new Paragraph("\n"));

                Paragraph pTitle = new Paragraph("PHIẾU CẤP THUỐC / HÓA ĐƠN", fontTitle);
                pTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(pTitle);
                document.add(new Paragraph("\n"));

     
                document.add(new Paragraph("Mã phiếu: " + maPhieu, fontBold));
                document.add(new Paragraph("Bệnh nhân: " + tenBN + " - SĐT: " + sdt, fontNormal));
                document.add(new Paragraph("Bác sĩ kê đơn: " + bacSi, fontNormal));
                if (!ngayTaiKham.equals("...")) {
                    document.add(new Paragraph("Hẹn tái khám: " + ngayTaiKham, fontNormal));
                }
                document.add(new Paragraph("Ngày cấp: " + java.time.LocalDate.now(), fontNormal));
                
                document.add(new Paragraph("\n----------------------------------------------------------\n"));

      
                PdfPTable pdfTable = new PdfPTable(6); 
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[]{1, 6, 1, 1, 1, 1}); 

                // Header bảng
                addCell(pdfTable, "STT", fontBold);
                addCell(pdfTable, "Tên thuốc / Cách dùng", fontBold);
                addCell(pdfTable, "Sáng", fontBold);
                addCell(pdfTable, "Trưa", fontBold);
                addCell(pdfTable, "Chiều", fontBold);
                addCell(pdfTable, "Tối", fontBold);

 
                DefaultTableModel model = (DefaultTableModel) table_2.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    String tenThuoc = model.getValueAt(i, 0).toString();
                    String sang = model.getValueAt(i, 1).toString();
                    String trua = model.getValueAt(i, 2).toString();
                    String chieu = model.getValueAt(i, 3).toString();
                    String toi = model.getValueAt(i, 4).toString();
                    String soNgay = model.getValueAt(i, 5).toString();
                    String cachDung = model.getValueAt(i, 6).toString();
            
                    addCell(pdfTable, String.valueOf(i + 1), fontNormal);
                    
         
                    String content = tenThuoc + "\n(" + soNgay + " ngày) - " + cachDung;
                    PdfPCell cellTen = new PdfPCell(new Phrase(content, fontNormal));
                    cellTen.setPadding(5);
                    pdfTable.addCell(cellTen);
                    
             
                    addCell(pdfTable, sang, fontNormal);
                    addCell(pdfTable, trua, fontNormal);
                    addCell(pdfTable, chieu, fontNormal);
                    addCell(pdfTable, toi, fontNormal);
                }

                document.add(pdfTable);
                
                document.add(new Paragraph("\n"));
                
                // 7. Footer
                document.add(new Paragraph("Lưu ý: Uống thuốc đúng theo chỉ dẫn.", fontNormal));
                document.add(new Paragraph("Xin cảm ơn quý khách!", fontNormal));
                
                document.add(new Paragraph("\n\n"));
                Paragraph pSign = new Paragraph("Dược sĩ cấp phát\n(Ký tên)", fontBold);
                pSign.setAlignment(Element.ALIGN_RIGHT);
                pSign.setIndentationRight(50);
                document.add(pSign);

                document.close();
                
                JOptionPane.showMessageDialog(this, "Xuất PDF thành công!\n" + filePath);
                
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(filePath));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi in PDF: " + ex.getMessage());
            }
        }
    }
    
    // Hàm phụ trợ thêm ô vào bảng PDF
    private void addCell(PdfPTable table, String text, com.itextpdf.text.Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        table.addCell(cell);
    }
	
	// --- HÀM STYLE RIÊNG (ĐỂ CODE GỌN HƠN) ---
	private void styleButton(JButton btn, Color bg, Color fg) {
		btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn.setBackground(bg);
		btn.setForeground(fg);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setOpaque(true);
	}
	
	private void styleButton(JButton btn, Color bg) {
		styleButton(btn, bg, Color.WHITE);
	}
	
	private void styleTable(JTable table) {
		table.setRowHeight(35);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setGridColor(new Color(230, 230, 230));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(235, 245, 255));
		table.setSelectionForeground(Color.BLACK);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		header.setBackground(new Color(245, 245, 245));
		header.setForeground(new Color(70, 70, 70));
		header.setPreferredSize(new java.awt.Dimension(0, 35));
	}
	
    private JLabel createLabel(JPanel parent, String text, int x, int y, Color color, Font font) {
    	JLabel l = new JLabel(text);
        l.setBounds(x, y, 200, 20);
        l.setForeground(color);
        if(font != null) l.setFont(font);
        else l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        parent.add(l);
        return l;
    }
}