package MODEL;

public class Session {
    public static int maNhanVien;
    public static String role;
    public static String chuyenKhoa;
	public static int getMaNhanVien() {
		return maNhanVien;
	}
	public static void setMaNhanVien(int maNhanVien) {
		Session.maNhanVien = maNhanVien;
	}
	public static String getRole() {
		return role;
	}
	public static void setRole(String role) {
		Session.role = role;
	}
	public static String getChuyenKhoa() {
		return chuyenKhoa;
	}
	public static void setChuyenKhoa(String chuyenKhoa) {
		Session.chuyenKhoa = chuyenKhoa;
	}
    
    
}
