package bd.edu.seu.online.model;

public class Transaction {
    private String receiver;
    private String sender;
    private String date;
    private double amount;
    private String type;

public Transaction()
{

}

    public Transaction(String receiver, String date, double amount, String type, String sender) {
        this.receiver = receiver;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.sender= sender;
    }



    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
