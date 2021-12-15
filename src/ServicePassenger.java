import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ServicePassenger {
    ServiceTrip serviceTrip = new ServiceTrip();
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addPassenger(int id) {
        Passengers passengers = serviceTrip.findPassengerById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passengers);
        transaction.commit();
        session.close();


    }
    public void checkExit(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from passengers where id = :id";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Passengers.class);
        query.setParameter("id", id);
      Passengers passengers = (Passengers) query.list().get(0);
        session.close();
    }
    public void increaseBalance(int id, int fee) {
       Passengers passengers = serviceTrip.findPassengerById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        passengers = session.load(Passengers.class, passengers.getId());
        int temp = passengers.getBalance() + fee;
        passengers.setBalance(temp);
        session.update(passengers);
        transaction.commit();
        session.close();


    }
    public void decreaseBalance(int id, int fee) {
        Passengers passengers = serviceTrip.findPassengerById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        passengers = session.load(Passengers.class, passengers.getId());
        int temp = passengers.getBalance() - fee;
        passengers.setBalance(temp);
        session.update(passengers);
        transaction.commit();
        session.close();


    }
}
