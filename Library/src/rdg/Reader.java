package rdg;

import main.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class Reader implements Rdg
{
    private Integer id;
    private String name;
    private String surname;
    private Timestamp validUntil;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }



    public boolean isValid() throws SQLException {
        return this.getValidUntil().after(new Timestamp(System.currentTimeMillis()));
    }

    public boolean hasUnpaidPenalty() throws SQLException
    {
        int res = 0;
        try (PreparedStatement s = DBContext.getConnection().prepareStatement(
                "SELECT count(*) FROM penalties WHERE account_id = ? AND is_paid IS FALSE" ))
        {
            s.setInt(1, id);
            try (ResultSet r = s.executeQuery()) {
                r.next();
                res = r.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res > 0;
    }



    @Override
    public void insert() throws SQLException
    {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("INSERT INTO accounts (name,surname,is_admin,valid_until) VALUES (?,?,false,?)", Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            s.setString(++i, name);
            s.setString(++i, surname);
            s.setTimestamp(++i, validUntil);
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

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE accounts SET name = ?, surname = ?, valid_until = ? WHERE id = ? AND is_admin IS FALSE")) {
            int i = 0;
            s.setString(++i, name);
            s.setString(++i, surname);
            s.setTimestamp(++i, validUntil);
            s.setInt(++i, id);

            s.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id is null");
        }

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("DELETE FROM accounts WHERE id = ? AND is_admin IS FALSE")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", validUntil=" + validUntil +
                '}';
    }




}
