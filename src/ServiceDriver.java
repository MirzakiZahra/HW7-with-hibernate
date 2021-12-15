import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDriver {
    ServiceTrip serviceTrip = new ServiceTrip();
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void addDriver(int id) {
        Driver driver = serviceTrip.findDriverById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(driver);
        transaction.commit();
        session.close();


    }
    public void checkExit(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver where id = :id";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("id", id);
        Driver driver = (Driver) query.list().get(0);
        session.close();
    }
    public Driver getStatus(int username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
            String sql = "select tripStatue from driver where username= :username";

        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username", username);
        Driver driver = (Driver) query.list().get(0);
        session.close();
        return driver;

    }
    public void changeStatue() {
        ;
    }
    public void increase(int id, int fee) {
       Driver driver = serviceTrip.findDriverById(id);
       Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        driver = session.load(Driver.class, driver.getId());
        int temp = driver.getBalance() + fee;
        driver.setBalance(temp);
        session.update(driver);
        transaction.commit();
        session.close();


    }
}
