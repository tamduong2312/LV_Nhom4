package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.DichVu;
import MODEL.NhanVien;
import MODEL.PhieuKham;
import MODEL.CTToaThuoc;

public class TinhTienDao {

    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";



    public double tinhTongTien(int maPhieuKham) {
        double tongTien = 0;

        String sql = """
            SELECT 
                (
                
                    SELECT COALESCE(dv.don_gia, 0)
                    FROM phieu_kham pk_sub
                    JOIN dang_ky_kham_benh dk ON pk_sub.ma_benh_nhan = dk.ma_benh_nhan 
                         AND DATE(pk_sub.ngay_kham) = DATE(dk.thoi_gian_dang_ky)
                    JOIN dich_vu dv ON dk.ma_chuyen_khoa = dv.ma_chuyen_khoa
                    WHERE pk_sub.ma_phieu_kham = pk.ma_phieu_kham
                      AND dv.loai_dich_vu = 'KHAM_BENH'
                    LIMIT 1
                ) 
                + 
                (
                    -- 2. TIỀN CẬN LÂM SÀNG
                    SELECT COALESCE(SUM(tong_tien), 0)
                    FROM phieu_chi_dinh
                    WHERE ma_phieu_kham = pk.ma_phieu_kham
                ) 
                + 
                (
             
                    SELECT COALESCE(SUM(
                        CAST(
                            REPLACE(REPLACE(tong_tien, '.', ''), ',', '') 
                        AS DECIMAL(15,2))
                    ), 0)
                    FROM toa_thuoc
                    WHERE ma_phieu_kham = pk.ma_phieu_kham
                ) AS tong_cong_thanh_toan

            FROM phieu_kham pk
            WHERE pk.ma_phieu_kham = ?
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieuKham);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tongTien = rs.getDouble("tong_cong_thanh_toan");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tongTien;
    }
    public List<DichVu> getListDichVuSuDung(int maPhieuKham) {
        List<DichVu> list = new ArrayList<>();

    
        String sqlKham = """
            SELECT dv.ma_dich_vu, dv.ten_dich_vu, dv.don_gia
            FROM dich_vu dv
            WHERE dv.loai_dich_vu = 'KHAM_BENH'
            AND dv.ma_chuyen_khoa IN (
         
                SELECT ma_chuyen_khoa FROM phieu_kham WHERE ma_phieu_kham = ?
                UNION
       
                SELECT dk.ma_chuyen_khoa
                FROM phieu_kham pk
                JOIN dang_ky_kham_benh dk ON pk.ma_benh_nhan = dk.ma_benh_nhan
                WHERE pk.ma_phieu_kham = ? 
                  AND DATE(pk.ngay_kham) = DATE(dk.thoi_gian_dang_ky)
            )
        """;


        String sqlCLS = """
            SELECT dv.ma_dich_vu, dv.ten_dich_vu, dv.don_gia
            FROM phieu_chi_dinh pcd
            JOIN chi_tiet_chi_dinh ctcd ON pcd.ma_phieu_chi_dinh = ctcd.ma_phieu_chi_dinh
            JOIN dich_vu dv ON ctcd.ma_dich_vu = dv.ma_dich_vu
            WHERE pcd.ma_phieu_kham = ?
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            
   
            try (PreparedStatement ps = conn.prepareStatement(sqlKham)) {
                ps.setInt(1, maPhieuKham);
                ps.setInt(2, maPhieuKham); 
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DichVu dv = new DichVu();
                    dv.setMaDichVu(rs.getInt("ma_dich_vu"));
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dv.setDonGia(rs.getLong("don_gia"));
                    list.add(dv);
                }
            }

  
            try (PreparedStatement ps = conn.prepareStatement(sqlCLS)) {
                ps.setInt(1, maPhieuKham);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DichVu dv = new DichVu();
                    dv.setMaDichVu(rs.getInt("ma_dich_vu"));
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dv.setDonGia(rs.getLong("don_gia"));
                    list.add(dv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<CTToaThuoc> getListThuocSuDung(int maPhieuKham) {
        List<CTToaThuoc> list = new ArrayList<>();
        String sql = """
            SELECT 
                t.ma_thuoc,
                t.ten_thuoc,
                ((ct.sang + ct.trua + ct.chieu + ct.toi) * ct.so_ngay) AS tong_so_luong,
                t.don_gia_ban
            FROM toa_thuoc tt
            JOIN ct_toa_thuoc ct ON tt.ma_toa_thuoc = ct.ma_toa_thuoc
            JOIN thuoc t ON ct.ma_thuoc = t.ma_thuoc
            WHERE tt.ma_phieu_kham = ?
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhieuKham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTToaThuoc ct = new CTToaThuoc();
                ct.setMaThuoc(rs.getInt("ma_thuoc"));
                ct.setTenThuoc(rs.getString("ten_thuoc"));
                
             
                int sl = rs.getInt("tong_so_luong");
                ct.setSoNgay(sl); 
                
          
                double gia = rs.getDouble("don_gia_ban");
                ct.setDonGia(gia);
                
        
                
                list.add(ct);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    

    public boolean thanhToanPhieuKham(int maPhieuKham, double tongTienThu) {
        String sql = "UPDATE phieu_kham SET trang_thai = 'DA_THANH_TOAN' WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhieuKham);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    
    
  


    public List<PhieuKham> timKiemPhieuKhamThanhToan(String keyword) {
        List<PhieuKham> list = new ArrayList<>();
        
 
        String sql = """
            SELECT pk.*, ck.ten_chuyen_khoa 
            FROM phieu_kham pk
            JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN nhan_vien bs ON pk.ma_nhan_vien = bs.ma_nhan_vien
            JOIN chuyen_khoa ck ON pk.ma_chuyen_khoa = ck.ma_chuyen_khoa 
            WHERE (pk.trang_thai = 'DA_KHAM'
              OR pk.trang_thai = 'DA_THANH_TOAN')
              AND DATE(pk.ngay_kham) = CURDATE()
              AND (
                   bn.ho_ten LIKE ? 
                OR bn.so_dien_thoai LIKE ? 
                OR bs.ho_ten LIKE ?
              )
            ORDER BY pk.ma_phieu_kham DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchVal = "%" + keyword.trim() + "%";
            ps.setString(1, searchVal); 
            ps.setString(2, searchVal); 
            ps.setString(3, searchVal); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKham pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                pk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                pk.setMaChuyenKhoa(rs.getString("ten_chuyen_khoa"));
                
                if (rs.getDate("ngay_kham") != null) {
                    pk.setNgayKham(rs.getDate("ngay_kham").toLocalDate());
                }
                
                pk.setTrangThai(rs.getString("trang_thai"));
     

                list.add(pk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public int demtrangthaiCHOTHANHTOAN() {
	    
    	String sql = """
    		    SELECT COUNT(DISTINCT ma_benh_nhan) as tong_so_benh_nhan
    		    FROM phieu_kham
    		    WHERE trang_thai IN ('DA_KHAM')
    		    AND DATE(ngay_kham) = CURRENT_DATE;
    		    """;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	ResultSet kq   = ps.executeQuery();
        if(kq.next()) {
        	
        	return kq.getInt("tong_so_benh_nhan");
        }
        }
        catch (SQLException e) 
        {
        	e.printStackTrace();
        	return 1;
        	
        	}
		return 0;

    }
    
    
    
    
   public int demtrangthaiDATHANHTOAN() {
	    
	   String sql = """
			    SELECT COUNT(DISTINCT ma_benh_nhan) as tong_so_benh_nhan
			    FROM phieu_kham
			    WHERE trang_thai IN ('DA_THANH_TOAN')
			    AND DATE(ngay_kham) = CURRENT_DATE;
			    """;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	ResultSet kq   = ps.executeQuery();
        if(kq.next()) {
        	
        	return kq.getInt("tong_so_benh_nhan");
        }
        }
        catch (SQLException e) 
        {
        	e.printStackTrace();
        	return 1;
        	
        	}
		return 0;

    }
   
   public int demtrangthaiTATCA() {
	   String sql = """
			    SELECT COUNT(DISTINCT ma_benh_nhan) as tong_so_benh_nhan
			    FROM phieu_kham
			    WHERE trang_thai IN ('DA_THANH_TOAN', 'DA_KHAM')
			    AND DATE(ngay_kham) = CURRENT_DATE;
			    """;
       try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql)) {

       	ResultSet kq   = ps.executeQuery();
       if(kq.next()) {
       	
       	return kq.getInt("tong_so_benh_nhan");
       }
       }
       catch (SQLException e) 
       {
       	e.printStackTrace();
       	return 1;
       	
       	}
		return 0;

   }
   
   
   public List<DichVu> getListDichVuSuDungTheoBN(int maBenhNhan , int mapk) {
	    List<DichVu> list = new ArrayList<>();
	    

	    String sql = """

	      SELECT dv.ma_dich_vu,dv.ten_dich_vu, dv.don_gia
FROM dang_ky_kham_benh dk
LEFT JOIN chi_tiet_chi_dinh ct ON dk.ma_chi_tiet_chi_dinh = ct.id
JOIN dich_vu dv ON (
    (dk.ma_chi_tiet_chi_dinh IS NULL AND dv.ma_chuyen_khoa = dk.ma_chuyen_khoa AND dv.loai_dich_vu = 'KHAM_BENH')
    OR
    (dk.ma_chi_tiet_chi_dinh IS NOT NULL AND ct.ma_dich_vu = dv.ma_dich_vu)
)
WHERE dk.ma_benh_nhan = ? AND dk.trang_thai = 'DA_KHAM' AND DATE(dk.thoi_gian_dang_ky) = CURDATE()
AND dk.ma_phieu_kham = ?
	    """;

	    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setInt(1, maBenhNhan);
	        ps.setInt(2, mapk);

	        
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            DichVu dv = new DichVu();
	            dv.setMaDichVu(rs.getInt("ma_dich_vu"));
	            dv.setTenDichVu(rs.getString("ten_dich_vu"));
	            dv.setDonGia(rs.getLong("don_gia"));
	            list.add(dv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
   
   public List<CTToaThuoc> getListThuocSuDungTheoBN(int maBenhNhan) {
	    List<CTToaThuoc> list = new ArrayList<>();
	    String sql = """
    SELECT 
            ct.id, 
            t.ma_thuoc,
            t.ten_thuoc,
            ((ct.sang + ct.trua + ct.chieu + ct.toi) * ct.so_ngay) AS tong_so_luong,
            t.don_gia_ban
        FROM phieu_kham pk
        JOIN toa_thuoc tt ON pk.ma_phieu_kham = tt.ma_phieu_kham
        JOIN ct_toa_thuoc ct ON tt.ma_toa_thuoc = ct.ma_toa_thuoc
        JOIN thuoc t ON ct.ma_thuoc = t.ma_thuoc
        WHERE pk.ma_phieu_kham = ?
          AND DATE(pk.ngay_kham) = CURDATE()
      
          AND (pk.trang_thai = 'DA_KHAM' OR pk.trang_thai = 'DA_THANH_TOAN')

          AND tt.trang_thai NOT IN ('HỦY CẤP THUỐC') 
	    """;

	    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setInt(1, maBenhNhan);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            CTToaThuoc ct = new CTToaThuoc();
	            ct.setId(rs.getInt("id"));
	            ct.setMaThuoc(rs.getInt("ma_thuoc"));
	            ct.setTenThuoc(rs.getString("ten_thuoc"));
	            ct.setSoNgay(rs.getInt("tong_so_luong")); 
	            ct.setDonGia(rs.getDouble("don_gia_ban"));
	            list.add(ct);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}