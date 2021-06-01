package entities;

public enum Genre {
    Fantasy("Fantasy"),
    SiFi ("SiFi"),
    Romance ("Romance"),
    Western("Western"),
    Dystopian ("Dystopian"),
    Contemporary ("Contemporary"),
    Thriller ("Thriller");


    private String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
