package MODEL;
import java.util.Date;

public class PhieuChiDinh {
    private int maPhieuChiDinh;
    private int maPhieuKham;
    private int maNguoiChiDinh;
    private Date ngayChiDinh;
    private double tongTien;
    private String trangThai; 
	public int getMaPhieuChiDinh() {
		return maPhieuChiDinh;
	}
	public void setMaPhieuChiDinh(int maPhieuChiDinh) {
		this.maPhieuChiDinh = maPhieuChiDinh;
	}
	public int getMaPhieuKham() {
		return maPhieuKham;
	}
	public void setMaPhieuKham(int maPhieuKham) {
		this.maPhieuKham = maPhieuKham;
	}
	public int getMaNguoiChiDinh() {
		return maNguoiChiDinh;
	}
	public void setMaNguoiChiDinh(int maNguoiChiDinh) {
		this.maNguoiChiDinh = maNguoiChiDinh;
	}
	public Date getNgayChiDinh() {
		return ngayChiDinh;
	}
	public void setNgayChiDinh(Date ngayChiDinh) {
		this.ngayChiDinh = ngayChiDinh;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public PhieuChiDinh(int maPhieuKham, int maNguoiChiDinh, Date ngayChiDinh, double tongTien, String trangThai) {
		super();
		this.maPhieuKham = maPhieuKham;
		this.maNguoiChiDinh = maNguoiChiDinh;
		this.ngayChiDinh = ngayChiDinh;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}
	public PhieuChiDinh() {
		super();
	}


}