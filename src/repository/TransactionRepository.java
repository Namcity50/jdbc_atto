package repository;

import database.DataBase;
import dto.Card;
import dto.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {
    public void createTransaction(Transaction transaction) {
        System.out.println(transaction.toString());
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into transaction(amount,type,created_date,terminal_code,card_number)" +
                            "values (?,?,?,?,?);");
            preparedStatement.setDouble(1, transaction.getAmount());
            preparedStatement.setString(2, transaction.getType());
            preparedStatement.setDate(3, Date.valueOf(transaction.getCreated_date()));
            preparedStatement.setString(4, transaction.getTerminal_code());
            preparedStatement.setString(5, transaction.getCard_number());
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> getTransactionList() {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from transaction order by created_date desc");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Transaction> transactionList = new LinkedList<>();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setType(resultSet.getString("type"));
                transaction.setCreated_date(LocalDate.from(resultSet.getDate("created_date").toLocalDate()));
                transaction.setTerminal_code(resultSet.getString("terminal_code"));
                transaction.setCard_number(resultSet.getString("card_number"));
                transactionList.add(transaction);
            }
            con.close();
            return transactionList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Transaction> getTransactionListDate(LocalDate fromDate,LocalDate toDate) {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from transaction where created_date between ? and ? order by created_date desc");
            preparedStatement.setDate(1, Date.valueOf(fromDate));
            preparedStatement.setDate(2, Date.valueOf(toDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Transaction> transactionList = new LinkedList<>();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setType(resultSet.getString("type"));
                transaction.setCreated_date(LocalDate.from(resultSet.getDate("created_date").toLocalDate()));
                transaction.setTerminal_code(resultSet.getString("terminal_code"));
                transaction.setCard_number(resultSet.getString("card_number"));
                transactionList.add(transaction);
            }
            con.close();
            return transactionList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Transaction> getTransactionProfileList() {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select ter.address as address," +
                            "tr.amount as amount," +
                            "tr.type as type," +
                            "tr.created_date as created_date," +
                            "tr.terminal_code as terminal_code," +
                            "tr.card_number as card_number" +
                            " from transaction tr inner join terminal ter on " +
                            "tr.terminal_code = ter.code order by created_date desc");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Transaction> transactionList = new LinkedList<>();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setAddress(resultSet.getString("address"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setType(resultSet.getString("type"));
                transaction.setCreated_date(LocalDate.from(resultSet.getDate("created_date").toLocalDate()));
                transaction.setTerminal_code(resultSet.getString("terminal_code").trim());
                transaction.setCard_number(resultSet.getString("card_number").trim());
                transactionList.add(transaction);
            }
            con.close();
            return transactionList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Transaction getTranByTerminalCode(String terNum) {
        Transaction transaction = new Transaction();
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from transaction where terminal_code=? ");
            preparedStatement.setString(1,terNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setType(resultSet.getString("type"));
                transaction.setTerminal_code(resultSet.getString("terminal_code"));
                transaction.setCard_number(resultSet.getString("card_number"));
            }
            connection.close();
            return transaction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Transaction getTranByCardNumber(String cardNum) {

        Transaction transaction = new Transaction();
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from transaction where card_number=? ");
            preparedStatement.setString(1,cardNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setType(resultSet.getString("type"));
                transaction.setTerminal_code(resultSet.getString("terminal_code"));
                transaction.setCard_number(resultSet.getString("card_number"));
            }
            connection.close();
            return transaction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
