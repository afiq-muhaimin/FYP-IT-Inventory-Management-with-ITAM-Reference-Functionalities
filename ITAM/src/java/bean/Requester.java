/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.RequesterDao;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class Requester {
    private String requesterID;
    private String requesterPhoneNum;
    private String requesterEmail;
    private String requesterName;
    private String servID;

    public Requester(String requesterID, String requesterPhoneNum, String requesterEmail, String requesterName, String servID) {
        this.requesterID = requesterID;
        this.requesterPhoneNum = requesterPhoneNum;
        this.requesterEmail = requesterEmail;
        this.requesterName = requesterName;
        this.servID = servID;
    }
    
    public Requester()
    {
        
    }

    public String getRequesterID() {
        return requesterID;
    }

    public String getRequesterPhoneNum() {
        return requesterPhoneNum;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getServID() {
        return servID;
    }

    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
    }

    public void setRequesterPhoneNum(String requesterPhoneNum) {
        this.requesterPhoneNum = requesterPhoneNum;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public void setServID(String servID) {
        this.servID = servID;
    }
    
    public String addRequester(Requester requester)
    {
        RequesterDao reqterdao = new RequesterDao();
        String result = reqterdao.add(requester);
        return result;
    }
    
    public String findRequester(String email,String phonenum)
    {
        String requesterid; 
        RequesterDao reqterdao = new RequesterDao();
        requesterid = reqterdao.find(email,phonenum);
        return requesterid;
    }
    
    public ArrayList getAllRequester()
    {
        ArrayList listrequester = new ArrayList();
        RequesterDao reqterdao = new RequesterDao();
        listrequester = reqterdao.getAll();
        return listrequester;
        
    }
    
}
