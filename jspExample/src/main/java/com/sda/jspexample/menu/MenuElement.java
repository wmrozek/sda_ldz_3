package com.sda.jspexample.menu;

public enum MenuElement {
    INDEX("Strona główna", "/"),
    ADD_BOOKS("Dodawanie książek", "/books/add"),
    BOOKS_LIST("Lista książek", "/books"),
    BOOK_RESERVATION("Wypożyczanie książki", "/books/reservation");

    MenuElement(String elementName, String url) {
        this.elementName = elementName;
        this.url = url;
    }

    private String elementName;
    private String url;

    public String getElementName() {
        return elementName;
    }

    public String getUrl() {
        return url;
    }
}
