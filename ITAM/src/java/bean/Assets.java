/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.opencsv.CSVWriter;
import dao.AssetDao;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class Assets {
    private String assetID;
    private String assetName;
    private String assetRemarks;
    private int assetOriginQty;
    private String typeID;
    private String assetImage;

    public Assets(String assetID, String assetName, String assetRemarks, int assetOriginQty,String typeID) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetOriginQty = assetOriginQty;
        this.assetRemarks = assetRemarks;
        this.assetOriginQty = assetOriginQty;
        this.typeID = typeID;
    }
    
    public Assets(String assetID, String assetName, String assetRemarks, int assetOriginQty, String typeID, String assetimage) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetOriginQty = assetOriginQty;
        this.assetRemarks = assetRemarks;
        this.assetOriginQty = assetOriginQty;
        this.typeID = typeID;
        this.assetImage = assetimage;
    }
    
    public Assets()
    {
        
    }
    

    public String getAssetID() {
        return assetID;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getAssetRemarks() {
        return assetRemarks;
    }

    public int getAssetOriginQty() {
        return assetOriginQty;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setAssetRemarks(String assetRemarks) {
        this.assetRemarks = assetRemarks;
    }

    public void setAssetOriginQty(int assetOriginQty) {
        this.assetOriginQty = assetOriginQty;
    }

    public String getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(String assetImage) {
        this.assetImage = assetImage;
    }
    
    public ArrayList getAllAsset()
    {
        ArrayList assets = new ArrayList();
        AssetDao astdao = new AssetDao();
        assets = astdao.getAll();
        return assets;
    }

    public String getTypeID() {
        return typeID;
    }
    
    public String getTypeID(String assetID) {
        AssetDao astdao = new AssetDao();
        String typeID = astdao.getTypeID(assetID);
        return typeID;
    }
    
    public Assets getAsset(String assetid)
    {
        Assets asset = new Assets ();
        AssetDao assetdao = new AssetDao();
        
        asset = assetdao.getAsset(assetid);
        return asset;
    }

    public String updateAssetQty(String oldasset, int oldqty,int newqty) {
        AssetDao astdao = new AssetDao();
        String result = astdao.updateAssetQty(oldasset,oldqty,newqty);
        return result;
    }
    
    public String updateAssetQty2(String newasset,String oldasset, int oldqty, int newqty)
    {
        AssetDao astdao = new AssetDao();
        String result = astdao.updateAssetQty2(newasset,oldasset,oldqty,newqty);
        return result;
    }

    public String updateAssetQty3(String oldassetid, int oldassetqty) {
        AssetDao astdao = new AssetDao();
        String result = astdao.updateAssetQty3(oldassetid,oldassetqty);
        return result;
    }
    
    public String updateAssetDetail(Assets asset)
    {
        AssetDao astdao = new AssetDao();
        String result = astdao.updateAssetDetail(asset);
        return result;
    }

    public String[] assetToCSVData(Assets asset)
    {
        String[] data = new String[6];
        data[0] = asset.assetID;
        data[1] = asset.assetName;
        data[2] = asset.assetRemarks;
        data[3] = String.valueOf(asset.assetOriginQty);
        return data;
    }
    
    public int AssetMax()
    {
        AssetDao astdao = new AssetDao();
        return astdao.findMax();
    }
    
    public int AssetMin()
    {
        AssetDao astdao = new AssetDao();
        return astdao.findMin();
    }
    
    public double AssetAvg()
    {
        AssetDao astdao = new AssetDao();
        return astdao.findAvg();
    }

    public String updateImage(String assetid, String assetimage) {
        AssetDao astdao = new AssetDao();
        String result = astdao.insertimage(assetid,assetimage);
        return result;
    }
    
    
}
