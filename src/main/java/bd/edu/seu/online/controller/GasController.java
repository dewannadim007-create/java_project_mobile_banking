package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.Transaction;
import bd.edu.seu.online.services.TransactionServices;
import bd.edu.seu.online.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class GasController implements Initializable {
    @FXML
    private Label availableBalanceLabel;
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;

    @FXML private TextField amountField;



    @FXML
    private void changeToUtility( ) {
        HelloApplication.changeScene("utility");}
    @FXML
    private void add500()
    {
        amountField.setText("");
        amountField.setText("500");
    }
    @FXML
    private void add1000()
    {
        amountField.setText("");
        amountField.setText("1000");
    }
    @FXML
    private void add1500()
    {
        amountField.setText("");
        amountField.setText("1500");
    }
    @FXML
    private void add1200()
    {
        amountField.setText("");
        amountField.setText("1200");
    }
    @FXML
    private void proceed( )
    {
        String account = accountField.getText();
        double givenAmount = Double.parseDouble(amountField.getText());
        double balance = UserService.getBalanceOnline(HelloApplication.loggedUser.getMobile());


        if(givenAmount< balance && UserService.verifyPin(passwordField.getText()) && TransactionServices.utilityAccountCheck("titas",account,"prepaid"))
        {
            TransactionServices.utilityBillPay(account,"titas","prepaid",givenAmount);
            TransactionServices.senderBalanceUpdateOnline(HelloApplication.loggedUser.getMobile(),givenAmount);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Payment Completed Successfully");
            alert.showAndWait();
            TransactionServices.transactionHistory(  new Transaction(account, LocalDate.now().toString(),givenAmount,"utility",HelloApplication.loggedUser.getAccount()));
            HelloApplication.changeScene("utility");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Insert Valid Data");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availableBalanceLabel.setText("৳"+UserService.getBalanceOnline(HelloApplication.loggedUser.getMobile()));

    }

}
