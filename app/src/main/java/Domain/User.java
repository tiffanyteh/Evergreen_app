package Domain;

public class User {
    private String Name;
    private String email;
    private String phoneno;
    private String address;
    private String pic;

    public User(){

    }

    public User(String name, String email, String phoneno, String address, String pic) {
        Name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.address = address;
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        Name = name;
    }

}
