package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Warehouse implements Rdg{
    private Integer id;
    private String name;
    private String address;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public void insert() throws SQLException {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("INSERT INTO warehouses (name, address) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            s.setString(++i,name);
            s.setString(++i,address);

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

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE warehouses SET name = ?, address = ? WHERE id = ?")) {
            int i = 0;

            s.setString(++i,name);
            s.setString(++i,address);
            s.setInt(++i, id);
            s.executeUpdate();
        }

    }

    @Override
    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id is null");
        }

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("DELETE FROM warehouses WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}
