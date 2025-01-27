package pengiriman;

public class PengirimanDTO {

    private String id;
    private String noResi;
    private String kurirId;
    private String namaKurir;
    private String idBarang;
    private String namaBarang;
    private String statusPengiriman;
    private String alamat;
    private String namaPembeli;
    private String tglPengiriman;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    public String getKurirId() {
        return kurirId;
    }

    public void setKurirId(String kurirId) {
        this.kurirId = kurirId;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getStatusPengiriman() {
        return statusPengiriman;
    }

    public void setStatusPengiriman(String statusPengiriman) {
        this.statusPengiriman = statusPengiriman;
    }

    public String getAlamat() {

        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getTglPengiriman() {
        return tglPengiriman;
    }

    public void setTglPengiriman(String tglPengiriman) {
        this.tglPengiriman = tglPengiriman;
    }
}
