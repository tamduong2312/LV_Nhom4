package MODEL;

import java.sql.Timestamp;

public class HoaDon {
    private int maHoaDon;
    private int maPhieuKham;
    private int maNhanVien;
    private double tongTien;
    private Timestamp ngayThanhToan;
    private String ghiChu;
    private String trangThai;
    
    private String tenBenhNhan;

    public String getTenBenhNhan() {
		return tenBenhNhan;
	}

	public void setTenBenhNhan(String tenBenhNhan) {
		this.tenBenhNhan = tenBenhNhan;
	}

	public HoaDon() {}

    public HoaDon(int maPhieuKham, int maNhanVien, double tongTien, String ghiChu) {
        this.maPhieuKham = maPhieuKham;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
        this.trangThai = "da thanh toan";
    }


    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }
    public int getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }
    public int getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(int maNhanVien) { this.maNhanVien = maNhanVien; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    public Timestamp getNgayThanhToan() { return ngayThanhToan; }
    public void setNgayThanhToan(Timestamp ngayThanhToan) { this.ngayThanhToan = ngayThanhToan; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}