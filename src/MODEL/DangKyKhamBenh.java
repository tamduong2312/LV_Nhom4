package MODEL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DangKyKhamBenh {
private int id;
private int mabenhnhan;
private int manhanvien;
private int machuyenkhoa;
private int sothutu;
private LocalDateTime thoigiandangky;
private TRANGTHAI trangthai;
private String ghichu;

private String trangThaiHienThi;
public String getTrangThaiHienThi() {
	return trangThaiHienThi;
}
public void setTrangThaiHienThi(String trangThaiHienThi) {
	this.trangThaiHienThi = trangThaiHienThi;
}
public enum TRANGTHAI {
 CHO_KHAM,
    DANG_KHAM,
    CHO_KHAM_BS,
    DA_KHAM,
    HUY,

}
public String SetTrangThai() {
	if(trangthai.name() == "CHO_KHAM" ) {
		
		return " chờ khám";
	}
	else if(trangthai.name() == "DANG_KHAM" ) {
		
		return " đang khám";
	}
	else if(trangthai.name() == "DA_KHAM" ) {
		
		return " đã khám";
	}
	
	else if(trangthai.name() == "CHO_KHAM_BS" ) {
		
		return "chờ khám bác sĩ";
	}
	
	return "hủy khám";
	
}
public String getThoiGianHienThi() {
    if (thoigiandangky == null) return "";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
    return thoigiandangky.format(formatter);
}

public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public int getMabenhnhan() {
	return mabenhnhan;
}


public void setMabenhnhan(int mabenhnhan) {
	this.mabenhnhan = mabenhnhan;
}


public int getManhanvien() {
	return manhanvien;
}


public void setManhanvien(int manhanvien) {
	this.manhanvien = manhanvien;
}


public int getMachuyenkhoa() {
	return machuyenkhoa;
}


public void setMachuyenkhoa(int machuyenkhoa) {
	this.machuyenkhoa = machuyenkhoa;
}


public int getSothutu() {
	return sothutu;
}


public void setSothutu(int sothutu) {
	this.sothutu = sothutu;
}


public LocalDateTime getThoigiandangky() {
	return thoigiandangky;
}


public void setThoigiandangky(LocalDateTime thoigiandangky) {
	this.thoigiandangky = thoigiandangky;
}


public TRANGTHAI getTrangthai() {
	return trangthai;
}


public void setTrangthai(TRANGTHAI trangthai) {
	this.trangthai = trangthai;
}


public String getGhichu() {
	return ghichu;
}


public void setGhichu(String ghichu) {
	this.ghichu = ghichu;
}





public DangKyKhamBenh() {
	super();
}
public DangKyKhamBenh(int mabenhnhan, int manhanvien, int machuyenkhoa,
        int sothutu, LocalDateTime thoigiandangky,
        TRANGTHAI trangthai, String ghichu) {
this.mabenhnhan = mabenhnhan;
this.manhanvien = manhanvien;
this.machuyenkhoa = machuyenkhoa;
this.sothutu = sothutu;
this.thoigiandangky = thoigiandangky;
this.trangthai = trangthai;
this.ghichu = ghichu;
}


}
