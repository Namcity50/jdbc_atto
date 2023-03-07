package container;

import controller.*;

public class ComponentController {
    public static AdminController adminController = new AdminController();
    public static AdminCardController adminCardController = new AdminCardController();
    public static AdminProfileController adminProfileController = new AdminProfileController();
    public static AdminTerminalController adminTerminalController = new AdminTerminalController();
    public static AdminTransactionController adminTransactionController = new AdminTransactionController();
    public static AuthController authController = new AuthController();
    public static ProfileCardController profileCardController = new ProfileCardController();
    public static ProfileController profileController = new ProfileController();
    public static ProfileTranController profileTranController = new ProfileTranController();
    public static AdminStatisticController adminStatisticController = new AdminStatisticController();
}
