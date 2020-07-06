package by.itstep.hibrnateLessons2020.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtils {

    private static SessionFactory sessionFactory;
    private static Session session;

    private static SessionFactory buildSessionFactory(){
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Project.class);
            System.out.println("Конфигурация хибернейт создана.");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("ServiceRegistry создан");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;


        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }

    }


    private static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            sessionFactory = buildSessionFactory();
            System.out.println("SessionFactory is created");
        }
        return sessionFactory;
    }

    public static Session getSession(){

        try {

            session = getSessionFactory().openSession();
            System.out.println("Session is open");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return session;
    }
}
