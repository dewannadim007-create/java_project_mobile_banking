package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.User;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class RegistrationController {
    @FXML private TextField accountField,emailField,mobileField,nameField,nidField;

    @FXML private DatePicker dobPicker;

    @FXML private PasswordField passwordField;

    @FXML public void changeToLogin() {
        HelloApplication.changeScene("login");
    }

    @FXML
    public void register() {

        boolean check = true;

        if (nameField.getText().isEmpty()) {
            check = false;
            nameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }

        if (nidField.getText().isEmpty()) {
            check = false;
            nidField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

        }

        if (dobPicker.getValue() == null || dobPicker.getValue().isAfter(LocalDate.now()) || dobPicker.getValue().equals(LocalDate.now())) {
            check = false;
            dobPicker.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }

        if (accountField.getText().isEmpty()) {
            check = false;
            accountField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }

        if (mobileField.getText().isEmpty()) {
            check = false;
            mobileField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
        if (emailField.getText().isEmpty()) {
            check = false;
            emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
        if (!passwordField.getText().isEmpty() )
        {
            int length = passwordField.getText().length();
            if (length > 8|| length==7|| length==6|| length==5|| length==4|| length==3|| length==2|| length==1 ) {
                check = false;
                passwordField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

            }
        }

        if (check) {
            String name = nameField.getText();
            String nid = nidField.getText();
            String password = passwordField.getText();
            LocalDate localDate = dobPicker.getValue();
            String dob = localDate.toString();
            String email = emailField.getText();
            String mobile = mobileField.getText();
            String account = accountField.getText();
            User user = new User(name, mobile, email, password, dob, account, nid);
            UserService userService = new UserService();
            boolean haveAccount = userService.checkAccount(account);
            if (haveAccount) {
                boolean exist = UserService.existingAccount(mobile, account);
                if (!exist) {
                    boolean isRegistered = userService.registration(user);
                    if (isRegistered) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Registration Done.Proceeding To Login");
                        alert.showAndWait();
                        UserService.createOnlineBankingAccount(account,mobile,0);

                        HelloApplication.changeScene("login");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.showAndWait();

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Account Already Exist");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You Have No Account In The Bank");
                alert.showAndWait();
            }
        }
        else
        {
            accountField.setText("");
            mobileField.setText("");
            emailField.setText("");
            passwordField.setText("");
            nameField.setText("");
            nidField.setText("");
            dobPicker.setValue(null);
        }
    }
}
