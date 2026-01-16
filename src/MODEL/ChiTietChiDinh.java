package MODEL;

import java.util.Date;

public class ChiTietChiDinh {
    private int id;
    private int maPhieuChiDinh;
    private int maDichVu;
    private int soLuong;
    private double donGia;
    private Date ngaythuhien; 
    private String trangThaiDV;
    private int maNhanVienThucHien;
    
    
	public int getMaNhanVienThucHien() {
		return maNhanVienThucHien;
	}
	public void setMaNhanVienThucHien(int maNhanVienThucHien) {
		this.maNhanVienThucHien = maNhanVienThucHien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaPhieuChiDinh() {
		return maPhieuChiDinh;
	}
	public void setMaPhieuChiDinh(int maPhieuChiDinh) {
		this.maPhieuChiDinh = maPhieuChiDinh;
	}
	public int getMaDichVu() {
		return maDichVu;
	}
	
	public Date getNgaythuhien() {
		return ngaythuhien;
	}
	public void setNgaythuhien(Date ngaythuhien) {
		this.ngaythuhien = ngaythuhien;
	}
	public void setMaDichVu(int maDichVu) {
		this.maDichVu = maDichVu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public String getTrangThaiDV() {
		return trangThaiDV;
	}
	public void setTrangThaiDV(String trangThaiDV) {
		this.trangThaiDV = trangThaiDV;
	}
	public ChiTietChiDinh(int maPhieuChiDinh, int maDichVu, int soLuong, double donGia, String trangThaiDV, Date Ngaythuchien) {
		super();
		this.maPhieuChiDinh = maPhieuChiDinh;
		this.maDichVu = maDichVu;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.trangThaiDV = trangThaiDV;
		this.ngaythuhien = Ngaythuchien;
	}
	
	public ChiTietChiDinh(int maPhieuChiDinh, int maDichVu, int soLuong, double donGia, Date ngaythuhien,
			String trangThaiDV, int maNhanVienThucHien) {
		super();
		this.maPhieuChiDinh = maPhieuChiDinh;
		this.maDichVu = maDichVu;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.ngaythuhien = ngaythuhien;
		this.trangThaiDV = trangThaiDV;
		this.maNhanVienThucHien = maNhanVienThucHien;
	}
	public ChiTietChiDinh() {
		super();
	}

}