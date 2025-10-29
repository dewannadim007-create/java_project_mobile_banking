package bd.edu.seu.online.services;


import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.Transaction;
import bd.edu.seu.online.singleton.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionServices {

    public static void balanceTransfer(String receiverAccount, double amount) {
        try {
            double receiverBalance = UserService.getBalanceAccount(receiverAccount);
            double finalBalance = receiverBalance + amount;

            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE bankAccount SET balance = " + finalBalance + " WHERE account='" + receiverAccount + "';";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void balanceTransferOnline(String receiverWallet, double amount) {
        try {
            double receiverBalance = UserService.getBalanceOnline(receiverWallet);
            double finalBalance = receiverBalance + amount;

            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE wallet SET balance = " + finalBalance + " WHERE mobile='" + receiverWallet + "';";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void senderBalanceUpdate(String mobile, double amount) {

        try {
            double senderBalance = UserService.getBalanceAccount(HelloApplication.loggedUser.getAccount());
            double finalBalance = senderBalance - amount;
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE bankAccount SET balance = " + finalBalance + " WHERE account='" + HelloApplication.loggedUser.getAccount() + "';";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void senderBalanceUpdateOnline(String mobile, double amount) {

        try {
            double senderBalance = UserService.getBalanceOnline(mobile);
            double finalBalance = senderBalance - amount;
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE wallet SET balance = " + finalBalance + " WHERE mobile='" + mobile + "';";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public  static  boolean utilityAccountCheck(String provider,String account,String type) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM utility WHERE account='" + account + "'AND type='" + type + "';";
            statement.execute(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String provider2 = resultSet.getString("provider");

                if (provider2.equals(provider)) {
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

    public static double utilityBillCheck(String account, String provider, String type) {
        double balance = 0;
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM utility WHERE account ='" + account + "' AND type='" + type + "' AND provider='"+provider+"';";
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

    public static void utilityBillPay(String account , String provider,String type, double amount) {
        double previousBalance = utilityBillCheck(account,provider,type);
        double finalBalance = previousBalance + amount;
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE utility SET balance = " + finalBalance + " WHERE account='" + account + "';";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean transactionHistory (Transaction transaction) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO transaction VALUE('"+transaction.getReceiver()+"','"+transaction.getDate()+"',"+transaction.getAmount()+",'"+transaction.getType()+"','"+transaction.getSender()+"');";
            statement.execute(query);
            return true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public static  List<Transaction> getTransactionList (String senderAccount) {
        List<Transaction> transactionList = new ArrayList<>();
        try
        {
            Connection connection =ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM transaction WHERE sender ='"+senderAccount+"';";
            ResultSet resultSet= statement.executeQuery(query);
            while (resultSet.next())
            {

                String receiver = resultSet.getString("receiver");
                String date = resultSet.getString("date");
                String type =  resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                Transaction transaction =  new Transaction(receiver,date,amount,type,senderAccount);
                transactionList.add(transaction);

            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return  transactionList;

    }

    public static String[] getMonthlyWalletExpense(String monthName) {
        String[] info = new String[2112];
        double monthlyExpense = 0;
        int count=0;
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement= connection.createStatement();
            String query = "SELECT amount FROM transaction WHERE monthname(date)='"+monthName+"' AND sender='"+HelloApplication.loggedUser.getAccount()+"' AND (type='utility' OR type='recharge' OR type='wallet to bank' OR type='wallet to wallet');";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                double ex = resultSet.getDouble("amount");
                monthlyExpense += ex;
                count++;
            }
            info[0]= String.valueOf(monthlyExpense);
            info[1]= String.valueOf(count);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return info;
    }

    public static String[] getDailyWalletExpense( ) {
        String[] info = new String[2112];
        double monthlyExpense = 0;
        int count=0;
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement= connection.createStatement();
            String query = "SELECT amount FROM transaction WHERE date=CURDATE() AND sender='"+HelloApplication.loggedUser.getAccount()+"' AND (type='utility' OR type='recharge' OR type='wallet to bank' OR type='wallet to wallet');";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                double ex = resultSet.getDouble("amount");
                monthlyExpense += ex;
                count++;
            }
            info[0]= String.valueOf(monthlyExpense);
            info[1]= String.valueOf(count);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return info;
    }

    public static String[] getMonthlyAccountExpense(String monthName) {
        String[] info = new String[2112];
        double monthlyExpense = 0;
        int count=0;
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement= connection.createStatement();
            String query = "SELECT amount FROM transaction WHERE monthname(date)='"+monthName+"' AND sender='"+HelloApplication.loggedUser.getAccount()+"' AND (type='add to wallet' OR type='bank to bank' OR type='bank to wallet');";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                double ex = resultSet.getDouble("amount");
                monthlyExpense += ex;
                count++;
            }
            info[0]= String.valueOf(monthlyExpense);
            info[1]= String.valueOf(count);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return info;
    }

    public static String[] getDailyAccountExpense( ) {
        String[] info = new String[2112];
        double monthlyExpense = 0;
        int count=0;
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement= connection.createStatement();
            String query = "SELECT amount FROM transaction WHERE date=CURDATE() AND sender='"+HelloApplication.loggedUser.getAccount()+"' AND (type='add to wallet' OR type='bank to bank' OR type='bank to wallet');";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                double ex = resultSet.getDouble("amount");
                monthlyExpense += ex;
                count++;
            }
            info[0]= String.valueOf(monthlyExpense);
            info[1]= String.valueOf(count);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return info;
    }
}



