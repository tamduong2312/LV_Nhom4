package SERVICE;

import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class TuDongGuiMail {

  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";


    private static final String EMAIL_FROM = "dvmtam1102003@gmail.com";
    private static final String EMAIL_PASS = "rnpm neej fbfl yhbu";

    public static void main(String[] args) {
        System.out.println("--- BẮT ĐẦU QUÉT LỊCH TÁI KHÁM ---");
        quetVaGuiMail();
        System.out.println("--- HOÀN TẤT ---");
    }

    public static void quetVaGuiMail() {
        Connection conn = null;
        PreparedStatement psSelect = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

      
            String sqlSelect = "SELECT l.id, l.ngay_tai_kham, b.ho_ten, b.email " +
                               "FROM lich_tai_kham l " +
                               "JOIN benh_nhan b ON l.ma_benh_nhan = b.ma_benh_nhan " +
                               "WHERE l.ngay_tai_kham = DATE_ADD(CURDATE(), INTERVAL 1 DAY) " + 
                               "AND l.trang_thai = 'CHUA_DEN' " +
                               "AND l.da_gui_thong_bao = 0";

            psSelect = conn.prepareStatement(sqlSelect);
            rs = psSelect.executeQuery();

            while (rs.next()) {
                int idLich = rs.getInt("id");
                String email = rs.getString("email");
                String tenBN = rs.getString("ho_ten");
                Date ngayKham = rs.getDate("ngay_tai_kham");

                if (email != null && !email.isEmpty()) {
                    System.out.println("Đang gửi mail cho: " + tenBN);
                    
               
                    boolean guiThanhCong = sendEmailNhacLich(email, tenBN, ngayKham.toString());

                 
                    if (guiThanhCong) {
                        String sqlUpdate = "UPDATE lich_tai_kham SET da_gui_thong_bao = 1 WHERE id = ?";
                        psUpdate = conn.prepareStatement(sqlUpdate);
                        psUpdate.setInt(1, idLich);
                        psUpdate.executeUpdate();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (psSelect != null) psSelect.close(); } catch (Exception e) {}
            try { if (psUpdate != null) psUpdate.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    public static boolean sendEmailNhacLich(String recipientEmail, String tenBenhNhan, String ngayTaiKham) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASS);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("NHẮC LỊCH TÁI KHÁM - PHÒNG KHÁM");
            
            String noiDung = "Chào " + tenBenhNhan + ",\n\n"
                    + "Đây là email nhắc nhở từ Phòng Khám.\n"
                    + "Bạn có lịch tái khám vào ngày mai: " + ngayTaiKham + ".\n"
                    + "Vui lòng sắp xếp thời gian đến đúng giờ.\n\n"
                    + "Trân trọng.";
            
            message.setText(noiDung);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}