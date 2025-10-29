package bd.edu.seu.online.services;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.User;
import bd.edu.seu.online.singleton.ConnectionSingleton;

import java.sql.*;

public class UserService {
    public boolean registration(User user) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO user VALUE('" + user.getName() + "','" + user.getNid() + "','" + user.getAccount() + "','" + user.getMobile() + "','" + user.getDOB() + "','" + user.getEmail() + "','" + user.getPassword() + "');";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public User login(String mobile, String password) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user WHERE mobile='" + mobile + "' AND password='" + password + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String nid = resultSet.getString("nid");
                String account = resultSet.getString("account");
                String mobile2 = resultSet.getString("mobile");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                return new User(name, mobile2, email, dob, account, nid);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean checkAccount(String account) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM bankAccount WHERE account='" + account + "';";
            statement.execute(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String account1 = resultSet.getString("account");

                if (account1.equals(account)) {
                    return true;
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean checkAccountOnline(String mobile) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM wallet WHERE mobile='" + mobile + "';";
            statement.execute(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String mobile1 = resultSet.getString("mobile");

                if (mobile1.equals(mobile)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String[] userInfo(String mobile) {
        String[] info = new String[2112];
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user WHERE mobile ='" + mobile + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                info[0] = resultSet.getString("name");
                info[1] = resultSet.getString("mobile");
                info[2] = resultSet.getString("account");
                info[3] = resultSet.getString("email");
                info[4] = resultSet.getString("dob");
                info[5] = resultSet.getString("nid");

                return info;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean existingAccount(String mobile, String account) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user WHERE mobile='" + mobile + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String account1 = resultSet.getString("account");
                if (account1.equals(account)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean changePassword(String mobile, String newPassword) {
        try {

            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE user SET password ='" + newPassword + "' WHERE mobile='" + mobile + "';";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static void createOnlineBankingAccount(String account, String mobile, double balance) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO wallet VALUE('" + account + "','" + mobile + "'," + balance + ");";
            statement.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static double getBalanceAccount(String account) {
        double balance = 0;
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM bankAccount WHERE account ='" + account + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                balance = resultSet.getDouble("balance");

                return balance;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public static double getBalanceOnline(String wallet) {
        double balance = 0;
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM wallet WHERE mobile ='" + wallet + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                balance = resultSet.getDouble("balance");

                return balance;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public static boolean verifyPin(String password) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user WHERE password='" + password + "'AND mobile='" + HelloApplication.loggedUser.getMobile() + "';";
            statement.execute(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String password2 = resultSet.getString("password");

                if (password2.equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void chequeApply(String account, String applied, int page, int chequeBook) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO cheque VALUE('" + account + "'," + page + "," + chequeBook + ",'" + applied + "');";
            statement.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static boolean lastApplied(String account) {
        try {

            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cheque WHERE account ='" + account + "';";
            statement.executeQuery(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String acc = resultSet.getString("account");
                if (acc.equals(account)) {
                    return true;
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}

