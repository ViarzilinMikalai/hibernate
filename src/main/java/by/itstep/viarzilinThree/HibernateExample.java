package by.itstep.viarzilinThree;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateExample
{
    private  Session  session = null;
    //---------------------------------------------------------------
    private Session createHibernateSession()
    {
        SessionFactory   sf  = null;
        ServiceRegistry  srvc = null;
        try {
            Configuration conf = new Configuration().configure();
            conf.addAnnotatedClass(User.class);
            conf.addAnnotatedClass(Auto.class);

            srvc = new StandardServiceRegistryBuilder()
                    .applySettings(conf.getProperties()).build();
            sf = conf.buildSessionFactory(srvc);
            session = sf.openSession();
            System.out.println("Создание сессии");
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        return session;
    }
    //---------------------------------------------------------------
    private void saveUser()
    {
        if (session == null)
            return;

        User user1 = new User();
        user1.setName ("Иван");
        user1.setLogin("ivan");

        User user2 = new User();
        user2.setName ("Сергей");
        user2.setLogin("serg"  );

        Transaction trans = session.beginTransaction();
        session.save(user1);
        session.save(user2);

        Auto auto = new Auto();
        auto.setName("Volvo");
        auto.setUser(user1);
        session.saveOrUpdate(auto);

        auto = new Auto();
        auto.setName("Skoda");
        auto.setUser(user1);
        session.saveOrUpdate(auto);

        session.flush();
        trans.commit();
        /*
         * Обновление detached-объект (выполнение select к БД)
         * и преобразование его в persistent
         */
        session.refresh(user1);

        System.out.println("user1 : " + user1.toString());
        System.out.println("user2 : " + user2.toString());
    }
    //---------------------------------------------------------------
    public HibernateExample()
    {
        // Создание сессии
        session = createHibernateSession();
        if (session != null) {
            System.out.println("session is created");
            // Добавление записей в таблицу
            saveUser();
            // Закрытие сессии
            session.close();
        } else {
            System.out.println("session is not created");
        }
    }
    //---------------------------------------------------------------
    public static void main(String[] args)
    {
        new HibernateExample();
        System.exit(0);
    }
}