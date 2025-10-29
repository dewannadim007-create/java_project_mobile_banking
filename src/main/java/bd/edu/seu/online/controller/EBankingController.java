package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.services.TransactionServices;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class
EBankingController {

    @FXML
    private Label bankBalanceLabel,walletBalanceLabel;

    @FXML
    void changeToHome() { HelloApplication.changeScene("home");}

    @FXML
    public void addMoney() {
        HelloApplication.changeScene("addMoney");
    }

    public void checkWallet() {
    walletBalanceLabel.setText("৳"+ UserService.getBalanceOnline(HelloApplication.loggedUser.getMobile()));
    }
    public void checkBank() {
        bankBalanceLabel.setText("৳"+ UserService.getBalanceAccount(HelloApplication.loggedUser.getAccount()));
    }



}
