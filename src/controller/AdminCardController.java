package controller;

import container.ComponentContainer;
import container.ComponentService;
import dto.Card;
import service.CardService;

import java.time.LocalDate;

public class AdminCardController {
    public void cardMenuAdmin() {
        String menu = """
                1 -> Created Card:
                2 -> Card List:
                3 -> Update Card:     
                4 -> Change Card status:
                5 -> Delete Card:
                0 -> Back:
                """;
        System.out.println(menu + "Select : ");
    }

    public void start() {
        int act ;
        boolean flag = true;
        while (flag){
            cardMenuAdmin();
            act = ComponentContainer.scanInt.nextInt();
            switch (act){
                case 1 -> createdCard();
                case 2 -> cardList();
                case 3 -> updateCardNumAdmin();
                case 4 -> updateAdminCardStatus();
                case 5 -> deleteCard();
                case 0 -> flag = false;
                default -> System.out.println(ComponentContainer.getMenu());
            }
        }
    }

    public void deleteCard() {
        System.out.print(" Card Number : ");
        String cardNum = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.cardService.deleteAdminByCard(cardNum);
        if (!b){
            System.out.println(" Not Found Card Number: ");
        }
        System.out.println( " Successfully Delete: ");
    }

    public void updateAdminCardStatus() {
        System.out.print(" Card Number : ");
        String cardNum = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.cardService.updateCardStatus(cardNum);
        if (!b){
            System.out.println(" Not Found Card Number: ");
        }
        System.out.println( " Successfully Update: ");
    }

    public void updateCardNumAdmin() {
        System.out.print(" Card Number : ");
        String cardNum = ComponentContainer.scanStr.nextLine();
        System.out.print(" New Card Number : ");
        String newCardNum = ComponentContainer.scanStr.nextLine();
        System.out.println("Exp_Date: ");
        String exp_date = ComponentContainer.scanStr.nextLine();
        String[] split = exp_date.split("-");
        LocalDate expDate = LocalDate.of(Integer.valueOf(split[1]),Integer.valueOf(split[0]),1);
        boolean b = ComponentService.cardService.updateCardNumber(cardNum,newCardNum,expDate);
        if (!b){
            System.out.println(" Not Found Card Number: ");
        }
        System.out.println( " Successfully Update: ");
    }

    public void cardList() {
        ComponentService.cardService.showAdminCardList();
    }

    public void createdCard() {
        System.out.println(" Card number: ");
        String num = ComponentContainer.scanStr.nextLine();
        System.out.println(" Exp_date: ");
        String exp_date = ComponentContainer.scanStr.nextLine();
        String[] split = exp_date.split("-");
        LocalDate localDate = LocalDate.of(Integer.valueOf(split[1]), Integer.valueOf(split[0]),1);
        Card card = new Card();
        card.setNumber(num);
        card.setExp_date(localDate);
        card.setBalance(000.00);
        card.setPhone("admin");
        card.setVisible(false);
        ComponentService.cardService.addCardAdmin(card);
    }
}
