/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.util;

//import javax.security.auth.login.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.annotation.Configuration;

        

/**
 *
 * @author 36694320191
 */
public class HibernateUtil {
    private static final SessionFactory sessionfactory;
    public static final String HIBERNATE_SESSION = "hibernate_session"; 
    static {
        try {
            System.out.println("tentando abrir uma session factory!");
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).buildServiceRegistry();
            
            sessionfactory = configuration.buildSessionFactory(serviceRegistry);
             System.out.println("session factory criada corretamente");          
                        
        } catch (Exception ex) {
            System.out.println("ocorreu um erro ao iniciar a session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
    }

    public static SessionFactory getSessionfactory() {
        return sessionfactory;
    }
        
}
