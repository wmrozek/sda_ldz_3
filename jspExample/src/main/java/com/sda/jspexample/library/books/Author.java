package com.sda.jspexample.library.books;

public class Author {
    private String name;
    private String surname;
    private String countryCode;

    public Author(String name, String surname, String countryCode) {
        this.name = name;
        this.surname = surname;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return surname +" "+name;
    }
}
