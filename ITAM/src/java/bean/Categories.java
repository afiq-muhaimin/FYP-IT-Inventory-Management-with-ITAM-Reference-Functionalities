/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CategoriesDao;
import java.util.ArrayList;

/**
 *
 * @author Afiq
 */
public class Categories {

    public String userCategoriesID;
    public String userCategoriesDetail;

    public Categories() {

    }

    public Categories(String catid, String catdetail) {
        this.userCategoriesDetail = catdetail;
        this.userCategoriesID = catid;
    }

    public ArrayList getAllCategories() {
        ArrayList usercat = new ArrayList();
        CategoriesDao catdao = new CategoriesDao();
        usercat = catdao.getAll();
        return usercat;
    }

    public String getUserCategoriesID() {
        return userCategoriesID;
    }

    public String getUserCategoriesDetail() {
        return userCategoriesDetail;
    }
    
}
