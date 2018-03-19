package com.abacus.resources.data;

public class Person extends Data {

    private String name;
    private int age;
    private String locale;

    public Person() {
        this.name = "";
        this.age = 0;
        this.locale = "";
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
