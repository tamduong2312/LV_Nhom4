package MODEL;

public class ChuyenKhoa {
	public int ma_chuyen_khoa;
	public String ten_chuyen_khoa;
	public String mo_ta;
	public int getMa_chuyen_khoa() {
		return ma_chuyen_khoa;
	}
	public void setMa_chuyen_khoa(int ma_chuyen_khoa) {
		this.ma_chuyen_khoa = ma_chuyen_khoa;
	}
	public String getTen_chuyen_khoa() {
		return ten_chuyen_khoa;
	}
	public void setTen_chuyen_khoa(String ten_chuyen_khoa) {
		this.ten_chuyen_khoa = ten_chuyen_khoa;
	}
	public String getMo_ta() {
		return mo_ta;
	}
	public void setMo_ta(String mo_ta) {
		this.mo_ta = mo_ta;
	}
	public ChuyenKhoa(int ma_chuyen_khoa, String ten_chuyen_khoa, String mo_ta) {
		super();
		this.ma_chuyen_khoa = ma_chuyen_khoa;
		this.ten_chuyen_khoa = ten_chuyen_khoa;
		this.mo_ta = mo_ta;
	}
	public ChuyenKhoa() {
		super();
	}
public String hienthi() {
	
	return ma_chuyen_khoa +"-"+ ten_chuyen_khoa;
	
}
}
