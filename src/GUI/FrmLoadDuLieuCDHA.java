package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;

import BUS.ChiDinhBus;
import BUS.PhieuKhamBus;
import MODEL.DanhSachChoCLS;
import MODEL.DuongdanFile;
import MODEL.KetQuaCDHA;
import MODEL.Session;

public class FrmLoadDuLieuCDHA extends JPanel {

    private static final long serialVersionUID = 1L;

    private int idChiTietChiDinh;
    private String tenBenhNhan;
    private String tenDichVu;
    private boolean isUpdateMode = false;
    
 
    private JTextArea txtMoTa, txtKetLuan, txtDeNghi;
    private JTextField txtFileAnh1, txtFileAnh2;
    private JButton btnLuu, btnHuy, btnChonAnh1, btnChonAnh2, btnMauBinhThuong;
    private JLabel lblTenBN, lblTenDV;
    

    private ChiDinhBus chiDinhBus = new ChiDinhBus();


    private final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    private final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Color PRIMARY_COLOR = new Color(0, 153, 153);

    public FrmLoadDuLieuCDHA(int idChiTiet) {
        this.idChiTietChiDinh = idChiTiet;
        DanhSachChoCLS info = chiDinhBus.getThongTinChiTietById(idChiTiet);
        
        if (info != null && info.getTenBenhNhan() != null) {
            this.tenBenhNhan = info.getTenBenhNhan();
            this.tenDichVu = info.getTenDichVu();
        } else {
            this.tenBenhNhan = "Không xác định";
            this.tenDichVu = "Không xác định";
            System.out.println("Lỗi: Không tìm thấy thông tin cho ID Kết Quả: " + idChiTiet);
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
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1));
        pnlHeader.setOpaque(false);
        pnlHeader.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 2, 0, new Color(200, 200, 200)),
            new EmptyBorder(0, 0, 10, 0)
        ));

        lblTenBN = new JLabel("BỆNH NHÂN: " + tenBenhNhan.toUpperCase());
        lblTenBN.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTenBN.setForeground(PRIMARY_COLOR);

        lblTenDV = new JLabel("DỊCH VỤ: " + tenDichVu);
        lblTenDV.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenDV.setForeground(Color.DARK_GRAY);

        pnlHeader.add(lblTenBN);
        pnlHeader.add(lblTenDV);
        add(pnlHeader, BorderLayout.NORTH);
    }

    private void initBody() {
        JPanel pnlBody = new JPanel(new GridBagLayout());
        pnlBody.setBackground(Color.WHITE);
        
   
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0; gbc1.gridy = 0; 
        gbc1.weightx = 1.0; gbc1.weighty = 0.0;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(5, 0, 5, 0);
        
        JPanel pnlLabelMoTa = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlLabelMoTa.setOpaque(false);
        
    
        JLabel lblTitleMoTa = new JLabel("MÔ TẢ HÌNH ẢNH:");
        lblTitleMoTa.setFont(FONT_LABEL);
        lblTitleMoTa.setForeground(PRIMARY_COLOR);
        pnlLabelMoTa.add(lblTitleMoTa);
        
        btnMauBinhThuong = new JButton("Mẫu bình thường");
        btnMauBinhThuong.setMargin(new Insets(2, 10, 2, 10));
        btnMauBinhThuong.setFocusPainted(false);
        btnMauBinhThuong.setBackground(new Color(240, 240, 240));
        pnlLabelMoTa.add(Box.createHorizontalStrut(20));
        pnlLabelMoTa.add(btnMauBinhThuong);
        
        pnlBody.add(pnlLabelMoTa, gbc1);


        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0; gbc2.gridy = 1; 
        gbc2.weightx = 1.0; gbc2.weighty = 0.4; 
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.insets = new Insets(0, 0, 10, 0);
        
        txtMoTa = new JTextArea();
        txtMoTa.setFont(FONT_INPUT);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        pnlBody.add(new JScrollPane(txtMoTa), gbc2);

   
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0; gbc3.gridy = 2; 
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(5, 0, 5, 0);
        

        JLabel lblTitleKetLuan = new JLabel("KẾT LUẬN:");
        lblTitleKetLuan.setFont(FONT_LABEL);
        lblTitleKetLuan.setForeground(PRIMARY_COLOR);
        pnlBody.add(lblTitleKetLuan, gbc3);


        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0; gbc4.gridy = 3; 
        gbc4.weightx = 1.0; gbc4.weighty = 0.2; 
        gbc4.fill = GridBagConstraints.BOTH;
        gbc4.insets = new Insets(0, 0, 10, 0);
        
        txtKetLuan = new JTextArea();
        txtKetLuan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtKetLuan.setForeground(Color.BLUE);
        txtKetLuan.setLineWrap(true);
        txtKetLuan.setWrapStyleWord(true);
        pnlBody.add(new JScrollPane(txtKetLuan), gbc4);
        

        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridx = 0; gbc5.gridy = 4; 
        gbc5.fill = GridBagConstraints.HORIZONTAL;
        gbc5.insets = new Insets(5, 0, 5, 0);
        
    
        JLabel lblTitleDeNghi = new JLabel("ĐỀ NGHỊ (Nếu có):");
        lblTitleDeNghi.setFont(FONT_LABEL);
        lblTitleDeNghi.setForeground(PRIMARY_COLOR);
        pnlBody.add(lblTitleDeNghi, gbc5);

        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.gridx = 0; gbc6.gridy = 5; 
        gbc6.weightx = 1.0; gbc6.weighty = 0.1; 
        gbc6.fill = GridBagConstraints.BOTH;
        gbc6.insets = new Insets(0, 0, 10, 0);
        
        txtDeNghi = new JTextArea();
        txtDeNghi.setFont(FONT_INPUT);
        pnlBody.add(new JScrollPane(txtDeNghi), gbc6);


        GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.gridx = 0; gbc7.gridy = 6; 
        gbc7.fill = GridBagConstraints.HORIZONTAL;
        gbc7.weighty = 0.0;
        
        JPanel pnlFiles = new JPanel(new GridLayout(1, 2, 20, 0));
        pnlFiles.setOpaque(false);
        

        JPanel p1 = new JPanel(new BorderLayout(5, 0)); p1.setOpaque(false);
        p1.add(new JLabel("Ảnh 1:"), BorderLayout.WEST);
        txtFileAnh1 = new JTextField(); 
        txtFileAnh1.setEditable(false);
        txtFileAnh1.setBackground(Color.WHITE);
        p1.add(txtFileAnh1, BorderLayout.CENTER);
        
        txtFileAnh1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Click đúp để xem
                    showImageDialog(txtFileAnh1.getText());
                }
            }
        });
        // Thêm cursor hình bàn tay để biết là bấm được
        txtFileAnh1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtFileAnh1.setToolTipText("Click đúp để xem ảnh");

    
        JPanel p2 = new JPanel(new BorderLayout(5, 0)); p2.setOpaque(false);
        p2.add(new JLabel("Ảnh 2:"), BorderLayout.WEST);
        txtFileAnh2 = new JTextField(); 
        txtFileAnh2.setEditable(false); 
        txtFileAnh2.setBackground(Color.WHITE);
        p2.add(txtFileAnh2, BorderLayout.CENTER);


        
        pnlFiles.add(p1);
        pnlFiles.add(p2);
        pnlBody.add(pnlFiles, gbc7);

        add(pnlBody, BorderLayout.CENTER);
    }

    private void initFooter() {
        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlFooter.setBackground(Color.WHITE);
        pnlFooter.setBorder(new MatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));


        add(pnlFooter, BorderLayout.SOUTH);
    }
    
    private void checkAndLoadData() {
        KetQuaCDHA kq = chiDinhBus.layKetQuaTheoId(idChiTietChiDinh); 
        
        if (kq != null) {
            isUpdateMode = true; 
            
            txtMoTa.setText(kq.getMoTa());
            txtKetLuan.setText(kq.getKetLuan());
            txtDeNghi.setText(kq.getDeNghi());
            
            if (kq.getAnh1() != null) txtFileAnh1.setText(kq.getAnh1());
            if (kq.getAnh2() != null) txtFileAnh2.setText(kq.getAnh2());
            
 
        }
    }
    
 // Hàm này mở một cửa sổ mới để xem ảnh
 // SỬA HÀM NÀY
    private void showImageDialog(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) return;
        
        File f = new File(imagePath);
        if (!f.exists()) {
            JOptionPane.showMessageDialog(this, "File ảnh không tồn tại: " + imagePath);
            return;
        }
        
        // 1. Lấy cửa sổ cha để dialog bám theo
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        
        // 2. Khởi tạo Dialog có Owner và Modal = true
        JDialog dialog = new JDialog(parentWindow, "Xem ảnh chi tiết", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(900, 700); // Tăng kích thước chút cho thoải mái
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        
        // 3. Load và Scale ảnh
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        
        // Tự động scale theo kích thước màn hình nếu ảnh quá to
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = screenSize.width - 100;
        int maxHeight = screenSize.height - 100;
        
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);
        
        if (imgW > maxWidth || imgH > maxHeight) {
            // Scale giữ tỷ lệ
            double ratio = Math.min((double)maxWidth/imgW, (double)maxHeight/imgH);
            int newW = (int)(imgW * ratio);
            int newH = (int)(imgH * ratio);
            img = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            dialog.setSize(newW + 50, newH + 80); // Resize dialog theo ảnh
            dialog.setLocationRelativeTo(null);
        }
        
        JLabel lblImg = new JLabel(icon);
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane scroll = new JScrollPane(lblImg);
        dialog.add(scroll, BorderLayout.CENTER);
        
        // 4. Thêm nút đóng (Tùy chọn nhưng nên có)
        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dialog.dispose());
        JPanel pnlBot = new JPanel();
        pnlBot.add(btnClose);
        dialog.add(pnlBot, BorderLayout.SOUTH);

        // 5. Hiển thị
        dialog.setVisible(true);
    }
    
    private void initEvents() {
        btnMauBinhThuong.addActionListener(e -> {
            txtMoTa.setText("- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.");
            txtKetLuan.setText("Hiện tại chưa phát hiện bất thường trên hình ảnh.");
            txtDeNghi.setText("");
        });






     
    }
    

    
    private String processFile(String rawPath) {
        if(rawPath == null || rawPath.trim().isEmpty()) return null;
        if (!rawPath.contains(":")) return rawPath; 

        File f = new File(rawPath);
        if(f.exists()) return DuongdanFile.luuFileAnh(f); 
        
        return null;
    }

    private void closeDialog() {
        Window win = SwingUtilities.getWindowAncestor(this);
        if (win != null) win.dispose();
    }
}