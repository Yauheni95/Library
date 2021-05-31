package entities;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private Genre genre;
    private Author author;
    private LocalDate dateCreated;

    public Book(int id, String title, Genre genre, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public Book() {
    }

    public Book(int id, String title, Genre genre, Author author, LocalDate dateCreated) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setGenre(String genre) {
        this.genre=Genre.valueOf(genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", author=" + author +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
