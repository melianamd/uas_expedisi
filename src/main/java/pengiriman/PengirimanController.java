package pengiriman;

import barang.BarangDTO;
import com.google.gson.Gson;
import common.Alert;
import common.BaseController;
import common.Common;
import connection.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PengirimanController implements BaseController {

    private Common common = new Common();
    private ConnectionManager connect = new ConnectionManager();
    private PengirimanDTO pengirimanDTO;
    private BarangDTO barangDTO;
    private List<BarangDTO> listBarang = new ArrayList<>();
    private List<PengirimanDTO> listPengiriman = new ArrayList<>();
    private Connection con = null;
    private Statement stmt = null;

    public void setListBarang(List<BarangDTO> listBarang) {
        this.listBarang = listBarang;
    }
    public List<BarangDTO> getListBarang() {
        return listBarang;
    }
    public void setPengirimanDTO(PengirimanDTO pengirimanDTO) {
        this.pengirimanDTO = pengirimanDTO;
    }
    public PengirimanDTO getPengirimanDTO() {
        return pengirimanDTO;
    }

    public BarangDTO getBarangDTO() {
        return barangDTO;
    }

    public void setBarangDTO(BarangDTO barangDTO) {
        this.barangDTO = barangDTO;
    }

    public List<PengirimanDTO> getListPengiriman() {
        return listPengiriman;
    }

    public void setListPengiriman(List<PengirimanDTO> listPengiriman) {
        this.listPengiriman = listPengiriman;
    }

    @Override
    public void run() {
        System.out.format("+-------------+--------------+------------+-----------+%n");
        System.out.format("|                 Aplikasi Expedisi                   |%n");
        System.out.format("+-------------+--------------+------------+-----------+%n");

        System.out.format("\n");
        System.out.format("\n");

        System.out.format("+-------------+--------------+------------+----------------+----------------+%n");
        System.out.format("| id          | kurir        | tgl kirim  | jml on process | jml completed  |%n");
        System.out.format("+-------------+--------------+------------+----------------+----------------+%n");


        try {
            String sql = "SELECT * FROM t_pengiriman";
            con = connect.getConnection();
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()){
                System.out.format("|" + common.formated(String.valueOf(res.getInt(1)), 12) + " ");
                System.out.format("|" + common.formated(res.getString(2), 13) + " ");
                System.out.format("|" + common.formated(res.getString(3), 11) + " ");
                System.out.format("|" + common.formated("-", 16));
                System.out.format("|" + common.formated("-", 16));
                System.out.format("|%n");
            }
            System.out.format("+-------------+--------------+------------+----------------+----------------+%n");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        legend();

        System.out.format("Input :");
        System.out.format("\n");

        Scanner scan = new Scanner(System.in);
        String menu = scan.nextLine();
        route(menu);
    }

    private void getListBarang(String idPengirim){
        System.out.format("+-------------+--------------+------------+-----------+%n");
        System.out.format("| no resi     | alamat       | barang     | status    |%n");
        System.out.format("+-------------+--------------+------------+-----------+%n");

        try {
            String sql = "SELECT * FROM t_barang WHERE pengiriman_id = '"+idPengirim+"'";
            con = connect.getConnection();
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()){
                System.out.format("|" + common.formated(res.getString(2), 12) + " ");
                System.out.format("|" + common.formated(res.getString(3), 13) + " ");
                System.out.format("|" + common.formated(res.getString(4), 11) + " ");
                System.out.format("|" + common.formated(common.formatStatus(res.getString(5)), 10) + " ");
                System.out.format("|\n");
            }
            System.out.format("+-------------+--------------+------------+-----------+%n");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        legend();
        legendStatus();

        System.out.format("Input :");
        System.out.format("\n");

        Scanner scan = new Scanner(System.in);
        String menu = scan.nextLine();
        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");
        System.out.format(menu);
        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");

        route(menu);
    }

    private void addBarang(){
        System.out.format("+-------------+--------------+------------+-----------+%n");
        String tglPengiriman = "tgl pengiriman : " + getPengirimanDTO().getTglPengiriman();
        System.out.format("| " + common.formated(tglPengiriman, 52) + "|%n");

        String kurir = "kurir : "+getPengirimanDTO().getNamaKurir();
        System.out.format("| " + common.formated(kurir, 52) + "|%n");
        System.out.format("+-------------+--------------+------------+-----------+%n");

        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");
        System.out.format("Add Barang");
        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");

        BarangDTO barangDTO = new BarangDTO();

        Scanner scan = new Scanner(System.in);
        System.out.format("\n");
        System.out.format("no resi : ");
        System.out.format("\n");
        String noResi = scan.nextLine();
        barangDTO.setNoResi(noResi);

        Scanner namaBarang = new Scanner(System.in);
        System.out.format("\n");
        System.out.format("nama barang : ");
        System.out.format("\n");
        String inputNamaBarang = namaBarang.nextLine();
        barangDTO.setNamaBarang(inputNamaBarang);

        Scanner alamat = new Scanner(System.in);
        System.out.format("\n");
        System.out.format("alamat : ");
        System.out.format("\n");
        String inputAlamat = alamat.nextLine();
        barangDTO.setAlamat(inputAlamat);

        barangDTO.setStatusId("1");
        barangDTO.setPengirimanId(getPengirimanDTO().getId());

        try {
            saveBarang(barangDTO);
            common.showAlert(new Alert("Berhasil", "Berhasil Menamhankan Barang"));
        } catch (Exception ex){
            System.out.println(ex);
            run();
        }
    }

    @Override
    public void route(String menu){
        String modul = "";
        String param = "";
        String param2 = "";
        if (menu.contains(":")){
            String[] listModule = menu.split("\\:");
            if (listModule != null && listModule.length > 0) {
                modul = listModule[0];
                param = listModule[1];
                if (listModule.length > 2){
                    param2 = listModule[2];
                }
            }
        } else {
            modul = menu;
        }

        switch (modul) {
            case "list-pengiriman" :
                run();
                break;
            case "list-barang" :
                getListBarang(param);
                break;
            case "add" :
                add();
                break;
            case "edit-status" :
                editStatus(param, param2);
                break;
            default:
                run();
                break;
        }
    }

    private void legend(){
        System.out.format("\n");
        System.out.format("ketik : ");
        System.out.format("\n");
        System.out.format("- add (Menambahkan Pegiriman)                - edit-status:{no resi}:{status id}");
        System.out.format("\n");
        System.out.format("- list-pengiriman (lihat daftar pengiriman)  - list-barang:{pengiriman id} (untuk melihat list barang)");
        System.out.format("\n");
        System.out.format("\n");
    }

    private void legendStatus(){
        System.out.format("Status : ");
        System.out.format("\n");
        System.out.format("- id : 1 (Dikirim)   - id : 2 (Sampai)");
        System.out.format("\n");
        System.out.format("\n");

    }


    @Override
    public void add(){

        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");
        System.out.format("Add Pengiriman");
        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");

        PengirimanDTO pengirimanDTO = new PengirimanDTO();

        Scanner scan = new Scanner(System.in);
        System.out.format("\n");
        System.out.format("Nama Kurir : ");
        System.out.format("\n");
        String noResi = scan.nextLine();
        pengirimanDTO.setNamaKurir(noResi);

        Scanner namaBarang = new Scanner(System.in);
        System.out.format("\n");
        System.out.format("Tanggal Pengiriman (YYYY-mm-dd) : ");
        System.out.format("\n");
        String inputNamaBarang = namaBarang.nextLine();
        pengirimanDTO.setTglPengiriman(inputNamaBarang);

        try {
            savePengiriman(pengirimanDTO);
        } catch (Exception ex){
            System.out.println(ex);
            run();
        }

        System.out.format("\n");
        System.out.format("\n");
    }


    public void savePengiriman(PengirimanDTO data) throws SQLException {
        Gson gson = new Gson();
        System.out.println(gson.toJson(data));

        String sql = "INSERT INTO t_pengiriman (nama_kurir, tgl_pengiriman) VALUES('"+data.getNamaKurir()+"','"+data.getTglPengiriman()+"')";
        con = connect.getConnection();
        stmt = con.createStatement();
        boolean id = stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
        common.showAlert(new Alert("Berhasil", "Menyimpan Pengiriman"));

        ResultSet res = stmt.getGeneratedKeys();
        if (res.next()) {
            data.setId(String.valueOf(res.getInt(1)));
        }
        setPengirimanDTO(data);
        System.out.format("\n");
        System.out.format("ketik : ");
        System.out.format("\n");
        System.out.format("- set-barang (untuk langsung menambahkan barang)     - back (untuk kembali)");

        System.out.format("\n");
        System.out.format("\n");
        System.out.format("input : ");
        System.out.format("\n");

        Scanner menu = new Scanner(System.in);
        String inputMenu = menu.nextLine();

        if ("set-barang".equalsIgnoreCase(inputMenu))
            addBarang();
        else if ("back".equalsIgnoreCase(inputMenu))
            run();
        else {
            common.showAlert(new Alert("Error", "Salah Input"));
        }
    }
    public void saveBarang(BarangDTO data) throws SQLException {
        Gson gson = new Gson();
        System.out.println(gson.toJson(data));

        String sql = "INSERT INTO t_barang (no_resi, nama_barang, alamat, status_id, pengiriman_id) VALUES('"+data.getNoResi()+"','"+data.getNamaBarang()+"','"+data.getAlamat()+"','"+data.getStatusId()+"','"+data.getPengirimanId()+"')";
        con = connect.getConnection();
        stmt = con.createStatement();
        stmt.execute(sql);
        common.showAlert(new Alert("Berhsail", "Menambahkan Barang"));

        System.out.format("\n");
        System.out.format("ketik : ");
        System.out.format("\n");
        System.out.format("- next (untuk lanjut)     - end (selesai)");

        System.out.format("\n");
        System.out.format("\n");
        System.out.format("input : ");
        System.out.format("\n");

        Scanner menu = new Scanner(System.in);
        String inputMenu = menu.nextLine();

        if ("next".equalsIgnoreCase(inputMenu))
            addBarang();
        else if ("end".equalsIgnoreCase(inputMenu)){
            setPengirimanDTO(new PengirimanDTO());
            setBarangDTO(new BarangDTO());
            run();
        }
        else {
            common.showAlert(new Alert("Error", "Input Salah"));
        }
    }

    private void editStatus(String noResi, String status) {
        try {
            String sql = "UPDATE t_barang SET status_id = '" + status + "' WHERE no_resi = '"+noResi+"'";
            con = connect.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
            run();
        }
        common.showAlert(new Alert("Berhasil", "Update Status"));
        run();
    }

}
