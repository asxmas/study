import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Session session = sessionFactory.openSession();
//        Course course = session.get(Course.class, 1);
//        System.out.println("Название курса: " + course.getName()
//                + "\nДлительность курса: " + course.getDuration()
//                + "\nТип курса: " + course.getType()
//                + "\nКол-во студентов: " + course.getStudentsCount());

//        Course course1= session.get(Course.class, 3);
//        System.out.println("Название курса: " + course1.getName()
//        + "\nФамилия учителя: " + course1.getTeacher().getName()
//        + "\nКоличество учеников: " + course1.getStudentsCount()
//        + "\nСписок учеников: " );
//        course1.getStudents().forEach(s -> System.out.print(s.getName() + ", "));

        Query purchaseListQuery = session.createQuery("from " + PurchaseList.class.getName());
        List<PurchaseList> pl = purchaseListQuery.getResultList();
        for(PurchaseList number : pl){
            Query studentQuery = session.createQuery("from " + Student.class.getName() + " where name = '" + number.getPurchaseListKey().getStudentName() + "'");
            Student student = (Student) studentQuery.getSingleResult();
            Query courseQuery = session.createQuery("from " + Course.class.getName() + " where name = '" + number.getPurchaseListKey().getCourseName() + "'");
            Course course = (Course) courseQuery.getSingleResult();

            Transaction transaction = session.beginTransaction();
            session.save(new LinkedPurchaseList(student.getId(), course.getId()));
            transaction.commit();
        }
        sessionFactory.close();
    }
}
