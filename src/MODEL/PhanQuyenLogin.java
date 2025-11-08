package MODEL;

public class PhanQuyenLogin {
	  private String role;
	    private String chuyenKhoa;
	    private int maNhanVien;

	    public PhanQuyenLogin(int maNhanVien, String role, String chuyenKhoa) {
	        this.maNhanVien = maNhanVien;
	        this.role = role;
	        this.chuyenKhoa = chuyenKhoa;
	    }

	    public String getRole() {
	        return role;
	    }

	    public String getChuyenKhoa() {
	        return chuyenKhoa;
	    }

		public int getMaNhanVien() {
			return maNhanVien;
		}


	    
}
