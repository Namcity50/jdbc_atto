package controller;

import container.ComponentContainer;
import container.ComponentController;

public class AdminController {
   public void adminMenu(){
       String menu = """
               1 -> Card:
               2 -> Terminal:
               3 -> Profile:
               4 -> Transaction:
               5 -> Statistic:
               """;
       System.out.println(menu + "Select : ");
   }
   public void start(){
       int act;
       while (true){
           adminMenu();
           act = ComponentContainer.scanInt.nextInt();
           switch (act){
               case 1 -> ComponentController.adminCardController.start();
               case 2 -> ComponentController.adminTerminalController.start();
               case 3 -> ComponentController.adminProfileController.start();
               case 4 -> ComponentController.adminTransactionController.start();
               case 5 -> ComponentController.adminStatisticController.start();
               default -> System.out.println(ComponentContainer.getMenu());
           }
       }
   }
}
