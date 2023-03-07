package controller;

import container.ComponentContainer;
import container.ComponentService;
import dto.Terminal;

public class AdminTerminalController {
    public void menuTerminal() {
        String menu = """
                 1 -> Create Terminal (code unique,address)
                 2 -> Terminal List
                 3 -> Update Terminal (code,address)
                 4 -> Change Terminal Status
                 5 -> Delete
                 0 -> Back:
                """;
        System.out.println(menu + "Select: ");
    }

    public void start() {
        boolean flag = true;
        int act;
        while (flag) {
            menuTerminal();
            act = ComponentContainer.scanInt.nextInt();
            switch (act) {
                case 1 -> adminCreateTerminal();
                case 2 -> getTerminalList();
                case 3 -> changeAddress();
                case 4 -> updateStatus();
                case 5 -> deleteTerminal();
                case 0 -> flag = false;
                default -> System.out.println(ComponentContainer.getMenu());
            }
        }
    }
    public void deleteTerminal() {
        System.out.println("Code Number:");
        String code = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.terminalService.deleteTerminal(code);
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully");
    }

    public void updateStatus() {
        System.out.println("Code Number:");
        String code = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.terminalService.updateStatusTerminal(code);
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully");
    }
    public void changeAddress() {
        System.out.println("Terminal Code :");
        String code = ComponentContainer.scanStr.nextLine();
        System.out.println("New Address:");
        String address = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.terminalService.updateAddress(code, address);
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully");
    }
    public void getTerminalList() {
        ComponentService.terminalService.getListTerminal();
    }

    public void adminCreateTerminal() {
        System.out.println("Code: ");
        String code = ComponentContainer.scanStr.nextLine();
        System.out.println("Address: ");
        String address = ComponentContainer.scanStr.nextLine();
        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setAddress(address);
        ComponentService.terminalService.addTerminal(terminal);
    }
    //    public void changeTerminalMenu() {
//        String menu = """
//                1 - Change Code:
//                2 - Change Address:
//                """;
//        System.out.println(menu);
//    }

//    public void startChange() {
//        boolean flag = true;
//        int act;
//        while (flag) {
//            changeTerminalMenu();
//            act = ComponentContainer.scanInt.nextInt();
//            switch (act) {
////                case 1 -> changeCode();
//                case 2 -> changeAddress();
//                case 0 -> flag = false;
//                default -> System.out.println(ComponentContainer.getMenu());
//            }
//        }
//    }
    //    public void changeCode() {
//        System.out.println("Code Old Number:");
//        String code = ComponentContainer.scanStr.nextLine();
//        System.out.println("Code New Number:");
//        String code1 = ComponentContainer.scanStr.nextLine();
//        boolean b = ComponentService.terminalService.updateCode(code, code1);
//        if (!b) {
//            System.out.println(ComponentContainer.getMenu());
//        }
//        System.out.println("Successfully");
//    }
}
