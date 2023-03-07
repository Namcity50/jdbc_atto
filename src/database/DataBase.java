package database;

import repository.ProfileRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_atto", "postgres", "nukus111");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void createTableProfile() {
        try {
            String profileTable = "create table if not exists profile(" +
                    "id serial primary key, " +
                    "name varchar(20) not null ," +
                    "surname varchar(20)not null ," +
                    "phone varchar(13)not null, " +
                    "password character(32)not null, " +
                    "status varchar(20)," +
                    "role varchar(20)," +
                    "created_date date," +
                    "visible boolean );";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(profileTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void createTableCard() {
        try {
            String cardTable = "create table if not exists card(" +
                    "id serial primary key ," +
                    "number varchar(20) unique not null," +
                    "exp_date date," +
                    "balance double precision," +
                    "status varchar(20)," +
                    "phone varchar(20)," +
                    "created_date date," +
                    "visible boolean );";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(cardTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void createTableTerminal() {
        try {
            String terminalTable = "create table if not exists terminal(" +
                    "code varchar (20)unique, " +
                    "address varchar(20) not null, " +
                    "status varchar(20) not null ," +
                    "created_date date," +
                    "visible boolean);";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(terminalTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void createTableTransaction() {
        try {
            String createTransaction = "create table if not exists transaction(" +
                    "amount double precision," +
                    "type varchar(20), " +
                    "created_date date," +
                    "terminal_code varchar(20), " +
                    "card_number varchar(20)," +
                    "constraint terminal_fk foreign key (terminal_code)" +
                    "references terminal(code)," +
                    "constraint cardNum_fk foreign key (card_number)" +
                    "references card(number));";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(createTransaction);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean initData() {
        getConnection();
        createTableProfile();
        createTableCard();
        createTableTerminal();
        createTableTransaction();
        ProfileRepository.intAdmin();
        ProfileRepository.intAdminCard();

        return true;
    }
}

