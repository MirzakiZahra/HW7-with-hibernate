import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ServiceTrip {
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addTrip(Trip trip) {
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

    public int findTripDestinationLength(int userName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select id from driver where username = '" + userName + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Trip.class);
        int idDriver=(Integer) query.list().get(0);
        sql = "select destinationLength from trip where driverId = '" + idDriver + "' and " +
                "tripStatue = '"+"on"+"'";
         query = session.createSQLQuery(sql);
        query.addEntity(Trip.class);
        int destinationLenghth=(Integer) query.list().get(0);
        session.close();
        return destinationLenghth;
    }

    public int findTripDestinationWidth(int userName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select id from driver where username = '" + userName + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Trip.class);
        int idDriver=(Integer) query.list().get(0);
        sql = "select destinationWidth from trip where driverId = '" + idDriver + "' and " +
                "tripStatue = '"+"on"+"'";
        query = session.createSQLQuery(sql);
        query.addEntity(Trip.class);
        int destinationWidth=(Integer) query.list().get(0);
        session.close();
        return destinationWidth;
    }
    public void changestatue(int userName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select id from driver where username = '" + userName + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Trip.class);
        int idDriver=(Integer) query.list().get(0);
        sql = "select * from trip where driverId = '" + idDriver + "' and " +
                "tripStatue = '"+"on"+"'";
        query = session.createSQLQuery(sql);
        query.addEntity(Trip.class);
        Trip trip=(Trip) query.list().get(0);
        trip.setTripStatus(Trip_status.OFFTRIP);
        session.update(trip);
        transaction.commit();
        session.close();
    }
    public void close(){
        sessionFactory.close();
    }



}
