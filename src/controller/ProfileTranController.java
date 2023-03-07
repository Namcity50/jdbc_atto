package controller;

import container.ComponentContainer;
import container.ComponentRepository;
import container.ComponentService;
import dto.Transaction;

import java.util.List;

public class ProfileTranController {
    public void menuTransaction() {
        String menu = """
                1 -> Transaction:
                2 -> Make Payment:
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
            case 1 -> transactionList();
            case 2 -> addPayment();
            case 0 -> flag = false;
            default -> System.out.println(ComponentContainer.getMenu());
          }
        }
    }
    public void transactionList() {
        List<Transaction> transactionList = ComponentRepository.transactionRepository.getTransactionList();
        System.out.println("List Transaction: ");
       for (Transaction transaction : transactionList){
           System.out.println(transaction.menu());
       }
    }
    public void addPayment() {
        System.out.println("Enter Card Number: ");
        String cardNum = ComponentContainer.scanStr.nextLine();
        System.out.println("Enter Terminal number: ");
        String terCode = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.transactionService.addProfilePaymentTransaction(cardNum,terCode);
        if (!b){
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }
}
