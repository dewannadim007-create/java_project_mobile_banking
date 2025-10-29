package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;

public class MenuController {
    public void changeToHome()
    {
        HelloApplication.changeScene("home");
    }
    public void   changeToLogin()
    {
        HelloApplication.changeScene("login");
    }
    public void changeToTransactionProfile()
    {
        HelloApplication.changeScene("transactionProfile");
    }
    public void changeToPassword()
    {
        HelloApplication.changeScene("changePassword");
    }
    public void changeToUserProfile()
    {
        HelloApplication.changeScene("userProfile");
    }

}
