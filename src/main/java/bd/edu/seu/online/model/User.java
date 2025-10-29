package bd.edu.seu.online.model;

public class User {
   private String name;
    private String mobile;
    private String email;
    private String password;
    private String DOB;
    private String account;
    private String nid;
    private String image;



    public User(String name, String mobile, String email, String DOB, String account, String nid) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.DOB = DOB;
        this.account = account;
        this.nid = nid;
    }

public User(String name, String mobile, String email, String password, String DOB, String account, String nid) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
        this.account = account;
        this.nid = nid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
}

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
