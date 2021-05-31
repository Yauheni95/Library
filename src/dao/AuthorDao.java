package dao;

import entities.Author;
import java.util.List;

public interface AuthorDao {
    Author create (Author author);

    Author read (String name);

    Author update (Author author);

    boolean delete (int id);

    List<Author> realAll();
}
