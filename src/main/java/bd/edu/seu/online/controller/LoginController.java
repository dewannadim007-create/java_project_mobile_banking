package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.User;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField mobileField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void changeToRegistration() {
        HelloApplication.changeScene("registration");
    }

    @FXML
    public void login()
    {
        String mobile = mobileField.getText();
        String password = passwordField.getText();
        UserService userService = new UserService();
        User user = userService.login(mobile,password);
        HelloApplication.loggedUser=user;
        if(user!= null)
          {    HelloApplication.changeScene("home");}
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.showAndWait();

        }

    }
}
