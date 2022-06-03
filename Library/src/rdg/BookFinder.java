package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookFinder {


    private static final BookFinder INSTANCE = new BookFinder();
    public static BookFinder getINSTANCE() {
        return INSTANCE;
    }
    private BookFinder(){}


    public List<Book> findAll(int max) throws SQLException {
        List<Book> res = new ArrayList<>();
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM books LIMIT ?")) {
            s.setInt(1,max);
            try (ResultSet r = s.executeQuery()) {
                while(r.next()) {
                    Book rr = new Book();
                    rr.setId(r.getInt("id"));
                    rr.setTitle(r.getString("title"));
                    rr.setAuthorName(r.getString("author_name"));
                    rr.setAuthorSurname(r.getString("author_surname"));
                    res.add(rr);
                }

            }
        }
        return res;
    }

    public Book findById(int id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM books WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Book rr = new Book();
                    rr.setId(r.getInt("id"));
                    rr.setTitle(r.getString("title"));
                    rr.setAuthorName(r.getString("author_name"));
                    rr.setAuthorSurname(r.getString("author_surname"));

                    if (r.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return rr;
                } else {
                    return null;
                }
            }
        }
    }

    public Book findByTitle(String title) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM books WHERE title = ?")) {
            s.setString(1, title);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Book rr = new Book();
                    rr.setId(r.getInt("id"));
                    rr.setTitle(r.getString("title"));
                    rr.setAuthorName(r.getString("author_name"));
                    rr.setAuthorSurname(r.getString("author_surname"));

                    if (r.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return rr;
                } else {
                    return null;
                }
            }
        }
    }

    public Book findByAuthor(String name, String surname) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM books WHERE author_name = ? AND author_surname=? ")) {
            s.setString(1, name);
            s.setString(2, surname);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Book rr = new Book();
                    rr.setId(r.getInt("id"));
                    rr.setTitle(r.getString("title"));
                    rr.setAuthorName(r.getString("author_name"));
                    rr.setAuthorSurname(r.getString("author_surname"));

                    if (r.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return rr;
                } else {
                    return null;
                }
            }
        }
    }



}
