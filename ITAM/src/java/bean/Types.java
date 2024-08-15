/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.TypesDao;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class Types {
    private String typesID; 
    private String typesDetails;

    public Types()
    {
        
    }
    public Types(String typesID, String typesDetails) {
        this.typesID = typesID;
        this.typesDetails = typesDetails;
    }

    public String getTypesID() {
        return typesID;
    }

    public String getTypesDetails() {
        return typesDetails;
    }

    public void setTypesID(String typesID) {
        this.typesID = typesID;
    }

    public void setTypesDetails(String typesDetails) {
        this.typesDetails = typesDetails;
    }
    
    public ArrayList getAllTypes()
    {
        ArrayList types = new ArrayList();
        TypesDao typDao = new TypesDao();
        types = typDao.getAll();
        return types;
    }
}
