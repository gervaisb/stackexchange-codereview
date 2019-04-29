package q209963;

public class    Person {
    private final String firstName;
    private final String lastName;
    private final int age;

    public Person(String first, String last, int age){
        this.firstName = first;
        this.lastName = last;
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }
}
