package DAO;

import MODEL.DonThuocCho;
import MODEL.PhieuKham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuocSiDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";

    public List<DonThuocCho> getDsChoCapPhat() {
        List<DonThuocCho> list = new ArrayList<>();


        String sql = """
SELECT DISTINCT
    pk.ma_phieu_kham,
    tt.ma_toa_thuoc,
    bn.ho_ten,
    bn.so_dien_thoai,
    nv.ho_ten AS ten_bac_si,
    pk.ngay_kham,
    pk.trang_thai
FROM ct_hoa_don cthd
JOIN hoa_don hd ON cthd.ma_hoa_don = hd.ma_hoa_don
JOIN phieu_kham pk ON hd.ma_phieu_kham = pk.ma_phieu_kham
JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
JOIN nhan_vien nv ON pk.ma_nhan_vien = nv.ma_nhan_vien
JOIN toa_thuoc tt ON pk.ma_phieu_kham = tt.ma_phieu_kham
WHERE hd.trang_thai = 'đã thanh toán'         
  AND cthd.loai_muc = 'THUOC'                
  AND DATE(hd.ngay_thanh_toan) = CURDATE()    
  AND hd.trang_thai NOT IN ('cần hoàn tiền')   
  AND tt.trang_thai NOT IN ('HỦY CẤP THUỐC')  
ORDER BY hd.ma_hoa_don DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DonThuocCho dt = new DonThuocCho();
                dt.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                dt.setMaToaThuoc(rs.getInt("ma_toa_thuoc"));
                dt.setTenBenhNhan(rs.getString("ho_ten"));
                dt.setTenBacSi(rs.getString("ten_bac_si"));
                dt.setThoiGian(rs.getTimestamp("ngay_kham"));
                dt.setSDT(rs.getLong("so_dien_thoai"));
                dt.setTrangThai(rs.getString("trang_thai"));
                list.add(dt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<DonThuocCho> getDsChoTheoDK(String trangthai) {
        List<DonThuocCho> list = new ArrayList<>();
        
   
        String sql = """
 SELECT DISTINCT
    pk.ma_phieu_kham,
    tt.ma_toa_thuoc,
    bn.ho_ten,
    bn.so_dien_thoai,
    nv.ho_ten AS ten_bac_si,
    pk.ngay_kham,
    pk.trang_thai
FROM ct_hoa_don cthd
JOIN hoa_don hd ON cthd.ma_hoa_don = hd.ma_hoa_don
JOIN phieu_kham pk ON hd.ma_phieu_kham = pk.ma_phieu_kham
JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
JOIN nhan_vien nv ON pk.ma_nhan_vien = nv.ma_nhan_vien
JOIN toa_thuoc tt ON pk.ma_phieu_kham = tt.ma_phieu_kham
WHERE hd.trang_thai = ?         
  AND cthd.loai_muc = 'THUOC'                
  AND DATE(hd.ngay_thanh_toan) = CURDATE()    
  AND hd.trang_thai NOT IN ('cần hoàn tiền')   
  AND tt.trang_thai NOT IN ('HỦY CẤP THUỐC')  
ORDER BY hd.ma_hoa_don DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)){
        	ps.setString(1, trangthai);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DonThuocCho dt = new DonThuocCho();
                dt.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                dt.setMaToaThuoc(rs.getInt("ma_toa_thuoc"));
                dt.setTenBenhNhan(rs.getString("ho_ten"));
                dt.setTenBacSi(rs.getString("ten_bac_si"));
                dt.setThoiGian(rs.getTimestamp("ngay_kham"));
                dt.setSDT(rs.getLong("so_dien_thoai"));
                dt.setTrangThai(rs.getString("trang_thai"));
                list.add(dt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<DonThuocCho> timKiemPhieuKhamThanhToan(String keyword) {
        List<DonThuocCho> list = new ArrayList<>();
        

        String sql = """
            SELECT 
                pk.ma_phieu_kham,
                tt.ma_toa_thuoc,
                bn.ho_ten,
                bn.so_dien_thoai,
                nv.ho_ten AS ten_bac_si,
                pk.ngay_kham,
                pk.trang_thai
            FROM phieu_kham pk
            JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN nhan_vien nv ON pk.ma_nhan_vien = nv.ma_nhan_vien
            JOIN toa_thuoc tt ON pk.ma_phieu_kham = tt.ma_phieu_kham
            WHERE (pk.trang_thai = 'DA_THANH_TOAN'
              OR pk.trang_thai = 'DA_CAP_THUOC')  
              AND DATE(pk.ngay_kham) = CURDATE()
               AND (
                   bn.ho_ten LIKE ? 
                OR bn.so_dien_thoai LIKE ? 
                OR nv.ho_ten LIKE ?
              )
            ORDER BY pk.ngay_kham DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)){
        	
            String searchVal = "%" + keyword.trim() + "%";
            ps.setString(1, searchVal); 
            ps.setString(2, searchVal); 
            ps.setString(3, searchVal); 
             ResultSet rs = ps.executeQuery() ;

            while (rs.next()) {
                DonThuocCho dt = new DonThuocCho();
                dt.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                dt.setMaToaThuoc(rs.getInt("ma_toa_thuoc"));
                dt.setTenBenhNhan(rs.getString("ho_ten"));
                dt.setTenBacSi(rs.getString("ten_bac_si"));
                dt.setThoiGian(rs.getTimestamp("ngay_kham"));
                dt.setSDT(rs.getLong("so_dien_thoai"));
                dt.setTrangThai(rs.getString("trang_thai"));
                list.add(dt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    		
    	    public int demtrangthaidacapthuoc() {
    	    
        String sql = "SELECT COUNT(trang_thai) as dem FROM phieu_kham WHERE trang_thai = 'DA_CAP_THUOC' and date(ngay_kham) = CURRENT_DATE";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	ResultSet kq   = ps.executeQuery();
        if(kq.next()) {
        	
        	return kq.getInt("dem");
        }
        }
        catch (SQLException e) 
        {
        	e.printStackTrace();
        	return 1;
        	
        	}
		return 0;

    }
    	    
    	    public int demtrangthaichocapthuoc() {

    	        String sql = """
    	            SELECT COUNT(*) as dem 
    	            FROM phieu_kham pk 
    	            WHERE pk.trang_thai = 'DA_THANH_TOAN' 
    	              AND DATE(pk.ngay_kham) = CURRENT_DATE
    	         
    	              AND EXISTS (SELECT 1 FROM toa_thuoc tt WHERE tt.ma_phieu_kham = pk.ma_phieu_kham)
    	        """;

    	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    	             PreparedStatement ps = conn.prepareStatement(sql)) {

    	            ResultSet kq = ps.executeQuery();
    	            if (kq.next()) {
    	                return kq.getInt("dem");
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	            return 0; 
    	        }
    	        return 0;
    	    }
}