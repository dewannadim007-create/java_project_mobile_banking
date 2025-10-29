package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class ChangePasswordController {
    @FXML
    private PasswordField confirmNewField,currentPasswordField,newPasswordField;

    @FXML
    void changeToMenu() {
        HelloApplication.changeScene("menu");}

    @FXML
    void submit()
    {
        boolean check = true;

        if (!currentPasswordField.getText().isEmpty() )
        {
            int length = currentPasswordField.getText().length();
            if (length > 8|| length==7|| length==6|| length==5|| length==4|| length==3|| length==2|| length==1 ) {
                check = false;
                currentPasswordField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

            }
        }
        if (!newPasswordField.getText().isEmpty() )
        {
            int length = newPasswordField.getText().length();
            if (length > 8|| length==7|| length==6|| length==5|| length==4|| length==3|| length==2|| length==1 ) {
                check = false;
                newPasswordField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

            }
        }
        if (!currentPasswordField.getText().isEmpty() )
        {
            int length = currentPasswordField.getText().length();
            if (length > 8|| length==7|| length==6|| length==5|| length==4|| length==3|| length==2|| length==1 ) {
                check = false;
                currentPasswordField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

            }
        }
        if(!newPasswordField.getText().equals(confirmNewField.getText())){
            check = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("New Password And Confirm Password Cannot Be Different");
            alert.showAndWait();
        }
        if (check) {

            String newPassword = newPasswordField.getText();
            UserService.changePassword(HelloApplication.loggedUser.getMobile(),newPassword);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Password Changed Successfully");
            alert.showAndWait();
            HelloApplication.changeScene("menu");
        }


    }
}
