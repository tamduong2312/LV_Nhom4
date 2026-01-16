package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.BaoCaoThongKeDao;
import MODEL.ThongKeDTO;

public class FrmBaoCaoThongKe extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlChartContainer;
    private JComboBox<String> cboLoaiThongKe;
    private JButton btnThongKe;
    private JButton btnXuatPDF; 
    
    private JComboBox<String> cboThang; 
    private JComboBox<String> cboNam;
    

    private JFreeChart currentChart; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmBaoCaoThongKe frame = new FrmBaoCaoThongKe();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmBaoCaoThongKe() {
        setTitle("Báo Cáo Thống Kê Phòng Khám");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1100, 700);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // --- PANEL CONTROL ---
        JPanel pnlControl = new JPanel();
        pnlControl.setBackground(new Color(240, 248, 255));
        pnlControl.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        contentPane.add(pnlControl, BorderLayout.NORTH);

        pnlControl.add(new JLabel("Thống kê:"));
        cboLoaiThongKe = new JComboBox<>(new String[]{"Số lượng bệnh nhân", "Doanh thu"});
        pnlControl.add(cboLoaiThongKe);
        
        pnlControl.add(new JLabel("Tháng:"));
        cboThang = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        pnlControl.add(cboThang);
        
        pnlControl.add(new JLabel("Năm:"));
        cboNam = new JComboBox<>(new String[]{"2023", "2024", "2025", "2026"});
        pnlControl.add(cboNam);

        btnThongKe = new JButton("Xem Biểu Đồ");
        btnThongKe.setBackground(new Color(0, 123, 255));
        btnThongKe.setForeground(Color.WHITE);
        pnlControl.add(btnThongKe);
        btnThongKe.setContentAreaFilled(false);
        btnThongKe.setOpaque(true);
        btnThongKe.setBorderPainted(false);


        


        btnXuatPDF = new JButton("Xuất File PDF");
        btnXuatPDF.setBackground(new Color(220, 53, 69)); 
        btnXuatPDF.setForeground(Color.WHITE);
        pnlControl.add(btnXuatPDF);
        btnXuatPDF.setContentAreaFilled(false);
        btnXuatPDF.setOpaque(true);
        btnXuatPDF.setBorderPainted(false);



        pnlChartContainer = new JPanel();
        pnlChartContainer.setLayout(new BorderLayout());
        pnlChartContainer.setBackground(Color.WHITE);
        contentPane.add(pnlChartContainer, BorderLayout.CENTER);


        btnThongKe.addActionListener(e -> xyLyHienThiBieuDo());
        
   
        btnXuatPDF.addActionListener(e -> xuatFilePDF());
        
        xyLyHienThiBieuDo();
    }

    private void xyLyHienThiBieuDo() {
        BaoCaoThongKeDao dao = new BaoCaoThongKeDao();
        int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        String loaiThongKe = cboLoaiThongKe.getSelectedItem().toString(); 
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (loaiThongKe.equals("Số lượng bệnh nhân")) {
            List<ThongKeDTO> list = dao.getSoLuongBenhNhanTheoThang(thang, nam);
            for (ThongKeDTO item : list) {
                dataset.addValue(item.getGiaTri(), "Bệnh nhân", String.valueOf(item.getNgay()));
            }
            veBieuDoCot(dataset, "THỐNG KÊ BỆNH NHÂN THÁNG " + thang + "/" + nam, "Ngày", "Số người");
        } else {
            List<ThongKeDTO> list = dao.getDoanhThuTheoThang(thang, nam);
            for (ThongKeDTO item : list) {
                dataset.addValue(item.getGiaTri(), "Doanh thu", String.valueOf(item.getNgay()));
            }
            veBieuDoCot(dataset, "THỐNG KÊ DOANH THU THÁNG " + thang + "/" + nam, "Ngày", "VND");
        }
    }

    private void veBieuDoCot(DefaultCategoryDataset dataset, String title, String categoryLabel, String valueLabel) {

        currentChart = ChartFactory.createBarChart(
                title, categoryLabel, valueLabel, dataset,
                PlotOrientation.VERTICAL, false, true, false
        );

        CategoryPlot plot = currentChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 123, 255));
        renderer.setDrawBarOutline(false);

        ChartPanel chartPanel = new ChartPanel(currentChart);
        chartPanel.setPreferredSize(new Dimension(800, 500));
        
        pnlChartContainer.removeAll();
        pnlChartContainer.add(chartPanel, BorderLayout.CENTER);
        pnlChartContainer.validate(); 
        pnlChartContainer.repaint();
    }


    private void xuatFilePDF() {
        if (currentChart == null) {
            JOptionPane.showMessageDialog(this, "Chưa có biểu đồ để xuất!");
            return;
        }


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
   
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            // Tạo PDF
            try {
                // Tạo Document iText (khổ giấy A4 xoay ngang cho rộng)
                Document document = new Document(com.itextpdf.text.PageSize.A4.rotate());
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
                
                document.open();

                //  Vẽ biểu đồ lên PDF
                PdfContentByte contentByte = writer.getDirectContent();
  
                int width = 800;
                int height = 500;
                
                PdfTemplate template = contentByte.createTemplate(width, height);
                Graphics2D graphics2d = template.createGraphics(width, height, new com.itextpdf.awt.DefaultFontMapper());
                
                // Tạo khung hình chữ nhật để vẽ
                Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
                
                // Vẽ biểu đồ JFreeChart lên Graphics2D của PDF
                currentChart.draw(graphics2d, rectangle2d);
                
                graphics2d.dispose();
                contentByte.addTemplate(template, 20, 50); 

                document.close();
                
                JOptionPane.showMessageDialog(this, "Xuất PDF thành công!\n" + filePath);
                
         
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(filePath));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất PDF: " + ex.getMessage());
            }
        }
    }
}