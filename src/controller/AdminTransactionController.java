package controller;

import container.ComponentContainer;
import container.ComponentController;
import container.ComponentService;
import service.TransactionService;

public class AdminTransactionController {
    public void menuTransaction(){
        String menu = """
                1 -> Transaction List:
                2 -> Company Card Balance:
                0 -> Back:
                """;
        System.out.println(menu);
    }
    public void start(){
        boolean flag = true;
        int act;
        while (flag){
            menuTransaction();
            act = ComponentContainer.scanInt.nextInt();
            switch (act){
                case 1 -> getByTransactionList();
                case 2 -> getCompanyByBalance();
                case 0 -> flag = false;
                default -> System.out.println(ComponentContainer.getMenu());
            }
        }
    }

    public void getCompanyByBalance() {
        ComponentService.cardService.getInitCard();
    }

    public void getByTransactionList() {
        ComponentService.transactionService.getList();
    }
}
