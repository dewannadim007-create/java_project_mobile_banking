package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import javafx.fxml.FXML;

public class SendMoneyController {
    @FXML
    public void changeToBB( ) {
        HelloApplication.changeScene("bb");
    }

    @FXML
    public void changeToBW() {
        HelloApplication.changeScene("bW");
    }

    @FXML
    public void changeToWB() {
        HelloApplication.changeScene("wB");
    }

    @FXML
    public void changeToWW() {
        HelloApplication.changeScene("wW");
    }
    @FXML
    public void changeToHome() {
        HelloApplication.changeScene("home");
    }
    @FXML
    public void changeToLogin() {
        HelloApplication.changeScene("login");
    }
}
