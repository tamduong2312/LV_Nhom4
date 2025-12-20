package MODEL;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PhieuNhapThuoc {
    private int maPhieuNhapThuoc;
    private LocalDate ngayNhap;
    private int maNhanVienNhap;
    private int maNhaCungCap;
    private double tongTienNhap;
    private String trangThai;
    private String ghiChu;

    public PhieuNhapThuoc() {
    }

    public PhieuNhapThuoc(int maPhieuNhapThuoc, LocalDate ngayNhap, int maNhanVienNhap, int maNhaCungCap, double tongTienNhap, String trangThai, String ghiChu) {
        this.maPhieuNhapThuoc = maPhieuNhapThuoc;
        this.ngayNhap = ngayNhap;
        this.maNhanVienNhap = maNhanVienNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.tongTienNhap = tongTienNhap;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaPhieuNhapThuoc() {
        return maPhieuNhapThuoc;
    }

    public void setMaPhieuNhapThuoc(int maPhieuNhapThuoc) {
        this.maPhieuNhapThuoc = maPhieuNhapThuoc;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate localDate) {
        this.ngayNhap = localDate;
    }

    public int getMaNhanVienNhap() {
        return maNhanVienNhap;
    }

    public void setMaNhanVienNhap(int maNhanVienNhap) {
        this.maNhanVienNhap = maNhanVienNhap;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public double getTongTienNhap() {
        return tongTienNhap;
    }

    public void setTongTienNhap(double tongTienNhap) {
        this.tongTienNhap = tongTienNhap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}