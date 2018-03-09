package com.abacus.resources.data;

public class Person extends Data {

    private final String name;
    private final int age;
    private final String locale;


    public Person(long id, String name, int age, String locale) {
        super(id);
        super.setType("Person");
        this.name = name;
        this.age = age;
        this.locale = locale;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getLocale() {
        return locale;
    }
}
