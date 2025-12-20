package MODEL;

public class PhanQuyenLogin {
	  private String role;
	    private String chuyenKhoa;
	    private int maNhanVien;
	    private int ma_nguoi_dung;
	    private String TenNV;
	    private boolean lanDauDangNhap;
	    private int machuyenkhoa;

		public int getMachuyenkhoa() {
			return machuyenkhoa;
		}

		public void setMachuyenkhoa(int machuyenkhoa) {
			this.machuyenkhoa = machuyenkhoa;
		}

		public boolean isLanDauDangNhap() {
			return lanDauDangNhap;
		}

		public void setLanDauDangNhap(boolean lanDauDangNhap) {
			this.lanDauDangNhap = lanDauDangNhap;
		}

		public PhanQuyenLogin(String role, String chuyenKhoa, int maNhanVien, int ma_nguoi_dung, String tenNV,
				boolean lanDauDangNhap) {
			super();
			this.role = role;
			this.chuyenKhoa = chuyenKhoa;
			this.maNhanVien = maNhanVien;
			this.ma_nguoi_dung = ma_nguoi_dung;
			TenNV = tenNV;
			this.lanDauDangNhap = lanDauDangNhap;
		}

		public PhanQuyenLogin(int maNhanVien,String tennv, String role, String chuyenKhoa,int ma_nguoi_dung,boolean lanDauDangNhap, int Machuyenkhoa) {
	        this.maNhanVien = maNhanVien;
	        this.role = role;
	        this.chuyenKhoa = chuyenKhoa;
	       this.ma_nguoi_dung =ma_nguoi_dung;
	       this.TenNV = tennv;
	       this.lanDauDangNhap = lanDauDangNhap;
	       this.machuyenkhoa = Machuyenkhoa;
	    }

		public PhanQuyenLogin(int maNhanVien, String role, String chuyenKhoa,int ma_nguoi_dung,boolean lanDauDangNhap) {
	        this.maNhanVien = maNhanVien;
	        this.role = role;
	        this.chuyenKhoa = chuyenKhoa;
	       this.ma_nguoi_dung =ma_nguoi_dung;
	       this.lanDauDangNhap = lanDauDangNhap;
	    }
	    
	    public PhanQuyenLogin(int maNhanVien, String role, String chuyenKhoa) {
	        this.maNhanVien = maNhanVien;
	        this.role = role;
	        this.chuyenKhoa = chuyenKhoa;
	       //this.ma_nguoi_dung =ma_nguoi_dung;
	    }


	    public String getTenNV() {
			return TenNV;
		}

		public void setTenNV(String tenNV) {
			TenNV = tenNV;
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

		public int getMa_nguoi_dung() {
			return ma_nguoi_dung;
		}

		public void setMa_nguoi_dung(int ma_nguoi_dung) {
			this.ma_nguoi_dung = ma_nguoi_dung;
		}


	    
}
