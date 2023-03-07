package repository;

import database.DataBase;
import dto.Card;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CardRepository {
    public void saveCard(Card card) {
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into card(number,exp_date,balance,status,phone,created_date,visible)" +
                            "values (?,?,?,?,?,now(),?);");
            preparedStatement.setString(1, card.getNumber());
            preparedStatement.setDate(2, Date.valueOf(card.getExp_date()));
            preparedStatement.setDouble(3, card.getBalance());
            preparedStatement.setString(4, card.getStatus());
            preparedStatement.setString(5, card.getPhone());
            preparedStatement.setBoolean(6, card.isVisible());
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Card getCardByNumber(String number) {
        try {
            Connection con = DataBase.getConnection();
            Card card = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from card where number =? ");
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number").trim());
                card.setExp_date(LocalDate.from(resultSet.getDate("exp_date").toLocalDate()));
                card.setBalance(resultSet.getDouble("balance"));
                card.setStatus(resultSet.getString("status"));
                card.setPhone(resultSet.getString("phone"));
                card.setVisible(resultSet.getBoolean("visible"));
                card.setCreated_date(LocalDate.from(resultSet.getTimestamp("created_date").toLocalDateTime()));
            }
            con.close();
            return card;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Card> getByCardList() {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from card ");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Card> cardList = new LinkedList<>();
            while (resultSet.next()) {
                Card card = new Card();
                card.setNumber(resultSet.getString("number"));
                card.setExp_date(LocalDate.from(resultSet.getDate("exp_date").toLocalDate()));
                card.setBalance(resultSet.getDouble("balance"));
                card.setPhone(resultSet.getString("phone"));
                card.setStatus(resultSet.getString("status"));
                card.setVisible(resultSet.getBoolean("visible"));
                cardList.add(card);
            }
            con.close();
            return cardList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatusQuery(String status, String number) {
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "update card set status =? where number =? ");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, number);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeCardByQuery(String cardNum) {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("delete from card where  number =? ");
            preparedStatement.setString(1, cardNum);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCardTable(boolean visible, String cardNum) {
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("update card set visible =?  where number =? ");
            preparedStatement.setBoolean(1, visible);
            preparedStatement.setString(2, cardNum);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBalance(double balance, String cardNum) {

        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("update card set balance =? where number =?");
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, cardNum);
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateCardNumberAndExpDateQuery(String newtCardNum, LocalDate date, String lastCard) {
        try {
            Connection con = DataBase.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(
                    "update card set number =?, exp_date =? where number =?");
            preparedStatement.setString(1, lastCard.trim());
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setString(3, newtCardNum.trim());
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProfileByCard(LocalDate localDate, String phone, boolean flag, String number) {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "update card set exp_date =?,phone =?,visible=? where number =?");
            preparedStatement.setDate(1, Date.valueOf(localDate));
            preparedStatement.setString(2,phone);
            preparedStatement.setBoolean(3,flag);
            preparedStatement.setString(4,number);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
