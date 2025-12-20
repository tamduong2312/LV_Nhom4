package MODEL;

public class PhongChucNang {
private int maphong;
private String tenphong;
private String loaiphong;
public int getMaphong() {
	return maphong;
}
public void setMaphong(int maphong) {
	this.maphong = maphong;
}
public String getTenphong() {
	return tenphong;
}
public void setTenphong(String tenphong) {
	this.tenphong = tenphong;
}
public String getLoaiphong() {
	return loaiphong;
}
public void setLoaiphong(String loaiphong) {
	this.loaiphong = loaiphong;
}
public PhongChucNang(String tenphong, String loaiphong) {
	super();
	this.tenphong = tenphong;
	this.loaiphong = loaiphong;
}
public PhongChucNang() {
	super();
}

}
