import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ServiceTrip {
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addTrip(int idDriver, int idPassenger) {
       Driver driver= findDriverById(idDriver);
      Passengers passengers=  findPassengerById(idPassenger);
        Trip trip=new Trip(passengers, driver);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(trip);
        transaction.commit();
        session.close();


    }

    public Driver findDriverById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver where id = :id";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("id", id);
        Driver driver = (Driver) query.list().get(0);
        session.close();
        return driver;
    }

    public Passengers findPassengerById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from passenger where id = :id";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Passengers.class);
        query.setParameter("id", id);
        Passengers passengers = (Passengers) query.list().get(0);
        session.close();
        return passengers;
    }
    public void find

}
