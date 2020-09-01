/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author wtonf
 * @param <E>
 * @param <D>
 */
public abstract class CrudBean<E, D extends CrudDAO> {
    private String estadoDaTela = "buscar";
    
    private E entidade;
    private List<E> entidades;
    
    public void novo(){
        entidade = CriarNovaEntidade();
        mudarparaInserir();
    }
    public void salvar(){
        try {
            getDao().salvar(entidade);
            entidade = CriarNovaEntidade();
            adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_ERROR);
            mudarparaBuscar();
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editar(E entidade){
        this.entidade = entidade;
        mudarparaEditar();
    }
    public void deletar(E entidade){
        try {
            getDao().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Apagado com sucesso", FacesMessage.SEVERITY_ERROR);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Não foi possivel apagar", FacesMessage.SEVERITY_ERROR);
        }
      
    }
    public void buscar(){
        if(isBusca() == false){
            mudarparaBuscar();
            return;
        }
        try {
            entidades = getDao().buscar();
            if(entidades == null || entidades.size()<1){
                adicionarMensagem("Não há nada cadastrado!", FacesMessage.SEVERITY_ERROR);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro){
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }



    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }

  
    public abstract D getDao();
    public abstract E CriarNovaEntidade();
    
    
    public boolean isInseri(){
        return "inserir".equals(estadoDaTela);
    }
    public boolean isEdita(){
        return "editar".equals(estadoDaTela);
    }
    public boolean isBusca(){
        return "buscar".equals(estadoDaTela);
    }
    public void mudarparaInserir(){
        estadoDaTela = "inserir";
    }
    public void mudarparaEditar(){
        estadoDaTela = "editar";
    }
    public void mudarparaBuscar(){
        estadoDaTela = "buscar";
    }
}
