package container;

import service.*;

public class ComponentService {
    public static AdminService adminService = new AdminService();
    public static CardService cardService = new CardService();
    public static ProfileService profileService = new ProfileService();
    public static TerminalService terminalService = new TerminalService();
    public static TransactionService transactionService = new TransactionService();
}
