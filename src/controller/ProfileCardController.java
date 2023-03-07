package controller;

import container.ComponentContainer;
import container.ComponentService;
import dto.Card;

import java.time.LocalDate;

public class ProfileCardController {
    public void menuProfileCard(){

        System.out.println(" Menu User : ");
        String menu = """
                1 -> Add Card:
                2 -> Card List:
                3 -> Delete:
                4 -> Refill:
                0 -> Back:
                """;
        System.out.print(menu + "\n Select : ");
    }
    public void startProfile() {
        boolean flag = true;
        int act ;
        while (flag){
            menuProfileCard();
            act = ComponentContainer.scanInt.nextInt();
            switch (act){
                case 1 -> addProfileCard();
                case 2 -> profileByCardList();
                case 3 -> deleteProfileByCard();
                case 4 -> addProfileRefill();
                case 0 -> flag = false;
                default -> {
                    System.out.println(ComponentContainer.getMenu());
                }
            }
        }
    }
    public void deleteProfileByCard() {
        System.out.println("Card number: ");
        String cardNum = ComponentContainer.scanStr.nextLine();
        boolean b = false;
        ComponentService.cardService.deleteCardBy(cardNum,b);
    }
//    public void updateProfileByCardStatus() {
//        System.out.print(" Card Number : ");
//        String cardNum = ComponentContainer.scanStr.nextLine();
//        boolean b = ComponentService.cardService.updateCardStatus(cardNum);
//        if (!b){
//            System.out.println(" Not Found Card Number: ");
//        }
//        System.out.println( " Successfully Update: ");
//    }
    public void profileByCardList() {
        boolean b = ComponentService.cardService.showCard();
        if (!b){
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.print(" Select : ");
    }
    public void addProfileCard() {
        System.out.print(" Card Number: ");
        String number = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.cardService.addCardProfile(number);
        if (!b){
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }
    public void addProfileRefill(){
        System.out.println("Card number: ");
        String cardNum = ComponentContainer.scanStr.nextLine();
        System.out.println("Summa : ");
        double balance = ComponentContainer.scanInt.nextDouble();
        boolean b = ComponentService.cardService.addCardRefill(cardNum,balance);
        if (!b){
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }
}
