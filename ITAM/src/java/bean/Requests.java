/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.RequestDao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Afiq
 */
public class Requests {
    private String requestID;
    private String userID;
    Date dateTaken = new Date();
    Date dateReturn = new Date();
    Date dateApprove = new Date();
    Date dateRequest = new Date();
    private String requestStatus;
    private String requesterID;
    
    
    public Requests()
    {
        
    }

    public Requests(String requestID, String userID, Date dateTaken, Date dateReturn,Date dateApprove,Date dateRequest ,String requestStatus, String requesterID) {
        this.requestID = requestID;
        this.userID = userID;
        this.requestStatus = requestStatus;
        this.requesterID = requesterID;
        this.dateTaken = dateTaken;
        this.dateReturn = dateReturn;
        this.dateApprove = dateApprove;
        this.dateRequest = dateRequest;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getUserID() {
        return userID;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public Date getDateApprove() {
        return dateApprove;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public String getRequesterID() {
        return requesterID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public void setDateApprove(Date dateApprove) {
        this.dateApprove = dateApprove;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
        
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
    }
    
    
    
    public ArrayList getAllRequests()
    {
        ArrayList listrequests = new ArrayList();
        RequestDao req = new RequestDao();
        listrequests = req.getAll();
        return listrequests;
    }
    
    public String checkRequest()
    {
        RequestDao rqtdao = new RequestDao();
        String valuecheck = rqtdao.check();
        return valuecheck;
    }

    public Date getDateTaken(String reqid) {
        System.out.println("in bean date taken");
        RequestDao rqtdao = new RequestDao();
        Date datetaken = rqtdao.getDateTaken(reqid);
        return datetaken;
    }
    
     public Date getDateReturn(String reqid) {
        System.out.println("in bean date return");
        RequestDao rqtdao = new RequestDao();
        Date datereturn = rqtdao.getDateReturn(reqid);
        return datereturn;
    }

    public String addRequest(Requests req) {
        RequestDao rqtdao = new RequestDao();
        String result = rqtdao.add(req);
        return result;
    }

    public String updateRequestStatus(String newstatus, String requestid) {
        RequestDao rqtdao = new RequestDao();
        String result = rqtdao.updateStatus(newstatus,requestid);
        return result;
    }
    
    public ArrayList findRequest(String requesterid)
    {
        ArrayList listrequest = new ArrayList();
        RequestDao rqtdao = new RequestDao();
        listrequest = rqtdao.find(requesterid);
        return listrequest;
    }

    public String[] RequestToCSVData(Requests req) {
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
       String[] data = new String [11];
       data[0] = req.getRequestID();
       data[1] = req.getUserID();
       data[2] = formatter.format(req.getDateTaken());
       data[3] = formatter.format(req.getDateReturn());
       data[4] = formatter.format(req.getDateApprove());
       data[5] = formatter.format(req.getDateRequest());
       data[6] = req.getRequestStatus();
       data[7] = req.getRequesterID();
       
      return data;
    }
    
    public List<Integer> requestAvgDay()
    {
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findAvgDay();
    }
    
    public int RequestQtyMax()
    { 
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findMaxAssetPerRequest();
    }
    
    public int RequestQtyMin()
    {
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findMinAssetPerRequest();
    }
    
    public int RequestByUserMin()
    {
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findMinRequestbyUser();
    }
    
    public int RequestByUserMax()
    {
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findMaxRequestbyUser();
    }
    
    public List<Integer> TotalAssetPerRequest()
    {
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findTotalAssetPerRequest();
    }
    
    public String assetRequestedMax()
    {
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findAssetPerRequestMax();
    }
    
    public String assetRequestedMin()
    {
        RequestDao rqtdao = new RequestDao();
        return rqtdao.findAssetPerRequestMin();
    }
    
    
    
    
    
    
    
    
    
    
    
}
