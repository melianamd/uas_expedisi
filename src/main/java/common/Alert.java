package common;

public class Alert {
    private String jenisAlert;
    private String msg;
    public Alert(String jenisAlert, String msg) {
        this.jenisAlert = jenisAlert;
        this.msg = msg;
    }

    public String getJenisAlert() {
        return jenisAlert;
    }
    public String getMsg() {
        return msg;
    }

}
