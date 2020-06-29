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
        String query = "select u from " + User.class.getSimpleName() + " u  ORDER BY 'id' DESC ";

        @SuppressWarnings("unchecked")
        List<User> list = session.getSession().createQuery(query).list();
        list
                .stream()
                .filter(o -> o.getAge() > 25)
//                .sorted((o1, o2) -> o2.getId().compareTo(o1.getId()))
                .sorted(Comparator.comparingLong(User::getAge).reversed())
                .forEach(s -> System.out.println(s.getId() + " " + s.getName() + " " + s.getAge()))
        ;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Процедура поиска записи
     */
    private void recordFind(final Long id) {
        System.out.println("\nЧтение записи таблицы по ID");
        User user = session.getSession().load(User.class, id);
        System.out.println(user.getId() + " " + user.getName() + " " + user.getAge());
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
//    public UserRun() {
//        // Создание сессии
//        session = createHibernateSession();
//        if (session != null) {
//            // Добавление записей в таблицу
//            recordsAdd();
//            // Чтение записей таблицы
//            recordsRead();
//            // Поиск запис+и по идентификатору
//            updateUser();
//            recordFind(1L);
////            removeUser();
//            recordsRead();
//            // Закрытие сессии
//            if (session.isOpen())
//                session.close();
//        }
//    }


    void closeSession(){
        if (session.isOpen()){
            session.close();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {
        UserRun userRun = new UserRun();
        userRun.createHibernateSession();
        userRun.recordsAdd();
        userRun.closeSession();


        userRun.createHibernateSession();
        userRun.recordsRead();
        userRun.closeSession();

        System.exit(0);
    }
}
