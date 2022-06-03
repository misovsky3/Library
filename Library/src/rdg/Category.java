package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Category implements Rdg{
    private Integer id;
    private String category;
    private Integer term;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", term=" + term +
                '}';
    }

    @Override
    public void insert() throws SQLException {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("INSERT INTO categories (category,term) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            s.setString(++i, category);
            s.setInt(++i, term);
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

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE categories SET category = ?, term = ? WHERE id = ?")) {
            int i = 0;
            s.setString(++i, category);
            s.setInt(++i, term);
            s.setInt(++i, id);

            s.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id is null");
        }

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("DELETE FROM categories WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }

    }
}
