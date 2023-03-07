package service;

import container.ComponentContainer;
import container.ComponentRepository;
import dto.Card;
import dto.Terminal;
import dto.Transaction;
import enums.EnumsTransaction;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TransactionService {
    public void getList() {
        List<Transaction> transactionList = ComponentRepository.transactionRepository.getTransactionProfileList();
        for (Transaction transaction : transactionList) {
            System.out.println(transaction.toString());
        }
    }

    public boolean addProfilePaymentTransaction(String cardNum, String terCode) {
        Transaction transaction = new Transaction();
        Card existC = ComponentRepository.cardRepository.getCardByNumber(cardNum);
        Terminal existT = ComponentRepository.terminalRepository.getTerminal(terCode);
        if (existT.getCode() == null && existC.getNumber() == null || !existC.isVisible()) {
            return false;
        }
        if (existC.getPhone().trim().equals(ComponentContainer.CURRENT_PROFILE.getPhone().trim()) &&
                existC.getBalance() >= 1400.0) {
            ComponentRepository.cardRepository.updateBalance(existC.getBalance() - 1400.0, cardNum);
            transaction.setAmount(1400.0);
            transaction.setType(EnumsTransaction.PAYMENT.name());
            transaction.setCreated_date(LocalDate.now());
            transaction.setTerminal_code(terCode);
            transaction.setCard_number(cardNum);
            ComponentRepository.transactionRepository.createTransaction(transaction);
        }
        Card card = ComponentRepository.cardRepository.getCardByNumber("2121");
        if (card.getNumber().equals("2121")) {
            ComponentRepository.cardRepository.updateBalance(card.getBalance() + 1400.0, "2121");
        }
        return true;
    }

    public void addRefillTransaction(Transaction transaction) {
        ComponentRepository.transactionRepository.createTransaction(transaction);
    }

    public boolean getPaymentByToday() {
        List<Transaction> transactionList = ComponentRepository.transactionRepository.getTransactionList();
        String add = String.valueOf(LocalDate.now());
        String[] sp = add.split("-");
        String day = sp[2];
        for (Transaction transaction : transactionList) {
            String s = String.valueOf(transaction.getCreated_date());
            String[] split = s.split("-");
            String date = split[2];
            if (date == null) {
                return false;
            }
            if (date.equals(day)) {
                System.out.println(Arrays.toString(new String[]{transaction.toString()}));
            }
        }
        return true;
    }

    public boolean getDailyBYPayment(String daily) {
        List<Transaction> transactionList = ComponentRepository.transactionRepository.getTransactionList();
        for (Transaction transaction : transactionList) {
            String s = String.valueOf(transaction.getCreated_date());
            if (s == null) {
                return false;
            }
            if (s.equals(daily)) {
                System.out.println(Arrays.toString(new String[]{transaction.toString()}));
            }
        }
        return true;
    }

    public boolean getInterimByPayments(LocalDate date, LocalDate day) {
        List<Transaction> transactionList = ComponentRepository.transactionRepository.getTransactionListDate(date,day);
        for (Transaction transaction : transactionList) {
            LocalDate localDate = transaction.getCreated_date();
            if (localDate == null){
                return false;
            }
                System.out.println(transaction.toString().toString());
        }
        return true;
    }

    public boolean getTransactionByTerminal(String terNum) {
        Transaction transaction = ComponentRepository.transactionRepository.getTranByTerminalCode(terNum);
        if (transaction.getTerminal_code() == null){
            return false;
        }
        if (transaction.getTerminal_code().equals(terNum)){
            System.out.println(transaction.toString().toString());
        }
        return true;
    }

    public boolean getTransactionByCard(String cardNum) {
        Transaction transaction = ComponentRepository.transactionRepository.getTranByCardNumber(cardNum);
        if (transaction.getTerminal_code() == null){
            return false;
        }
        if (transaction.getTerminal_code().equals(cardNum)){
            System.out.println(transaction.toString().toString());
        }
        return true;

    }
}