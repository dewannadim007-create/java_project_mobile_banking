package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.services.TransactionServices;
import bd.edu.seu.online.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label balanceLabel,greetingsLabel;

    @FXML
    private Label nameLabel;

    @FXML
    public  void changeToMenu()
    {
        HelloApplication.changeScene("menu");
    }
    public void changeToUtility(){HelloApplication.changeScene("utility");}
    public void changeToSendMoney(){HelloApplication.changeScene("sendMoney");}
    public void changeToEBanking(){HelloApplication.changeScene("eBanking");}
    public void changeToCheckBook(){HelloApplication.changeScene("checkBook");}
    public void changeToStatement(){HelloApplication.changeScene("statement");}

    public void checkWallet(){
        double checkBalance = UserService.getBalanceOnline(HelloApplication.loggedUser.getMobile());
        balanceLabel.setText("");
        balanceLabel.setText(" ৳"+checkBalance);}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] info = UserService.userInfo(HelloApplication.loggedUser.getMobile());
        if(info!= null) {
        nameLabel.setText(info[0].toUpperCase());
        }

        int hour = LocalTime.now().getHour();
        if (hour >= 5 && hour < 12) {
           greetingsLabel.setText("Good Morning!");
        } else if (hour >= 12 && hour < 19) {
            greetingsLabel.setText("Good Afternoon!");
        } else if (hour >= 19 && hour < 20) {
                greetingsLabel.setText("Good Evening!");
        } else {
            greetingsLabel.setText("Good Night!");
        }
    }

}
