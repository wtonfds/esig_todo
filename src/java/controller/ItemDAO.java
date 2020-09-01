/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Item;



/**
 *
 * @author wtonf
 */
public class ItemDAO implements CrudDAO<Item>{
    
    @PersistenceContext
    
    
    
    @Override
    public void salvar( Item item ){
        
        if(item.getId() == null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();        
        }else{
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        }
    }
    
    
    @Override
    public List<Item> buscar(){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT p FROM Item p ORDER BY P.nome DESC");
        return (List<Item>)q.getResultList();
    }

    @Override
    public void deletar(Item item) {
       
        if(item != null){
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery("DELETE item  FROM Item WHERE id = "+ item.getId());
            q.executeUpdate();
            em.getTransaction().commit();        
        }else{
            JOptionPane.showConfirmDialog(null, "Erro ao deletar item");
        }
       
    }
    
    private EntityManager getEntityManager(){
        EntityManagerFactory  em = Persistence.createEntityManagerFactory("desafio_esigPU");
        return em.createEntityManager();
    }
}
