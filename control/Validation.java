/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MyPC
 */
public class Validation {

    private Pattern p;
    private Matcher m;

    public boolean isValidUsername(String s) {
        if (s.isBlank()) { //Can't be blank
            return false;
        }
        if (s.length() < 6 || s.length() > 16) { //Length of password can't  less than 6 or get over 16 characters
            return false;
        }
        p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE); //Check the username contain special character or not
        m = p.matcher(s);
        return !m.find();
    }

    public boolean isValidPassword(String s) {
        if (s.isBlank()) { //Can't be blank
            return false;
        }
        if (s.length() > 16) { //Length of password can't get over 16 characters
            return false;
        }
        boolean b1, b2, b3;
        p = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]");  //At least 1 capital letter
        m = p.matcher(s);
        b1 = m.find();
        p = Pattern.compile("[0123456789]"); //At least 1 digital letter
        m = p.matcher(s);
        b2 = m.find();
        p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE); //At least 1 special character
        m = p.matcher(s);
        b3 = m.find();
        return b1 && b2 && b3;
    }

    public boolean isValidFullName(String s) {
        if (s.isBlank()) { //Can't be blank
            return false;
        }
        p = Pattern.compile("[^a-zA-Z ]"); //Check the fullName contain digital/special character or not
        m = p.matcher(s);
        return !m.find();
    }

    public boolean isValidEmail(String s) {
        if (s.isBlank()) { //Can't be blank
            return false;
        }
        if (s.length() > 16) { //Length of password can't get over 16 characters
            return false;
        }
        p = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"); //At least 1 capital letter
        m = p.matcher(s);
        return m.find();
    }

}
