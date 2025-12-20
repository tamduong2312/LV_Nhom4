package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import MODEL.DangKyKhamBenh;
import MODEL.DangKyKhamBenh.TRANGTHAI;


public class DangKyKhamBenhDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public List<DangKyKhamBenh> getAll() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = "SELECT * FROM dang_ky_kham_benh ORDER BY thoi_gian_dang_ky DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // lay tat ca benh nhan trong ngay hom nay
    public List<DangKyKhamBenh> getAllhomnay() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
            SELECT * 
            FROM dang_ky_kham_benh
            WHERE DATE(thoi_gian_dang_ky) = CURDATE()
            ORDER BY so_thu_tu ASC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    // lay tat ca benh nhan trong ngay hom nay của trợ lý tổng quát
    public List<DangKyKhamBenh> getAlltodayTroLyTQ(int mack) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
        	    SELECT * FROM dang_ky_kham_benh
        	    WHERE DATE(thoi_gian_dang_ky) = CURDATE() 
        	      AND  ma_chuyen_khoa = ? AND trang_thai != 'DA_KHAM' 	      
        	    ORDER BY so_thu_tu ASC
        	""";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)){
        	
        	pstm.setInt(1, mack);
             ResultSet rs = pstm.executeQuery() ;

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<DangKyKhamBenh> getAllTodayByChuyenKhoa(int maChuyenKhoa) {
        List<DangKyKhamBenh> list = new ArrayList<>();

        String query = """
            SELECT 
                dk.id,
                dk.ma_benh_nhan,
                dk.ma_nhan_vien,
                dk.ma_chuyen_khoa,
                dk.so_thu_tu,
                dk.ghi_chu,
                dk.thoi_gian_dang_ky,
                
                dk.trang_thai,                     
                pk.trang_thai AS trang_thai_phieu  
            FROM 
                dang_ky_kham_benh dk
            INNER JOIN 
                phieu_kham pk ON dk.ma_benh_nhan = pk.ma_benh_nhan 
            WHERE 
                DATE(dk.thoi_gian_dang_ky) = CURDATE() 
                AND DATE(pk.ngay_kham) = CURDATE()
                AND dk.ma_chuyen_khoa = ?
                AND pk.trang_thai != 'DA_KHAM'
                AND pk.trang_thai != 'CHO_THANH_TOAN'
                AND pk.trang_thai != 'DA_THANH_TOAN'
                AND pk.trang_thai != 'DA_CAP_THUOC'
                
            ORDER BY 
                dk.so_thu_tu ASC;
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

     
            pstm.setInt(1, maChuyenKhoa);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    DangKyKhamBenh dk = new DangKyKhamBenh();

                    dk.setId(rs.getInt("id"));
                    dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                    dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                    dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                    dk.setSothutu(rs.getInt("so_thu_tu"));
                    dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                    dk.setGhichu(rs.getString("ghi_chu"));
                    

                    dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                    dk.setTrangThaiHienThi(rs.getString("trang_thai_phieu"));

                    list.add(dk);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<DangKyKhamBenh> getAllTodayByBSChuyenKhoa(int maChuyenKhoa) {
    List<DangKyKhamBenh> list = new ArrayList<>();


    String query = """
        SELECT 
            dk.id,                         
            pk.ma_benh_nhan, 
            pk.ma_nhan_vien, 
            pk.ma_chuyen_khoa,                
            dk.so_thu_tu,               
            pk.ghi_chu, 
            pk.ngay_kham AS thoi_gian,
            dk.trang_thai AS trang_thai_dk,    
            pk.trang_thai AS trang_thai_phieu  
        FROM 
            phieu_kham pk
        INNER JOIN 
            dang_ky_kham_benh dk ON pk.ma_benh_nhan = dk.ma_benh_nhan 
            AND DATE(dk.thoi_gian_dang_ky) = DATE(pk.ngay_kham)
        WHERE 
            DATE(pk.ngay_kham) = CURDATE() 
            AND pk.ma_chuyen_khoa = ?          
            AND pk.trang_thai NOT IN ('DA_KHAM', 'CHO_THANH_TOAN', 'DA_THANH_TOAN', 'DA_CAP_THUOC')
        ORDER BY 
            dk.so_thu_tu ASC;
    """;

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstm = conn.prepareStatement(query)) {

        pstm.setInt(1, maChuyenKhoa);

        try (ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

             
                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));        
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian").toLocalDateTime());
                dk.setGhichu(rs.getString("ghi_chu"));
                
        
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai_dk")));
                dk.setTrangThaiHienThi(rs.getString("trang_thai_phieu"));

                list.add(dk);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
    // ==========================================
    //  LẤY SỐ THỨ TỰ TIẾP THEO TRONG NGÀY
    // ==========================================
    public int laySoThuTuTiepTheo(int maChuyenKhoa) {
        String query = """
            SELECT COUNT(*) AS so 
            FROM dang_ky_kham_benh
            WHERE ma_chuyen_khoa = ?
              AND DATE(thoi_gian_dang_ky) = CURDATE()
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, maChuyenKhoa);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("so") + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    // ==========================================
    //  THÊM ĐĂNG KÝ KHÁM BỆNH
    // ==========================================
    public int themDangKy(DangKyKhamBenh dk) {

        String query = """
            INSERT INTO dang_ky_kham_benh 
            (ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, so_thu_tu,
             thoi_gian_dang_ky, trang_thai, ghi_chu)
            VALUES (?, ?, ?, ?, NOW(), ?, ?)
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, dk.getMabenhnhan());
            pstm.setObject(2, dk.getManhanvien() == 0 ? null : dk.getManhanvien());
            pstm.setInt(3, dk.getMachuyenkhoa());
            pstm.setInt(4, dk.getSothutu());
            pstm.setString(5, dk.getTrangthai().name());
            pstm.setString(6, dk.getGhichu());

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================
    //  CẬP NHẬT TRẠNG THÁI
    // ==========================================
    public int capNhatTrangThai(int id) {
        String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'DANG_KHAM' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1,id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    
    public boolean capNhatChuyenKhoaDangKy(int maBenhNhan, int maChuyenKhoaMoi) {

        String sql = """
            UPDATE dang_ky_kham_benh 
            SET ma_chuyen_khoa = ?, 
                trang_thai = 'CHO_KHAM_BS'
            WHERE ma_benh_nhan = ? 
              AND DATE(thoi_gian_dang_ky) = CURDATE()
              AND trang_thai != 'DA_KHAM';
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maChuyenKhoaMoi);
            ps.setInt(2, maBenhNhan);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public int capNhatDaKhamByMaBenhNhan(int maBenhNhan) {
        String query = """
            UPDATE dang_ky_kham_benh dk
            INNER JOIN phieu_kham pk ON dk.ma_benh_nhan = pk.ma_benh_nhan
            SET dk.trang_thai = 'DA_KHAM'
            WHERE dk.ma_benh_nhan = ? 
              AND DATE(dk.thoi_gian_dang_ky) = CURDATE()
              AND DATE(pk.ngay_kham) = CURDATE();
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1, maBenhNhan);

        
            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    // cập nhật trạng thái vắng mặt 
    public int capNhatTrangThaiVangMat(int id) {
        String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'VANG_MAT' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1,id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    // lấy danh sách bệnh nhân vắng mặt ngày hôm nay
    public List<DangKyKhamBenh> getAllVangMatToDay() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
            SELECT * 
            FROM dang_ky_kham_benh
            WHERE DATE(thoi_gian_dang_ky) = CURDATE() AND trang_thai='VANG_MAT' AND ma_chuyen_khoa = 1
            ORDER BY so_thu_tu ASC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    // updateTrangThaiChoKhamBS//
    public int updateTrangThaiChoKhamBS(int maPK) {
    	int kq = 0;
        String sql = "UPDATE dang_ky_kham_benh SET trang_thai='CHO_KHAM_BS' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public int capNhatTrangThaiHUY(int id) {
        String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'HUY' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1, id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================
    //  XÓA / HỦY ĐĂNG KÝ
    // ==========================================
    public int xoaDangKy(int id) {
        String query = "DELETE FROM dang_ky_kham_benh WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, id);
            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================
    //  TÌM KIẾM
    // ==========================================
    public List<DangKyKhamBenh> timKiem(String keyword) {

        List<DangKyKhamBenh> list = new ArrayList<>();

        String query = """
            SELECT dk.* 
            FROM dang_ky_kham_benh dk 
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.id
            WHERE bn.ho_ten LIKE ?
               OR dk.so_thu_tu LIKE ?
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            String value = "%" + keyword.trim() + "%";
            pstmt.setString(1, value);
            pstmt.setString(2, value);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
 // Đăng ký số thứ tự mới cho bệnh nhân quay lại khám
  
    public boolean DangKySTTmoidobenhnhanquaylaikham(int idDangKy) {
        Connection conn = null;
        PreparedStatement pstmGet = null;
        PreparedStatement pstmUpdate = null;
        ResultSet rs = null;
        
        //  Lấy STT nhỏ nhất hiện tại trong danh sách chờ
        String sqlGetMin = """
            SELECT MIN(so_thu_tu) 
            FROM dang_ky_kham_benh
            WHERE DATE(thoi_gian_dang_ky) = CURDATE() 
            AND (trang_thai = 'DANG_KHAM')
        """;

        //  Cập nhật STT mới và Trạng thái cho bệnh nhân quay lại
        String sqlUpdate = "UPDATE dang_ky_kham_benh SET so_thu_tu = ?, trang_thai = 'CHO_KHAM' WHERE id = ?";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
       
            conn.setAutoCommit(false); 

            // --- TÍNH STT MỚI ---
            pstmGet = conn.prepareStatement(sqlGetMin);
            rs = pstmGet.executeQuery();
            
            int sttMoi = 1; // 
            
            if (rs.next()) {
                int minStt = rs.getInt(1);
                if (minStt > 0) {
                
                    sttMoi = minStt; 
                }
            }

            // ---  UPDATE VÀO DB ---
            pstmUpdate = conn.prepareStatement(sqlUpdate);
            pstmUpdate.setInt(1, sttMoi);
            pstmUpdate.setInt(2, idDangKy);
            
            int rowAffected = pstmUpdate.executeUpdate();
            
            if (rowAffected > 0) {
                conn.commit(); 
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try { 
            	
            	if (conn != null)
            		conn.rollback(); 
            	} 
            catch (SQLException ex) {
            	
            }
            return false;
        } finally {

            try {
                if (rs != null) rs.close();
                if (pstmGet != null) pstmGet.close();
                if (pstmUpdate != null) pstmUpdate.close();
                if (conn != null) conn.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    // lay tat ca benh nhan trong ngay hom nay của trợ lý tổng quát

//    public List<DangKyKhamBenh> getAlltodayTroLyCK(String tenChuyenKhoa) {
//        List<DangKyKhamBenh> list = new ArrayList<>();
//        
//      
//        String query = """
//            SELECT dk.* 
//            FROM dang_ky_kham_benh dk
//            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
//            WHERE DATE(dk.thoi_gian_dang_ky) = CURDATE() 
//              AND dk.trang_thai = 'CHO_KHAM' 
//              AND ck.ten_chuyen_khoa = ? 
//            ORDER BY dk.so_thu_tu ASC
//        """;
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             PreparedStatement pstm = conn.prepareStatement(query)) {
//            
//
//            pstm.setString(1, tenChuyenKhoa);
//            
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                DangKyKhamBenh dk = new DangKyKhamBenh();
//
//                dk.setId(rs.getInt("id"));
//                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
//                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
//                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
//                dk.setSothutu(rs.getInt("so_thu_tu"));
//                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
//                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
//                dk.setGhichu(rs.getString("ghi_chu"));
//
//                list.add(dk);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
    
    
    public List<DangKyKhamBenh> getAlltodayTroLyCK(int mack) {
        List<DangKyKhamBenh> list = new ArrayList<>();


        String query = """
            SELECT 
                dk.id,                         
                pk.ma_benh_nhan, 
                pk.ma_nhan_vien, 
                pk.ma_chuyen_khoa,                
                dk.so_thu_tu,               
                pk.ghi_chu, 
                pk.ngay_kham AS thoi_gian,
                dk.trang_thai AS trang_thai_dk,    
                pk.trang_thai AS trang_thai_phieu  
            FROM 
                phieu_kham pk
            INNER JOIN 
                dang_ky_kham_benh dk ON pk.ma_benh_nhan = dk.ma_benh_nhan 
                AND DATE(dk.thoi_gian_dang_ky) = DATE(pk.ngay_kham)
            WHERE 
                DATE(pk.ngay_kham) = CURDATE() 
                AND pk.ma_chuyen_khoa = ?          
                AND pk.trang_thai NOT IN ('DA_KHAM', 'CHO_THANH_TOAN', 'DA_THANH_TOAN', 'DA_CAP_THUOC')
            ORDER BY 
                dk.so_thu_tu ASC;
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, mack);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    DangKyKhamBenh dk = new DangKyKhamBenh();

                 
                    dk.setId(rs.getInt("id"));
                    dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                    dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                    dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                    dk.setSothutu(rs.getInt("so_thu_tu"));        
                    dk.setThoigiandangky(rs.getTimestamp("thoi_gian").toLocalDateTime());
                    dk.setGhichu(rs.getString("ghi_chu"));
                    
            
                    dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai_dk")));
                    dk.setTrangThaiHienThi(rs.getString("trang_thai_phieu"));

                    list.add(dk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DangKyKhamBenh> getAllVangMatCKToDay(String ck) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
                SELECT dk.* 
                FROM dang_ky_kham_benh dk
                JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
                WHERE DATE(dk.thoi_gian_dang_ky) = CURDATE() 
                  AND dk.trang_thai = 'VANG_MAT' 
                  AND ck.ten_chuyen_khoa = ? 
                ORDER BY dk.so_thu_tu ASC
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)){
            pstm.setString(1, ck);
             ResultSet rs = pstm.executeQuery() ;
    
            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public List<DangKyKhamBenh> getAllLichSuDangKy() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String sql = """
            SELECT dk.*, bn.ho_ten, bn.so_dien_thoai, ck.ten_chuyen_khoa
            FROM dang_ky_kham_benh dk
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
            ORDER BY dk.thoi_gian_dang_ky DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. HÀM TÌM KIẾM THEO TỪ KHÓA (Tên BN, SĐT, CCCD)
    public List<DangKyKhamBenh> timKiemLichSuTheoText(String keyword) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String sql = """
            SELECT dk.*, bn.ho_ten, bn.so_dien_thoai, ck.ten_chuyen_khoa
            FROM dang_ky_kham_benh dk
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
            WHERE bn.ho_ten LIKE ? 
               OR bn.so_dien_thoai LIKE ?
               OR CAST(bn.cccd AS CHAR) LIKE ?
            ORDER BY dk.thoi_gian_dang_ky DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String val = "%" + keyword.trim() + "%";
            ps.setString(1, val);
            ps.setString(2, val);
            ps.setString(3, val);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. HÀM LỌC THEO NGÀY
    public List<DangKyKhamBenh> locLichSuTheoNgay(String tuNgay, String denNgay) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String sql = """
            SELECT dk.*, bn.ho_ten, bn.so_dien_thoai, ck.ten_chuyen_khoa
            FROM dang_ky_kham_benh dk
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.id
            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
            WHERE DATE(dk.thoi_gian_dang_ky) BETWEEN ? AND ?
            ORDER BY dk.thoi_gian_dang_ky DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    private DangKyKhamBenh mapRowToModel(ResultSet rs) throws SQLException {
        DangKyKhamBenh dk = new DangKyKhamBenh();
        dk.setId(rs.getInt("id"));
        dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
        dk.setManhanvien(rs.getInt("ma_nhan_vien"));
        dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
        dk.setSothutu(rs.getInt("so_thu_tu"));
        dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
        dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
        

        
        return dk;
    }
}
