package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import BUS.ChiDinhBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import BUS.XetNghiemBus;
import MODEL.DanhSachChoCLS;
import MODEL.DuongdanFile;
import MODEL.KetQuaCDHA;
import MODEL.Session;

// --- IMPORT ITEXT PDF ---
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class FrmLoadDuLieuCDHA extends JPanel {

    private static final long serialVersionUID = 1L;

    private int idChiTietChiDinh;
    private String tenBenhNhan;
    private String tenDichVu;
    private boolean isUpdateMode = false;
    
    private JTextArea txtMoTa, txtKetLuan, txtDeNghi;
    

    private List<JTextField> listTxtAnh = new ArrayList<>();
    private JPanel pnlListFiles; 
    
    private JButton btnLuu, btnHuy, btnMauBinhThuong, btnAddImage, btnIn;
    private JLabel lblTenBN, lblTenDV;
    
    private String check;

    private ChiDinhBus chiDinhBus = new ChiDinhBus();

    private final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    private final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Color PRIMARY_COLOR = new Color(0, 153, 153);

    public FrmLoadDuLieuCDHA(int idChiTietChiDinh, String check) {
this.check = check;

    this.idChiTietChiDinh = idChiTietChiDinh;
//        XetNghiemBus bus = new XetNghiemBus();
//        this.idChiTietChiDinh = bus.layIdChiTietByMaPhieuKham(Session.maphieukham);
        
        
        DanhSachChoCLS info = chiDinhBus.getThongTinChiTietById(idChiTietChiDinh);
        
        if (info != null && info.getTenBenhNhan() != null) {
            this.tenBenhNhan = info.getTenBenhNhan();
            this.tenDichVu = info.getTenDichVu();
        } else {
            this.tenBenhNhan = "Không xác định";
            this.tenDichVu = "Không xác định";
            System.out.println("Lỗi: Không tìm thấy thông tin cho ID Kết Quả: " + idChiTietChiDinh);
        }
        
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(10, 20, 10, 20));

        initHeader();
        initBody();
        initFooter();
        
        checkAndLoadData();
        initEvents();
    }

    private void initHeader() {
        JPanel pnlHeader = new JPanel();
        pnlHeader.setLayout(new GridLayout(2, 1));
        pnlHeader.setOpaque(false);
        pnlHeader.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 2, 0, new Color(200, 200, 200)),
            new EmptyBorder(0, 0, 10, 0)
        ));

        lblTenBN = new JLabel("BỆNH NHÂN: " +      this.tenBenhNhan.toUpperCase());
        lblTenBN.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTenBN.setForeground(PRIMARY_COLOR);

        lblTenDV = new JLabel("DỊCH VỤ: " +    this.tenDichVu );
        lblTenDV.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenDV.setForeground(Color.DARK_GRAY);

        pnlHeader.add(lblTenBN);
        pnlHeader.add(lblTenDV);
        add(pnlHeader, BorderLayout.NORTH);
    }

    private void initBody() {
        JPanel pnlBody = new JPanel();
        pnlBody.setBackground(Color.WHITE);
        pnlBody.setLayout(new GridBagLayout());
        add(pnlBody, BorderLayout.CENTER);


        GridBagConstraints gbc_1 = new GridBagConstraints();
        gbc_1.fill = GridBagConstraints.BOTH;
        gbc_1.gridx = 0;
        gbc_1.gridy = 0;
        gbc_1.weightx = 1.0;
        gbc_1.insets = new Insets(5, 0, 5, 0);
        
        JPanel pnlLabelMoTa = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlLabelMoTa.setOpaque(false);
        JLabel lblTitleMoTa = new JLabel("MÔ TẢ HÌNH ẢNH:");
        lblTitleMoTa.setFont(FONT_LABEL);
        lblTitleMoTa.setForeground(PRIMARY_COLOR);
        pnlLabelMoTa.add(lblTitleMoTa);
        btnMauBinhThuong = new JButton("Mẫu bình thường");
        pnlLabelMoTa.add(Box.createHorizontalStrut(20));
        pnlLabelMoTa.add(btnMauBinhThuong);
        pnlBody.add(pnlLabelMoTa, gbc_1);

        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.fill = GridBagConstraints.BOTH;
        gbc_2.gridx = 0;
        gbc_2.gridy = 1;
        gbc_2.weighty = 0.3;
        txtMoTa = new JTextArea();
        txtMoTa.setFont(FONT_INPUT);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        pnlBody.add(new JScrollPane(txtMoTa), gbc_2);


        GridBagConstraints gbc_3 = new GridBagConstraints();
        gbc_3.fill = GridBagConstraints.BOTH;
        gbc_3.gridx = 0;
        gbc_3.gridy = 2;
        gbc_3.insets = new Insets(10, 0, 5, 0);
        JLabel lblTitleKetLuan = new JLabel("KẾT LUẬN:");
        lblTitleKetLuan.setFont(FONT_LABEL);
        lblTitleKetLuan.setForeground(PRIMARY_COLOR);
        pnlBody.add(lblTitleKetLuan, gbc_3);

        GridBagConstraints gbc_4 = new GridBagConstraints();
        gbc_4.fill = GridBagConstraints.BOTH;
        gbc_4.gridx = 0;
        gbc_4.gridy = 3;
        gbc_4.weighty = 0.2;
        txtKetLuan = new JTextArea();
        txtKetLuan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtKetLuan.setForeground(Color.BLUE);
        txtKetLuan.setLineWrap(true);
        pnlBody.add(new JScrollPane(txtKetLuan), gbc_4);


        GridBagConstraints gbc_5 = new GridBagConstraints();
        gbc_5.fill = GridBagConstraints.BOTH;
        gbc_5.gridx = 0;
        gbc_5.gridy = 4;
        gbc_5.insets = new Insets(10, 0, 5, 0);
        JLabel lblTitleDeNghi = new JLabel("ĐỀ NGHỊ:");
        lblTitleDeNghi.setFont(FONT_LABEL);
        lblTitleDeNghi.setForeground(PRIMARY_COLOR);
        pnlBody.add(lblTitleDeNghi, gbc_5);

        GridBagConstraints gbc_6 = new GridBagConstraints();
        gbc_6.fill = GridBagConstraints.BOTH;
        gbc_6.gridx = 0;
        gbc_6.gridy = 5;
        gbc_6.weighty = 0.1;
        txtDeNghi = new JTextArea();
        txtDeNghi.setFont(FONT_INPUT);
        pnlBody.add(new JScrollPane(txtDeNghi), gbc_6);


        GridBagConstraints gbc_7 = new GridBagConstraints();
        gbc_7.fill = GridBagConstraints.BOTH;
        gbc_7.gridx = 0;
        gbc_7.gridy = 6;
        gbc_7.insets = new Insets(10, 0, 5, 0);
        JPanel pnlImgHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlImgHeader.setOpaque(false);
        pnlImgHeader.add(new JLabel("HÌNH ẢNH KẾT QUẢ:"));
        btnAddImage = new JButton("+");
        btnAddImage.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddImage.setBackground(new Color(0, 102, 204));
        btnAddImage.setForeground(Color.WHITE);
        btnAddImage.setContentAreaFilled(false);
        btnAddImage.setOpaque(true);
        btnAddImage.setBorderPainted(false);
 
        pnlImgHeader.add(btnAddImage);
        pnlBody.add(pnlImgHeader, gbc_7);

        GridBagConstraints gbc_8 = new GridBagConstraints();
        gbc_8.fill = GridBagConstraints.BOTH;
        gbc_8.gridx = 0;
        gbc_8.gridy = 7;
        gbc_8.weighty = 0.3;
        pnlListFiles = new JPanel();
        pnlListFiles.setBackground(Color.WHITE);
        pnlListFiles.setLayout(new BoxLayout(pnlListFiles, BoxLayout.Y_AXIS));
        pnlBody.add(new JScrollPane(pnlListFiles), gbc_8);

    
        themDongChonAnh("");
        themDongChonAnh("");
    }

    private void themDongChonAnh(String duongDan) {
        JPanel pnlRow = new JPanel(new BorderLayout(10, 0));
        pnlRow.setOpaque(false);
        pnlRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        pnlRow.setBorder(new EmptyBorder(2, 5, 2, 5));


        JLabel lbl = new JLabel("Ảnh:");
        lbl.setPreferredSize(new Dimension(50, 25));
        
        JTextField txt = new JTextField(duongDan);
        txt.setEditable(false);
        txt.setBackground(Color.WHITE);
        txt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showImageDialog(txt.getText());
                }
            }
        });

        JButton btnChon = new JButton("Chọn");
        btnChon.setContentAreaFilled(false);
        btnChon.setOpaque(true);
        btnChon.setBorderPainted(false);

        btnChon.addActionListener(e -> chonAnh(txt));


        JButton btnXoa = new JButton("Xóa");
        btnXoa.setForeground(Color.RED);
        btnXoa.addActionListener(e -> {
            pnlListFiles.remove(pnlRow); 
            listTxtAnh.remove(txt);     
            pnlListFiles.revalidate();
            pnlListFiles.repaint();
        });


        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pnlButtons.setOpaque(false);
        pnlButtons.add(btnChon);
        pnlButtons.add(btnXoa);

        pnlRow.add(lbl, BorderLayout.WEST);
        pnlRow.add(txt, BorderLayout.CENTER);
        pnlRow.add(pnlButtons, BorderLayout.EAST);

        listTxtAnh.add(txt);
        pnlListFiles.add(pnlRow);
        

        pnlListFiles.revalidate();
        pnlListFiles.repaint();
    }

    private void initFooter() {
        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlFooter.setBackground(Color.WHITE);
        pnlFooter.setBorder(new MatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));

        btnHuy = new JButton("Đóng");
        btnHuy.setPreferredSize(new Dimension(100, 40));
        btnHuy.setContentAreaFilled(false);
        btnHuy.setOpaque(true);
        btnHuy.setBorderPainted(false);

        
        btnIn = new JButton("IN KẾT QUẢ (PDF)");
        btnIn.setBackground(new Color(255, 193, 7));
        btnIn.setForeground(Color.BLACK);
        btnIn.setPreferredSize(new Dimension(150, 40));
        btnIn.setContentAreaFilled(false);
        btnIn.setOpaque(true);
        btnIn.setBorderPainted(false);


        btnLuu = new JButton("LƯU KẾT QUẢ");
        btnLuu.setBackground(new Color(40, 167, 69));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLuu.setPreferredSize(new Dimension(180, 40));
        btnLuu.setContentAreaFilled(false);
        btnLuu.setOpaque(true);
        btnLuu.setBorderPainted(false);
        if(this.check.equalsIgnoreCase("check")) {
        btnLuu.setEnabled(false);	
        }


        pnlFooter.add(btnHuy);
        pnlFooter.add(btnIn);
        pnlFooter.add(btnLuu);
        add(pnlFooter, BorderLayout.SOUTH);
    }

    private void initEvents() {
        btnAddImage.addActionListener(e -> themDongChonAnh(""));

        btnMauBinhThuong.addActionListener(e -> {
            txtMoTa.setText("- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.");
            txtKetLuan.setText("Hiện tại chưa phát hiện bất thường.");
        });

        btnHuy.addActionListener(e -> {
            Window win = SwingUtilities.getWindowAncestor(this);
            if (win != null) win.dispose();
        });

        btnLuu.addActionListener(e -> saveAction());
        
        btnIn.addActionListener(e -> xuatPDF());
    }

    private void saveAction() {
        if (txtMoTa.getText().trim().isEmpty() || txtKetLuan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mô tả và Kết luận!");
            return;
        }

        // Gộp tất cả ảnh vào 1 chuỗi ngăn cách bằng ";"
        StringBuilder sb = new StringBuilder();
        for (JTextField tf : listTxtAnh) {
            String path = processFile(tf.getText());
            if (path != null) {
                if (sb.length() > 0) sb.append(";");
                sb.append(path);
            }
        }

        boolean kq = isUpdateMode ? 
            chiDinhBus.BScapNhatKetQuaDaCo(idChiTietChiDinh, txtMoTa.getText(), txtKetLuan.getText(), 
                                         txtDeNghi.getText(), Session.maNhanVien, sb.toString(), null) :
            chiDinhBus.BSluuKetQuaCDHAMoi(idChiTietChiDinh, txtMoTa.getText(), txtKetLuan.getText(), 
                                         txtDeNghi.getText(), Session.maNhanVien, sb.toString(), null);
        
        if (kq) {
            JOptionPane.showMessageDialog(this, "Lưu kết quả thành công!");
            new PhieuKhamBus().updateTrangThaiDAKHAMChiDinhCLS(Session.maphieukham);
            PhieuKhamBus bus2 = new PhieuKhamBus();
            bus2.updateTrangThaiDAKHAMTrongChiDinhCLS(Session.maphieukham);
            
    
        	DangKyKhamBenhBus bus1 = new DangKyKhamBenhBus();
       
    		bus1.capNhatDaKhamById(Session.maDangKyHienTai);
        }
    }

    private void showImageDialog(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty() || !new File(imagePath).exists()) {
            JOptionPane.showMessageDialog(this, "Ảnh không tồn tại hoặc chưa chọn ảnh!");
            return;
        }
        
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Xem ảnh chi tiết", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(900, 700);
        dialog.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        
    
        if (img.getWidth(null) > 850 || img.getHeight(null) > 600) {
            double ratio = Math.min(850.0/img.getWidth(null), 600.0/img.getHeight(null));
            img = img.getScaledInstance((int)(img.getWidth(null)*ratio), (int)(img.getHeight(null)*ratio), Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }
        
        JLabel lblImg = new JLabel(icon);
        dialog.getContentPane().add(new JScrollPane(lblImg));
        dialog.setVisible(true);
    }
    public String getKetLuanTuForm() {
        return txtKetLuan.getText().trim();
    }
    private void xuatPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("KQ_" + tenBenhNhan.replace(" ", "_") + ".pdf"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) filePath += ".pdf";
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();
                BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                com.itextpdf.text.Font fBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font fNorm = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.NORMAL);

                document.add(new Paragraph("PHIẾU KẾT QUẢ CHẨN ĐOÁN HÌNH ẢNH", fBold));
                document.add(new Paragraph("Bệnh nhân: " + tenBenhNhan.toUpperCase(), fNorm));
                document.add(new Paragraph("\n1. MÔ TẢ:\n" + txtMoTa.getText(), fNorm));
                document.add(new Paragraph("\n2. KẾT LUẬN:\n" + txtKetLuan.getText(), fBold));

                document.add(new Paragraph("\n3. HÌNH ẢNH KÈM THEO:\n", fBold));
                for (JTextField tf : listTxtAnh) {
                    String p = tf.getText().trim();
                    if (!p.isEmpty() && new File(p).exists()) {
                        com.itextpdf.text.Image pdfImg = com.itextpdf.text.Image.getInstance(p);
                        pdfImg.scaleToFit(250f, 250f);
                        document.add(pdfImg);
                        document.add(new Paragraph(" "));
                    }
                }
                document.close();
                Desktop.getDesktop().open(new File(filePath));
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    private void checkAndLoadData() {
    
        if (  this.idChiTietChiDinh <= 0) return;
        KetQuaCDHA kq = chiDinhBus.layKetQuaTheoIdChiTiet(  this.idChiTietChiDinh); 
        if (kq != null) {
            isUpdateMode = true; 
            txtMoTa.setText(kq.getMoTa()); txtKetLuan.setText(kq.getKetLuan()); txtDeNghi.setText(kq.getDeNghi());
            pnlListFiles.removeAll(); 
            listTxtAnh.clear();
            if (kq.getAnh1() != null && !kq.getAnh1().isEmpty()) {
                String[] paths = kq.getAnh1().split(";");
                for (String p : paths)
                themDongChonAnh(p);
            }
            btnLuu.setText("CẬP NHẬT KẾT QUẢ");
        }
    }

    private void chonAnh(JTextField txt) {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            txt.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }

    private String processFile(String rawPath) {
        if(rawPath == null || rawPath.trim().isEmpty()) return null;
        if (!rawPath.contains(":")) return rawPath; 
        File f = new File(rawPath);
        return f.exists() ? DuongdanFile.luuFileAnh(f) : null;
    }
}