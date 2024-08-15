/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.AssetLogsDao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Afiq
 */
public class AssetLogs {
    private String assetsLogsID;
    private String assetID;
    private String userID;
    Date dateupdate = new Date();
    private int quantityInvolve;
    private String remarks; 
    private String updateOperation;
    private String requestID;

    public AssetLogs()
    {
        
    }
    
    public AssetLogs(String assetsLogsID, String assetID, String userID, Date dateUpdate ,int quantityInvolve, String remarks, String updateOperation, String requestID) {
        this.assetsLogsID = assetsLogsID;
        this.assetID = assetID;
        this.userID = userID;
        this.quantityInvolve = quantityInvolve;
        this.remarks = remarks;
        this.updateOperation = updateOperation;
        this.requestID = requestID;
        this.dateupdate = dateUpdate;
    }

    public String getAssetsLogsID() {
        return assetsLogsID;
    }

    public String getAssetID() {
        return assetID;
    }

    public String getUserID() {
        return userID;
    }

    public Date getDateupdate() {
        return dateupdate;
    }

    public int getQuantityInvolve() {
        return quantityInvolve;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getUpdateOperation() {
        return updateOperation;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setAssetsLogsID(String assetsLogsID) {
        this.assetsLogsID = assetsLogsID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDateupdate(Date dateupdate) {
        this.dateupdate = dateupdate;
    }

    public void setQuantityInvolve(int quantityInvolve) {
        this.quantityInvolve = quantityInvolve;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUpdateOperation(String updateOperation) {
        this.updateOperation = updateOperation;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
    
    public ArrayList getAllAssetLogs()
    {
        ArrayList assetlogs = new ArrayList();
        AssetLogsDao astlogsdao = new AssetLogsDao();
        assetlogs = astlogsdao.getAll();
        return assetlogs;
    }
    
    public String addAssetMovement(AssetLogs astlogs)
    {
        String result;
        AssetLogsDao astlogdao = new AssetLogsDao();
        result = astlogdao.add(astlogs);
        return result;
        
    }
    
    public String addAssetLogs(AssetLogs astlogs)
    {
        String result;
        AssetLogsDao astlogdao = new AssetLogsDao();
        result = astlogdao.updateLogs(astlogs);
        return result;
    }
    
    public ArrayList getAssetMovement(String mvtoperation,String assetid)
    {
        ArrayList listastmovement = new ArrayList();
        AssetLogsDao astlogdao = new AssetLogsDao();
        listastmovement = astlogdao.find(mvtoperation,assetid);
        return listastmovement;
    }
    
    public AssetLogs findAssetLogs(String astlogid)
    {
        AssetLogs astlog = new AssetLogs();
        AssetLogsDao astlogdao = new AssetLogsDao();
        astlog = astlogdao.find(astlogid);
        return astlog;
    }
    
    public int AssetLogMax()
    {
        AssetLogsDao astlogdao = new AssetLogsDao();
        int maxvalue = astlogdao.findMax();
        return maxvalue;
    }
    
    public int AssetLogMin()
    {
        AssetLogsDao astlogdao = new AssetLogsDao();
        int minvalue = astlogdao.findMin();
        return minvalue;
    }
    
    public double AssetLogAvg()
    {
        AssetLogsDao astlogdao = new AssetLogsDao();
        double avgvalue = astlogdao.findAvg();
        return avgvalue;
    }

    public String[] AssetLogToCSVData(AssetLogs log) 
    {
       String[] data = new String [8];
       data[0] = log.getAssetsLogsID();
       data[1] = log.getAssetID();
       data[2] = log.getUserID();
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
       data[3] = formatter.format(log.getDateupdate());
       data[4] = Integer.toString(log.getQuantityInvolve());
       data[5] = log.getRemarks();
       data[6] = log.getUpdateOperation();
       data[7] = log.getRequestID();
       return data;
    }
}
