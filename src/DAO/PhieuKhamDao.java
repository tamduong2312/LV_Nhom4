package DAO;

import MODEL.DanhMucBenhLy;
import MODEL.PhieuKham;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PhieuKhamDao {

    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";


    public int taoPhieuKham(int maBenhNhan, int maNhanVien, String maChuyenKhoa) {
        String sql = "INSERT INTO phieu_kham (ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, ngay_kham, trang_thai) "
                   + "VALUES (?, ?, ?, NOW(), 'Đang khám')";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, maBenhNhan);
            ps.setInt(2, maNhanVien);
            ps.setString(3, maChuyenKhoa);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    public int TroLyTQTaoPhieuKham(int maBenhNhan, int maNhanVien, int maChuyenKhoa) {
    	   int generatedKey = 0;
        String sql = "INSERT INTO phieu_kham (ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, ngay_kham, trang_thai) "
                   + "VALUES (?, ?, ?, NOW(), 'Đang khám')";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, maBenhNhan);
            ps.setInt(2, maNhanVien);
            ps.setInt(3, maChuyenKhoa);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            	generatedKey = rs.getInt(1); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedKey;
    }
    public int BSTaoPhieuKham(int maBenhNhan, int maNhanVien, int maChuyenKhoa, String ghichu) {
 	   int generatedKey = 0;
     String sql = "INSERT INTO phieu_kham (ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, ngay_kham, trang_thai, ghi_chu) "
                + "VALUES (?, ?, ?, NOW(), 'DA_DOI_KHOA',?)";

     try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
          PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

         ps.setInt(1, maBenhNhan);
         ps.setInt(2, maNhanVien);
         ps.setInt(3, maChuyenKhoa);
         ps.setString(4, ghichu);

         ps.executeUpdate();

         ResultSet rs = ps.getGeneratedKeys();
         if (rs.next()) {
         	generatedKey = rs.getInt(1); 
         }

     } catch (Exception e) {
         e.printStackTrace();
     }

     return generatedKey;
 }
    public int taoDangKyChuyenKhoa(int maBenhNhan, int maNhanVien, int maChuyenKhoaMoi, String ghiChu) {
        int nextSTT = 1;
 	   int generatedKey = 0;
        //  Lấy STT tiếp theo của khoa mới trong ngày
        String sqlGetMaxSTT = "SELECT IFNULL(MAX(so_thu_tu), 0) + 1 FROM dang_ky_kham_benh "
                            + "WHERE ma_chuyen_khoa = ? AND DATE(thoi_gian_dang_ky) = CURDATE()";

        //  Chèn bản đăng ký mới với trạng thái DOI_CHUYEN_KHOA
        String sqlInsert = "INSERT INTO dang_ky_kham_benh (ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, so_thu_tu, thoi_gian_dang_ky, trang_thai, ghi_chu) "
                         + "VALUES (?, ?, ?, ?, NOW(), 'DOI_CHUYEN_KHOA', ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
         
            try (PreparedStatement ps1 = conn.prepareStatement(sqlGetMaxSTT)) {
                ps1.setInt(1, maChuyenKhoaMoi);
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) nextSTT = rs.getInt(1);
            }

   
            try (PreparedStatement ps2 = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
                ps2.setInt(1, maBenhNhan);
                ps2.setInt(2, maNhanVien);
                ps2.setInt(3, maChuyenKhoaMoi);
                ps2.setInt(4, nextSTT);
                ps2.setString(5, "Chuyển chuyên khoa: " + (ghiChu != null ? ghiChu : ""));
                ps2.executeUpdate();
                ResultSet rs = ps2.getGeneratedKeys();
                if (rs.next()) {
                	generatedKey = rs.getInt(1); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return generatedKey;

      
    }


    public void capNhatDaKhamByID(int idDangKy) {
        String sql = "UPDATE dang_ky_kham_benh SET trang_thai = 'DA_KHAM' WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDangKy);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    

    public PhieuKham getPhieuKham(int maPK) {
        String sql = "SELECT * FROM phieu_kham WHERE ma_phieu_kham = ?";
        PhieuKham pk = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPK);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                pk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                pk.setMaChuyenKhoa(rs.getString("ma_chuyen_khoa"));
                pk.setNgayKham(rs.getDate("ngay_kham").toLocalDate());
                pk.setTrieuChung(rs.getString("trieu_chung"));
                pk.setChanDoan(rs.getString("chan_doan"));
                pk.setHuongDieuTri(rs.getString("huong_dieu_tri"));
                pk.setLoiDanBacSi(rs.getString("loi_dan_bac_si"));
                pk.setTienKham(rs.getLong("tien_kham"));
                pk.setTrangThai(rs.getString("trang_thai"));
                pk.setGhiChu(rs.getString("ghi_chu"));

                Date ngayTao = rs.getDate("ngay_tao");
                if (ngayTao != null) pk.setNgayTao(ngayTao.toLocalDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pk;
    }

 
    
    public List<PhieuKham> getallPhieuKham() {
        List<PhieuKham> list = new ArrayList<>();
        String sql = "SELECT * FROM phieu_kham ";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
    
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	PhieuKham pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                pk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                pk.setMaChuyenKhoa(rs.getString("ma_chuyen_khoa"));
                pk.setNgayKham(rs.getDate("ngay_kham").toLocalDate());
                pk.setTrangThai(rs.getString("trang_thai"));
                pk.setGhiChu(rs.getString("ghi_chu"));

                Date ngayTao = rs.getDate("ngay_tao");
                if (ngayTao != null) pk.setNgayTao(ngayTao.toLocalDate());
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    

    public boolean capNhatChanDoan(PhieuKham pk) {
        String sql = "UPDATE phieu_kham SET trieu_chung=?, chan_doan=?, huong_dieu_tri=?, loi_dan_bac_si=?, trang_thai=? "
                   + "WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pk.getTrieuChung());
            ps.setString(2, pk.getChanDoan());
            ps.setString(3, pk.getHuongDieuTri());
            ps.setString(4, pk.getLoiDanBacSi());
            ps.setString(5, pk.getTrangThai());
            ps.setInt(6, pk.getMaPhieuKham());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public int updateTrangThai(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='CHO_KHAM_BS' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public int updateTrangThaiBSKHAMBENH(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='BS_DANG_KHAM_BENH' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    
    public int updateTrangThaiChiDinhCLS(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='DOI_KET_QUA_CLS' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public int updateTrangThaiDAKHAMChiDinhCLS(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='DA_KHAM' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public boolean updateTrangThaiPhieuKhamTheoMaDangKy(int idDangKy) {

        String sql = """
            UPDATE phieu_kham 
            SET trang_thai = 'DA_KHAM' 
            WHERE ma_phieu_kham = (
                SELECT ma_phieu_kham 
                FROM dang_ky_kham_benh 
                WHERE id = ?
            )
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idDangKy);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public int updateTrangThaiDAKHAMTrongChiDinhCLS(int maPK) {
    	int kq = 0;
        String sql = "UPDATE chi_tiet_chi_dinh ct "
        		+ "SET trang_thai_dv = 'DA_KHAM' "
        		+ "WHERE ct.id  = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public int updateTrangThaiThucHienCLSTrongCTChiDinh(int maPK) {
    	int kq = 0;
        String sql = "UPDATE chi_tiet_chi_dinh ct "
        		+ "SET trang_thai_dv = 'DA_THUC_HIEN_CLS' "
        		+ "WHERE ct.ma_phieu_chi_dinh  = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    
    

    public int updateTrangThaiKQChiDinhCLS(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='DA_CO_KET_QUA_CLS' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public int updateTrangThaiHoantatKhamBenh(int maPK , int manv) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='DA_KHAM' , ma_nhan_vien = ? WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, manv);
            ps.setInt(2, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public int updateTrangThaiHoanTatCapThuoc(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='DA_CAP_THUOC' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public int updateTrangThaiTiepNhanChuyenKhoa(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='TIEP_NHAN_KHAM_CHUYEN_KHOA' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public int updateChuyenKhoa(int maPK, int mack, String ghichu) {
    	int kq = 0;
    	String sql = "UPDATE phieu_kham SET ma_chuyen_khoa = ?,trang_thai = 'DOI_CHUYEN_KHOA', ghi_chu = ? WHERE ma_phieu_kham = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, mack);
            ps.setString(2, ghichu);
            ps.setInt(3, maPK);
            kq = ps.executeUpdate();  

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return kq;
    }
    
    public int updateTrangThaiHUYKHAM(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='HUY_KHAM' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public int getMaPhieuKhamByBenhNhan(int maBenhNhan, int maChuyenKhoa) {
        int maPhieu = 0;


        String sql = "SELECT ma_phieu_kham FROM phieu_kham " +
                "WHERE ma_benh_nhan = ? " +
                "AND ma_chuyen_khoa = ? " + 
                "AND DATE(ngay_kham) = CURDATE() " + 
                "ORDER BY ma_phieu_kham DESC LIMIT 1"; 
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, maBenhNhan);
            pstm.setInt(2, maChuyenKhoa); 
            
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                maPhieu = rs.getInt("ma_phieu_kham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPhieu;
    }
    
    public int getMaPhieuKhamByIdDangKy(int idDangKy) {
        int maPK = 0;

        String sql = "SELECT ma_phieu_kham FROM dang_ky_kham_benh WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idDangKy);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                maPK = rs.getInt("ma_phieu_kham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPK;
    }
    
    public int getMaPhieuKhamByNgay(int maBenhNhan, Date ngayKham) {
        int maPhieu = 0;
        
 
        String sql = "SELECT ma_phieu_kham FROM phieu_kham " +
                     "WHERE ma_benh_nhan = ? AND DATE(ngay_kham) = ? " +
                     "ORDER BY ma_phieu_kham DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maBenhNhan);
            ps.setDate(2, ngayKham); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    maPhieu = rs.getInt("ma_phieu_kham");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return maPhieu; 
    }
    
    
    public List<PhieuKham> getLichSuKhamByMaBenhNhan(int maBenhNhan) {
        List<PhieuKham> list = new ArrayList<>();
        
     
        String sql = """
      
    SELECT 
        ma_phieu_kham, 
        ma_benh_nhan, 
        ma_nhan_vien, 
        ma_chuyen_khoa, 
        ngay_kham, 
        trang_thai 
    FROM phieu_kham 
    WHERE ma_benh_nhan = ?
    AND (trang_thai = 'DA_KHAM' OR trang_thai = 'DA_CAP_THUOC' OR trang_thai='DA_THANH_TOAN') 
    ORDER BY ngay_kham DESC
    
        	    """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maBenhNhan);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhieuKham pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                pk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                pk.setMaChuyenKhoa(rs.getString("ma_chuyen_khoa"));
          
                
          
                pk.setNgayKham(rs.getDate("ngay_kham").toLocalDate()); 
                pk.setTrangThai(rs.getString("trang_thai"));
                
                list.add(pk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    
    public int getMaPhieuKhamTrongNgay(int maBenhNhan, int mack) {
        int maPhieu = 0;

        String sql = "SELECT ma_phieu_kham FROM phieu_kham WHERE ma_benh_nhan = ? AND ma_chuyen_khoa = ? AND DATE(ngay_kham) = CURDATE() ORDER BY ma_phieu_kham DESC LIMIT 1";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maBenhNhan);
            ps.setInt(2, mack);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                maPhieu = rs.getInt("ma_phieu_kham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPhieu;
    }
    

    public List<PhieuKham> getAllPhieuKhamDaKhamHomNay() {
        List<PhieuKham> list = new ArrayList<>();
        String sql = """
            SELECT 
                bn.ma_benh_nhan, bn.ho_ten, bn.so_dien_thoai,
                GROUP_CONCAT(pk.ma_phieu_kham) AS chuoi_id_phieu, -- Lấy "358,362,364"
                COUNT(pk.ma_phieu_kham) AS so_luong_phieu,
                MAX(pk.ngay_kham) AS ngay_kham_moi
            FROM phieu_kham pk
            JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
            WHERE pk.trang_thai = 'DA_KHAM' 
              AND DATE(pk.ngay_kham) = CURDATE()
            GROUP BY bn.ma_benh_nhan
            ORDER BY ngay_kham_moi DESC
        """;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuKham pk = new PhieuKham();
                pk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                pk.setGhiChu(rs.getString("chuoi_id_phieu")); 
                pk.setTrangThai(rs.getString("ho_ten"));     
                pk.setMaPhieuKham(rs.getInt("so_luong_phieu")); 
                list.add(pk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    
    public List<PhieuKham> getPhieuKhamTheoBenhNhanTrongNgay1(int maBN) {
        List<PhieuKham> list = new ArrayList<>();
        String sql = "SELECT pk.*, ck.ten_chuyen_khoa " +
                     "FROM phieu_kham pk " +
                     "JOIN chuyen_khoa ck ON pk.ma_chuyen_khoa = ck.ma_chuyen_khoa " +
                     "WHERE pk.ma_benh_nhan = ? AND DATE(pk.ngay_kham) = CURDATE() AND pk.trang_thai = 'DA_KHAM'";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKham pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaChuyenKhoa(rs.getString("ten_chuyen_khoa"));
                pk.setTrangThai(rs.getString("trang_thai"));
                list.add(pk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    
    public List<PhieuKham> getDanhSachBenhNhanThuNgan() {
        List<PhieuKham> list = new ArrayList<>();

        String sql = """
        SELECT 
    pk.ma_phieu_kham,
    bn.ma_benh_nhan, 
    bn.ho_ten, 
    bn.so_dien_thoai, 
    pk.ma_nhan_vien, 
    pk.ngay_kham, 
    pk.trang_thai,
    hd.trang_thai AS trang_thai_hd 
FROM dang_ky_kham_benh dk
JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.ma_benh_nhan
JOIN phieu_kham pk ON dk.ma_phieu_kham = pk.ma_phieu_kham
LEFT JOIN hoa_don hd ON dk.ma_phieu_kham = hd.ma_phieu_kham 
WHERE 
    (dk.trang_thai = 'DA_KHAM' OR hd.trang_thai = 'cần hoàn tiền' OR hd.trang_thai = 'đã hoàn tiền' OR hd.trang_thai = 'đã thanh toán')
    AND DATE(dk.thoi_gian_dang_ky) = CURDATE()
GROUP BY 
    bn.ma_benh_nhan, 
    bn.ho_ten, 
    bn.so_dien_thoai, 
    pk.ma_nhan_vien, 
    pk.ngay_kham, 
    pk.trang_thai,
    hd.trang_thai;
        	    """;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while (rs.next()) {
        	    PhieuKham pk = new PhieuKham();
        	    pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
        	    pk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
        	    pk.setMaNhanVien(rs.getInt("ma_nhan_vien"));

     
        	    String trangThaiPK = rs.getString("trang_thai");
        	    String trangThaiHD = rs.getString("trang_thai_hd");

  
        	    if (trangThaiHD != null && !trangThaiHD.isEmpty()) {
        	        pk.setTrangThai(trangThaiHD); 
        	    } else {
        	        pk.setTrangThai(trangThaiPK);
        	    }

        	    pk.setGhiChu(rs.getString("ho_ten")); 
        	    if (rs.getDate("ngay_kham") != null) {
        	        pk.setNgayKham(rs.getDate("ngay_kham").toLocalDate());
        	    }
        	    list.add(pk);
        	}
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    public List<PhieuKham> getPhieuKhamTheoBenhNhanTrongNgay(int maBN) {
        List<PhieuKham> list = new ArrayList<>();
        String sql = """
        		SELECT 
        pk.*, 
        ck.ten_chuyen_khoa 
    FROM dang_ky_kham_benh dk
    JOIN phieu_kham pk on dk.ma_phieu_kham = pk.ma_phieu_kham
    JOIN chuyen_khoa ck ON pk.ma_chuyen_khoa = ck.ma_chuyen_khoa 
    WHERE pk.ma_benh_nhan = ?
    AND DATE(pk.ngay_kham) = CURDATE() 
    AND pk.trang_thai = 'DA_THANH_TOAN'
        		""";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKham pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaChuyenKhoa(rs.getString("ten_chuyen_khoa"));
                pk.setTrangThai(rs.getString("trang_thai"));
                list.add(pk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    
    public List<PhieuKham> getDanhsachBenhNhanDaKhamTrongNgay(int maBN) {
        List<PhieuKham> list = new ArrayList<>();
        String sql = """
        		SELECT 
        pk.*, 
        ck.ten_chuyen_khoa 
    FROM dang_ky_kham_benh dk
    JOIN phieu_kham pk on dk.ma_phieu_kham = pk.ma_phieu_kham
    JOIN chuyen_khoa ck ON pk.ma_chuyen_khoa = ck.ma_chuyen_khoa 
    WHERE pk.ma_benh_nhan = ?
    AND DATE(pk.ngay_kham) = CURDATE() 
    AND pk.trang_thai = 'DA_KHAM'
        		""";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKham pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaChuyenKhoa(rs.getString("ten_chuyen_khoa"));
                pk.setTrangThai(rs.getString("trang_thai"));
                list.add(pk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    public List<PhieuKham> getAllPhieuKhamDaKhamHomNayByDK(String trangthai) {
        List<PhieuKham> list = new ArrayList<>();
        

        String sql = "SELECT pk.*, ck.ten_chuyen_khoa " +
                     "FROM phieu_kham pk " +
                     "JOIN dang_ky_kham_benh dk ON pk.ma_benh_nhan = dk.ma_benh_nhan " +
                     "   AND DATE(pk.ngay_kham) = DATE(dk.thoi_gian_dang_ky) " + 
                     "JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa " + 
                     "WHERE pk.trang_thai = ? " +
                 
                     "AND DATE(pk.ngay_kham) = CURDATE() " +
                     "ORDER BY pk.ma_phieu_kham DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)){
            	 
            	 ps.setString(1, trangthai);
            	 
             ResultSet rs = ps.executeQuery() ;

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
                pk.setGhiChu(rs.getString("ghi_chu"));

                if (rs.getDate("ngay_tao") != null) {
                    pk.setNgayTao(rs.getDate("ngay_tao").toLocalDate());
                }

                list.add(pk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    

    public int getMaPhieuKhamGocTuChiDinh(int maBN, int maCK_CLS) {
        int maPK = 0;

        String sql = "SELECT pk.ma_phieu_kham " +
                     "FROM phieu_kham pk " +
                     "JOIN phieu_chi_dinh pcd ON pk.ma_phieu_kham = pcd.ma_phieu_kham " +
                     "JOIN chi_tiet_chi_dinh ctcd ON pcd.ma_phieu_chi_dinh = ctcd.ma_phieu_chi_dinh " +
                     "JOIN dich_vu dv ON ctcd.ma_dich_vu = dv.ma_dich_vu " +
                     "WHERE pk.ma_benh_nhan = ? " +
                     "AND dv.ma_chuyen_khoa = ? " + 
                     "AND DATE(pk.ngay_kham) = CURDATE() " +
                     "ORDER BY pk.ma_phieu_kham DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, maBN);
            pstm.setInt(2, maCK_CLS);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    maPK = rs.getInt("ma_phieu_kham");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maPK;
    }
    
    public void lienKetPhieuKhamVaoDangKy(int idDangKy, int maPK) {
        String sql = "UPDATE dang_ky_kham_benh SET ma_phieu_kham = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPK);
            ps.setInt(2, idDangKy);
            ps.executeUpdate();
        } catch (Exception e) 
        { 
        	e.printStackTrace(); 
        }
        
        }
    
    public String getTrangThai(int maPK) {
        String trangThai = "";
        String sql = "SELECT trang_thai FROM phieu_kham WHERE ma_phieu_kham = ?";
        

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maPK);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    trangThai = rs.getString("trang_thai");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trangThai;
    }
}
