package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean addBook(Book book) {
        boolean box = true;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                box = false;
                break;
            }
        }
        if (box)
            books.add(book);
        return box;
    }

    public List<Book> getListOfBooks() {
        return this.books;
    }

    public List<Author> getListOfAuthors() {
        Set<Author> authorSet = new HashSet<>();
        for (Book book : this.books) {
            authorSet.add(book.getAuthor());
        }
        return new ArrayList<>(authorSet);
    }

    public List<Book> getListOfBooksByAuthor(Author author) {
        List<Book> booksList = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getAuthor().equals(author)) {
                booksList.add(book);
            }
        }
        return booksList;
    }

    public List<Book> getListOfBooksByAuthor(String author) {
        List<Book> booksList = new ArrayList<>();
        for (Book book : this.books) {
            if ((book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()).equals(author)) {
                booksList.add(book);
            }
        }
        return booksList;
    }

    public boolean removeBook(int id) {
        boolean box = false;
        int counter = 0;
        for (Book a : books) {
            if (a.getId() == id) {
                box = true;
                break;
            }
            counter++;
        }
        if (box)
            this.books.remove(counter);
        return box;
    }

    public boolean editBook(int id, String title, String genre) {
        boolean flag = false;
        for (int i = 0; !flag && i < this.books.size(); i++) {
            if (id == this.books.get(i).getId()) {
                this.books.get(i).setTitle(title);
                this.books.get(i).setGenre(genre);
                flag = true;
            }
        }
        return flag;
    }

}
