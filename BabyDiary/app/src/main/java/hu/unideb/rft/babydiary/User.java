package hu.unideb.rft.babydiary;

/**
 * Created by Vozarpet on 2018. 11. 09..
 * User Táblát leíró objektum
 * !!! SEX nem része, ha tárolnikell bővül. !!!
 * ToString nincs felül definiálva.
 */

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String userrole;
    private String firstname;
    private String lastname;
    private String sex;

   public User(){

   }

    public User(int id, String username, String password, String email, String userrole, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userrole = userrole;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    //sexet is tartalmazó konstruktor és getter setter
    /*
    public User(int id, String username, String password, String email, String userrole, String firstname, String lastname, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userrole = userrole;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
