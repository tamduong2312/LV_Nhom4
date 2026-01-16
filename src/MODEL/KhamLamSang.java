package MODEL;

public class KhamLamSang {
    private int id;
    private int maPhieuKham;
    private String lyDoKham;
    private String tienSuBanThan;

    private String benhSu;
    private String chanDoanSoBo;
    private String loiDanBacSi;
    private String ketQuaKhamCanLamSang; 
    private String khamLamSang;          


    public KhamLamSang() {
    }


    public KhamLamSang(int maPhieuKham, String lyDoKham, String tienSuBanThan,
                       String benhSu, String chanDoanSoBo, String loiDanBacSi, 
                       String ketQuaKhamCanLamSang, String khamLamSang) {
        this.maPhieuKham = maPhieuKham;
        this.lyDoKham = lyDoKham;
        this.tienSuBanThan = tienSuBanThan;
   
        this.benhSu = benhSu;
        this.chanDoanSoBo = chanDoanSoBo;
        this.loiDanBacSi = loiDanBacSi;
        this.ketQuaKhamCanLamSang = ketQuaKhamCanLamSang;
        this.khamLamSang = khamLamSang;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }

    public String getLyDoKham() { return lyDoKham; }
    public void setLyDoKham(String lyDoKham) { this.lyDoKham = lyDoKham; }

    public String getTienSuBanThan() { return tienSuBanThan; }
    public void setTienSuBanThan(String tienSuBanThan) { this.tienSuBanThan = tienSuBanThan; }


    public String getBenhSu() { return benhSu; }
    public void setBenhSu(String benhSu) { this.benhSu = benhSu; }

    public String getChanDoanSoBo() { return chanDoanSoBo; }
    public void setChanDoanSoBo(String chanDoanSoBo) { this.chanDoanSoBo = chanDoanSoBo; }

    public String getLoiDanBacSi() { return loiDanBacSi; }
    public void setLoiDanBacSi(String loiDanBacSi) { this.loiDanBacSi = loiDanBacSi; }

    public String getKetQuaKhamCanLamSang() { return ketQuaKhamCanLamSang; }
    public void setKetQuaKhamCanLamSang(String ketQuaKhamCanLamSang) { this.ketQuaKhamCanLamSang = ketQuaKhamCanLamSang; }

    public String getKhamLamSang() { return khamLamSang; }
    public void setKhamLamSang(String khamLamSang) { this.khamLamSang = khamLamSang; }
}