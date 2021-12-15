import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class Management {
    List<Driver> drivers = new ArrayList<Driver>();
    List<Passengers> passengers = new ArrayList<Passengers>();
    public Driver minDistance(int passengerWidth, int passengerLength){
        int min=0;
        int indicator=0;
        for (Driver driver : drivers){
            if (indicator == 0){
                min=driver.calculateDistance(passengerWidth,passengerLength);
                indicator++;
            }else {
                if (driver.calculateDistance(passengerWidth,passengerLength) < min){
                    min = driver.calculateDistance(passengerWidth,passengerLength);
                }else{
                    min=min;
                }
            }
        }for (Driver driver : drivers){
            if (driver.calculateDistance(passengerWidth,passengerLength) == min){
                return driver;
            }
        }
        return null;
    }
    public Driver findDriver(int username){
        for (Driver driver : drivers){
            if (driver.getUsername() == username){
                return driver;
            }
        }
        return null;
    }
    public Passengers findPassenger(int driverUsername){
        for (Passengers passenger : passengers){
            if (passenger.getDriver().getUsername() == driverUsername){
                return passenger;
            }
        }
        return null;
    }
    public Passengers findPassengerByUsername(int username){
        for (Passengers passenger : passengers){
            if (passenger.getUsername() == username){
                return passenger;
            }
        }
        return null;
    }
    public void finishTrip(Driver driver, int width, int length, ServiceDriver serviceDriver,
                           ServiceTrip serviceTrip, int username,
                          ServicePassenger servicePassenger) throws SQLException {
        Passengers passenger = new Passengers();
        width=serviceTrip.findTripDestinationWidth(username);
        length=serviceTrip.findTripDestinationLength(username);
        driver.setWidth(width);
        driver.setLength(length);
        driver.setTripStatue(Trip_status.OFFTRIP);
        passenger = this.findPassenger(driver.getUsername());
        passenger.setTrip_status(Trip_status.OFFTRIP);
        serviceDriver.changeStatue(username);
       serviceDriver.changeDestination(length,width,username);
        serviceTrip.changestatue(username);
       
    }
}
