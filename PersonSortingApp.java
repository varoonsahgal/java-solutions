package com.intuit;

import java.util.ArrayList;
import java.util.Collections;

class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person o) {
        // First, compare by last name
        int lastNameComparison = this.lastName.compareTo(o.lastName);

        if (lastNameComparison != 0) {
            return lastNameComparison;
        } else {
            // If last names are the same, compare by first name
            return this.firstName.compareTo(o.firstName);
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + age + " years old)";
    }
}

public class PersonSortingApp {
    public static void main(String[] args) {
        ArrayList<Person> myFamily = new ArrayList<>();
        myFamily.add(new Person("Dana", "Wyatt", 63));
        myFamily.add(new Person("Zachary", "Westly", 31));
        myFamily.add(new Person("Elisha", "Aslan", 14));
        myFamily.add(new Person("Ian", "Auston", 16));
        myFamily.add(new Person("Zephaniah", "Hughes", 9));
        myFamily.add(new Person("Ezra", "Aiden", 17));
        myFamily.add(new Person("Frank", "Aiden", 17));

        // Sorting the list
        Collections.sort(myFamily);//

        // Displaying the sorted list
        for (Person person : myFamily) {
            System.out.println(person);
        }
    }
}
