package dao;

import entities.Book;
import java.util.List;

public interface BookDao {
    Book create (Book book);

    Book read (String title);

    Book read (int id);

    Book update (Book book);

    boolean delete (int id);

    List<Book> realAll();

}
