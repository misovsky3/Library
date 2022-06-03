package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Book implements Rdg{
    private Integer id;
    private String title;
    private String authorName;
    private String authorSurname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title= '" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                '}';
    }

    @Override
    public void insert() throws SQLException
    {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("INSERT INTO books (title, author_name, author_surname) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            s.setString(++i, title);
            s.setString(++i, authorName);
            s.setString(++i, authorSurname);
            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                id = r.getInt(1);
            }
        }
    }

    @Override
    public void update() throws SQLException {
        if (id == null)
            throw new IllegalStateException("Id is null");

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE books SET title = ?, author_name = ?, author_surname = ? WHERE id = ?")) {
            int i = 0;
            s.setString(++i, title);
            s.setString(++i, authorName);
            s.setString(++i, authorSurname);
            s.setInt(++i, id);

            s.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id is null");
        }

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("DELETE FROM books WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }

    }
}
