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
    public int checkExitOfDriver(int username){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver where username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        query.setParameter("username",username);
        int output = (Integer) query.list().size();
        session.close();
        return output;
    }
    public void showDriver(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from driver ";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Driver.class);
        List<Driver> driverList = query.list();
        driverList.stream().forEach(i-> System.out.println(i));
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
