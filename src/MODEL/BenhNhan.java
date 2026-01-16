package MODEL;

import java.time.LocalDate;

public class BenhNhan {
	private int MaBenhNhan;
	private String HoTen;
	private Boolean GT;
	private LocalDate NgaySinh;
	private String DiaChi;
	private String SDT;
	private String email;
	private String NgheNghiep;
	private String NhomMau;
	private String DiUngThuoc;
	private String NguoiGiamHo;
	private long SDTNguoiGiamHo;
	private String GhiChu;
	private long CCCD;
	
	public String GioiTinh() {
		if(GT == true) {
			
			return "Nam";
		}
		else 
			return "Nữ";
		 
	 }
	 
	public int getMaBenhNhan() {
		return MaBenhNhan;
	}
	public void setMaBenhNhan(int maBenhNhan) {
		MaBenhNhan = maBenhNhan;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public Boolean getGT() {
		return GT;
	}
	public void setGT(Boolean gT) {
		GT = gT;
	}
	public LocalDate getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNgheNghiep() {
		return NgheNghiep;
	}
	public void setNgheNghiep(String ngheNghiep) {
		NgheNghiep = ngheNghiep;
	}
	public String getNhomMau() {
		return NhomMau;
	}
	public void setNhomMau(String nhomMau) {
		NhomMau = nhomMau;
	}
	public String getDiUngThuoc() {
		return DiUngThuoc;
	}
	public void setDiUngThuoc(String diUngThuoc) {
		DiUngThuoc = diUngThuoc;
	}
	public String getNguoiGiamHo() {
		return NguoiGiamHo;
	}
	public void setNguoiGiamHo(String nguoiGiamHo) {
		NguoiGiamHo = nguoiGiamHo;
	}
	public long getSDTNguoiGiamHo() {
		return SDTNguoiGiamHo;
	}
	public void setSDTNguoiGiamHo(long sDTNguoiGiamHo) {
		SDTNguoiGiamHo = sDTNguoiGiamHo;
	}
	public String getGhiChu() {
		return GhiChu;
	}
	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}
	public long getCCCD() {
		return CCCD;
	}
	public void setCCCD(long cCCD) {
		CCCD = cCCD;
	}
	public BenhNhan(String hoTen , LocalDate ngaySinh, String diaChi, String sDT, String email,
			String ngheNghiep, String nhomMau, String diUngThuoc, String nguoiGiamHo, long sDTNguoiGiamHo,
			String ghiChu, long cCCD,Boolean gT) {
		super();
		HoTen = hoTen;

		NgaySinh = ngaySinh;
		DiaChi = diaChi;
		SDT = sDT;
		this.email = email;
		NgheNghiep = ngheNghiep;
		NhomMau = nhomMau;
		DiUngThuoc = diUngThuoc;
		NguoiGiamHo = nguoiGiamHo;
		SDTNguoiGiamHo = sDTNguoiGiamHo;
		GhiChu = ghiChu;
		CCCD = cCCD;
		GT = gT;
	}
	public BenhNhan() {
		super();
	}
	
	
	
	
	

}
