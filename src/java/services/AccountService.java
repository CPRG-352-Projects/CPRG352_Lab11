package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
                GmailService.sendMail(email, "Notes App Login", "A login has been made to your notes app account.", false);

                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public boolean forgotPassword(String email, String path) {
        boolean userExist = false;
        
        UserDB userDB = new UserDB();
        try {
            User user = userDB.get(email);
            if(user != null) {
//              GmailService.sendMail(email, "Notes App Forgot Password", "Password to your notes app account.", false);

                String to = user.getEmail();
                String subject = "Notes App Password";
                String template = path + "/emailtemplates/forgot.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("password", user.getPassword());
                
                GmailService.sendMail(to, subject, template, tags);
                userExist = true;
            } else {
                userExist = false;
            }
        } catch (Exception e) {
        }
        return userExist;
    }
    
}
