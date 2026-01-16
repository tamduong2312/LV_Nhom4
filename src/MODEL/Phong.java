package MODEL;

public class Phong {
public int maphong;
public String tenphong;
public String Loaiphong;
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
	return Loaiphong;
}
public void setLoaiphong(String loaiphong) {
	Loaiphong = loaiphong;
}
public Phong() {
	super();
}
public Phong(String tenphong, String loaiphong) {
	super();
	this.tenphong = tenphong;
	Loaiphong = loaiphong;
}



}
