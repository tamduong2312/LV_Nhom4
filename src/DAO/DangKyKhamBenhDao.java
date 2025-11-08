package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import MODEL.DangKyKhamBenh;
import MODEL.DangKyKhamBenh.TRANGTHAI;
import MODEL.NhanVien.Role;

public class DangKyKhamBenhDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "12345";

    // ============================
    // 1. LẤY TẤT CẢ ĐĂNG KÝ KHÁM
    // ============================
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

    // ==========================================
    // 2. LẤY SỐ THỨ TỰ TIẾP THEO TRONG NGÀY
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
    // 3. THÊM ĐĂNG KÝ KHÁM BỆNH
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
    // 4. CẬP NHẬT TRẠNG THÁI
    // ==========================================
    public int capNhatTrangThai(int id, String trangThai) {
        String query = "UPDATE dang_ky_kham_benh SET trang_thai = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, trangThai);
            pstm.setInt(2, id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================
    // 5. XÓA / HỦY ĐĂNG KÝ
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
    // 6. TÌM KIẾM
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
}
