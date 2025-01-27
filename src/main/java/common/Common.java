package common;

public class Common {
    public String formated(String text, int jumlah) {
        int jumlahText = text.length();
        if (jumlahText < jumlah) {
            int sisa = jumlah - jumlahText;

            String textSisa = "";
            for (int i = 0; i < sisa; i++) {
                textSisa += " ";
            }

            return text + textSisa;
        }

        return text;
    }

    public void showAlert(Alert alert) {

        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");
        System.out.format(alert.getJenisAlert() + " " + alert.getMsg());
        System.out.format("\n");
        System.out.format("+====================+");
        System.out.format("\n");
    }

    public String formatStatus(String status) {
        switch (status){
            case "1" :
                return "Dikirim";
            case "2" :
                return "Sampai";
            default:
                return "-";
        }
    }
}
