/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ServicesDao;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class Services {
    private String servicesID;
    private String servicesName;
    private String departmentID;

    public Services(String servicesID, String servicesName, String departmentID) {
        this.servicesID = servicesID;
        this.servicesName = servicesName;
        this.departmentID = departmentID;
    }

    public Services() {
        
    }

    public String getServicesID() {
        return servicesID;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesID(String servicesID) {
        this.servicesID = servicesID;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }
    
    public ArrayList getAllServices()
    {
        ArrayList service = new ArrayList();
        ServicesDao servdao = new ServicesDao();
        service = servdao.getAll();
        System.out.println("Size in bean "+service.size());
        return service;
    }
}
