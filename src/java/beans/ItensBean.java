/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.CrudBean;
import controller.CrudDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Item;
import controller.ItemDAO;

/**
 *
 * @author wtonf
 */

@SessionScoped
@ManagedBean
public class ItensBean extends CrudBean<Item, ItemDAO>{

    private ItemDAO itemDao;
    
    @Override
    public ItemDAO getDao() {
        
        if(itemDao == null){
            itemDao = new ItemDAO();
        }
        return itemDao;
    }

    @Override
    public Item CriarNovaEntidade() {
        return new Item();
   }

    
    
}
