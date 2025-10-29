package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import javafx.fxml.FXML;

public class UtilityController {
    @FXML
    public  void changeToHome()
    {
        HelloApplication.changeScene("home");
    }
    public void changeToGas(){HelloApplication.changeScene("gas");}
    public void changeToPayment(){HelloApplication.changeScene("payment");}
    public void changeToRecharge(){HelloApplication.changeScene("recharge");}
}
