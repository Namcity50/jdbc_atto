package dto;

import java.time.LocalDate;

public class Transaction {
    private String card_number;
    private Double amount;
    private String terminal_code;
    private String type;
    private LocalDate created_date;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTerminal_code() {
        return terminal_code;
    }

    public void setTerminal_code(String terminal_code) {
        this.terminal_code = terminal_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "card_number='" + card_number +
                ", amount=" + amount +
                ", terminal_code='" + terminal_code +
                ", type='" + type +
                ", created_date=" + created_date +
                ", address='" + address +
                '}';
    }
    public String menu(){
        return  "card_number='" + card_number +
                ", amount=" + amount +
                ", terminal_code='" + terminal_code +
                ", type='" + type +
                ", created_date=" + created_date;
    }
}
