import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ServiceDriver {
    ServiceTrip serviceTrip = new ServiceTrip();
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addDriver(Driver driver) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(driver);
        transaction.commit();
        session.close();


    }

    public int checkExitOfDriver(int username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username", username);
        int output = (Integer) query.list().size();
        session.close();
        return output;
    }

    public void showDriver() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver ";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        List<Driver> driverList = query.list();
        driverList.stream().forEach(i -> System.out.println(i));
    }

    public String getStatus(int username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select tripStatue from driver where username= :username";

        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username", username);
        String tripStatue = (String) query.list().get(0);
        session.close();
        return tripStatue;

    }

    public void changeStatue(int username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select driver where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username", username);
        Driver driver = (Driver) query.list().get(0);
        if (this.getStatus(username) == "on") {
            driver.setTripStatue(Trip_status.OFFTRIP);
        } else {
            driver.setTripStatue(Trip_status.ONTRIP);
        }
        session.update(driver);
        transaction.commit();
        session.close();
    }



    public void increase(int username, int fee) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username", username);
       Driver driver = (Driver) query.list().get(0);
       int temp= driver.getBalance()+fee;
       driver.setBalance(temp);
       session.update(driver);
       transaction.commit();
        session.close();


    }
    public void changeDestination(int username,int length, int width){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username", username);
        Driver driver = (Driver) query.list().get(0);
        driver.setWidth(width);
        driver.setLength(length);
        session.update(driver);
        transaction.commit();
        session.close();
    }
}
