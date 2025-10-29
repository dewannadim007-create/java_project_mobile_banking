package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.Transaction;
import bd.edu.seu.online.services.TransactionServices;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WBController {
    @FXML private TextField amountField,receiverAccountField;

    @FXML private PasswordField passwordField;

    @FXML
    public void transfer()
    {
        double amount = Double.parseDouble(amountField.getText());
        String receiverAccount = receiverAccountField.getText();
        String password = passwordField.getText();
        UserService userService = new UserService();
        boolean haveAccount = userService.checkAccount(receiverAccount);
        double senderBalance = UserService.getBalanceOnline(HelloApplication.loggedUser.getMobile());
        double remainingBalance = senderBalance - amount;
        boolean pinVerification = UserService.verifyPin(password);
        boolean accountCheck = HelloApplication.loggedUser.getAccount().equals(receiverAccount);


        if(haveAccount && remainingBalance>=amount && pinVerification )
        {
            TransactionServices.balanceTransfer(receiverAccount,amount);
            TransactionServices.senderBalanceUpdateOnline(HelloApplication.loggedUser.getMobile(),amount);
            TransactionServices.transactionHistory(  new Transaction(receiverAccount, LocalDate.now().toString(),amount,"wallet to bank",HelloApplication.loggedUser.getAccount()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Transfer Successful");
            alert.showAndWait();
            amountField.setText("");
            receiverAccountField.setText("");
            passwordField.setText("");

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Provide Valid Information");
            alert.showAndWait();
            amountField.setText("");
            receiverAccountField.setText("");
            passwordField.setText("");

        }
    }
    public void changeToHome()
    {
        HelloApplication.changeScene("sendMoney");
    }
}
