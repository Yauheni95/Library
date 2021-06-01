package dao;

import entities.Author;
import entities.Book;
import entities.Genre;
import util.DBConnector;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    AuthorDao authorDao;

    public BookDaoImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @Override
    public Book create(Book book) {
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("INSERT INTO books (id, title, genre,author, dateCreated) VALUES (?,?,?,?,?)");
            stmt.setInt(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getGenre().getName());
            stmt.setString(4, book.getAuthor().getFirstName() +" "+ book.getAuthor().getLastName());
            book.setDateCreated(LocalDate.now());
            stmt.setDate(5, Date.valueOf(book.getDateCreated()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book read(String title) {
        Book book = new Book();
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("SELECT * FROM books WHERE title = (?)");
            stmt.setString(1, title);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(authorDao.read(resultSet.getString("author")));
                book.setDateCreated(LocalDate.parse(resultSet.getString("dateCreated")));
                book.setGenre(resultSet.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book read(int id) {
        Book book = new Book();
        Author author;
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("SELECT * FROM books WHERE id = (?)");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                author=authorDao.read(resultSet.getString(4));
                book.setAuthor(author);
                book.setDateCreated(LocalDate.parse(resultSet.getString("dateCreated")));
                book.setGenre(resultSet.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book update(Book newBook) {
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("UPDATE books SET title= ?, genre= ? WHERE id = ?");
            stmt.setString(1, newBook.getTitle());
            stmt.setString(2, newBook.getGenre().getName());
            stmt.setInt(3, newBook.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return read(newBook.getTitle());
    }

    @Override
    public boolean delete(int id) {
        boolean box = false;
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("SELECT * FROM books WHERE id = ?");
            stmt.setInt(1, id);
            if (stmt.executeQuery().next()) {
                stmt = DBConnector.connection.prepareStatement("DELETE FROM books WHERE id = ?");
                stmt.setInt(1, id);
                stmt.execute();
                box = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return box;
    }

    @Override
    public List<Book> realAll() {
        List<Book> list = new ArrayList<>();
        int id;
        String title;
        Author author;
        Genre genre=null;
        LocalDate dateCreated;
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                id=resultSet.getInt("id");
                title=resultSet.getString("title");
                author=authorDao.read(resultSet.getString("author"));
                dateCreated=LocalDate.parse(resultSet.getString("dateCreated"));
                genre= Genre.valueOf(((resultSet.getString("genre"))));
                Book book = new Book(id,title,genre,author,dateCreated);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

