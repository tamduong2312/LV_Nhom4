package DAO;

import MODEL.ChiSoTongQuat;
import MODEL.ChuyenKhoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiSoDao {

    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";


    public boolean luuChiSo(ChiSoTongQuat cs) { 
       String sql = "INSERT INTO chi_so_noi_tong_quat "
                   + "(ma_phieu_kham, nhiet_do, huyet_ap_tam_thu, huyet_ap_tam_truong, "
                   + "nhip_tim, nhip_tho, can_nang, chieu_cao, ghi_chu) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cs.getMaPhieuKham());
            ps.setDouble(2, cs.getNhietDo());
            ps.setInt(3, cs.getHuyetApThu());
            ps.setInt(4, cs.getHuyetApTruong());
            ps.setInt(5, cs.getNhipTim());
            ps.setInt(6, cs.getNhipTho());
            ps.setDouble(7, cs.getCanNang());
            ps.setDouble(8, cs.getChieuCao());
            ps.setString(9, cs.getGhiChu());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean capnhatChiSo(ChiSoTongQuat cs , int id) {
        String sql = "UPDATE chi_so_noi_tong_quat SET "
                    + "nhiet_do = ?, "
                    + "huyet_ap_tam_thu = ?, "
                    + "huyet_ap_tam_truong = ?, "
                    + "nhip_tim = ?, "
                    + "nhip_tho = ?, "
                    + "can_nang = ?, "
                    + "chieu_cao = ?, "
                    + "ghi_chu = ? "
                    + "WHERE ma_phieu_kham = ?"; 

         try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
              PreparedStatement ps = conn.prepareStatement(sql)) {

        
             ps.setDouble(1, cs.getNhietDo());
             ps.setInt(2, cs.getHuyetApThu());
             ps.setInt(3, cs.getHuyetApTruong());
             ps.setInt(4, cs.getNhipTim());
             ps.setInt(5, cs.getNhipTho());
             ps.setDouble(6, cs.getCanNang());
             ps.setDouble(7, cs.getChieuCao());
             ps.setString(8, cs.getGhiChu());
             
     
             ps.setInt(9, id); 

             return ps.executeUpdate() > 0; 

         } catch (Exception e) {
             e.printStackTrace();
         }

         return false;
     }
    public List<ChiSoTongQuat> getallChiSo(int id) {
        List<ChiSoTongQuat> list = new ArrayList<>();
        String query = "SELECT * FROM chi_so_noi_tong_quat WHERE ma_phieu_kham = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)){
        		
        		pstmt.setInt(1, id);
             ResultSet rs = pstmt.executeQuery() ;

            while (rs.next()) {
            	ChiSoTongQuat nv = new ChiSoTongQuat();
                nv.setNhietDo(rs.getFloat("nhiet_do"));
                nv.setHuyetApThu(rs.getInt("huyet_ap_tam_thu"));
                nv.setHuyetApTruong(rs.getInt("huyet_ap_tam_truong"));
                nv.setNhipTim(rs.getInt("nhip_tim"));
                nv.setNhipTho(rs.getInt("nhip_tho"));
                nv.setCanNang(rs.getFloat("can_nang"));
                nv.setChieuCao(rs.getFloat("chieu_cao"));
                nv.setGhiChu(rs.getString("ghi_chu"));
  
     
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
