/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.RequestedAssetDao;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class RequestedAsset {

    private String requestedID;
    private String requestID;
    private String assetID;
    private int quantity;

    public RequestedAsset()
    {
        
    }
    
    public RequestedAsset(String requestedID, String requestID, String assetID, int quantity) {
        this.requestedID = requestedID;
        this.requestID = requestID;
        this.assetID = assetID;
        this.quantity = quantity;
    }

    public String getRequestedID() {
        return requestedID;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getAssetID() {
        return assetID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setRequestedID(String requestedID) {
        this.requestedID = requestedID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public ArrayList getAllRequested()
    {
        ArrayList<RequestedAsset> listrequested = new ArrayList();
        RequestedAssetDao rqtast = new RequestedAssetDao();
        listrequested = rqtast.getAll();
        return listrequested;
    }
    
    public ArrayList getRequestedAsset(String assetid) {
       ArrayList listreqast = new ArrayList();
       RequestedAssetDao reqastdao = new RequestedAssetDao();
       listreqast = reqastdao.getRequestedAsset(assetid);
       return listreqast;
    }
    
    public int getSumQuantity(String assetid)
    {
       RequestedAssetDao reqastdao = new RequestedAssetDao();
       int sumquantity = reqastdao.getSumQuantityReqAst(assetid);
       return sumquantity;
    }

    public String addRequestedAsset(RequestedAsset reqast) {
        RequestedAssetDao reqastdao = new RequestedAssetDao();
        String result = reqastdao.add(reqast);
        return result;
    }
    
    public ArrayList findRequestedAsset(String requestid)
    {
       ArrayList listreqast = new ArrayList();
       RequestedAssetDao reqastdao = new RequestedAssetDao();
       listreqast = reqastdao.find(requestid);
       return listreqast;
    }
}
