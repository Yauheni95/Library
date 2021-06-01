package service;

import entities.Book;
import java.util.Comparator;
import java.util.List;

public interface BookService {
    boolean addBook(int id, String title, String genre, String author);

    List<Book> getListOfBooks(Comparator<Book> comparator);

    boolean deleteBookFromLibrary(int id);

    boolean editBook(int id, String newTitle, String newGenre);

}
