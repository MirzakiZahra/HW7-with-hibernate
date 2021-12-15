import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ServiceCar {
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addTrip(String name_of_car, int plaque, String color_of_car) {
        Car car=new Car(name_of_car,plaque,color_of_car);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
        sessionFactory.close();


    }
}
