package MODEL;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class NhanVien {
 public int MaNV;
 public String HoTen;
 public Boolean GT;
 public LocalDate Ngay_sinh;
 public long CCCD;
 public String DiaChi;
 public long SDT;
 public String Email;
 public String BangCap;
 public String ChucVu;
 public String MaChuyenKhoa;
 public LocalDate NgayVaoLam;
 public Time Calam;
 

 public Time getCalam() {
	return Calam;
}
public void setCalam(Time calam) {
	Calam = calam;
}


public int MaNguoiDung;
 public Role role;
public int getMaNV() {
	return MaNV;
}
public void setMaNV(int maNV) {
	MaNV = maNV;
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
public LocalDate getNgay_sinh() {
	return Ngay_sinh;
}
public void setNgay_sinh(LocalDate ngay_sinh) {
	Ngay_sinh = ngay_sinh;
}
public long getCCCD() {
	return CCCD;
}
public void setCCCD(long cCCD) {
	CCCD = cCCD;
}
public String getDiaChi() {
	return DiaChi;
}
public void setDiaChi(String diaChi) {
	DiaChi = diaChi;
}
public long getSDT() {
	return SDT;
}
public void setSDT(long sDT) {
	SDT = sDT;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getBangCap() {
	return BangCap;
}
public void setBangCap(String bangCap) {
	BangCap = bangCap;
}
public String getChucVu() {
	return ChucVu;
}
public void setChucVu(String chucVu) {
	ChucVu = chucVu;
}
public String getMaChuyenKhoa() {
	return MaChuyenKhoa;
}
public void setMaChuyenKhoa(String maChuyenKhoa) {
	MaChuyenKhoa = maChuyenKhoa;
}
public LocalDate getNgayVaoLam() {
	return NgayVaoLam;
}
public void setNgayVaoLam(LocalDate ngayVaoLam) {
	NgayVaoLam = ngayVaoLam;
}

public int getMaNguoiDung() {
	return MaNguoiDung;
}
public void setMaNguoiDung(int maNguoiDung) {
	MaNguoiDung = maNguoiDung;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
 
 
 
public enum Role {
    QUAN_TRI,
    BAC_SI,
    LE_TAN,
    THU_NGAN,
    DUOC_SI,
    KHO,
    QUAN_LY
}



public NhanVien() {
	super();
}

public NhanVien(String hoTen, Boolean gT, LocalDate ngay_sinh, long cCCD, String diaChi, long sDT, String email,
		String bangCap, String chucVu, String maChuyenKhoa, LocalDate ngayVaoLam, int maNguoiDung,
		Role role) {
	super();
	HoTen = hoTen;
	GT = gT;
	Ngay_sinh = ngay_sinh;
	CCCD = cCCD;
	DiaChi = diaChi;
	SDT = sDT;
	Email = email;
	BangCap = bangCap;
	ChucVu = chucVu;
	MaChuyenKhoa = maChuyenKhoa;
	NgayVaoLam = ngayVaoLam;

	MaNguoiDung = maNguoiDung;
	this.role = role;
}


 public NhanVien(String hoTen, Boolean gT, LocalDate ngay_sinh, long cCCD, String diaChi, long sDT, String email,
		String bangCap, String chucVu, String maChuyenKhoa, LocalDate ngayVaoLam, Time calam, int maNguoiDung, Role role) {
	super();
	HoTen = hoTen;
	GT = gT;
	Ngay_sinh = ngay_sinh;
	CCCD = cCCD;
	DiaChi = diaChi;
	SDT = sDT;
	Email = email;
	BangCap = bangCap;
	ChucVu = chucVu;
	MaChuyenKhoa = maChuyenKhoa;
	NgayVaoLam = ngayVaoLam;
	Calam = calam;
	MaNguoiDung = maNguoiDung;
	this.role = role;
}
public String GioiTinh() {
	if(GT == true) {
		
		return "Nam";
	}
	else 
		return "Nữ";
	 
 }
 
 
}
