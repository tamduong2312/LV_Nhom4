package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import BUS.ChuyenKhoaBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import DAO.PhieuChuyenKhoaDao;
import BUS.BangPhanCongCaLamBus;
import MODEL.ChuyenKhoa;
import MODEL.PhieuChuyenKhoa;
import MODEL.Session;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class PnlChuyenKhoa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea txtLyDo; 
	private JTextArea txtTomTatLamSang; 
	private JComboBox<String> cmbmack;
	private JLabel lblTenBN, lblMaBN, lblKhoaHienTai;
	
	private ChuyenKhoaBus ckBus = new ChuyenKhoaBus();
	private DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
	private BangPhanCongCaLamBus phanCongBus = new BangPhanCongCaLamBus();
	private PhieuKhamBus pkBus = new PhieuKhamBus();

	private void loadDataComboBox() {
		List<ChuyenKhoa> listck = ckBus.getAllCK();
		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
		boxModel.addElement("-- Chọn chuyên khoa đích --");
		for (ChuyenKhoa ck : listck) {
			if (ck.getMa_chuyen_khoa() != Session.machuyenkhoa) {
				boxModel.addElement(ck.hienthi());
			}
		}
		cmbmack.setModel(boxModel);
	}

	public PnlChuyenKhoa() {
		setLayout(null);
		setBackground(new Color(245, 248, 250));
		setSize(1200, 900); 

		Font fontTitle = new Font("Segoe UI", Font.BOLD, 22);
		Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
		Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);
		Color colPrimary = new Color(0, 102, 204);

		JLabel lblTitle = new JLabel("PHIẾU CHUYỂN CHUYÊN KHOA ĐIỀU TRỊ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 20, 800, 35);
		lblTitle.setFont(fontTitle);
		lblTitle.setForeground(colPrimary);
		add(lblTitle);


		JPanel pnlInfo = new JPanel();
		pnlInfo.setBackground(Color.WHITE);
		pnlInfo.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "Thông tin hành chính"));
		pnlInfo.setBounds(40, 70, 720, 80);
		pnlInfo.setLayout(null);
		add(pnlInfo);

		lblTenBN = new JLabel("Bệnh nhân: " + Session.TenBenhNhan );
		lblTenBN.setFont(fontLabel);
		lblTenBN.setBounds(20, 25, 300, 20);
		pnlInfo.add(lblTenBN);

		lblMaBN = new JLabel("Mã BN: " + Session.mabenhnhan);
		lblMaBN.setFont(fontLabel);
		lblMaBN.setBounds(20, 50, 200, 20);
		pnlInfo.add(lblMaBN);

		lblKhoaHienTai = new JLabel("Khoa chuyển đi: " + Session.tenChuyenKhoa);
		lblKhoaHienTai.setFont(fontLabel);
		lblKhoaHienTai.setBounds(350, 25, 350, 20);
		pnlInfo.add(lblKhoaHienTai);

	
		JLabel lblChon = new JLabel("Chuyên khoa đích:");
		lblChon.setFont(fontLabel);
		lblChon.setBounds(40, 170, 200, 25);
		add(lblChon);

		cmbmack = new JComboBox<>();
		cmbmack.setFont(fontInput);
		cmbmack.setBounds(40, 195, 720, 35);
		add(cmbmack);


		JLabel lblTomTat = new JLabel("Tóm tắt tình trạng lâm sàng:");
		lblTomTat.setFont(fontLabel);
		lblTomTat.setBounds(40, 245, 300, 25);
		add(lblTomTat);

		txtTomTatLamSang = new JTextArea();
		txtTomTatLamSang.setFont(fontInput);
		txtTomTatLamSang.setLineWrap(true);
		txtTomTatLamSang.setWrapStyleWord(true);
		JScrollPane sp1 = new JScrollPane(txtTomTatLamSang);
		sp1.setBounds(40, 270, 720, 80);
		add(sp1);


		JLabel lblLDo = new JLabel("Lý do chuyển khoa:");
		lblLDo.setFont(fontLabel);
		lblLDo.setBounds(40, 365, 300, 25);
		add(lblLDo);

		txtLyDo = new JTextArea();
		txtLyDo.setFont(fontInput);
		txtLyDo.setLineWrap(true);
		txtLyDo.setWrapStyleWord(true);
		JScrollPane sp2 = new JScrollPane(txtLyDo);
		sp2.setBounds(40, 390, 720, 80);
		add(sp2);

	
		JButton btnXacNhan = new JButton("XÁC NHẬN CHUYỂN");
		btnXacNhan.setBackground(new Color(40, 167, 69));
		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setFont(fontLabel);
		btnXacNhan.setBounds(540, 500, 220, 45);
		btnXacNhan.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnXacNhan.setContentAreaFilled(false);
		btnXacNhan.setOpaque(true);
		btnXacNhan.setBorderPainted(false);

		add(btnXacNhan);

		JButton btnInPDF = new JButton("IN PHIẾU CHUYỂN (PDF)");
		btnInPDF.setBackground(new Color(255, 193, 7));
		btnInPDF.setForeground(Color.BLACK);
		btnInPDF.setFont(fontLabel);
		btnInPDF.setContentAreaFilled(false);
		btnInPDF.setOpaque(true);
		btnInPDF.setBorderPainted(false);

		btnInPDF.setBounds(300, 500, 220, 45);
		add(btnInPDF);


		btnXacNhan.addActionListener(e -> {
			if (cmbmack.getSelectedIndex() <= 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên khoa đích!");
				return;
			}
			thucHienChuyenKhoa(false);
		});


		btnInPDF.addActionListener(e -> {
			if (cmbmack.getSelectedIndex() <= 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên khoa đích trước khi in!");
				return;
			}
			xuatPhieuChuyenKhoaPDF();
		});

		loadDataComboBox();
	}

	private void thucHienChuyenKhoa(boolean silent) {
	    if (cmbmack.getSelectedIndex() <= 0) {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên khoa chuyển đến!");
	        return;
	    }

	    try {
	        String selectedItem = cmbmack.getSelectedItem().toString();
	        int maCKMoi = Integer.parseInt(selectedItem.split("\\-")[0].trim());
	        String tenCKMoi = selectedItem.split("\\-")[1].trim();


	        int gioiHan = phanCongBus.layGioiHanBNHomNay(tenCKMoi);
	        int soCaThucTe = dkBus.demSoCaThucTeChuaHuy(maCKMoi);

	        if (gioiHan > 0 && soCaThucTe >= gioiHan) {
	            JOptionPane.showMessageDialog(null, "Khoa " + tenCKMoi + " hiện đã hết chỗ nhận bệnh!");
	            return;
	        }


	        String ghiChuMoi = "Chuyển từ khoa " + Session.chuyenKhoa;
	        int idDangKyMoi = pkBus.taoDangKyChuyenKhoa(Session.mabenhnhan, Session.maNhanVien, maCKMoi, ghiChuMoi);

	        if (idDangKyMoi > 0) {

	            PhieuChuyenKhoa pck = new PhieuChuyenKhoa();
	            pck.setMaPhieuKham(Session.maphieukham); 
	            pck.setMaBenhNhan(Session.mabenhnhan);
	            pck.setMaNhanVien(Session.maNhanVien);
	            pck.setMaCkTu(Session.machuyenkhoa);
	            pck.setMaCkDen(maCKMoi);
	            pck.setTomTatLamSang(txtTomTatLamSang.getText());
	            pck.setLyDoChuyen(txtLyDo.getText());
	            pck.setIdDangKyMoi(idDangKyMoi);

	            PhieuChuyenKhoaDao pckDao = new PhieuChuyenKhoaDao();
	            if (pckDao.luuPhieuChuyen(pck)) {
	                
	   
	                pkBus.capNhatDaKhamByID(Session.maDangKyHienTai);

	                if (!silent) {
	                    JOptionPane.showMessageDialog(null, "Đã lưu phiếu và chuyển bệnh nhân thành công!");
	    
	                }
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

	private void xuatPhieuChuyenKhoaPDF() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
		fileChooser.setSelectedFile(new File("PhieuChuyenKhoa_" + Session.mabenhnhan + ".pdf"));

		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			if (!filePath.endsWith(".pdf")) filePath += ".pdf";

			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(filePath));
				document.open();

				BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				com.itextpdf.text.Font fTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD);
				com.itextpdf.text.Font fBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
				com.itextpdf.text.Font fNormal = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);

				document.add(new Paragraph("PHÒNG KHÁM ĐA KHOA\n\n", fBold));
				Paragraph title = new Paragraph("PHIẾU CHUYỂN CHUYÊN KHOA", fTitle);
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);
				document.add(new Paragraph("\n"));

				document.add(new Paragraph("Họ tên bệnh nhân: "  , fNormal));
				document.add(new Paragraph("Mã bệnh nhân: " + Session.mabenhnhan, fNormal));
				document.add(new Paragraph("Khoa chuyển đi: " + Session.chuyenKhoa, fNormal));
				document.add(new Paragraph("Khoa chuyển đến: " + cmbmack.getSelectedItem().toString(), fNormal));
				document.add(new Paragraph("Thời gian chuyển: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), fNormal));
				document.add(new Paragraph("-----------------------------------------------------------------------", fNormal));

				document.add(new Paragraph("\n1. Tóm tắt lâm sàng:", fBold));
				document.add(new Paragraph(txtTomTatLamSang.getText(), fNormal));

				document.add(new Paragraph("\n2. Lý do chuyển khoa:", fBold));
				document.add(new Paragraph(txtLyDo.getText(), fNormal));

				document.add(new Paragraph("\n\n"));
				Paragraph sign = new Paragraph("Bác sĩ chuyển khoa\n(Ký và ghi rõ họ tên)", fBold);
				sign.setAlignment(Element.ALIGN_RIGHT);
				document.add(sign);

				document.close();
				JOptionPane.showMessageDialog(this, "Đã in phiếu chuyển PDF!");
				Desktop.getDesktop().open(new File(filePath));
				

				int ask = JOptionPane.showConfirmDialog(this, "Bạn đã in phiếu, muốn thực hiện lệnh chuyển trên hệ thống ngay?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(ask == JOptionPane.YES_OPTION) thucHienChuyenKhoa(false);

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Lỗi in PDF: " + ex.getMessage());
			}
		}
	}
}