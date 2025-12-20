package MODEL;

import java.time.LocalDate;

public class Thuoc {
private int mathuoc;
private String tenthuoc;
private String hoatchat;
private String hamluong;
private String dangthuoc;
private String loaithuoc;
private String donvitinh;
private double dongianhap;
private double dongiaban;
private int soluongton;
private LocalDate ngaysanxuat;
private LocalDate hansudung;
private String nhasanxuat;
private String nuocsanxuat;
private String ghichu;

public long dongiabanthuoc(){
	
    return (long) dongiaban;
}

public long gianhapthuoc(){
	
    return (long) dongianhap;
}
public int getMathuoc() {
	return mathuoc;
}
public void setMathuoc(int mathuoc) {
	this.mathuoc = mathuoc;
}
public String getTenthuoc() {
	return tenthuoc;
}
public void setTenthuoc(String tenthuoc) {
	this.tenthuoc = tenthuoc;
}
public String getHoatchat() {
	return hoatchat;
}
public void setHoatchat(String hoatchat) {
	this.hoatchat = hoatchat;
}
public String getHamluong() {
	return hamluong;
}
public void setHamluong(String hamluong) {
	this.hamluong = hamluong;
}
public String getDangthuoc() {
	return dangthuoc;
}
public void setDangthuoc(String dangthuoc) {
	this.dangthuoc = dangthuoc;
}
public String getLoaithuoc() {
	return loaithuoc;
}
public void setLoaithuoc(String loaithuoc) {
	this.loaithuoc = loaithuoc;
}
public String getDonvitinh() {
	return donvitinh;
}
public void setDonvitinh(String donvitinh) {
	this.donvitinh = donvitinh;
}
public double getDongianhap() {
	return dongianhap;
}
public void setDongianhap(double dongianhap) {
	this.dongianhap = dongianhap;
}
public double getDongiaban() {
	return dongiaban;
}
public void setDongiaban(double dongiaban) {
	this.dongiaban = dongiaban;
}
public int getSoluongton() {
	return soluongton;
}
public void setSoluongton(int soluongton) {
	this.soluongton = soluongton;
}
public LocalDate getNgaysanxuat() {
	return ngaysanxuat;
}
public void setNgaysanxuat(LocalDate ngaysanxuat) {
	this.ngaysanxuat = ngaysanxuat;
}
public LocalDate getHansudung() {
	return hansudung;
}
public void setHansudung(LocalDate hansudung) {
	this.hansudung = hansudung;
}
public String getNhasanxuat() {
	return nhasanxuat;
}
public void setNhasanxuat(String nhasanxuat) {
	this.nhasanxuat = nhasanxuat;
}
public String getNuocsanxuat() {
	return nuocsanxuat;
}
public void setNuocsanxuat(String nuocsanxuat) {
	this.nuocsanxuat = nuocsanxuat;
}
public String getGhichu() {
	return ghichu;
}
public void setGhichu(String ghichu) {
	this.ghichu = ghichu;
}
public Thuoc(String tenthuoc, String hoatchat, String hamluong, String dangthuoc, String loaithuoc, String donvitinh,
		double dongianhap, double dongiaban, int soluongton, LocalDate ngaysanxuat, LocalDate hansudung,
		String nhasanxuat, String nuocsanxuat, String ghichu) {
	super();
	this.tenthuoc = tenthuoc;
	this.hoatchat = hoatchat;
	this.hamluong = hamluong;
	this.dangthuoc = dangthuoc;
	this.loaithuoc = loaithuoc;
	this.donvitinh = donvitinh;
	this.dongianhap = dongianhap;
	this.dongiaban = dongiaban;
	this.soluongton = soluongton;
	this.ngaysanxuat = ngaysanxuat;
	this.hansudung = hansudung;
	this.nhasanxuat = nhasanxuat;
	this.nuocsanxuat = nuocsanxuat;
	this.ghichu = ghichu;
}
public Thuoc() {
	super();
}

@Override
public String toString() {
	return tenthuoc;
}


}
