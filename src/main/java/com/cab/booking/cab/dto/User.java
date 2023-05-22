package com.cab.booking.cab.dto;
import java.lang.reflect.Field;

public class User {
    private String username;
    private String email;
    private String contactNo;
    private String password;



    public User() {
    }

    public User(String username, String email, String contactNo, String password) {
        this.username = username;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNull() {
        Field fields[] = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                Object value = f.get(this);
                if (value != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        return true;

    }
//    public String getValidate() {
//        return validate;
//    }
//
//    public void setValidate(String validate) {
//        this.validate = validate;
//    }
}
