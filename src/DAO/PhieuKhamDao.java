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
    public int TroLyTQTaoPhieuKham(int maBenhNhan, int maNhanVien, String maChuyenKhoa) {
    	   int generatedKey = 0;
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
            	generatedKey = rs.getInt(1); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedKey;
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
    
    public int updateTrangThaiHoantatKhamBenh(int maPK) {
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
    	String sql = "UPDATE phieu_kham SET ma_chuyen_khoa = ?, ghi_chu = ? WHERE ma_phieu_kham = ?";

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
    
    public int getMaPhieuKhamByBenhNhan(int maBenhNhan) {
        int maPhieu = 0;

        String sql = "SELECT ma_phieu_kham FROM phieu_kham " +
                "WHERE ma_benh_nhan = ? " +
                "AND DATE(ngay_kham) = CURDATE() " + 
                "ORDER BY ma_phieu_kham DESC LIMIT 1"; 
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, maBenhNhan);
            
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                maPhieu = rs.getInt("ma_phieu_kham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPhieu;
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
        
     
        String sql = "SELECT ma_phieu_kham, ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, ngay_kham, trang_thai " +
                     "FROM phieu_kham " +
                     "WHERE ma_benh_nhan = ? " +
                     "AND DATE(ngay_kham) < CURDATE() " +
                     "ORDER BY ngay_kham DESC";

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
    
    
    public int getMaPhieuKhamTrongNgay(int maBenhNhan) {
        int maPhieu = 0;

        String sql = "SELECT ma_phieu_kham FROM phieu_kham WHERE ma_benh_nhan = ? AND DATE(ngay_kham) = CURDATE() ORDER BY ma_phieu_kham DESC LIMIT 1";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maBenhNhan);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                maPhieu = rs.getInt("ma_phieu_kham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPhieu;
    }
    
    
 // Lấy danh sách tất cả các phiếu khám ĐÃ KHÁM trong ngày HÔM NAY
 // Lấy danh sách phiếu đã khám hôm nay, kèm theo Tên Chuyên Khoa chính xác
    public List<PhieuKham> getAllPhieuKhamDaKhamHomNay() {
        List<PhieuKham> list = new ArrayList<>();
        
        // Câu lệnh SQL:
        // 1. Lấy tất cả thông tin từ phieu_kham (pk.*)
        // 2. Lấy thêm tên chuyên khoa (ck.ten_chuyen_khoa) để hiển thị
        String sql = "SELECT pk.*, ck.ten_chuyen_khoa " +
                     "FROM phieu_kham pk " +
                     "JOIN dang_ky_kham_benh dk ON pk.ma_benh_nhan = dk.ma_benh_nhan " +
                     "   AND DATE(pk.ngay_kham) = DATE(dk.thoi_gian_dang_ky) " + // Khớp bệnh nhân trong cùng ngày
                     "JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa " + // Lấy tên CK từ bảng chuyên khoa
                     "WHERE (pk.trang_thai = 'DA_KHAM' " +
                     "OR pk.trang_thai = 'DA_THANH_TOAN') " +
                     "AND DATE(pk.ngay_kham) = CURDATE() " +
                     "ORDER BY pk.ma_phieu_kham DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PhieuKham pk = new PhieuKham();
                pk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                pk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                pk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                
                // QUAN TRỌNG: Lấy tên chuyên khoa từ bảng JOIN và set vào model
                // Lúc hiển thị lên bảng, cột Chuyên khoa sẽ hiện tên (Vd: "Tai Mũi Họng") thay vì mã
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
    
    
    public List<PhieuKham> getAllPhieuKhamDaKhamHomNayByDK(String trangthai) {
        List<PhieuKham> list = new ArrayList<>();
        
        // Câu lệnh SQL:
        // 1. Lấy tất cả thông tin từ phieu_kham (pk.*)
        // 2. Lấy thêm tên chuyên khoa (ck.ten_chuyen_khoa) để hiển thị
        String sql = "SELECT pk.*, ck.ten_chuyen_khoa " +
                     "FROM phieu_kham pk " +
                     "JOIN dang_ky_kham_benh dk ON pk.ma_benh_nhan = dk.ma_benh_nhan " +
                     "   AND DATE(pk.ngay_kham) = DATE(dk.thoi_gian_dang_ky) " + // Khớp bệnh nhân trong cùng ngày
                     "JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa " + // Lấy tên CK từ bảng chuyên khoa
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
                
                // QUAN TRỌNG: Lấy tên chuyên khoa từ bảng JOIN và set vào model
                // Lúc hiển thị lên bảng, cột Chuyên khoa sẽ hiện tên (Vd: "Tai Mũi Họng") thay vì mã
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
}
