package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.User;
import bd.edu.seu.online.services.TransactionServices;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StatementController implements Initializable {
    @FXML
    private ImageView imageView;

    @FXML
    private Label dailyWalletLabel,dailyAccountLabel,monthlyWalletLabel,monthlyAccountLabel,dailyWalletTrLabel,dailyAccountTrLabel,monthlyWalletTrLabel,monthlyAccountTrLabel;

    @FXML
    public void changeToHome() {HelloApplication.changeScene("home");}


    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] statement =TransactionServices.getDailyWalletExpense();
        dailyWalletLabel.setText("/"+statement[0]);
        dailyWalletTrLabel.setText(statement[1]);

        String[] statement2 =TransactionServices.getDailyAccountExpense();
        dailyAccountLabel.setText("/"+statement2[0]);
        dailyAccountTrLabel.setText(statement2[1]);

        String[] statement3 = TransactionServices.getMonthlyWalletExpense(LocalDate.now().getMonth().toString());
        monthlyWalletLabel.setText("/"+statement3[0]);
        monthlyWalletTrLabel.setText(statement3[1]);

        String[] statement4 = TransactionServices.getMonthlyAccountExpense(LocalDate.now().getMonth().toString());
        monthlyAccountLabel.setText("/"+statement4[0]);
        monthlyAccountTrLabel.setText(statement4[1]);
        }


    }







