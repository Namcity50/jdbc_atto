package service;

import container.ComponentContainer;
import container.ComponentRepository;
import container.ComponentService;
import dto.Card;
import dto.Transaction;
import enums.EnumStatus;
import enums.EnumsTransaction;
import repository.CardRepository;

import java.time.LocalDate;
import java.util.List;

public class CardService {
    public boolean addCardProfile(String number){
        Card exist = ComponentRepository.cardRepository.getCardByNumber(number);
        LocalDate localDate = LocalDate.now();
        boolean flag = true;
        if (exist.getNumber() == null){
           return false;
        }
        if (exist.getStatus().equals(EnumStatus.ACTIVE.name())){
            ComponentRepository.cardRepository.addProfileByCard(
                    localDate.plusYears(5),ComponentContainer.CURRENT_PROFILE.getPhone(),flag,number);
        }
           return true;
    }
    public void addCardAdmin(Card card) {
        Card exist = ComponentRepository.cardRepository.getCardByNumber(card.getNumber());
        if (exist != null) {
            System.out.println(" Mazgi Card exist: ");
            return;
        }
        card.setStatus(String.valueOf(EnumStatus.ACTIVE));
        card.setCreated_date(LocalDate.now());
        ComponentRepository.cardRepository.saveCard(card);
        System.out.println("Successfully : ");
    }

    public boolean showCard() {

        List<Card> cardList = ComponentRepository.cardRepository.getByCardList();
       for (Card card : cardList){
           if (card == null){
               return false;
           }
           if (card.isVisible() && card.getPhone().equals(ComponentContainer.CURRENT_PROFILE.getPhone())){
               System.out.println(card.toString().toString());
           }
       }
       return true;
    }
    public boolean updateCardStatus(String cardNum) {
        Card card = ComponentRepository.cardRepository.getCardByNumber(cardNum);
        if (card == null) {
            return false;
        } else if (card.getStatus().equals(EnumStatus.ACTIVE.name())) {
            ComponentRepository.cardRepository.updateStatusQuery(EnumStatus.BLOCE.name(), cardNum);
            return true;
        } else if (card.getStatus().equals(EnumStatus.BLOCE.name())) {
            ComponentRepository.cardRepository.updateStatusQuery(EnumStatus.ACTIVE.name(), cardNum);
            return true;
        }
        return true;
    }

    public void deleteCardBy(String cardNum, boolean visible) {
        Card card = ComponentRepository.cardRepository.getCardByNumber(cardNum);
        if (card == null) {
            System.out.println(" Not Found: ");
        }
        card.setVisible(false);
        ComponentRepository.cardRepository.deleteCardTable(visible, cardNum);
        System.out.println("Successfully delete Card: ");
    }

    public boolean addCardRefill(String cardNum, double balance) {

        Transaction transaction = new Transaction();
        Card card = ComponentRepository.cardRepository.getCardByNumber(cardNum);
        if (!card.isVisible()){
            return false;
        }
        if (card.getNumber() == null || card.getStatus().equals(EnumStatus.BLOCE.name()) ){
            return false;
        }
        if (ComponentContainer.CURRENT_PROFILE.getPhone().equals(card.getPhone())) {
            ComponentRepository.cardRepository.updateBalance(balance + card.getBalance(),cardNum);
            transaction.setAmount(balance);
            transaction.setType(EnumsTransaction.REFILL.name());
            transaction.setCreated_date(LocalDate.now());
            transaction.setCard_number(cardNum);
            transaction.setAddress("refill");
            ComponentService.transactionService.addRefillTransaction(transaction);
            return true;
        }
         return true;
    }

    public void showAdminCardList() {
        CardRepository cardRepository = new CardRepository();
        List<Card> cardList = cardRepository.getByCardList();
        for (Card card : cardList) {
            System.out.println(card.toString());
        }
    }

    public boolean deleteAdminByCard(String cardNum) {

       Card card = ComponentRepository.cardRepository.getCardByNumber(cardNum);
       if (card != null){
           ComponentRepository.cardRepository.removeCardByQuery(cardNum);
           return true;
       }
      return false;
    }

    public boolean updateCardNumber(String lastCardNum,String newCardNum,LocalDate date) {
        Card exist = ComponentRepository.cardRepository.getCardByNumber(lastCardNum);
        if (exist == null){
            return false;
        } else if (exist.getNumber().equals(lastCardNum)) {
            ComponentRepository.cardRepository.updateCardNumberAndExpDateQuery(lastCardNum,date,newCardNum);
            return true;
        }
        return true;
    }

    public void getInitCard() {
        Card card = ComponentRepository.cardRepository.getCardByNumber("2121");
        if (card.getPhone().equals("2222")){
            System.out.println("Card num = " + card.getNumber() + "Card Balance = " + card.getBalance());
        }
    }

    public boolean getTotalByAmount() {
        Card card = ComponentRepository.cardRepository.getCardByNumber("2121");
        if (card.getBalance() == null){
            return false;
        }
        if (card.getPhone().equals("2222")){
            System.out.println("Card Balance = " + card.getBalance());
        }
        return true;
    }
}
