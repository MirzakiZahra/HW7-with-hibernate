import javax.persistence.*;


public class Person {

    private int id;
    private String name;
    private int age;
    private String address;
    private int username;
    private int width;
    private int length;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public Person(String name, int age, String address, int username, int width, int length) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.username = username;
        this.width = width;
        this.length = length;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", username=" + username +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}
