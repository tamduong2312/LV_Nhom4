package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BUS.ChuyenKhoaBus;
import BUS.DangKyKhamBenhBus;
import BUS.PhieuKhamBus;
import MODEL.ChuyenKhoa;
import MODEL.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnlChuyenKhoa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox<String> cmbmack;

	/**
	 * Create the panel.
	 */
	
	  private void loadDataComboBox() {
		  
		  List<ChuyenKhoa> listck = new ArrayList<>();
		  ChuyenKhoaBus ckbus = new  ChuyenKhoaBus();
		  
	        listck = ckbus.getAllCK();
	        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
	        boxModel.addElement("");
	        for (ChuyenKhoa ck : listck) {
	            boxModel.addElement( ck.hienthi());
	        }
	        cmbmack.setModel(boxModel);
	    }
	public PnlChuyenKhoa() {
	
		setLayout(null);
		setBackground(new Color(255, 255, 255)); 
		setSize(450, 320); 

	
		Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
		Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);
		Color colText = new Color(50, 50, 50);
		Color colPrimary = new Color(111, 66, 193); 

	
		JLabel lblNewLabel = new JLabel("Chọn chuyên khoa muốn chuyển:");
		lblNewLabel.setBounds(30, 20, 300, 25);
		lblNewLabel.setFont(fontLabel);
		lblNewLabel.setForeground(new Color(0, 102, 204)); 
		add(lblNewLabel);


		cmbmack = new JComboBox();
		cmbmack.setBounds(30, 55, 380, 35); 
		cmbmack.setFont(fontInput);
		cmbmack.setBackground(Color.WHITE);
		add(cmbmack);


		JLabel lblLDo = new JLabel("Lý do chuyển:");
		lblLDo.setBounds(30, 110, 150, 25);
		lblLDo.setFont(fontLabel);
		lblLDo.setForeground(colText);
		add(lblLDo);


		textField = new JTextField();
		textField.setBounds(30, 145, 380, 80); 
		textField.setFont(fontInput);

		textField.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(200, 200, 200)), 
				new EmptyBorder(5, 10, 5, 10)
		));

		add(textField);
		textField.setColumns(10);


		JButton btnNewButton = new JButton("XÁC NHẬN CHUYỂN");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        if (cmbmack.getSelectedItem() == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên khoa!");
		            return;
		        }
		        
		        if (Session.maphieukham <= 0) {
		            JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu khám để chuyển!");
		            return;
		        }

		        try {
		
		            String selectedItem = cmbmack.getSelectedItem().toString();
		            String ghichu = textField.getText();
		            
		      
		            int maCKMoi = Integer.parseInt(selectedItem.split("\\-")[0].trim()); 
		            
		            JOptionPane.showMessageDialog(null, maCKMoi);

	
		            PhieuKhamBus pkBus = new PhieuKhamBus(); 
		            
	
		

		            int ketQua = pkBus.updateChuyenKhoa(Session.maphieukham, maCKMoi, ghichu);
//		            DangKyKhamBenhBus dkBus = new DangKyKhamBenhBus();
//		            dkBus.capNhatChuyenKhoaDangKy(Session.mabenhnhan, maCKMoi);

		            if (ketQua > 0) {
		                JOptionPane.showMessageDialog(null, "Chuyển chuyên khoa thành công!");
		                
		          
		                SwingUtilities.getWindowAncestor(PnlChuyenKhoa.this).dispose();

		            } else {
		                JOptionPane.showMessageDialog(null, "Chuyển khoa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Lỗi định dạng dữ liệu: " + ex.getMessage());
		        }
		    }
		});
		btnNewButton.setBounds(115, 250, 200, 40);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBackground(colPrimary);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnNewButton);
		
		loadDataComboBox() ;
	}
}