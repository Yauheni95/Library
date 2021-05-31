package dao;

import entities.Author;
import util.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao{
    @Override
    public Author create(Author author) {
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("INSERT INTO authors (first_name, last_name) VALUES (?,?)");
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.execute();
            stmt = DBConnector.connection.prepareStatement("SELECT id_authors FROM authors  WHERE first_name = ? AND last_name=?");
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next())
                author.setId(resultSet.getInt("id_authors"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Author read(String name) {
        Author author=new Author();
        String [] strings =name.split(" ");
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("SELECT * FROM authors WHERE first_name = (?) AND last_name= (?)");
            stmt.setString(1, strings[0]);
            stmt.setString(2, strings[1]);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                author.setId(resultSet.getInt("id_authors"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Author update(Author author) {
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("UPDATE authors SET title= ?, genre= ? WHERE id = ?");
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.setInt(3, author.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public boolean delete(int id) {
        boolean box = false;
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("SELECT * FROM authors WHERE id = ?");
            stmt.setInt(1, id);
            if (stmt.executeQuery().next()) {
                stmt = DBConnector.connection.prepareStatement("DELETE FROM authors WHERE id = ?");
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
    public List<Author> realAll() {
        List<Author> list = new ArrayList<>();
        Author author = new Author();
        try {
            PreparedStatement stmt = DBConnector.connection.prepareStatement("SELECT * FROM authors");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                author.setId(resultSet.getInt("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                list.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
