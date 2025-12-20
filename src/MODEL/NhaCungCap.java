package MODEL;

public class NhaCungCap {
private int MaNhaCungCap;
private String TenNhaCungCap;
private String DiaChi;
private long SDT;
private String Email;
private String ghichu;
public int getMaNhaCungCap() {
	return MaNhaCungCap;
}
public void setMaNhaCungCap(int maNhaCungCap) {
	MaNhaCungCap = maNhaCungCap;
}
public String getTenNhaCungCap() {
	return TenNhaCungCap;
}
public void setTenNhaCungCap(String tenNhaCungCap) {
	TenNhaCungCap = tenNhaCungCap;
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
public String getGhichu() {
	return ghichu;
}
public void setGhichu(String ghichu) {
	this.ghichu = ghichu;
}
public NhaCungCap(String tenNhaCungCap, String diaChi, long sDT, String email, String ghichu) {
	super();
	TenNhaCungCap = tenNhaCungCap;
	DiaChi = diaChi;
	SDT = sDT;
	Email = email;
	this.ghichu = ghichu;
}
public NhaCungCap() {
	super();
}



}
