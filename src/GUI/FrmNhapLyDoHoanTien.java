package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.HoaDonBus;
import MODEL.Session;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmNhapLyDoHoanTien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtlydo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmNhapLyDoHoanTien frame = new FrmNhapLyDoHoanTien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmNhapLyDoHoanTien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtlydo = new JTextField();
		txtlydo.setBounds(46, 40, 348, 110);
		contentPane.add(txtlydo);
		txtlydo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nhập lý do hoàn tiền:");
		lblNewLabel.setBounds(149, 15, 108, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HoaDonBus hdbus = new HoaDonBus();
				
				String lydo = txtlydo.getText().toString();
				
				hdbus.capNhatGhiChuTheoMaPK(Session.maphieukham, lydo);
			JOptionPane.showMessageDialog(null, "Cập nhật ghi chú thành công");
				
			}
		});
		btnLuu.setBounds(106, 173, 89, 23);
		contentPane.add(btnLuu);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHuy.setBounds(223, 173, 89, 23);
		contentPane.add(btnHuy);
	}
}
