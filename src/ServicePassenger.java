import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ServicePassenger {
    ServiceTrip serviceTrip = new ServiceTrip();
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addPassenger(Passengers passengers) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passengers);
        transaction.commit();
        session.close();


    }

    public int checkExitOfPassenger(int username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from passengers where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Passengers.class);
        query.setParameter("username", username);
        int output = (Integer) query.list().size();
        session.close();
        return output;
    }

    public void increaseBalance(int username, int fee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from passengers where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Passengers.class);
        query.setParameter("username", username);
        Passengers passengers = (Passengers) query.list().get(0);
        int temp = passengers.getBalance() + fee;
        passengers.setBalance(temp);
        session.update(passengers);
        transaction.commit();
        session.close();


    }

    public void decreaseBalance(int username, int fee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from passengers where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Passengers.class);
        query.setParameter("username", username);
        Passengers passengers = (Passengers) query.list().get(0);
        int temp = passengers.getBalance() - fee;
        passengers.setBalance(temp);
        session.update(passengers);
        transaction.commit();
        session.close();


    }
    public int findBalane(int username){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from passengers where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username", username);
       Passengers passengers = (Passengers) query.list().get(0);
       int temp= passengers.getBalance();
       return temp;
    }
}
