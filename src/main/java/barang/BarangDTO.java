package barang;

public class BarangDTO {
    private String namaBarang;
    private String noResi;
    private String pengirimanId;
    private String alamat;
    private String statusId;
    private String statusName;
    private String baranId;

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    public String getPengirimanId() {
        return pengirimanId;
    }

    public void setPengirimanId(String pengirimanId) {
        this.pengirimanId = pengirimanId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getBaranId() {
        return baranId;
    }

    public void setBaranId(String baranId) {
        this.baranId = baranId;
    }
}
