package controller;

import container.ComponentContainer;
import container.ComponentService;

import java.time.LocalDate;

public class AdminStatisticController {
    public void menuStatistic() {
        String menu = """
                1 -> Today's Payments:          Bugungi to'lovlar:
                2 -> Daily Payments:            Kunlik to'lovlar:
                3 -> Interim Payments:          Oraliq to'lovlar:
                4 -> Total Amount:              Umumiy balance:
                5 -> Transaction By Terminal:
                6 -> Transaction By Card:
                0 -> Back:
                """;
        System.out.println(menu + "Select : ");
    }

    public void start() {
        boolean flag = true;
        int act;
        while (flag) {
            menuStatistic();
            act = ComponentContainer.scanInt.nextInt();
            switch (act) {
                case 1 -> getTodayPayments();
                case 2 -> getDailyPayments();
                case 3 -> getInterimPayments();
                case 4 -> getTotalAmount();
                case 5 -> getTransactionTerminal();
                case 6 -> getTransactionCard();
                case 0 -> flag = false;
                default -> System.out.println(ComponentContainer.getMenu());
            }
        }
    }

    public void getTransactionCard() {
        System.out.println("Card number : ");
        String cardNum = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.transactionService.getTransactionByCard(cardNum);
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }

    public void getTransactionTerminal() {
        System.out.println("Terminal Number : ");
        String terNum = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.transactionService.getTransactionByTerminal(terNum);
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }

    public void getTotalAmount() {
        boolean b = ComponentService.cardService.getTotalByAmount();
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }

    }

    public void getInterimPayments() {
        System.out.println("From Year-Month-Day:");
        String date = ComponentContainer.scanStr.nextLine();
        System.out.println("To Year-Month-Day:");
        String day = ComponentContainer.scanStr.nextLine();
        String[] fromDate = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.valueOf(fromDate[0]), Integer.valueOf(fromDate[1]), Integer.valueOf(fromDate[2]));
        String[] toDate = day.split("-");
        LocalDate localDate1 = LocalDate.of(Integer.valueOf(toDate[0]), Integer.valueOf(toDate[1]), Integer.valueOf(toDate[2]));
        boolean b = ComponentService.transactionService.getInterimByPayments(localDate, localDate1);
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }

    public void getDailyPayments() {
        System.out.println("Year-Month-Day:");
        String date = ComponentContainer.scanStr.nextLine();
        boolean b = ComponentService.transactionService.getDailyBYPayment(date);
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }

    public void getTodayPayments() {
        boolean b = ComponentService.transactionService.getPaymentByToday();
        if (!b) {
            System.out.println(ComponentContainer.getMenu());
        }
        System.out.println("Successfully:");
    }
}
