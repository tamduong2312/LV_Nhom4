package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.toedter.calendar.JDateChooser;

import BUS.BenhNhanBus;
import MODEL.BenhNhan;

public class FrmQlbenhnhancuaLetan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    

    private JTextField txtten;
    private JTextField txtcccd;
    private JTextField txtdiachi;
    private JTextField txtsdt;
    private JTextField txtemail;
    private JTextField txtnhommau;
    private JTextField txtdiung;
    private JTextField txttimkiem;
    private JTextField txtnguoigiamho;
    private JTextField txtsdtnguoigiamho;
    private JTextField txtnghenghiep;
    private JTextField txtghichu;
    
    private JTable table;
    private JComboBox<String> cmbngaysinh; 
    private JDateChooser dateChooserNgaySinh;
    
    private List<BenhNhan> listbenhnhen = new ArrayList<>();
    private BenhNhanBus benhnhanbus = new BenhNhanBus();
    private int index;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
                    FrmQlbenhnhancuaLetan frame = new FrmQlbenhnhancuaLetan();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void loadDataTable() {
        listbenhnhen = benhnhanbus.getAllBN();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (BenhNhan nd : listbenhnhen) {
            model.addRow(new Object[] {
                nd.getMaBenhNhan(), nd.getHoTen(), nd.GioiTinh(), nd.getNgaySinh(), nd.getDiaChi(), 
                nd.getSDT(), nd.getEmail(), nd.getNgheNghiep(), nd.getNhomMau(),
                nd.getDiUngThuoc(), nd.getNguoiGiamHo(), nd.getSDTNguoiGiamHo(), 
                nd.getGhiChu(), nd.getCCCD()
            });
        }
    }

    private void loadDataTableTIMKIEMBN(String id) {
        listbenhnhen = benhnhanbus.TimkiemBN(id);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (BenhNhan nd : listbenhnhen) {
            model.addRow(new Object[] {
                nd.getMaBenhNhan(), nd.getHoTen(), nd.GioiTinh(), nd.getNgaySinh(), nd.getDiaChi(), 
                nd.getSDT(), nd.getEmail(), nd.getNgheNghiep(), nd.getNhomMau(),
                nd.getDiUngThuoc(), nd.getNguoiGiamHo(), nd.getSDTNguoiGiamHo(), 
                nd.getGhiChu(), nd.getCCCD()
            });
        }
    }

    private String getString(Object value) {
        return value == null ? "" : value.toString();
    }

    private long getLong(Object value) {
        try {
            return value == null ? 0 : Long.parseLong(value.toString());
        } catch(Exception e) { return 0; }
    }

    /**
     * Create the frame.
     */
    public FrmQlbenhnhancuaLetan() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Locale.setDefault(new Locale("vi", "VN"));
        setTitle("Quản Lý Bệnh Nhân - Lễ Tân");
        setBounds(100, 100, 1200, 760);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 245, 250));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(0, 123, 255));
        pnlHeader.setPreferredSize(new Dimension(100, 60));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        contentPane.add(pnlHeader, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("QUẢN LÝ HỒ SƠ BỆNH NHÂN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);


        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(240, 245, 250));
        pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlCenter.setLayout(new BorderLayout(10, 10));
        contentPane.add(pnlCenter, BorderLayout.CENTER);

        // 1. INPUT FORM
        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new Color(0, 123, 255), 1, true),
                "Thông tin chi tiết", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new Font("Segoe UI", Font.BOLD, 14), 
                new Color(0, 123, 255)
        ));
        pnlCenter.add(pnlInput, BorderLayout.NORTH);

        // Init Components
        Font fontLbl = new Font("Segoe UI", Font.PLAIN, 13);
        Font fontTxt = new Font("Segoe UI", Font.PLAIN, 14);

        txtten = new JTextField(); txtten.setFont(fontTxt);
        txtcccd = new JTextField(); txtcccd.setFont(fontTxt);
        txtsdt = new JTextField(); txtsdt.setFont(fontTxt);
        txtemail = new JTextField(); txtemail.setFont(fontTxt);
        txtnhommau = new JTextField(); txtnhommau.setFont(fontTxt);
        txtghichu = new JTextField(); txtghichu.setFont(fontTxt);
        
        cmbngaysinh = new JComboBox<>(new String[] {"Nam", "Nữ"}); 
        cmbngaysinh.setFont(fontTxt);
        
        dateChooserNgaySinh = new JDateChooser();
        dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
        
        txtdiachi = new JTextField(); txtdiachi.setFont(fontTxt);
        txtnghenghiep = new JTextField(); txtnghenghiep.setFont(fontTxt);
        txtdiung = new JTextField(); txtdiung.setFont(fontTxt);
        txtnguoigiamho = new JTextField(); txtnguoigiamho.setFont(fontTxt);
        txtsdtnguoigiamho = new JTextField(); txtsdtnguoigiamho.setFont(fontTxt);

        addInput(pnlInput, createLabel("Họ tên:", fontLbl), 0, 0);
        addInput(pnlInput, txtten, 1, 0);
        
        addInput(pnlInput, createLabel("CCCD:", fontLbl), 0, 1);
        addInput(pnlInput, txtcccd, 1, 1);
        
        addInput(pnlInput, createLabel("SĐT:", fontLbl), 0, 2);
        addInput(pnlInput, txtsdt, 1, 2);
        
        addInput(pnlInput, createLabel("Email:", fontLbl), 0, 3);
        addInput(pnlInput, txtemail, 1, 3);
        
        addInput(pnlInput, createLabel("Nhóm máu:", fontLbl), 0, 4);
        addInput(pnlInput, txtnhommau, 1, 4);
        
        addInput(pnlInput, createLabel("Ghi chú:", fontLbl), 0, 5);
        addInput(pnlInput, txtghichu, 1, 5);

        // Cột phải
        addInput(pnlInput, createLabel("Giới tính:", fontLbl), 2, 0);
        addInput(pnlInput, cmbngaysinh, 3, 0); 
        
        addInput(pnlInput, createLabel("Ngày sinh:", fontLbl), 2, 1);
        addInput(pnlInput, dateChooserNgaySinh, 3, 1);
        
        addInput(pnlInput, createLabel("Địa chỉ:", fontLbl), 2, 2);
        addInput(pnlInput, txtdiachi, 3, 2);
        
        addInput(pnlInput, createLabel("Nghề nghiệp:", fontLbl), 2, 3);
        addInput(pnlInput, txtnghenghiep, 3, 3);
        
        addInput(pnlInput, createLabel("Dị ứng thuốc:", fontLbl), 2, 4);
        addInput(pnlInput, txtdiung, 3, 4);
        

        addInput(pnlInput, createLabel("Người giám hộ:", fontLbl), 0, 6);
        addInput(pnlInput, txtnguoigiamho, 1, 6);
        
        addInput(pnlInput, createLabel("SĐT Giám hộ:", fontLbl), 2, 6);
        addInput(pnlInput, txtsdtnguoigiamho, 3, 6);


        JPanel pnlActions = new JPanel(new BorderLayout());
        pnlActions.setOpaque(false);
        
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSearch.setOpaque(false);
        

        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(fontLbl);
        pnlSearch.add(lblSearch);
        
        txttimkiem = new JTextField(20);
        txttimkiem.setFont(fontTxt);
        pnlSearch.add(txttimkiem);
        
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setOpaque(false);
        
        JButton btnThemNV = createStyledButton("Thêm BN", new Color(0, 123, 255));
        JButton btnSuaNV = createStyledButton("Sửa BN", new Color(0, 200, 83));
        JButton btnXoaNV = createStyledButton("Xóa BN", new Color(220, 53, 69));
        JButton btnInPhiu = createStyledButton("In Phiếu", new Color(255, 193, 7));
        btnInPhiu.setForeground(Color.BLACK);
        JButton btnQuayTrLi = createStyledButton("Thoát", new Color(108, 117, 125));

        pnlButtons.add(btnThemNV);
        pnlButtons.add(btnSuaNV);
        pnlButtons.add(btnXoaNV);
        pnlButtons.add(btnInPhiu);
        pnlButtons.add(btnQuayTrLi);
        
        pnlActions.add(pnlSearch, BorderLayout.WEST);
        pnlActions.add(pnlButtons, BorderLayout.EAST);

        // 3. TABLE
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(227, 242, 253));
        table.setSelectionForeground(Color.BLACK);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(Color.WHITE);
        
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Mã BN", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Email", 
                "Nghề nghiệp", "Nhóm máu", "Dị ứng", "Người giám hộ", "SĐT GH", "Ghi chú", "CCCD"
            }
        ));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
 
        JPanel pnlBottom = new JPanel(new BorderLayout(0, 10));
        pnlBottom.setOpaque(false);
        pnlBottom.add(pnlActions, BorderLayout.NORTH);
        pnlBottom.add(scrollPane, BorderLayout.CENTER);
        
        pnlCenter.add(pnlBottom, BorderLayout.CENTER);



        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index == -1) return;


                String hoTen           = getString(table.getValueAt(index, 1));
                String gioiTinh        = getString(table.getValueAt(index, 2));
                String ngaySinh        = getString(table.getValueAt(index, 3));
                String diaChi          = getString(table.getValueAt(index, 4));
                long soDT              = getLong(table.getValueAt(index, 5));
                String email           = getString(table.getValueAt(index, 6));
                String ngheNghiep      = getString(table.getValueAt(index, 7));
                String nhomMau         = getString(table.getValueAt(index, 8));
                String diUngThuoc      = getString(table.getValueAt(index, 9));
                String nguoiGiamHo     = getString(table.getValueAt(index, 10));
                long sdtNguoiGiamHo    = getLong(table.getValueAt(index, 11));
                String ghiChu          = getString(table.getValueAt(index, 12));
                long cccd              = getLong(table.getValueAt(index, 13));

                txtten.setText(hoTen);
                txtcccd.setText(cccd + "");
                txtdiachi.setText(diaChi);
                txtsdt.setText("0" + soDT); 
                if(soDT == 0) txtsdt.setText("");
                
                txtemail.setText(email);
                txtnghenghiep.setText(ngheNghiep);
                txtnhommau.setText(nhomMau);
                txtdiung.setText(diUngThuoc);
                txtnguoigiamho.setText(nguoiGiamHo);
                txtsdtnguoigiamho.setText("0" + sdtNguoiGiamHo);
                if(sdtNguoiGiamHo == 0) txtsdtnguoigiamho.setText("");
                
                txtghichu.setText(ghiChu);

                cmbngaysinh.setSelectedItem(gioiTinh);

                try {
                    java.util.Date ns = java.sql.Date.valueOf(ngaySinh);
                    dateChooserNgaySinh.setDate(ns);
                } catch (Exception ex) {
                    dateChooserNgaySinh.setDate(null);
                }
            }
        });
////// nút thêm ////////////
        btnThemNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ten = txtten.getText().toString();
                String nhommau = txtnhommau.getText().toString();
                String diachi = txtdiachi.getText().toString();
                String nguoigiamho = txtnguoigiamho.getText();
                String email = txtemail.getText();
                String diungthuoc = txtdiung.getText();
                String ghichu = txtghichu.getText().toString();
                String nghenghiep = txtnghenghiep.getText();
                String cccdStr = txtcccd.getText().trim();
                String sdtStr = txtsdt.getText().trim();
                String SDTnguoigiamhoStr = txtsdtnguoigiamho.getText().trim();
                if (!sdtStr.matches("0\\d{9}")) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ (Phải 10 số, bắt đầu là 0)!",
                            "Lỗi SĐT", JOptionPane.WARNING_MESSAGE);
                    txtsdt.requestFocus();
                    return;
                }
                
                
                if (!cccdStr.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(null, "CCCD phải là chuỗi số gồm 12 chữ số!", "Lỗi CCCD",
                            JOptionPane.WARNING_MESSAGE);
                    txtcccd.requestFocus();
                    return;
                }
                if(!SDTnguoigiamhoStr.isEmpty()) {
                if (!SDTnguoigiamhoStr.matches("0\\d{9}") ) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ (Phải 10 số, bắt đầu là 0)!",
                            "Lỗi SĐT", JOptionPane.WARNING_MESSAGE);
                    txtsdtnguoigiamho.requestFocus();
                    return;
                }
                }
                
                if(dateChooserNgaySinh.getDate() == null) {
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn ngày sinh");
                    return;
                }
                
                LocalDate ngaySinh = dateChooserNgaySinh.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                
                String selected = cmbngaysinh.getSelectedItem().toString();
                boolean GT;
                switch (selected) {
                    case "Nam":  GT = true; break;
                    case "Nữ":   GT = false; break;
                    default:     GT = false;
                }
                
                long cccd = Long.parseLong(cccdStr);
                long sdt = Long.parseLong(sdtStr);
                long SDTnguoigiamho = Long.parseLong(SDTnguoigiamhoStr);
                BenhNhan bn =  new BenhNhan(ten,ngaySinh,diachi,sdt,email,nghenghiep,nhommau,diungthuoc,nguoigiamho,SDTnguoigiamho,ghichu,cccd,GT);
                benhnhanbus.ThemBN(bn);
                loadDataTable();
                JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thành công");
            }
        });
/////////////// nút xóa ////////////////
        btnXoaNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  try {
                        index = table.getSelectedRow();
                        if (index != -1) {
                            int id = (int) table.getValueAt(index, 0);  

                            int confirm = JOptionPane.showConfirmDialog(
                                    null,
                                    "Bạn có chắc chắn muốn xóa bệnh nhân này không?",
                                    "Xác nhận xóa",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE
                            );

                            if (confirm == JOptionPane.YES_OPTION) {
                                benhnhanbus.xoaBN(id);
                                JOptionPane.showMessageDialog(null, "Đã xóa bệnh nhân thành công!");
                                loadDataTable();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân cần xóa!");
                        }
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, "Xóa bệnh nhân không thành công: " + e2.getMessage());
                    }
            }
        });

        btnSuaNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                index = table.getSelectedRow();
                if(index == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân cần sửa!");
                    return;
                }
                int id = (int) table.getValueAt(index, 0);
                
                String ten = txtten.getText().toString();
                String nhommau = txtnhommau.getText().toString();
                String diachi = txtdiachi.getText().toString();
                String nguoigiamho = txtnguoigiamho.getText();
                String email = txtemail.getText();
                String diungthuoc = txtdiung.getText();
                String ghichu = txtghichu.getText().toString();
                String nghenghiep = txtnghenghiep.getText();
                long SDT = 0;
                try {
                    SDT = Long.parseLong(txtsdt.getText().trim());
                } catch (Exception e2) {
                     JOptionPane.showMessageDialog(null,"Số điện thoại phải là số");
                     return;
                }
                long cccd = 0;
                try {
                    cccd = Long.parseLong(txtcccd.getText().trim());
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null,"CCCD phải là số");
                    return;
                }
                
                long SDTnguoigiamho = 0;
                try {
                    String sdtGH = txtsdtnguoigiamho.getText().trim();
                    if(!sdtGH.isEmpty())
                         SDTnguoigiamho = Long.parseLong(sdtGH);
                } catch (Exception e2) {
                }
                
                if(dateChooserNgaySinh.getDate() == null) {
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn ngày sinh");
                    return;
                }

                LocalDate ngaySinh = dateChooserNgaySinh.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                
                String selected = cmbngaysinh.getSelectedItem().toString();
                boolean GT;
                switch (selected) {
                    case "Nam":  GT = true; break;
                    case "Nữ":   GT = false; break;
                    default:     GT = false;
                }
                BenhNhan bn =  new BenhNhan(ten,ngaySinh,diachi,SDT,email,nghenghiep,nhommau,diungthuoc,nguoigiamho,SDTnguoigiamho,ghichu,cccd,GT);
                benhnhanbus.SuaBN(bn, id);
                loadDataTable();
                JOptionPane.showMessageDialog(null, "Sửa bệnh nhân thành công");
            }
        });

        txttimkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                 String text = txttimkiem.getText().trim();
                    if (text.isEmpty()) {
                        loadDataTable(); 
                    } else {
                        try {
                            loadDataTableTIMKIEMBN(text);
                        } catch (NumberFormatException ex) {
                        }
                    }
            }
        });

        btnQuayTrLi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmLeTan ql = new FrmLeTan();
                ql.setVisible(true);
                dispose();
            }
        });

        btnInPhiu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân để in phiếu!");
                    return;
                }

                try {
                    String maBN           = getString(table.getValueAt(index, 0));
                    String hoTen          = getString(table.getValueAt(index, 1));
                    String gioiTinh       = getString(table.getValueAt(index, 2));
                    String ngaySinh       = getString(table.getValueAt(index, 3));
                    String diaChi         = getString(table.getValueAt(index, 4));
                    String sdt            = getString(table.getValueAt(index, 5));
                    String email          = getString(table.getValueAt(index, 6));
                    String ngheNghiep     = getString(table.getValueAt(index, 7));
                    String nhomMau        = getString(table.getValueAt(index, 8));
                    String diUng          = getString(table.getValueAt(index, 9));
                    String nguoiGiamHo    = getString(table.getValueAt(index, 10));
                    String sdtNguoiGiamHo = getString(table.getValueAt(index, 11));
                    String ghiChu         = getString(table.getValueAt(index, 12));
                    String cccd           = getString(table.getValueAt(index, 13));

                    String folderPath = "src/pdf/";
                    File folder = new File(folderPath);
                    if(!folder.exists()) folder.mkdirs();
             
                    String filePath = folderPath +"phieu_benh_nhan_" + maBN + ".pdf";
                    Document pdf = new Document();
                    PdfWriter.getInstance(pdf, new FileOutputStream(filePath));
                    pdf.open();

   
                    BaseFont bf;
                    try {
                         bf = BaseFont.createFont("src/font/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                    } catch (Exception exFont) {
                        // Fallback nếu không có font
                        bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
                    }
                    
                    com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(bf, 20, com.itextpdf.text.Font.BOLD);
                    com.itextpdf.text.Font contentFont = new com.itextpdf.text.Font(bf, 12);

                    Paragraph title = new Paragraph("PHIẾU THÔNG TIN BỆNH NHÂN", titleFont);
                    title.setAlignment(Element.ALIGN_CENTER);
                    title.setSpacingAfter(20);
                    pdf.add(title);

                    BiConsumer<String[], String[]> addLine = (left, right) -> {
                        Paragraph p = new Paragraph();
                        p.setFont(contentFont);
                        String leftText = left[0] + " " + (left[1].isEmpty() ? "..." : left[1]);
                        String rightText = right[0] + " " + (right[1].isEmpty() ? "..." : right[1]);

                        Chunk leftChunk = new Chunk(leftText, contentFont);
                        Chunk rightChunk = new Chunk(rightText, contentFont);
                        
                        p.add(leftChunk);
                        p.add(new Chunk(new VerticalPositionMark()));
                        p.add(rightChunk);
                        try {
                            pdf.add(p);
                            pdf.add(new Paragraph(" ")); // Spacer
                        } catch (DocumentException e1) {
                            e1.printStackTrace();
                        }
                    };

                    addLine.accept(new String[]{"Mã bệnh nhân:", maBN}, new String[]{"Giới tính:", gioiTinh});
                    addLine.accept(new String[]{"Họ tên:", hoTen}, new String[]{"Ngày sinh:", ngaySinh});
                    addLine.accept(new String[]{"CCCD:", cccd}, new String[]{"Nhóm máu:", nhomMau});
                    addLine.accept(new String[]{"Địa chỉ:", diaChi}, new String[]{"Nghề nghiệp:", ngheNghiep});
                    addLine.accept(new String[]{"SĐT:", sdt}, new String[]{"Email:", email});
                    addLine.accept(new String[]{"Dị ứng thuốc:", diUng}, new String[]{"Người giám hộ:", nguoiGiamHo});
                    addLine.accept(new String[]{"SĐT người giám hộ:", sdtNguoiGiamHo}, new String[]{"Ghi chú:", ghiChu});

                    pdf.close();
                    File pdfFile = new File(filePath);

                    if(pdfFile.exists()) {
                        Desktop.getDesktop().open(pdfFile); 
                    }
                    JOptionPane.showMessageDialog(null, "Xuất PDF thành công!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        loadDataTable();
    }
    

    private void addInput(JPanel p, JComponent c, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; 
        gbc.gridy = y;
        gbc.insets = new Insets(5, 10, 5, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
    
        if(x % 2 != 0) {
            gbc.weightx = 1.0; 
        } else {
            gbc.weightx = 0.0;
        }
        
        p.add(c, gbc);
    }
    
    private JLabel createLabel(String text, Font font) {

        JLabel labelMoi = new JLabel(text);
        labelMoi.setFont(font);
        return labelMoi;
    }
    
    private JButton createStyledButton(String text, Color bg) {
        RoundedButton btn = new RoundedButton(text, bg);
        btn.setPreferredSize(new Dimension(110, 35));
        return btn;
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
            
            if (getModel().isPressed()) {
                g2.setColor(backgroundColor.darker());
            } else {
                g2.setColor(backgroundColor);
            }
            
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}