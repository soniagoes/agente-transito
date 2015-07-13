package br.com.agente.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

public class PhaseListenerAgente implements PhaseListener{
/*
 Antigamente a forma mais comum para se criar uma forma de verificar 
    a autorização das requisições feitas para determinado recurso de 
    uma aplicação web era utilizando um filtro (Filter). Porém em Java 
    Server Faces (JSF), podemos realizar esta tarefa de uma outra forma 
    mais integrada, utilizando a interface PhaseListener.
    
    Esta interface disponibiliza 3 métodos:
    1 void afterPhase(PhaseEvent event);
    2 void beforePhase(PhaseEvent event);
    3 PhaseId getPhaseId();
    
    A idéia principal do listener será verificar se existe um atributo de 
    sessão chamado "currentUser" que será uma instância de um objeto User e 
    este representará o usuário logado na aplicação. Caso este atributo exista, 
    o listener deixa o ciclo da pagina seguir, mas caso o atributo não exista o 
    ciclo será interrompido e redirecionado para a página de login.
*/
   //antes da fase
   @Override
    public void beforePhase(PhaseEvent fase) {
        if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW));
        System.out.println("Antes da Fase: " + getPhaseId());
        Session session = HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        FacesContextUtil.setRequestSession(session);
        
    }
    //depois da fase
    @Override
    public void afterPhase(PhaseEvent fase) {
        System.out.println("Depois da Fase: " + getPhaseId());
        if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            Session session = FacesContextUtil.getRequestSession();
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction().isActive()){
                    session.getTransaction().rollback(); //desfaz tudo o que foi feito
                }
                
            }finally{
                session.close();
            }
            
            
        }
    }
   @Override
    public PhaseId getPhaseId() {
      return PhaseId.ANY_PHASE;
    }
    
    
}
