/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CompanySupplierDao;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class CompanySupplier {
    private String companySupplierID;
    private String companySupplierName;
    
    public CompanySupplier()
    {
        
    }

    public CompanySupplier(String companySupplierID, String companySupplierName) {
        this.companySupplierID = companySupplierID;
        this.companySupplierName = companySupplierName;
    }

    public String getCompanySupplierID() {
        return companySupplierID;
    }

    public String getCompanySupplierName() {
        return companySupplierName;
    }

    public void setCompanySupplierID(String companySupplierID) {
        this.companySupplierID = companySupplierID;
    }

    public void setCompanySupplierName(String companySupplierName) {
        this.companySupplierName = companySupplierName;
    }
    
    public ArrayList getAllCompanySupplier()
    {
        ArrayList companies = new ArrayList();
        CompanySupplierDao cmpDao = new CompanySupplierDao();
        companies = cmpDao.getAll();
        return companies;
    }
    
    
}
