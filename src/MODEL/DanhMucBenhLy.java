package MODEL;

public class DanhMucBenhLy {
private int id;
private String ma_icd ;
private String ten_benh;
private String trieu_chung_goi_y;
private String chuyen_khoa_lien_quan;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMa_icd() {
	return ma_icd;
}
public void setMa_icd(String ma_icd) {
	this.ma_icd = ma_icd;
}
public String getTen_benh() {
	return ten_benh;
}
public void setTen_benh(String ten_benh) {
	this.ten_benh = ten_benh;
}
public String getTrieu_chung_goi_y() {
	return trieu_chung_goi_y;
}
public void setTrieu_chung_goi_y(String trieu_chung_goi_y) {
	this.trieu_chung_goi_y = trieu_chung_goi_y;
}
public String getChuyen_khoa_lien_quan() {
	return chuyen_khoa_lien_quan;
}
public void setChuyen_khoa_lien_quan(String chuyen_khoa_lien_quan) {
	this.chuyen_khoa_lien_quan = chuyen_khoa_lien_quan;
}
public DanhMucBenhLy(String ma_icd, String ten_benh, String trieu_chung_goi_y, String chuyen_khoa_lien_quan) {
	super();
	this.ma_icd = ma_icd;
	this.ten_benh = ten_benh;
	this.trieu_chung_goi_y = trieu_chung_goi_y;
	this.chuyen_khoa_lien_quan = chuyen_khoa_lien_quan;
}
public DanhMucBenhLy() {
	super();
}
@Override
public String toString() {
	return this.ten_benh;
}



}
