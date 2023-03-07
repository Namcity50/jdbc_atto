package repository;

import database.DataBase;
import dto.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ProfileRepository {
    public static void intAdmin() {
        try {
            Connection con = DataBase.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into profile(id,name,surname,phone,password,status,role,created_date,visible) " +
                            "values('-1','ali','aliyev','2222','2222','REGISTRETED','ADMIN',now(),true)" +
                            " ON CONFLICT (id) DO NOTHING;");
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void intAdminCard() {
        try {
            Connection con = DataBase.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into card(id,number,exp_date,balance,status,phone,created_date,visible) " +
                            "values('-1','2121',now(),'0','ACTIVE','2222',now(),true)" +
                            " ON CONFLICT (id) DO NOTHING;");
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveProfile(Profile profile) {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into profile(name,surname,phone,password,status,role,created_date)" +
                            "values (?,?,?,?,?,?,now());");
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getSurname());
            preparedStatement.setString(3, profile.getPhone());
            preparedStatement.setString(4, profile.getPassword().trim());
            preparedStatement.setString(5, profile.getStatus());
            preparedStatement.setString(6, profile.getRole());
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public Profile getByProfilePhone(String phone) {
        try {
            Connection con = DataBase.getConnection();
            Profile profile = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from profile where phone =?");
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setStatus(resultSet.getString("status"));
                profile.setRole(resultSet.getString("role"));
                profile.setCreated_date(LocalDate.from(resultSet.getTimestamp("created_date").toLocalDateTime()));

            }
            con.close();
            return profile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Profile getByPhoneAndPassword(String phone, String password) {
        Profile profile = null;
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from profile where phone =? and password =?");
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setStatus(resultSet.getString("status"));
                profile.setRole(resultSet.getString("role"));
                profile.setCreated_date(LocalDate.from(resultSet.getTimestamp("created_date").toLocalDateTime()));
            }
            con.close();
            return profile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Profile> getByProfileList() {
        Connection con = DataBase.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from profile ");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Profile> profileList = new LinkedList<>();
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setStatus(resultSet.getString("status"));
                profile.setRole(resultSet.getString("role"));
                profile.setCreated_date(LocalDate.from(resultSet.getDate("created_date").toLocalDate()));
                profile.setVisible(resultSet.getBoolean("visible"));
                profileList.add(profile);
            }
            con.close();
            return profileList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateStatusProfileQuery(String status, String number) {
        try {
            Connection con = DataBase.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "update profile set status =? where phone =? ");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, number);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println("Successfully: " + effectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
