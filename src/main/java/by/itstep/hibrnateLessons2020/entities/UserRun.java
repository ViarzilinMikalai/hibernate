package by.itstep.hibrnateLessons2020.entities;

import by.itstep.viarzilinTwo.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Comparator;
import java.util.List;

public class UserRun {
    private Session session = null;
    User user1 = new User("Ivan", 25);
    User user2 = new User("Alex", 26);
    User user3 = new User("Vovan", 22);
    Cars car1 = new Cars("red", user1);
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура создания сессии
     *
     * @return org.hibernate.Session
     */
    private Session createHibernateSession() {
        SessionFactory sessionFactory = null;
        ServiceRegistry serviceRegistry = null;
        try {
            try {
                Configuration cfg = new Configuration();
                cfg.addAnnotatedClass(User.class);
                cfg.addAnnotatedClass(Cars.class);
                serviceRegistry = new StandardServiceRegistryBuilder().
                        applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
            session = sessionFactory.openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return session;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура добавления записей в таблицу
     */

    private void recordsAdd() {
        try {
            System.out.println("Добавление записи в таблицу БД");
            Transaction tx = session.beginTransaction();
            session.save(user1);
            session.save(user2);
            session.save(user3);
            session.save(car1);
            tx.commit();
//            session.flush();
            System.out.println("\tЗаписи добавлены");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура чтения записей
     */
    private void recordsRead() {
        System.out.println("\nЧтение записей таблицы");
        String query = "select u from " + User.class.getSimpleName() + " u ";
        String queryCars = "select c from " + Cars.class.getSimpleName() + " c ";

        @SuppressWarnings("unchecked")
        List<User> list = session.getSession().createQuery(query).list();
        List<Cars> carslist = session.getSession().createQuery(queryCars).list();
        list
                .stream()
//                .filter(o -> o.getAge() > 25)
//                .sorted((o1, o2) -> o1.getId().compareTo(o2.getId()))
                .sorted(Comparator.comparingLong(User::getId))
                .forEach(s -> System.out.println(s.getId() + " " + s.getName() + " " + s.getAge()))
        ;

        carslist
                .stream()
                .forEach(s -> System.out.println(s.getId() + " " + s.getColor() + " " + s.getUser().toString()));
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура поиска записи
     */
    private void recordFind(final Long id) {
        System.out.println("\nЧтение записи таблицы по ID");
        User user = session.getSession().load(User.class, id);
        Cars car = session.getSession().load(Cars.class, id);
        System.out.println(user.getId() + " " + user.getName() + " " + user.getAge());
        System.out.println(car.getId() + " " + car.getColor() + " " + car.getUser().toString());
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Обновление записи в БД
     */
    private void updateUser() {
        try {
            User user4 = new User("Victor", 33);
            user1.setName("Иван");
            System.out.println("Изменение записи в таблице БД");
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(user1);
            session.saveOrUpdate(user4);
            tx.commit();
            System.out.println("\tЗаписи обновлены");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void removeUser() {
        try {

            System.out.println("Изменение записи в таблице БД");
            Transaction tx = session.beginTransaction();
            session.delete(user2);
            tx.commit();
            System.out.println("\tЗаписи обновлены");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Конструктор класса
     */
    public UserRun() {
        // Создание сессии
        session = createHibernateSession();
        if (session != null) {
            // Добавление записей в таблицу
            recordsAdd();
            // Чтение записей таблицы
            recordsRead();
            // Поиск запис+и по идентификатору
//            updateUser();
            session.close();
            session = createHibernateSession();


            System.out.println("\nЧтение записи таблицы по ID");
            Cars car = session.getSession().load(Cars.class, 1L);
            session.close();
//            System.out.println(user.getId() + " " + user.getName() + " " + user.getAge());
            System.out.println(car.getId() + " " + car.getColor() + " " + car.getUser().toString());


//            recordFind(1L);
//            removeUser();
//            recordsRead();
            // Закрытие сессии
            if (session.isOpen())
                session.close();
        }
    }


    void closeSession(){
        if (session.isOpen()){
            session.close();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {
//        UserRun userRun = new UserRun();
//        userRun.createHibernateSession();
//        userRun.recordsAdd();
//        userRun.closeSession();
//
//
//        userRun.createHibernateSession();
//        userRun.recordsRead();
//        userRun.closeSession();
        new UserRun();
        System.exit(0);
    }
}
