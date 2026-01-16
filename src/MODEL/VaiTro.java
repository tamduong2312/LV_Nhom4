package MODEL;

public class VaiTro {
	private String maVaiTro;
	private String tenVaiTro;
	public String getMaVaiTro() {
		return maVaiTro;
	}
	public void setMaVaiTro(String maVaiTro) {
		this.maVaiTro = maVaiTro;
	}
	public String getTenVaiTro() {
		return tenVaiTro;
	}
	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}
	public VaiTro(String maVaiTro, String tenVaiTro) {
		super();
		this.maVaiTro = maVaiTro;
		this.tenVaiTro = tenVaiTro;
	}
	public VaiTro() {
		super();
	}
	
}
