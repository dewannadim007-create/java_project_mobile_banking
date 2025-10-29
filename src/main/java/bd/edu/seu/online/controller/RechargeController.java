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

public class RechargeController implements Initializable {
    @FXML
    private Label availableBalanceLabel;
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;

    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> operatorChoicebox;
    @FXML private ChoiceBox<String> typeChoiceBox;


    @FXML private void changeToUtility( ) {
        HelloApplication.changeScene("utility");}
    @FXML private void add20() {
        amountField.setText("");
        amountField.setText("20");
    }
    @FXML private void add50() {
        amountField.setText("");
        amountField.setText("50");
    }
    @FXML private void add100() {
        amountField.setText("");
        amountField.setText("100");
    }
    @FXML private void add500() {
        amountField.setText("");
        amountField.setText("500");
    }

    @FXML private void proceed( ) {
        if(!amountField.getText().isEmpty())
        {
            String account = accountField.getText();
            double givenAmount = Double.parseDouble(amountField.getText());
            double balance = UserService.getBalanceOnline(HelloApplication.loggedUser.getMobile());
            String provider = operatorChoicebox.getValue().toLowerCase();
            String type = typeChoiceBox.getValue();

            if (givenAmount < balance && UserService.verifyPin(passwordField.getText()) && givenAmount >= 20 && account.length() == 11) {
                TransactionServices.utilityBillPay(account, provider, type, givenAmount);
                TransactionServices.senderBalanceUpdateOnline(HelloApplication.loggedUser.getMobile(), givenAmount);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Payment Completed Successfully");
                alert.showAndWait();
                TransactionServices.transactionHistory(new Transaction(account, LocalDate.now().toString(), givenAmount, "recharge", HelloApplication.loggedUser.getMobile()));
                HelloApplication.changeScene("recharge");
            }
            else
               {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Insert Valid Data");
                alert.showAndWait();
              }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availableBalanceLabel.setText("৳"+UserService.getBalanceOnline(HelloApplication.loggedUser.getMobile()));
        ObservableList<String>observableList = FXCollections.observableArrayList();
        observableList.add("Grameenphone");
        observableList.add("Airtel");
        observableList.add("Banglalink");
        observableList.add("Teletalk");
        observableList.add("Robi");
        operatorChoicebox.setItems(observableList);
        ObservableList<String>observableList2 = FXCollections.observableArrayList();
        observableList2.add("Prepaid");
        observableList2.add("Postpaid");
        typeChoiceBox.setItems( observableList2);

    }

}
