package rdg;

import java.sql.SQLException;

public interface Rdg {
    public void insert() throws SQLException;
    public void update() throws SQLException;
    public void delete() throws SQLException;
    public String toString();
}
