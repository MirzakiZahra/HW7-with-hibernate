import javax.persistence.*;
import java.sql.SQLException;

@Entity

public class Passengers extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    private int password;
    int balance = 0;

    @OneToOne(cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    Driver driver = new Driver();
    Trip_status trip_status;
    // boolean trip;

    public Trip_status getTrip_status() {
        return trip_status;
    }

    public void setTrip_status(Trip_status trip_status) {
        this.trip_status = trip_status;
    }

    public Driver getDriver() {
        return driver;
    }

    public Passengers() {

    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passengers(String name, int age, String address, int username, int width, int length, int password, int balance, Driver driver, Trip_status trip_status) {
        super(name, age, address, username, width, length);
        this.password = password;
        this.balance = balance;
        this.driver = driver;
        this.trip_status = trip_status;

    }

    public Passengers(int password, int balance, Driver driver, Trip_status trip_status) {
        this.password = password;
        this.balance = balance;
        this.driver = driver;
        this.trip_status = trip_status;

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void increase_balance(int fund) {
        int temp = this.getBalance() + fund;
        this.setBalance(temp);


    }

    public Trip requestCash(Trip trip, int destinationLength, int destinationWidth,
                            int originLength, int originWidth) {
        trip.setDestinationLength(destinationLength);
        trip.setDestinationWidth(destinationWidth);
        trip.setOriginalLength(originLength);
        trip.setOrigenWidth(originWidth);
        trip.setPassengers(this);
        return trip;

    }

    public Trip requestOnline(Trip trip, int destinationLength, int destinationWidth,
                              int originLength, int originWidth, ServicePassenger servicePassenger,
                              int username) throws SQLException {
        trip.setDestinationLength(destinationLength);
        trip.setDestinationWidth(destinationWidth);
        trip.setOriginalLength(originLength);
        trip.setOrigenWidth(originWidth);
        if (servicePassenger.findBalane(username) >= trip.calculateCost()) {
            trip.setPassengers(this);
            this.decrease(trip.calculateCost());
        } else {
            System.out.println("Please Increase your Balance");
        }
        return trip;


    }

    public void decrease(int fund) {
        int number = this.balance - fund;
        this.setBalance(number);
    }

}
