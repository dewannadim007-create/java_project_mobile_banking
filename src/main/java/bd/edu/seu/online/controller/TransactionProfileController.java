package bd.edu.seu.online.controller;

import bd.edu.seu.online.HelloApplication;
import bd.edu.seu.online.model.Transaction;
import bd.edu.seu.online.services.TransactionServices;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TransactionProfileController implements Initializable {
    @FXML
    private TableColumn<Transaction, Number> amountColumn;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> receiverColumn;

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, String> typeColumn;
    @FXML
    private ChoiceBox<String> filterTypeChoice;
    @FXML
    private Label monthlyExpenseLabel;



    private ObservableList<Transaction> transactionsObservableList;

    public void changeToMenu()
    {
        HelloApplication.changeScene("menu");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        receiverColumn.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getReceiver()));
        amountColumn.setCellValueFactory(c-> new SimpleDoubleProperty(c.getValue().getAmount() ));
        typeColumn.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getType()));
        dateColumn.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getDate()));

        transactionsObservableList = FXCollections.observableArrayList();

        TransactionServices transactionServices =  new TransactionServices();
        List<Transaction> transactionList =transactionServices.getTransactionList(HelloApplication.loggedUser.getAccount());

        transactionsObservableList.addAll(transactionList);
        transactionTable.setItems(transactionsObservableList);
        transactionServices.getTransactionList(HelloApplication.loggedUser.getAccount());

        ObservableList<String> observableList=FXCollections.observableArrayList();
        observableList.add("utility");
        observableList.add("bank to bank");
        observableList.add("bank to wallet");
        observableList.add("wallet to wallet");
        observableList.add("wallet to bank");
        observableList.add("add to wallet");
        observableList.add("recharge");
        filterTypeChoice.setItems(observableList);
monthlyExpenseLabel.setText("");
    }
    public void filter(){
      String filterBy = filterTypeChoice.getValue().toLowerCase();
            List<Transaction> transactionList = TransactionServices.getTransactionList(HelloApplication.loggedUser.getAccount());

            List<Transaction> filteredList = transactionList.stream().filter(t -> t.getType().contains(filterBy)).toList();
            transactionsObservableList.clear();
            transactionsObservableList.addAll(filteredList);

    }
    public void reset()
    {
        List<Transaction>transactionList=TransactionServices.getTransactionList(HelloApplication.loggedUser.getAccount());
        transactionsObservableList.clear();
        transactionsObservableList.addAll(transactionList);
    }

}
