import container.ComponentController;
import container.ComponentRepository;
import controller.AuthController;
import database.DataBase;
import repository.ProfileRepository;

public class Main {

    public static void main(String[] args) {
        System.out.println(DataBase.initData());
        ComponentController.authController.start();

    }
}