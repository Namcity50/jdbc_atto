package repository;

import database.DataBase;
import dto.Terminal;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TerminalRepository {
    public void createTerminal(Terminal terminal) {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into terminal(code,address,status,created_date,visible)" +
                            "values (?,?,?,now(),?)");
            preparedStatement.setString(1, terminal.getCode());
            preparedStatement.setString(2, terminal.getAddress());
            preparedStatement.setDate(3, Date.valueOf(terminal.getCreated_date()));
            preparedStatement.setBoolean(4, terminal.isVisible());
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Terminal getTerminal(String code) {
        Connection con = DataBase.getConnection();
        Terminal terminal = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from terminal where code =?");
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                terminal = new Terminal();
                terminal.setCode("code");
                terminal.setAddress("address");
                terminal.setStatus("status");
                terminal.setCreated_date(LocalDate.from(resultSet.getTimestamp("created_date").toLocalDateTime()));
                terminal.setVisible(resultSet.getBoolean("visible"));
            }
            con.close();
            return terminal;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Terminal> getListAll() {

        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from terminal");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Terminal> terminalList = new LinkedList<>();
            while (resultSet.next()) {
                Terminal terminal = new Terminal();
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setStatus(resultSet.getString("status"));
                terminal.setCreated_date(LocalDate.from(resultSet.getDate("created_date").toLocalDate()));
                terminal.setVisible(resultSet.getBoolean("visible"));
                terminalList.add(terminal);
            }
            con.close();
            return terminalList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Terminal getTerminalCode(String terCode) {
        try {
            Connection con = DataBase.getConnection();
            Terminal terminal = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from terminal where code =? ");
            preparedStatement.setString(1, terCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                terminal = new Terminal();
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setStatus(resultSet.getString("status"));
                terminal.setCreated_date(LocalDate.from(resultSet.getTimestamp("created_date").toLocalDateTime()));
                terminal.setVisible(resultSet.getBoolean("visible"));
            }
            con.close();
            return terminal;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateTerminalAddress(String code, String address) {
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "update terminal set address =? where code =? ");
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, code);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTerminalStatus(String status, String code) {
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "update terminal set status =? where number =? ");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, code);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTerminalByQuery(String code) {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("delete from terminal where  code =? ");
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //    public void updateTerminalCode(String newCode, String oldCode) {
//        try {
//            Connection con = DataBase.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement(
//                    "update terminal set code =? where code =? ");
//            preparedStatement.setString(1, newCode);
//            preparedStatement.setString(2, oldCode);
//            int effectedRows = preparedStatement.executeUpdate();
//            System.out.println(effectedRows);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
