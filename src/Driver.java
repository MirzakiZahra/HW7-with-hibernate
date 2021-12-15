import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Driver extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }
    Car car = new Car();
    private int balance;
    private Trip_status tripStatue;
    private int Width;
    private int Length;
    private PaymentType paymentType;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }



    public Trip_status getTripStatue() {
        return tripStatue;
    }

    public void setTripStatue(Trip_status tripStatue) {
        this.tripStatue = tripStatue;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver(String name, int age, String address, int username, int width, int length, Car car, int balance, Trip_status tripStatue, int width1, int length1) {
        super(name, age, address, username, width, length);
        this.car = car;
        this.balance = balance;
        this.tripStatue = tripStatue;
        Width = width1;
        Length = length1;
    }

    public Driver(Car car, int balance, Trip_status tripStatue, int width, int length) {
        this.car = car;
        this.balance = balance;
        this.tripStatue = tripStatue;
        Width = width;
        Length = length;
    }

    public Driver(Car car) {
        this.car = car;
    }

    public Driver() {
    }

    public int calculateDistance(int passengerWidth,int passengerLength){
        return ((this.Length-passengerLength)+(this.Width-passengerWidth));
    }

    @Override
    public String toString() {
        return "Driver{" +
                "car=" + car +
                ", balance=" + balance +
                ", tripStatue=" + tripStatue +
                ", Width=" + Width +
                ", Length=" + Length +
                ", paymentType=" + paymentType +
                '}';
    }
}
