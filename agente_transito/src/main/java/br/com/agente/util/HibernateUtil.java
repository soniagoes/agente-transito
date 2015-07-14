package br.com.agente.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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
