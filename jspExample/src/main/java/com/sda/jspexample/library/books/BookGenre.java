package com.sda.jspexample.library.books;

public enum BookGenre {
    EPOS("Epopeja"), DRAMA("Dramat"), THRILLER("Thriller"),
    COMEDY("Komedia"), STORY("Powieść");

    private String friendlyName;

    BookGenre(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
