package by.itstep.hibrnateLessons2020.entities;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static Session session;





    public static void main(String[] args) {
        Project project = new Project("rfrfrf");
        Set<Project> projectSet = new HashSet<Project>();
        projectSet.add(project);
        Employee employee = new Employee("uiuiui", "erererer", projectSet);
        session = HibernateUtils.getSession();
        Transaction tx =session.beginTransaction();
//        session.saveOrUpdate(project);
        System.out.println(project.getName());
        session.saveOrUpdate(employee);
        tx.commit();
        session.close();

        session = HibernateUtils.getSession();
        String query = "select p from Project p ";


        @SuppressWarnings("unchecked")
        List<Project> list = session.createQuery(query).list();

        list
                .stream()
                .forEach(o -> System.out.println(o.getName()));

        session.close();
        System.exit(0);
    }
}
