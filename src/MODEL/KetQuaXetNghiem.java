package MODEL;

import java.sql.Timestamp;

public class KetQuaXetNghiem {
    private int id;
    private int maChiTietChiDinh;
    private Timestamp ngayThucHien;
    private int nguoiThucHien;
    private String ketLuan; // Cột mới thêm
    
    private int maBSketluan;
    

    public int getMaBSketluan() {
		return maBSketluan;
	}

	public void setMaBSketluan(int maBSketluan) {
		this.maBSketluan = maBSketluan;
	}


    private float hongCau;
    private float bachCau;
    private float tieuCau;
    private float huyetSacTo;
    private float hematocrit;
    private String nhomMau;
    private int tocDoMauLang;
    private String dongMauCoBan;

    private float glucose;
    private float hbA1c;
    private float ure;
    private float creatinine;
    private float astGot;
    private float altGpt;
    private float ggt;
    private float cholesterolTp;
    private float triglyceride;
    private float hdlC;
    private float ldlC;
    private float acidUric;
    private float bilirubinTp;


    private float tyTrong;
    private float ph;
    private String bachCauNuocTieu;
    private String hongCauNuocTieu;
    private String proteinNuocTieu;
    private String duongNuocTieu;
    private String nitrit;
    private String ketone;


    private String hbsag;
    private String hcvAb;
    private String hivAb;
    private String tphaSyphilis;
    private float crpDinhLuong;
    private float rfDinhLuong;


    private String ghiChuThem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaChiTietChiDinh() {
		return maChiTietChiDinh;
	}

	public void setMaChiTietChiDinh(int maChiTietChiDinh) {
		this.maChiTietChiDinh = maChiTietChiDinh;
	}

	public Timestamp getNgayThucHien() {
		return ngayThucHien;
	}

	public void setNgayThucHien(Timestamp ngayThucHien) {
		this.ngayThucHien = ngayThucHien;
	}

	public int getNguoiThucHien() {
		return nguoiThucHien;
	}

	public void setNguoiThucHien(int nguoiThucHien) {
		this.nguoiThucHien = nguoiThucHien;
	}

	public String getKetLuan() {
		return ketLuan;
	}

	public void setKetLuan(String ketLuan) {
		this.ketLuan = ketLuan;
	}

	public float getHongCau() {
		return hongCau;
	}

	public void setHongCau(float hongCau) {
		this.hongCau = hongCau;
	}

	public float getBachCau() {
		return bachCau;
	}

	public void setBachCau(float bachCau) {
		this.bachCau = bachCau;
	}

	public float getTieuCau() {
		return tieuCau;
	}

	public void setTieuCau(float tieuCau) {
		this.tieuCau = tieuCau;
	}

	public float getHuyetSacTo() {
		return huyetSacTo;
	}

	public void setHuyetSacTo(float huyetSacTo) {
		this.huyetSacTo = huyetSacTo;
	}

	public float getHematocrit() {
		return hematocrit;
	}

	public void setHematocrit(float hematocrit) {
		this.hematocrit = hematocrit;
	}

	public String getNhomMau() {
		return nhomMau;
	}

	public void setNhomMau(String nhomMau) {
		this.nhomMau = nhomMau;
	}

	public int getTocDoMauLang() {
		return tocDoMauLang;
	}

	public void setTocDoMauLang(int tocDoMauLang) {
		this.tocDoMauLang = tocDoMauLang;
	}

	public String getDongMauCoBan() {
		return dongMauCoBan;
	}

	public void setDongMauCoBan(String dongMauCoBan) {
		this.dongMauCoBan = dongMauCoBan;
	}

	public float getGlucose() {
		return glucose;
	}

	public void setGlucose(float glucose) {
		this.glucose = glucose;
	}

	public float getHbA1c() {
		return hbA1c;
	}

	public void setHbA1c(float hbA1c) {
		this.hbA1c = hbA1c;
	}

	public float getUre() {
		return ure;
	}

	public void setUre(float ure) {
		this.ure = ure;
	}

	public float getCreatinine() {
		return creatinine;
	}

	public void setCreatinine(float creatinine) {
		this.creatinine = creatinine;
	}

	public float getAstGot() {
		return astGot;
	}

	public void setAstGot(float astGot) {
		this.astGot = astGot;
	}

	public float getAltGpt() {
		return altGpt;
	}

	public void setAltGpt(float altGpt) {
		this.altGpt = altGpt;
	}

	public float getGgt() {
		return ggt;
	}

	public void setGgt(float ggt) {
		this.ggt = ggt;
	}

	public float getCholesterolTp() {
		return cholesterolTp;
	}

	public void setCholesterolTp(float cholesterolTp) {
		this.cholesterolTp = cholesterolTp;
	}

	public float getTriglyceride() {
		return triglyceride;
	}

	public void setTriglyceride(float triglyceride) {
		this.triglyceride = triglyceride;
	}

	public float getHdlC() {
		return hdlC;
	}

	public void setHdlC(float hdlC) {
		this.hdlC = hdlC;
	}

	public float getLdlC() {
		return ldlC;
	}

	public void setLdlC(float ldlC) {
		this.ldlC = ldlC;
	}

	public float getAcidUric() {
		return acidUric;
	}

	public void setAcidUric(float acidUric) {
		this.acidUric = acidUric;
	}

	public float getBilirubinTp() {
		return bilirubinTp;
	}

	public void setBilirubinTp(float bilirubinTp) {
		this.bilirubinTp = bilirubinTp;
	}

	public float getTyTrong() {
		return tyTrong;
	}

	public void setTyTrong(float tyTrong) {
		this.tyTrong = tyTrong;
	}

	public float getPh() {
		return ph;
	}

	public void setPh(float ph) {
		this.ph = ph;
	}

	public String getBachCauNuocTieu() {
		return bachCauNuocTieu;
	}

	public void setBachCauNuocTieu(String bachCauNuocTieu) {
		this.bachCauNuocTieu = bachCauNuocTieu;
	}

	public String getHongCauNuocTieu() {
		return hongCauNuocTieu;
	}

	public void setHongCauNuocTieu(String hongCauNuocTieu) {
		this.hongCauNuocTieu = hongCauNuocTieu;
	}

	public String getProteinNuocTieu() {
		return proteinNuocTieu;
	}

	public void setProteinNuocTieu(String proteinNuocTieu) {
		this.proteinNuocTieu = proteinNuocTieu;
	}

	public String getDuongNuocTieu() {
		return duongNuocTieu;
	}

	public void setDuongNuocTieu(String duongNuocTieu) {
		this.duongNuocTieu = duongNuocTieu;
	}

	public String getNitrit() {
		return nitrit;
	}

	public void setNitrit(String nitrit) {
		this.nitrit = nitrit;
	}

	public String getKetone() {
		return ketone;
	}

	public void setKetone(String ketone) {
		this.ketone = ketone;
	}

	public String getHbsag() {
		return hbsag;
	}

	public void setHbsag(String hbsag) {
		this.hbsag = hbsag;
	}

	public String getHcvAb() {
		return hcvAb;
	}

	public void setHcvAb(String hcvAb) {
		this.hcvAb = hcvAb;
	}

	public String getHivAb() {
		return hivAb;
	}

	public void setHivAb(String hivAb) {
		this.hivAb = hivAb;
	}

	public String getTphaSyphilis() {
		return tphaSyphilis;
	}

	public void setTphaSyphilis(String tphaSyphilis) {
		this.tphaSyphilis = tphaSyphilis;
	}

	public float getCrpDinhLuong() {
		return crpDinhLuong;
	}

	public void setCrpDinhLuong(float crpDinhLuong) {
		this.crpDinhLuong = crpDinhLuong;
	}

	public float getRfDinhLuong() {
		return rfDinhLuong;
	}

	public void setRfDinhLuong(float rfDinhLuong) {
		this.rfDinhLuong = rfDinhLuong;
	}

	public String getGhiChuThem() {
		return ghiChuThem;
	}

	public void setGhiChuThem(String ghiChuThem) {
		this.ghiChuThem = ghiChuThem;
	}

	public KetQuaXetNghiem(int maChiTietChiDinh, Timestamp ngayThucHien, int nguoiThucHien, String ketLuan,
			float hongCau, float bachCau, float tieuCau, float huyetSacTo, float hematocrit, String nhomMau,
			int tocDoMauLang, String dongMauCoBan, float glucose, float hbA1c, float ure, float creatinine,
			float astGot, float altGpt, float ggt, float cholesterolTp, float triglyceride, float hdlC, float ldlC,
			float acidUric, float bilirubinTp, float tyTrong, float ph, String bachCauNuocTieu, String hongCauNuocTieu,
			String proteinNuocTieu, String duongNuocTieu, String nitrit, String ketone, String hbsag, String hcvAb,
			String hivAb, String tphaSyphilis, float crpDinhLuong, float rfDinhLuong, String ghiChuThem) {
		super();
		this.maChiTietChiDinh = maChiTietChiDinh;
		this.ngayThucHien = ngayThucHien;
		this.nguoiThucHien = nguoiThucHien;
		this.ketLuan = ketLuan;
		this.hongCau = hongCau;
		this.bachCau = bachCau;
		this.tieuCau = tieuCau;
		this.huyetSacTo = huyetSacTo;
		this.hematocrit = hematocrit;
		this.nhomMau = nhomMau;
		this.tocDoMauLang = tocDoMauLang;
		this.dongMauCoBan = dongMauCoBan;
		this.glucose = glucose;
		this.hbA1c = hbA1c;
		this.ure = ure;
		this.creatinine = creatinine;
		this.astGot = astGot;
		this.altGpt = altGpt;
		this.ggt = ggt;
		this.cholesterolTp = cholesterolTp;
		this.triglyceride = triglyceride;
		this.hdlC = hdlC;
		this.ldlC = ldlC;
		this.acidUric = acidUric;
		this.bilirubinTp = bilirubinTp;
		this.tyTrong = tyTrong;
		this.ph = ph;
		this.bachCauNuocTieu = bachCauNuocTieu;
		this.hongCauNuocTieu = hongCauNuocTieu;
		this.proteinNuocTieu = proteinNuocTieu;
		this.duongNuocTieu = duongNuocTieu;
		this.nitrit = nitrit;
		this.ketone = ketone;
		this.hbsag = hbsag;
		this.hcvAb = hcvAb;
		this.hivAb = hivAb;
		this.tphaSyphilis = tphaSyphilis;
		this.crpDinhLuong = crpDinhLuong;
		this.rfDinhLuong = rfDinhLuong;
		this.ghiChuThem = ghiChuThem;
	}

	public KetQuaXetNghiem() {
		super();
	}


}