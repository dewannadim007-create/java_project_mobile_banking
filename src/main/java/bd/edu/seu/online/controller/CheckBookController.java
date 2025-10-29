package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CheckBookController implements Initializable {

    @FXML
    private PasswordField passwordField;
    ;

    @FXML
    private ChoiceBox<Integer> checkNumberChoice;

    @FXML
    private Label mobileLabel,nameLabel,accountLabel;

    @FXML
    private ChoiceBox<Integer> pageChoiceBox;

    @FXML
    public void apply() {

        if(pageChoiceBox.getValue()== null || checkNumberChoice.getValue()== null || passwordField.getText() == null) {
        }
        else {

            String applyDate = String.valueOf(LocalDate.now());
            int page = pageChoiceBox.getValue();
            int checkNumber = checkNumberChoice.getValue();
            String password = passwordField.getText();
            String account = HelloApplication.loggedUser.getAccount();
            if (UserService.verifyPin(password)) {
                if (!UserService.lastApplied(HelloApplication.loggedUser.getAccount())) {
                    UserService.chequeApply(account, applyDate, page, checkNumber);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Success");
                    alert.showAndWait();
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Already Applied");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Password");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void changeToHome() { HelloApplication.changeScene("home");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(HelloApplication.loggedUser.getName());
        accountLabel.setText(HelloApplication.loggedUser.getAccount());
        mobileLabel.setText(HelloApplication.loggedUser.getMobile());


        ObservableList<Integer>observableList= FXCollections.observableArrayList();
        observableList.add(10);
        observableList.add(20);
        observableList.add(50);
        pageChoiceBox.setItems(observableList);

        ObservableList<Integer>observableListNew= FXCollections.observableArrayList();
        observableListNew.add(1);
        observableListNew.add(2);
        checkNumberChoice.setItems(observableListNew);



    }
}
