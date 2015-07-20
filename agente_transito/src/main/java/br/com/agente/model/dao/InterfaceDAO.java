package br.com.agente.model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface InterfaceDAO<T> {
    //crud
    //DAO generico para todas as entidades
    // ctrl + shift + home
    // ctrl + shift + setinha para baixo >> para copiar
    // ctrl + shift + i  >>  para importar bibliotecas
    void save (T entity);
    void update (T entity);
    void remove (T entity);
    void merge (T entity);
        
    T getEntity(Serializable id); //recupera um objeto pelo id
    T getEntityByDetachedCriteria(DetachedCriteria criteria); //
    T getEntityByHQLQuery(String stringQuery);
    List<T> getEntities(); // pega todas as entidades
    List<T> getListByDetachedCriteria(DetachedCriteria criteria);   
    
}
