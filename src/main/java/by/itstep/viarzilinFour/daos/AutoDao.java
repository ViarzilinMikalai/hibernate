package by.itstep.viarzilinFour.daos;

import by.itstep.viarzilinFour.domain.Auto;
import by.itstep.viarzilinFour.domain.User;
import by.itstep.viarzilinFour.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoDao {

    public void save(Auto auto) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(auto);
        tx1.commit();
        session.close();
    }

    public Auto findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Auto auto = session.get(Auto.class, id);
        session.close();
        return auto;

//        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Auto.class, id);
    }

    public List<Auto> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Auto").list();
    }
}
