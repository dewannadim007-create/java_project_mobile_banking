package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.User;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    @FXML private Label bankAccLabel,emailLabel,mobileLabel,nameLabel,nidLabel,dobLabel;

    @FXML  private void changeToMenu()
  {
      HelloApplication.changeScene("menu");
  }
    @FXML  private void   changeToLogin ()
    {
        HelloApplication.changeScene("login");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] info = UserService.userInfo(HelloApplication.loggedUser.getMobile());
        if(info!= null) {
            nameLabel.setText(info[0]);
            nidLabel.setText(info[5]);
            bankAccLabel.setText(info[2]);
            emailLabel.setText(info[3]);
            mobileLabel.setText(info[1]);
            dobLabel.setText(info[4]);
        }
    }
}
