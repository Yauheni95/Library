package service;

import dao.AuthorDao;
import dao.BookDao;
import entities.Author;
import entities.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;


    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Override
    public boolean addBook(int id, String title, String genre, String author) {
        if (bookDao.read(id).getId() == id)
            return false;
        else {
            String[] array = author.split(" ");
            Book book = new Book();
            Author author1 = new Author(array[0], array[1]);
            book.setId(id);
            book.setTitle(title);
            book.setGenre(genre);
            book.setAuthor(author1);
            bookDao.create(book);
            if (authorDao.read(author)==null)
            authorDao.create(author1);
            return true;
        }
    }

    @Override
    public List<Book> getListOfBooks(Comparator<Book> comparator) {
        return bookDao.realAll().stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public boolean deleteBookFromLibrary(int id) {
        if (bookDao.read(id).getId() != id)
            return false;
        else {
            bookDao.delete(id);
            return true;
        }
    }

    @Override
    public boolean editBook(int id, String newTitle, String newGenre) {
        if (bookDao.read(id).getId() != id)
            return false;
        else {
            Book book = new Book();
            book.setId(id);
            book.setTitle(newTitle);
            book.setGenre(newGenre);
            bookDao.update(book);
            return true;
        }
    }
}
