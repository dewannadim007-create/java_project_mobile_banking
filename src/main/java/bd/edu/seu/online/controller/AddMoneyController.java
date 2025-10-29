package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.Transaction;
import bd.edu.seu.online.services.TransactionServices;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddMoneyController implements Initializable {
    @FXML
    private TextField amountField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label balanceLabel;


    @FXML
    public void transfer()
    {
        double amount = Double.parseDouble(amountField.getText());
        String password = passwordField.getText();
        UserService userService = new UserService();
        boolean haveAccount = userService.checkAccountOnline(HelloApplication.loggedUser.getMobile());
        double senderBalance = UserService.getBalanceAccount(HelloApplication.loggedUser.getAccount());
        double remainingBalance = senderBalance - amount-1000;
        boolean pinVerification = UserService.verifyPin(password);


        if(haveAccount && remainingBalance>amount && pinVerification  )
        {
            TransactionServices.balanceTransferOnline(HelloApplication.loggedUser.getMobile(),amount);
            TransactionServices.senderBalanceUpdate(HelloApplication.loggedUser.getMobile(),amount);
            TransactionServices.transactionHistory(  new Transaction(HelloApplication.loggedUser.getMobile(), LocalDate.now().toString(),amount,"add to wallet",HelloApplication.loggedUser.getAccount()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Transfer Successful");
            alert.showAndWait();
            amountField.setText("");
            passwordField.setText("");

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Provide Valid Information");
            alert.showAndWait();
            amountField.setText("");
            passwordField.setText("");

        }
    }
    public void changeToEBanking()
    {
        HelloApplication.changeScene("eBanking");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        balanceLabel.setText("Bank Balance: ৳"+UserService.getBalanceAccount(HelloApplication.loggedUser.getAccount()));
    }
}
