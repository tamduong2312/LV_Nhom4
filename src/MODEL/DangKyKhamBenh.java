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
private int maPhieuKham; 
private LocalDateTime thoigiandangky;
private TRANGTHAI trangthai;
private String ghichu;

private String tenDichVu;

private int maChiTietChiDinh;

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
    BS_DANG_KHAM,
    DA_KHAM,
    VANG_MAT,
    DOI_CHUYEN_KHOA,
    CHI_DINH_CLS,
    CHO_CLS,
    DA_THUC_HIEN_CLS,
    HUY,


}

public int getMaPhieuKham() { return maPhieuKham; }
public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }

public String getTrangThaiTiengViet() {
  
    if (trangThaiHienThi != null && !trangThaiHienThi.isEmpty()) {
        return trangThaiHienThi;
    }


    if (trangthai == null) return "Chờ khám";
    

    switch (trangthai) {
        case CHO_KHAM: return "Chờ khám";
        case DANG_KHAM: return "Đang khám";
        case DA_KHAM: return "Đã khám";
        case CHO_KHAM_BS: return "Chờ khám bác sĩ";
        case BS_DANG_KHAM: return "Bác sĩ đang khám";
        case VANG_MAT: return "Vắng mặt";
        case DOI_CHUYEN_KHOA: return "Đổi chuyên khoa";
        case CHO_CLS: return "Chờ CLS";
        case CHI_DINH_CLS: return "chỉ định CLS";
        case HUY: return "Hủy khám";
        case DA_THUC_HIEN_CLS: return "Đã thực hiện CLS";
        default: return "Không xác định";
    }
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


public String getTenDichVu() {
	return tenDichVu;
}
public void setTenDichVu(String tenDichVu) {
	this.tenDichVu = tenDichVu;
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


public int getMaChiTietChiDinh() {
	return maChiTietChiDinh;
}
public void setMaChiTietChiDinh(int maChiTietChiDinh) {
	this.maChiTietChiDinh = maChiTietChiDinh;
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
