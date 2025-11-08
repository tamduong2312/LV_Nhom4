package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmQuanLy extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLy frame = new FrmQuanLy();
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
	public FrmQuanLy() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnQLNV = new JButton("");
		btnQLNV.setBackground(new Color(255, 255, 255));
		btnQLNV.setIcon(new ImageIcon(FrmQuanLy.class.getResource("/image/medical-staff.png")));
		btnQLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQuanLyNhanVien ql = new frmQuanLyNhanVien();
				ql.show();
				dispose();
			}
		});
		btnQLNV.setBounds(252, 26, 214, 73);
		contentPane.add(btnQLNV);
		
		JButton btnQLTK = new JButton("");
		btnQLTK.setBackground(new Color(255, 255, 255));
		btnQLTK.setIcon(new ImageIcon(FrmQuanLy.class.getResource("/image/management.png")));
		btnQLTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmQuanLyTaiKhoan ql = new FrmQuanLyTaiKhoan();
				ql.show();
				dispose();
			}
		});
		btnQLTK.setBounds(252, 110, 214, 73);
		contentPane.add(btnQLTK);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLogin ql = new FrmLogin();
				ql.show();
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmQuanLy.class.getResource("/image/logout (2).png")));
		btnNewButton.setBounds(725, 11, 51, 49);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmQuanLyBenhNhan q = new FrmQuanLyBenhNhan();
				q.show();
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon(FrmQuanLy.class.getResource("/image/patient-bed.png")));
		btnNewButton_1.setBounds(252, 194, 214, 79);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmQuanLyThuoc ql = new FrmQuanLyThuoc();
				ql.show();
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(FrmQuanLy.class.getResource("/image/medicine.png")));
		btnNewButton_2.setBounds(252, 284, 214, 73);
		contentPane.add(btnNewButton_2);
	}
}
