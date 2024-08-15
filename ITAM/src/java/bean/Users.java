/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;
import dao.UsersDao;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class Users {
    private String userID;
    private String userName;  
    private String userPassword;
    private String userEmail;
    private String userFullName;
    private String userPhoneNum;
    private String serviceID;
    private String userCategories;

    public Users(String userID, String userName, String userPassword, String userEmail, String userFullName, String userPhoneNum, String serviceID, String userCategories) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userFullName = userFullName;
        this.userPhoneNum = userPhoneNum;
        this.serviceID = serviceID;
        this.userCategories = userCategories;
    }
    
    
    
    public Users()
    {
        
    }
    
    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }
    
    public Users getUsers(String userid)
    {
        Users user = new Users();
        UsersDao usrdao = new UsersDao();
        user = usrdao.getUsers(userid);
        return user;
    }
    
     public ArrayList getAllUsers()
    {
        ArrayList userslist = new ArrayList();
        UsersDao usrdao = new UsersDao();
        userslist = usrdao.getAll();
        return userslist;
        
    }

    @Override
    public String toString() {
        return "Users{" + "userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail=" + userEmail + ", userFullName=" + userFullName + ", userPhoneNum=" + userPhoneNum + '}';
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(String userCategories) {
        this.userCategories = userCategories;
    }
    
    public String userUpdate(Users user)
    {
        UsersDao usrdao = new UsersDao();
        String resultupdate = usrdao.userUpdate(user);
        String resultverify = usrdao.userVerifyEmail(user);
        if(resultupdate.equals("SUCCESS") && resultverify.equals("SUCCESS"))
        {
            return resultupdate;
        }
       return "FAILED";
    }
    
    public String userUpdatePass(String userid, String newpass)
    {
        UsersDao usrdao = new UsersDao();
        String resultupdatepass = usrdao.updatePass(userid,newpass);
        if(resultupdatepass.equals("SUCCESS"))
        {
            return resultupdatepass;
        }
        return "FAILED";
    }

    public String userUpdateCat(String userid, String catid) {
        UsersDao usrdao = new UsersDao();
        String resultupdatecat = usrdao.updateCat(userid,catid);
        if(resultupdatecat.equals("SUCCESS"))
        {
            return resultupdatecat;
        }
        return "FAILED";
    }

    public String getUserFullName(String userid) {
        UsersDao usrdao = new UsersDao();
        String fullname = usrdao.find(userid);
        return fullname;
    }
    
    public ArrayList checkUpdateDiff(String userID,String userretName,String userretPassword, String userretEmail, String userretFullName, String usrretPhoneNum) 
    {
        Users user = getUsers(userID);
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        String userEmail = user.getUserEmail();
        String userFullName = user.getUserFullName();
        String userPhoneNum = user.getUserPhoneNum();
        
        ArrayList <String> listchange = new ArrayList();
        
        if(!userName.equals(userretName))
        {
            listchange.add("Username");
        }
        if(!userPassword.equals(userretPassword))
        {
            listchange.add("Password");
        }
        if(!userEmail.equals(userretEmail))
        {
            listchange.add("Email");
        }
        if(!userFullName.equals(userretFullName))
        {
            listchange.add("User Full Name");
        }
        if (!userPhoneNum.equals(usrretPhoneNum)) 
        {
            listchange.add("Phone Number");
        }
        
        return listchange;
    }
    
    
    
}
