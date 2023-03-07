package controller;

import container.ComponentContainer;
import container.ComponentController;

public class ProfileController {

   public void menuProfile(){
       String menu = """
               1 -> Card:
               2 -> Transaction:
               """;
       System.out.println(menu + "Select: ");
   }
   public void start(){
       boolean flag = true;
       int act;
       while (flag){
           menuProfile();
           act = ComponentContainer.scanInt.nextInt();
           switch (act){
               case 1 -> ComponentController.profileCardController.startProfile();
               case 2 -> ComponentController.profileTranController.start();
               case 0 -> flag = false;
               default -> {
                   System.out.println(ComponentContainer.getMenu());
               }
           }
       }
   }
}
