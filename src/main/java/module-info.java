module bd.edu.seu.online {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
   requires java.smartcardio;
    requires de.jensd.fx.glyphs.fontawesome;
    requires mysql.connector.j;


    opens bd.edu.seu.online to javafx.fxml;
    exports bd.edu.seu.online;
    exports bd.edu.seu.online.controller;
    opens bd.edu.seu.online.controller to javafx.fxml;
    exports bd.edu.seu.online.singleton;
    opens bd.edu.seu.online.singleton to javafx.fxml;
    exports bd.edu.seu.online.services;
    opens bd.edu.seu.online.services to javafx.fxml;
    exports bd.edu.seu.online.model;
    opens bd.edu.seu.online.model to javafx.fxml;
}