package login;

import common.BaseController;
import pengiriman.PengirimanController;

import java.util.Scanner;

public class LoginController implements BaseController {
    @Override
    public void run(){
        Scanner username = new Scanner(System.in);
        System.out.format("Username");
        System.out.format("\n");
        String name = username.nextLine();

        Scanner password = new Scanner(System.in);
        System.out.format("Password");
        System.out.format("\n");
        String pass = password.nextLine();

        boolean isValid = "Admin".equals(name) && "123".equals(pass);

        PengirimanController pengirimanController = new PengirimanController();
        if (isValid)
            pengirimanController.run();

        System.out.format("\n");
        System.out.format("======================");
        System.out.format("\n");
        System.out.format("Username dan Password Salah");
        System.out.format("\n");
        System.out.format("======================");
        System.out.format("\n");

        run();
    }

    @Override
    public void add() {

    }

    @Override
    public void route(String menu) {

    }
}
