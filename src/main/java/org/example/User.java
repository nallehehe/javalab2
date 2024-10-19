package org.example;

public class User {
//    private static final AtomicInteger counter = new AtomicInteger(0);
//    private int id;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
//        this.id = counter.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /*public int getId() {
        return id;
    }
     */
}
