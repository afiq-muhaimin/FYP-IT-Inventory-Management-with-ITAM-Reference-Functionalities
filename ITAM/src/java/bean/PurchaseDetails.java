/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import dao.PurchaseDetailDao;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Afiq
 */
public class PurchaseDetails {
    private String purchaseID;
    private String companysupplierID;
    private String invoiceNumber;
    private String PONumber;
    Date datePurchase = new Date();
    private String assetID;
    private int purchaseqty;
    
    public PurchaseDetails(String purchaseID, String companysupplierID, String invoiceNumber, String PONumber,Date datePurchase ,String assetID, int purchaseqty) {
        this.purchaseID = purchaseID;
        this.companysupplierID = companysupplierID;
        this.invoiceNumber = invoiceNumber;
        this.PONumber = PONumber;
        this.assetID = assetID;
        this.datePurchase = datePurchase;
        this.purchaseqty = purchaseqty;
    }
    
    public PurchaseDetails()
    {}
    
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getPONumber() {
        return PONumber;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public String getPurchaseID() {
        return purchaseID;
    }

    public String getCompanysupplierID() {
        return companysupplierID;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public void setCompanysupplierID(String companysupplierID) {
        this.companysupplierID = companysupplierID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public void setPurchaseqty(int purchaseqty) {
        this.purchaseqty = purchaseqty;
    }

    public int getPurchaseqty() {
        return purchaseqty;
    }
    
    public ArrayList getAll()
    {
        PurchaseDetailDao pdetaildao = new PurchaseDetailDao();
        ArrayList pdetails = pdetaildao.getAll();
        return pdetails;
    }
    
    public int getColumnCount()
    {
        return getClass().getDeclaredFields().length;
    }
    
    public PurchaseDetails getPdetail(String purchaseid)
    {
        PurchaseDetailDao pdetaildao = new PurchaseDetailDao();
        PurchaseDetails pdetail = new PurchaseDetails();
        pdetail = pdetaildao.getPdetail(purchaseid);
        return pdetail;
    }
    
    public ArrayList getPurchaseDetails(String ponumber, String invnumber)
    {
        PurchaseDetailDao pdetaildao = new PurchaseDetailDao();
        ArrayList pdetails = pdetaildao.getAllPdetail(ponumber,invnumber);
        return pdetails;
    }
    
    public String updatePurchaseDetail(PurchaseDetails pdetail)
    {
        PurchaseDetailDao pdetaildao = new PurchaseDetailDao();
        String result = pdetaildao.updateDetails(pdetail);
        return result;
    }

    @Override
    public String toString() {
        return "PurchaseDetails{" + "purchaseID=" + purchaseID + ", companysupplierID=" + companysupplierID + ", invoiceNumber=" + invoiceNumber + ", PONumber=" + PONumber + ", datePurchase=" + datePurchase + ", assetID=" + assetID + ", purchaseqty=" + purchaseqty + '}';
    }
    
}
