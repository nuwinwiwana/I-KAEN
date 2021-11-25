package com.example.ikaen;

public class person {

    // Variable to store data corresponding
    // to lastname keyword in database
    private String email;
    // Variable to store data corresponding
    // to firstname keyword in database
    private String fullname;

    // Variable to store data corresponding
    // to age keyword in database
    private String password;

    // Variable to store data corresponding
    // to age keyword in database
    private String phone;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public person() {}

    // Getter and setter method

    public String getemail()
    {
        return email;
    }
    public void setemail(String email)
    {
        this.email = email;
    }
    public String getfullname()
    {
        return fullname;
    }
    public void setfullname(String fullname)
    {
        this.fullname = fullname;
    }
    public String getpassword()
    {
        return password;
    }
    public void setpassword(String password)
    {
        this.password = password;
    }
    public String getphone()
    {
        return phone;
    }
    public void setphone(String phone)
    {
        this.phone = phone;
    }
}
